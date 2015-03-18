package com.jinxin.model;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 返回商户接口信息Bean</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-11-30下午02:21:42
 */
public class ReturnMerchantBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;

	/** 商户交易的返回地址 **/
	private String returnUrl;
	
	/** 商户号 **/
	private String merNo;
	
	/** 网关接入号 **/
	private String gatewayNo;
	
	/** 签名Key **/
	private String signKey;
	
	/** 流水订单号 **/
	private String tradeNo;
	
	/** 商户订单号 **/
	private String orderNo;
	
	/** 交易币种 **/
	private String orderCurrency;
	
	/** 交易金额：String类型，防止商户传入的值是非法金额 **/
	private String orderAmount;
	
	/** 退款金额金额：String类型，防止商户传入的值是非法金额 **/
	private String refundAmount;
	
	/** 退款结果结果 **/
	private int refundStatus;
	
	/** 交易结果 **/
	private int orderStatus;
	
	/** 交易信息 **/
	private String orderInfo;
	
	/** 签名信息 **/
	private String signInfo;
	
	/** 备注 **/
	private String remark;

	public String getReturnUrl() {
		return returnUrl;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	public String getMerNo() {
		return merNo;
	}

	public void setMerNo(String merNo) {
		this.merNo = merNo;
	}

	public String getGatewayNo() {
		return gatewayNo;
	}

	public void setGatewayNo(String gatewayNo) {
		this.gatewayNo = gatewayNo;
	}

	public String getSignKey() {
		return signKey;
	}

	
	
	public String getRefundAmount() {
		return refundAmount;
	}

	public void setRefundAmount(String refundAmount) {
		this.refundAmount = refundAmount;
	}

	public void setSignKey(String signKey) {
		this.signKey = signKey;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getOrderCurrency() {
		return orderCurrency;
	}

	public void setOrderCurrency(String orderCurrency) {
		this.orderCurrency = orderCurrency;
	}

	public String getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(String orderAmount) {
		this.orderAmount = orderAmount;
	}

	public int getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(int orderStatus) {
		this.orderStatus = orderStatus;
	}

	public String getOrderInfo() {
		return orderInfo;
	}

	public void setOrderInfo(String orderInfo) {
		this.orderInfo = orderInfo;
	}

	public String getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(String signInfo) {
		this.signInfo = signInfo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getRefundStatus() {
		return refundStatus;
	}

	public void setRefundStatus(int refundStatus) {
		this.refundStatus = refundStatus;
	}
}
