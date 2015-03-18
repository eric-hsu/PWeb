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
import javax.persistence.UniqueConstraint;

/**
 * ChannelDiffinfo entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CHANNEL_DIFFINFO", uniqueConstraints = @UniqueConstraint(columnNames = "CDI_TR_NO"))
public class ChannelDiffinfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "CDI_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CHANNEL_DIFFINFO_SEQ")
	@SequenceGenerator(name = "CCPS_CHANNEL_DIFFINFO_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CHANNEL_DIFFINFO_SEQ")
	private Long cdiId;
	// 正常交易记录表
	@ManyToOne
	@JoinColumn(name = "CDI_TR_NO", nullable = false)
	private Traderecord traderecord;
	// 系统跟踪号 必须从100000开始,保证长度为6, 如果到999999则从头再来
	@Column(name = "CDI_SYS_STRACE")
	private String cdiSysStrace;
	// 账单号 从1000开始,如果到999999则从头开始或者转移数据

	@Column(name = "CDI_BILLNO")
	private String cdiBillno;
	// 0 : 未冲正 ; 1 : 冲正成功 ; 2 冲正失败 ;
	@Column(name = "CDI_REVERSAL", precision = 38, scale = 0)
	private Long cdiReversal;
	// 处理码;
	@Column(name = "CDI_PRO_CODE")
	private String cdiProCode;
	// 人民币交易金额;
	@Column(name = "CDI_CNY_AMOUNT", precision = 18)
	private BigDecimal cdiCnyAmount;
	// 检索索引号;
	@Column(name = "CDI_INDEXNO")
	private String cdiIndexno;
	// 收卡单位终端标识码;
	@Column(name = "CDI_INDENTIFY_CODE")
	private String cdiIndentifyCode;
	// 返回时间;
	@Column(name = "CDI_RETURNTIME")
	private Date cdiReturntime;
	// 货币交易代码;
	@Column(name = "CDI_TRADECDOE")
	private String cdiTradecdoe;
	// 外币交易金额;
	@Column(name = "CDI_TRADEAMOUNT", precision = 18)
	private BigDecimal cdiTradeamount;
	// 汇率;
	@Column(name = "CDI_RATE", precision = 18, scale = 4)
	private BigDecimal cdiRate;
	// 备注
	@Column(name = "CDI_REMARK")
	private String cdiRemark;

	/**
	 * @return the cdiId
	 */
	public Long getCdiId() {
		return cdiId;
	}

	/**
	 * @param cdiId
	 *            the cdiId to set
	 */
	public void setCdiId(Long cdiId) {
		this.cdiId = cdiId;
	}

	/**
	 * @return the traderecord
	 */
	public Traderecord getTraderecord() {
		return traderecord;
	}

	/**
	 * @param traderecord
	 *            the traderecord to set
	 */
	public void setTraderecord(Traderecord traderecord) {
		this.traderecord = traderecord;
	}

	/**
	 * @return the cdiSysStrace
	 */
	public String getCdiSysStrace() {
		return cdiSysStrace;
	}

	/**
	 * @param cdiSysStrace
	 *            the cdiSysStrace to set
	 */
	public void setCdiSysStrace(String cdiSysStrace) {
		this.cdiSysStrace = cdiSysStrace;
	}

	/**
	 * @return the cdiBillno
	 */
	public String getCdiBillno() {
		return cdiBillno;
	}

	/**
	 * @param cdiBillno
	 *            the cdiBillno to set
	 */
	public void setCdiBillno(String cdiBillno) {
		this.cdiBillno = cdiBillno;
	}

	/**
	 * @return the cdiReversal
	 */
	public Long getCdiReversal() {
		return cdiReversal;
	}

	/**
	 * @param cdiReversal
	 *            the cdiReversal to set
	 */
	public void setCdiReversal(Long cdiReversal) {
		this.cdiReversal = cdiReversal;
	}

	/**
	 * @return the cdiProCode
	 */
	public String getCdiProCode() {
		return cdiProCode;
	}

	/**
	 * @param cdiProCode
	 *            the cdiProCode to set
	 */
	public void setCdiProCode(String cdiProCode) {
		this.cdiProCode = cdiProCode;
	}

	/**
	 * @return the cdiCnyAmount
	 */
	public BigDecimal getCdiCnyAmount() {
		return cdiCnyAmount;
	}

	/**
	 * @param cdiCnyAmount
	 *            the cdiCnyAmount to set
	 */
	public void setCdiCnyAmount(BigDecimal cdiCnyAmount) {
		this.cdiCnyAmount = cdiCnyAmount;
	}

	/**
	 * @return the cdiIndexno
	 */
	public String getCdiIndexno() {
		return cdiIndexno;
	}

	/**
	 * @param cdiIndexno
	 *            the cdiIndexno to set
	 */
	public void setCdiIndexno(String cdiIndexno) {
		this.cdiIndexno = cdiIndexno;
	}

	/**
	 * @return the cdiIndentifyCode
	 */
	public String getCdiIndentifyCode() {
		return cdiIndentifyCode;
	}

	/**
	 * @param cdiIndentifyCode
	 *            the cdiIndentifyCode to set
	 */
	public void setCdiIndentifyCode(String cdiIndentifyCode) {
		this.cdiIndentifyCode = cdiIndentifyCode;
	}

	/**
	 * @return the cdiReturntime
	 */
	public Date getCdiReturntime() {
		return cdiReturntime;
	}

	/**
	 * @param cdiReturntime
	 *            the cdiReturntime to set
	 */
	public void setCdiReturntime(Date cdiReturntime) {
		this.cdiReturntime = cdiReturntime;
	}

	/**
	 * @return the cdiTradecdoe
	 */
	public String getCdiTradecdoe() {
		return cdiTradecdoe;
	}

	/**
	 * @param cdiTradecdoe
	 *            the cdiTradecdoe to set
	 */
	public void setCdiTradecdoe(String cdiTradecdoe) {
		this.cdiTradecdoe = cdiTradecdoe;
	}

	/**
	 * @return the cdiTradeamount
	 */
	public BigDecimal getCdiTradeamount() {
		return cdiTradeamount;
	}

	/**
	 * @param cdiTradeamount
	 *            the cdiTradeamount to set
	 */
	public void setCdiTradeamount(BigDecimal cdiTradeamount) {
		this.cdiTradeamount = cdiTradeamount;
	}

	/**
	 * @return the cdiRate
	 */
	public BigDecimal getCdiRate() {
		return cdiRate;
	}

	/**
	 * @param cdiRate
	 *            the cdiRate to set
	 */
	public void setCdiRate(BigDecimal cdiRate) {
		this.cdiRate = cdiRate;
	}

	/**
	 * @return the cdiRemark
	 */
	public String getCdiRemark() {
		return cdiRemark;
	}

	/**
	 * @param cdiRemark
	 *            the cdiRemark to set
	 */
	public void setCdiRemark(String cdiRemark) {
		this.cdiRemark = cdiRemark;
	}

}