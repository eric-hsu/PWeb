package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;

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
 * <p> Title: </p>
 * <p>Description: TranSettDetail映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_TRAN_SETT_DETAIL")
public class TranSettDetail implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// tsdId自增(1,1);
	@Id
	@Column(name = "TSD_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_TRAN_SETT_DETAIL_SEQ")
	@SequenceGenerator(name = "CCPS_TRAN_SETT_DETAIL_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_TRAN_SETT_DETAIL_SEQ")
	private Long tsdId;

	//
	@ManyToOne
	@JoinColumn(name = "TSD_TS_ID", nullable = false)
	private TranSettlement tranSettlement;

	// 网关接入号
	@Column(name = "TSD_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long tsdGwNo;

	// 银行代码 ;
	@Column(name = "TSD_BANK_CODE", nullable = false)
	private String tsdBankCode;

	// 商户的支付币种, 可能不同于通道本身的币种 ;
	@Column(name = "TSD_CURRENCY", nullable = false)
	private String tsdCurrency;

	// 由此表的交易币种换算成主表的划款币种时的汇率;
	@Column(name = "TSD_RATE_VALUE", nullable = false, precision = 18, scale = 4)
	private BigDecimal tsdRateValue;

	//交易扣率
	@Column(name = "TSD_TRADERATE", nullable = false, precision = 18, scale = 4)
	private BigDecimal tsdTraderate;
	
	// 单笔手续费币种 ;
	@Column(name = "TSD_SPP_CURRENCY", nullable = false)
	private String tsdSppCurrency;
	
	//单笔手续费
	@Column(name = "TSD_SPP_AMOUNT", nullable = false, precision = 18, scale = 4)
	private BigDecimal tsdSppAmount;
	
	//保证金扣率
	@Column(name = "TSD_BAILRATE", nullable = false, precision = 18, scale = 4)
	private BigDecimal tsdBailrate;
	
	// 通道交易成功笔数;
	@Column(name = "TSD_TRADE_COUNT", nullable = false, precision = 38, scale = 0)
	private Integer tsdTradeCount;
	
	// 通道交易失败笔数;
	@Column(name = "TSD_TRADE_FAILCOUNT", nullable = false, precision = 38, scale = 0)
	private Integer tsdTradeFailCount;

	// 通道拒付笔数;
	@Column(name = "TSD_REFUNDMENT_COUNT", nullable = false, precision = 38, scale = 0)
	private Integer tsdRefundmentCount;

	// 通道拒付笔数;
	@Column(name = "TSD_PROTEST_COUNT", nullable = false, precision = 38, scale = 0)
	private Integer tsdProtestCount;

	// 通道冻结笔数;
	@Column(name = "TSD_CONGEAL_COUNT", nullable = false, precision = 38, scale = 0)
	private Integer tsdCongealCount;
	
	//拒付申诉笔数
	@Column(name = "TSD_EXPLAIN_COUNT", nullable = false, precision = 38, scale = 0)
	private Integer tsdExplainCount;
	
	// 拒付申诉金额;
	@Column(name = "TSD_EXPLAIN_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdExplainAmount;

	// 交易金额;
	@Column(name = "TSD_TRADE_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdTradeAmount;

	// 退款金额;
	@Column(name = "TSD_REFUNDMENT_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdRefundmentAmount;

	// 拒付金额;
	@Column(name = "TSD_PROTEST_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdProtestAmount;

	// 冻结金额;
	@Column(name = "TSD_CONGEAL_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdCongealAmount;

	// 保证金
	@Column(name = "TSD_RESERVER_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdReserverAmount;

	//单笔手续费
	@Column(name = "TSD_SPP_AMOUNTS", nullable = false, precision = 18)
	private BigDecimal tsdSppAmounts;
	
	
	// 手续费
	@Column(name = "TSD_FEE_AMOUNT", nullable = false, precision = 18)
	private BigDecimal tsdFeeAmount;

	// 应划金额(按交易币种);
	@Column(name = "TSD_TRADE_TOTAL", nullable = false, precision = 18)
	private BigDecimal tsdTradeTotal;

	// 应划金额(按结算币种);
	@Column(name = "TSD_SETT_TOTAL", nullable = false, precision = 18)
	private BigDecimal tsdSettTotal;
	//划款类型
	@Column(name = "TSD_SETTTYPE")
	private Integer tsdSettType;
	

	/**
	 * @return the tsdExplainCount
	 */
	public Integer getTsdExplainCount() {
		return tsdExplainCount;
	}

	/**
	 * @param tsdExplainCount the tsdExplainCount to set
	 */
	public void setTsdExplainCount(Integer tsdExplainCount) {
		this.tsdExplainCount = tsdExplainCount;
	}

	/**
	 * @return the tsdExplainAmount
	 */
	public BigDecimal getTsdExplainAmount() {
		return tsdExplainAmount;
	}

	/**
	 * @param tsdExplainAmount the tsdExplainAmount to set
	 */
	public void setTsdExplainAmount(BigDecimal tsdExplainAmount) {
		this.tsdExplainAmount = tsdExplainAmount;
	}

	public Long getTsdId() {
		return tsdId;
	}

	public void setTsdId(Long tsdId) {
		this.tsdId = tsdId;
	}

	public TranSettlement getTranSettlement() {
		return tranSettlement;
	}

	public void setTranSettlement(TranSettlement tranSettlement) {
		this.tranSettlement = tranSettlement;
	}

	public Long getTsdGwNo() {
		return tsdGwNo;
	}

	public void setTsdGwNo(Long tsdGwNo) {
		this.tsdGwNo = tsdGwNo;
	}

	public String getTsdBankCode() {
		return tsdBankCode;
	}

	public void setTsdBankCode(String tsdBankCode) {
		this.tsdBankCode = tsdBankCode;
	}

	public String getTsdCurrency() {
		return tsdCurrency;
	}

	public void setTsdCurrency(String tsdCurrency) {
		this.tsdCurrency = tsdCurrency;
	}

	public BigDecimal getTsdRateValue() {
		return tsdRateValue;
	}

	public void setTsdRateValue(BigDecimal tsdRateValue) {
		this.tsdRateValue = tsdRateValue;
	}

	public Integer getTsdTradeCount() {
		return tsdTradeCount;
	}

	public void setTsdTradeCount(Integer tsdTradeCount) {
		this.tsdTradeCount = tsdTradeCount;
	}
	
	public Integer getTsdTradeFailCount() {
		return tsdTradeFailCount;
	}

	public void setTsdTradeFailCount(Integer tsdTradeFailCount) {
		this.tsdTradeFailCount = tsdTradeFailCount;
	}

	public Integer getTsdRefundmentCount() {
		return tsdRefundmentCount;
	}

	public void setTsdRefundmentCount(Integer tsdRefundmentCount) {
		this.tsdRefundmentCount = tsdRefundmentCount;
	}

	public Integer getTsdProtestCount() {
		return tsdProtestCount;
	}

	public void setTsdProtestCount(Integer tsdProtestCount) {
		this.tsdProtestCount = tsdProtestCount;
	}

	public Integer getTsdCongealCount() {
		return tsdCongealCount;
	}

	public void setTsdCongealCount(Integer tsdCongealCount) {
		this.tsdCongealCount = tsdCongealCount;
	}

	public BigDecimal getTsdTradeAmount() {
		return tsdTradeAmount;
	}

	public void setTsdTradeAmount(BigDecimal tsdTradeAmount) {
		this.tsdTradeAmount = tsdTradeAmount;
	}

	public BigDecimal getTsdRefundmentAmount() {
		return tsdRefundmentAmount;
	}

	public void setTsdRefundmentAmount(BigDecimal tsdRefundmentAmount) {
		this.tsdRefundmentAmount = tsdRefundmentAmount;
	}

	public BigDecimal getTsdProtestAmount() {
		return tsdProtestAmount;
	}

	public void setTsdProtestAmount(BigDecimal tsdProtestAmount) {
		this.tsdProtestAmount = tsdProtestAmount;
	}

	public BigDecimal getTsdCongealAmount() {
		return tsdCongealAmount;
	}

	public void setTsdCongealAmount(BigDecimal tsdCongealAmount) {
		this.tsdCongealAmount = tsdCongealAmount;
	}

	public BigDecimal getTsdReserverAmount() {
		return tsdReserverAmount;
	}

	public void setTsdReserverAmount(BigDecimal tsdReserverAmount) {
		this.tsdReserverAmount = tsdReserverAmount;
	}
	
	public BigDecimal getTsdSppAmounts() {
		return tsdSppAmounts;
	}

	public void setTsdSppAmounts(BigDecimal tsdSppAmounts) {
		this.tsdSppAmounts = tsdSppAmounts;
	}

	public BigDecimal getTsdFeeAmount() {
		return tsdFeeAmount;
	}

	public void setTsdFeeAmount(BigDecimal tsdFeeAmount) {
		this.tsdFeeAmount = tsdFeeAmount;
	}

	public BigDecimal getTsdTradeTotal() {
		return tsdTradeTotal;
	}

	public void setTsdTradeTotal(BigDecimal tsdTradeTotal) {
		this.tsdTradeTotal = tsdTradeTotal;
	}

	public BigDecimal getTsdSettTotal() {
		return tsdSettTotal;
	}

	public void setTsdSettTotal(BigDecimal tsdSettTotal) {
		this.tsdSettTotal = tsdSettTotal;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public Integer getTsdSettType() {
		return tsdSettType;
	}

	public void setTsdSettType(Integer tsdSettType) {
		this.tsdSettType = tsdSettType;
	}

	public BigDecimal getTsdTraderate() {
		return tsdTraderate;
	}

	public void setTsdTraderate(BigDecimal tsdTraderate) {
		this.tsdTraderate = tsdTraderate;
	}

	public String getTsdSppCurrency() {
		return tsdSppCurrency;
	}

	public void setTsdSppCurrency(String tsdSppCurrency) {
		this.tsdSppCurrency = tsdSppCurrency;
	}

	public BigDecimal getTsdSppAmount() {
		return tsdSppAmount;
	}

	public void setTsdSppAmount(BigDecimal tsdSppAmount) {
		this.tsdSppAmount = tsdSppAmount;
	}

	public BigDecimal getTsdBailrate() {
		return tsdBailrate;
	}

	public void setTsdBailrate(BigDecimal tsdBailrate) {
		this.tsdBailrate = tsdBailrate;
	}
	
}