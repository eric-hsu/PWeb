package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * Channel entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CHANNEL")
public class Channel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	// 通道代码
	// 自增(1,1);
	@Id
	@Column(name = "CHA_CODE", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CHA_CODE_SEQ")
	@SequenceGenerator(name = "CCPS_CHA_CODE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CHA_CODE_SEQ")
	private Long chaCode;
	// 银行代码
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "CHA_BANK_CODE", nullable = false)
	private Bank bank;

	// 自增(1,1);
	@Column(name = "CHA_ID", nullable = false, precision = 38, scale = 0)
	private Long chaId;
	// 通道名称
	@Column(name = "CHA_NAME", nullable = false)
	private String chaName;
	// 通道商户号,指银行给我们分配的商户号,即登入银行网站后台的账号.(支付、勾兑共用)
	@Column(name = "CHA_MERNO", precision = 38, scale = 0)
	private String chaMerno;	

	// 通道用户名(自动勾兑使用)
	@Column(name = "CHA_VPC_USER")
	private String chaVpcUser;
	// 通道进入码(支付、勾兑共用)
	@Column(name = "CHA_VPC_ACCESSCODE")
	private String chaVpcAccesscode;
	// 通道用户名密码(自动勾兑使用)
	@Column(name = "CHA_VPC_PASSWORD")
	private String chaVpcPassword;
	// 通道安全码(支付使用)
	@Column(name = "CHA_SECURE_SECRET")
	private String chaSecureSecret;
	// 1 : 单币种 ; 2: 多币种;
	@Column(name = "CHA_CURRENCYTYPE", nullable = false, precision = 38, scale = 0)
	private Long chaCurrencytype;
	// 如::USD、CNY、EUR,从字典库读出
	@Column(name = "CHA_CURRENCY", nullable = false)
	private String chaCurrency;
	
	// 如::1 借记卡，2 信用卡 
	@Column(name = "CHA_CARDSUPPORTTYPE", nullable = false)
	private String chaCardSupportType;
	
	// 通道类型：0 个人网银支付 ， 1 快捷支付 ，3 企业网银支付
	@Column(name = "CHA_TYPE", nullable = false)
	private String chaType;

	// 指本公司与银行的结算账号
	@Column(name = "CHA_SETTLEMENT_BANK")
	private String chaSettlementBank;
	// 指本通道在银行后台管理员帐号
	@Column(name = "CHA_ADMIN_ACCOUNT", nullable = false)
	private String chaAdminAccount;
	// 指本通道在银行后台管理员密码
	@Column(name = "CHA_ADMIN_PWD", nullable = false)
	private String chaAdminPwd;
	// 1 : 正常; -1: 停用; 通道状态
	@Column(name = "CHA_STAUTS", nullable = false, precision = 38, scale = 0)
	private Long chaStauts;
	// 银行通道是否为3方接口 1 : 是; 0 : 否;
	@Column(name = "CHA_ISTHREEPARTY", precision = 38, scale = 0)
	private Long chaIsthreeparty;
	// 单笔手续费币种
	@Column(name = "CHA_FEECURRENCY")
	private String chaFeecurrency;
	// 单笔手续费金额
	@Column(name = "CHA_FEEAMOUNT", precision = 18)
	private BigDecimal chaFeeamount;
	// 失败订单是否收取手续费 1 :是 0:否
	@Column(name = "CHA_FEE_FAIL", precision = 38, scale = 0)
	private Long chaFeeFail;
	// 结算前成功订单全额异常是否收取手续费 1 :是 0:否
	@Column(name = "CHA_FEE_SUCCESS", precision = 38, scale = 0)
	private Long chaFeeSuccess;
	// 结算后成功订单全额异常是否收取手续费 1 :是 0:否
	@Column(name = "CHA_FEE_SUCCESS_AFTER", precision = 38, scale = 0)
	private Long chaFeeSuccessAfter;
	// 结算前是否收取异常金额的手续费 1 :是 0:否
	@Column(name = "CHA_IS_BACK", precision = 38, scale = 0)
	private Long chaIsBack;
	// 结算后是否收取异常金额的手续费 1 :是 0:否
	@Column(name = "CHA_IS_BACK_AFTER", precision = 38, scale = 0)
	private Long chaIsBackAfter;
	// 生效时间
	@Column(name = "CHA_EFFECT_DATE")
	private Date chaEffectDate;
	// 添加人
	@Column(name = "CHA_LOGIN_NAME", nullable = false)
	private String chaLoginName;
	// 添加时间
	@Column(name = "CHA_OPRTIME", nullable = false)
	private Date chaOprtime;
	// 备注
	@Column(name = "CHA_REMARK")
	private String chaRemark;
	
	// 通道标识
	@Column(name = "CHA_FALG")
	private String chaFalg;
	
	// 1 : 是; 0 : 否; 是否支持部分退款、拒付、冻结
	@Column(name = "CHA_PART", nullable = false, precision = 38, scale = 0)
	private Integer chaPart;
	
	// 卡种展示
	@Transient
	private String cardTypeshow;
	
	// 卡种表
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "channel")
	private Set<ChaCardtype> chaCardtypes = new HashSet<ChaCardtype>(0);

	public Channel() {
	}

	public Channel(Long chaCode) {
		this.chaCode = chaCode;
	}

	/**
	 * @return the chaCode
	 */
	public Long getChaCode() {
		return chaCode;
	}

	/**
	 * @param chaCode
	 *            the chaCode to set
	 */
	public void setChaCode(Long chaCode) {
		this.chaCode = chaCode;
	}

	/**
	 * @return the bank
	 */
	public Bank getBank() {
		return bank;
	}

	/**
	 * @param bank
	 *            the bank to set
	 */
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	/**
	 * @return the chaId
	 */
	public Long getChaId() {
		return chaId;
	}

	/**
	 * @param chaId
	 *            the chaId to set
	 */
	public void setChaId(Long chaId) {
		this.chaId = chaId;
	}

	/**
	 * @return the chaName
	 */
	public String getChaName() {
		return chaName;
	}

	/**
	 * @param chaName
	 *            the chaName to set
	 */
	public void setChaName(String chaName) {
		this.chaName = chaName;
	}


	/**
	 * @return the chaVpcUser
	 */
	public String getChaVpcUser() {
		return chaVpcUser;
	}

	/**
	 * @param chaVpcUser
	 *            the chaVpcUser to set
	 */
	public void setChaVpcUser(String chaVpcUser) {
		this.chaVpcUser = chaVpcUser;
	}

	/**
	 * @return the chaVpcAccesscode
	 */
	public String getChaVpcAccesscode() {
		return chaVpcAccesscode;
	}

	/**
	 * @param chaVpcAccesscode
	 *            the chaVpcAccesscode to set
	 */
	public void setChaVpcAccesscode(String chaVpcAccesscode) {
		this.chaVpcAccesscode = chaVpcAccesscode;
	}

	/**
	 * @return the chaVpcPassword
	 */
	public String getChaVpcPassword() {
		return chaVpcPassword;
	}

	/**
	 * @param chaVpcPassword
	 *            the chaVpcPassword to set
	 */
	public void setChaVpcPassword(String chaVpcPassword) {
		this.chaVpcPassword = chaVpcPassword;
	}

	/**
	 * @return the chaSecureSecret
	 */
	public String getChaSecureSecret() {
		return chaSecureSecret;
	}

	/**
	 * @param chaSecureSecret
	 *            the chaSecureSecret to set
	 */
	public void setChaSecureSecret(String chaSecureSecret) {
		this.chaSecureSecret = chaSecureSecret;
	}



	/**
	 * @return the chaCurrencytype
	 */
	public Long getChaCurrencytype() {
		return chaCurrencytype;
	}

	/**
	 * @param chaCurrencytype
	 *            the chaCurrencytype to set
	 */
	public void setChaCurrencytype(Long chaCurrencytype) {
		this.chaCurrencytype = chaCurrencytype;
	}

	/**
	 * @return the chaCurrency
	 */
	public String getChaCurrency() {
		return chaCurrency;
	}

	/**
	 * @param chaCurrency
	 *            the chaCurrency to set
	 */
	public void setChaCurrency(String chaCurrency) {
		this.chaCurrency = chaCurrency;
	}

	/**
	 * @return the chaSettlementBank
	 */
	public String getChaSettlementBank() {
		return chaSettlementBank;
	}

	/**
	 * @param chaSettlementBank
	 *            the chaSettlementBank to set
	 */
	public void setChaSettlementBank(String chaSettlementBank) {
		this.chaSettlementBank = chaSettlementBank;
	}

	/**
	 * @return the chaAdminAccount
	 */
	public String getChaAdminAccount() {
		return chaAdminAccount;
	}

	/**
	 * @param chaAdminAccount
	 *            the chaAdminAccount to set
	 */
	public void setChaAdminAccount(String chaAdminAccount) {
		this.chaAdminAccount = chaAdminAccount;
	}

	/**
	 * @return the chaAdminPwd
	 */
	public String getChaAdminPwd() {
		return chaAdminPwd;
	}

	/**
	 * @param chaAdminPwd
	 *            the chaAdminPwd to set
	 */
	public void setChaAdminPwd(String chaAdminPwd) {
		this.chaAdminPwd = chaAdminPwd;
	}

	/**
	 * @return the chaStauts
	 */
	public Long getChaStauts() {
		return chaStauts;
	}

	/**
	 * @param chaStauts
	 *            the chaStauts to set
	 */
	public void setChaStauts(Long chaStauts) {
		this.chaStauts = chaStauts;
	}

	/**
	 * @return the chaLoginName
	 */
	public String getChaLoginName() {
		return chaLoginName;
	}

	/**
	 * @param chaLoginName
	 *            the chaLoginName to set
	 */
	public void setChaLoginName(String chaLoginName) {
		this.chaLoginName = chaLoginName;
	}

	/**
	 * @return the chaOprtime
	 */
	public Date getChaOprtime() {
		return chaOprtime;
	}

	/**
	 * @param chaOprtime
	 *            the chaOprtime to set
	 */
	public void setChaOprtime(Date chaOprtime) {
		this.chaOprtime = chaOprtime;
	}

	/**
	 * @return the chaRemark
	 */
	public String getChaRemark() {
		return chaRemark;
	}

	/**
	 * @param chaRemark
	 *            the chaRemark to set
	 */
	public void setChaRemark(String chaRemark) {
		this.chaRemark = chaRemark;
	}

	/**
	 * @return the chaCardtypes
	 */
	public Set<ChaCardtype> getChaCardtypes() {
		return chaCardtypes;
	}

	/**
	 * @param chaCardtypes
	 *            the chaCardtypes to set
	 */
	public void setChaCardtypes(Set<ChaCardtype> chaCardtypes) {
		this.chaCardtypes = chaCardtypes;
	}
	/**
	 * @return the chaMerno
	 */
	public String getChaMerno() {
		return chaMerno;
	}

	/**
	 * @param chaMerno the chaMerno to set
	 */
	public void setChaMerno(String chaMerno) {
		this.chaMerno = chaMerno;
	}
	
	public Long getChaIsthreeparty() {
		return chaIsthreeparty;
	}

	public void setChaIsthreeparty(Long chaIsthreeparty) {
		this.chaIsthreeparty = chaIsthreeparty;
	}

	public String getChaFeecurrency() {
		return chaFeecurrency;
	}

	public void setChaFeecurrency(String chaFeecurrency) {
		this.chaFeecurrency = chaFeecurrency;
	}

	public BigDecimal getChaFeeamount() {
		return chaFeeamount;
	}

	public void setChaFeeamount(BigDecimal chaFeeamount) {
		this.chaFeeamount = chaFeeamount;
	}

	public Long getChaFeeFail() {
		return chaFeeFail;
	}

	public void setChaFeeFail(Long chaFeeFail) {
		this.chaFeeFail = chaFeeFail;
	}

	public Long getChaFeeSuccess() {
		return chaFeeSuccess;
	}

	public void setChaFeeSuccess(Long chaFeeSuccess) {
		this.chaFeeSuccess = chaFeeSuccess;
	}

	public Long getChaFeeSuccessAfter() {
		return chaFeeSuccessAfter;
	}

	public void setChaFeeSuccessAfter(Long chaFeeSuccessAfter) {
		this.chaFeeSuccessAfter = chaFeeSuccessAfter;
	}

	public Long getChaIsBack() {
		return chaIsBack;
	}

	public void setChaIsBack(Long chaIsBack) {
		this.chaIsBack = chaIsBack;
	}

	public Long getChaIsBackAfter() {
		return chaIsBackAfter;
	}

	public void setChaIsBackAfter(Long chaIsBackAfter) {
		this.chaIsBackAfter = chaIsBackAfter;
	}

	public Date getChaEffectDate() {
		return chaEffectDate;
	}

	public void setChaEffectDate(Date chaEffectDate) {
		this.chaEffectDate = chaEffectDate;
	}

	public String getChaFalg() {
		return chaFalg;
	}

	public void setChaFalg(String chaFalg) {
		this.chaFalg = chaFalg;
	}

	public String getChaCardSupportType() {
		return chaCardSupportType;
	}

	public void setChaCardSupportType(String chaCardSupportType) {
		this.chaCardSupportType = chaCardSupportType;
	}

	public String getChaType() {
		return chaType;
	}

	public void setChaType(String chaType) {
		this.chaType = chaType;
	}
	
	public Integer getChaPart() {
		return chaPart;
	}

	public void setChaPart(Integer chaPart) {
		this.chaPart = chaPart;
	}

	public String getCardTypeshow() {
		String cardtypes = this.chaCardSupportType;
		String name="";
		if("1".equals(chaType)){
			if(cardtypes.contains("0")){
				name = name +"借记卡"+";";
			}
			if(cardtypes.contains("1")){
				name = name +"信用卡"+";";
			}
		}
		return name;
	}

	public void setCardTypeshow(String cardTypeshow) {
		this.cardTypeshow = cardTypeshow;
	}
}