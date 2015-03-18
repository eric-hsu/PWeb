package com.jinxin.service.paygateway.online.bcm.impl;

import java.io.File;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bocom.netpay.b2cAPI.BOCOMB2CClient;
import com.bocom.netpay.b2cAPI.BOCOMB2COPReply;
import com.bocom.netpay.b2cAPI.BOCOMSetting;
import com.bocom.netpay.b2cAPI.OpResultSet;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.MerchantServiceI;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.bcm.util.BcmConstants;
import com.jinxin.service.paygateway.online.bcm.util.BcmSendBean;
import com.jinxin.service.paygateway.online.bcm.util.BcmUtils;

/**
 * @className:BcmPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("bcmService")
@Scope("prototype")
public class BcmPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(BcmPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker bcmWorker;
	@Autowired
	private MerchantServiceI merchantService;
	
	/**
     * 支付前的操作，用于在线支付(页面支付)
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	public OperateResult prefixPayment(Traderecord traderecord,HttpServletRequest request) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		BcmSendBean sendDataBean = new BcmSendBean();
		try{
			 init();

			 String pweb_server_url  = StaticMapUtil.paramMap.get("pweb_server_url");
			 if(StringUtils.isBlank(pweb_server_url)){
				operateResult.setMessage("未配置银行通知地址。");
				return operateResult;
			 }
				
			 sendDataBean.setServiceUrl(BOCOMSetting.OrderURL);
			 sendDataBean.setMerNo(BOCOMSetting.MerchantID);
			 sendDataBean.setTranType(BcmConstants.SEND_TRAN_TYPE);
			 sendDataBean.setIntefaceVersion(BcmConstants.SEND_INTERFACE_VERSION);
			 sendDataBean.setCurType(BcmConstants.SEND_CUR_TYPE);
			 sendDataBean.setNotifyType(BcmConstants.SEND_NOTIFY_TYPE_YES);
			 sendDataBean.setMerURL(pweb_server_url+"/bcmController/backNotify.do");
			 sendDataBean.setGoodsURL(pweb_server_url+"/bcmController/frontNotify.do");
			 sendDataBean.setTrNo(traderecord.getTrNo());
			 sendDataBean.setAmount(String.valueOf(traderecord.getTrAmount()));

			 SimpleDateFormat format = new SimpleDateFormat("HHmmss");
		     Date date = new Date();
		        
			 sendDataBean.setOrderDate(DateTimeUtils.formatDate(date, "yyyyMMdd"));
			 sendDataBean.setOrderTime(format.format(date));
			 sendDataBean.setPayBatchNo("");
			 sendDataBean.setNetType(BcmConstants.SEND_NET_TYPE);
			 sendDataBean.setSign(BcmUtils.getSignMsg(sendDataBean));
			 
			request.setAttribute("sendDataBean", sendDataBean);
			operateResult.setSuccess(true);
		}catch(Exception e){
			operateResult.setMessage(e.getMessage());
			return operateResult;
		}
		return operateResult;
	}
	
	 /**
	     * 收款Notify调用代理，用于在线支付(页面通知)
	     * 
	     * @param request
	     *            从request获取变量值
	     * @return
	     * @throws Exception 
	     */
	public OperateResult frontNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始交通银行页面通知*********");
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 	BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 	bankReturnBean.setReq(request);
				bankReturnBean.setResp(response);
				bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				
			 	String notifyMsg = request.getParameter("notifyMsg");
			 	logger.info("通知报文："+notifyMsg);
		        int lastIndex = notifyMsg.lastIndexOf("|");
		        // TODO：获取签名信息
		        String signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length());
		        // TODO：获取源信息
		        String sourceMsg = notifyMsg.substring(0, lastIndex + 1);
		        // TODO：验签
		        int veriyCode = BcmUtils.veriySign(signMsg, sourceMsg);
		        StringTokenizer stName = new StringTokenizer(sourceMsg, "|");
		        List<String> al = new ArrayList<String>();
		        // TODO：拆解通知结果到Arraylist
		        while (stName.hasMoreTokens()) {
		            String value = (String) stName.nextElement();
		            al.add(value);
		        }
		        // 商户客户号（商户编号）
		        String merchNo = al.get(0);
		        //订单号
		        String orderNo = al.get(1);
		        // 订单金额 单位:元 并带两位小数
		        String tranAmount=al.get(2);
		        //订单币种: 人民币 CNY 
		        String tranCurrType = al.get(3);
		        //平台批次号
		        String payBatNo = al.get(4);
		        //商户批次号
		        String merchBatchNo = al.get(5);
		        //交易日期(yyyyMMdd)
		        String tranDate=al.get(6);
		        //交易时间(HHmmss)
		        String tranTime = al.get(7);
		        //交易流水号
		        String derialNo= al.get(8);
		        //交易结果(1 成功)
		        String tranRst= al.get(9);
		        //手续费总额
		        String feeSum = al.get(10);
		        //银行卡类型（0 借记卡 1 准贷记卡 2 贷记卡 3 他行银联卡）
		        String cardType = al.get(11);
		        //银行备注
		        String bankMoNo=al.get(12);
		        //错误信息描述
		        String errDis = al.get(13);
		        
		        logger.info("TranAmount="+tranAmount+",MerchNo="+merchNo+",OrderNo："+orderNo+
		        		",TranCurrType="+tranCurrType+",PayBatNo="+payBatNo+",MerchBatchNo="+merchBatchNo+
		        		",TranDate="+tranDate+",TranTime="+tranTime+",SerialNo="+derialNo+
		        		",TranRst="+tranRst+",FeeSum="+feeSum+",CardType="+cardType+",BankMoNo="+bankMoNo+",ErrDis="+errDis+",VeriyCode="+veriyCode);

		        if (0 > veriyCode) {
		        	operateResult.setMessage("商户端验证签名失败：return code:" + veriyCode);
		            operateResult.setSuccess(false);
		            return operateResult;
		        } else if (BcmConstants.TRAN_RESULT.equals(tranRst)) {
		        	 bankReturnBean.setResponseStatus(1);
		        	 logger.info("支付结果通知，流水号："+orderNo+",支付金额："+tranAmount+",支付状态：成功");
		        } else {
		        	bankReturnBean.setResponseStatus(0);
		        	 logger.info("支付结果通知，流水号："+orderNo+",支付金额："+tranAmount+",支付状态：失败");
		        }
		        
				 bankReturnBean.setResponseBankNo(derialNo);
				 bankReturnBean.setResponseTrdeNo(orderNo);
				 bankReturnBean.setResponseBatchNo(payBatNo);
				 bankReturnBean.setResponseInfo(bankMoNo);
				 bankReturnBean.setResponseCode("");
				 bankReturnBean.setResponseAmount(Double.valueOf(tranAmount));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestampT(tranDate+" "+tranTime));
				 bankReturnBean.setResponseQueryNo("");
				 bankReturnBean.setResponseType("");
				
		 }catch(Exception e){
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	}
	 
	/**
     * 根据外部交易号查询（后台查询）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	 public OperateResult query(Traderecord traderecord,BankReturnBean bankReturnBean){
		logger.info("******开始交通银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    init();
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			 	String outTradeNo = traderecord.getTrNo();
		        BOCOMB2CClient client = new BOCOMB2CClient();
		        // 查询订单（支持批量查询，目前只是单笔查询）
		        BOCOMB2COPReply rep = client.queryOrder(outTradeNo);
		        if (null == rep) {
		            operateResult.setSuccess(false);
		            operateResult.setMessage(client.getLastErr());
		            return operateResult;
		        } else {
		        	 String code = rep.getRetCode(); // 得到交易返回码
		             String msg = rep.getErrorMessage();// 错误信息]
		             if (BcmConstants.QUERY_RESULT.equals(code)) {
		                 OpResultSet oprSet = rep.getOpResultSet();
		                 int iNum = oprSet.getOpresultNum();
		                 for (int index = 0; index < iNum; index++) {
		                     Map<String,String> map = new HashMap<String,String>();
		                     // 订单号
		                     String order = oprSet.getResultValueByName(index, "order");
		                     // 订单日期
		                     String orderDate = oprSet.getResultValueByName(index, "orderDate");
		                     // 订单时间
		                     String orderTime = oprSet.getResultValueByName(index, "orderTime");		                     
		                     // 币种
		                     String curType = oprSet.getResultValueByName(index, "curType");
		                     // 金额
		                     String amount = oprSet.getResultValueByName(index, "amount");
		                     // 交易日期
		                     String tranDate = oprSet.getResultValueByName(index, "tranDate");
		                     // 交易时间
		                     String tranTime = oprSet.getResultValueByName(index, "tranTime");
		                     // 支付交易状态
		                     String tranState = oprSet.getResultValueByName(index, "tranState");
		                     // 订单状态
		                     String orderState = oprSet.getResultValueByName(index, "orderState");
		                     String message = "";
		                     // 手续费
		                     String fee = oprSet.getResultValueByName(index, "fee");
		                     // 银行流水号
		                     String bankSerialNo = oprSet.getResultValueByName(index, "bankSerialNo");
		                     // 银行批次号
		                     String bankBatNo = oprSet.getResultValueByName(index, "bankBatNo");
		                     // 交易卡类型0:借记卡 1：准贷记卡 2:贷记卡
		                     String cardType = oprSet.getResultValueByName(index, "cardType");
		                     // 商户批次号
		                     String merchantBatNo = oprSet.getResultValueByName(index, "merchantBatNo");
		                     // 商户备注
		                     String merchantComment = oprSet.getResultValueByName(index, "merchantComment");
		                     // 银行备注
		                     String bankComment = oprSet.getResultValueByName(index, "bankComment");
		                     
		                     bankReturnBean.setResponseTrdeNo(order);
		                     bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestampT(orderDate+" "+orderTime));
		                     bankReturnBean.setResponseAmount(Double.valueOf(amount));
		                     bankReturnBean.setResponseInfo(message);
		                     bankReturnBean.setResponseBankNo(bankSerialNo);
		                     bankReturnBean.setResponseBatchNo(bankBatNo);
		                     if(BcmConstants.NO_PAY.equals(orderState)){
		                         message = "未支付";
		                         bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_PROCESS);
		                     }
		                     if(BcmConstants.PAY_SUCCESS.equals(orderState)){
		                         message = "已支付";
		                         bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_SUCCESS);
		                     }
		                     if(BcmConstants.CANCEL.equals(orderState)){
		                         message = "已撤销";
		                         bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
		                     }
		                     if(BcmConstants.REFUND_PART.equals(orderState)){
		                         message = "已部分退货";
		                         bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
		                     }
		                     if(BcmConstants.REFUND_ALL.equals(orderState)){
		                         message = "已全部退货";
		                         bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
		                     }
		                     if(BcmConstants.REFUNDING.equals(orderState)){
		                         message = "退货处理中";
		                         bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND_ING);
		                     }
		               }
		         }
		    }
			logger.info("交通网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
		}catch(Exception e){
			operateResult.setSuccess(false);
			operateResult.setMessage("系统异常，信息："+e.getMessage());
			logger.info(e.getMessage());
			return operateResult;
		}
		return operateResult;
	}

	 
	 /**
     * 退款，针对已经收款的任务单（后台退款）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
    public OperateResult refund(Traderecord traderecord,BankReturnBean bankReturnBean){
    	 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(false);
		 operateResult.setMessage("该订单不支持退款");
		return operateResult;
	}

	@Override
	public OperateResult query(Date startTime, Date endTime,
			List<BankReturnBean> list) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult cancel(Traderecord traderecord, BankReturnBean bankReturnBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult notifyRefund(HttpServletRequest request,
			BankReturnBean bankReturnBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始交通银行服务器通知*********");
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 	BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 	bankReturnBean.setReq(request);
				bankReturnBean.setResp(response);
				bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				
			 	String notifyMsg = request.getParameter("notifyMsg");
			 	logger.info("通知报文："+notifyMsg);
		        int lastIndex = notifyMsg.lastIndexOf("|");
		        // TODO：获取签名信息
		        String signMsg = notifyMsg.substring(lastIndex + 1, notifyMsg.length());
		        // TODO：获取源信息
		        String sourceMsg = notifyMsg.substring(0, lastIndex + 1);
		        // TODO：验签
		        int veriyCode = BcmUtils.veriySign(signMsg, sourceMsg);
		        StringTokenizer stName = new StringTokenizer(sourceMsg, "|");
		        List<String> al = new ArrayList<String>();
		        // TODO：拆解通知结果到Arraylist
		        while (stName.hasMoreTokens()) {
		            String value = (String) stName.nextElement();
		            al.add(value);
		        }
		        // 商户客户号（商户编号）
		        String merchNo = al.get(0);
		        //订单号
		        String orderNo = al.get(1);
		        // 订单金额 单位:元 并带两位小数
		        String tranAmount=al.get(2);
		        //订单币种: 人民币 CNY 
		        String tranCurrType = al.get(3);
		        //平台批次号
		        String payBatNo = al.get(4);
		        //商户批次号
		        String merchBatchNo = al.get(5);
		        //交易日期(yyyyMMdd)
		        String tranDate=al.get(6);
		        //交易时间(HHmmss)
		        String tranTime = al.get(7);
		        //交易流水号
		        String derialNo= al.get(8);
		        //交易结果(1 成功)
		        String tranRst= al.get(9);
		        //手续费总额
		        String feeSum = al.get(10);
		        //银行卡类型（0 借记卡 1 准贷记卡 2 贷记卡 3 他行银联卡）
		        String cardType = al.get(11);
		        //银行备注
		        String bankMoNo=al.get(12);
		        //错误信息描述
		        String errDis = al.get(13);
		        
		        logger.info("TranAmount="+tranAmount+",MerchNo="+merchNo+",OrderNo："+orderNo+
		        		",TranCurrType="+tranCurrType+",PayBatNo="+payBatNo+",MerchBatchNo="+merchBatchNo+
		        		",TranDate="+tranDate+",TranTime="+tranTime+",SerialNo="+derialNo+
		        		",TranRst="+tranRst+",FeeSum="+feeSum+",CardType="+cardType+",BankMoNo="+bankMoNo+",ErrDis="+errDis+",VeriyCode="+veriyCode);

		        if (0 > veriyCode) {
		        	operateResult.setMessage("商户端验证签名失败：return code:" + veriyCode);
		            operateResult.setSuccess(false);
		            return operateResult;
		        } else if (BcmConstants.TRAN_RESULT.equals(tranRst)) {
		        	 bankReturnBean.setResponseStatus(1);
		        	 logger.info("支付结果通知，流水号："+orderNo+",支付金额："+tranAmount+",支付状态：成功");
		        } else {
		        	bankReturnBean.setResponseStatus(0);
		        	 logger.info("支付结果通知，流水号："+orderNo+",支付金额："+tranAmount+",支付状态：失败");
		        }
		        
				 bankReturnBean.setResponseBankNo(derialNo);
				 bankReturnBean.setResponseTrdeNo(orderNo);
				 bankReturnBean.setResponseBatchNo(payBatNo);
				 bankReturnBean.setResponseInfo(bankMoNo);
				 bankReturnBean.setResponseCode("");
				 bankReturnBean.setResponseAmount(Double.valueOf(tranAmount));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestampT(tranDate+" "+tranTime));
				 bankReturnBean.setResponseQueryNo("");
				 bankReturnBean.setResponseType("");
		 }catch(Exception e){
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	 }
	
	 private static void init() {
        BOCOMB2CClient client = new BOCOMB2CClient();
        try {
        	//String path = BcmConstants.class.getResource("B2CMerchantSocket.xml").getPath(); 
        	String path = Thread.currentThread().getContextClassLoader().getResource("B2CMerchantSocket.xml").getPath();
        	logger.info("path1：" + path); 
        	 path = path.replaceAll("%20", " ");
        	logger.info("path2：" + path);
            // TODO:初始化基本信息
            int ret = client.initialize(path);
            if (0 != ret) {
                // TODO:初始化失败
            	logger.info("初始化失败,错误信息：" + client.getLastErr());
            }else{
            	logger.info("初始化成功,ret:" + ret);
            }
        } catch (Exception e) {
        	logger.info("初始化失败,错误信息：文件B2CMerchantSocket.xml未找到！" + e);
        }
    }
}
