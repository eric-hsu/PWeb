package com.jinxin.service.paygateway.online.bccb.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.http.client.utils.DateUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.bccb.ebusiness.client.MerPaymentInfo;
import com.bccb.ebusiness.client.MerPaymentRequest;
import com.bccb.ebusiness.client.MerPaymentResponse;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.MerchantServiceI;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.bccb.util.BccbConstants;
import com.jinxin.service.paygateway.online.bccb.util.BccbSendBean;
import com.jinxin.service.paygateway.online.cib.util.CibConstants;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;
import com.jinxin.service.paygateway.online.cib.util.CibUtils;
import com.jinxin.service.paygateway.online.cib.util.EncryptUtil;

/**
 * @className:BccbPayGatewayProxyImpl
 * @classDescription:北京银行
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("bccbService")
@Scope("prototype")
public class BccbPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(BccbPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker bccbWorker;
	
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
		BccbSendBean sendDataBean = new BccbSendBean();
		try{
			
			String pweb_server_url= StaticMapUtil.paramMap.get("pweb_server_url");
			 /*   获取相关支付信息  */
		   	 // 1.获取订单号
			String orderNum = traderecord.getTrNo();
			
			// 2.获取订单金额(整型字符串，后两位表示小数)
			String orderAmt = String.valueOf(traderecord.getTrAmount());
			
			// 3.商户接收支付信息URL  商户接收jsp 
			String notifyUrl = pweb_server_url+"/bccbController/backNotify.do";
			
			// 4.商户备注信息 l
			String merRemarks = "";
			
			// 5.商户ID
			String merchantID = BccbConstants.merchantID;
			
			// 6.商户证书号
			String certNumber = BccbConstants.certNumber;
			
			// 7.商户接收通知消息方式 通知类型 0 http;)
			String notifyType = BccbConstants.notifyType;
			
			// 8.是否在线取货 !--是否在线取货 0 是;1 否-->
			String goodsType = BccbConstants.goodsType;
			
			// 9.交易类型 交易类型 0 支付;1 查询对帐单 
			String actionType = BccbConstants.actionType;
			
			//10.对帐日期
			String merCheckDate = DateUtils.formatDate(new Date(), "yyyyMMddHHmmss");
			
			
			/*   生产商户信息对象   */
			MerPaymentInfo merPayInfo = new MerPaymentInfo();
			// 1.设置订单号
			merPayInfo.setOrderNum(orderNum);
			// 2.设置订单金额
			merPayInfo.setOrderAmt(orderAmt);
			// 3.设置通知URL
			merPayInfo.setNotifyUrl(notifyUrl);
			// 4.设置商户备注
			merPayInfo.setMerRemarks(merRemarks);
			// 5.设置商户接收通知方式
			merPayInfo.setNotifyType(notifyType);
			// 6.设置是否在线取货标志
			merPayInfo.setGoodsType(goodsType);
			// 7.设置交易类型
			merPayInfo.setActionType(actionType);
			//8 .设置对帐查询日期
			merPayInfo.setMerCheckDate(merCheckDate);
			
	        /*   生成支付请求对象  */
	        MerPaymentRequest merReq = new MerPaymentRequest(merPayInfo);
	        
	      //银行请求URL
	        String bankUrl = "";
	        //银行应用标识
	        String netType = "";
	        //请求数据包
	        String merReqData = "";
	        if (!merReq.isReadyNow()){
	        	operateResult.setSuccess(false);
	        	operateResult.setMessage("订单签名失败！");
	        	return operateResult;
	                //失败后的商户特殊处理
	        }else{
		        bankUrl= (String)merReq.getBankURL();
		        netType = (String)merReq.getNetType();	
		        merReqData = (String)merReq.getReqData();	 
		        logger.info("merReqData = " + merReqData);      
	        }
			
			sendDataBean.setServiceUrl(bankUrl);
			sendDataBean.setNetType(netType);
			sendDataBean.setMerReqData(merReqData);
			
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
		 logger.info("******开始北京银行页面通知*********");
		 
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 	BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 	bankReturnBean.setReq(request);
			 	bankReturnBean.setResp(response);
			 	bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
		        String bankSignData = request.getParameter("bankSignData");
		        logger.info("通知信息："+bankSignData);
		        if(StringUtils.isBlank(bankSignData)){
		        	operateResult.setSuccess(false);
		        	operateResult.setMessage("数据异常！");
		        	return operateResult;
		        	
		        }
		        
		        MerPaymentResponse merRep = new MerPaymentResponse(bankSignData);
		        //订单号
		        String orderNum = "";
		        //订单金额
		        String orderAmt = "";
		        //交易状态
		        String orderStatus = "";
		        //返回码
		        String retCode = "";
				
				String accDate="";
		        if (!merRep.isReadyNow()){
		        	operateResult.setSuccess(false);
		        	operateResult.setMessage("通知信息异常");
		        	return operateResult;
		                //失败后的商户特殊处理
		        }else{
			        orderNum= (String)merRep.getOrderNum();
			        orderAmt = (String)merRep.getOrderAmt();
			        orderStatus = (String)merRep.getOrderStatus();	
			        retCode = (String)merRep.getRetCode();
					accDate = (String)merRep.getaccDate();
			        System.out.println("orderNum = " + orderNum);
			        System.out.println("orderStatus = " + orderStatus);
			        System.out.println("retCode = " + retCode);
					System.out.println("accDate = " + accDate);
					
					if("0".equals(orderStatus)){
						bankReturnBean.setResponseStatus(1);
						logger.info("支付结果通知，流水号："+orderNum+",支付金额："+orderAmt+",支付状态：成功");
					}else{
						bankReturnBean.setResponseStatus(0);
						logger.info("支付结果通知，流水号："+orderNum+",支付金额："+orderAmt+",支付状态：失败");
					}
					bankReturnBean.setResponseTrdeNo(orderNum);
					bankReturnBean.setResponseAmount(Double.valueOf(orderAmt));
					bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
		        }
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
		logger.info("******开始北京银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		operateResult.setMessage("暂不支持此功能");
		 logger.info("暂不支持此功能!");
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
		 logger.info("******开始北京银行服务器通知*********");
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 	bankReturnBean.setReq(request);
			 	bankReturnBean.setResp(response);
			 	bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
		        String bankSignData = request.getParameter("bankSignData");
		        logger.info("通知信息："+bankSignData);
		        if(StringUtils.isBlank(bankSignData)){
		        	operateResult.setSuccess(false);
		        	operateResult.setMessage("数据异常！");
		        	return operateResult;
		        	
		        }
		        
		        MerPaymentResponse merRep = new MerPaymentResponse(bankSignData);
		        //订单号
		        String orderNum = "";
		        //订单金额
		        String orderAmt = "";
		        //交易状态
		        String orderStatus = "";
		        //返回码
		        String retCode = "";
				
				String accDate="";
		        if (!merRep.isReadyNow()){
		        	operateResult.setSuccess(false);
		        	operateResult.setMessage("通知信息异常");
		        	return operateResult;
		                //失败后的商户特殊处理
		        }else{
			        orderNum= (String)merRep.getOrderNum();
			        orderAmt = (String)merRep.getOrderAmt();
			        orderStatus = (String)merRep.getOrderStatus();	
			        retCode = (String)merRep.getRetCode();
					accDate = (String)merRep.getaccDate();
			        System.out.println("orderNum = " + orderNum);
			        System.out.println("orderStatus = " + orderStatus);
			        System.out.println("retCode = " + retCode);
					System.out.println("accDate = " + accDate);
					
					if("0".equals(orderStatus)){
						bankReturnBean.setResponseStatus(1);
						logger.info("支付结果通知，流水号："+orderNum+",支付金额："+orderAmt+",支付状态：成功");
					}else{
						bankReturnBean.setResponseStatus(0);
						logger.info("支付结果通知，流水号："+orderNum+",支付金额："+orderAmt+",支付状态：失败");
					}
					bankReturnBean.setResponseTrdeNo(orderNum);
					bankReturnBean.setResponseAmount(Double.valueOf(orderAmt));
					bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
		        }
		 }catch(Exception e){
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	 }
	
	//签名
	public static void main(String[] args){
		String signstr = "servicecib.netpay.mb2cver01mrch_no330423sub_mrch无ord_no21245215324533ord_date20141023curCNYord_amt1000.00"+CibConstants.KEY;
		String sign = EncryptUtil.getMD5Encrypt(signstr);
		System.out.println(sign);
	}
}
