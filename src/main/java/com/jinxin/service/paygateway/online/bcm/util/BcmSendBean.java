package com.jinxin.service.paygateway.online.bcm.util;

import com.jinxin.model.online.OlineSendDataBean;

/**
 * @className:AcnSendBean.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午4:12:18
 */

public class BcmSendBean extends OlineSendDataBean{
	
	private String tranType;
	
	private String intefaceVersion;
	
	private String curType;
	
	private String notifyType;
	
	private String merURL;
	
	private String goodsURL;
	
	private String orderDate;
	
	private String orderContent;
	
	private String orderMono;
	
	private String phdFlag;
	
	private String jumpSeconds;
	
	private String proxyMerName;
	
	private String proxyMerType;
	
	private String proxyMerCredentials;
	
	private String netType;
	
	public String getOrderContent() {
		return orderContent;
	}

	public void setOrderContent(String orderContent) {
		this.orderContent = orderContent;
	}

	public String getOrderMono() {
		return orderMono;
	}

	public void setOrderMono(String orderMono) {
		this.orderMono = orderMono;
	}

	public String getPhdFlag() {
		return phdFlag;
	}

	public void setPhdFlag(String phdFlag) {
		this.phdFlag = phdFlag;
	}

	public String getJumpSeconds() {
		return jumpSeconds;
	}

	public void setJumpSeconds(String jumpSeconds) {
		this.jumpSeconds = jumpSeconds;
	}

	public String getProxyMerName() {
		return proxyMerName;
	}

	public void setProxyMerName(String proxyMerName) {
		this.proxyMerName = proxyMerName;
	}

	public String getProxyMerType() {
		return proxyMerType;
	}

	public void setProxyMerType(String proxyMerType) {
		this.proxyMerType = proxyMerType;
	}

	public String getProxyMerCredentials() {
		return proxyMerCredentials;
	}

	public void setProxyMerCredentials(String proxyMerCredentials) {
		this.proxyMerCredentials = proxyMerCredentials;
	}

	public String getNetType() {
		return netType;
	}

	public void setNetType(String netType) {
		this.netType = netType;
	}

	public String getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(String orderDate) {
		this.orderDate = orderDate;
	}

	private String orderTime;
	
	private String payBatchNo;

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public String getIntefaceVersion() {
		return intefaceVersion;
	}

	public void setIntefaceVersion(String intefaceVersion) {
		this.intefaceVersion = intefaceVersion;
	}

	public String getCurType() {
		return curType;
	}

	public void setCurType(String curType) {
		this.curType = curType;
	}

	public String getNotifyType() {
		return notifyType;
	}

	public void setNotifyType(String notifyType) {
		this.notifyType = notifyType;
	}

	public String getMerURL() {
		return merURL;
	}

	public void setMerURL(String merURL) {
		this.merURL = merURL;
	}

	public String getGoodsURL() {
		return goodsURL;
	}

	public void setGoodsURL(String goodsURL) {
		this.goodsURL = goodsURL;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

	public String getPayBatchNo() {
		return payBatchNo;
	}

	public void setPayBatchNo(String payBatchNo) {
		this.payBatchNo = payBatchNo;
	}
	
}
