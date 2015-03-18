package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.Date;

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
 * <p>Description: UnNormalProcess映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_UNNORMAL_PROCESS")
public class UnNormalProcess implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "UP_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_UNNORMAL_PROCESS_SEQ")
	@SequenceGenerator(name = "CCPS_UNNORMAL_PROCESS_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_UNNORMAL_PROCESS_SEQ")
	private Long upId;

	// 流水订单号
	@ManyToOne
	@JoinColumn(name = "UP_TR_NO")
	private Traderecord traderecord;

	// 0 :系统后台操作人员; 1:商户提交; 2:持卡人客服提交; 3:风控提交
	@Column(name = "UP_SUBMITBY", nullable = false, precision = 38, scale = 0)
	private Integer upSubmitby;

	// 1 :退款;2: 拒付; 3:冻结;4 :解冻变正常;5:解冻变退款;
	//6:解冻变拒付 7 申诉失败 8 申诉成功; 9.申诉中  温馨提示:状态变化是否参于划款请参照<划款流程图>
	@Column(name = "UP_TYPE", nullable = false, precision = 38, scale = 0)
	private Integer upType;

	// 与交易币种相同
	@Column(name = "UP_CURRENCY", nullable = false)
	private String upCurrency;

	// 异常金额; 金额为负表示向商户扣钱,反之给商户钱;
	@Column(name = "UP_AMOUNT", nullable = false, precision = 18)
	private BigDecimal upAmount;

	// 异常原因
	@Column(name = "UP_REASON", nullable = false)
	private String upReason;

	// -1:失败; 0: 待审核; 1审核成功未处理 2:审核成功已处理 
	@Column(name = "UP_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer upStatus;

	// 添加人(登录用户)
	@Column(name = "UP_LOGIN_NAME", nullable = false) 
	private String upLoginName;

	// 添加时间(系统时间)
	@Column(name = "UP_OPRTIME", nullable = false) 
	private Date upOprtime;

	// 审核账号
	@Column(name = "UP_CHECKACCOUNT")
	private String upCheckaccount;

	// 审核时间
	@Column(name = "UP_CHECKTIME")
	private Date upChecktime;

	// 执行账号
	@Column(name = "UP_EXEACCOUNT")
	private String upExeaccount;

	// 执行时间
	@Column(name = "UP_EXETIME")
	private Date upExetime;

	// 0: 未划款; 1 : 已制表 ; 2: 已划款 (默认状态交易=0)
	@Column(name = "UP_SETTLEMENT_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer upSettlementStatus;

	// 交易划款记录表ID
	@Column(name = "UP_TS_ID", precision = 38, scale = 0)
	private Long upTsId;

	// 0: 未划款; 1 : 已制表 ; 2: 已划款
	@Column(name = "UP_AGENT_STATUS", nullable = false, precision = 38, scale = 0)
	private Integer upAgentStatus;

	// 代理商佣金划款表ID
	@Column(name = "UP_AS_ID", precision = 38, scale = 0)
	private Long upAsId;

	// 1 是 0 否 1表示划款后发生的异常 0为划款前发生的异常(要进行处理)--交易划款
	@Column(name = "UP_ISFIRST", nullable = false, precision = 38, scale = 0)
	private Integer upIsfirst;

	// 1 是 0 否 1表示划款后发生的异常 0为划款前发生的异常(要进行处理)--代理商划款
	@Column(name = "UP_ISFIRST_AGENT", nullable = false, precision = 38, scale = 0)
	private Integer upIsfirstagent;
	// 备注
	@Column(name = "UP_REMARK")
	private String upRemark;
	
	
	

	/**
	 * @return the upIsfirstagent
	 */
	public Integer getUpIsfirstagent() {
		return upIsfirstagent;
	}

	/**
	 * @param upIsfirstagent the upIsfirstagent to set
	 */
	public void setUpIsfirstagent(Integer upIsfirstagent) {
		this.upIsfirstagent = upIsfirstagent;
	}

	public Long getUpId() {
		return upId;
	}

	public void setUpId(Long upId) {
		this.upId = upId;
	}

	public Traderecord getTraderecord() {
		return traderecord;
	}

	public void setTraderecord(Traderecord traderecord) {
		this.traderecord = traderecord;
	}

	public Integer getUpSubmitby() {
		return upSubmitby;
	}

	public void setUpSubmitby(Integer upSubmitby) {
		this.upSubmitby = upSubmitby;
	}

	public Integer getUpType() {
		return upType;
	}

	public void setUpType(Integer upType) {
		this.upType = upType;
	}

	public String getUpCurrency() {
		return upCurrency;
	}

	public void setUpCurrency(String upCurrency) {
		this.upCurrency = upCurrency;
	}

	public BigDecimal getUpAmount() {
		return upAmount;
	}

	public void setUpAmount(BigDecimal upAmount) {
		this.upAmount = upAmount;
	}

	public String getUpReason() {
		return upReason;
	}

	public void setUpReason(String upReason) {
		this.upReason = upReason;
	}

	public Integer getUpStatus() {
		return upStatus;
	}

	public void setUpStatus(Integer upStatus) {
		this.upStatus = upStatus;
	}

	public String getUpLoginName() {
		return upLoginName;
	}

	public void setUpLoginName(String upLoginName) {
		this.upLoginName = upLoginName;
	}

	public Date getUpOprtime() {
		return upOprtime;
	}

	public void setUpOprtime(Date upOprtime) {
		this.upOprtime = upOprtime;
	}

	public String getUpCheckaccount() {
		return upCheckaccount;
	}

	public void setUpCheckaccount(String upCheckaccount) {
		this.upCheckaccount = upCheckaccount;
	}

	public Date getUpChecktime() {
		return upChecktime;
	}

	public void setUpChecktime(Date upChecktime) {
		this.upChecktime = upChecktime;
	}

	public String getUpExeaccount() {
		return upExeaccount;
	}

	public void setUpExeaccount(String upExeaccount) {
		this.upExeaccount = upExeaccount;
	}

	public Date getUpExetime() {
		return upExetime;
	}

	public void setUpExetime(Date upExetime) {
		this.upExetime = upExetime;
	}

	public Integer getUpSettlementStatus() {
		return upSettlementStatus;
	}

	public void setUpSettlementStatus(Integer upSettlementStatus) {
		this.upSettlementStatus = upSettlementStatus;
	}

	public Long getUpTsId() {
		return upTsId;
	}

	public void setUpTsId(Long upTsId) {
		this.upTsId = upTsId;
	}

	public Integer getUpAgentStatus() {
		return upAgentStatus;
	}

	public void setUpAgentStatus(Integer upAgentStatus) {
		this.upAgentStatus = upAgentStatus;
	}

	public Long getUpAsId() {
		return upAsId;
	}

	public void setUpAsId(Long upAsId) {
		this.upAsId = upAsId;
	}

	public Integer getUpIsfirst() {
		return upIsfirst;
	}

	public void setUpIsfirst(Integer upIsfirst) {
		this.upIsfirst = upIsfirst;
	}

	public String getUpRemark() {
		return upRemark;
	}

	public void setUpRemark(String upRemark) {
		this.upRemark = upRemark;
	}

}