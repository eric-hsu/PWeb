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
 * AgentMer entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_AGENT_MER")
public class AgentMer implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 自增(1,1);
	@Id
	@Column(name = "AM_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_AGENT_MER_SEQ")
	@SequenceGenerator(name = "CCPS_AGENT_MER_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_AGENT_MER_SEQ")
	private Long amId;
	// 代理商表
	@ManyToOne
	@JoinColumn(name = "AM_AGENT_NO", nullable = false)
	private Agent agent;
	// 商户表
	@ManyToOne
	@JoinColumn(name = "AM_MER_NO", nullable = false)
	private Merchant merchant;
	// 网关接入号
	@Column(name = "AM_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long amGwNo;
	// 回佣率;
	@Column(name = "AM_RATE", precision = 18, scale = 4)
	private BigDecimal amRate;
	// 单笔回佣金币种;
	@Column(name = "AM_FEE_CURRENCY")
	private String amFeeCurrency;
	// 单笔回佣金;
	@Column(name = "AM_FEE", precision = 18)
	private BigDecimal amFee;
	// 失败订单是否收取手续费 1 :是 0:否
	@Column(name = "AM_FEE_FAIL", precision = 18)
	private int amFeeFail;
	//划款前成功订单全额异常是否收取手续费 1 :是 0:否
	@Column(name = "AM_FEE_SUCCESS", precision = 18)
	private int amFeeSuccess;
	//划款后成功订单全额异常是否收取手续费 1 :是 0:否
	@Column(name = "AM_FEE_SUCCESS_AFTER", precision = 18)
	private int amFeeSuccessAfter;
	//划款前是否收取异常金额的手续费 1 :是 0:否
	@Column(name = "AM_IS_BACK", precision = 18)
	private int amIsBack;
	//划款后是否收取异常金额的手续费 1 :是 0:否
	@Column(name = "AM_IS_BACK_AFTER", precision = 18)
	private int amIsBackAfter;
	// 生效时间;
	@Column(name = "AM_VALID_TIME")
	private Date amValidtime;
	// 添加人;
	@Column(name = "AM_LOGIN_NAME", nullable = false)
	private String amLoginName;
	// 添加时间;
	@Column(name = "AM_OPRTIME", nullable = false)
	private Date amOprtime;
	// 备注
	@Column(name = "AM_REMARK")
	private String amRemark;

	
	
	
	/**
	 * @return the amValidtime
	 */
	public Date getAmValidtime() {
		return amValidtime;
	}

	/**
	 * @param amValidtime the amValidtime to set
	 */
	public void setAmValidtime(Date amValidtime) {
		this.amValidtime = amValidtime;
	}

	/**
	 * @return the amId
	 */
	public Long getAmId() {
		return amId;
	}

	/**
	 * @param amId
	 *            the amId to set
	 */
	public void setAmId(Long amId) {
		this.amId = amId;
	}

	/**
	 * @return the agent
	 */
	public Agent getAgent() {
		return agent;
	}

	/**
	 * @param agent
	 *            the agent to set
	 */
	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	/**
	 * @return the merchant
	 */
	public Merchant getMerchant() {
		return merchant;
	}

	/**
	 * @param merchant
	 *            the merchant to set
	 */
	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}

	/**
	 * @return the amGwNo
	 */
	public Long getAmGwNo() {
		return amGwNo;
	}

	/**
	 * @param amGwNo
	 *            the amGwNo to set
	 */
	public void setAmGwNo(Long amGwNo) {
		this.amGwNo = amGwNo;
	}

	/**
	 * @return the amRate
	 */
	public BigDecimal getAmRate() {
		return amRate;
	}

	/**
	 * @param amRate
	 *            the amRate to set
	 */
	public void setAmRate(BigDecimal amRate) {
		this.amRate = amRate;
	}

	/**
	 * @return the amFeeCurrency
	 */
	public String getAmFeeCurrency() {
		return amFeeCurrency;
	}

	/**
	 * @param amFeeCurrency
	 *            the amFeeCurrency to set
	 */
	public void setAmFeeCurrency(String amFeeCurrency) {
		this.amFeeCurrency = amFeeCurrency;
	}

	/**
	 * @return the amFee
	 */
	public BigDecimal getAmFee() {
		return amFee;
	}

	/**
	 * @param amFee
	 *            the amFee to set
	 */
	public void setAmFee(BigDecimal amFee) {
		this.amFee = amFee;
	}

	/**
	 * @return the amLoginName
	 */
	public String getAmLoginName() {
		return amLoginName;
	}

	/**
	 * @param amLoginName
	 *            the amLoginName to set
	 */
	public void setAmLoginName(String amLoginName) {
		this.amLoginName = amLoginName;
	}

	/**
	 * @return the amOprtime
	 */
	public Date getAmOprtime() {
		return amOprtime;
	}

	/**
	 * @param amOprtime
	 *            the amOprtime to set
	 */
	public void setAmOprtime(Date amOprtime) {
		this.amOprtime = amOprtime;
	}

	/**
	 * @return the amRemark
	 */
	public String getAmRemark() {
		return amRemark;
	}

	/**
	 * @param amRemark
	 *            the amRemark to set
	 */
	public void setAmRemark(String amRemark) {
		this.amRemark = amRemark;
	}

	public int getAmFeeFail() {
		return amFeeFail;
	}

	public void setAmFeeFail(int amFeeFail) {
		this.amFeeFail = amFeeFail;
	}

	public int getAmFeeSuccess() {
		return amFeeSuccess;
	}

	public void setAmFeeSuccess(int amFeeSuccess) {
		this.amFeeSuccess = amFeeSuccess;
	}

	public int getAmFeeSuccessAfter() {
		return amFeeSuccessAfter;
	}

	public void setAmFeeSuccessAfter(int amFeeSuccessAfter) {
		this.amFeeSuccessAfter = amFeeSuccessAfter;
	}

	public int getAmIsBack() {
		return amIsBack;
	}

	public void setAmIsBack(int amIsBack) {
		this.amIsBack = amIsBack;
	}

	public int getAmIsBackAfter() {
		return amIsBackAfter;
	}

	public void setAmIsBackAfter(int amIsBackAfter) {
		this.amIsBackAfter = amIsBackAfter;
	}

}