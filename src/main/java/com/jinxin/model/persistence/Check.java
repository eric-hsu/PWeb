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
 * Check entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CHECK")
public class Check implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "CHE_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CHECK_SEQ")
	@SequenceGenerator(name = "CCPS_CHECK_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CHECK_SEQ")
	private Long cheId;
	
	// 流水订单号
	@Column(name = "CHE_TR_NO", nullable = false)
	private String cheTradeNo;
	
	//收单币种
	@Column(name = "CHA_TRADECURRECNY",nullable = false)
	private String cheTradeCurrecny;
	//收单金额
	@Column(name = "CHE_TRADEAMOUNT",nullable = false)
	private BigDecimal cheTradeAmount;
	//交易时间
	@Column(name = "CHE_TRADETIME",nullable = false)
	private Date cheTradeTime;
	//银行代码
	@Column(name = "CHE_BANK_CODE",nullable = false)
	private String	cheBankCode;	
	//通道代码
	@Column(name = "CHE_CHA_CODE",nullable = false)
	private Integer	cheCHACode;	
	//交易状态
	@Column(name = "CHE_TRADESTATUS",nullable = false)
	private Integer	cheTradeStatus;	
	// 申请人账号(添加人)
	@Column(name = "CHE_LOG_NAME", nullable = false)
	private String cheLogName;
	// 申请时间(添加时间)
	@Column(name = "CHE_OPRTIME", nullable = false)
	private Date cheOprtime;
	// 审核状态 -1:失败 0:待审核 1:审核成功未处理 2:审核成功已处理
	@Column(name = "CHE_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer cheStatus;
	// 审核人账号
	@Column(name = "CHE_ACCOUNT")
	private String cheAccount;
	// 审核时间
	@Column(name = "CHE_CHECK_OPRTIME")
	private Date cheCheckOprtime;
	//处理人
	@Column(name = "CHE_EXEACCOUNT")
	private String cheEXEAcount;	
	//处理时间
	@Column(name = "CHE_EXETIME")	
	private Date cheEXETime;
	// 备注
	@Column(name = "CHE_REMARK")
	private String cheRemark;

	/**
	 * @return the cheId
	 */
	public Long getCheId() {
		return cheId;
	}

	/**
	 * @param cheId
	 *            the cheId to set
	 */
	public void setCheId(Long cheId) {
		this.cheId = cheId;
	}

//	/**
//	 * @return the traderecord
//	 */
//	public Traderecord getTraderecord() {
//		return traderecord;
//	}
//
//	/**
//	 * @param traderecord
//	 *            the traderecord to set
//	 */
//	public void setTraderecord(Traderecord traderecord) {
//		this.traderecord = traderecord;
//	}

	/**
	 * @return the cheLogName
	 */
	public String getCheLogName() {
		return cheLogName;
	}

	/**
	 * @param cheLogName
	 *            the cheLogName to set
	 */
	public void setCheLogName(String cheLogName) {
		this.cheLogName = cheLogName;
	}

	/**
	 * @return the cheOprtime
	 */
	public Date getCheOprtime() {
		return cheOprtime;
	}

	/**
	 * @param cheOprtime
	 *            the cheOprtime to set
	 */
	public void setCheOprtime(Date cheOprtime) {
		this.cheOprtime = cheOprtime;
	}

	/**
	 * @return the cheAccount
	 */
	public String getCheAccount() {
		return cheAccount;
	}

	/**
	 * @param cheAccount
	 *            the cheAccount to set
	 */
	public void setCheAccount(String cheAccount) {
		this.cheAccount = cheAccount;
	}

	/**
	 * @return the cheCheckOprtime
	 */
	public Date getCheCheckOprtime() {
		return cheCheckOprtime;
	}

	/**
	 * @param cheCheckOprtime
	 *            the cheCheckOprtime to set
	 */
	public void setCheCheckOprtime(Date cheCheckOprtime) {
		this.cheCheckOprtime = cheCheckOprtime;
	}

	/**
	 * @return the cheRemark
	 */
	public String getCheRemark() {
		return cheRemark;
	}

	/**
	 * @param cheRemark
	 *            the cheRemark to set
	 */
	public void setCheRemark(String cheRemark) {
		this.cheRemark = cheRemark;
	}
	/**
	 * @return the cheStatus
	 */
	public Integer getCheStatus() {
		return cheStatus;
	}

	/**
	 * @param cheStatus the cheStatus to set
	 */
	public void setCheStatus(Integer cheStatus) {
		this.cheStatus = cheStatus;
	}
	/**
	 * @return the cheTradeCurrecny
	 */
	public String getCheTradeCurrecny() {
		return cheTradeCurrecny;
	}

	/**
	 * @param cheTradeCurrecny the cheTradeCurrecny to set
	 */
	public void setCheTradeCurrecny(String cheTradeCurrecny) {
		this.cheTradeCurrecny = cheTradeCurrecny;
	}

	/**
	 * @return the cheTradeAmount
	 */
	public BigDecimal getCheTradeAmount() {
		return cheTradeAmount;
	}

	/**
	 * @param cheTradeAmount the cheTradeAmount to set
	 */
	public void setCheTradeAmount(BigDecimal cheTradeAmount) {
		this.cheTradeAmount = cheTradeAmount;
	}

	/**
	 * @return the cheTradeTime
	 */
	public Date getCheTradeTime() {
		return cheTradeTime;
	}

	/**
	 * @param cheTradeTime the cheTradeTime to set
	 */
	public void setCheTradeTime(Date cheTradeTime) {
		this.cheTradeTime = cheTradeTime;
	}

	/**
	 * @return the cheBankCode
	 */
	public String getCheBankCode() {
		return cheBankCode;
	}

	/**
	 * @param cheBankCode the cheBankCode to set
	 */
	public void setCheBankCode(String cheBankCode) {
		this.cheBankCode = cheBankCode;
	}

	/**
	 * @return the cheTradeStatus
	 */
	public Integer getCheTradeStatus() {
		return cheTradeStatus;
	}

	/**
	 * @param cheTradeStatus the cheTradeStatus to set
	 */
	public void setCheTradeStatus(Integer cheTradeStatus) {
		this.cheTradeStatus = cheTradeStatus;
	}
	/**
	 * @return the cheCHACode
	 */
	public Integer getCheCHACode() {
		return cheCHACode;
	}

	/**
	 * @param cheCHACode the cheCHACode to set
	 */
	public void setCheCHACode(Integer cheCHACode) {
		this.cheCHACode = cheCHACode;
	}
	/**
	 * @return the cheTradeNo
	 */
	public String getCheTradeNo() {
		return cheTradeNo;
	}

	/**
	 * @param cheTradeNo the cheTradeNo to set
	 */
	
	public void setCheTradeNo(String cheTradeNo) {
		this.cheTradeNo = cheTradeNo;
	}
	/**
	 * @return the cheEXETime
	 */
	public Date getCheEXETime() {
		return cheEXETime;
	}

	/**
	 * @param cheEXETime the cheEXETime to set
	 */
	public void setCheEXETime(Date cheEXETime) {
		this.cheEXETime = cheEXETime;
	}
	/**
	 * @return the cheEXEAcount
	 */
	public String getCheEXEAcount() {
		return cheEXEAcount;
	}

	/**
	 * @param cheEXEAcount the cheEXEAcount to set
	 */
	public void setCheEXEAcount(String cheEXEAcount) {
		this.cheEXEAcount = cheEXEAcount;
	}	
}