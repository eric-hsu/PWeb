package com.jinxin.service.paygateway.enterprise.abc.impl;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.hitrust.b2b.trustpay.client.TrxResponse;
import com.hitrust.b2b.trustpay.client.b2b.FundTransferRequest;
import com.hitrust.b2b.trustpay.client.b2b.QueryTrnxRequest;
import com.hitrust.b2b.trustpay.client.b2b.TrnxInfo;
import com.hitrust.b2b.trustpay.client.b2b.TrnxItem;
import com.hitrust.b2b.trustpay.client.b2b.TrnxItems;
import com.hitrust.b2b.trustpay.client.b2b.TrnxRemark;
import com.hitrust.b2b.trustpay.client.b2b.TrnxRemarks;
import com.hitrust.b2b.trustpay.client.b2b.TrnxResult;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.enterprise.EnterprisePayGatewayProxy;
import com.jinxin.service.paygateway.enterprise.abc.util.AbcEConstants;
import com.jinxin.service.paygateway.enterprise.abc.util.AbcESendBean;

/**
 * @className:BocServiceImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("abcEService")
@Scope("prototype")
public class AbcEPayGatewayProxyImpl extends EnterprisePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(AbcEPayGatewayProxyImpl.class);

	/**
     * 支付前的操作，用于在线支付(页面支付)
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	public OperateResult prefixPayment(Traderecord traderecord,HttpServletRequest request) {
		logger.info("******开始兴业银行在线支付*********");
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		AbcESendBean sendDataBean = new AbcESendBean();
		try{
			String pweb_server_url  = StaticMapUtil.paramMap.get("pweb_server_url");
			if(StringUtils.isBlank(pweb_server_url)){
				operateResult.setMessage("未配置银行通知地址。");
				return operateResult;
			}

			
		    String tOrderAmountStr  = String.valueOf(traderecord.getTrAmount());
		    double  tTrnxAmount    = Double.parseDouble(tOrderAmountStr);
		    String tResultNotifyURL = pweb_server_url+"/abcEController/backNotify.do";;
		    
			String tMerchantTrnxNo           = traderecord.getTrNo();
			String tTrnxDate                 = DateTimeUtils.formatDate(traderecord.getTrDatetime(),"yyyy/MM/dd");;
			String tTrnxTime                 = DateTimeUtils.formatDate(traderecord.getTrDatetime(),"hh:MM:ss");
			String tAccountDBNo              = AbcEConstants.AccountDBNo;
			String tAccountDBName            = AbcEConstants.AccountDBName;
			       tAccountDBName = new String(tAccountDBName.getBytes("ISO-8859-1"),"gb2312");
			String tAccountDBBank            = AbcEConstants.AccountDBBank;
			
			//2、生成TrnxInfo对象
			TrnxItems tTrnxItems = new TrnxItems();  
			tTrnxItems.addTrnxItem(new TrnxItem("0001",     "显示器",       1000.00f, 2));

			TrnxRemarks tTrnxRemarks = new TrnxRemarks();
			tTrnxRemarks.addTrnxRemark(new TrnxRemark("合同号",  "555000000"));


			TrnxInfo tTrnxInfo = new TrnxInfo();
			tTrnxInfo.setTrnxOpr("TrnxOperator0001");
			tTrnxInfo.setTrnxRemarks(tTrnxRemarks);
			tTrnxInfo.setTrnxItems(tTrnxItems);
			
			//4、生成支付请求对象
			FundTransferRequest tFundTransferRequest = new FundTransferRequest();
			tFundTransferRequest.setTrnxInfo(tTrnxInfo);                           //设定交易细项        （必要信息）
			tFundTransferRequest.setMerchantTrnxNo(tMerchantTrnxNo);               //设定商户交易编号    （必要信息）
			tFundTransferRequest.setTrnxAmount(tTrnxAmount);                       //设定交易金额        （必要信息）
			tFundTransferRequest.setTrnxDate(tTrnxDate);                           //设定交易日期        （必要信息）
			tFundTransferRequest.setTrnxTime(tTrnxTime);                           //设定交易时间        （必要信息）
			tFundTransferRequest.setAccountDBNo(tAccountDBNo);                     //设定收款方账号      （必要信息）
			tFundTransferRequest.setAccountDBName(tAccountDBName);                 //设定收款方账户名    （必要信息）
			tFundTransferRequest.setAccountDBBank(tAccountDBBank);                 //设定收款方账户开户行联行号（必要信息）
			tFundTransferRequest.setResultNotifyURL(tResultNotifyURL);             //设定交易结果回传网址（必要信息）
			tFundTransferRequest.setMerchantRemarks("jinxin order");             //设定商户备注信息
			 
			//4、传送直接支付请求并取得支付网址
			TrxResponse tTrxResponse = tFundTransferRequest.postRequest();
			
			if (tTrxResponse.isSuccess()) {
				//6、支付请求提交成功，将客户端导向支付页面
				logger.info("农行订单发送成功，流水号："+traderecord.getTrNo()+"");
				operateResult.setSuccess(true);
				sendDataBean.setServiceUrl(tTrxResponse.getValue("PaymentURL"));
			   request.setAttribute("sendDataBean", sendDataBean);
			}else{
				logger.info("农行订单发送失败，流水号："+traderecord.getTrNo()+"，返回码："+tTrxResponse.getReturnCode()+",错误原因："+tTrxResponse.getErrorMessage());
				operateResult.setSuccess(false);
				operateResult.setMessage(tTrxResponse.getErrorMessage()+"("+tTrxResponse.getReturnCode()+")");
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
			 if(StringUtils.isBlank(request.getParameter("MSG"))){
				 operateResult.setSuccess(false);
				 operateResult.setMessage("通知信息错误！");
					return operateResult;
				}
			 bankReturnBean.setReq(request);
			 bankReturnBean.setResp(response);
			 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
			 TrnxResult tResult = new TrnxResult(request.getParameter("MSG"));
			 //PaymentResult tResult = new PaymentResult("PE1TRz48TWVzc2FnZT48VHJ4UmVzcG9uc2U+PFJldHVybkNvZGU+MDAwMDwvUmV0dXJuQ29kZT48RXJyb3JNZXNzYWdlPjwvRXJyb3JNZXNzYWdlPjxFQ01lcmNoYW50VHlwZT5CMkM8L0VDTWVyY2hhbnRUeXBlPjxNZXJjaGFudElEPjM0NDEwMDAwNDAwM0EwMTwvTWVyY2hhbnRJRD48VHJ4VHlwZT5QYXlSZXN1bHQ8L1RyeFR5cGU+PE9yZGVyTm8+MjAxNDA3MjQxNjU3MjE2MTYxMjA8L09yZGVyTm8+PEFtb3VudD4wLjAxPC9BbW91bnQ+PEJhdGNoTm8+MDAwMDA0PC9CYXRjaE5vPjxWb3VjaGVyTm8+MDAwMDQzPC9Wb3VjaGVyTm8+PEhvc3REYXRlPjIwMTQvMDcvMjQ8L0hvc3REYXRlPjxIb3N0VGltZT4xNjo1ODozMDwvSG9zdFRpbWU+PE1lcmNoYW50UmVtYXJrcz5wYXk8L01lcmNoYW50UmVtYXJrcz48UGF5VHlwZT5QQVkwMTwvUGF5VHlwZT48Tm90aWZ5VHlwZT4xPC9Ob3RpZnlUeXBlPjxQYXlJUD4xODMuMTYuMTQ2Ljc5PC9QYXlJUD48UGF5UmVmZXJlcj4xMTkuMTQ3LjIxNy4xNDk8L1BheVJlZmVyZXI+PGlSc3BSZWY+MzkwNzI0MDI1NzA5PC9pUnNwUmVmPjwvVHJ4UmVzcG9uc2U+PC9NZXNzYWdlPjxTaWduYXR1cmUtQWxnb3JpdGhtPlNIQTF3aXRoUlNBPC9TaWduYXR1cmUtQWxnb3JpdGhtPjxTaWduYXR1cmU+bGxhc282YU9DbElMY3cySXJPdzdVZ3BBZ3NBbTR1ZmUyc1M0NnhmdXlTVkt6QXpKWWpiaXM5dzhLb2RiMTJTWXVwenVzOTcxSlZwUndCL1MrQmZvem5OM1VFdFFwMnNMUmJnZG1iNUNXZUdnNEtqTDVxTkpOdWZqa1ZsSzErdUJqWEFoNkR0MnNDNGhkKzJtWFhmSjZPUnVuSXpLemNjSHVaMzg3cnZybkFVPTwvU2lnbmF0dXJlPjwvTVNHPg==");
			 
			//2、判断支付结果状态，进行后续操作
			if (tResult.isSuccess()) {
				 bankReturnBean.setResponseBankNo(tResult.getValue("iRspRef"));
				 bankReturnBean.setResponseTrdeNo(tResult.getValue("OrderNo"));
				 bankReturnBean.setResponseBatchNo(tResult.getValue("BatchNo"));
				 bankReturnBean.setResponseInfo(tResult.getValue("MerchantRemarks"));
				 bankReturnBean.setResponseCode(tResult.getValue("VoucherNo"));
				 bankReturnBean.setResponseAmount(Double.valueOf(tResult.getValue("Amount")));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(tResult.getValue("HostDate").replace("/", "-")+" "+tResult.getValue("HostTime")));
				 bankReturnBean.setResponseQueryNo(tResult.getValue("OrderNo"));
				 bankReturnBean.setResponseType(tResult.getValue("NotifyType"));
				 bankReturnBean.setResponseStatus(1);
				logger.info("服务器支付结果通知，流水号："+tResult.getValue("OrderNo")+",支付金额："+tResult.getValue("Amount")+",支付状态：成功");
			}else{
				bankReturnBean.setResponseStatus(0);
				bankReturnBean.setResponseInfo(tResult.getErrorMessage());
				bankReturnBean.setResponseCode(tResult.getReturnCode());
				operateResult.setSuccess(false);
				operateResult.setMessage(tResult.getErrorMessage());
				//4、支付失败
				logger.info("服务器支付结果通知，流水号："+tResult.getValue("OrderNo")+",支付金额："+tResult.getValue("Amount")+",支付状态：支付失败"+",失败原因："+tResult.getErrorMessage()+",返回码："+tResult.getReturnCode());
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
				 if(StringUtils.isBlank(request.getParameter("MSG"))){
					 operateResult.setSuccess(false);
					 operateResult.setMessage("通知信息错误！");
						return operateResult;
					}
				 bankReturnBean.setReq(request);
				 bankReturnBean.setResp(response);
				 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				 TrnxResult tResult = new TrnxResult(request.getParameter("MSG"));
				
				//2、判断支付结果状态，进行后续操作
				if (tResult.isSuccess()) {
					 bankReturnBean.setResponseBankNo(tResult.getValue("iRspRef"));
					 bankReturnBean.setResponseTrdeNo(tResult.getValue("OrderNo"));
					 bankReturnBean.setResponseBatchNo(tResult.getValue("BatchNo"));
					 bankReturnBean.setResponseInfo(tResult.getValue("MerchantRemarks"));
					 bankReturnBean.setResponseCode(tResult.getValue("VoucherNo"));
					 bankReturnBean.setResponseAmount(Double.valueOf(tResult.getValue("Amount")));
					 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(tResult.getValue("HostDate").replace("/", "-")+" "+tResult.getValue("HostTime")));
					 bankReturnBean.setResponseQueryNo(tResult.getValue("OrderNo"));
					 bankReturnBean.setResponseType(tResult.getValue("NotifyType"));
					 bankReturnBean.setResponseStatus(1);
					logger.info("支付结果通知，流水号："+tResult.getValue("OrderNo")+",支付金额："+tResult.getValue("Amount")+",支付状态：成功");
				}else{
					bankReturnBean.setResponseStatus(0);
					bankReturnBean.setResponseInfo(tResult.getErrorMessage());
					bankReturnBean.setResponseCode(tResult.getReturnCode());
					operateResult.setSuccess(false);
					operateResult.setMessage(tResult.getErrorMessage());
					//4、支付失败
					logger.info("支付结果通知，流水号："+tResult.getValue("OrderNo")+",支付金额："+tResult.getValue("Amount")+",支付状态：支付失败"+",失败原因："+tResult.getErrorMessage()+",返回码："+tResult.getReturnCode());
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
		logger.info("******开始农业银行离线查询*********");
	    logger.info("查询流水号："+traderecord.getTrNo());
	    	
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
		try{
			
			if(traderecord==null){
				operateResult.setSuccess(false);
				operateResult.setMessage("参数不合法");
				return operateResult;
			}
			String tradeNo   = traderecord.getTrNo();
			//1、取得交易查询请求所需要的信息
			String tMerchantTrnxNo           = traderecord.getTrNo();;
			String tMerchantRemarks          = "jinxin query";

			//2、生成交易查询请求对象
			QueryTrnxRequest tQueryTrnxRequest = new QueryTrnxRequest();
			tQueryTrnxRequest.setMerchantTrnxNo(tMerchantTrnxNo);               //设定商户交易编号    （必要信息）
			tQueryTrnxRequest.setMerchantRemarks(tMerchantRemarks);             //设定商户备注信息
			 
			//3、传送交易查询请求并取得支付网址
			TrxResponse tTrxResponse = tQueryTrnxRequest.postRequest();
			if (tTrxResponse.isSuccess()) {
			//4、交易查询请求提交成功
				logger.info("TrnxType       = [" + tTrxResponse.getValue("TrnxType"  )     + "]");
				logger.info("TrnxAMT        = [" + tTrxResponse.getValue("TrnxAMT"  )      + "]");
				logger.info("MerchantID     = [" + tTrxResponse.getValue("MerchantID"  )   + "]");
				logger.info("MerchantTrnxNo = [" + tTrxResponse.getValue("MerchantTrnxNo") + "]");
				logger.info("ReturnCode     = [" + tTrxResponse.getValue("ReturnCode")     + "]");
				logger.info("TrnxStatus     = [" + tTrxResponse.getValue("TrnxStatus")     + "]");
				logger.info("TrnxTime       = [" + tTrxResponse.getValue("TrnxTime")     + "  ]");
				logger.info("CustomerNo     = [" + tTrxResponse.getValue("CorporationCustomerNo")     + "  ]");
				logger.info("TrnxSN         = [" + tTrxResponse.getValue("TrnxSN")     + "  ]");
				logger.info("AccountNo      = [" + tTrxResponse.getValue("AccountNo")     + "  ]");
				logger.info("AccountName    = [" + tTrxResponse.getValue("AccountName")     + "  ]");
				logger.info("AccountBank    = [" + tTrxResponse.getValue("AccountBank")     + "  ]");
				logger.info("AccountDBNo    = [" + tTrxResponse.getValue("AccountDBNo")     + "  ]");
				logger.info("AccountDBName  = [" + tTrxResponse.getValue("AccountDBName")     + "  ]");
				logger.info("AccountDBBank  = [" + tTrxResponse.getValue("AccountDBBank")     + "  ]");
				
				  
				 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				 bankReturnBean.setResponseTrdeNo(tTrxResponse.getValue("MerchantTrnxNo"));
				 bankReturnBean.setResponseBankNo(tTrxResponse.getValue("TrnxSN"));
				 bankReturnBean.setResponseInfo(tTrxResponse.getValue("ReturnCode"));
				 bankReturnBean.setResponseAmount(Double.valueOf(tTrxResponse.getValue("TrnxAMT")));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(tTrxResponse.getValue("TrnxTime")));
				 if("2".equals(tTrxResponse.getValue("TrnxStatus"))){
					 bankReturnBean.setResponseStatus(1);
				 }else{
					 bankReturnBean.setResponseStatus(2);
				 }
			}
			else {
			//5、交易查询请求提交失败，商户自定后续动作
			  	operateResult.setSuccess(false);
				operateResult.setMessage(tTrxResponse.getErrorMessage());
				logger.info("农业银行返回查询操作结果：失败 ，流水号： " + traderecord.getTrNo() + ",ReturnCode:"+tTrxResponse.getReturnCode()+",ErrorMessage:"+tTrxResponse.getErrorMessage());
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
    	operateResult.setMessage("农业银行企业网银不支持退款！");
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
