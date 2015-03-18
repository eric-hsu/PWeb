package com.jinxin.service.paygateway.online.cgb.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
import com.jinxin.service.paygateway.OnlineWorker;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.cgb.util.CgbConstants;
import com.jinxin.service.paygateway.online.cgb.util.CgbSendBean;
import com.jinxin.service.paygateway.online.cgb.util.CgbUtils;
import com.jinxin.service.paygateway.online.cib.util.EncryptUtil;


/**
 * @className:CgbPayGatewayProxyImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("cgbService")
@Scope("prototype")
public class CgbPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(CgbPayGatewayProxyImpl.class);
	@Autowired
	private OnlineWorker cgbWorker;
	/**
     * 支付前的操作，用于在线支付(页面支付)
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	public OperateResult prefixPayment(Traderecord traderecord,HttpServletRequest request) {
		logger.info("******开始广发银行在线支付*********");
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		CgbSendBean sendDataBean = new CgbSendBean();
		try{
						
			String merchid = CgbConstants.MERCHID;
			String orderid = traderecord.getTrNo();
			String amount = String.valueOf(traderecord.getTrAmount());
			String signdata = merchid + orderid + amount;		//'合并源数据
			OperateResult result =  CgbUtils.sign(signdata,"");	//'执行签名packetSign(String as[]),并获得签名结果=String sign_md(String s, String s1)返回值
			
			if(!result.isSuccess()){
				operateResult.setMessage(result.getMessage());
				return operateResult;
			}

			String signResult = result.getMessage();
			
			String GdbURL = CgbConstants.PAYURL;
			
			String pweb_server_url= StaticMapUtil.paramMap.get("pweb_server_url");
			if(signResult.length() > 10 ){
				sendDataBean.setServiceUrl(GdbURL);
				sendDataBean.setMerNo(merchid);
				sendDataBean.setTrNo(orderid);
				sendDataBean.setAmount(amount);
				sendDataBean.setSign(signResult);
				sendDataBean.setReturnurl(pweb_server_url+"/cgbController/backNotify.do");
				request.setAttribute("sendDataBean", sendDataBean);
				operateResult.setSuccess(true);
			}else{
				operateResult.setMessage("签名值异常！");
				return operateResult;
			}

			
		}catch(Exception e){
			operateResult.setMessage(e.getMessage());
			return operateResult;
		}
		return operateResult;
	}

	  /**
     * 收款Notify调用代理，用于在线支付(后台通知)
     * 
     * @param request
     *            从request获取变量值
     * @return
     * @throws Exception 
     */
	 public OperateResult backNotifyPayment(HttpServletRequest request,HttpServletResponse response,BankReturnBean bankReturnBean) throws Exception{
		 
		 OperateResult operateResult = new OperateResult();
		 operateResult.setSuccess(true);
		 try{
			 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
			 
			 //商户号
			 String merchid = request.getParameter("merchid");
			 //订单号
			 String orderid = request.getParameter("orderid");
			 //订单金额
			 String amount = request.getParameter("amount");
			 //返回码
			 String success = request.getParameter("success");
			 //流水号
			 String sequence = request.getParameter("sequence");
			 //订单日期
			 String orderdate = request.getParameter("orderdate");
			 //订单签名
			 String crypt = request.getParameter("crypt");
			 //账户类型
			 String accttype = request.getParameter("accttype");
			 //卡类型
			 String percardtype = request.getParameter("percardtype");
			 
			 String signstr = "orderid"+orderid+"amount"+amount+"orderdate"+orderdate+"success"+success+"sequence"+sequence+"merchid"+merchid;
			 String sign = EncryptUtil.getMD5Encrypt(signstr);
			 
			 if(!crypt.equals(sign)){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("订单签名错误！");
				 return operateResult;
			 }

			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			
			 bankReturnBean.setResponseBankNo(sequence);
			 bankReturnBean.setResponseTrdeNo(orderid);
			 bankReturnBean.setResponseBatchNo("");
			 bankReturnBean.setResponseInfo("");
			 bankReturnBean.setResponseCode(success);
			 bankReturnBean.setResponseAmount(Double.valueOf(amount));
			 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(orderdate));
			 bankReturnBean.setResponseQueryNo("");
			 bankReturnBean.setResponseType("0");
			 if("RC000".equals(success)){
				 bankReturnBean.setResponseStatus(1);
				 logger.info("支付结果通知，流水号："+orderid+",支付金额："+amount+",支付状态：成功");
			 }else{
				 bankReturnBean.setResponseStatus(0);
				 logger.info("支付结果通知，流水号："+orderid+",支付金额："+amount+",支付状态：失败");
			 }
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
			 
			 OperateResult operateResult = new OperateResult();
			 operateResult.setSuccess(true);
			 try{
				 BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
				 
				 //商户号
				 String merchid = request.getParameter("merchid");
				 //订单号
				 String orderid = request.getParameter("orderid");
				 //订单金额
				 String amount = request.getParameter("amount");
				 //返回码
				 String success = request.getParameter("success");
				 //流水号
				 String sequence = request.getParameter("sequence");
				 //订单日期
				 String orderdate = request.getParameter("orderdate");
				 //订单签名
				 String crypt = request.getParameter("crypt");
				 //账户类型
				 String accttype = request.getParameter("accttype");
				 //卡类型
				 String percardtype = request.getParameter("percardtype");
				 
				 String signstr = "orderid"+orderid+"amount"+amount+"orderdate"+orderdate+"success"+success+"sequence"+sequence+"merchid"+merchid;
				 String sign = EncryptUtil.getMD5Encrypt(signstr);
				 
				 if(!crypt.equals(sign)){
					 operateResult.setSuccess(false);
					 operateResult.setMessage("订单签名错误！");
					 return operateResult;
				 }

				 bankReturnBean.setReq(request);
				 bankReturnBean.setResp(response);
				 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				
				 bankReturnBean.setResponseBankNo(sequence);
				 bankReturnBean.setResponseTrdeNo(orderid);
				 bankReturnBean.setResponseBatchNo("");
				 bankReturnBean.setResponseInfo("");
				 bankReturnBean.setResponseCode(success);
				 bankReturnBean.setResponseAmount(Double.valueOf(amount));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(orderdate));
				 bankReturnBean.setResponseQueryNo("");
				 bankReturnBean.setResponseType("0");
				 if("RC000".equals(success)){
					 bankReturnBean.setResponseStatus(1);
					 logger.info("支付结果通知，流水号："+orderid+",支付金额："+amount+",支付状态：成功");
				 }else{
					 bankReturnBean.setResponseStatus(0);
					 logger.info("支付结果通知，流水号："+orderid+",支付金额："+amount+",支付状态：失败");
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
		logger.info("******开始广发银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
		try{
			String sendXml="";
			String merchid = CgbConstants.MERCHID;
			String orderid = traderecord.getTrNo();
			String returl = StaticMapUtil.paramMap.get("NOTIFY_CGB_BACK_URL");
			
			String signstr = "orderid"+orderid+"merchid"+merchid+"returl"+returl;			
			
			OperateResult result =  CgbUtils.sign(signstr,"");	//'执行签名packetSign(String as[]),并获得签名结果=String sign_md(String s, String s1)返回值
			
			if(!result.isSuccess()){
				operateResult.setMessage(result.getMessage());
				return operateResult;
			}
			
			CgbSendBean sendDataBean = new CgbSendBean();
			sendDataBean.setServiceUrl(CgbConstants.QUERYURL);
			sendDataBean.setTrNo(orderid);
			sendDataBean.setMerNo(merchid);
			sendDataBean.setReturnurl(returl);
			sendDataBean.setSign(result.getMessage());

			Map<String, String> params = new HashMap<String, String>();
			String receiveXml = cgbWorker.send(params, sendDataBean);
			
			operateResult = CgbUtils.resultParam(receiveXml, bankReturnBean);
			if(!operateResult.isSuccess()){
				logger.info("广发银行返回查询操作结果：失败 ，流水号： " + traderecord.getTrNo() +",ErrorMessage:"+operateResult.getMessage());
				return operateResult;
			}
			
			logger.info("广发网关查询结果，流水号："+traderecord.getTrNo()+",支付金额："+bankReturnBean.getResponseAmount()+",支付状态返回码："+bankReturnBean.getResponseStatus());
			
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
		return null;
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
