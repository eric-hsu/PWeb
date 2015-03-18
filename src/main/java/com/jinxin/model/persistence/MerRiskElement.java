package com.jinxin.model.persistence;

// default package

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
import javax.persistence.Transient;

import org.springframework.transaction.annotation.Transactional;

/**
 * <p> Title: 记录商户各个网关接入号与风控元素绑定关系（适用于所有商户）</p>
 * <p>Description: CCPS_MER_RISKELMENT映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_RISKELMENT")
public class MerRiskElement implements java.io.Serializable {

	// Fields
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "MR_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_RISKELMENT_SEQ")      
    @SequenceGenerator(name="CCPS_MER_RISKELMENT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_RISKELMENT_SEQ")
	private Long mrId;
	
	// 风控元素表记录ID
	@ManyToOne
	@JoinColumn(name = "MR_RE_ID", nullable = false)
	private RiskElement riskElement;
	
	// 商户号+3位
	@Column(name = "MR_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long mrGwNo;
	
	// 商户号
	@Column(name = "MR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mrMerNo;
	
	// 添加人
	@Column(name = "MR_LOGIN_NAME", nullable = false)
	private String mrLoginName;
	
	// 添加时间
	@Column(name = "MR_OPRTIME", nullable = false)
	private Date mrOprtime;
	
	//备注
	@Column(name = "MR_REMARK")
	private String mrRemark;

	//风控元素(中文)
	@Transient
	private String info;
	
	//风控元素(英文)
	@Transient
	private String info2;
	public Long getMrId() {
		return mrId;
	}

	public void setMrId(Long mrId) {
		this.mrId = mrId;
	}

	public RiskElement getRiskElement() {
		return riskElement;
	}

	public void setRiskElement(RiskElement riskElement) {
		this.riskElement = riskElement;
	}

	public Long getMrGwNo() {
		return mrGwNo;
	}

	public void setMrGwNo(Long mrGwNo) {
		this.mrGwNo = mrGwNo;
	}

	public Long getMrMerNo() {
		return mrMerNo;
	}

	public void setMrMerNo(Long mrMerNo) {
		this.mrMerNo = mrMerNo;
	}

	public String getMrLoginName() {
		return mrLoginName;
	}

	public void setMrLoginName(String mrLoginName) {
		this.mrLoginName = mrLoginName;
	}

	public Date getMrOprtime() {
		return mrOprtime;
	}

	public void setMrOprtime(Date mrOprtime) {
		this.mrOprtime = mrOprtime;
	}

	public String getMrRemark() {
		return mrRemark;
	}

	public void setMrRemark(String mrRemark) {
		this.mrRemark = mrRemark;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getInfo2() {
		return info2;
	}

	public void setInfo2(String info2) {
		this.info2 = info2;
	}
	

}