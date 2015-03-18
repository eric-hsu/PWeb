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
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 商户国家限定表bean
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version
 * @date
 */
@Entity
@Table(name = "CCPS_COUNTY_LIMIT")
public class CountyLimit implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "CL_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_COUNTY_LIMIT_SEQ")
	@SequenceGenerator(name = "CCPS_COUNTY_LIMIT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_COUNTY_LIMIT_SEQ")
	private Long clId;

	// 商户号
	@Column(name = "CL_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long clMerNo;

	// 网关接入号
	@Column(name = "CL_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long clGwNo;

	// 限定种类
	@Column(name = "CL_NAME", nullable = false, precision = 38, scale = 0)
	private Integer clName;

	// 限定类型
	@Column(name = "CL_TYPE", nullable = false, precision = 38, scale = 0)
	private Integer clType;

	// 限定国家
	@Column(name = "CL_ZH_NAME")
	private String clZhName;

	// 限定国家简称
	@Column(name = "CL_CODE", nullable = false)
	private String clCode;

	// 操作人账号
	@Column(name = "CL_LOGIN_NAME", nullable = false)
	private String clLoginName;

	// 操作时间
	@Column(name = "CL_OPRTIME", nullable = false)
	private Date clOprtime;

	// 备注
	@Column(name = "CL_REMARK")
	private String clRemark;

	// Constructors

	/** default constructor */
	public CountyLimit() {
	}

	public Long getClId() {
		return this.clId;
	}

	public void setClId(Long clId) {
		this.clId = clId;
	}

	public Long getClMerNo() {
		return this.clMerNo;
	}

	public void setClMerNo(Long clMerNo) {
		this.clMerNo = clMerNo;
	}

	public Long getClGwNo() {
		return this.clGwNo;
	}

	public void setClGwNo(Long clGwNo) {
		this.clGwNo = clGwNo;
	}

	public Integer getClName() {
		return clName;
	}

	public void setClName(Integer clName) {
		this.clName = clName;
	}

	public Integer getClType() {
		return clType;
	}

	public void setClType(Integer clType) {
		this.clType = clType;
	}

	public String getClZhName() {
		return this.clZhName;
	}

	public void setClZhName(String clZhName) {
		this.clZhName = clZhName;
	}

	public String getClCode() {
		return this.clCode;
	}

	public void setClCode(String clCode) {
		this.clCode = clCode;
	}

	public String getClLoginName() {
		return this.clLoginName;
	}

	public void setClLoginName(String clLoginName) {
		this.clLoginName = clLoginName;
	}

	public Date getClOprtime() {
		return this.clOprtime;
	}

	public void setClOprtime(Date clOprtime) {
		this.clOprtime = clOprtime;
	}

	public String getClRemark() {
		return this.clRemark;
	}

	public void setClRemark(String clRemark) {
		this.clRemark = clRemark;
	}

}