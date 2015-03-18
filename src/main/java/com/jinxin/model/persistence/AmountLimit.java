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

/**
 * AmountLimit entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_AMOUNT_LIMIT")
public class AmountLimit implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "AL_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AMOUNT_LIMIT_SEQ")
	@SequenceGenerator(name = "CCPS_AMOUNT_LIMIT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AMOUNT_LIMIT_SEQ")
	private Long alId;
	// 商户号
	@Column(name = "AL_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long alMerNo;
	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "AL_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long alGwNo;
	// 卡种 1.visa 2 master
	@Column(name = "AL_CARD_TYPE", nullable = false, precision = 38, scale = 0)
	private Long alCardType;
	// 币种
	@Column(name = "AL_CURRENCY", nullable = false)
	private String alCurrency;
	// 每笔限定金额
	@Column(name = "AL_SINGLE_AMOUNT", precision = 18)
	private BigDecimal alSingleAmount;
	// 每日限定金额
	@Column(name = "AL_DAY_AMOUNT", precision = 18)
	private BigDecimal alDayAmount;
	// 每周限定金额
	@Column(name = "AL_WEEK_AMOUNT", precision = 18)
	private BigDecimal alWeekAmount;
	// 每月限定金额
	@Column(name = "AL_MONTH_AMOUNT", precision = 18)
	private BigDecimal alMonthAmount;
	// 添加人
	@Column(name = "AL_LOGIN_NAME", nullable = false)
	private String alLoginName;
	// 添加时间
	@Column(name = "AL_OPRTIME", nullable = false)
	private Date alOprtime;
	// 备注
	@Column(name = "AL_REMARK")
	private String alRemark;

	/**
	 * @return the alId
	 */
	public Long getAlId() {
		return alId;
	}

	/**
	 * @param alId
	 *            the alId to set
	 */
	public void setAlId(Long alId) {
		this.alId = alId;
	}

	/**
	 * @return the alMerNo
	 */
	public Long getAlMerNo() {
		return alMerNo;
	}

	/**
	 * @param alMerNo
	 *            the alMerNo to set
	 */
	public void setAlMerNo(Long alMerNo) {
		this.alMerNo = alMerNo;
	}

	/**
	 * @return the alGwNo
	 */
	public Long getAlGwNo() {
		return alGwNo;
	}

	/**
	 * @param alGwNo
	 *            the alGwNo to set
	 */
	public void setAlGwNo(Long alGwNo) {
		this.alGwNo = alGwNo;
	}

	/**
	 * @return the alCardType
	 */
	public Long getAlCardType() {
		return alCardType;
	}

	/**
	 * @param alCardType
	 *            the alCardType to set
	 */
	public void setAlCardType(Long alCardType) {
		this.alCardType = alCardType;
	}

	/**
	 * @return the alCurrency
	 */
	public String getAlCurrency() {
		return alCurrency;
	}

	/**
	 * @param alCurrency
	 *            the alCurrency to set
	 */
	public void setAlCurrency(String alCurrency) {
		this.alCurrency = alCurrency;
	}

	/**
	 * @return the alSingleAmount
	 */
	public BigDecimal getAlSingleAmount() {
		return alSingleAmount;
	}

	/**
	 * @param alSingleAmount
	 *            the alSingleAmount to set
	 */
	public void setAlSingleAmount(BigDecimal alSingleAmount) {
		this.alSingleAmount = alSingleAmount;
	}

	/**
	 * @return the alDayAmount
	 */
	public BigDecimal getAlDayAmount() {
		return alDayAmount;
	}

	/**
	 * @param alDayAmount
	 *            the alDayAmount to set
	 */
	public void setAlDayAmount(BigDecimal alDayAmount) {
		this.alDayAmount = alDayAmount;
	}

	/**
	 * @return the alWeekAmount
	 */
	public BigDecimal getAlWeekAmount() {
		return alWeekAmount;
	}

	/**
	 * @param alWeekAmount
	 *            the alWeekAmount to set
	 */
	public void setAlWeekAmount(BigDecimal alWeekAmount) {
		this.alWeekAmount = alWeekAmount;
	}

	/**
	 * @return the alMonthAmount
	 */
	public BigDecimal getAlMonthAmount() {
		return alMonthAmount;
	}

	/**
	 * @param alMonthAmount
	 *            the alMonthAmount to set
	 */
	public void setAlMonthAmount(BigDecimal alMonthAmount) {
		this.alMonthAmount = alMonthAmount;
	}

	/**
	 * @return the alLoginName
	 */
	public String getAlLoginName() {
		return alLoginName;
	}

	/**
	 * @param alLoginName
	 *            the alLoginName to set
	 */
	public void setAlLoginName(String alLoginName) {
		this.alLoginName = alLoginName;
	}

	/**
	 * @return the alOprtime
	 */
	public Date getAlOprtime() {
		return alOprtime;
	}

	/**
	 * @param alOprtime
	 *            the alOprtime to set
	 */
	public void setAlOprtime(Date alOprtime) {
		this.alOprtime = alOprtime;
	}

	/**
	 * @return the alRemark
	 */
	public String getAlRemark() {
		return alRemark;
	}

	/**
	 * @param alRemark
	 *            the alRemark to set
	 */
	public void setAlRemark(String alRemark) {
		this.alRemark = alRemark;
	}

}