package com.jinxin.model.persistence;

// default package
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 交易监控
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version
 * @date
 */
@Entity
@Table(name = "CCPS_TRAN_TRADERECORD")
public class TranTraderecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID

	@Column(name = "TTR_ID", nullable = false, precision = 38, scale = 0)
	private Long ttrId;

	// 流水订单号
	@Id
	@Column(name = "TTR_NO", unique = true, nullable = false)
	private String ttrNo;

	// 交易时间
	@Column(name = "TTR_DATETIME")
	private Date ttrDatetime;

	// 商户号
	@Column(name = "TTR_MER_NO")
	private String ttrMerNo;

	// 网关接入号
	@Column(name = "TTR_GW_NO")
	private String ttrGwNo;

	// 商户订单号
	@Column(name = "TTR_MER_ORDERNO")
	private String ttrMerOrderno;

	// 交易币种
	@Column(name = "TTR_CURRENCY")
	private String ttrCurrency;

	// 交易金额
	@Column(name = "TTR_AMOUNT")
	private String ttrAmount;

	// 交易状态
	@Column(name = "TTR_STATUS")
//	private Long ttrStatus;
	private Integer ttrStatus;

	// 交易扣率
	@Column(name = "TTR_MR_TRADE_RATE")
	private String ttrMrTradeRate;

	// 保证金扣率
	@Column(name = "TTR_MR_RESEVER_RATE")
	private String ttrMrReseverRate;

	// 交易汇率
	@Column(name = "TTR_RATE_VALUE")
	private String ttrRateValue;

	// 收单币种
	@Column(name = "TTR_BW_BANKCURRENCY")
	private String ttrBwBankcurrency;

	// 收单金额
	@Column(name = "TTR_BW_BANKAMOUT")
	private String ttrBwBankamout;

	// 支付银行
	@Column(name = "TTR_BANK_CODE")
	private String ttrBankCode;

	// 支付通道
	@Column(name = "TTR_CHA_CODE")
	private String ttrChaCode;

	// 系统返回时间
	@Column(name = "TTR_BANKDATETIME")
//	private String ttrBankdatetime;
	private Date ttrBankdatetime;

	// 系统返回代码
	@Column(name = "TTR_BANKCODE")
	private String ttrBankcode;

	// 系统返回信息(对内)
	@Column(name = "TTR_BANKINFO_IN")
	private String ttrBankinfoIn;

	// 系统返回信息(对外)
	@Column(name = "TTR_BANKINFO_OUT")
	private String ttrBankinfoOut;
	
	//商户来源网址
	@Column(name = "TTR_WEBSITE")
	private String website;

	// 商户返回地址
	@Column(name = "TTR_RETURNURL")
	private String ttrReturnurl;

	// 提交地址
	@Column(name = "TTR_SUBMITURL")
	private String ttrSubmiturl;

	// CookieID
	@Column(name = "TTR_COOKIEID")
	private String ttrCookieid;

	// 邮件
	@Column(name = "TTR_EMAIL")
	private String ttrEmail;

	// IP地址
	@Column(name = "TTR_IPADDRESS")
	private String ttrIpaddress;

	// IP所在国家
	@Column(name = "TTR_IPCOUNTRY")
	private String ttrIpcountry;

	// 备注
	@Column(name = "TTR_REMARK")
	private String ttrRemark;

	// 交易监控级别
	@Column(name = "TTR_LEVEL")
	private String ttrLevel;
	// Constructors
	
	@Column(name = "TE_ID")
	private int teId;

	/** default constructor */
	public TranTraderecord() {
	}

	/**
	 * @return the ttrId
	 */
	public Long getTtrId() {
		return ttrId;
	}

	/**
	 * @param ttrId the ttrId to set
	 */
	public void setTtrId(Long ttrId) {
		this.ttrId = ttrId;
	}

	/**
	 * @return the ttrNo
	 */
	public String getTtrNo() {
		return ttrNo;
	}

	/**
	 * @param ttrNo the ttrNo to set
	 */
	public void setTtrNo(String ttrNo) {
		this.ttrNo = ttrNo;
	}

	/**
	 * @return the ttrDatetime
	 */
	public Date getTtrDatetime() {
		return ttrDatetime;
	}

	/**
	 * @param ttrDatetime the ttrDatetime to set
	 */
	public void setTtrDatetime(Date ttrDatetime) {
		this.ttrDatetime = ttrDatetime;
	}

	/**
	 * @return the ttrMerNo
	 */
	public String getTtrMerNo() {
		return ttrMerNo;
	}

	/**
	 * @param ttrMerNo the ttrMerNo to set
	 */
	public void setTtrMerNo(String ttrMerNo) {
		this.ttrMerNo = ttrMerNo;
	}

	/**
	 * @return the ttrGwNo
	 */
	public String getTtrGwNo() {
		return ttrGwNo;
	}

	/**
	 * @param ttrGwNo the ttrGwNo to set
	 */
	public void setTtrGwNo(String ttrGwNo) {
		this.ttrGwNo = ttrGwNo;
	}

	/**
	 * @return the ttrMerOrderno
	 */
	public String getTtrMerOrderno() {
		return ttrMerOrderno;
	}

	/**
	 * @param ttrMerOrderno the ttrMerOrderno to set
	 */
	public void setTtrMerOrderno(String ttrMerOrderno) {
		this.ttrMerOrderno = ttrMerOrderno;
	}

	/**
	 * @return the ttrCurrency
	 */
	public String getTtrCurrency() {
		return ttrCurrency;
	}

	/**
	 * @param ttrCurrency the ttrCurrency to set
	 */
	public void setTtrCurrency(String ttrCurrency) {
		this.ttrCurrency = ttrCurrency;
	}

	/**
	 * @return the ttrAmount
	 */
	public String getTtrAmount() {
		return ttrAmount;
	}

	/**
	 * @param ttrAmount the ttrAmount to set
	 */
	public void setTtrAmount(String ttrAmount) {
		this.ttrAmount = ttrAmount;
	}


	/**
	 * @return the ttrMrTradeRate
	 */
	public String getTtrMrTradeRate() {
		return ttrMrTradeRate;
	}

	/**
	 * @param ttrMrTradeRate the ttrMrTradeRate to set
	 */
	public void setTtrMrTradeRate(String ttrMrTradeRate) {
		this.ttrMrTradeRate = ttrMrTradeRate;
	}

	/**
	 * @return the ttrMrReseverRate
	 */
	public String getTtrMrReseverRate() {
		return ttrMrReseverRate;
	}

	/**
	 * @param ttrMrReseverRate the ttrMrReseverRate to set
	 */
	public void setTtrMrReseverRate(String ttrMrReseverRate) {
		this.ttrMrReseverRate = ttrMrReseverRate;
	}

	/**
	 * @return the ttrRateValue
	 */
	public String getTtrRateValue() {
		return ttrRateValue;
	}

	/**
	 * @param ttrRateValue the ttrRateValue to set
	 */
	public void setTtrRateValue(String ttrRateValue) {
		this.ttrRateValue = ttrRateValue;
	}

	/**
	 * @return the ttrBwBankcurrency
	 */
	public String getTtrBwBankcurrency() {
		return ttrBwBankcurrency;
	}

	/**
	 * @param ttrBwBankcurrency the ttrBwBankcurrency to set
	 */
	public void setTtrBwBankcurrency(String ttrBwBankcurrency) {
		this.ttrBwBankcurrency = ttrBwBankcurrency;
	}

	/**
	 * @return the ttrBwBankamout
	 */
	public String getTtrBwBankamout() {
		return ttrBwBankamout;
	}

	/**
	 * @param ttrBwBankamout the ttrBwBankamout to set
	 */
	public void setTtrBwBankamout(String ttrBwBankamout) {
		this.ttrBwBankamout = ttrBwBankamout;
	}

	/**
	 * @return the ttrBankCode
	 */
	public String getTtrBankCode() {
		return ttrBankCode;
	}

	/**
	 * @param ttrBankCode the ttrBankCode to set
	 */
	public void setTtrBankCode(String ttrBankCode) {
		this.ttrBankCode = ttrBankCode;
	}

	/**
	 * @return the ttrChaCode
	 */
	public String getTtrChaCode() {
		return ttrChaCode;
	}

	/**
	 * @param ttrChaCode the ttrChaCode to set
	 */
	public void setTtrChaCode(String ttrChaCode) {
		this.ttrChaCode = ttrChaCode;
	}

	/**
	 * @return the ttrBankcode
	 */
	public String getTtrBankcode() {
		return ttrBankcode;
	}

	/**
	 * @param ttrBankcode the ttrBankcode to set
	 */
	public void setTtrBankcode(String ttrBankcode) {
		this.ttrBankcode = ttrBankcode;
	}

	/**
	 * @return the ttrBankinfoIn
	 */
	public String getTtrBankinfoIn() {
		return ttrBankinfoIn;
	}

	/**
	 * @param ttrBankinfoIn the ttrBankinfoIn to set
	 */
	public void setTtrBankinfoIn(String ttrBankinfoIn) {
		this.ttrBankinfoIn = ttrBankinfoIn;
	}

	/**
	 * @return the ttrBankinfoOut
	 */
	public String getTtrBankinfoOut() {
		return ttrBankinfoOut;
	}

	/**
	 * @param ttrBankinfoOut the ttrBankinfoOut to set
	 */
	public void setTtrBankinfoOut(String ttrBankinfoOut) {
		this.ttrBankinfoOut = ttrBankinfoOut;
	}

	/**
	 * @return the ttrReturnurl
	 */
	public String getTtrReturnurl() {
		return ttrReturnurl;
	}

	/**
	 * @param ttrReturnurl the ttrReturnurl to set
	 */
	public void setTtrReturnurl(String ttrReturnurl) {
		this.ttrReturnurl = ttrReturnurl;
	}

	/**
	 * @return the ttrSubmiturl
	 */
	public String getTtrSubmiturl() {
		return ttrSubmiturl;
	}

	/**
	 * @param ttrSubmiturl the ttrSubmiturl to set
	 */
	public void setTtrSubmiturl(String ttrSubmiturl) {
		this.ttrSubmiturl = ttrSubmiturl;
	}

	/**
	 * @return the ttrCookieid
	 */
	public String getTtrCookieid() {
		return ttrCookieid;
	}

	/**
	 * @param ttrCookieid the ttrCookieid to set
	 */
	public void setTtrCookieid(String ttrCookieid) {
		this.ttrCookieid = ttrCookieid;
	}

	/**
	 * @return the ttrEmail
	 */
	public String getTtrEmail() {
		return ttrEmail;
	}

	/**
	 * @param ttrEmail the ttrEmail to set
	 */
	public void setTtrEmail(String ttrEmail) {
		this.ttrEmail = ttrEmail;
	}

	/**
	 * @return the ttrIpaddress
	 */
	public String getTtrIpaddress() {
		return ttrIpaddress;
	}

	/**
	 * @param ttrIpaddress the ttrIpaddress to set
	 */
	public void setTtrIpaddress(String ttrIpaddress) {
		this.ttrIpaddress = ttrIpaddress;
	}

	/**
	 * @return the ttrIpcountry
	 */
	public String getTtrIpcountry() {
		return ttrIpcountry;
	}

	/**
	 * @param ttrIpcountry the ttrIpcountry to set
	 */
	public void setTtrIpcountry(String ttrIpcountry) {
		this.ttrIpcountry = ttrIpcountry;
	}

	/**
	 * @return the ttrRemark
	 */
	public String getTtrRemark() {
		return ttrRemark;
	}

	/**
	 * @param ttrRemark the ttrRemark to set
	 */
	public void setTtrRemark(String ttrRemark) {
		this.ttrRemark = ttrRemark;
	}


	/**
	 * @return the ttrStatus
	 */
	public Integer getTtrStatus() {
		return ttrStatus;
	}

	/**
	 * @param ttrStatus the ttrStatus to set
	 */
	public void setTtrStatus(Integer ttrStatus) {
		this.ttrStatus = ttrStatus;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	/**
	 * @return the ttrBankdatetime
	 */
	public Date getTtrBankdatetime() {
		return ttrBankdatetime;
	}

	/**
	 * @param ttrBankdatetime the ttrBankdatetime to set
	 */
	public void setTtrBankdatetime(Date ttrBankdatetime) {
		this.ttrBankdatetime = ttrBankdatetime;
	}

	public String getTtrLevel() {
		return ttrLevel;
	}

	public void setTtrLevel(String ttrLevel) {
		this.ttrLevel = ttrLevel;
	}

	public int getTeId() {
		return teId;
	}

	public void setTeId(int teId) {
		this.teId = teId;
	}


	
}