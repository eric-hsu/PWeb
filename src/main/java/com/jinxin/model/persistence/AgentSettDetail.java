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
import javax.persistence.Transient;

/**
 * AgentSettDetail entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_AGENT_SETT_DETAIL")
public class AgentSettDetail implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 自增(1,1);
	@Id
	@Column(name = "ASD_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AGENT_SETT_DETAIL_SEQ")
	@SequenceGenerator(name = "CCPS_AGENT_SETT_DETAIL_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AGENT_SETT_DETAIL_SEQ")
	private Long asdId;
	// 代理商结算表
	@ManyToOne
	@JoinColumn(name = "ASD_AS_ID", nullable = false)
	private AgentSettlement agentSettlement;
	// 商户号;
	@Column(name = "ASD_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long asdMerNo;
	// 网关接入号
	@Column(name = "ASD_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long asdGwNo;
	// 交易币种;
	@Column(name = "ASD_TS_CURRENCY", nullable = false)
	private String asdTsCurrency;
	// 划款汇率;
	@Column(name = "ASD_RATE_VALUE", nullable = false, precision = 18, scale = 4)
	private BigDecimal asdRateValue;
	// 交易成功笔数;
	@Column(name = "ASD_TRADE_COUNT", nullable = false, precision = 38, scale = 0)
	private Long asdTradeCount;
	
	// 交易失败笔数;
	@Column(name = "ASD_TRADE_FAILCOUNT", nullable = false, precision = 38, scale = 0)
	private Long asdTradeFailCount;
	

	// 退款笔数;
	@Column(name = "ASD_REFUNDMENT_COUNT", nullable = false, precision = 38, scale = 0)
	private Long asdRefundmentCount;
	// 通道拒付笔数;
	@Column(name = "ASD_PROTEST_COUNT", nullable = false, precision = 38, scale = 0)
	private Long asdProtestCount;
	// 通道冻结笔数;
	@Column(name = "ASD_CONGEAL_COUNT", nullable = false, precision = 38, scale = 0)
	private Long asdCongealCount;
	//拒付申诉笔数
	@Column(name = "ASD_EXPLAIN_COUNT",  precision = 38, scale = 0)
	private Long asdExplainCount;
	// 交易金额;
	@Column(name = "ASD_TRADE_AMOUNT", nullable = false, precision = 18)
	private BigDecimal asdTradeAmount;
	// 退款金额;
	@Column(name = "ASD_REFUNDMENT_AMOUNT", nullable = false, precision = 18)
	private BigDecimal asdRefundmentAmount;
	// 拒付金额;
	@Column(name = "ASD_PROTEST_AMOUNT", nullable = false, precision = 18)
	private BigDecimal asdProtestAmount;
	// 冻结金额;
	@Column(name = "ASD_CONGEAL_AMOUNT", nullable = false, precision = 18)
	private BigDecimal asdCongealAmount;
	
	// 拒付申诉金额;
	@Column(name = "ASD_EXPLAIN_AMOUNT",  precision = 18)
	private BigDecimal asdExplainAmount;
	// 回佣率;指代理商扣率
	@Column(name = "ASD_RATE", precision = 18, scale = 4)
	private BigDecimal asdRate;
	// 单笔回佣金币种;
	@Column(name = "ASD_FEE_CURRENCY")
	private String asdFeeCurrency;
	// 单笔回佣金;
	@Column(name = "ASD_FEE", precision = 18)
	private BigDecimal asdFee;
	// 佣金(按交易币种);
	@Column(name = "ASD_AMOUNT_TRADE", nullable = false, precision = 18)
	private BigDecimal asdAmountTrade;
	// 佣金(按结算币种);
	@Column(name = "ASD_AMOUNT_SETT", precision = 18)
	private BigDecimal asdAmountSett;
	//划款类型
	@Column(name = "ASD_SETTTYPE")
	private Integer asdSettType;
	
	
	
	//失败订单是否收取手续费 (针对代理商)1 :是 0:否
	@Transient		
	private Integer trFEEFailAgent;
	
	//划款前成功订单全额异常是否收取手续费(针对代理商) 1 :是 0:否
	@Transient		
	private Integer trFEESuccessAgent;
	
	//划款后成功订单全额异常是否收取手续费(针对代理商) 1 :是 0:否
	@Transient			
	private Integer trFEESuccessAfterAgent;
	
	//划款前是否收取异常金额的手续费(针对代理商) 1 :是 0:否
	@Transient		
	private Integer trIsBackAgent;
	
	//划款后是否收取异常金额的手续费(针对代理商) 1 :是 0:否
	@Transient		
	private Integer trIsBackAfterAgent;

	
	
	
	/**
	 * @return the trFEEFailAgent
	 */
	public Integer getTrFEEFailAgent() {
		return trFEEFailAgent;
	}

	/**
	 * @param trFEEFailAgent the trFEEFailAgent to set
	 */
	public void setTrFEEFailAgent(Integer trFEEFailAgent) {
		this.trFEEFailAgent = trFEEFailAgent;
	}

	/**
	 * @return the trFEESuccessAgent
	 */
	public Integer getTrFEESuccessAgent() {
		return trFEESuccessAgent;
	}

	/**
	 * @param trFEESuccessAgent the trFEESuccessAgent to set
	 */
	public void setTrFEESuccessAgent(Integer trFEESuccessAgent) {
		this.trFEESuccessAgent = trFEESuccessAgent;
	}

	/**
	 * @return the trFEESuccessAfterAgent
	 */
	public Integer getTrFEESuccessAfterAgent() {
		return trFEESuccessAfterAgent;
	}

	/**
	 * @param trFEESuccessAfterAgent the trFEESuccessAfterAgent to set
	 */
	public void setTrFEESuccessAfterAgent(Integer trFEESuccessAfterAgent) {
		this.trFEESuccessAfterAgent = trFEESuccessAfterAgent;
	}

	/**
	 * @return the trIsBackAgent
	 */
	public Integer getTrIsBackAgent() {
		return trIsBackAgent;
	}

	/**
	 * @param trIsBackAgent the trIsBackAgent to set
	 */
	public void setTrIsBackAgent(Integer trIsBackAgent) {
		this.trIsBackAgent = trIsBackAgent;
	}

	/**
	 * @return the trIsBackAfterAgent
	 */
	public Integer getTrIsBackAfterAgent() {
		return trIsBackAfterAgent;
	}

	/**
	 * @param trIsBackAfterAgent the trIsBackAfterAgent to set
	 */
	public void setTrIsBackAfterAgent(Integer trIsBackAfterAgent) {
		this.trIsBackAfterAgent = trIsBackAfterAgent;
	}

	/**
	 * @return the asdTradeFailCount
	 */
	public Long getAsdTradeFailCount() {
		return asdTradeFailCount;
	}

	/**
	 * @param asdTradeFailCount the asdTradeFailCount to set
	 */
	public void setAsdTradeFailCount(Long asdTradeFailCount) {
		this.asdTradeFailCount = asdTradeFailCount;
	}

	/**
	 * @return the asdExplainCount
	 */
	public Long getAsdExplainCount() {
		return asdExplainCount;
	}

	/**
	 * @param asdExplainCount the asdExplainCount to set
	 */
	public void setAsdExplainCount(Long asdExplainCount) {
		this.asdExplainCount = asdExplainCount;
	}

	/**
	 * @return the asdExplainAmount
	 */
	public BigDecimal getAsdExplainAmount() {
		return asdExplainAmount;
	}

	/**
	 * @param asdExplainAmount the asdExplainAmount to set
	 */
	public void setAsdExplainAmount(BigDecimal asdExplainAmount) {
		this.asdExplainAmount = asdExplainAmount;
	}

	/**
	 * @return the asdId
	 */
	public Long getAsdId() {
		return asdId;
	}

	/**
	 * @param asdId the asdId to set
	 */
	public void setAsdId(Long asdId) {
		this.asdId = asdId;
	}

	/**
	 * @return the agentSettlement
	 */
	public AgentSettlement getAgentSettlement() {
		return agentSettlement;
	}

	/**
	 * @param agentSettlement
	 *            the agentSettlement to set
	 */
	public void setAgentSettlement(AgentSettlement agentSettlement) {
		this.agentSettlement = agentSettlement;
	}

	/**
	 * @return the asdMerNo
	 */
	public Long getAsdMerNo() {
		return asdMerNo;
	}

	/**
	 * @param asdMerNo
	 *            the asdMerNo to set
	 */
	public void setAsdMerNo(Long asdMerNo) {
		this.asdMerNo = asdMerNo;
	}

	/**
	 * @return the asdGwNo
	 */
	public Long getAsdGwNo() {
		return asdGwNo;
	}

	/**
	 * @param asdGwNo
	 *            the asdGwNo to set
	 */
	public void setAsdGwNo(Long asdGwNo) {
		this.asdGwNo = asdGwNo;
	}

	/**
	 * @return the asdTsCurrency
	 */
	public String getAsdTsCurrency() {
		return asdTsCurrency;
	}

	/**
	 * @param asdTsCurrency
	 *            the asdTsCurrency to set
	 */
	public void setAsdTsCurrency(String asdTsCurrency) {
		this.asdTsCurrency = asdTsCurrency;
	}

	/**
	 * @return the asdRateValue
	 */
	public BigDecimal getAsdRateValue() {
		return asdRateValue;
	}

	/**
	 * @param asdRateValue
	 *            the asdRateValue to set
	 */
	public void setAsdRateValue(BigDecimal asdRateValue) {
		this.asdRateValue = asdRateValue;
	}

	/**
	 * @return the asdTradeCount
	 */
	public Long getAsdTradeCount() {
		return asdTradeCount;
	}

	/**
	 * @param asdTradeCount
	 *            the asdTradeCount to set
	 */
	public void setAsdTradeCount(Long asdTradeCount) {
		this.asdTradeCount = asdTradeCount;
	}

	/**
	 * @return the asdRefundmentCount
	 */
	public Long getAsdRefundmentCount() {
		return asdRefundmentCount;
	}

	/**
	 * @param asdRefundmentCount
	 *            the asdRefundmentCount to set
	 */
	public void setAsdRefundmentCount(Long asdRefundmentCount) {
		this.asdRefundmentCount = asdRefundmentCount;
	}

	/**
	 * @return the asdProtestCount
	 */
	public Long getAsdProtestCount() {
		return asdProtestCount;
	}

	/**
	 * @param asdProtestCount
	 *            the asdProtestCount to set
	 */
	public void setAsdProtestCount(Long asdProtestCount) {
		this.asdProtestCount = asdProtestCount;
	}

	/**
	 * @return the asdCongealCount
	 */
	public Long getAsdCongealCount() {
		return asdCongealCount;
	}

	/**
	 * @param asdCongealCount
	 *            the asdCongealCount to set
	 */
	public void setAsdCongealCount(Long asdCongealCount) {
		this.asdCongealCount = asdCongealCount;
	}

	/**
	 * @return the asdTradeAmount
	 */
	public BigDecimal getAsdTradeAmount() {
		return asdTradeAmount;
	}

	/**
	 * @param asdTradeAmount
	 *            the asdTradeAmount to set
	 */
	public void setAsdTradeAmount(BigDecimal asdTradeAmount) {
		this.asdTradeAmount = asdTradeAmount;
	}

	/**
	 * @return the asdRefundmentAmount
	 */
	public BigDecimal getAsdRefundmentAmount() {
		return asdRefundmentAmount;
	}

	/**
	 * @param asdRefundmentAmount
	 *            the asdRefundmentAmount to set
	 */
	public void setAsdRefundmentAmount(BigDecimal asdRefundmentAmount) {
		this.asdRefundmentAmount = asdRefundmentAmount;
	}

	/**
	 * @return the asdProtestAmount
	 */
	public BigDecimal getAsdProtestAmount() {
		return asdProtestAmount;
	}

	/**
	 * @param asdProtestAmount
	 *            the asdProtestAmount to set
	 */
	public void setAsdProtestAmount(BigDecimal asdProtestAmount) {
		this.asdProtestAmount = asdProtestAmount;
	}

	/**
	 * @return the asdCongealAmount
	 */
	public BigDecimal getAsdCongealAmount() {
		return asdCongealAmount;
	}

	/**
	 * @param asdCongealAmount
	 *            the asdCongealAmount to set
	 */
	public void setAsdCongealAmount(BigDecimal asdCongealAmount) {
		this.asdCongealAmount = asdCongealAmount;
	}

	/**
	 * @return the asdRate
	 */
	public BigDecimal getAsdRate() {
		return asdRate;
	}

	/**
	 * @param asdRate
	 *            the asdRate to set
	 */
	public void setAsdRate(BigDecimal asdRate) {
		this.asdRate = asdRate;
	}

	/**
	 * @return the asdFeeCurrency
	 */
	public String getAsdFeeCurrency() {
		return asdFeeCurrency;
	}

	/**
	 * @param asdFeeCurrency
	 *            the asdFeeCurrency to set
	 */
	public void setAsdFeeCurrency(String asdFeeCurrency) {
		this.asdFeeCurrency = asdFeeCurrency;
	}

	/**
	 * @return the asdFee
	 */
	public BigDecimal getAsdFee() {
		return asdFee;
	}

	/**
	 * @param asdFee
	 *            the asdFee to set
	 */
	public void setAsdFee(BigDecimal asdFee) {
		this.asdFee = asdFee;
	}

	/**
	 * @return the asdAmountTrade
	 */
	public BigDecimal getAsdAmountTrade() {
		return asdAmountTrade;
	}

	/**
	 * @param asdAmountTrade
	 *            the asdAmountTrade to set
	 */
	public void setAsdAmountTrade(BigDecimal asdAmountTrade) {
		this.asdAmountTrade = asdAmountTrade;
	}

	/**
	 * @return the asdAmountSett
	 */
	public BigDecimal getAsdAmountSett() {
		return asdAmountSett;
	}

	/**
	 * @param asdAmountSett
	 *            the asdAmountSett to set
	 */
	public void setAsdAmountSett(BigDecimal asdAmountSett) {
		this.asdAmountSett = asdAmountSett;
	}

	/**
	 * @return the asdSettType
	 */
	public Integer getAsdSettType() {
		return asdSettType;
	}

	/**
	 * @param asdSettType the asdSettType to set
	 */
	public void setAsdSettType(Integer asdSettType) {
		this.asdSettType = asdSettType;
	}

	

}