package com.jinxin.model.persistence;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p> Title: </p>
 * <p>Description: UnNormalTraderecord映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_UNNORMAL_TRADERECORD")
public class UnNormalTraderecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// utrId 自增(1,1);
	@Column(name = "UTR_ID", nullable = false, precision = 38, scale = 0)
	private Long utrId;

	// 流水订单号
	@Id
	@Column(name = "UTR_NO", unique = true, nullable = false)
	private String utrNo;

	// 商户号
	@Column(name = "UTR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long utrMerNo;

	// 网关接入号
	@Column(name = "UTR_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long utrGwNo;

	// 商户订单号
	@Column(name = "UTR_MER_ORDERNO")
	private String utrMerOrderno;

	// 交易币种
	@Column(name = "UTR_CURRENCY")
	private String utrCurrency;

	// 交易金额
	@Column(name = "UTR_AMOUNT")
//	private BigDecimal utrAmount;
	private String utrAmount;
	// 商户支付银行
	@Column(name = "UTR_BANK_CODE")
	private String utrBankCode;

	// 商户支付通道
	@Column(name = "UTR_CHA_CODE")
	private String utrChaCode;

	// 商户返回地址
	@Column(name = "UTR_RETURNURL")
	private String utrReturnurl;

	// 商户提交来源网址
	@Column(name = "UTR_WEBSITE")
	private String utrWebsite;

	// 提交地址
	@Column(name = "UTR_SUBMITURL")
	private String utrSubmiturl;

	// 指调用网关接口支付的时间(即插入异常信息表胡时间);
	@Column(name = "UTR_PAYSTARTTIME", nullable = false)
	private Date utrPaystarttime;

	// 指持卡人进入信用卡信息填写的时间
	@Column(name = "UTR_PAYTIME")
	private Date utrPaytime;

	// 支付结束时间
	@Column(name = "UTR_PAYENDTIME")
	private Date utrPayendtime;

	// 错误代码
	@Column(name = "UTR_ERRORCODE")
	private String utrErrorcode;

	// 错误原因,指对外显示的错误信息
	@Column(name = "UTR_ERRORREASON_OUT")
	private String utrErrorreasonOut;

	// 错误原因,指对内显示的错误信息
	@Column(name = "UTR_ERRORREASON_IN")
	private String utrErrorreasonIn;

	// 商户传过来的备注信息
	@Column(name = "UTR_MER_REMARK")
	private String utrMerRemark;

	//风控元素
	@Transient
	private String riskElement;
	
	public Long getUtrId() {
		return utrId;
	}

	public void setUtrId(Long utrId) {
		this.utrId = utrId;
	}

	public String getUtrNo() {
		return utrNo;
	}

	public void setUtrNo(String utrNo) {
		this.utrNo = utrNo;
	}

	public Long getUtrMerNo() {
		return utrMerNo;
	}

	public void setUtrMerNo(Long utrMerNo) {
		this.utrMerNo = utrMerNo;
	}

	public Long getUtrGwNo() {
		return utrGwNo;
	}

	public void setUtrGwNo(Long utrGwNo) {
		this.utrGwNo = utrGwNo;
	}

	public String getUtrMerOrderno() {
		return utrMerOrderno;
	}

	public void setUtrMerOrderno(String utrMerOrderno) {
		this.utrMerOrderno = utrMerOrderno;
	}

	public String getUtrCurrency() {
		return utrCurrency;
	}

	public void setUtrCurrency(String utrCurrency) {
		this.utrCurrency = utrCurrency;
	}

	public String getUtrBankCode() {
		return utrBankCode;
	}

	public void setUtrBankCode(String utrBankCode) {
		this.utrBankCode = utrBankCode;
	}

	public String getUtrChaCode() {
		return utrChaCode;
	}

	public void setUtrChaCode(String utrChaCode) {
		this.utrChaCode = utrChaCode;
	}

	public String getUtrReturnurl() {
		return utrReturnurl;
	}

	public void setUtrReturnurl(String utrReturnurl) {
		this.utrReturnurl = utrReturnurl;
	}

	public String getUtrWebsite() {
		return utrWebsite;
	}

	public void setUtrWebsite(String utrWebsite) {
		this.utrWebsite = utrWebsite;
	}

	public String getUtrSubmiturl() {
		return utrSubmiturl;
	}

	public void setUtrSubmiturl(String utrSubmiturl) {
		this.utrSubmiturl = utrSubmiturl;
	}

	public Date getUtrPaystarttime() {
		return utrPaystarttime;
	}

	public void setUtrPaystarttime(Date utrPaystarttime) {
		this.utrPaystarttime = utrPaystarttime;
	}

	public Date getUtrPaytime() {
		return utrPaytime;
	}

	public void setUtrPaytime(Date utrPaytime) {
		this.utrPaytime = utrPaytime;
	}

	public Date getUtrPayendtime() {
		return utrPayendtime;
	}

	public void setUtrPayendtime(Date utrPayendtime) {
		this.utrPayendtime = utrPayendtime;
	}

	public String getUtrErrorcode() {
		return utrErrorcode;
	}

	public void setUtrErrorcode(String utrErrorcode) {
		this.utrErrorcode = utrErrorcode;
	}

	public String getUtrErrorreasonOut() {
		return utrErrorreasonOut;
	}

	public void setUtrErrorreasonOut(String utrErrorreasonOut) {
		this.utrErrorreasonOut = utrErrorreasonOut;
	}

	public String getUtrErrorreasonIn() {
		return utrErrorreasonIn;
	}

	public void setUtrErrorreasonIn(String utrErrorreasonIn) {
		this.utrErrorreasonIn = utrErrorreasonIn;
	}

	public String getUtrMerRemark() {
		return utrMerRemark;
	}

	public void setUtrMerRemark(String utrMerRemark) {
		this.utrMerRemark = utrMerRemark;
	}	
	/**
	 * @param utrAmount the utrAmount to set
	 */
	public void setUtrAmount(String utrAmount) {
		this.utrAmount = utrAmount;
	}

	/**
	 * @return the utrAmount
	 */
	public String getUtrAmount() {
		return utrAmount;
	}

	public String getRiskElement() {
		return riskElement;
	}

	public void setRiskElement(String riskElement) {
		this.riskElement = riskElement;
	}
	
}