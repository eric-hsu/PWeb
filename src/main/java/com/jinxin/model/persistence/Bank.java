package com.jinxin.model.persistence;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Bank entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_BANK")
public class Bank implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 自增(1,1);
	
	@Column(name = "BANK_ID", unique = true, nullable = false, precision = 38, scale = 0)
	private Long bankId;
	
	// 银行代码
	@Id
	@Column(name = "BANK_CODE", nullable = false)
	private String bankCode;
	// 银行名称
	@Column(name = "BANK_NAME", nullable = false)
	private String bankName;
	// 银行支付URL
	@Column(name = "BANK_PAY_URL")
	private String bankPayUrl;
	// 银行自动勾兑URL
	@Column(name = "BANK_CHECK_URL")
	private String bankCheckUrl;
	// 银行后台管理网址
	@Column(name = "BANK_WEBSITE")
	private String bankWebsite;
	// 本系统支付通道的提交地址
	@Column(name = "BANK_REQ_URL", nullable = false)
	private String bankReqUrl;
	// 本系统支付通道的接收地址
	@Column(name = "BANK_RES_URL")
	private String bankResUrl;
	// 调用方法名

	@Column(name = "BANK_METHOD_NAME")
	private String bankMethodName;
	// 添加人
	@Column(name = "BANK_LOGIN_NAME", nullable = false)
	private String bankLoginName;
	// 添加时间
	@Column(name = "BANK_OPRTIME", nullable = false)
	private Date bankOprtime;

	// 是否直连:1是,0否
	@Column(name = "BANK_ISDIRECT", nullable = false)
	private Integer bankIsdirect;
	// 备注
	@Column(name = "BANK_REMARK")
	private String bankRemark;
	// 通道表
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "bank")
	private Set<Channel> channels = new HashSet<Channel>(0);

	public Bank() {
	}

	public Bank(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the bankId
	 */
	public Long getBankId() {
		return bankId;
	}

	/**
	 * @param bankId
	 *            the bankId to set
	 */
	public void setBankId(Long bankId) {
		this.bankId = bankId;
	}

	/**
	 * @return the bankCode
	 */
	public String getBankCode() {
		return bankCode;
	}

	/**
	 * @param bankCode
	 *            the bankCode to set
	 */
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	/**
	 * @return the bankName
	 */
	public String getBankName() {
		return bankName;
	}

	/**
	 * @param bankName
	 *            the bankName to set
	 */
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	/**
	 * @return the bankPayUrl
	 */
	public String getBankPayUrl() {
		return bankPayUrl;
	}

	/**
	 * @param bankPayUrl
	 *            the bankPayUrl to set
	 */
	public void setBankPayUrl(String bankPayUrl) {
		this.bankPayUrl = bankPayUrl;
	}

	/**
	 * @return the bankCheckUrl
	 */
	public String getBankCheckUrl() {
		return bankCheckUrl;
	}

	/**
	 * @param bankCheckUrl
	 *            the bankCheckUrl to set
	 */
	public void setBankCheckUrl(String bankCheckUrl) {
		this.bankCheckUrl = bankCheckUrl;
	}

	/**
	 * @return the bankWebsite
	 */
	public String getBankWebsite() {
		return bankWebsite;
	}

	/**
	 * @param bankWebsite
	 *            the bankWebsite to set
	 */
	public void setBankWebsite(String bankWebsite) {
		this.bankWebsite = bankWebsite;
	}

	/**
	 * @return the bankReqUrl
	 */
	public String getBankReqUrl() {
		return bankReqUrl;
	}

	/**
	 * @param bankReqUrl
	 *            the bankReqUrl to set
	 */
	public void setBankReqUrl(String bankReqUrl) {
		this.bankReqUrl = bankReqUrl;
	}

	/**
	 * @return the bankResUrl
	 */
	public String getBankResUrl() {
		return bankResUrl;
	}

	/**
	 * @param bankResUrl
	 *            the bankResUrl to set
	 */
	public void setBankResUrl(String bankResUrl) {
		this.bankResUrl = bankResUrl;
	}

	/**
	 * @return the bankMethodName
	 */
	public String getBankMethodName() {
		return bankMethodName;
	}

	/**
	 * @param bankMethodName
	 *            the bankMethodName to set
	 */
	public void setBankMethodName(String bankMethodName) {
		this.bankMethodName = bankMethodName;
	}

	/**
	 * @return the bankLoginName
	 */
	public String getBankLoginName() {
		return bankLoginName;
	}

	/**
	 * @param bankLoginName
	 *            the bankLoginName to set
	 */
	public void setBankLoginName(String bankLoginName) {
		this.bankLoginName = bankLoginName;
	}

	/**
	 * @return the bankOprtime
	 */
	public Date getBankOprtime() {
		return bankOprtime;
	}

	/**
	 * @param bankOprtime
	 *            the bankOprtime to set
	 */
	public void setBankOprtime(Date bankOprtime) {
		this.bankOprtime = bankOprtime;
	}

	/**
	 * @return the bankRemark
	 */
	public String getBankRemark() {
		return bankRemark;
	}

	/**
	 * @param bankRemark
	 *            the bankRemark to set
	 */
	public void setBankRemark(String bankRemark) {
		this.bankRemark = bankRemark;
	}

	public Integer getBankIsdirect() {
		return bankIsdirect;
	}

	public void setBankIsdirect(Integer bankIsdirect) {
		this.bankIsdirect = bankIsdirect;
	}

	/**
	 * @return the channels
	 */
	public Set<Channel> getChannels() {
		return channels;
	}

	/**
	 * @param channels
	 *            the channels to set
	 */
	public void setChannels(Set<Channel> channels) {
		this.channels = channels;
	}



}