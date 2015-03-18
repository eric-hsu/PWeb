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

/**
 * Agent entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_AGENT")
public class Agent implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// 代理商号
	@Id
	@Column(name = "AGENT_NO", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AGENTNO_SEQ")
	@SequenceGenerator(name = "CCPS_AGENTNO_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AGENTNO_SEQ")
	private Long agentNo;
	
	// 表自增列
	@Column(name = "AGENT_ID", nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AGENT_SEQ")
	@SequenceGenerator(name = "CCPS_AGENT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AGENT_SEQ")
	private Long agentId;

	// 代理商名称;
	@Column(name = "AGENT_NAME", nullable = false)
	private String agentName;
	// 代理商登陆名;
	@Column(name = "AGENT_LOGINNAME", nullable = false)
	private String agentLoginname;
	// 代理商登陆密码;
	@Column(name = "AGENT_LOGINPWD", nullable = false)
	private String agentLoginpwd;
	// 联系人;
	@Column(name = "AGENT_LINKMAN")
	private String agentLinkman;
	// 联系电话;
	@Column(name = "AGENT_TEL")
	private String agentTel;
	// 联系地址;
	@Column(name = "AGENT_ADDRESS")
	private String agentAddress;
	// 代理商网站;
	@Column(name = "AGENT_WEBSIT")
	private String agentWebsit;
	// 邮件;
	@Column(name = "AGENT_EMAIL")
	private String agentEmail;
	// 开户人;
	@Column(name = "AGENT_ACCOUNT_HOLDER", nullable = false)
	private String agentAccountHolder;
	// 开户行;
	@Column(name = "AGENT_ACCOUNT_BANK", nullable = false)
	private String agentAccountBank;
	// 开户账号;
	@Column(name = "AGENT_ACCOUNT_NO", nullable = false)
	private String agentAccountNo;
	// 开户账号对应币种;
	@Column(name = "AGENT_ACCOUNT_CURRENCY", nullable = false)
	private String agentAccountCurrency;
	// 开户账号对应币种对应的金额;
	@Transient
	private BigDecimal agentAccountCurrencyAmount;
	
	// 状态;
	@Column(name = "AGENT_STATUS", nullable = false)
	private String agentStatus;
	/**
	 * @return the agentAccountCurrencyAmount
	 */
	public BigDecimal getAgentAccountCurrencyAmount() {
		return agentAccountCurrencyAmount;
	}

	/**
	 * @param agentAccountCurrencyAmount the agentAccountCurrencyAmount to set
	 */
	public void setAgentAccountCurrencyAmount(BigDecimal agentAccountCurrencyAmount) {
		this.agentAccountCurrencyAmount = agentAccountCurrencyAmount;
	}

	// 添加人;
	@Column(name = "AGENT_LOGIN_NAME", nullable = false)
	private String createName;
	// 添加时间;
	@Column(name = "AGENT_OPRTIME", nullable = false)
	private Date agentOprtime;
	// 备注
	@Column(name = "AGENT_REMARK")
	private String agentRemark;
	// 代理商关联商户
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "agent")
	private Set<AgentMer> agentMers = new HashSet<AgentMer>(0);
	// 代理商登录日志
	@OneToMany(cascade = CascadeType.ALL,   mappedBy = "agent")
	private Set<SysAgentLoginlog> sysAgentLoginlogs = new HashSet<SysAgentLoginlog>(
			0);

	/**
	 * @return the agentNo
	 */
	public Long getAgentNo() {
		return agentNo;
	}

	/**
	 * @param agentNo
	 *            the agentNo to set
	 */
	public void setAgentNo(Long agentNo) {
		this.agentNo = agentNo;
	}

	/**
	 * @return the agentId
	 */
	public Long getAgentId() {
		return agentId;
	}

	/**
	 * @param agentId
	 *            the agentId to set
	 */
	public void setAgentId(Long agentId) {
		this.agentId = agentId;
	}

	/**
	 * @return the agentName
	 */
	public String getAgentName() {
		return agentName;
	}

	/**
	 * @param agentName
	 *            the agentName to set
	 */
	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	/**
	 * @return the agentLoginname
	 */
	public String getAgentLoginname() {
		return agentLoginname;
	}

	/**
	 * @param agentLoginname
	 *            the agentLoginname to set
	 */
	public void setAgentLoginname(String agentLoginname) {
		this.agentLoginname = agentLoginname;
	}

	/**
	 * @return the agentLoginpwd
	 */
	public String getAgentLoginpwd() {
		return agentLoginpwd;
	}

	/**
	 * @param agentLoginpwd
	 *            the agentLoginpwd to set
	 */
	public void setAgentLoginpwd(String agentLoginpwd) {
		this.agentLoginpwd = agentLoginpwd;
	}

	/**
	 * @return the agentLinkman
	 */
	public String getAgentLinkman() {
		return agentLinkman;
	}

	/**
	 * @param agentLinkman
	 *            the agentLinkman to set
	 */
	public void setAgentLinkman(String agentLinkman) {
		this.agentLinkman = agentLinkman;
	}

	/**
	 * @return the agentTel
	 */
	public String getAgentTel() {
		return agentTel;
	}

	/**
	 * @param agentTel
	 *            the agentTel to set
	 */
	public void setAgentTel(String agentTel) {
		this.agentTel = agentTel;
	}

	/**
	 * @return the agentAddress
	 */
	public String getAgentAddress() {
		return agentAddress;
	}

	/**
	 * @param agentAddress
	 *            the agentAddress to set
	 */
	public void setAgentAddress(String agentAddress) {
		this.agentAddress = agentAddress;
	}

	/**
	 * @return the agentWebsit
	 */
	public String getAgentWebsit() {
		return agentWebsit;
	}

	/**
	 * @param agentWebsit
	 *            the agentWebsit to set
	 */
	public void setAgentWebsit(String agentWebsit) {
		this.agentWebsit = agentWebsit;
	}

	/**
	 * @return the agentEmail
	 */
	public String getAgentEmail() {
		return agentEmail;
	}

	/**
	 * @param agentEmail
	 *            the agentEmail to set
	 */
	public void setAgentEmail(String agentEmail) {
		this.agentEmail = agentEmail;
	}

	/**
	 * @return the agentAccountHolder
	 */
	public String getAgentAccountHolder() {
		return agentAccountHolder;
	}

	/**
	 * @param agentAccountHolder
	 *            the agentAccountHolder to set
	 */
	public void setAgentAccountHolder(String agentAccountHolder) {
		this.agentAccountHolder = agentAccountHolder;
	}

	/**
	 * @return the agentAccountBank
	 */
	public String getAgentAccountBank() {
		return agentAccountBank;
	}

	/**
	 * @param agentAccountBank
	 *            the agentAccountBank to set
	 */
	public void setAgentAccountBank(String agentAccountBank) {
		this.agentAccountBank = agentAccountBank;
	}

	/**
	 * @return the agentAccountNo
	 */
	public String getAgentAccountNo() {
		return agentAccountNo;
	}

	/**
	 * @param agentAccountNo
	 *            the agentAccountNo to set
	 */
	public void setAgentAccountNo(String agentAccountNo) {
		this.agentAccountNo = agentAccountNo;
	}

	/**
	 * @return the agentAccountCurrency
	 */
	public String getAgentAccountCurrency() {
		return agentAccountCurrency;
	}

	/**
	 * @param agentAccountCurrency
	 *            the agentAccountCurrency to set
	 */
	public void setAgentAccountCurrency(String agentAccountCurrency) {
		this.agentAccountCurrency = agentAccountCurrency;
	}


	public String getCreateName() {
		return createName;
	}

	public void setCreateName(String createName) {
		this.createName = createName;
	}

	/**
	 * @return the agentOprtime
	 */
	public Date getAgentOprtime() {
		return agentOprtime;
	}

	/**
	 * @param agentOprtime
	 *            the agentOprtime to set
	 */
	public void setAgentOprtime(Date agentOprtime) {
		this.agentOprtime = agentOprtime;
	}

	/**
	 * @return the agentRemark
	 */
	public String getAgentRemark() {
		return agentRemark;
	}

	/**
	 * @param agentRemark
	 *            the agentRemark to set
	 */
	public void setAgentRemark(String agentRemark) {
		this.agentRemark = agentRemark;
	}

	/**
	 * @return the agentMers
	 */
	public Set<AgentMer> getAgentMers() {
		return agentMers;
	}

	/**
	 * @param agentMers
	 *            the agentMers to set
	 */
	public void setAgentMers(Set<AgentMer> agentMers) {
		this.agentMers = agentMers;
	}

	/**
	 * @return the sysAgentLoginlogs
	 */
	public Set<SysAgentLoginlog> getSysAgentLoginlogs() {
		return sysAgentLoginlogs;
	}

	/**
	 * @param sysAgentLoginlogs
	 *            the sysAgentLoginlogs to set
	 */
	public void setSysAgentLoginlogs(Set<SysAgentLoginlog> sysAgentLoginlogs) {
		this.sysAgentLoginlogs = sysAgentLoginlogs;
	}

	public String getAgentStatus() {
		return agentStatus;
	}

	public void setAgentStatus(String agentStatus) {
		this.agentStatus = agentStatus;
	}

}