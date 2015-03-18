package com.jinxin.service.paygateway.online.abc.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.hitrust.trustpay.client.TrxResponse;
import com.hitrust.trustpay.client.XMLDocument;
import com.hitrust.trustpay.client.b2c.Order;
import com.hitrust.trustpay.client.b2c.OrderItem;
import com.hitrust.trustpay.client.b2c.PaymentRequest;
import com.hitrust.trustpay.client.b2c.PaymentResult;
import com.hitrust.trustpay.client.b2c.QueryOrderRequest;
import com.hitrust.trustpay.client.b2c.RefundRequest;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.utils.StaticMapUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.BankTradeInfoBean;
import com.jinxin.model.online.OlineSendDataBean;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.paygateway.online.OnlinePayGatewayProxy;
import com.jinxin.service.paygateway.online.abc.util.AbcConstants;
import com.jinxin.service.paygateway.online.abc.util.AbcSendBean;


/**
 * @className:BocServiceImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:06:00
 */
@Service("abcService")
@Scope("prototype")
public class AbcPayGatewayProxyImpl extends OnlinePayGatewayProxy{
	private static final Logger logger = Logger.getLogger(AbcPayGatewayProxyImpl.class);

	/**
     * 支付前的操作，用于在线支付(页面支付)
     * 
     * @param TOrder
     *            任务单对象
     * @return
     */
	public OperateResult prefixPayment(Traderecord traderecord,HttpServletRequest request) {
		logger.info("******开始农业银行在线支付*********");
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(false);
		AbcSendBean sendDataBean = new AbcSendBean();
		try{
			String pweb_server_url  = StaticMapUtil.paramMap.get("pweb_server_url");
			if(StringUtils.isBlank(pweb_server_url)){
				operateResult.setMessage("未配置银行通知地址。");
				return operateResult;
			}
			String orderDate = DateTimeUtils.formatDate(traderecord.getTrDatetime(),"yyyy/MM/dd");
			String orderTime = DateTimeUtils.formatDate(traderecord.getTrDatetime(),"hh:MM:ss");
			//订单编号（必要信息）
			String tOrderNo         = traderecord.getTrNo();
			//订单有效期(必填信息), 订单有效期是该账单在农行数据库中保留的天数，超过有效期的订单将会被清理。
			String tExpiredDate     = AbcConstants.EXPIREDDATE;
			//订单说明
			String tOrderDesc       = "jinxinpay";
			//订单日期（必要信息- YYYY/MM/DD）
			String tOrderDate       = orderDate;
			//订单时间（必要信息- HH:MM:SS）
			String tOrderTime       = orderTime;
			//订单金额（必要信息）
			String tOrderAmountStr  = String.valueOf(traderecord.getTrAmount());
			//订单查询网址（必要信息）
			String tOrderURL        = "";
			//若不指定，系统默认是农行卡支付。1：农行卡支付网上支付平台将会回传农行卡支付页面。2：国际卡支付网上支付平台将会回传国际卡支付页面。3：贷记卡支付网上支付平台将会回传贷记卡支付页面。A：农行卡和贷记卡支付合并网上支付平台将会回传农行卡和贷记卡支付合并支付页面。5：第三方的跨行支付
			String tPaymentType     = "1";
			//必须设定消费者支付成功后，网上支付平台会将支付结果信息发送到此网址。
			String tResultNotifyURL = pweb_server_url+"/abcController/backNotify.do";
			//网上支付平台的支付结果信息将包含此备注信息。商户可以用来传递所需的信息。
			String tMerchantRemarks = "jinxinpay";
			double  tOrderAmount    = Double.parseDouble(tOrderAmountStr);
			//1：internet网络接入 2：手机网络接入 3:数字电视网络接入 4:智能客户端
			String tPaymentLinkType = "1";
			//买方IP 地址信息
			String tBuyIP = traderecord.getTrIp();

			//2、生成订单对象
			Order tOrder = new Order();
			tOrder.setOrderNo    (tOrderNo    ); //设定订单编号 （必要信息）
			tOrder.setExpiredDate(new Integer(tExpiredDate).intValue()); //设定订单有效期 （必要信息）
			tOrder.setOrderDesc  (tOrderDesc  ); //设定订单说明
			tOrder.setOrderDate  (tOrderDate  ); //设定订单日期 （必要信息 - YYYY/MM/DD）
			tOrder.setOrderTime  (tOrderTime  ); //设定订单时间 （必要信息 - HH:MM:SS）
			tOrder.setOrderAmount(tOrderAmount); //设定订单金额 （必要信息）
			tOrder.setOrderURL   (tOrderURL   ); //设定订单网址
			tOrder.setBuyIP   (tBuyIP   ); //设定订单IP

			//3、生成定单订单对象，并将订单明细加入定单中（可选信息）
			tOrder.addOrderItem(new OrderItem("IP000001", "电子商务产品", tOrderAmount, 1));

			//4、生成支付请求对象
			PaymentRequest tPaymentRequest = new PaymentRequest();
			tPaymentRequest.setOrder      (tOrder      ); //设定支付请求的订单 （必要信息）
			tPaymentRequest.setProductType(PaymentRequest.PRD_TYPE_TWO); //设定商品种类 （必要信息）
			                                              //PaymentRequest.PRD_TYPE_ONE：非实体商品，如服务、IP卡、下载MP3、...
			                                              //PaymentRequest.PRD_TYPE_TWO：实体商品
			tPaymentRequest.setPaymentType(tPaymentType); //设定支付类型
			                                              //PaymentRequest.PAY_TYPE_ABC：农行卡支付
			                                              //PaymentRequest.PAY_TYPE_INT：国际卡支付
			tPaymentRequest.setNotifyType(PaymentRequest.NOTIFY_TYPE_SERVER);   //设定商户通知方式
			                                              //0：URL页面通知
			                                              //1：服务器通知
			tPaymentRequest.setResultNotifyURL(tResultNotifyURL); //设定支付结果回传网址 （必要信息）
			tPaymentRequest.setMerchantRemarks(tMerchantRemarks); //设定商户备注信息
			tPaymentRequest.setPaymentLinkType(tPaymentLinkType);//设定支付接入方式

			//5、传送支付请求并取得支付网址
			//TrxResponse tTrxResponse = tPaymentRequest.postRequest();
			TrxResponse tTrxResponse = tPaymentRequest.extendPostRequest(1);
			
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
			 PaymentResult tResult = new PaymentResult(request.getParameter("MSG"));
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
				 PaymentResult tResult = new PaymentResult(request.getParameter("MSG"));
				
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
			//2、生成商户订单查询请求对象
			QueryOrderRequest tRequest = new QueryOrderRequest();
			tRequest.setOrderNo(tradeNo);  //订单号           （必要信息）
			tRequest.enableDetailQuery(false);  //是否查询详细信息 （必要信息）
	
			//3、传送商户订单查询请求并取得订单查询结果
			TrxResponse tResponse = tRequest.postRequest();
			 Order tOrder = null;
			//2、判断支付结果状态，进行后续操作
			if (tResponse.isSuccess()) {
				 logger.info("农业银行返回查询操作结果：成功"); 
				 tOrder = new Order(new XMLDocument(tResponse.getValue("Order")));
				 
				 logger.info("OrderNo      = [" + tOrder.getOrderNo     () + "]<br>");
				 logger.info("OrderAmount  = [" + tOrder.getOrderAmount () + "]<br>");
				 logger.info("OrderDesc    = [" + tOrder.getOrderDesc   () + "]<br>");
				 logger.info("OrderDate    = [" + tOrder.getOrderDate   () + "]<br>");
				 logger.info("OrderTime    = [" + tOrder.getOrderTime   () + "]<br>");
				 logger.info("OrderURL     = [" + tOrder.getOrderURL    () + "]<br>");
				 logger.info("PayAmount    = [" + tOrder.getPayAmount   () + "]<br>");
				 logger.info("RefundAmount = [" + tOrder.getRefundAmount() + "]<br>");
				 logger.info("OrderStatus  = [" + tOrder.getOrderStatus () + "]<br>");
				  
				 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				 bankReturnBean.setResponseTrdeNo(tOrder.getOrderNo());
				 bankReturnBean.setResponseInfo(tOrder.getOrderDesc());
				 bankReturnBean.setResponseAmount(Double.valueOf(tOrder.getOrderAmount()));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestamp(tOrder.getOrderDate().replace("/", "-")+" "+tOrder.getOrderTime()));
				//00：订单已取消       01：订单已建立，等待支付       02：消费者已支付，等待支付结果      03：订单已支付（支付成功     04：订单已结算（支付成功）     05：订单已退款       99：订单支付失败
				 //支付成功
				 if("02".equals(tOrder.getOrderStatus()) || "03".equals(tOrder.getOrderStatus()) || "04".equals(tOrder.getOrderStatus())){
					 bankReturnBean.setResponseStatus(1);
				//支付失败
				}else if("99".equals(tOrder.getOrderStatus())){
					bankReturnBean.setResponseStatus(0);
				//支付中
				}else if("01".equals(tOrder.getOrderStatus())){
					bankReturnBean.setResponseStatus(2);
				//订单已退款
				}else if("05".equals(tOrder.getOrderStatus())){
					bankReturnBean.setResponseStatus(3);
				}else if("00".equals(tOrder.getOrderStatus())){
					bankReturnBean.setResponseStatus(6);
				}
				logger.info("农行网关查询结果，流水号："+tOrder.getOrderNo()+",支付金额："+tOrder.getOrderAmount()+",支付状态返回码："+tOrder.getOrderStatus());
			}else{
				operateResult.setSuccess(false);
				operateResult.setMessage(tResponse.getErrorMessage());
				logger.info("农业银行返回查询操作结果：失败 ，流水号： " + traderecord.getTrNo() + ",ReturnCode:"+tResponse.getReturnCode()+",ErrorMessage:"+tResponse.getErrorMessage());
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
    	
    	logger.info("******开始农业银行离线退款*********");
    	logger.info("退款流水号："+traderecord.getTrNo()+",退款金额："+traderecord.getTrRefundmentAmount()+",银行流水号："+traderecord.getTrBankorderno());
    	OperateResult operateResult = new OperateResult();
    	operateResult.setSuccess(true);
    	BankTradeInfoBean bankTradeInfoBean = new BankTradeInfoBean();
		try{
			//1、取得商户订单查询所需要的信息
			String tOrderNo   = traderecord.getTrNo();
			String tTrxAmountStr = String.valueOf(traderecord.getTrRefundmentAmount());
			String tNewOrderNo =traderecord.getTrBankorderno();
			if(StringUtils.isBlank(tOrderNo)|| StringUtils.isBlank(tTrxAmountStr)){
				operateResult.setSuccess(false);
				operateResult.setMessage("参数不合法");
				return operateResult;
			}
			double  tTrxAmount    = Double.parseDouble(tTrxAmountStr);
			//2、生成退货请求对象
			RefundRequest tRequest = new RefundRequest();
			tRequest.setOrderNo  (tOrderNo  );  //订单号   （必要信息）
			//tRequest.setNewOrderNo  (tNewOrderNo);  //新订单号   （必要信息）
			tRequest.setTrxAmount(tTrxAmount);  //退货金额 （必要信息）
	
			//3、传送退货请求并取得退货结果
			TrxResponse tResponse = tRequest.postRequest();
			
			//4、判断退货结果状态，进行后续操作
			if (tResponse.isSuccess()) {
				logger.info("农业银行返回退款申请操作结果：成功");
				//5、退货成功
				logger.info("TrxType   = [" + tResponse.getValue("TrxType"  ) + "]<br>");
				//订单号
				logger.info("OrderNo   = [" + tResponse.getValue("OrderNo"  ) + "]<br>");
				//退款订单号
				logger.info("NewOrderNo   = [" + tResponse.getValue("NewOrderNo"  ) + "]<br>");
				//退货金额
				logger.info("TrxAmount = [" + tResponse.getValue("TrxAmount") + "]<br>");
				//交易批次号
				logger.info("BatchNo   = [" + tResponse.getValue("BatchNo"  ) + "]<br>");
				//交易凭证号（用于交易对账时使用）
				logger.info("VoucherNo = [" + tResponse.getValue("VoucherNo") + "]<br>");
				//银行交易日期（YYYY/MM/DD）
				logger.info("HostDate  = [" + tResponse.getValue("HostDate" ) + "]<br>");
				//银行交易时间（HH:MM:SS）
				logger.info("HostTime  = [" + tResponse.getValue("HostTime" ) + "]<br>");
				//银行返回交易流水号
				logger.info("TrnxNo    = [" + tResponse.getValue("iRspRef"  ) + "]<br>");
				
				 bankReturnBean.setTradeInfoBean(bankTradeInfoBean);
				 bankReturnBean.setResponseTrdeNo(tResponse.getValue("OrderNo"));
				 bankReturnBean.setResponseBankNo(tResponse.getValue("iRspRef"));
				 bankReturnBean.setResponseBatchNo(tResponse.getValue("BatchNo"));
				 bankReturnBean.setResponseRefundAmount(Double.valueOf(tResponse.getValue("TrxAmount")));
				 bankReturnBean.setResponseTradeTime(DateTimeUtils.parseTimestampT(tResponse.getValue("HostDate").replace("/", "-")+" "+tResponse.getValue("HostTime")));
				 bankReturnBean.setResponseStatus(3);
			}else {
				//退款订单号
				logger.info("ReturnCode   = [" + tResponse.getReturnCode() + "]<br>");
				//退货金额
				logger.info("ErrorMessage = [" + tResponse.getErrorMessage() + "]<br>");
				logger.info("农业银行返回退款申请操作结果：失败 ，流水号： " + tOrderNo + ",ReturnCode:"+tResponse.getReturnCode()+",ErrorMessage:"+tResponse.getErrorMessage());
				operateResult.setSuccess(false);
				operateResult.setMessage("(银行退款失败原因)"+tResponse.getErrorMessage());
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
