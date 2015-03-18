package com.jinxin.service.paygateway.online.cib.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.persistence.Merchant;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.MerchantServiceI;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.cib.util.CibConstants;
import com.jinxin.service.paygateway.online.cib.util.CibSendBean;
import com.jinxin.service.paygateway.online.cib.util.CibUtils;
import com.jinxin.service.paygateway.online.cib.util.EncryptUtil;


/**
 * @className:CibPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("cibService")
@Scope("prototype")
public class CibPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(CibPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker cibWorker;
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
		CibSendBean sendDataBean = new CibSendBean();
		try{
			
			sendDataBean.setServiceUrl(CibConstants.SERVICEURL);
			sendDataBean.setService(CibConstants.SERVICE);
			sendDataBean.setVer(CibConstants.VERSION);
			sendDataBean.setMerNo(CibConstants.MRCHNO);
			
			Merchant merchant = merchantService.getMerchant(String.valueOf(traderecord.getTrMerNo()));
			
			sendDataBean.setSub_mrch(merchant.getMerName());
			sendDataBean.setTrNo(traderecord.getTrNo());
			sendDataBean.setOrd_date(DateTimeUtils.formatDate(new Date(), "yyyyMMdd"));
			DecimalFormat df = new DecimalFormat("#.00");
			
	    	String amount="";
	    	BigDecimal samount = traderecord.getTrAmount();
	    	if(samount.compareTo(new BigDecimal("1"))>=0){
	    		amount = df.format(samount);
	    	}else{
	    		amount = "0"+df.format(samount);
	    	}
	    	
			sendDataBean.setAmount(amount);
			sendDataBean.setCurrency(CibConstants.CURRENCY);
			sendDataBean.setOrd_ip("");
			
			StringBuffer signstr = new StringBuffer();
			signstr.append("service"+sendDataBean.getService());
			signstr.append("ver"+sendDataBean.getVer());
			signstr.append("mrch_no"+sendDataBean.getMerNo());
			if(StringUtils.isNotBlank(sendDataBean.getSub_mrch())){
				signstr.append("sub_mrch"+sendDataBean.getSub_mrch());
			}
			signstr.append("ord_no"+sendDataBean.getTrNo());
			signstr.append("ord_date"+sendDataBean.getOrd_date());
			signstr.append("cur"+sendDataBean.getCurrency());
			signstr.append("ord_amt"+sendDataBean.getAmount());
			signstr.append(CibConstants.KEY);

			logger.info("signstr="+signstr);
			String sign = EncryptUtil.getMD5Encrypt(signstr.toString());
			sendDataBean.setSign(sign);
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
		 logger.info("******开始兴业银行页面通知*********");
		 logger.info("通知流水号："+request.getParameter("ord_no"));
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 
			 String bank = request.getParameter("bank");
			 String mrch_no = request.getParameter("mrch_no");
			 String ord_no = request.getParameter("ord_no");
			 String cur = request.getParameter("cur");
			 String ord_amt = request.getParameter("ord_amt");
			 String pay_amt = request.getParameter("pay_amt");
			 String sno = request.getParameter("sno");
			 String mac = request.getParameter("mac");
			 String signstr = "bank"+bank+"mrch_no"+mrch_no+"ord_no"+ord_no+"cur"+cur+"ord_amt"+ord_amt+"pay_amt"+pay_amt+"sno"+sno+CibConstants.KEY;
			 String sign = EncryptUtil.getMD5Encrypt(signstr);
			 if(!sign.equals(mac)){
				 operateResult.setMessage("验证签名失败！");
				 operateResult.setSuccess(false);
				 return operateResult;
			 }
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(sno);
			 bankReturnBean.setResponseTrdeNo(ord_no);
			 bankReturnBean.setResponseBatchNo("");
			 bankReturnBean.setResponseInfo("");
			 bankReturnBean.setResponseCode("");
			 bankReturnBean.setResponseAmount(Double.valueOf(pay_amt));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
			 bankReturnBean.setResponseQueryNo("");
			 bankReturnBean.setResponseType("");
			 bankReturnBean.setResponseStatus(1);
			logger.info("支付结果通知，流水号："+ord_no+",支付金额："+pay_amt+",支付状态：成功");
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
		logger.info("******开始兴业银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			String sendXml="";
			String service = CibConstants.QUERYSERVICE;
			String ver = CibConstants.VERSION;
			String mrch_no =CibConstants.MRCHNO;
			String ord_no = traderecord.getTrNo();
			
			String signstr = "service"+service+"ver"+ver+"mrch_no"+mrch_no+"ord_no"+ord_no+CibConstants.KEY;
			String sign = EncryptUtil.getMD5Encrypt(signstr);
			
			CibSendBean cibSendBean = new CibSendBean();
			cibSendBean.setServiceUrl(CibConstants.QUERYURL);
			cibSendBean.setService(service);
			cibSendBean.setVer(ver);
			cibSendBean.setMerNo(mrch_no);
			cibSendBean.setTrNo(ord_no);
			cibSendBean.setSign(sign);
			
			Map<String, String> params = new HashMap<String, String>();
			String receiveXml = cibWorker.send(params, cibSendBean);
			
			operateResult = CibUtils.paraxml(receiveXml, bankReturnBean);
			if(!operateResult.isSuccess()){
				logger.info("兴业银行返回查询操作结果：失败 ，流水号： " + traderecord.getTrNo() +",ErrorMessage:"+operateResult.getMessage());
				return operateResult;
			}
			
			logger.info("兴业网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
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
		 logger.info("******开始兴业银行服务器通知*********");
		 logger.info("通知流水号："+request.getParameter("ord_no"));
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 
			 String bank = request.getParameter("bank");
			 String mrch_no = request.getParameter("mrch_no");
			 String ord_no = request.getParameter("ord_no");
			 String cur = request.getParameter("cur");
			 String ord_amt = request.getParameter("ord_amt");
			 String pay_amt = request.getParameter("pay_amt");
			 String sno = request.getParameter("sno");
			 String mac = request.getParameter("mac");
			 String signstr = "bank"+bank+"mrch_no"+mrch_no+"ord_no"+ord_no+"cur"+cur+"ord_amt"+ord_amt+"pay_amt"+pay_amt+"sno"+sno+CibConstants.KEY;
			 String sign = EncryptUtil.getMD5Encrypt(signstr);
			 if(!sign.equals(mac)){
				 operateResult.setMessage("验证签名失败！");
				 operateResult.setSuccess(false);
				 return operateResult;
			 }
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(sno);
			 bankReturnBean.setResponseTrdeNo(ord_no);
			 bankReturnBean.setResponseBatchNo("");
			 bankReturnBean.setResponseInfo("");
			 bankReturnBean.setResponseCode("");
			 bankReturnBean.setResponseAmount(Double.valueOf(pay_amt));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
			 bankReturnBean.setResponseQueryNo("");
			 bankReturnBean.setResponseType("");
			 bankReturnBean.setResponseStatus(1);
			logger.info("支付结果通知，流水号："+ord_no+",支付金额："+pay_amt+",支付状态：成功");
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
