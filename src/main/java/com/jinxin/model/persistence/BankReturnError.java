package com.jinxin.model.persistence;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 银行返回异常信息
 * </p>
 * <p>
 * Copyright: jinxin (c) 2013 版权
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author 
 * @version V1.0
 * @date 2013-8-5下午06:57:15
 */
@Entity
@Table(name = "CCPS_BANKRETURN_ERROR")
public class BankReturnError implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// Property accessors
	@Id
	@Column(name = "BRE_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_BANKRETURN_ERROR_SEQ")
	@SequenceGenerator(name = "CCPS_BANKRETURN_ERROR_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_BANKRETURN_ERROR_SEQ")
	private Long breId;
	@Column(name = "BRE_TR_NO")
	// 流水订单号
	private String breTrNo;

	// 银行订单号
	@Column(name = "BRE_BANK_NO")
	private String breBankNo;
	// 银行代码
	@Column(name = "BRE_BANK_CODE")
	private String breBankCode;

	// 通道代码
	@Column(name = "BRE_CHA_CODE", precision = 38, scale = 0)
	private Long breChaCode;
	// 收单币种
	@Column(name = "BRE_CURRENCY")
	private String breCurrency;
	// 收单金额
	@Column(name = "BRE_AMOUNT")
	private String breAmount;
	// 银行返回代码
	@Column(name = "BRE_BANKRETURN_CODE")
	private String breBankreturnCode;
	// 银行返回信息
	@Column(name = "BRE_BANKRETURN_INFO")
	private String breBankreturnInfo;
	// 错误代码
	@Column(name = "BRE_ERROR_CODE")
	private String breErrorCode;
	// 错误信息
	@Column(name = "BRE_ERROR_INFO")
	private String breErrorInfo;
	// 记录时间
	@Column(name = "BRE_TIME")
	private String breTime;

	/**
	 * @return the breId
	 */
	public Long getBreId() {
		return breId;
	}

	/**
	 * @param breId
	 *            the breId to set
	 */
	public void setBreId(Long breId) {
		this.breId = breId;
	}

	/**
	 * @return the breTrNo
	 */
	public String getBreTrNo() {
		return breTrNo;
	}

	/**
	 * @param breTrNo
	 *            the breTrNo to set
	 */
	public void setBreTrNo(String breTrNo) {
		this.breTrNo = breTrNo;
	}

	/**
	 * @return the breBankNo
	 */
	public String getBreBankNo() {
		return breBankNo;
	}

	/**
	 * @param breBankNo
	 *            the breBankNo to set
	 */
	public void setBreBankNo(String breBankNo) {
		this.breBankNo = breBankNo;
	}

	/**
	 * @return the breBankCode
	 */
	public String getBreBankCode() {
		return breBankCode;
	}

	/**
	 * @param breBankCode
	 *            the breBankCode to set
	 */
	public void setBreBankCode(String breBankCode) {
		this.breBankCode = breBankCode;
	}

	/**
	 * @return the breChaCode
	 */
	public Long getBreChaCode() {
		return breChaCode;
	}

	/**
	 * @param breChaCode
	 *            the breChaCode to set
	 */
	public void setBreChaCode(Long breChaCode) {
		this.breChaCode = breChaCode;
	}

	/**
	 * @return the breCurrency
	 */
	public String getBreCurrency() {
		return breCurrency;
	}

	/**
	 * @param breCurrency
	 *            the breCurrency to set
	 */
	public void setBreCurrency(String breCurrency) {
		this.breCurrency = breCurrency;
	}

	/**
	 * @return the breAmount
	 */
	public String getBreAmount() {
		return breAmount;
	}

	/**
	 * @param breAmount
	 *            the breAmount to set
	 */
	public void setBreAmount(String breAmount) {
		this.breAmount = breAmount;
	}

	/**
	 * @return the breBankreturnCode
	 */
	public String getBreBankreturnCode() {
		return breBankreturnCode;
	}

	/**
	 * @param breBankreturnCode
	 *            the breBankreturnCode to set
	 */
	public void setBreBankreturnCode(String breBankreturnCode) {
		this.breBankreturnCode = breBankreturnCode;
	}

	/**
	 * @return the breBankreturnInfo
	 */
	public String getBreBankreturnInfo() {
		return breBankreturnInfo;
	}

	/**
	 * @param breBankreturnInfo
	 *            the breBankreturnInfo to set
	 */
	public void setBreBankreturnInfo(String breBankreturnInfo) {
		this.breBankreturnInfo = breBankreturnInfo;
	}

	/**
	 * @return the breErrorCode
	 */
	public String getBreErrorCode() {
		return breErrorCode;
	}

	/**
	 * @param breErrorCode
	 *            the breErrorCode to set
	 */
	public void setBreErrorCode(String breErrorCode) {
		this.breErrorCode = breErrorCode;
	}

	/**
	 * @return the breErrorInfo
	 */
	public String getBreErrorInfo() {
		return breErrorInfo;
	}

	/**
	 * @param breErrorInfo
	 *            the breErrorInfo to set
	 */
	public void setBreErrorInfo(String breErrorInfo) {
		this.breErrorInfo = breErrorInfo;
	}

	/**
	 * @return the breTime
	 */
	public String getBreTime() {
		return breTime;
	}

	/**
	 * @param breTime
	 *            the breTime to set
	 */
	public void setBreTime(String breTime) {
		this.breTime = breTime;
	}

}