package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * UnnormalOperate entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_UNNORMAL_OPERATE")
public class UnnormalOperate implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@Column(name = "UO_OD", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_UNNORMAL_OPERATE_SEQ")
	@SequenceGenerator(name = "CCPS_UNNORMAL_OPERATE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_UNNORMAL_OPERATE_SEQ")
	private Long uoOd;
	//流水订单号;
	@ManyToOne
	@JoinColumn(name = "UO_TR_NO", nullable=false)
	private Traderecord traderecord;
	
	//操作类型  1 伪冒 2.其它';
	@Column(name = "UO_TYPE", precision = 38, scale = 0)
	private Long uoType;
	
	// 异常币种 与交易币种相同
	@Column(name = "UO_CURRENCY", nullable = false)
	private String uoCurrency;

	// 异常金额;
	@Column(name = "UO_AMOUNT", nullable = false, precision = 18)
	private BigDecimal uoAmount;
	//异常原因
	@Column(name = "UO_REASON")
	private int uoReason;
	
	//异常时间
	@Column(name = "UO_TIME")
	private Date uoTime;
	/**
	 * @return the uoTime
	 */
	public Date getUoTime() {
		return uoTime;
	}
	/**
	 * @param uoTime the uoTime to set
	 */
	public void setUoTime(Date uoTime) {
		this.uoTime = uoTime;
	}
	//操作人
	@Column(name = "UO_LOGIN_NAME")
	private String uoLoginName;
	
	//操作时间
	@Column(name = "UO_OPER_DATE")
	private Date uoOperDate;
	//备注
	@Column(name = "UO_REMARK")
	private String uoRemark;
	/**
	 * @return the uoOd
	 */
	public Long getUoOd() {
		return uoOd;
	}
	/**
	 * @return the uoReason
	 */
	public int getUoReason() {
		return uoReason;
	}
	/**
	 * @param uoReason the uoReason to set
	 */
	public void setUoReason(int uoReason) {
		this.uoReason = uoReason;
	}
	/**
	 * @param uoOd the uoOd to set
	 */
	public void setUoOd(Long uoOd) {
		this.uoOd = uoOd;
	}
	/**
	 * @return the traderecord
	 */
	public Traderecord getTraderecord() {
		return traderecord;
	}
	/**
	 * @param traderecord the traderecord to set
	 */
	public void setTraderecord(Traderecord traderecord) {
		this.traderecord = traderecord;
	}
	/**
	 * @return the uoType
	 */
	public Long getUoType() {
		return uoType;
	}
	/**
	 * @param uoType the uoType to set
	 */
	public void setUoType(Long uoType) {
		this.uoType = uoType;
	}
	/**
	 * @return the uoLoginName
	 */
	public String getUoLoginName() {
		return uoLoginName;
	}
	/**
	 * @param uoLoginName the uoLoginName to set
	 */
	public void setUoLoginName(String uoLoginName) {
		this.uoLoginName = uoLoginName;
	}
	/**
	 * @return the uoOperDate
	 */
	public Date getUoOperDate() {
		return uoOperDate;
	}
	/**
	 * @param uoOperDate the uoOperDate to set
	 */
	public void setUoOperDate(Date uoOperDate) {
		this.uoOperDate = uoOperDate;
	}
	/**
	 * @return the uoRemark
	 */
	public String getUoRemark() {
		return uoRemark;
	}
	/**
	 * @param uoRemark the uoRemark to set
	 */
	public void setUoRemark(String uoRemark) {
		this.uoRemark = uoRemark;
	}
	/**
	 * @return the uoCurrency
	 */
	public String getUoCurrency() {
		return uoCurrency;
	}
	/**
	 * @param uoCurrency the uoCurrency to set
	 */
	public void setUoCurrency(String uoCurrency) {
		this.uoCurrency = uoCurrency;
	}
	/**
	 * @return the uoAmount
	 */
	public BigDecimal getUoAmount() {
		return uoAmount;
	}
	/**
	 * @param uoAmount the uoAmount to set
	 */
	public void setUoAmount(BigDecimal uoAmount) {
		this.uoAmount = uoAmount;
	}
	

}