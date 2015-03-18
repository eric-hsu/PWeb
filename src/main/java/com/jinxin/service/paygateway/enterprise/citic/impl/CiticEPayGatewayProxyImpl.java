package com.jinxin.service.paygateway.enterprise.citic.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
import com.jinxin.service.paygateway.EnterpriseWorker;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.enterprise.EnterprisePayGatewayProxy;
import com.jinxin.service.paygateway.enterprise.citic.util.CiticEConstants;
import com.jinxin.service.paygateway.enterprise.citic.util.CiticESendBean;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.citic.util.CiticConstants;
import com.jinxin.service.paygateway.enterprise.citic.util.citicUtil.CNCBCryptoUtil;
import com.jinxin.service.paygateway.enterprise.citic.util.citicUtil.CNCBCryptoUtilTest;
import com.jinxin.service.paygateway.enterprise.citic.util.citicUtil.CommBean;
import com.jinxin.service.paygateway.enterprise.citic.util.citicUtil.XmlDataInterfaceService;

/**
 * @className:CiticEPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("citicEService")
@Scope("prototype")
public class CiticEPayGatewayProxyImpl extends EnterprisePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(CiticEPayGatewayProxyImpl.class);
	
	@Autowired
	private EnterpriseWorker citicEWorker;
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
		CiticESendBean sendDataBean = new CiticESendBean();
		try{
			
			String pweb_server_url= StaticMapUtil.paramMap.get("pweb_server_url");

			XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			String orderDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	        String orderTime=new SimpleDateFormat("HH:mm:ss").format(new Date());
	        String orderNo=traderecord.getTrNo();
	        String amount = String.valueOf(traderecord.getTrAmount().doubleValue());
            xmlservice.setXmlField("VERSION",CiticEConstants.VERSION);
            xmlservice.setXmlField("BIZCODE",CiticEConstants.BIZCODE);
	        xmlservice.setXmlField("MCTNO",CiticEConstants.MCTNO);
	        xmlservice.setXmlField("NTFTYPE",CiticEConstants.NTFTYPE);
	        xmlservice.setXmlField("RSPTYPE",CiticEConstants.RSPTYPE);
	        xmlservice.setXmlField("PCBURL",pweb_server_url+"/citicEController/frontNotify.do");
	        xmlservice.setXmlField("SCBURL",pweb_server_url+"/citicEController/backNotify.do");
	        xmlservice.setXmlField("TRANPERIOD",CiticEConstants.TRANPERIOD);
	        
	        String MCTJNLNO = traderecord.getTrNo();
	        String BSNNO = CiticEConstants.BSNNO;
	        //收款账号 *
	        String PAYEEACCNO = CiticEConstants.PAYEEACCNO;
	        String CRYCODE = CiticEConstants.CRYCODE;
	        //描述支付交易的备注信息。
	        String PURPOSE="金信商户订单";
	        String ORDERNO=traderecord.getTrMerOrderno();
	        //订单日期时间 格式：YYYYMMDDhhmmss*
	        String ORDERTIME =DateTimeUtils.getDateTimeNoSeparator(new Date());
	        
	        String MCDNAME="金信商户订单";
	        String MCDTYPE="00";
	        String ORDERDESC="金信商户订单";
	        String ORDERAMT = String.valueOf(traderecord.getTrAmount());
	        //订单受益人
	        String BERNAME ="金信集团";
	        
	        Merchant merchant = merchantService.getMerchant(String.valueOf(traderecord.getTrMerNo()));
			
	        //描述订单来源信息，如：生成订单的商户名称。
	        String ORDERSRC = merchant.getMerName();
	        String[][] payMessage={{"MCTJNLNO","BSNNO","PAYEEACCNO","CRYCODE","PURPOSE","ORDERNO","ORDERTIME","MCDNAME","MCDTYPE","ORDERDESC","ORDERAMT","BERNAME","ORDERSRC"},
	        		{MCTJNLNO,BSNNO,PAYEEACCNO,CRYCODE,PURPOSE,ORDERNO,ORDERTIME,MCDNAME,MCDTYPE,ORDERDESC,ORDERAMT,BERNAME,ORDERSRC}};
	        
	        CommBean commBean = new CommBean(payMessage);
	        xmlservice.setXmlList("TRANLIST", commBean);
			xmlservice.formXml();

		    String xmlresult =  xmlservice.resultXml;
		    logger.info("xml字符串："+xmlresult);
		     
		    CNCBCryptoUtil util = new CNCBCryptoUtil();
			CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			byte[] signbyte = test.sign(xmlresult, util);
			
	    	String strSignedMsg = new String(signbyte);
	    	logger.info("strSignedMsg："+strSignedMsg);
	    	
	    	sendDataBean.setSign(strSignedMsg);
	    	sendDataBean.setServiceUrl(CiticEConstants.SERVICEURL);
	    	logger.info("payurl："+CiticEConstants.SERVICEURL);
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
		 String SIGNRSPMSG = request.getParameter("SIGNRSPMSG");
//		 String SIGNRSPMSG ="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";
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 
			 logger.info("通知密文："+SIGNRSPMSG);
			 CNCBCryptoUtil util = new CNCBCryptoUtil();
			 CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			 byte[] signbyte = test.verify(SIGNRSPMSG, util);
			 String returnxml =  new String(signbyte);
			 XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			 xmlservice.parseResultEBStream(returnxml);
			 logger.info("通知明文："+returnxml);
			 
			 CommBean commBean = xmlservice.getXmlList("TRANRSTLIST");
			 
			 String[][] data = commBean.getData();
			 
			 
			 if(data.length!=2){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("通知信息有误！");
				 logger.info(commBean.toString());
				 return operateResult;
			 }
			 Map<String,String> map = new HashMap<String,String>();
			 for(int i=0;i<data[0].length;i++){
				 map.put(data[0][i], data[1][i]);
				 logger.info(data[0][i]+"="+data[1][i]);
				
			 }

			 String MCTJNLNO = map.get("MCTJNLNO");
			 String STT = map.get("STT");
			 String RSTCODE = map.get("RSTCODE");
			 String BSNNO = map.get("BSNNO");
			 String PAYERACCNO = map.get("PAYERACCNO");
			 String PAYEEACCNO = map.get("PAYEEACCNO");
			 String TRANAMT = map.get("TRANAMT");
			 String CRYCODE = map.get("CRYCODE");
			 String ORDERNO = map.get("ORDERNO");
			 
			 bankReturnBean.setResponseBankNo(MCTJNLNO);
			 bankReturnBean.setResponseTrdeNo(MCTJNLNO);
			 bankReturnBean.setResponseInfo("付款账户："+PAYERACCNO+",收款账户："+PAYEEACCNO+",业务编号："+BSNNO);
			 bankReturnBean.setResponseCode(CRYCODE);
			 bankReturnBean.setResponseAmount(Double.valueOf(TRANAMT));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));

			 if("AAAAAAA".equals(RSTCODE)){
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
	    CiticESendBean sendDataBean = new CiticESendBean();
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			
			XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			/*xmlservice.setXmlField("action", "DLPGOSTT");
			xmlservice.setXmlField("userName", CiticEConstants.USERNAME);
			xmlservice.setXmlField("mctNo", CiticEConstants.MCTNO);
			xmlservice.setXmlField("mctJnlNo ", traderecord.getTrNo());*/
			
			String localtime = DateTimeUtils.getDateTimeNoSeparator(new Date());
			xmlservice.setXmlField("mctNo",CiticEConstants.MCTNO);
		    xmlservice.setXmlField("mctJnlNo",traderecord.getTrNo());
	        xmlservice.setXmlField("LOCALTIME",localtime);
	         
			xmlservice.formXml();
		    String sendXml =  xmlservice.resultXml;
		    
		    CNCBCryptoUtil util = new CNCBCryptoUtil();
			CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			byte[] signbyte = test.sign(sendXml, util);
			
	    	String strSignedMsg = new String(signbyte);
	    	logger.info("strSignedMsg："+strSignedMsg);
	    	
			Map<String, String> params = new HashMap<String, String>();
			params.put("SIGNREQMSG",strSignedMsg);
			sendDataBean.setServiceUrl(CiticEConstants.QUERYURL);

			String returnxml = citicEWorker.send(params, sendDataBean);

			XmlDataInterfaceService xmlservice1 = new XmlDataInterfaceService();
			xmlservice1.parseResultEBStream(returnxml);
			 logger.info("通知明文："+returnxml);
			 String status = xmlservice1.getXmlFieldValue("status");
			 String statusText = xmlservice1.getXmlFieldValue("statusText");
			 
			 if(!"AAAAAAA".equals(status)){
				operateResult.setSuccess(false);
				operateResult.setMessage(statusText);
				logger.info("订单查询异常："+statusText);
				return operateResult;
			 }
			 
			 CommBean commBean = xmlservice1.getXmlList("TRANRSTLIST");
			 
			 String[][] data = commBean.getData();
			 
			 
			 if(data.length!=2){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("通知信息有误！");
				 logger.info(commBean.toString());
				 return operateResult;
			 }
			 Map<String,String> map = new HashMap<String,String>();
			 for(int i=0;i<data[0].length;i++){
				 map.put(data[0][i], data[1][i]);
				 logger.info(data[0][i]+"="+data[1][i]);
				
			 }

			 String MCTJNLNO = map.get("MCTJNLNO");
			 String STT = map.get("STT");
			 String RSTCODE = map.get("RSTCODE");
			 String BSNNO = map.get("BSNNO");
			 String PAYERACCNO = map.get("PAYERACCNO");
			 String PAYEEACCNO = map.get("PAYEEACCNO");
			 String TRANAMT = map.get("TRANAMT");
			 String CRYCODE = map.get("CRYCODE");
			 String ORDERNO = map.get("ORDERNO");
			 
			 bankReturnBean.setResponseBankNo(MCTJNLNO);
			 bankReturnBean.setResponseTrdeNo(MCTJNLNO);
			 bankReturnBean.setResponseInfo("付款账户："+PAYERACCNO+",收款账户："+PAYEEACCNO+",业务编号："+BSNNO);
			 bankReturnBean.setResponseCode(CRYCODE);
			 bankReturnBean.setResponseAmount(Double.valueOf(TRANAMT));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));

			 if("AAAAAAA".equals(RSTCODE)){
				 bankReturnBean.setResponseStatus(1);
			 }else{
				 bankReturnBean.setResponseStatus(0);
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
		 //经过BASE64编码
		 String SIGNRSPMSG = request.getParameter("SIGNRSPMSG");
//		 String SIGNRSPMSG ="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";
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 
			 logger.info("通知密文："+SIGNRSPMSG);
			 CNCBCryptoUtil util = new CNCBCryptoUtil();
			 CNCBCryptoUtilTest test = new CNCBCryptoUtilTest(util);
			 byte[] signbyte = test.verify(SIGNRSPMSG, util);
			 String returnxml =  new String(signbyte);
			 XmlDataInterfaceService xmlservice = new XmlDataInterfaceService();
			 xmlservice.parseResultEBStream(returnxml);
			 logger.info("通知明文："+returnxml);
			 
			 CommBean commBean = xmlservice.getXmlList("TRANRSTLIST");
			 
			 String[][] data = commBean.getData();
			 
			 
			 if(data.length!=2){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("通知信息有误！");
				 logger.info(commBean.toString());
				 return operateResult;
			 }
			 Map<String,String> map = new HashMap<String,String>();
			 for(int i=0;i<data[0].length;i++){
				 map.put(data[0][i], data[1][i]);
				 logger.info(data[0][i]+"="+data[1][i]);
				
			 }

			 String MCTJNLNO = map.get("MCTJNLNO");
			 String STT = map.get("STT");
			 String RSTCODE = map.get("RSTCODE");
			 String BSNNO = map.get("BSNNO");
			 String PAYERACCNO = map.get("PAYERACCNO");
			 String PAYEEACCNO = map.get("PAYEEACCNO");
			 String TRANAMT = map.get("TRANAMT");
			 String CRYCODE = map.get("CRYCODE");
			 String ORDERNO = map.get("ORDERNO");
			 
			 bankReturnBean.setResponseBankNo(MCTJNLNO);
			 bankReturnBean.setResponseTrdeNo(MCTJNLNO);
			 bankReturnBean.setResponseInfo("付款账户："+PAYERACCNO+",收款账户："+PAYEEACCNO+",业务编号："+BSNNO);
			 bankReturnBean.setResponseCode(CRYCODE);
			 bankReturnBean.setResponseAmount(Double.valueOf(TRANAMT));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));

			 if("AAAAAAA".equals(RSTCODE)){
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
