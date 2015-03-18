package com.jinxin.service.paygateway.online.icbc.impl;

import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
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
import cn.com.infosec.icbc.ReturnValue;

import com.jinxin.common.utils.CommonUtil;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.ConstantsBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.icbc.util.IcbcConstants;
import com.jinxin.service.paygateway.online.icbc.util.IcbcReceiveDataBean;
import com.jinxin.service.paygateway.online.icbc.util.IcbcSendBean;
import com.jinxin.service.paygateway.online.icbc.util.IcbcUtils;
import com.jinxin.service.paygateway.online.icbc.util.SSL.SSLSendAndRec;

/**
 * @className:IcbcPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("icbcService")
@Scope("prototype")
public class IcbcPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(IcbcPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker icbcWorker;
	
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
		IcbcSendBean sendDataBean = new IcbcSendBean();
		try{
			String pweb_server_url  = StaticMapUtil.paramMap.get("pweb_server_url");
			if(StringUtils.isBlank(pweb_server_url)){
				operateResult.setMessage("未配置银行通知地址。");
				return operateResult;
			}
			
			sendDataBean.setServiceUrl(IcbcConstants.SERVICEURL);
			sendDataBean.setInterfaceName(IcbcConstants.INTERFACENAME);
			sendDataBean.setInterfaceVersion(IcbcConstants.INTERFACEVERSION);
			
			SimpleDateFormat format = new SimpleDateFormat("HHmmss");
	        Date date = new Date();
	        
			//tranData 部分
			sendDataBean.setOrderDate("20140925"+format.format(date));//DateTimeUtils.getDateTimeNoSeparator(new Date())
			sendDataBean.setOrderid(traderecord.getTrNo()); 
			
			DecimalFormat df = new DecimalFormat("#");
	    	String amount="";
	    	BigDecimal samount = traderecord.getTrAmount().multiply(new BigDecimal("100"));
	    	amount = df.format(samount);
			sendDataBean.setAmount(amount);

			sendDataBean.setInstallmentTimes(IcbcConstants.INSTALLMENTTIMES);
			sendDataBean.setMerAcct(IcbcConstants.MERACCT);
			sendDataBean.setGoodsID("");
			sendDataBean.setGoodsName(IcbcConstants.GOODSNAME);
			sendDataBean.setGoodsNum("1");
			sendDataBean.setCarriageAmt("0");
			sendDataBean.setVerifyJoinFlag(IcbcConstants.VERIFYJOINFLAG);
			sendDataBean.setLanguage(IcbcConstants.LANGUAGE);
			sendDataBean.setCurType(IcbcConstants.CURTYPE);
			sendDataBean.setMerID(IcbcConstants.MERID);
			sendDataBean.setCreditType(IcbcConstants.CREDITTYPE);
			sendDataBean.setNotifyType(IcbcConstants.NOTIFYTYPE);
			sendDataBean.setResultType(IcbcConstants.RESULTTYPE);
			sendDataBean.setMerReference("");
			sendDataBean.setMerCustomIp("183.16.144.99");//"14.155.30.151"CommonUtil.getIpAddr(request)
			sendDataBean.setGoodsType("1");//
			sendDataBean.setMerCustomID("");
			sendDataBean.setMerCustomPhone("");
			sendDataBean.setGoodsAddress("");
			sendDataBean.setMerOrderRemark("");
			sendDataBean.setMerHint("");
			sendDataBean.setRemark1("");
			sendDataBean.setRemark2("");
			sendDataBean.setMerURL(pweb_server_url+"/icbcController/backNotify.do"); 
			sendDataBean.setMerVAR("");
			sendDataBean.setE_isMerFlag("");
			sendDataBean.setE_Name("");
			sendDataBean.setE_TelNum("");
			sendDataBean.setE_CredType("");
			sendDataBean.setE_CredNum("");

			String tranData = IcbcUtils.createXml(sendDataBean);

			
			
			
			logger.info("明文："+tranData+"");
			String password = IcbcConstants.PASSWORD;
			String SignMsgBase64 ="";
			String CertBase64="";
			byte[] byteSrc = tranData.getBytes();
			char[] keyPass = password.toCharArray();
//			FileInputStream in1 = new FileInputStream(IcbcConstants.CERTPATH);
//			byte[] bcert = new byte[in1.available()];
//			in1.read(bcert);
//			in1.close();
			
			InputStream in=SSLSendAndRec.class.getResourceAsStream(IcbcConstants.CERTNAME);
			byte[] bcert = new byte[in.available()];
			in.read(bcert);
			in.close();
			
			
//			FileInputStream in2 = new FileInputStream(IcbcConstants.KEYPATH);
//			byte[] bkey = new byte[in2.available()];
//			in2.read(bkey);
//			in2.close();
			
			InputStream in2=SSLSendAndRec.class.getResourceAsStream(IcbcConstants.KEYNAME);
			byte[] bkey = new byte[in2.available()];
			in2.read(bkey);
			in2.close();
			
		    byte[] sign =ReturnValue.sign(byteSrc,byteSrc.length,bkey,keyPass);
		    if (sign==null) {
		    	logger.info("签名失败,签名返回为空。<br>请检查证书私钥和私钥保护口令是否正确。");
		    }else{
		    	logger.info("签名成功");
		   
			    byte[] EncSign = ReturnValue.base64enc(sign);
			    SignMsgBase64=new String(EncSign).toString();
			    logger.info("签名信息BASE64编码："+SignMsgBase64+"");
			    
			    byte[] EncCert=ReturnValue.base64enc(bcert);
				CertBase64=new String(EncCert).toString();
				logger.info("证书公钥BASE64编码："+CertBase64+"");
			}

		    String signStr = new String(ReturnValue.base64enc(byteSrc)).toString();
			sendDataBean.setTranData(signStr);// tranDataXml 加密
			sendDataBean.setMerSignMsg(SignMsgBase64);// tranDataXml 签名
			sendDataBean.setMerCert(CertBase64);//cert证书获取

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
		return null;
	}

	/**
     * 根据外部交易号查询（后台查询）
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	 public OperateResult query(Traderecord traderecord,BankReturnBean bankReturnBean){
		logger.info("******开始工商银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			IcbcSendBean icbcSendBean = new IcbcSendBean();
			
			icbcSendBean.setOrderid(traderecord.getTrNo());
			icbcSendBean.setOrderDate(DateTimeUtils.getDateTimeNoSeparator(traderecord.getTrBanktradetime()));
			icbcSendBean.setMerID(IcbcConstants.MERID);
			icbcSendBean.setMerAcct(IcbcConstants.MERACCT);
			
			
			
			Map<String, String> params = new HashMap<String, String>();
			
			String resultXml = icbcWorker.send(params, icbcSendBean);
			Map<String, String> receiveMap = new HashMap<String, String>();
			if(resultXml.length()>20){
				receiveMap = IcbcUtils.getQueryResultMap(resultXml);
			}else{
				operateResult.setSuccess(false);
				operateResult.setMessage("工行订单查询失败，原因："+resultXml);
				return operateResult;
			}

			if(receiveMap.get("orderNum") ==null || StringUtils.isBlank(String.valueOf(receiveMap.get("orderNum")))){
				operateResult.setSuccess(false);
				operateResult.setMessage(String.valueOf(receiveMap.get("errorCode")));
				return operateResult;
			}
			
			bankReturnBean.setResponseBankNo("");
			bankReturnBean.setResponseTrdeNo(String.valueOf(receiveMap.get("orderNum")));
			bankReturnBean.setResponseBatchNo("");
			bankReturnBean.setResponseInfo("");
			bankReturnBean.setResponseCode("");
			bankReturnBean.setResponseAmount(Double.valueOf(String.valueOf(receiveMap.get("amount"))));
			bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(String.valueOf(receiveMap.get("tranDate")),"yyyyMMdd"));
			bankReturnBean.setResponseQueryNo("");
			bankReturnBean.setResponseType("");
			
			//orderStatus 0-支付成功，未清算 1-支付成功，已清算 2-支付失败  3-支付可疑交易
			String orderStatus = String.valueOf(receiveMap.get("tranStat"));
			
			if("0".equals(orderStatus) || "1".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_SUCCESS);
			}else if("2".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_FAIL);
			}else if("3".equals(orderStatus)){
				bankReturnBean.setResponseStatus(ConstantsBean.TRADE_STATUS_PROCESS);
			}

			logger.info("工商网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
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
		 logger.info("******开始工商银行后台通知*********");
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 
			 String merVAR = request.getParameter("merVAR");
			 String notifyData = request.getParameter("notifyData");
			 String signMsg = request.getParameter("signMsg");
			 logger.info("merVAR："+merVAR);
			 logger.info("notifyData："+notifyData);
			 logger.info("signMsg："+signMsg);
//			 boolean flag = IcbcUtils.verifySign(signMsg);
//			 if(!flag){
//				 operateResult.setMessage("验证签名失败");
//				 operateResult.setSuccess(false);
//				 return operateResult;
//			 }
			 
			 String receiveXml  =IcbcUtils.decryptSign(notifyData);
			 logger.info("receiveXml："+receiveXml);
			 if(StringUtils.isBlank(receiveXml)){
				 operateResult.setMessage("订单解密错误");
				 operateResult.setSuccess(false);
				 return operateResult;
			 }
			 IcbcReceiveDataBean icbcReceiveDataBean = IcbcUtils.createReceiveDataBean(receiveXml);
			 
			 if(icbcReceiveDataBean.getOrderid() ==null){
				 operateResult.setMessage("订单号错误");
				 operateResult.setSuccess(false);
				 return operateResult;
			 }

			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo("");
			 bankReturnBean.setResponseTrdeNo(icbcReceiveDataBean.getOrderid());
			 bankReturnBean.setResponseBatchNo(icbcReceiveDataBean.getTranBatchNo());
			 bankReturnBean.setResponseInfo(icbcReceiveDataBean.getComment());
			 bankReturnBean.setResponseCode("");
			 bankReturnBean.setResponseAmount(Double.valueOf(icbcReceiveDataBean.getAmount()));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(icbcReceiveDataBean.getNotifyDate(),"yyyyMMddHHmmss"));
			 bankReturnBean.setResponseQueryNo("");
			 bankReturnBean.setResponseType("");
			 bankReturnBean.setResponseStatus(1);
			logger.info("支付结果通知，流水号："+icbcReceiveDataBean.getOrderid()+",支付金额："+icbcReceiveDataBean.getAmount()+",支付状态：成功");
		 }catch(Exception e){
			 logger.error(e.getMessage());
		 }
		 return operateResult;
	 }
}
