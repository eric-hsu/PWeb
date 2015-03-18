package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
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
 * ChannelReport entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CHANNEL_REPORT")
public class ChannelReport implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "BR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CHANNEL_REPORT_SEQ")
	@SequenceGenerator(name = "CCPS_CHANNEL_REPORT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CHANNEL_REPORT_SEQ")
	private Long brId;
	// 银行代码
	@Column(name = "BR_BANK_CODE", nullable = false)
	private String brBankCode;
	// 通道代码
	@Column(name = "BR_CHA_CODE", nullable = false, precision = 38, scale = 0)
	private Long brChaCode;
	// 币种
	@Column(name = "BR_BANK_CURRENCY", nullable = false)
	private String brBankCurrency;
	// 成功数
	@Column(name = "BR_SUCCESS_COUNT", nullable = false, precision = 38, scale = 0)
	private int brSuccessCount;
	// 总交易数
	@Column(name = "BR_TOTAL_COUNT", nullable = false, precision = 38, scale = 0)
	private int brTotalCount;
	// 总成功交易金额
	@Column(name = "BR_TOTAL_AMOUNT", nullable = false, precision = 18)
	private BigDecimal brTotalAmount;
	// 交易日期
	@Column(name = "BR_DATE")
	private Date brDate;
	//成功率统计
    @Transient
	private double succussRate;
    
	public double getSuccussRate() {
		return succussRate;
	}

	public void setSuccussRate(double succussRate) {
		this.succussRate = succussRate;
	}

	/**
	 * @return the brId
	 */
	public Long getBrId() {
		return brId;
	}

	/**
	 * @param brId
	 *            the brId to set
	 */
	public void setBrId(Long brId) {
		this.brId = brId;
	}

	/**
	 * @return the brBankCode
	 */
	public String getBrBankCode() {
		return brBankCode;
	}

	/**
	 * @param brBankCode
	 *            the brBankCode to set
	 */
	public void setBrBankCode(String brBankCode) {
		this.brBankCode = brBankCode;
	}

	/**
	 * @return the brChaCode
	 */
	public Long getBrChaCode() {
		return brChaCode;
	}

	/**
	 * @param brChaCode
	 *            the brChaCode to set
	 */
	public void setBrChaCode(Long brChaCode) {
		this.brChaCode = brChaCode;
	}

	/**
	 * @return the brBankCurrency
	 */
	public String getBrBankCurrency() {
		return brBankCurrency;
	}

	/**
	 * @param brBankCurrency
	 *            the brBankCurrency to set
	 */
	public void setBrBankCurrency(String brBankCurrency) {
		this.brBankCurrency = brBankCurrency;
	}

	/**
	 * @return the brSuccessCount
	 */
	public int getBrSuccessCount() {
		return brSuccessCount;
	}

	/**
	 * @param brSuccessCount
	 *            the brSuccessCount to set
	 */
	public void setBrSuccessCount(int brSuccessCount) {
		this.brSuccessCount = brSuccessCount;
	}

	/**
	 * @return the brTotalCount
	 */
	public int getBrTotalCount() {
		return brTotalCount;
	}

	/**
	 * @param brTotalCount
	 *            the brTotalCount to set
	 */
	public void setBrTotalCount(int brTotalCount) {
		this.brTotalCount = brTotalCount;
	}

	/**
	 * @return the brTotalAmount
	 */
	public BigDecimal getBrTotalAmount() {
		return brTotalAmount;
	}

	/**
	 * @param brTotalAmount
	 *            the brTotalAmount to set
	 */
	public void setBrTotalAmount(BigDecimal brTotalAmount) {
		this.brTotalAmount = brTotalAmount;
	}

	/**
	 * @return the brDate
	 */
	public Date getBrDate() {
		return brDate;
	}

	/**
	 * @param brDate
	 *            the brDate to set
	 */
	public void setBrDate(Date brDate) {
		this.brDate = brDate;
	}

}