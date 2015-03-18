package com.jinxin.model.persistence;

// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: 记录商户信息</p>
 * <p>Description: CCPS_MERCHANT映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MERCHANT")
public class Merchant implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	// 商户号
	@Id
	@Column(name = "MER_NO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MERCHANT_MERNO_SEQ")      
    @SequenceGenerator(name="CCPS_MERCHANT_MERNO_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MERCHANT_MERNO_SEQ")
	private Long merNo;
	
	// 存储的是所属行业字典所对应ID号
	@ManyToOne
	@JoinColumn(name = "MER_BUS_ID", nullable = false)
	private Business business;
	
	// 自增(1,1)
	@Column(name = "MER_ID", nullable = false, precision = 38, scale = 0) 
	private Long merId;
	
	// 商户名称
	@Column(name = "MER_NAME", nullable = false)
	private String merName;
	
	// 1 : 身份证; 2 : 营业执照
	@Column(name = "MER_CER_TYPE", precision = 38, scale = 0)
	private Long merCerType;
	
	// 证件号
	@Column(name = "MER_CER_NO")
	private String merCerNo;
	
	// 1 :海外; 2:国内
	@Column(name = "MER_AREA", nullable = false, precision = 38, scale = 0)
	private Long merArea;
	
	// 省名或者州
	@Column(name = "MER_STATE")
	private String merState;
	
	// 城市名
	@Column(name = "MER_CITY")
	private String merCity;
	
	// 地址
	@Column(name = "MER_ADDRESS")
	private String merAddress;
	
	// 联系人
	@Column(name = "MER_LINKMAN", nullable = false)
	private String merLinkman;
	
	// 联系电话
	@Column(name = "MER_LINKPHONE", nullable = false)
	private String merLinkphone;
	
	// 传真
	@Column(name = "MER_FAX")
	private String merFax;
	
	// 商户官网地址
	@Column(name = "MER_WEBSITE", nullable = false)
	private String merWebsite;
	
	// 邮箱地址
	@Column(name = "MER_EMAIL", nullable = false)
	private String merEmail;
	
	// -2 : 删除 ; -1 : 停用; 0 : 未激活 ; 1 : 正常
	@Column(name = "MER_STATUS", nullable = false, precision = 38, scale = 0)
	private Long merStatus;
	
	// 交易结算周期; 单位:天
	@Column(name = "MER_TRADEDAYS", precision = 38, scale = 0)
	private Integer merTradedays;
	
	// 保证金结算周期;单位:天
	@Column(name = "MER_RESERVEDAYS", nullable = false, precision = 38, scale = 0)
	private Integer merReservedays;
	
	// 共10级,默认第5级; 级数越大信誉度越高, 反之越低
	@Column(name = "MER_LEVEL", nullable = false, precision = 38, scale = 0)
	private Long merLevel;
	
	// 注册日期
	@Column(name = "MER_REGDATE", nullable = false)
	private Date merRegdate;
	
	// 合同起始日期
	@Column(name = "MER_PACTSTDATE")
	private Date merPactstdate;
	
	// 合同结束日期
	@Column(name = "MER_PACTENDATE")
	private Date merPactendate;
	
	// 指本公司的销售人员
	@Column(name = "MER_SALES")
	private String merSales;
	
	// 0: 只能上传图片;1:只能填写跟踪单号;2: 既能上传图片又能填写跟踪单号 
	@Column(name = "MER_UPLOADTYPE", precision = 38, scale = 0)
	private Long merUploadtype;
	
	// -2未提交;-1:待审核;1 :审核成功;0 审核失败 指 海外商户新注册时填写的用户调查表是否通过审核
	@Column(name = "MER_ISCHECK", nullable = false, precision = 38, scale = 0)
	private Long merIscheck;
	
	// 审核失败原因
	@Column(name = "MER_FAILREASON")
	private String merFailreason;
	
	// 上次勾兑时间
	@Column(name = "MER_LASTCHECKTIME")
	private Date merLastchecktime;
	
	// 备注
	@Column(name = "MER_REMARK")
	private String merRemark;
	
	@Column(name="MER_LOGIN_NAME")
	private String merLoginName;
	
	
	// 商户号
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "merchant")
//	@PersistenceContext(type=PersistenceContextType.EXTENDED, unitName="default")
	private Set<MerBankAccount> merBankAccounts = new HashSet<MerBankAccount>(0);
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "merchant")
	private Set<Gateway> gatewaies = new HashSet<Gateway>(0);
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "merchant")
	private Set<MerCheckIp> merCheckIps = new HashSet<MerCheckIp>(0);
	
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "merchant")
	private Set<AgentMer> agentMers = new HashSet<AgentMer>(0);

	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "merchant")
	private Set<SysMerAdmin> sysMerAdmins = new HashSet<SysMerAdmin>(0);

	public Merchant() {
	}

	public Merchant(Long merNo) {
		super();
		this.merNo = merNo;
	}



	public Long getMerNo() {
		return merNo;
	}

	public void setMerNo(Long merNo) {
		this.merNo = merNo;
	}

	public Business getBusiness() {
		return business;
	}

	public void setBusiness(Business business) {
		this.business = business;
	}

	public Long getMerId() {
		return merId;
	}

	public void setMerId(Long merId) {
		this.merId = merId;
	}

	public String getMerName() {
		return merName;
	}

	public void setMerName(String merName) {
		this.merName = merName;
	}

	public Long getMerCerType() {
		return merCerType;
	}

	public void setMerCerType(Long merCerType) {
		this.merCerType = merCerType;
	}

	public String getMerCerNo() {
		return merCerNo;
	}

	public void setMerCerNo(String merCerNo) {
		this.merCerNo = merCerNo;
	}

	public Long getMerArea() {
		return merArea;
	}

	public void setMerArea(Long merArea) {
		this.merArea = merArea;
	}

	public String getMerState() {
		return merState;
	}

	public void setMerState(String merState) {
		this.merState = merState;
	}

	public String getMerCity() {
		return merCity;
	}

	public void setMerCity(String merCity) {
		this.merCity = merCity;
	}

	public String getMerAddress() {
		return merAddress;
	}

	public void setMerAddress(String merAddress) {
		this.merAddress = merAddress;
	}

	public String getMerLinkman() {
		return merLinkman;
	}

	public void setMerLinkman(String merLinkman) {
		this.merLinkman = merLinkman;
	}

	public String getMerLinkphone() {
		return merLinkphone;
	}

	public void setMerLinkphone(String merLinkphone) {
		this.merLinkphone = merLinkphone;
	}

	public String getMerFax() {
		return merFax;
	}

	public void setMerFax(String merFax) {
		this.merFax = merFax;
	}

	public String getMerWebsite() {
		return merWebsite;
	}

	public void setMerWebsite(String merWebsite) {
		this.merWebsite = merWebsite;
	}

	public String getMerEmail() {
		return merEmail;
	}

	public void setMerEmail(String merEmail) {
		this.merEmail = merEmail;
	}

	public Long getMerStatus() {
		return merStatus;
	}

	public void setMerStatus(Long merStatus) {
		this.merStatus = merStatus;
	}

	public Integer getMerTradedays() {
		return merTradedays;
	}

	public void setMerTradedays(Integer merTradedays) {
		this.merTradedays = merTradedays;
	}

	public Integer getMerReservedays() {
		return merReservedays;
	}

	public void setMerReservedays(Integer merReservedays) {
		this.merReservedays = merReservedays;
	}

	public Long getMerLevel() {
		return merLevel;
	}

	public void setMerLevel(Long merLevel) {
		this.merLevel = merLevel;
	}

	public Date getMerRegdate() {
		return merRegdate;
	}

	public void setMerRegdate(Date merRegdate) {
		this.merRegdate = merRegdate;
	}

	public Date getMerPactstdate() {
		return merPactstdate;
	}

	public void setMerPactstdate(Date merPactstdate) {
		this.merPactstdate = merPactstdate;
	}

	public Date getMerPactendate() {
		return merPactendate;
	}

	public void setMerPactendate(Date merPactendate) {
		this.merPactendate = merPactendate;
	}

	public String getMerSales() {
		return merSales;
	}

	public void setMerSales(String merSales) {
		this.merSales = merSales;
	}

	public Long getMerUploadtype() {
		return merUploadtype;
	}

	public void setMerUploadtype(Long merUploadtype) {
		this.merUploadtype = merUploadtype;
	}

	public Long getMerIscheck() {
		return merIscheck;
	}

	public void setMerIscheck(Long merIscheck) {
		this.merIscheck = merIscheck;
	}

	public String getMerFailreason() {
		return merFailreason;
	}

	public void setMerFailreason(String merFailreason) {
		this.merFailreason = merFailreason;
	}

	public Date getMerLastchecktime() {
		return merLastchecktime;
	}

	public void setMerLastchecktime(Date merLastchecktime) {
		this.merLastchecktime = merLastchecktime;
	}

	public String getMerRemark() {
		return merRemark;
	}

	public void setMerRemark(String merRemark) {
		this.merRemark = merRemark;
	}

	public Set<MerBankAccount> getMerBankAccounts() {
		return merBankAccounts;
	}

	public void setMerBankAccounts(Set<MerBankAccount> merBankAccounts) {
		this.merBankAccounts = merBankAccounts;
	}

	public Set<Gateway> getGatewaies() {
		return gatewaies;
	}

	public void setGatewaies(Set<Gateway> gatewaies) {
		this.gatewaies = gatewaies;
	}

	public Set<MerCheckIp> getMerCheckIps() {
		return merCheckIps;
	}

	public void setMerCheckIps(Set<MerCheckIp> merCheckIps) {
		this.merCheckIps = merCheckIps;
	}

	public Set<AgentMer> getAgentMers() {
		return agentMers;
	}

	public void setAgentMers(Set<AgentMer> agentMers) {
		this.agentMers = agentMers;
	}

	public Set<SysMerAdmin> getSysMerAdmins() {
		return sysMerAdmins;
	}

	public void setSysMerAdmins(Set<SysMerAdmin> sysMerAdmins) {
		this.sysMerAdmins = sysMerAdmins;
	}

	public String getMerLoginName() {
		return merLoginName;
	}

	public void setMerLoginName(String merLoginName) {
		this.merLoginName = merLoginName;
	}

}