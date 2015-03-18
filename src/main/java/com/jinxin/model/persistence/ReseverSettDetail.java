package com.jinxin.model.persistence;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * <p>Title:</p>
 * <p>Description: CCPS_RESEVER_SETT_DETAIL映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_RESEVER_SETT_DETAIL")

public class ReseverSettDetail  implements java.io.Serializable {
  
	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1L;

	//自增
    @Id 
    @Column(name="RSD_ID", unique=true, nullable=false, precision=38, scale=0)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_RESEVER_SETT_DETAIL_SEQ")      
     @SequenceGenerator(name="CCPS_RESEVER_SETT_DETAIL_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_RESEVER_SETT_DETAIL_SEQ")
    private Long rsdId;
    
    //自增
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="RSD_RS_ID", nullable=false)
     private ReseverSettlement reseverSettlement;
    
    //网关接入号
    @Column(name="RSD_GW_NO", nullable=false, precision=38, scale=0)
     private Long rsdGwNo;
    
    //银行代码
    @Column(name="RSD_BANK_CODE", nullable=false)
     private String rsdBankCode;
    
    //划款汇率
    @Column(name="RSD_RATE_VALUE", nullable=false, precision=18, scale=4)
     private BigDecimal rsdRateValue;
    
    //保证金扣率
    @Column(name="RSD_BAILRATE", nullable=false, precision=18, scale=4)
     private BigDecimal rsdBailrate;
    
    //商户交易币种
    @Column(name="RSD_TR_CURRENCY", nullable=false)
     private String rsdTrCurrency;
    
    //交易笔数
    @Column(name="RSD_TRADE_COUNT", nullable=false, precision=38, scale=0)
     private Integer rsdTradeCount;
   
    //退款保证金金额
    @Column(name="RSD_REFUNDMENT_AMOUNT", nullable=false, precision=18)
     private BigDecimal rsdRefundmentAmount;
    
    //拒付保证金金额
    @Column(name="RSD_PROTEST_AMOUNT", nullable=false, precision=18)
     private BigDecimal rsdProtestAmount;
    
    //交易金额
    @Column(name="RSD_TRADE_AMOUNT", nullable=false, precision=18)
     private BigDecimal rsdTradeAmount;
	
	// 拒付申诉金额;
	@Column(name = "RSD_EXPLAIN_AMOUNT", nullable = false, precision = 18)
	private BigDecimal rsdExplainAmount;
    
    /**
	 * @return the rsdExplainAmount
	 */
	public BigDecimal getRsdExplainAmount() {
		return rsdExplainAmount;
	}

	/**
	 * @param rsdExplainAmount the rsdExplainAmount to set
	 */
	public void setRsdExplainAmount(BigDecimal rsdExplainAmount) {
		this.rsdExplainAmount = rsdExplainAmount;
	}

	//应划保证金金额(按交易币种)
    @Column(name="RSD_TRADE_TOTAL", nullable=false, precision=18)
     private BigDecimal rsdTradeTotal;
    
    //应划保证金金额(按结算币种)
    @Column(name="RSD_SETT_TOTAL", nullable=false, precision=18)
     private BigDecimal rsdSettTotal;

	public Long getRsdId() {
		return rsdId;
	}

	public void setRsdId(Long rsdId) {
		this.rsdId = rsdId;
	}
	
	public ReseverSettlement getReseverSettlement() {
		return reseverSettlement;
	}

	public void setReseverSettlement(ReseverSettlement reseverSettlement) {
		this.reseverSettlement = reseverSettlement;
	}

	public Long getRsdGwNo() {
		return rsdGwNo;
	}

	public void setRsdGwNo(Long rsdGwNo) {
		this.rsdGwNo = rsdGwNo;
	}

	public String getRsdBankCode() {
		return rsdBankCode;
	}

	public void setRsdBankCode(String rsdBankCode) {
		this.rsdBankCode = rsdBankCode;
	}

	public BigDecimal getRsdRateValue() {
		return rsdRateValue;
	}

	public void setRsdRateValue(BigDecimal rsdRateValue) {
		this.rsdRateValue = rsdRateValue;
	}

	public String getRsdTrCurrency() {
		return rsdTrCurrency;
	}

	public void setRsdTrCurrency(String rsdTrCurrency) {
		this.rsdTrCurrency = rsdTrCurrency;
	}

	public Integer getRsdTradeCount() {
		return rsdTradeCount;
	}

	public void setRsdTradeCount(Integer rsdTradeCount) {
		this.rsdTradeCount = rsdTradeCount;
	}

	public BigDecimal getRsdRefundmentAmount() {
		return rsdRefundmentAmount;
	}

	public void setRsdRefundmentAmount(BigDecimal rsdRefundmentAmount) {
		this.rsdRefundmentAmount = rsdRefundmentAmount;
	}

	public BigDecimal getRsdProtestAmount() {
		return rsdProtestAmount;
	}

	public void setRsdProtestAmount(BigDecimal rsdProtestAmount) {
		this.rsdProtestAmount = rsdProtestAmount;
	}

	public BigDecimal getRsdTradeAmount() {
		return rsdTradeAmount;
	}

	public void setRsdTradeAmount(BigDecimal rsdTradeAmount) {
		this.rsdTradeAmount = rsdTradeAmount;
	}

	public BigDecimal getRsdTradeTotal() {
		return rsdTradeTotal;
	}

	public void setRsdTradeTotal(BigDecimal rsdTradeTotal) {
		this.rsdTradeTotal = rsdTradeTotal;
	}

	public BigDecimal getRsdSettTotal() {
		return rsdSettTotal;
	}

	public void setRsdSettTotal(BigDecimal rsdSettTotal) {
		this.rsdSettTotal = rsdSettTotal;
	}

	public BigDecimal getRsdBailrate() {
		return rsdBailrate;
	}

	public void setRsdBailrate(BigDecimal rsdBailrate) {
		this.rsdBailrate = rsdBailrate;
	}
	
}