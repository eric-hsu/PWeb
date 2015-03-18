package com.jinxin.model;

/**
 * @className:Card.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-23 下午4:53:31
 */

public class Card {
	
	/** 卡类型编码（基础数据在CDM系统维护） */
    private String cardTypeCode;
    
    /** 银行编码/网关编码（基础数据在CDM系统维护） */
    private String bankCode;
    
	 /** 账户名称 */
    private String accountName;
    
    /** 账号 */
    private String accountNo;

    /** 户主/持卡人证件号 */
    private String certificateNo;

    /** 户主/持卡人证件类型（基础数据在CDM系统维护） */
    private String certificateTypeCode;

    /** 账户有效期 */
    private String accountPeriod;

    /** 银行卡后4位*/
    private String bankCardNoFour;

    /** 安全码 */
    private String securityCode;
    
    /** 电话号码*/
    private String telephone;

	public String getAccountName() {
		return this.accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getCertificateNo() {
		return this.certificateNo;
	}

	public void setCertificateNo(String certificateNo) {
		this.certificateNo = certificateNo;
	}

	public String getCertificateTypeCode() {
		return this.certificateTypeCode;
	}

	public void setCertificateTypeCode(String certificateTypeCode) {
		this.certificateTypeCode = certificateTypeCode;
	}

	public String getAccountPeriod() {
		return this.accountPeriod;
	}

	public void setAccountPeriod(String accountPeriod) {
		this.accountPeriod = accountPeriod;
	}

	public String getAccountNo() {
		return this.accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankCardNoFour() {
		return this.bankCardNoFour;
	}

	public void setBankCardNoFour(String bankCardNoFour) {
		this.bankCardNoFour = bankCardNoFour;
	}

	public String getBankCode() {
		return this.bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getCardTypeCode() {
		return this.cardTypeCode;
	}

	public void setCardTypeCode(String cardTypeCode) {
		this.cardTypeCode = cardTypeCode;
	}

	public String getSecurityCode() {
		return this.securityCode;
	}

	public void setSecurityCode(String securityCode) {
		this.securityCode = securityCode;
	}

	public String getTelephone() {
		return this.telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

}
