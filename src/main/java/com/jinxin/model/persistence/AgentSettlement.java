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

import com.jinxin.common.utils.ParamChecking;


/**
 * AgentSettlement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_AGENT_SETTLEMENT")
public class AgentSettlement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增（1,1）,批次号ID
	@Id
	@Column(name = "AS_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AGENT_SETTLEMENT_SEQ")
	@SequenceGenerator(name = "CCPS_AGENT_SETTLEMENT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AGENT_SETTLEMENT_SEQ")
	private Long asId;
	// 代理商号
	@Column(name = "AS_AGENT_NO", nullable = false, precision = 38, scale = 0)
	private Long asAgentNo;
	// 划款币种
	@Column(name = "AS_ACCOUNT_CURRENCY", nullable = false)
	private String asAccountCurrency;
	// 佣金
	@Column(name = "AS_AMOUNT", nullable = false, precision = 18)
	private BigDecimal asAmount;
	// 开户人
	@Column(name = "AS_ACCOUNT_HOLDER", nullable = false)
	private String asAccountHolder;
	// 开户行
	@Column(name = "AS_ACCOUNT_BANK", nullable = false)
	private String asAccountBank;
	// 开户账号
	@Column(name = "AS_ACCOUNT_NO", nullable = false)
	private String asAccountNo;
	// 创表人
	@Column(name = "AS_CREATEACCOUNT")
	private String asCreateaccount;
	// 创表时间
	@Column(name = "AS_CREATETIME")
	private Date asCreatetime;
	// 审核人账号
	@Column(name = "AS_CHECKACCOUNT")
	private String asCheckaccount;
	// 审核时间
	@Column(name = "AS_CHECKTIME")
	private Date asChecktime;
	
	// 复核人
	@Column(name = "AS_RECHECKACCOUNT")
	private String asRecheckaccount;

	// 复核时间
	@Column(name = "AS_RECHECKTIME")
	private Date asRechecktime;
	
	// 划款账号
	@Column(name = "AS_SETTACOUNT")
	private String asSettacount;
	// 划款时间
	@Column(name = "AS_SETTIME")
	private Date asSettime;
	// 划款银行
	@Column(name = "AS_SETBANK")
	private String asSetbank;
	// 实际划款币种
	@Column(name = "AS_SETCURRENCY")
	private String asSetcurrency;
	// 财务划给代理商的款
	@Column(name = "AS_FACT_AMOUNT", precision = 18)
	private BigDecimal asFactAmount;
	//
	@Column(name = "AS_REMARK")
	private String asRemark;

	// -3已取消;-2 复核失败 -1: 审核失败; 0: 待处理; 1 : 已审核待复核 ; 2:已复核待处理 3 :已处理;
	@Column(name = "AS_STATUS", nullable = false, precision = 38, scale = 0)
	private Long asStatus;
	// 代理商划款明细
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "agentSettlement")
	private Set<AgentSettDetail> agentSettDetails = new HashSet<AgentSettDetail>(
			0);

	/**
	 * @return the asId
	 */
	public Long getAsId() {
		return asId;
	}

	/**
	 * @param asId
	 *            the asId to set
	 */
	public void setAsId(Long asId) {
		this.asId = asId;
	}

	/**
	 * @return the asAgentNo
	 */
	public Long getAsAgentNo() {
		return asAgentNo;
	}

	/**
	 * @param asAgentNo
	 *            the asAgentNo to set
	 */
	public void setAsAgentNo(Long asAgentNo) {
		this.asAgentNo = asAgentNo;
	}

	/**
	 * @return the asAccountCurrency
	 */
	public String getAsAccountCurrency() {
		return asAccountCurrency;
	}

	/**
	 * @param asAccountCurrency
	 *            the asAccountCurrency to set
	 */
	public void setAsAccountCurrency(String asAccountCurrency) {
		this.asAccountCurrency = asAccountCurrency;
	}

	/**
	 * @return the asAmount
	 */
	public BigDecimal getAsAmount() {
		return asAmount;
	}

	/**
	 * @return the asRecheckaccount
	 */
	public String getAsRecheckaccount() {
		return asRecheckaccount;
	}

	/**
	 * @param asRecheckaccount the asRecheckaccount to set
	 */
	public void setAsRecheckaccount(String asRecheckaccount) {
		this.asRecheckaccount = asRecheckaccount;
	}

	/**
	 * @return the asRechecktime
	 */
	public Date getAsRechecktime() {
		return asRechecktime;
	}

	/**
	 * @param asRechecktime the asRechecktime to set
	 */
	public void setAsRechecktime(Date asRechecktime) {
		this.asRechecktime = asRechecktime;
	}

	/**
	 * @param asAmount
	 *            the asAmount to set
	 */
	public void setAsAmount(BigDecimal asAmount) {
		this.asAmount = asAmount;
	}

	/**
	 * @return the asAccountHolder
	 */
	public String getAsAccountHolder() {
		return asAccountHolder;
	}

	/**
	 * @param asAccountHolder
	 *            the asAccountHolder to set
	 */
	public void setAsAccountHolder(String asAccountHolder) {
		this.asAccountHolder = asAccountHolder;
	}

	/**
	 * @return the asAccountBank
	 */
	public String getAsAccountBank() {
		return asAccountBank;
	}

	/**
	 * @param asAccountBank
	 *            the asAccountBank to set
	 */
	public void setAsAccountBank(String asAccountBank) {
		this.asAccountBank = asAccountBank;
	}

	/**
	 * @return the asAccountNo
	 */
	public String getAsAccountNo() {
		return asAccountNo;
	}

	/**
	 * @param asAccountNo
	 *            the asAccountNo to set
	 */
	public void setAsAccountNo(String asAccountNo) {
		this.asAccountNo = asAccountNo;
	}

	/**
	 * @return the asCreateaccount
	 */
	public String getAsCreateaccount() {
		return asCreateaccount;
	}

	/**
	 * @param asCreateaccount
	 *            the asCreateaccount to set
	 */
	public void setAsCreateaccount(String asCreateaccount) {
		this.asCreateaccount = asCreateaccount;
	}

	/**
	 * @return the asCreatetime
	 */
	public Date getAsCreatetime() {
		return asCreatetime;
	}

	/**
	 * @param asCreatetime
	 *            the asCreatetime to set
	 */
	public void setAsCreatetime(Date asCreatetime) {
		this.asCreatetime = asCreatetime;
	}

	/**
	 * @return the asCheckaccount
	 */
	public String getAsCheckaccount() {
		return asCheckaccount;
	}

	/**
	 * @param asCheckaccount
	 *            the asCheckaccount to set
	 */
	public void setAsCheckaccount(String asCheckaccount) {
		this.asCheckaccount = asCheckaccount;
	}

	/**
	 * @return the asChecktime
	 */
	public Date getAsChecktime() {
		return asChecktime;
	}

	/**
	 * @param asChecktime
	 *            the asChecktime to set
	 */
	public void setAsChecktime(Date asChecktime) {
		this.asChecktime = asChecktime;
	}

	/**
	 * @return the asSettacount
	 */
	public String getAsSettacount() {
		return asSettacount;
	}

	/**
	 * @param asSettacount
	 *            the asSettacount to set
	 */
	public void setAsSettacount(String asSettacount) {
		this.asSettacount = asSettacount;
	}

	/**
	 * @return the asSettime
	 */
	public Date getAsSettime() {
		return asSettime;
	}

	/**
	 * @param asSettime
	 *            the asSettime to set
	 */
	public void setAsSettime(Date asSettime) {
		this.asSettime = asSettime;
	}

	/**
	 * @return the asSetbank
	 */
	public String getAsSetbank() {
		return asSetbank;
	}

	/**
	 * @param asSetbank
	 *            the asSetbank to set
	 */
	public void setAsSetbank(String asSetbank) {
		this.asSetbank = asSetbank;
	}

	/**
	 * @return the asSetcurrency
	 */
	public String getAsSetcurrency() {
		return asSetcurrency;
	}

	/**
	 * @param asSetcurrency
	 *            the asSetcurrency to set
	 */
	public void setAsSetcurrency(String asSetcurrency) {
		this.asSetcurrency = asSetcurrency;
	}

	/**
	 * @return the asFactAmount
	 */
	public BigDecimal getAsFactAmount() {
		return asFactAmount;
	}

	/**
	 * @param asFactAmount
	 *            the asFactAmount to set
	 */
	public void setAsFactAmount(BigDecimal asFactAmount) {
		this.asFactAmount = asFactAmount;
	}

	/**
	 * @return the asRemark
	 */
	public String getAsRemark() {
		return asRemark;
	}

	/**
	 * @param asRemark
	 *            the asRemark to set
	 */
	public void setAsRemark(String asRemark) {
		this.asRemark = ParamChecking.str_null_to_empty(asRemark);
	}

	/**
	 * @return the asStatus
	 */
	public Long getAsStatus() {
		return asStatus;
	}

	/**
	 * @param asStatus
	 *            the asStatus to set
	 */
	public void setAsStatus(Long asStatus) {
		this.asStatus = asStatus;
	}

	/**
	 * @return the agentSettDetails
	 */
	public Set<AgentSettDetail> getAgentSettDetails() {
		return agentSettDetails;
	}

	/**
	 * @param agentSettDetails
	 *            the agentSettDetails to set
	 */
	public void setAgentSettDetails(Set<AgentSettDetail> agentSettDetails) {
		this.agentSettDetails = agentSettDetails;
	}

}