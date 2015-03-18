package com.jinxin.model;


import java.sql.Timestamp;

/**
 * 
 * <p>Title: </p>
 * <p>Description: 交易记录bean:根据银行返回信息获取的交易信息</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-4-29下午12:05:33
 */
public class BankTradeInfoBean implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	
	/** 流水订单号 **/
	private String tradeNo;
	
	/** 商户订单号 **/
	private String merOrderNo;
	
	/** 商户号 **/
	private int tradeMerNo;
	
	/** 商户号邮箱 **/
	private String tradeMerEmail;
	
	/** 网关接入号 **/
	private int tradeGateWayNo;
	
	/** 网关接入号key **/
	private String tradeGateWayKey;
	
	/** 交易币种 **/
	private String tradeCurrency;
	
	/** 交易金额 **/
	private Double tradeAmount;
	
	/** 交易时间 **/
	private Timestamp tradeTime;
	
	/** 提交银行币种 **/
	private String bankCurrency;
	
	/** 提交银行金额 **/
	private Double bankAmount;
	
	/** 交易返回地址 **/
	private String tradeReturnUrl;
	
	/** 交易备注 **/
	private String tradeRemark;
	
	/** 持卡人Email **/
	private String creditEmail;
	
	/** firstName **/
	private String creditFristName;
	
	/** lastName **/
	private String creditLastName;
	
	/** 发送邮件商户 **/
	private int gateWayMailToMer;
	
	/** 发送邮件持卡人 **/
	private int gateWayMailToHolder;

	public String getTradeNo() {
		return tradeNo;
	}

	public void setTradeNo(String tradeNo) {
		this.tradeNo = tradeNo;
	}

	public int getTradeMerNo() {
		return tradeMerNo;
	}

	public void setTradeMerNo(int tradeMerNo) {
		this.tradeMerNo = tradeMerNo;
	}

	public String getTradeMerEmail() {
		return tradeMerEmail;
	}

	public void setTradeMerEmail(String tradeMerEmail) {
		this.tradeMerEmail = tradeMerEmail;
	}

	public int getTradeGateWayNo() {
		return tradeGateWayNo;
	}

	public void setTradeGateWayNo(int tradeGateWayNo) {
		this.tradeGateWayNo = tradeGateWayNo;
	}

	public String getTradeGateWayKey() {
		return tradeGateWayKey;
	}

	public void setTradeGateWayKey(String tradeGateWayKey) {
		this.tradeGateWayKey = tradeGateWayKey;
	}

	public String getTradeCurrency() {
		return tradeCurrency;
	}

	public void setTradeCurrency(String tradeCurrency) {
		this.tradeCurrency = tradeCurrency;
	}

	public Double getTradeAmount() {
		return tradeAmount;
	}

	public void setTradeAmount(Double tradeAmount) {
		this.tradeAmount = tradeAmount;
	}

	public Timestamp getTradeTime() {
		return tradeTime;
	}

	public void setTradeTime(Timestamp tradeTime) {
		this.tradeTime = tradeTime;
	}

	public String getTradeReturnUrl() {
		return tradeReturnUrl;
	}

	public void setTradeReturnUrl(String tradeReturnUrl) {
		this.tradeReturnUrl = tradeReturnUrl;
	}

	public String getTradeRemark() {
		return tradeRemark;
	}

	public void setTradeRemark(String tradeRemark) {
		this.tradeRemark = tradeRemark;
	}

	public String getBankCurrency() {
		return bankCurrency;
	}

	public void setBankCurrency(String bankCurrency) {
		this.bankCurrency = bankCurrency;
	}

	public Double getBankAmount() {
		return bankAmount;
	}

	public void setBankAmount(Double bankAmount) {
		this.bankAmount = bankAmount;
	}

	public String getCreditEmail() {
		return creditEmail;
	}

	public void setCreditEmail(String creditEmail) {
		this.creditEmail = creditEmail;
	}

	public String getCreditFristName() {
		return creditFristName;
	}

	public void setCreditFristName(String creditFristName) {
		this.creditFristName = creditFristName;
	}

	public String getCreditLastName() {
		return creditLastName;
	}

	public void setCreditLastName(String creditLastName) {
		this.creditLastName = creditLastName;
	}

	public int getGateWayMailToMer() {
		return gateWayMailToMer;
	}

	public void setGateWayMailToMer(int gateWayMailToMer) {
		this.gateWayMailToMer = gateWayMailToMer;
	}

	public int getGateWayMailToHolder() {
		return gateWayMailToHolder;
	}

	public void setGateWayMailToHolder(int gateWayMailToHolder) {
		this.gateWayMailToHolder = gateWayMailToHolder;
	}

	public String getMerOrderNo() {
		return merOrderNo;
	}

	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}
}
