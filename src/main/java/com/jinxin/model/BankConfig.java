package com.jinxin.model;

import java.util.List;

public class BankConfig {
	private String mcMerNo;
	private String mcGwNo;
	private String bankname;
	private String bankcode;
	private String chaCode;
	private String chaName;
	private String chaType;
	private String cardSupportType;
	
	
	public String getMcMerNo() {
		return mcMerNo;
	}
	public void setMcMerNo(String mcMerNo) {
		this.mcMerNo = mcMerNo;
	}
	public String getMcGwNo() {
		return mcGwNo;
	}
	public void setMcGwNo(String mcGwNo) {
		this.mcGwNo = mcGwNo;
	}

	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getChaCode() {
		return chaCode;
	}
	public void setChaCode(String chaCode) {
		this.chaCode = chaCode;
	}
	public String getChaName() {
		return chaName;
	}
	public void setChaName(String chaName) {
		this.chaName = chaName;
	}
	
	public String getChaType() {
		return chaType;
	}
	public void setChaType(String chaType) {
		this.chaType = chaType;
	}
	public String getCardSupportType() {
		return cardSupportType;
	}
	public void setCardSupportType(String cardSupportType) {
		this.cardSupportType = cardSupportType;
	}

}
