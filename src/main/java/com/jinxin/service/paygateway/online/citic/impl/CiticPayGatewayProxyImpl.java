package com.jinxin.service.paygateway.online.citic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
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
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.MerchantServiceI;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.citic.util.CiticConstants;
import com.jinxin.service.paygateway.online.citic.util.CiticSendBean;
import com.jinxin.service.paygateway.online.citic.util.CiticUtils;
import com.jinxin.service.paygateway.online.citic.util.citicUtil.CNCBCryptoUtil;
import com.jinxin.service.paygateway.online.citic.util.citicUtil.CNCBCryptoUtilTest;
import com.jinxin.service.paygateway.online.citic.util.citicUtil.RetXmlHandle;
import com.jinxin.service.paygateway.online.citic.util.citicUtil.XmlDataInterfaceService;

import com.lsy.baselib.crypto.processor.ECCryptoProcessor;


/**
 * @className:CiticPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("citicService")
@Scope("prototype")
public class CiticPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(CiticPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker citicWorker;
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
		CiticSendBean sendDataBean = new CiticSendBean();
		try{
			
			String pweb_server_url= StaticMapUtil.paramMap.get("pweb_server_url");

			XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        String orderTime=new SimpleDateFormat("HH:mm:ss").format(new Date());
	        String orderNo=traderecord.getTrNo();
	        String amount = String.valueOf(traderecord.getTrAmount().doubleValue());
            xmlservice.setXmlField("E3RDPAYNO",CiticConstants.E3RDPAYNO);
            xmlservice.setXmlField("ORDERMODE",CiticConstants.ORDERMODE);
	        xmlservice.setXmlField("ORDERDATE",orderDate);
	        xmlservice.setXmlField("ORDERTIME",orderTime);
	        xmlservice.setXmlField("ORDERNO",orderNo);
	        xmlservice.setXmlField("CURRID",CiticConstants.CURRID);
	        xmlservice.setXmlField("ORDERAMT",amount);
	        xmlservice.setXmlField("MEMO","");
	        xmlservice.setXmlField("NOTIFYMODE",CiticConstants.NOTIFYMODE);
	        xmlservice.setXmlField("NOTIFYURL",pweb_server_url+"/citicController/frontNotify.do");
	        xmlservice.setXmlField("RISKLEVEL",CiticConstants.RISKLEVEL);
	        xmlservice.setXmlField("SUPPTCARDTYPE",CiticConstants.SUPPTCARDTYPE);
	        xmlservice.setXmlField("TTL","0");
	        xmlservice.setXmlField("MEMBERID","");
	        xmlservice.setXmlField("NOTIFYSCOPE",CiticConstants.NOTIFYSCOPE);
	        xmlservice.setXmlField("ISSAFEINF",CiticConstants.ISSAFEINF);
	        xmlservice.setXmlField("REFERER","");
			xmlservice.formXml();

		    String xmlresult =  xmlservice.resultXml;
		    System.out.println("xml字符串："+xmlresult);
		     
		    CNCBCryptoUtil util = new CNCBCryptoUtil();
			CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			byte[] signbyte = test.sign(xmlresult, util);
			
	    	String strSignedMsg = new String(signbyte);
	    	System.out.println("strSignedMsg："+strSignedMsg);
	    	
	    	sendDataBean.setSign(strSignedMsg);
	    	sendDataBean.setServiceUrl(CiticConstants.SERVICEURL);
		    	
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
		 logger.info("******开始中信银行页面通知*********");
		 
		 //经过BASE64编码
		 String SIGNRESMSG = request.getParameter("SIGNRESMSG");
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 
			 logger.info("通知密文："+SIGNRESMSG);
			 CNCBCryptoUtil util = new CNCBCryptoUtil();
			 CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			 byte[] signbyte = test.verify(SIGNRESMSG, util);
			 String returnxml =  new String(signbyte);
			 XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			 xmlservice.parseResultEBStream(returnxml);
			 logger.info("通知明文："+returnxml);
			 Map<String,String> fieldValueMap = xmlservice.getXmlContentHash();
			 Iterator it = fieldValueMap.keySet().iterator();
			 while(it.hasNext()){
			 	String key = (String) it.next();
			 	String value = fieldValueMap.get(key);
			 	logger.info(key+":"+value);
			 }
			 String ORDERNO = fieldValueMap.get("ORDERNO");
			 if(StringUtils.isBlank(ORDERNO)){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("通知信息有误！");
			 }
			 
			 String MSGCODE = fieldValueMap.get("MSGCODE");
			 String PAYNO = fieldValueMap.get("PAYNO");
			 String PAYAMT = fieldValueMap.get("PAYAMT");
			 String MSGCN = fieldValueMap.get("MSGCN");
			 String ACCNO = fieldValueMap.get("ACCNO");
			 String ACCTYPE = fieldValueMap.get("ACCTYPE");
			 String PBCSTNAME = fieldValueMap.get("PBCSTNAME");
			 String ACCHASH = fieldValueMap.get("ACCHASH");
			 
			 bankReturnBean.setResponseBankNo(PAYNO);
			 bankReturnBean.setResponseTrdeNo(ORDERNO);
			 bankReturnBean.setResponseInfo(MSGCN+",交易卡号:"+ACCNO+",账户类型:"+ACCTYPE+",个人客户中文姓名:"+PBCSTNAME+",交易卡号哈希值:"+ACCHASH);
			 bankReturnBean.setResponseCode(MSGCODE);
			 bankReturnBean.setResponseAmount(Double.valueOf(PAYAMT));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));

			 if("AAAAAAA".equals(MSGCODE)){
				 bankReturnBean.setResponseStatus(1);
			 }else{
				 bankReturnBean.setResponseStatus(0);
			 }
		 }catch(Exception e){
			 operateResult.setSuccess(false);
			 operateResult.setMessage(e.getMessage());
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
		logger.info("******开始中信银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    CiticSendBean sendDataBean = new CiticSendBean();
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			 String localtime = DateTimeUtils.getDateTimeNoSeparator(new Date());
			 XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
		     xmlservice.setXmlField("E3RDPAYNO",CiticConstants.E3RDPAYNO);
		     xmlservice.setXmlField("ORDERNO",traderecord.getTrNo());
	         xmlservice.setXmlField("localtime",localtime);
			 xmlservice.formXml();
		     String queryxml = xmlservice.resultXml;
		     CNCBCryptoUtil util = new CNCBCryptoUtil();
			 CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			 byte[] signbyte = test.sign(queryxml, util);
	    	 String strSignedMsg = new String(signbyte);
	    	 logger.info("signedMsg："+strSignedMsg);
			
	    	 Map<String,String> params = new HashMap<String,String>();
	    	 params.put("SIGNREQMSG", strSignedMsg);
	 		 sendDataBean.setServiceUrl(CiticConstants.QUERYURL);
	    	 
	 		String queryresult = citicWorker.send(params, sendDataBean);
	 		RetXmlHandle.processRetXml(queryresult);
		    Map<String,String> queryresultmap=RetXmlHandle.getXmlcontenthash();
	    	String status=(String) queryresultmap.get("status");
	    	if(status!=null){
	    		if("AAAAAAA".equals(status)){
		    		String verify=(String) queryresultmap.get("data");
		    		byte[] verifybyte = test.verify(verify, util);
		    		String queryresultdata= new String(verifybyte);   
		    		logger.info("queryresultdata="+queryresultdata);
					 xmlservice.parseResultEBStream(queryresultdata);
					 Map<String,String> queryresultMap = xmlservice.getXmlContentHash();
					 Iterator it = queryresultMap.keySet().iterator();
					 while(it.hasNext()){
					 	String key = (String) it.next();
					 	String value = queryresultMap.get(key);
					 	logger.info(key+":"+value);
					 }
					 
					 String orderstatus = queryresultMap.get("status");
					 String statusText = queryresultMap.get("statusText");
					 String orderTime = queryresultMap.get("orderTime");
					 String accNo = queryresultMap.get("accNo");
					 String accType = queryresultMap.get("accType");
					 String orderNo = queryresultMap.get("orderNo");
					 String payNo = queryresultMap.get("payNo");
					 String hostDate = queryresultMap.get("hostDate");
					 String payAmt = queryresultMap.get("payAmt");
					 String msgCode = queryresultMap.get("msgCode");
					 String pbCstName = queryresultMap.get("pbCstName");
					 
					 bankReturnBean.setResponseBankNo(payNo);
					 bankReturnBean.setResponseTrdeNo(orderNo);
					 bankReturnBean.setResponseInfo("交易卡号:"+accNo+",账户类型:"+accType+",个人客户中文姓名:"+pbCstName+",交易状态："+statusText);
					 bankReturnBean.setResponseCode(msgCode);
					 bankReturnBean.setResponseAmount(Double.valueOf(payAmt));
					 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));

					 if("AAAAAAA".equals(msgCode)){
						 bankReturnBean.setResponseStatus(1);
					 }else if("TIMEOUT".equals(msgCode)){
						 bankReturnBean.setResponseStatus(2);
					 }else{
						 bankReturnBean.setResponseStatus(0);
					 } 
		    	}else{
		    		String statusText=(String) queryresultmap.get("statusText");
		    		logger.info("statusText="+statusText);
		        }
	    	}
	 		
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
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(false);
		 operateResult.setMessage("该订单不支持批量查询");
		return operateResult;
	}

	@Override
	public OperateResult cancel(Traderecord traderecord, BankReturnBean bankReturnBean) {
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(false);
		 operateResult.setMessage("该订单不支持取消");
		return operateResult;
	}

	@Override
	public OperateResult notifyRefund(HttpServletRequest request,
			BankReturnBean bankReturnBean) {
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(false);
		 operateResult.setMessage("该订单不支持退款");
		return operateResult;
	}
	
	 public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始中信银行服务器通知*********");
		 String SIGNRESMSG = request.getParameter("SIGNRESMSG");
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 
			 logger.info("通知信息："+SIGNRESMSG);
			 CNCBCryptoUtil util = new CNCBCryptoUtil();
			 CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			 byte[] signbyte = test.verify(SIGNRESMSG, util);
			 String returnxml =  new String(signbyte);
			 XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			 xmlservice.parseResultEBStream(returnxml);
			 Map<String,String> fieldValueMap = xmlservice.getXmlContentHash();
			 Iterator it = fieldValueMap.keySet().iterator();
			 while(it.hasNext()){
			 	String key = (String) it.next();
			 	String value = fieldValueMap.get(key);
			 	logger.info(key+":"+value);
			 }
			 String ORDERNO = fieldValueMap.get("ORDERNO");
			 if(StringUtils.isBlank(ORDERNO)){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("通知信息有误！");
			 }
			 
			 String MSGCODE = fieldValueMap.get("MSGCODE");
			 String PAYNO = fieldValueMap.get("PAYNO");
			 String PAYAMT = fieldValueMap.get("PAYAMT");
			 String MSGCN = fieldValueMap.get("MSGCN");
			 String ACCNO = fieldValueMap.get("ACCNO");
			 String ACCTYPE = fieldValueMap.get("ACCTYPE");
			 String PBCSTNAME = fieldValueMap.get("PBCSTNAME");
			 String ACCHASH = fieldValueMap.get("ACCHASH");
			 
			 bankReturnBean.setResponseBankNo(PAYNO);
			 bankReturnBean.setResponseTrdeNo(ORDERNO);
			 bankReturnBean.setResponseInfo(MSGCN+",交易卡号:"+ACCNO+",账户类型:"+ACCTYPE+",个人客户中文姓名:"+PBCSTNAME+",交易卡号哈希值:"+ACCHASH);
			 bankReturnBean.setResponseCode(MSGCODE);
			 bankReturnBean.setResponseAmount(Double.valueOf(PAYAMT));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));

			 if("AAAAAAA".equals(MSGCODE)){
				 bankReturnBean.setResponseStatus(1);
			 }else{
				 bankReturnBean.setResponseStatus(0);
			 }
		 }catch(Exception e){
			 operateResult.setSuccess(false);
			 operateResult.setMessage(e.getMessage());
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	 }
}
