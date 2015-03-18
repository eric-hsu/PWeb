package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 财务报表bean
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
@Table(name = "CCPS_FINANCE_REPORT")
public class FinanceReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "FR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_FINANCE_REPORT_SEQ")
	@SequenceGenerator(name = "CCPS_FINANCE_REPORT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_FINANCE_REPORT_SEQ")
	private Long frId;

	// 商户号
	@Column(name = "FR_MER_NO", precision = 38, scale = 0)
	private Long frMerNo;

	// 交易截止日期
	@Column(name = "FR_ENDDATE", length = 7)
	private Date frEnddate;

	// 交易币种
	@Column(name = "FR_CURRENCY")
	private String frCurrency;

	// 总交易额
	@Column(name = "FR_TOTAL_AMOUNT", precision = 18)
	private BigDecimal frTotalAmount;

	// 已退款
	@Column(name = "FR_REFUNDMENT_AMOUNT", precision = 18)
	private BigDecimal frRefundmentAmount;

	// 已拒付
	@Column(name = "FR_PROTEST_AMOUNT", precision = 18)
	private BigDecimal frProtestAmount;

	// 已冻结
	@Column(name = "FR_CONGEAL_AMOUNT", precision = 18)
	private BigDecimal frCongealAmount;

	// 已结算交易款
	@Column(name = "FR_TRADE_SETT", precision = 18)
	private BigDecimal frTradeSett;

	// 已结算保证金
	@Column(name = "FR_RESEVER_SETT", precision = 18)
	private BigDecimal frReseverSett;

	// 已扣保证金
	@Column(name = "FR_RESEVER_GET", precision = 18)
	private BigDecimal frReseverGet;

	// 已扣手续费
	@Column(name = "FR_FEE_GET", precision = 18)
	private BigDecimal frFeeGet;

	// 应扣手续费
	@Column(name = "FR_FEE_MUST", precision = 18)
	private BigDecimal frFeeMust;

	// 待结算金额
	@Column(name = "FR_SETTIGN", precision = 18)
	private BigDecimal frSettign;

	// 应付商户交易款
	@Column(name = "FR_TRADE_MUST", precision = 18)
	private BigDecimal frTradeMust;

	// 应该商户保证金
	@Column(name = "FR_RESEVER_MUST", precision = 18)
	private BigDecimal frReseverMust;

	// Constructors

	/** default constructor */
	public FinanceReport() {
	}

	public Long getFrId() {
		return this.frId;
	}

	public void setFrId(Long frId) {
		this.frId = frId;
	}

	public Long getFrMerNo() {
		return this.frMerNo;
	}

	public void setFrMerNo(Long frMerNo) {
		this.frMerNo = frMerNo;
	}

	public Date getFrEnddate() {
		return this.frEnddate;
	}

	public void setFrEnddate(Date frEnddate) {
		this.frEnddate = frEnddate;
	}

	public String getFrCurrency() {
		return this.frCurrency;
	}

	public void setFrCurrency(String frCurrency) {
		this.frCurrency = frCurrency;
	}

	public BigDecimal getFrTotalAmount() {
		return this.frTotalAmount;
	}

	public void setFrTotalAmount(BigDecimal frTotalAmount) {
		this.frTotalAmount = frTotalAmount;
	}

	public BigDecimal getFrRefundmentAmount() {
		return this.frRefundmentAmount;
	}

	public void setFrRefundmentAmount(BigDecimal frRefundmentAmount) {
		this.frRefundmentAmount = frRefundmentAmount;
	}

	public BigDecimal getFrProtestAmount() {
		return this.frProtestAmount;
	}

	public void setFrProtestAmount(BigDecimal frProtestAmount) {
		this.frProtestAmount = frProtestAmount;
	}

	public BigDecimal getFrCongealAmount() {
		return this.frCongealAmount;
	}

	public void setFrCongealAmount(BigDecimal frCongealAmount) {
		this.frCongealAmount = frCongealAmount;
	}

	public BigDecimal getFrTradeSett() {
		return this.frTradeSett;
	}

	public void setFrTradeSett(BigDecimal frTradeSett) {
		this.frTradeSett = frTradeSett;
	}

	public BigDecimal getFrReseverSett() {
		return this.frReseverSett;
	}

	public void setFrReseverSett(BigDecimal frReseverSett) {
		this.frReseverSett = frReseverSett;
	}

	public BigDecimal getFrReseverGet() {
		return this.frReseverGet;
	}

	public void setFrReseverGet(BigDecimal frReseverGet) {
		this.frReseverGet = frReseverGet;
	}

	public BigDecimal getFrFeeGet() {
		return this.frFeeGet;
	}

	public void setFrFeeGet(BigDecimal frFeeGet) {
		this.frFeeGet = frFeeGet;
	}

	public BigDecimal getFrFeeMust() {
		return this.frFeeMust;
	}

	public void setFrFeeMust(BigDecimal frFeeMust) {
		this.frFeeMust = frFeeMust;
	}

	public BigDecimal getFrSettign() {
		return this.frSettign;
	}

	public void setFrSettign(BigDecimal frSettign) {
		this.frSettign = frSettign;
	}

	public BigDecimal getFrTradeMust() {
		return this.frTradeMust;
	}

	public void setFrTradeMust(BigDecimal frTradeMust) {
		this.frTradeMust = frTradeMust;
	}

	public BigDecimal getFrReseverMust() {
		return this.frReseverMust;
	}

	public void setFrReseverMust(BigDecimal frReseverMust) {
		this.frReseverMust = frReseverMust;
	}

}