package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

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
@Table(name = "CCPS_TRADERECORD")
@DynamicInsert(true)
@DynamicUpdate(true)
public class Traderecord implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// trId自增(1,1);
	
	@Column(name = "TR_ID", nullable = false, precision = 38, scale = 0)
	private Long trId;

	// 流水订单号;
	@Id
	@Column(name = "TR_NO", nullable = false)
	private String trNo;

	// 商户订单号;
	@Column(name = "TR_MER_ORDERNO", nullable = false)
	private String trMerOrderno;

	// 商户号;
	@Column(name = "TR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long trMerNo;

	// 网关接入号;
	@Column(name = "TR_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long trGwNo;

	// 交易币种;
	@Column(name = "TR_CURRENCY", nullable = false)
	private String trCurrency;

	// 交易金额;
	@Column(name = "TR_AMOUNT", nullable = false, precision = 18)
	private BigDecimal trAmount;

	// 交易状态 -2 : 待确认 ; -1 : 待处理 ; 0 : 失败 ; 1 : 成功 ;
	@Column(name = "TR_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer trStatus;

	// 交易扣率;
	@Column(name = "TR_TRADE_RATE", nullable = false, precision = 18,columnDefinition="number(18,4) default 0" )
	private BigDecimal trTradeRate;

	// 单笔手续费币种;
	@Column(name = "TR_SPP_CURRENCY")
	private String trSppCurrency;

	// Single pen poundage ==SPP 单笔手续费;
	@Column(name = "TR_SPP", precision = 18)
	private BigDecimal trSpp;

	// 保证金扣率;
	@Column(name = "TR_RESEVER_RATE", nullable = false, precision = 18)
	private BigDecimal trReseverRate;

	// 交易汇率;
	@Column(name = "TR_RATE_VALUE", nullable = false, precision = 18, scale = 4)
	private BigDecimal trRateValue;

	// 收单币种 银行通道接收币种;
	@Column(name = "TR_BANKCURRENCY", nullable = false)
	private String trBankcurrency;

	// 收单金额;
	@Column(name = "TR_BANKAMOUT", nullable = false, precision = 18)
	private BigDecimal trBankamout;

	// 支付银行代码;
	@Column(name = "TR_BANK_CODE", nullable = false)
	private String trBankCode;

	// 支付通道代码;
	@Column(name = "TR_CHA_CODE", nullable = false, precision = 38, scale = 0)
	private Long trChaCode;

	// 是否延时通道 1 : 是; 0 : 否;
	@Column(name = "TR_ISDELAY", nullable = false, precision = 38, scale = 0)
	private Integer trIsdelay;

	// 通道扣率 与银行结算时使用
	@Column(name = "TR_CHA_RATE", nullable = false, precision = 18)
	private BigDecimal trChaRate;

	// 公司与银行的结算银行
	@Column(name = "TR_CHA_SETT_BANK")
	private String trChaSettBank;

	// 银行订单号
	@Column(name = "TR_BANKORDERNO")
	private String trBankorderno;

	// 银行返回代码;
	@Column(name = "TR_BANKRETURNCODE")
	private String trBankreturncode;

	// 银行返回信息;
	@Column(name = "TR_BANKINFO")
	private String trBankinfo;

	// 指调用网关接口支付的时间(即插入异常信息表胡时间);
	@Column(name = "TR_PAYSTARTTIME", nullable = false)
	private Date trPaystarttime;

	// 指持卡人进入信用卡信息填写的时间
	@Column(name = "TR_PAYENDTIME")
	private Date trPayendtime;

	// 指插入本交易表的时间（即提交银行通道时间）
	@Column(name = "TR_DATETIME", nullable = false)
	private Date trDatetime;

	// 银行交易时间;
	@Column(name = "TR_BANKTRADETIME")
	private Date trBanktradetime;
	
	// 银行返回时间;
	@Column(name = "TR_BANKDATETIME")
	private Date trBankdatetime;

	// 商户服务器返回URL;
	@Column(name = "TR_RETURNURL")
	private String trReturnurl;
	
	// 商户页面通知URL;
	@Column(name = "TR_PAGE_RETURNURL")
	private String trPageReturnurl;

	// 商户提交来源网址;
	@Column(name = "TR_WEBSITE")
	private String trWebsite;

	// 提交地址;指哪个支付域名; 51paypay
	@Column(name = "TR_SUBMITURL", nullable = false)
	private String trSubmiturl;
	
	// 下单电脑ip
	@Column(name = "TR_IP")
	private String trIp;

	// 勾兑 0 : 未勾对;1 : 已勾对; 
	@Column(name = "TR_CHECKED", nullable = false, precision = 38, scale = 0)
	private Integer trChecked;

	// 勾兑时间;
	@Column(name = "TR_CHECKDATETIME")
	private Date trCheckdatetime;

	// 退款 0 :为未退款;1 :为已退款 ;
	@Column(name = "TR_REFUNDMENT", nullable = false, precision = 38, scale = 0)
	private Integer trRefundment;

	// 拒付 0 :为未拒付;1:为已拒付;
	@Column(name = "TR_PROTEST", nullable = false, precision = 38, scale = 0)
	private Integer trProtest;

	// 冻结 0 :为未冻结;1:为已冻结;
	@Column(name = "TR_CONGEAL", nullable = false, precision = 38, scale = 0)
	private Integer trCongeal;

	// 妥投 0 :为未妥投;1:为已妥投;
	@Column(name = "TR_DELIVERY", nullable = false, precision = 38, scale = 0)
	private Integer trDelivery;

	// 退款金额; 每次退款的金额累加起来更新本字段;
	@Column(name = "TR_REFUNDMENT_AMOUNT", precision = 18)
	private BigDecimal trRefundmentAmount;

	// 拒付金额;每次拒付的金额累加起来更新本字段
	@Column(name = "TR_PROTEST_AMOUNT", precision = 18)
	private BigDecimal trProtestAmount;

	// 冻结金额;每次冻结的金额累加起来更新本字段
	@Column(name = "TR_CONGEAL_AMOUNT", precision = 18)
	private BigDecimal trCongealAmount;

	// 未结算金额;
	@Column(name = "TR_UNSETT_AMOUNT", precision = 18)
	private BigDecimal trUnsettAmount;

	// 已结算金额;
	@Column(name = "TR_SETT_AMOUNT", precision = 18)
	private BigDecimal trSettAmount;

	// 交易划款ID存放第一次划款的ID,第二次以后存放于异常表;
	@Column(name = "TR_TS_ID", nullable = false, precision = 38, scale = 0)
	private Long trTsId;

	// 交易划款状态; 0: 未划款; 1:已制表 ; 2:已划款
	@Column(name = "TR_TS_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer trTsStatus;

	// 保证金划款ID;
	@Column(name = "TR_RS_ID", nullable = false, precision = 38, scale = 0)
	private Long trRsId;

	// 保证金划款状态; 0: 未划款; 1:已制表 ; 2:已划款
	@Column(name = "TR_RS_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer trRsStatus;

	// 代理商号;
	@Column(name = "TR_AGENT_NO", precision = 38, scale = 0)
	private Long trAgentNo;
	
	// 代理商划款ID;
	@Column(name = "TR_AS_ID", nullable = false, precision = 38, scale = 0)
	private Long trAsId;

	// 代理商划款状态; 0: 未划款; 1:已制表 ; 2:已划款
	@Column(name = "TR_AS_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer trAsStatus;

	// 查询号 银行返回;
	@Column(name = "TR_QUERYNO")
	private String trQueryno;

	// 银行交易授权号 银行返回;
	@Column(name = "TR_AUTHORIZELD")
	private String trAuthorizeld;

	// 批次号 银行返回;
	@Column(name = "TR_BATCHNO")
	private String trBatchno;

	// 终端号 银行返回;
	@Column(name = "TR_TERMINALNO")
	private String trTerminalno;

	// 1 : DCC ; 0 : EDC;
	@Column(name = "TR_ISDCC", nullable = false, precision = 38, scale = 0)
	private Integer trIsdcc;

	// 划款审单批次号
	@Column(name = "TR_TS_BATCH", nullable = false, precision = 38, scale = 0)
	private Long trTsBatch;

	// 1 : 是; 0 : 否;
	@Column(name = "TR_ISTSCHECK", nullable = false, precision = 38, scale = 0)
	private Integer trIstscheck;

	// 保证金划审单批次号
	@Column(name = "TR_RS_BATCH", precision = 38, scale = 0)
	private Long trRsBatch;

	// 1 : 是; 0 : 否;
	@Column(name = "TR_ISRSCHECK", nullable = false, precision = 38, scale = 0)
	private Integer trIsrscheck;

	// 风控需要 0 : 未审核; 1 : 确认无风险;2: 确认有风险;
	@Column(name = "TR_ISDAYCHECK", nullable = false, precision = 38, scale = 0)
	private Integer trIsdaycheck;

	// 拒付申述 -2 未申诉 -1 申诉中  0:失败;1:成功 ;
	@Column(name = "TR_ISEXPLAIN", precision = 38, scale = 0)
	private Integer trIsexplain;
//1 : 是; 0 : 否;伪冒状态
	@Column(name = "TR_ISFRAUD", precision = 38, scale = 0)
	private Integer trIsfraud;
	//1 : 是; 0 : 否; 投诉状态
	@Column(name = "TR_ISCOMPLAIN", precision = 38, scale = 0)
	private Integer trIscomplain;

	// 1:.2.5 方接口 ;2: 2 方接口;3:3方接口
	@Column(name = "TR_INF_TYPE", nullable = false, precision = 38, scale = 0)
	private Integer trInfType;

	// 备注;
	@Column(name = "TR_REMARK")
	private String trRemark;
	
	//是否锁定 1 : 是; 0 : 否
	@Column(name = "TR_ISLOCK", nullable = true, precision = 38, scale = 0)
	private Integer trIsLock;		
	
	//代理商回佣率
	@Column(name = "TR_AGENT_RATE", nullable = true, precision = 38, scale = 0)
	private BigDecimal trAgentRate;	

	//代理商单笔手续费币种
	@Column(name = "TR_AGENT_SPP_CURRENCY")
	private String trAgentSPPCurrency;
	
	//代理商单笔手续费金额
	@Column(name = "TR_AGENT_SPP", precision = 18)
	private BigDecimal trAgentSPP;
	
//	//是否返还商户异常金额手续费  1 : 是; 0 : 否; 
//	@Column(name = "TR_UNNORMALBACK_MER")	
//	private Integer trUnormalBackMER;
//
//
//	//是否返还代理商异常金额手续费 1 : 是; 0 : 否;
//	@Column(name = "TR_UNNORMALBACK_AGENT")	
//	private Integer trUnormalBackAgent;	
//	
//	//是否收取商户单笔手续费 1 : 是; 0 : 否;
//	@Column(name = "TR_FEEBACK_MER")	
//	private Integer trFEEBackMER;		
//	
//	//是否给予代理商单笔手续费 1 : 是; 0 : 否;
//	@Column(name = "TR_FEEBACK_AGENT")	
//	private Integer trFEEBackAgent;		
//	
//	//银行是否收取单笔手续费 1 : 是; 0 : 否;
//	@Column(name = "TR_FEEBACK_BANK")	
//	private Integer trFEEBackBank;			
//	
//	//银行是否返还代理商异常金额手续费 1 : 是; 0 : 否; 
//	@Column(name = "TR_UNNORMALBACK_BANK")	
//	private Integer trUnormalBackBank;		
	
	//新增15个属性	
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
	
	//失败订单是否收取手续费(针对通道) 1 :是 0:否
	@Column(name = "TR_FEE_FAIL_CHA")		
	private Integer trFEEFailCHA;
	
	//划款前成功订单全额异常是否收取手续费(针对通道) 1 :是 0:否
	@Column(name = "TR_FEE_SUCCESS_CHA")		
	private Integer trFEESuccessCHA;
	
	//划款后成功订单全额异常是否收取手续费(针对通道) 1 :是 0:否
	@Column(name = "TR_FEE_SUCCESS_AFTER_CHA")		
	private Integer trFEESuccessAfterCHA;
	
	//划款前是否收取异常金额的手续费(针对通道) 1 :是 0:否
	@Column(name = "TR_IS_BACK_CHA")		
	private Integer trIsBackCHA;
	
	//划款后是否收取异常金额的手续费(针对通道) 1 :是 0:否
	@Column(name = "TR_IS_BACK_AFTER_CHA")		
	private Integer trIsBackAfterCHA;
	
	
	//结算银行单笔手续费币种
	@Column(name = "TR_BANK_SPP_CURRENCY")	
	private String trBankSPPCurrency;		
	
	//结算银行单笔手续费金额 (Single pen poundage ==SPP 结算银行单笔手续费金额)
	@Column(name = "TR_BANK_SPP", precision = 18)
	private BigDecimal trBankSPP;		
	
	//申诉成功金额
	@Column(name = "TR_EXP_AMOUNT", precision = 18)
	private BigDecimal trEXPAmount;

	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "traderecord")
	private Set<UnnormalOperate> unnormalOperates = new HashSet<UnnormalOperate>(
			0);
	//划款次数 1 首次 2补划
	@Transient
	private Integer hktimes;
	
	
	// 卡种
	@Column(name = "TR_CARDTYPE", precision = 38, scale = 0)
	private Long trCardtype;
	
	// 交易序列号 提交到银行的唯一值 如：YESPAYMENTS为12位 CHINAPAY为16位
	@Column(name = "TR_REFERENCE")
	private String trReference;

	/**
	 * @return the trCardtype
	 */
	public Long getTrCardtype() {
		return trCardtype;
	}

	/**
	 * @param trCardtype the trCardtype to set
	 */
	public void setTrCardtype(Long trCardtype) {
		this.trCardtype = trCardtype;
	}

	public Traderecord() {
	}
	
	public Traderecord(String trNo) {
		this.trNo = trNo;
	}
	/**
	 * @return the trAgentNo
	 */
	public Long getTrAgentNo() {
		return trAgentNo;
	}
	/**
	 * @param trAgentNo the trAgentNo to set
	 */
	public void setTrAgentNo(Long trAgentNo) {
		this.trAgentNo = trAgentNo;
	}
	/**
	 * @return the hktimes
	 */
	public Integer getHktimes() {
		return hktimes;
	}
	/**
	 * @param hktimes the hktimes to set
	 */
	public void setHktimes(Integer hktimes) {
		this.hktimes = hktimes;
	}
	/**
	 * @return the trIsfraud
	 */
	public Integer getTrIsfraud() {
		return trIsfraud;
	}
	/**
	 * @param trIsfraud the trIsfraud to set
	 */
	public void setTrIsfraud(Integer trIsfraud) {
		this.trIsfraud = trIsfraud;
	}
	/**
	 * @return the trIscomplain
	 */
	public Integer getTrIscomplain() {
		return trIscomplain;
	}
	/**
	 * @param trIscomplain the trIscomplain to set
	 */
	public void setTrIscomplain(Integer trIscomplain) {
		this.trIscomplain = trIscomplain;
	}
	/**
	 * @return the unnormalOperates
	 */
	public Set<UnnormalOperate> getUnnormalOperates() {
		return unnormalOperates;
	}
	/**
	 * @param unnormalOperates the unnormalOperates to set
	 */
	public void setUnnormalOperates(Set<UnnormalOperate> unnormalOperates) {
		this.unnormalOperates = unnormalOperates;
	}
	/**
	 * @return the trIsLock
	 */
	public Integer getTrIsLock() {
		return trIsLock;
	}
	/**
	 * @param trIsLock the trIsLock to set
	 */
	public void setTrIsLock(Integer trIsLock) {
		this.trIsLock = trIsLock;
	}

	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "traderecord")
	private Set<TrackingnoBills> trackingnoBillses = new HashSet<TrackingnoBills>(
			0);

	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "traderecord")
	private Set<ChannelDiffinfo> channelDiffinfo = new HashSet<ChannelDiffinfo>(0);
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "traderecord")
	private Set<UnNormalProcess> unNormalProcesses = new HashSet<UnNormalProcess>(
			0);

//	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "traderecord")
//	private Set<Check> checks = new HashSet<Check>(0);

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

	public String getTrMerOrderno() {
		return trMerOrderno;
	}

	public void setTrMerOrderno(String trMerOrderno) {
		this.trMerOrderno = trMerOrderno;
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

	public String getTrBankcurrency() {
		return trBankcurrency;
	}

	public void setTrBankcurrency(String trBankcurrency) {
		this.trBankcurrency = trBankcurrency;
	}

	public BigDecimal getTrBankamout() {
		return trBankamout;
	}

	public void setTrBankamout(BigDecimal trBankamout) {
		this.trBankamout = trBankamout;
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

	public Integer getTrIsdelay() {
		return trIsdelay;
	}

	public void setTrIsdelay(Integer trIsdelay) {
		this.trIsdelay = trIsdelay;
	}

	public BigDecimal getTrChaRate() {
		return trChaRate;
	}

	public void setTrChaRate(BigDecimal trChaRate) {
		this.trChaRate = trChaRate;
	}

	public String getTrChaSettBank() {
		return trChaSettBank;
	}

	public void setTrChaSettBank(String trChaSettBank) {
		this.trChaSettBank = trChaSettBank;
	}

	public String getTrBankorderno() {
		return trBankorderno;
	}

	public void setTrBankorderno(String trBankorderno) {
		this.trBankorderno = trBankorderno;
	}

	

	/**
	 * @return the trBankreturncode
	 */
	public String getTrBankreturncode() {
		return trBankreturncode;
	}

	/**
	 * @param trBankreturncode the trBankreturncode to set
	 */
	public void setTrBankreturncode(String trBankreturncode) {
		this.trBankreturncode = trBankreturncode;
	}

	public String getTrBankinfo() {
		return trBankinfo;
	}

	public void setTrBankinfo(String trBankinfo) {
		this.trBankinfo = trBankinfo;
	}

	public Date getTrPaystarttime() {
		return trPaystarttime;
	}

	public void setTrPaystarttime(Date trPaystarttime) {
		this.trPaystarttime = trPaystarttime;
	}

	public Date getTrPayendtime() {
		return trPayendtime;
	}

	public void setTrPayendtime(Date trPayendtime) {
		this.trPayendtime = trPayendtime;
	}

	public Date getTrDatetime() {
		return trDatetime;
	}

	public void setTrDatetime(Date trDatetime) {
		this.trDatetime = trDatetime;
	}

	public Date getTrBankdatetime() {
		return trBankdatetime;
	}

	public void setTrBankdatetime(Date trBankdatetime) {
		this.trBankdatetime = trBankdatetime;
	}

	public String getTrReturnurl() {
		return trReturnurl;
	}

	public void setTrReturnurl(String trReturnurl) {
		this.trReturnurl = trReturnurl;
	}

	public String getTrWebsite() {
		return trWebsite;
	}

	public void setTrWebsite(String trWebsite) {
		this.trWebsite = trWebsite;
	}

	public String getTrSubmiturl() {
		return trSubmiturl;
	}

	public void setTrSubmiturl(String trSubmiturl) {
		this.trSubmiturl = trSubmiturl;
	}

	public Integer getTrChecked() {
		return trChecked;
	}

	public void setTrChecked(Integer trChecked) {
		this.trChecked = trChecked;
	}

	public Date getTrCheckdatetime() {
		return trCheckdatetime;
	}

	public void setTrCheckdatetime(Date trCheckdatetime) {
		this.trCheckdatetime = trCheckdatetime;
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

	public Integer getTrDelivery() {
		return trDelivery;
	}

	public void setTrDelivery(Integer trDelivery) {
		this.trDelivery = trDelivery;
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

	public BigDecimal getTrUnsettAmount() {
		return trUnsettAmount;
	}

	public void setTrUnsettAmount(BigDecimal trUnsettAmount) {
		this.trUnsettAmount = trUnsettAmount;
	}

	public BigDecimal getTrSettAmount() {
		return trSettAmount;
	}

	public void setTrSettAmount(BigDecimal trSettAmount) {
		this.trSettAmount = trSettAmount;
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

	public Long getTrRsId() {
		return trRsId;
	}

	public void setTrRsId(Long trRsId) {
		this.trRsId = trRsId;
	}

	public Integer getTrRsStatus() {
		return trRsStatus;
	}

	public void setTrRsStatus(Integer trRsStatus) {
		this.trRsStatus = trRsStatus;
	}

	public Long getTrAsId() {
		return trAsId;
	}

	public void setTrAsId(Long trAsId) {
		this.trAsId = trAsId;
	}

	public Integer getTrAsStatus() {
		return trAsStatus;
	}

	public void setTrAsStatus(Integer trAsStatus) {
		this.trAsStatus = trAsStatus;
	}

	public String getTrQueryno() {
		return trQueryno;
	}

	public void setTrQueryno(String trQueryno) {
		this.trQueryno = trQueryno;
	}

	public String getTrAuthorizeld() {
		return trAuthorizeld;
	}

	public void setTrAuthorizeld(String trAuthorizeld) {
		this.trAuthorizeld = trAuthorizeld;
	}

	public String getTrBatchno() {
		return trBatchno;
	}

	public void setTrBatchno(String trBatchno) {
		this.trBatchno = trBatchno;
	}

	public String getTrTerminalno() {
		return trTerminalno;
	}

	public void setTrTerminalno(String trTerminalno) {
		this.trTerminalno = trTerminalno;
	}

	public Integer getTrIsdcc() {
		return trIsdcc;
	}

	public void setTrIsdcc(Integer trIsdcc) {
		this.trIsdcc = trIsdcc;
	}

	public Long getTrTsBatch() {
		return trTsBatch;
	}

	public void setTrTsBatch(Long trTsBatch) {
		this.trTsBatch = trTsBatch;
	}

	public Integer getTrIstscheck() {
		return trIstscheck;
	}

	public void setTrIstscheck(Integer trIstscheck) {
		this.trIstscheck = trIstscheck;
	}

	public Long getTrRsBatch() {
		return trRsBatch;
	}

	public void setTrRsBatch(Long trRsBatch) {
		this.trRsBatch = trRsBatch;
	}

	public Integer getTrIsrscheck() {
		return trIsrscheck;
	}

	public void setTrIsrscheck(Integer trIsrscheck) {
		this.trIsrscheck = trIsrscheck;
	}

	public Integer getTrIsdaycheck() {
		return trIsdaycheck;
	}

	public void setTrIsdaycheck(Integer trIsdaycheck) {
		this.trIsdaycheck = trIsdaycheck;
	}

	public Integer getTrIsexplain() {
		return trIsexplain;
	}

	public void setTrIsexplain(Integer trIsexplain) {
		this.trIsexplain = trIsexplain;
	}



	public Integer getTrInfType() {
		return trInfType;
	}

	public void setTrInfType(Integer trInfType) {
		this.trInfType = trInfType;
	}

	public String getTrRemark() {
		return trRemark;
	}

	public void setTrRemark(String trRemark) {
		this.trRemark = trRemark;
	}

	public Set<TrackingnoBills> getTrackingnoBillses() {
		return trackingnoBillses;
	}

	public void setTrackingnoBillses(Set<TrackingnoBills> trackingnoBillses) {
		this.trackingnoBillses = trackingnoBillses;
	}

	public Set<ChannelDiffinfo> getChannelDiffinfo() {
		return channelDiffinfo;
	}

	public void setChannelDiffinfo(Set<ChannelDiffinfo> channelDiffinfo) {
		this.channelDiffinfo = channelDiffinfo;
	}

	public Set<UnNormalProcess> getUnNormalProcesses() {
		return unNormalProcesses;
	}

	public void setUnNormalProcesses(Set<UnNormalProcess> unNormalProcesses) {
		this.unNormalProcesses = unNormalProcesses;
	}

//	public Set<Check> getChecks() {
//		return checks;
//	}
//
//	public void setChecks(Set<Check> checks) {
//		this.checks = checks;
//	}
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
	 * @return the trBankSPPCurrency
	 */
	public String getTrBankSPPCurrency() {
		return trBankSPPCurrency;
	}
	/**
	 * @param trBankSPPCurrency the trBankSPPCurrency to set
	 */
	public void setTrBankSPPCurrency(String trBankSPPCurrency) {
		this.trBankSPPCurrency = trBankSPPCurrency;
	}
	/**
	 * @return the trBankSPP
	 */
	public BigDecimal getTrBankSPP() {
		return trBankSPP;
	}
	/**
	 * @param trBankSPP the trBankSPP to set
	 */
	public void setTrBankSPP(BigDecimal trBankSPP) {
		this.trBankSPP = trBankSPP;
	}	
	
	/**
	 * @return the trFEEFailMER
	 */
	public Integer getTrFEEFailMER() {
		return trFEEFailMER;
	}
	/**
	 * @param trFEEFailMER the trFEEFailMER to set
	 */
	public void setTrFEEFailMER(Integer trFEEFailMER) {
		this.trFEEFailMER = trFEEFailMER;
	}
	/**
	 * @return the trFEESuccessMER
	 */
	public Integer getTrFEESuccessMER() {
		return trFEESuccessMER;
	}
	/**
	 * @param trFEESuccessMER the trFEESuccessMER to set
	 */
	public void setTrFEESuccessMER(Integer trFEESuccessMER) {
		this.trFEESuccessMER = trFEESuccessMER;
	}
	/**
	 * @return the trFEESuccessAfterMER
	 */
	public Integer getTrFEESuccessAfterMER() {
		return trFEESuccessAfterMER;
	}
	/**
	 * @param trFEESuccessAfterMER the trFEESuccessAfterMER to set
	 */
	public void setTrFEESuccessAfterMER(Integer trFEESuccessAfterMER) {
		this.trFEESuccessAfterMER = trFEESuccessAfterMER;
	}
	/**
	 * @return the trIsBackMER
	 */
	public Integer getTrIsBackMER() {
		return trIsBackMER;
	}
	/**
	 * @param trIsBackMER the trIsBackMER to set
	 */
	public void setTrIsBackMER(Integer trIsBackMER) {
		this.trIsBackMER = trIsBackMER;
	}
	/**
	 * @return the trIsBackAfterMER
	 */
	public Integer getTrIsBackAfterMER() {
		return trIsBackAfterMER;
	}
	/**
	 * @param trIsBackAfterMER the trIsBackAfterMER to set
	 */
	public void setTrIsBackAfterMER(Integer trIsBackAfterMER) {
		this.trIsBackAfterMER = trIsBackAfterMER;
	}
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
	 * @return the trFEEFailCHA
	 */
	public Integer getTrFEEFailCHA() {
		return trFEEFailCHA;
	}
	/**
	 * @param trFEEFailCHA the trFEEFailCHA to set
	 */
	public void setTrFEEFailCHA(Integer trFEEFailCHA) {
		this.trFEEFailCHA = trFEEFailCHA;
	}
	/**
	 * @return the trFEESuccessCHA
	 */
	public Integer getTrFEESuccessCHA() {
		return trFEESuccessCHA;
	}
	/**
	 * @param trFEESuccessCHA the trFEESuccessCHA to set
	 */
	public void setTrFEESuccessCHA(Integer trFEESuccessCHA) {
		this.trFEESuccessCHA = trFEESuccessCHA;
	}
	/**
	 * @return the trFEESuccessAfterCHA
	 */
	public Integer getTrFEESuccessAfterCHA() {
		return trFEESuccessAfterCHA;
	}
	/**
	 * @param trFEESuccessAfterCHA the trFEESuccessAfterCHA to set
	 */
	public void setTrFEESuccessAfterCHA(Integer trFEESuccessAfterCHA) {
		this.trFEESuccessAfterCHA = trFEESuccessAfterCHA;
	}
	/**
	 * @return the trIsBackCHA
	 */
	public Integer getTrIsBackCHA() {
		return trIsBackCHA;
	}
	/**
	 * @param trIsBackCHA the trIsBackCHA to set
	 */
	public void setTrIsBackCHA(Integer trIsBackCHA) {
		this.trIsBackCHA = trIsBackCHA;
	}
	/**
	 * @return the trIsBackAfterCHA
	 */
	public Integer getTrIsBackAfterCHA() {
		return trIsBackAfterCHA;
	}
	/**
	 * @param trIsBackAfterCHA the trIsBackAfterCHA to set
	 */
	public void setTrIsBackAfterCHA(Integer trIsBackAfterCHA) {
		this.trIsBackAfterCHA = trIsBackAfterCHA;
	}	
	
	/**
	 * @return the trEXPAmount
	 */
	public BigDecimal getTrEXPAmount() {
		return trEXPAmount;
	}
	/**
	 * @param trEXPAmount the trEXPAmount to set
	 */
	public void setTrEXPAmount(BigDecimal trEXPAmount) {
		this.trEXPAmount = trEXPAmount;
	}

	/**
	 * @return the trBanktradetime
	 */
	public Date getTrBanktradetime() {
		return trBanktradetime;
	}

	/**
	 * @param trBanktradetime the trBanktradetime to set
	 */
	public void setTrBanktradetime(Date trBanktradetime) {
		this.trBanktradetime = trBanktradetime;
	}

	public String getTrReference() {
		return trReference;
	}

	public void setTrReference(String trReference) {
		this.trReference = trReference;
	}

	public String getTrPageReturnurl() {
		return trPageReturnurl;
	}

	public void setTrPageReturnurl(String trPageReturnurl) {
		this.trPageReturnurl = trPageReturnurl;
	}
	

	public String getTrIp() {
		return this.trIp;
	}

	public void setTrIp(String trIp) {
		this.trIp = trIp;
	}

	@Override
	public String toString() {
		return "Traderecord [trNo=" + trNo + ", trMerOrderno=" + trMerOrderno
				+ ", trMerNo=" + trMerNo + ", trGwNo=" + trGwNo
				+ ", trCurrency=" + trCurrency + ", trAmount=" + trAmount
				+ ", trStatus=" + trStatus + ", trBankCode=" + trBankCode
				+ ", trDatetime=" + trDatetime + "]";
	}
}