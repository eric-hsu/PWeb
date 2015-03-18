package com.jinxin.service.paygateway.online.cmbc.impl;

import java.net.URL;
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

import Union.JnkyServer;

import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.cmbc.util.CmbcConstants;
import com.jinxin.service.paygateway.online.cmbc.util.CmbcSendBean;

/**
 * @className:HxbPayGatewayProxyImpl
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("cmbcService")
@Scope("prototype")
public class CmbcPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(CmbcPayGatewayProxyImpl.class);
	
	@Autowired
	private OnlineWorker cmbcWorker;
	
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
		operateResult.setMessage("支付网关异常，请选择别的付款方式！");
		CmbcSendBean sendDataBean = new CmbcSendBean();
		try{
			sendDataBean.setServiceUrl(CmbcConstants.SERVICEURL);
			String pweb_server_url  = StaticMapUtil.paramMap.get("pweb_server_url");
			if(StringUtils.isBlank(pweb_server_url)){
				operateResult.setMessage("未配置银行通知地址。");
				return operateResult;
			}
			logger.info("通知地址，returnUrl="+pweb_server_url);
			String billNo = traderecord.getTrNo();//订单号
			String txAmt = String.valueOf(traderecord.getTrAmount()); //交易金额
			String PayerCurr="01";//币种
			String txDate =DateTimeUtils.formatDate(traderecord.getTrDatetime(),"yyyyMMdd");//交易日期
			String txTime=DateTimeUtils.formatDate(traderecord.getTrDatetime(),"hhMMss");//交易时间
			String corpID=CmbcConstants.corpID;//商户代码
			String corpName=CmbcConstants.corpName;//商户名称
			String Billremark1="";//备注1
			String Billremark2="";//备注2
			String CorpRetType=CmbcConstants.CorpRetType;//是否实时返回标志
			String retUrl=pweb_server_url+"/cmbcController/frontNotify.do";//处理结果返回的URL
			String langMAC = "";//MAC
			String bankCode="";//银行编码(跨行支付需要)
			String productId="";//产品编码（跨行支付需要）
			

			URL cerFile = CmbcConstants.class.getResource("1024new.cer");
		    String certFilePath = cerFile.getPath().replace("%20", " ");
		    
		    URL pfxFile = CmbcConstants.class.getResource("ceshi.pfx");
		    String pfxFilePath = pfxFile.getPath().replace("%20", " ");
		    logger.info("certFilePath=["+certFilePath+"]");

			JnkyServer jnkyserver=new JnkyServer(certFilePath, pfxFilePath, CmbcConstants.pxfpassword);
					
			String orderinfo = billNo+"|"+txAmt+"|"+PayerCurr+"|"+txDate+"|"+txTime+"|"+corpID+"|"+corpName+
					"|"+Billremark1+"|"+Billremark2+"|"+CorpRetType+"|"+retUrl+"|"+langMAC;
			
			String  decrydata= jnkyserver.EnvelopData(orderinfo,"utf-8");

			String sign = "";
			sendDataBean.setSign(sign);
			sendDataBean.setDecrydata(decrydata);
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
		 logger.info("******开始民生银行页面通知*********");
		 String payresult = request.getParameter("payresult");
		 logger.info("通知信息密文,payresult="+payresult);
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 URL cerFile = CmbcConstants.class.getResource("1024new.cer");
		    String certFilePath = cerFile.getPath().replace("%20", " ");
		    
		    URL pfxFile = CmbcConstants.class.getResource("ceshi.pfx");
		    String pfxFilePath = pfxFile.getPath().replace("%20", " ");

			JnkyServer jnkyserver=new JnkyServer(certFilePath, pfxFilePath, CmbcConstants.pxfpassword);
				
			 String decryptData = jnkyserver.DecryptData(payresult,"utf-8");
			 logger.info("通知信息明文,payresult=["+decryptData+"]");
			 if(!decryptData.contains("|")){
				operateResult.setSuccess(false);
				operateResult.setMessage("订单信息异常！");
				return operateResult;
			 }
			 
			 String resultdatas[] = decryptData.split("\\|");
			 String billNo = resultdatas[0];
			 String corpID = resultdatas[1];
			 String txAmt = resultdatas[2];
			 String txDate = resultdatas[3];
			 String txTime = resultdatas[4];
			 String billstatus = resultdatas[5];
			
			 
			 logger.info("billNo=["+billNo+"]"+"corpID=["+corpID+"]"+"txAmt=["+txAmt+"]"+"txDate=["+txDate+"]"+
					 "txTime=["+txTime+"]"+"billstatus=["+billstatus+"]");
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(billNo);
			 bankReturnBean.setResponseTrdeNo(billNo);
			 bankReturnBean.setResponseAmount(Double.valueOf(txAmt));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
			// 交易状态 -2 : 待确认 ; -1 : 待处理 ; 0 : 支付失败 ; 1 : 成功 ; 2 : 支付中 ; 3 ：已退款； 4 : 退款中 ； 5 :  退款失败   ； 6 ：已取消 ； 取消失败 
			 if("0".equals(billstatus)){
				 bankReturnBean.setResponseStatus(1);
			 }else if("1".equals(billstatus)){
				 bankReturnBean.setResponseStatus(2);
			 }else if("2".equals(billstatus)){
				 bankReturnBean.setResponseStatus(0);
			 }else{
				 operateResult.setSuccess(false);
				 operateResult.setMessage("订单号："+billNo+",订单通知状态异常！");
				 return operateResult;
			 }
			 
		 }catch(Exception e){
			 operateResult.setMessage(e.getMessage());
			 operateResult.setSuccess(false);
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
		logger.info("******开始民生银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			String billNo = traderecord.getTrNo();//订单号
			String corpID=CmbcConstants.corpID;//商户代码
			String queryinfo = billNo+"|"+corpID;
			
			URL cerFile = CmbcConstants.class.getResource("1024new.cer");
		    String certFilePath = cerFile.getPath().replace("%20", " ");
		    
		    URL pfxFile = CmbcConstants.class.getResource("ceshi.pfx");
		    String pfxFilePath = pfxFile.getPath().replace("%20", " ");
		   

			JnkyServer jnkyserver=new JnkyServer(certFilePath, pfxFilePath, CmbcConstants.pxfpassword);
			
			String  cryptograph= jnkyserver.EnvelopData(queryinfo,"utf-8");
			CmbcSendBean  cmbcSendBean = new CmbcSendBean();
			cmbcSendBean.setServiceUrl(CmbcConstants.QUERYURL);
			
			Map<String, String> params = new HashMap<String, String>();
			params.put("cryptograph", cryptograph);
			String resultXml = cmbcWorker.send(params, cmbcSendBean);
			
			logger.info("resultXml="+resultXml);
			
			logger.info("民生网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
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
    		logger.info("******开始民生银行订单退款*********");
    		logger.info("流水号："+traderecord.getTrNo());
    		OperateResult operateResult = new OperateResult();
    		operateResult.setSuccess(true);
    		try{

    			URL cerFile = CmbcConstants.class.getResource("1024new.cer");
    		    String certFilePath = cerFile.getPath().replace("%20", " ");
    		    
    		    URL pfxFile = CmbcConstants.class.getResource("ceshi.pfx");
    		    String pfxFilePath = pfxFile.getPath().replace("%20", " ");
    		   

    			JnkyServer jnkyserver=new JnkyServer(certFilePath, pfxFilePath, CmbcConstants.pxfpassword);
    			
    			
    			String tranCode = "0001";//请求交易码
    			String corpID=CmbcConstants.corpID;//商户代码
    			SimpleDateFormat f=new SimpleDateFormat("yyyyMMddHHmmss");
    			String refundDate= f.format(new Date());//yyyyMMddHHmmss  以年月日时分秒组成
    			String billNo = traderecord.getTrNo();//订单编号
    			String refundNo = traderecord.getTrNo();//退货订单编号
    			String refundAmount = String.valueOf(traderecord.getTrRefundmentAmount());//退货金额
    			String remark="";//备注
    			String notifyUrl = "dddd";//通知商户URL地址
    			
    			String refundinfo = tranCode+"|"+corpID+"|"+refundDate+"|"+billNo+"|"+refundNo+"|"+refundAmount+"|"+remark+"|"+notifyUrl;
    			
    			
    			String  cryptograph= jnkyserver.EnvelopData(refundinfo,"utf-8");
    			
    			CmbcSendBean  cmbcSendBean = new CmbcSendBean();
    			cmbcSendBean.setServiceUrl(CmbcConstants.REFUNDURL);
    			
    			Map<String, String> params = new HashMap<String, String>();
    			params.put("cryptograph", cryptograph);
    			String resultXml = cmbcWorker.send(params, cmbcSendBean);
    			
    			logger.info("resultXml="+resultXml);
    			
    			logger.info("民生网关退款申请结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
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
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(false);
		 operateResult.setMessage("该订单不支持批量查询");
		return operateResult;
	}

	@Override
	public OperateResult cancel(Traderecord traderecord, BankReturnBean bankReturnBean) {
		OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(false);
		 operateResult.setMessage("该订单不支持取消操作");
		return operateResult;
	}

	@Override
	public OperateResult notifyRefund(HttpServletRequest request,
			BankReturnBean bankReturnBean) {
		// TODO Auto-generated method stub
		return null;
	}
	
	 public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 logger.info("******开始民生银行服务器通知*********");
		 String payresult = request.getParameter("payresult");
		 logger.info("通知信息密文,payresult="+payresult);
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{

			 URL cerFile = CmbcConstants.class.getResource("1024new.cer");
		    String certFilePath = cerFile.getPath().replace("%20", " ");
		    
		    URL pfxFile = CmbcConstants.class.getResource("ceshi.pfx");
		    String pfxFilePath = pfxFile.getPath().replace("%20", " ");
		    logger.info("certFilePath=["+certFilePath+"]");

			JnkyServer jnkyserver=new JnkyServer(certFilePath, pfxFilePath, CmbcConstants.pxfpassword);
				
			 String decryptData = jnkyserver.DecryptData(payresult,"utf-8");
			 logger.info("通知信息明文,payresult=["+decryptData+"]");
			 if(!decryptData.contains("|")){
				operateResult.setSuccess(false);
				operateResult.setMessage("订单信息异常！");
				return operateResult;
			 }
			 
			 String resultdatas[] = decryptData.split("\\|");
			 String billNo = resultdatas[0];
			 String corpID = resultdatas[1];
			 String txAmt = resultdatas[2];
			 String txDate = resultdatas[3];
			 String txTime = resultdatas[4];
			 String billstatus = resultdatas[5];
			
			 
			 logger.info("billNo=["+billNo+"]"+"corpID=["+corpID+"]"+"txAmt=["+txAmt+"]"+"txDate=["+txDate+"]"+
					 "txTime=["+txTime+"]"+"billstatus=["+billstatus+"]");
			 
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 bankReturnBean.setResponseBankNo(billNo);
			 bankReturnBean.setResponseTrdeNo(billNo);
			 bankReturnBean.setResponseAmount(Double.valueOf(txAmt));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(DateTimeUtils.getDateTime(new Date())));
			// 交易状态 -2 : 待确认 ; -1 : 待处理 ; 0 : 支付失败 ; 1 : 成功 ; 2 : 支付中 ; 3 ：已退款； 4 : 退款中 ； 5 :  退款失败   ； 6 ：已取消 ； 取消失败 
			 if("0".equals(billstatus)){
				 bankReturnBean.setResponseStatus(1);
			 }else if("1".equals(billstatus)){
				 bankReturnBean.setResponseStatus(2);
			 }else if("2".equals(billstatus)){
				 bankReturnBean.setResponseStatus(0);
			 }else{
				 operateResult.setSuccess(false);
				 operateResult.setMessage("订单号："+billNo+",订单通知状态异常！");
				 return operateResult;
			 }
			 
		 }catch(Exception e){
			 logger.error(e.getMessage());
			 operateResult.setMessage(e.getMessage());
			 operateResult.setSuccess(false);
		 }
		 return operateResult;
	 }
}
