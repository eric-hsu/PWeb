/**
 * create date 2009-5-6
 */
package com.jinxin.common.valueobj;

import java.util.Date;


/**
 * @author eric
 * 
 */
public class MerResult {

	 /**
     * 通知类型，1，支付结果通知，2，查询结果通知，3，退款结果通知
     */
    private String type;
    
    /**
     * 商户号
     */
    private String merNo;
    
    /**
     * 商户网关接入号
     */
    private String gatewayNo;
    
    /**
     * 商户订单号
     */
    private String orderNo;
    
    /**
     * 平台支付流水号
     */
    private String tradeNo;
    
    /**
     * 交易状态
     */
    private String status;

    /**
     * 签名信息（商户号+平台接入号+商户订单号+平台流水号+key）
     */
    private String signInfo;

    /**
     * 网关支付通知应答信息
     */
    private String remark;
    
    /**
     * 前台通知地址
     */
	private String frontNotifyUrl;
	
	/**
     * 后台通知地址
     */
	private String backNotifyUrl;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getFrontNotifyUrl() {
		return frontNotifyUrl;
	}

	public void setFrontNotifyUrl(String frontNotifyUrl) {
		this.frontNotifyUrl = frontNotifyUrl;
	}

	public String getBackNotifyUrl() {
		return backNotifyUrl;
	}

	public void setBackNotifyUrl(String backNotifyUrl) {
		this.backNotifyUrl = backNotifyUrl;
	}
}
