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
 * AsTraderecord entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_AS_TRADERECORD")
public class AsTraderecord implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 自增(1,1);
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AS_TRADERECORD_SEQ")
	@SequenceGenerator(name = "CCPS_AS_TRADERECORD_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AS_TRADERECORD_SEQ")
	@Column(name = "TR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	private Long trId;
	// 流水订单号;
	@Column(name = "TR_NO", nullable = false)
	private String trNo;
	
	// 商户号;
	@Column(name = "TR_MER_NO",  precision = 38, scale = 0)
	private Long trMerNo;
	// 网关接入号;
	@Column(name = "TR_GW_NO",  precision = 38, scale = 0)
	private Long trGwNo;
	// 交易币种;
	@Column(name = "TR_CURRENCY")
	private String trCurrency;
	// 交易金额;
	@Column(name = "TR_AMOUNT",  precision = 18)
	private BigDecimal trAmount;
	// -2 : 待确认 ; -1 : 待处理 ; 0 : 失败 ; 1 : 成功 ;
	@Column(name = "TR_STATUS",  precision = 38, scale = 0)
	private Long trStatus;
	// 交易扣率;
	@Column(name = "TR_TRADE_RATE",  precision = 18)
	private BigDecimal trTradeRate;
	// 单笔手续费币种;
	@Column(name = "TR_SPP_CURRENCY")
	private String trSppCurrency;
	// Single pen poundage ==SPP 单笔手续费;
	@Column(name = "TR_SPP", precision = 18)
	private BigDecimal trSpp;
	// 保证金扣率;
	@Column(name = "TR_RESEVER_RATE",  precision = 18)
	private BigDecimal trReseverRate;
	// 交易汇率;
	@Column(name = "TR_RATE_VALUE",  precision = 18, scale = 4)
	private BigDecimal trRateValue;
	
	// 支付银行代码;
	@Column(name = "TR_BANK_CODE")
	private String trBankCode;
	// 支付通道代码;
	@Column(name = "TR_CHA_CODE",  precision = 38, scale = 0)
	private Long trChaCode;
	

	// 指插入本交易表的时间（即提交银行通道时间）
	@Column(name = "TR_DATETIME")
	private Date trDatetime;

	// 0 :为未退款;1 :为已退款 ;
	@Column(name = "TR_REFUNDMENT",  precision = 38, scale = 0)
	private Long trRefundment;
	// 0 :为未拒付;1:为已拒付;
	@Column(name = "TR_PROTEST",  precision = 38, scale = 0)
	private Long trProtest;
	// 0 :为未冻结;1:为已冻结;
	@Column(name = "TR_CONGEAL",  precision = 38, scale = 0)
	private Long trCongeal;
	// 0 :为未妥投;1:为已妥投;
	@Column(name = "TR_DELIVERY", precision = 38, scale = 0)
	private Long trDelivery;
	// 退款金额; 每次退款的金额累加起来更新本字段;
	@Column(name = "TR_REFUNDMENT_AMOUNT", precision = 18)
	private BigDecimal trRefundmentAmount;
	// 拒付金额;每次拒付的金额累加起来更新本字段
	@Column(name = "TR_PROTEST_AMOUNT", precision = 18)
	private BigDecimal trProtestAmount;
	// 冻结金额;每次冻结的金额累加起来更新本字段
	@Column(name = "TR_CONGEAL_AMOUNT", precision = 18)
	private BigDecimal trCongealAmount;
	
	//划款类型 1:首次划款 2:补款	
	@Column(name = "TR_TYPE",  precision = 38, scale = 0)
	private Integer trType;
	
	
	//代理商回佣率
	@Column(name = "TR_AGENT_RATE",  precision = 38, scale = 0)
	private BigDecimal trAgentRate;	

	//代理商单笔手续费币种
	@Column(name = "TR_AGENT_SPP_CURRENCY")
	private String trAgentSPPCurrency;
	
	//代理商单笔手续费金额
	@Column(name = "TR_AGENT_SPP", precision = 18)
	private BigDecimal trAgentSPP;
	
	
	//失败订单是否收取手续费 (针对代理商)1 :是 0:否
	@Column(name = "TR_FEE_FAIL_AGENT")			
	private Integer trFEEFailAgent;
	
	//划款前成功订单全额异常是否收取手续费(针对代理商) 1 :是 0:否
	@Column(name = "TR_FEE_SUCCESS_AGENT")			
	private Integer trFEESuccessAgent;
	
	//划款后成功订单全额异常是否收取手续费(针对代理商) 1 :是 0:否
	@Column(name = "TR_FEE_SUCCESS_AFTER_AGENT")		
	private Integer trFEESuccessAfterAgent;
	
	//划款前是否收取异常金额的手续费(针对代理商) 1 :是 0:否
	@Column(name = "TR_IS_BACK_AGENT")		
	private Integer trIsBackAgent;
	
	//划款后是否收取异常金额的手续费(针对代理商) 1 :是 0:否
	@Column(name = "TR_IS_BACK_AFTER_AGENT")	
	private Integer trIsBackAfterAgent;
	
	
	//划款金额
	//@Column(name = "TR_AMOUNT_SETT")		
//	private Integer trAmountSett;
	
	

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
	 * @return the trType
	 */
	public Integer getTrType() {
		return trType;
	}

	/**
	 * @return the trAgentRate
	 */
	public BigDecimal getTrAgentRate() {
		return trAgentRate;
	}

	/**
	 * @param trAgentRate the trAgentRate to set
	 */
	public void setTrAgentRate(BigDecimal trAgentRate) {
		this.trAgentRate = trAgentRate;
	}

	/**
	 * @return the trAgentSPPCurrency
	 */
	public String getTrAgentSPPCurrency() {
		return trAgentSPPCurrency;
	}

	/**
	 * @param trAgentSPPCurrency the trAgentSPPCurrency to set
	 */
	public void setTrAgentSPPCurrency(String trAgentSPPCurrency) {
		this.trAgentSPPCurrency = trAgentSPPCurrency;
	}

	/**
	 * @return the trAgentSPP
	 */
	public BigDecimal getTrAgentSPP() {
		return trAgentSPP;
	}

	/**
	 * @param trAgentSPP the trAgentSPP to set
	 */
	public void setTrAgentSPP(BigDecimal trAgentSPP) {
		this.trAgentSPP = trAgentSPP;
	}

	/**
	 * @param trType the trType to set
	 */
	public void setTrType(Integer trType) {
		this.trType = trType;
	}

	// 代理商划款ID;
	@Column(name = "TR_AS_ID", nullable = false, precision = 38, scale = 0)
	private Long trAsId;
	// 代理商划款状态; 0: 未划款; 1:已制表 ; 2:已划款
	@Column(name = "TR_AS_STATUS", nullable = false, precision = 38, scale = 0)
	private Long trAsStatus;
	
	// 拒付申述 -2 未申诉 -1 申诉中  0:失败;1:成功 ;
	@Column(name = "TR_ISEXPLAIN", precision = 38, scale = 0)
	private Integer trIsexplain;
	
	
	
	
	

	// 备注;
	@Column(name = "TR_REMARK")
	private String trRemark;

	


	/**
	 * @return the trIsexplain
	 */
	public Integer getTrIsexplain() {
		return trIsexplain;
	}

	/**
	 * @param trIsexplain the trIsexplain to set
	 */
	public void setTrIsexplain(Integer trIsexplain) {
		this.trIsexplain = trIsexplain;
	}

	/**
	 * @return the trId
	 */
	public Long getTrId() {
		return trId;
	}

	/**
	 * @param trId the trId to set
	 */
	public void setTrId(Long trId) {
		this.trId = trId;
	}

	/**
	 * @return the trNo
	 */
	public String getTrNo() {
		return trNo;
	}

	/**
	 * @param trNo the trNo to set
	 */
	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}

	

	/**
	 * @return the trMerNo
	 */
	public Long getTrMerNo() {
		return trMerNo;
	}

	/**
	 * @param trMerNo the trMerNo to set
	 */
	public void setTrMerNo(Long trMerNo) {
		this.trMerNo = trMerNo;
	}

	/**
	 * @return the trGwNo
	 */
	public Long getTrGwNo() {
		return trGwNo;
	}

	/**
	 * @param trGwNo the trGwNo to set
	 */
	public void setTrGwNo(Long trGwNo) {
		this.trGwNo = trGwNo;
	}

	/**
	 * @return the trCurrency
	 */
	public String getTrCurrency() {
		return trCurrency;
	}

	/**
	 * @param trCurrency the trCurrency to set
	 */
	public void setTrCurrency(String trCurrency) {
		this.trCurrency = trCurrency;
	}

	/**
	 * @return the trAmount
	 */
	public BigDecimal getTrAmount() {
		return trAmount;
	}

	/**
	 * @param trAmount the trAmount to set
	 */
	public void setTrAmount(BigDecimal trAmount) {
		this.trAmount = trAmount;
	}

	/**
	 * @return the trStatus
	 */
	public Long getTrStatus() {
		return trStatus;
	}

	/**
	 * @param trStatus the trStatus to set
	 */
	public void setTrStatus(Long trStatus) {
		this.trStatus = trStatus;
	}

	/**
	 * @return the trTradeRate
	 */
	public BigDecimal getTrTradeRate() {
		return trTradeRate;
	}

	/**
	 * @param trTradeRate the trTradeRate to set
	 */
	public void setTrTradeRate(BigDecimal trTradeRate) {
		this.trTradeRate = trTradeRate;
	}

	/**
	 * @return the trSppCurrency
	 */
	public String getTrSppCurrency() {
		return trSppCurrency;
	}

	/**
	 * @param trSppCurrency the trSppCurrency to set
	 */
	public void setTrSppCurrency(String trSppCurrency) {
		this.trSppCurrency = trSppCurrency;
	}

	/**
	 * @return the trSpp
	 */
	public BigDecimal getTrSpp() {
		return trSpp;
	}

	/**
	 * @param trSpp the trSpp to set
	 */
	public void setTrSpp(BigDecimal trSpp) {
		this.trSpp = trSpp;
	}

	/**
	 * @return the trReseverRate
	 */
	public BigDecimal getTrReseverRate() {
		return trReseverRate;
	}

	/**
	 * @param trReseverRate the trReseverRate to set
	 */
	public void setTrReseverRate(BigDecimal trReseverRate) {
		this.trReseverRate = trReseverRate;
	}

	/**
	 * @return the trRateValue
	 */
	public BigDecimal getTrRateValue() {
		return trRateValue;
	}

	/**
	 * @param trRateValue the trRateValue to set
	 */
	public void setTrRateValue(BigDecimal trRateValue) {
		this.trRateValue = trRateValue;
	}

	/**
	 * @return the trBankCode
	 */
	public String getTrBankCode() {
		return trBankCode;
	}

	/**
	 * @param trBankCode the trBankCode to set
	 */
	public void setTrBankCode(String trBankCode) {
		this.trBankCode = trBankCode;
	}

	/**
	 * @return the trChaCode
	 */
	public Long getTrChaCode() {
		return trChaCode;
	}

	/**
	 * @param trChaCode the trChaCode to set
	 */
	public void setTrChaCode(Long trChaCode) {
		this.trChaCode = trChaCode;
	}

	/**
	 * @return the trDatetime
	 */
	public Date getTrDatetime() {
		return trDatetime;
	}

	/**
	 * @param trDatetime the trDatetime to set
	 */
	public void setTrDatetime(Date trDatetime) {
		this.trDatetime = trDatetime;
	}

	/**
	 * @return the trRefundment
	 */
	public Long getTrRefundment() {
		return trRefundment;
	}

	/**
	 * @param trRefundment the trRefundment to set
	 */
	public void setTrRefundment(Long trRefundment) {
		this.trRefundment = trRefundment;
	}

	/**
	 * @return the trProtest
	 */
	public Long getTrProtest() {
		return trProtest;
	}

	/**
	 * @param trProtest the trProtest to set
	 */
	public void setTrProtest(Long trProtest) {
		this.trProtest = trProtest;
	}

	/**
	 * @return the trCongeal
	 */
	public Long getTrCongeal() {
		return trCongeal;
	}

	/**
	 * @param trCongeal the trCongeal to set
	 */
	public void setTrCongeal(Long trCongeal) {
		this.trCongeal = trCongeal;
	}

	/**
	 * @return the trDelivery
	 */
	public Long getTrDelivery() {
		return trDelivery;
	}

	/**
	 * @param trDelivery the trDelivery to set
	 */
	public void setTrDelivery(Long trDelivery) {
		this.trDelivery = trDelivery;
	}

	/**
	 * @return the trRefundmentAmount
	 */
	public BigDecimal getTrRefundmentAmount() {
		return trRefundmentAmount;
	}

	/**
	 * @param trRefundmentAmount the trRefundmentAmount to set
	 */
	public void setTrRefundmentAmount(BigDecimal trRefundmentAmount) {
		this.trRefundmentAmount = trRefundmentAmount;
	}

	/**
	 * @return the trProtestAmount
	 */
	public BigDecimal getTrProtestAmount() {
		return trProtestAmount;
	}

	/**
	 * @param trProtestAmount the trProtestAmount to set
	 */
	public void setTrProtestAmount(BigDecimal trProtestAmount) {
		this.trProtestAmount = trProtestAmount;
	}

	/**
	 * @return the trCongealAmount
	 */
	public BigDecimal getTrCongealAmount() {
		return trCongealAmount;
	}

	/**
	 * @param trCongealAmount the trCongealAmount to set
	 */
	public void setTrCongealAmount(BigDecimal trCongealAmount) {
		this.trCongealAmount = trCongealAmount;
	}

	/**
	 * @return the trAsId
	 */
	public Long getTrAsId() {
		return trAsId;
	}

	/**
	 * @param trAsId the trAsId to set
	 */
	public void setTrAsId(Long trAsId) {
		this.trAsId = trAsId;
	}

	/**
	 * @return the trAsStatus
	 */
	public Long getTrAsStatus() {
		return trAsStatus;
	}

	/**
	 * @param trAsStatus the trAsStatus to set
	 */
	public void setTrAsStatus(Long trAsStatus) {
		this.trAsStatus = trAsStatus;
	}

	/**
	 * @return the trRemark
	 */
	public String getTrRemark() {
		return trRemark;
	}

	/**
	 * @param trRemark the trRemark to set
	 */
	public void setTrRemark(String trRemark) {
		this.trRemark = trRemark;
	}

	

}