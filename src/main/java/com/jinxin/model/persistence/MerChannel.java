package com.jinxin.model.persistence;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.jinxin.common.utils.ParamChecking;

/**
 * <p> Title: 商户各网关接入号与通道的绑定关系表(针对网关接入号+通道+卡种)</p>
 * <p>Description: CCPS_MER_CHANNEL映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_CHANNEL")
public class MerChannel implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	@Id
	@Column(name = "MC_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_CHANNEL_SEQ")      
    @SequenceGenerator(name="CCPS_MER_CHANNEL_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_CHANNEL_SEQ")
	private Long mcId;
	
	// 商户号
	@Column(name = "MC_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mcMerNo;
	
	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "MC_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long mcGwNo;
	
	// 银行代码
	@Column(name = "MC_BANK_CODE", nullable = false)
	private String mcBankCode;
	// 新的银行代码
	@Transient
	private String mcBankCodeNew;
	//接口类型 1: 2.5方 2: 2方 3:3方
	@Transient
	private String mcInfType;
	//新的接口类型 1: 2.5方 2: 2方 3:3方
	@Transient
	private String mcInfTypeNew;
	// 通道代码
	@Column(name = "MC_CHA_CODE", nullable = false, precision = 38, scale = 0)
	private Long mcChaCode;
	// 新的通道代码
	@Transient
	private Long mcChaCodeNew;
	
	//通道状态-1:停用;1:正常
	@Transient
	private Integer cstatus;
	
	//通道支持币种
	@Transient
	private String ccurr;
	
	// 存储的是,卡种ID
	@Column(name = "MC_CARDTYPE", nullable = false, precision = 38, scale = 0)
	private Long mcCardtype;
	//卡种名称
	@Transient
	private String cardName;
	
	// -1:停用;1:正常  绑定状态
	@Column(name = "MC_STATUS", nullable = false, precision = 38, scale = 0)
	private Long mcStatus;
	
	// 记录历史通道代码,为了批量转通道后恢复使用
	@Column(name = "MC_CODE", precision = 38, scale = 0)
	private Long mcCode;
	
	// 添加人
	@Column(name = "MC_LOGIN_NAME", nullable = false)
	private String mcLoginName;
	
	// 添加时间
	@Column(name = "MC_OPRTIME", nullable = false)
	private Date mcOprtime;
	
	@Column(name = "MC_REMARK")
	private String mcRemark;

	public Long getMcId() {
		return mcId;
	}

	public void setMcId(Long mcId) {
		this.mcId = mcId;
	}

	public Long getMcMerNo() {
		return mcMerNo;
	}

	public void setMcMerNo(Long mcMerNo) {
		this.mcMerNo = mcMerNo;
	}

	/**
	 * @return the mcBankCodeNew
	 */
	public String getMcBankCodeNew() {
		return mcBankCodeNew;
	}

	/**
	 * @param mcBankCodeNew the mcBankCodeNew to set
	 */
	public void setMcBankCodeNew(String mcBankCodeNew) {
		this.mcBankCodeNew = mcBankCodeNew;
	}

	/**
	 * @return the mcChaCodeNew
	 */
	public Long getMcChaCodeNew() {
		return mcChaCodeNew;
	}

	/**
	 * @param mcChaCodeNew the mcChaCodeNew to set
	 */
	public void setMcChaCodeNew(Long mcChaCodeNew) {
		this.mcChaCodeNew = mcChaCodeNew;
	}

	public Long getMcGwNo() {
		return mcGwNo;
	}

	public void setMcGwNo(Long mcGwNo) {
		this.mcGwNo = mcGwNo;
	}

	public String getMcBankCode() {
		return mcBankCode;
	}

	public void setMcBankCode(String mcBankCode) {
		this.mcBankCode = mcBankCode;
	}

	public Long getMcChaCode() {
		return mcChaCode;
	}

	public void setMcChaCode(Long mcChaCode) {
		this.mcChaCode = mcChaCode;
	}

	public Long getMcCardtype() {
		return mcCardtype;
	}

	public void setMcCardtype(Long mcCardtype) {
		this.mcCardtype = mcCardtype;
	}

	

	public Long getMcCode() {
		return mcCode;
	}

	public void setMcCode(Long mcCode) {
		this.mcCode = mcCode;
	}

	public String getMcLoginName() {
		return mcLoginName;
	}

	public void setMcLoginName(String mcLoginName) {
		this.mcLoginName = mcLoginName;
	}

	public Date getMcOprtime() {
		return mcOprtime;
	}

	public void setMcOprtime(Date mcOprtime) {
		this.mcOprtime = mcOprtime;
	}

	public String getMcRemark() {
		return mcRemark;
	}

	public void setMcRemark(String mcRemark) {
		this.mcRemark = ParamChecking.str_null_to_empty(mcRemark);
	}

	/**
	 * @return the cardName
	 */
	public String getCardName() {
		return cardName;
	}

	/**
	 * @param cardName the cardName to set
	 */
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	/**
	 * @return the cstatus
	 */
	public int getCstatus() {
		return cstatus;
	}

	/**
	 * @param cstatus the cstatus to set
	 */
	public void setCstatus(Integer cstatus) {
		this.cstatus = cstatus;
	}

	/**
	 * @return the ccurr
	 */
	public String getCcurr() {
		return ccurr;
	}

	/**
	 * @param ccurr the ccurr to set
	 */
	public void setCcurr(String ccurr) {
		this.ccurr = ccurr;
	}

	/**
	 * @return the mcStatus
	 */
	public Long getMcStatus() {
		return mcStatus;
	}

	/**
	 * @param mcStatus the mcStatus to set
	 */
	public void setMcStatus(Long mcStatus) {
		this.mcStatus = mcStatus;
	}
	
	/**
	 * @return the mcInfType
	 */
	public String getMcInfType() {
		return mcInfType;
	}

	/**
	 * @param mcInfType the mcInfType to set
	 */
	public void setMcInfType(String mcInfType) {
		this.mcInfType = mcInfType;
	}

	/**
	 * @return the mcInfTypeNew
	 */
	public String getMcInfTypeNew() {
		return mcInfTypeNew;
	}

	/**
	 * @param mcInfTypeNew the mcInfTypeNew to set
	 */
	public void setMcInfTypeNew(String mcInfTypeNew) {
		this.mcInfTypeNew = mcInfTypeNew;
	}
}