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
 * <p> Title: </p>
 * <p>Description: Traderecord映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_TS_TRADERECORD")
public class TsTraderecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "TR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_TS_TRADERECORD_SEQ")
	@SequenceGenerator(name = "CCPS_TS_TRADERECORD_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_TS_TRADERECORD_SEQ")
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
	@Column(name = "TR_CURRENCY", nullable = false)
	private String trCurrency;

	// 交易金额;
	@Column(name = "TR_AMOUNT",  precision = 18)
	private BigDecimal trAmount;

	//支付状态  -2 : 待确认 ; -1 : 待处理 ; 0 : 失败 ; 1 : 成功 ;
	@Column(name = "TR_STATUS",  precision = 38, scale = 0)
	private Integer trStatus;

	// 交易扣率;
	@Column(name = "TR_TRADE_RATE",  precision = 18)
	private BigDecimal trTradeRate;

	// 单笔手续费币种;
	@Column(name = "TR_SPP_CURRENCY")
	private String trSppCurrency;

	//  单笔手续费;
	@Column(name = "TR_SPP", precision = 18)
	private BigDecimal trSpp;

	// 保证金扣率;
	@Column(name = "TR_RESEVER_RATE",  precision = 18)
	private BigDecimal trReseverRate;

	// 交易汇率;
	@Column(name = "TR_RATE_VALUE",  precision = 18, scale = 4)
	private BigDecimal trRateValue;

	// 支付银行代码;
	@Column(name = "TR_BANK_CODE", nullable = false)
	private String trBankCode;

	// 支付通道代码;
	@Column(name = "TR_CHA_CODE",  precision = 38, scale = 0)
	private Long trChaCode;
	
	// 指插入本交易表的时间（即提交银行通道时间）
	@Column(name = "TR_DATETIME", nullable = false)
	private Date trDatetime;

	// 0 :为未退款;1 :为已退款 ;
	@Column(name = "TR_REFUNDMENT",  precision = 38, scale = 0)
	private Integer trRefundment;

	// 0 :为未拒付;1:为已拒付;
	@Column(name = "TR_PROTEST",  precision = 38, scale = 0)
	private Integer trProtest;

	// 0 :为未冻结;1:为已冻结;
	@Column(name = "TR_CONGEAL",  precision = 38, scale = 0)
	private Integer trCongeal;

	// 退款金额; 
	@Column(name = "TR_REFUNDMENT_AMOUNT", precision = 18)
	private BigDecimal trRefundmentAmount;

	// 拒付金额;
	@Column(name = "TR_PROTEST_AMOUNT", precision = 18)
	private BigDecimal trProtestAmount;

	// 冻结金额;
	@Column(name = "TR_CONGEAL_AMOUNT", precision = 18)
	private BigDecimal trCongealAmount;

	// 交易划款ID
	@Column(name = "TR_TS_ID",  precision = 38, scale = 0)
	private Long trTsId;

	// 交易划款状态; 0: 未划款; 1:已制表 ; 2:已划款
	@Column(name = "TR_TS_STATUS",  precision = 38, scale = 0)
	private Integer trTsStatus;
	
	//划款类型 1:首次划款 2:补款	
	@Column(name = "TR_TYPE",  precision = 38, scale = 0)
	private Integer trType;
	
	// 拒付申述 -2 未申诉 -1 申诉中  0:失败;1:成功 ;
	@Column(name = "TR_ISEXPLAIN", precision = 38, scale = 0)
	private Integer trIsexplain;
	
	//失败订单是否收取手续费(针对商户) 1 :是 0:否
	@Column(name = "TR_FEE_FAIL_MER")
	private Integer trFEEFailMER;

	//划款前成功订单全额异常是否收取手续费(针对商户) 1 :是 0:否
	@Column(name = "TR_FEE_SUCCESS_MER")
	private Integer trFEESuccessMER;
	
	//划款后成功订单全额异常是否收取手续费(针对商户) 1 :是 0:否
	@Column(name = "TR_FEE_SUCCESS_AFTER_MER")
	private Integer trFEESuccessAfterMER;
	
	//划款前是否收取异常金额的手续费(针对商户) 1 :是 0:否
	@Column(name = "TR_IS_BACK_MER")	
	private Integer trIsBackMER;
	
	//划款后是否收取异常金额的手续费 (针对商户)1 :是 0:否
	@Column(name = "TR_IS_BACK_AFTER_MER")		
	private Integer trIsBackAfterMER;
	
	//划款金额
	@Column(name = "TR_AMOUNT_SETT")		
	private BigDecimal trAmountSett;
	
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

	public Long getTrId() {
		return trId;
	}

	public void setTrId(Long trId) {
		this.trId = trId;
	}

	public String getTrNo() {
		return trNo;
	}

	public void setTrNo(String trNo) {
		this.trNo = trNo;
	}

	public Long getTrMerNo() {
		return trMerNo;
	}

	public void setTrMerNo(Long trMerNo) {
		this.trMerNo = trMerNo;
	}

	public Long getTrGwNo() {
		return trGwNo;
	}

	public void setTrGwNo(Long trGwNo) {
		this.trGwNo = trGwNo;
	}

	public String getTrCurrency() {
		return trCurrency;
	}

	public void setTrCurrency(String trCurrency) {
		this.trCurrency = trCurrency;
	}

	public BigDecimal getTrAmount() {
		return trAmount;
	}

	public void setTrAmount(BigDecimal trAmount) {
		this.trAmount = trAmount;
	}

	public Integer getTrStatus() {
		return trStatus;
	}

	public void setTrStatus(Integer trStatus) {
		this.trStatus = trStatus;
	}

	public BigDecimal getTrTradeRate() {
		return trTradeRate;
	}

	public void setTrTradeRate(BigDecimal trTradeRate) {
		this.trTradeRate = trTradeRate;
	}

	public String getTrSppCurrency() {
		return trSppCurrency;
	}

	public void setTrSppCurrency(String trSppCurrency) {
		this.trSppCurrency = trSppCurrency;
	}

	public BigDecimal getTrSpp() {
		return trSpp;
	}

	public void setTrSpp(BigDecimal trSpp) {
		this.trSpp = trSpp;
	}

	public BigDecimal getTrReseverRate() {
		return trReseverRate;
	}

	public void setTrReseverRate(BigDecimal trReseverRate) {
		this.trReseverRate = trReseverRate;
	}

	public BigDecimal getTrRateValue() {
		return trRateValue;
	}

	public void setTrRateValue(BigDecimal trRateValue) {
		this.trRateValue = trRateValue;
	}

	public String getTrBankCode() {
		return trBankCode;
	}

	public void setTrBankCode(String trBankCode) {
		this.trBankCode = trBankCode;
	}

	public Long getTrChaCode() {
		return trChaCode;
	}

	public void setTrChaCode(Long trChaCode) {
		this.trChaCode = trChaCode;
	}
	public Date getTrDatetime() {
		return trDatetime;
	}

	public void setTrDatetime(Date trDatetime) {
		this.trDatetime = trDatetime;
	}

	public Integer getTrRefundment() {
		return trRefundment;
	}

	public void setTrRefundment(Integer trRefundment) {
		this.trRefundment = trRefundment;
	}

	public Integer getTrProtest() {
		return trProtest;
	}

	public void setTrProtest(Integer trProtest) {
		this.trProtest = trProtest;
	}

	public Integer getTrCongeal() {
		return trCongeal;
	}

	public void setTrCongeal(Integer trCongeal) {
		this.trCongeal = trCongeal;
	}

	public BigDecimal getTrRefundmentAmount() {
		return trRefundmentAmount;
	}

	public void setTrRefundmentAmount(BigDecimal trRefundmentAmount) {
		this.trRefundmentAmount = trRefundmentAmount;
	}

	public BigDecimal getTrProtestAmount() {
		return trProtestAmount;
	}

	public void setTrProtestAmount(BigDecimal trProtestAmount) {
		this.trProtestAmount = trProtestAmount;
	}

	public BigDecimal getTrCongealAmount() {
		return trCongealAmount;
	}

	public void setTrCongealAmount(BigDecimal trCongealAmount) {
		this.trCongealAmount = trCongealAmount;
	}
	public Long getTrTsId() {
		return trTsId;
	}

	public void setTrTsId(Long trTsId) {
		this.trTsId = trTsId;
	}

	public Integer getTrTsStatus() {
		return trTsStatus;
	}

	public void setTrTsStatus(Integer trTsStatus) {
		this.trTsStatus = trTsStatus;
	}
	public String getTrRemark() {
		return trRemark;
	}

	public void setTrRemark(String trRemark) {
		this.trRemark = trRemark;
	}

	public Integer getTrType() {
		return trType;
	}

	public void setTrType(Integer trType) {
		this.trType = trType;
	}

	public Integer getTrFEEFailMER() {
		return trFEEFailMER;
	}

	public void setTrFEEFailMER(Integer trFEEFailMER) {
		this.trFEEFailMER = trFEEFailMER;
	}

	public Integer getTrFEESuccessMER() {
		return trFEESuccessMER;
	}

	public void setTrFEESuccessMER(Integer trFEESuccessMER) {
		this.trFEESuccessMER = trFEESuccessMER;
	}

	public Integer getTrFEESuccessAfterMER() {
		return trFEESuccessAfterMER;
	}

	public void setTrFEESuccessAfterMER(Integer trFEESuccessAfterMER) {
		this.trFEESuccessAfterMER = trFEESuccessAfterMER;
	}

	public Integer getTrIsBackMER() {
		return trIsBackMER;
	}

	public void setTrIsBackMER(Integer trIsBackMER) {
		this.trIsBackMER = trIsBackMER;
	}

	public Integer getTrIsBackAfterMER() {
		return trIsBackAfterMER;
	}

	public void setTrIsBackAfterMER(Integer trIsBackAfterMER) {
		this.trIsBackAfterMER = trIsBackAfterMER;
	}

	public BigDecimal getTrAmountSett() {
		return trAmountSett;
	}

	public void setTrAmountSett(BigDecimal trAmountSett) {
		this.trAmountSett = trAmountSett;
	}
	
}