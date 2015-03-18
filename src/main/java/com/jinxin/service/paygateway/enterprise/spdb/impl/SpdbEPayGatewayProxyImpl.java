package com.jinxin.service.paygateway.enterprise.spdb.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.hitrust.trustpay.client.b2c.PaymentResult;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.enterprise.spdb.util.SpdbEConstants;
import com.jinxin.service.paygateway.enterprise.spdb.util.SpdbESendBean;
import com.jinxin.service.paygateway.enterprise.spdb.util.SpdbEUtils;
import com.jinxin.service.paygateway.enterprise.spdb.util.sign.MerchantSignVerifyB;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;


//import com.jinxin.service.paygateway.online.spdb.util;


/**
 * @className:spdbPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("spdbEService")
@Scope("prototype")
public class SpdbEPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(SpdbEPayGatewayProxyImpl.class);
	@Autowired
	private OnlineWorker spdbEWorker;
	
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
		SpdbESendBean sendDataBean = new SpdbESendBean();
		String sign = "";
		
		try{
			//交易缩写
			sendDataBean.setTranAbbr(SpdbEConstants.tranAbbr);
			//企业客户号
			sendDataBean.setMasterID("2011622643");
			//商户日期时间
			sendDataBean.setMercDtTm(DateTimeUtils.formatDate(new Date(), "yyyyMMddhhMMss"));
			//订单号 只支持12位			
			if (false == traderecord.getTrNo().isEmpty())
			{
				String trNo = traderecord.getTrNo().substring(3, 15);
				sendDataBean.setTermSsn(trNo);//traderecord.getTrNo()
				operateResult.setTrNo(trNo);
			}
			
			//原交易清算日期  支付交易不用填值，撤销退货需填
			sendDataBean.setOSttDate(SpdbEConstants.OSttDate);

			//原网关流水 退款时用
			sendDataBean.setOAcqSsn("");
			//商户号
			sendDataBean.setMercCode("791308160000201");
			//终端号
			sendDataBean.setTermCode(SpdbEConstants.TermCode);
			//交易金额
			
			DecimalFormat df = new DecimalFormat("#.00");
			
	    	String amount="";
	    	BigDecimal samount = traderecord.getTrAmount();
	    	if(samount.compareTo(new BigDecimal("1"))>=0){
	    		amount = df.format(samount);
	    	}else{
	    		amount = "0"+df.format(samount);
	    	}	    	
			sendDataBean.setTranAmt(amount);
			
			//交易备注1
			sendDataBean.setRemark1(SpdbEConstants.Remark1);
			//交易备注2
			sendDataBean.setRemark2(SpdbEConstants.Remark2);
			//支付交易中
			String NOTIFY_ABC_BACK_URL  = StaticMapUtil.paramMap.get("NOTIFY_SPDB_BACK_URL");
			if(StringUtils.isBlank(NOTIFY_ABC_BACK_URL)){
				sendDataBean.setMercUrl(SpdbEConstants.MercUrl);
				logger.info("浦发银行未配置银行通知地址。");
			}
			else
			{
				sendDataBean.setMercUrl(NOTIFY_ABC_BACK_URL);
			}
	
			//二级商户类别
			sendDataBean.setSubMercFlag(SpdbEConstants.SubMercFlag);							
			//二级商户名
			sendDataBean.setSubMercName(SpdbEConstants.SubMercName);
			//二级商品名
			sendDataBean.setSubMercGoodsName(SpdbEConstants.SubMercGoodsName);
			
			String signstr = "TranAbbr="+sendDataBean.getTranAbbr()+"|MasterID="+sendDataBean.getMasterID()+"|MercDtTm="+sendDataBean.getMercDtTm()+
							"|TermSsn="+sendDataBean.getTermSsn()+"|OSttDate="+sendDataBean.getOSttDate()+"|OAcqSsn="+sendDataBean.getOAcqSsn()+
							"|MercCode="+sendDataBean.getMercCode() + "|TermCode="+sendDataBean.getTermCode() +
							"|TranAmt="+sendDataBean.getTranAmt() + "|Remark1="+sendDataBean.getRemark1() +
							"|Remark2="+sendDataBean.getRemark2() + "|MercUrl="+sendDataBean.getMercUrl() +
							"|SubMercFlag="+sendDataBean.getSubMercFlag() +
							"|SubMercName="+sendDataBean.getSubMercName() + "|SubMercGoodsName="+sendDataBean.getSubMercGoodsName();
			
			
			sign = MerchantSignVerifyB.merchantSignData_ABA(signstr);
			sendDataBean.setSign(sign);
			sendDataBean.setSignstr(signstr);
			
			sendDataBean.setServiceUrl("https://ebank.spdb.com.cn/payment/main");
			
			logger.info(sign);
			logger.info(signstr);
			request.setAttribute("sendDataBean", sendDataBean);
			operateResult.setSuccess(true);
		}catch(Exception e){
			
			operateResult.setMessage("非法操作！");
			return operateResult;
		}
		return operateResult;
	}

	
	/**
     * 收款Notify调用代理，用于在线支付(银行返回的后台通知)
     * 
     * @param request
     *            从request获取变量值
     * @return
     * @throws Exception 
     */
	 public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始浦发银行服务器通知*********");
		 
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);

		 try{
			 
			//返回的原文
			 String Plain = request.getParameter("Plain");
			 //返回的签名
			 String Signature = request.getParameter("Signature");
			//验证签名是否正确
			 if(false == MerchantSignVerifyB.merchantVerifyPayGate_ABA(Signature.trim(), Plain.trim())){
				 operateResult.setMessage("浦发银行－银行返回数据验证签名失败！");
				 operateResult.setSuccess(false);
				 return operateResult;
			 }
			 
			 //解析签名
			//String TranAbbr = Plain.substring((Plain.indexOf("TranAbbr=") + "TranAbbr=".length()), Plain.indexOf('|',(Plain.indexOf("TranAbbr=") + "TranAbbr=".length()))); 
			String AcqSsn = Plain.substring((Plain.indexOf("AcqSsn=") + "AcqSsn=".length()), Plain.indexOf('|',(Plain.indexOf("AcqSsn=") + "AcqSsn=".length())));
			//String MercDtTm =Plain.substring((Plain.indexOf("MercDtTm=") + "MercDtTm=".length()), Plain.indexOf('|',(Plain.indexOf("MercDtTm=") + "MercDtTm=".length())));
			String TermSsn	=Plain.substring((Plain.indexOf("TermSsn=") + "TermSsn=".length()), Plain.indexOf('|',(Plain.indexOf("TermSsn=") + "TermSsn=".length())));
			String RespCode	=Plain.substring((Plain.indexOf("RespCode=") + "RespCode=".length()), Plain.indexOf('|',(Plain.indexOf("RespCode=") + "RespCode=".length())));
			String TermCode	=Plain.substring((Plain.indexOf("TermCode=") + "TermCode=".length()), Plain.indexOf('|',(Plain.indexOf("TermCode=") + "TermCode=".length())));
			//String MercCode	=Plain.substring((Plain.indexOf("MercCode=") + "MercCode=".length()), Plain.indexOf('|',(Plain.indexOf("MercCode=") + "MercCode=".length())));
			String TranAmt	=Plain.substring((Plain.indexOf("TranAmt=") + "TranAmt=".length()), Plain.indexOf('|',(Plain.indexOf("TranAmt=") + "TranAmt=".length())));
			//String SettDate	=Plain.substring((Plain.indexOf("SettDate=") + "SettDate=".length()), Plain.length());
			
			logger.info("通知流水号："+TermSsn);
			
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(AcqSsn);
			 bankReturnBean.setResponseTrdeNo(TermSsn);
			 bankReturnBean.setResponseBatchNo("");
			 bankReturnBean.setResponseInfo("");
			 bankReturnBean.setResponseCode(RespCode);
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
			 bankReturnBean.setResponseQueryNo("");
			 bankReturnBean.setResponseType("0");//浦发银行只提供了一个页面通知
			 if (RespCode.equals("00"))
			 {
				 bankReturnBean.setResponseStatus(1);
			 }
			 else
			 {
				 bankReturnBean.setResponseStatus(0);
			 }
			 bankReturnBean.setResponsePayAmount(Double.parseDouble(TranAmt));
			 bankReturnBean.setResponseTerminalNo(TermCode);
			logger.info("支付结果通知，流水号："+TermSsn+",支付金额："+TranAmt+",支付状态：成功");
		 }catch(Exception e){
			 logger.error(e.getMessage());
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
		 logger.info("******开始浦发银行页面通知*********");
		 
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			//返回的原文
			 String Plain = request.getParameter("Plain");
			 //返回的签名
			 String Signature = request.getParameter("Signature");
			 
			 logger.info("浦发银行返回的：Plain="+Plain);
			 logger.info("浦发银行返回的：Signature="+Signature);
			 
			//验证签名是否正确
			 if(false == MerchantSignVerifyB.merchantVerifyPayGate_ABA(Signature.trim(), Plain.trim())){
				 operateResult.setMessage("浦发银行－银行返回数据验证签名失败！");
				 operateResult.setSuccess(false);
				 return operateResult;
			 }
			 
			 //解析签名
			//String TranAbbr = Plain.substring((Plain.indexOf("TranAbbr=") + "TranAbbr=".length()), Plain.indexOf('|',(Plain.indexOf("TranAbbr=") + "TranAbbr=".length()))); 
			String AcqSsn = Plain.substring((Plain.indexOf("AcqSsn=") + "AcqSsn=".length()), Plain.indexOf('|',(Plain.indexOf("AcqSsn=") + "AcqSsn=".length())));
			//String MercDtTm =Plain.substring((Plain.indexOf("MercDtTm=") + "MercDtTm=".length()), Plain.indexOf('|',(Plain.indexOf("MercDtTm=") + "MercDtTm=".length())));
			String TermSsn	=Plain.substring((Plain.indexOf("TermSsn=") + "TermSsn=".length()), Plain.indexOf('|',(Plain.indexOf("TermSsn=") + "TermSsn=".length())));
			String RespCode	=Plain.substring((Plain.indexOf("RespCode=") + "RespCode=".length()), Plain.indexOf('|',(Plain.indexOf("RespCode=") + "RespCode=".length())));
			String TermCode	=Plain.substring((Plain.indexOf("TermCode=") + "TermCode=".length()), Plain.indexOf('|',(Plain.indexOf("TermCode=") + "TermCode=".length())));
			//String MercCode	=Plain.substring((Plain.indexOf("MercCode=") + "MercCode=".length()), Plain.indexOf('|',(Plain.indexOf("MercCode=") + "MercCode=".length())));
			String TranAmt	=Plain.substring((Plain.indexOf("TranAmt=") + "TranAmt=".length()), Plain.indexOf('|',(Plain.indexOf("TranAmt=") + "TranAmt=".length())));
			//String SettDate	=Plain.substring((Plain.indexOf("SettDate=") + "SettDate=".length()), Plain.length());
				
			logger.info("通知流水号："+TermSsn + " RespCode = " + RespCode);
			
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(AcqSsn);
			 bankReturnBean.setResponseTrdeNo(TermSsn);
			 bankReturnBean.setResponseBatchNo("");
			 bankReturnBean.setResponseInfo("");
			 bankReturnBean.setResponseCode(RespCode);
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
			 bankReturnBean.setResponseQueryNo("");
			 bankReturnBean.setResponseType("0");//浦发银行只提供了一个页面通知
			 if (RespCode.equals("00"))
			 {
				 logger.info(" RespCode = " + RespCode);
				 bankReturnBean.setResponseStatus(1);
			 }
			 else
			 {
				 logger.info(" RespCode = " + RespCode);
				 bankReturnBean.setResponseStatus(0);
			 }
			 bankReturnBean.setResponsePayAmount(Double.parseDouble(TranAmt));
			 bankReturnBean.setResponseTerminalNo(TermCode);
			logger.info("支付结果通知，流水号："+TermSsn+",支付金额："+TranAmt+",支付状态：成功");
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
		logger.info("******开始浦发银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{

			SpdbESendBean spdbSendBean = new SpdbESendBean();
			spdbSendBean.setServiceUrl(SpdbEConstants.SERVICEURL);
			Map<String, String> params = new HashMap<String, String>();
			String signstr = "MercCode="+SpdbEConstants.MercCode+"|OTranAbbr="+SpdbEConstants.OTranAbbr+"|TermSsn="+traderecord.getTrReference();
			String sign = MerchantSignVerifyB.merchantSignData_ABA(signstr);
	
			params.put("transName", SpdbEConstants.MercCode);//商户号
			params.put("Plain", signstr);//交易字段拼装的明文
			params.put("Signature", sign);//对Plain的签名

			String receiveXml = spdbEWorker.send(params, spdbSendBean);

			Map<String, String> resultMap = SpdbEUtils.getTradeList(receiveXml);
			//订单号
			String TermSsn = resultMap.get("TermSsn");
			//交易金额
			String TranAmt = resultMap.get("TranAmt");
			//完成状态 00-交易成功 01-交易失败 02-撤消成功  03-部分退货  04-全部退货  05-预授权确认成功  06-预授权撤销成功  99-交易超时
			String CompFlag = resultMap.get("CompFlag");
			//响应码
			String RespCode = resultMap.get("RespCode");
			//清算日期
			String SettDate = resultMap.get("SettDate");
			//网关流水
			String AcqSsn = resultMap.get("AcqSsn");
			
			bankReturnBean.setResponseBankNo(AcqSsn);
			bankReturnBean.setResponseTrdeNo(TermSsn);
			bankReturnBean.setResponseInfo(CompFlag);
			bankReturnBean.setResponseCode(RespCode);
			bankReturnBean.setResponseAmount(Double.valueOf(TranAmt));
			bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(SettDate, "yyyyMMddHHmmss"));

			//orderStatus 订单状态，0-待支付；1-已支付；2-已结算；3-已撤销；4-部分退款；5-全额退款。
			if("00".equals(CompFlag) || "05".equals(CompFlag)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_SUCCESS);
			}else if("01".equals(CompFlag)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_FAIL);
			}else if("02".equals(CompFlag) || "03".equals(CompFlag) || "04".equals(CompFlag) || "06".equals(CompFlag)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_REFUND);
			}else{
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_FAIL);
			}
			operateResult.setSuccess(true);
			
			logger.info("浦发银行网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
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
    	logger.info("******开始浦发银行退款*********");
	    logger.info("退款流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
		try{
	    	SpdbESendBean spdbSendBean = new SpdbESendBean();
			spdbSendBean.setServiceUrl(SpdbEConstants.SERVICEURL);
			String TranAbbr = SpdbEConstants.RTranAbbr;
			String MasterID = SpdbEConstants.MasterID;
			SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
			String MercDtTm =f.format(traderecord.getTrBanktradetime());//yyyyMMddHHmmss  以年月日时分秒组成
			String TermSsn = traderecord.getTrNo();
			String OSttDate = "";//原交易清算日期
			String OAcqSsn = traderecord.getTrBankorderno();//原网关流水
			String MercCode = SpdbEConstants.MercCode;
			String TermCode = SpdbEConstants.TermCode;
			String TranAmt = String.valueOf(traderecord.getTrRefundmentAmount());
			String Remark1 = "";
			String Remark2="";
			
			String signstr = "TranAbbr="+TranAbbr+"|MasterID="+MasterID+"|MercDtTm="+MercDtTm+"|TermSsn="+TermSsn+"|OSttDate="+OSttDate
					+"|OAcqSsn="+OAcqSsn+"|MercCode="+MercCode+"|TermCode="+TermCode+"|TranAmt="+TranAmt+"|Remark1="+Remark1
					+"|Remark2="+Remark2;
			
			String sign = MerchantSignVerifyB.merchantSignData_ABA(signstr);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("transName", SpdbEConstants.MercCode);//商户号
			params.put("Plain", signstr);//交易字段拼装的明文
			params.put("Signature", sign);//对Plain的签名
	
			String receiveXml = spdbEWorker.send(params, spdbSendBean);
	
			Map<String, String> resultMap = SpdbEUtils.getTradeList(receiveXml);
			
			//交易缩写
			String rTranAbbr = resultMap.get("TranAbbr");
			//网关流水
			String rAcqSsn = resultMap.get("AcqSsn");
			//商户日期时间
			String rMercDtTm = resultMap.get("MercDtTm");
			//订单号
			String rTermSsn = resultMap.get("TermSsn");
			//响应码
			String rRespCode = resultMap.get("RespCode");
			//终端号
			String rTermCode = resultMap.get("TermCode");
			//商户号
			String rMercCode = resultMap.get("MercCode");
			//交易金额
			String rTranAmt = resultMap.get("TranAmt");
			//清算日期
			String rSettDate = resultMap.get("SettDate");
			
			 if (rRespCode.equals("00"))
			 {
				 logger.info(" rRespCode = " + rRespCode);
				 bankReturnBean.setResponseStatus(1);
				 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				 bankReturnBean.setResponseTrdeNo(rTermSsn);
				 bankReturnBean.setResponseBankNo(rAcqSsn);
				 bankReturnBean.setResponseRefundAmount(Double.valueOf(rTranAmt));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestampT(rMercDtTm));
				 bankReturnBean.setResponseStatus(3);
			 }else{
				 logger.info(" rRespCode = " + rRespCode);
				 operateResult.setSuccess(false);
				 operateResult.setMessage("(银行退款失败原因)"+rRespCode);
				 return operateResult;
			 }
			
			
		}catch(Exception e){
			operateResult.setSuccess(false);
			operateResult.setMessage("系统异常，信息："+e.getMessage());
			logger.info(e.getMessage());
			return operateResult;
		}
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
	
}
