package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 接口附加元素表bean
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
@Table(name = "CCPS_EXTRA_ELEMENT")
public class ExtraElement implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "EE_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_EXTRA_ELEMENT_SEQ")
	@SequenceGenerator(name = "CCPS_EXTRA_ELEMENT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_EXTRA_ELEMENT_SEQ")
	private Long eeId;

	// 元素名称(中文)
	@Column(name = "EE_NAME_CN", nullable = false)
	private String eeNameCn;

	// 元素名称(英文)
	@Column(name = "EE_NAME_EN", nullable = false)
	private String eeNameEn;

	// 元素值
	@Column(name = "EE_VALUE", nullable = false)
	private String eeValue;
	
	// 长度
	@Column(name = "EE_LENGTH", nullable = false)
	private int eeLength;

	// 操作人账号
	@Column(name = "EE_LOGIN_NAME")
	private String eeLoginName;

	// 操作时间
	@Column(name = "EE_OPRTIME")
	private String eeOprtime;

	
	// 备注
	@Column(name = "EE_REMARK")
	private String eeRemark;

	// 接口附加角色元素关联表
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "extraElement")
	private Set<ExtraRoleEle> extraRoleEles = new HashSet<ExtraRoleEle>(0);

	// Constructors

	/** default constructor */
	public ExtraElement() {
	}

	 
	public Long getEeId() {
		return eeId;
	}


	public void setEeId(Long eeId) {
		this.eeId = eeId;
	}


	public String getEeNameCn() {
		return this.eeNameCn;
	}

	public void setEeNameCn(String eeNameCn) {
		this.eeNameCn = eeNameCn;
	}

	public String getEeNameEn() {
		return this.eeNameEn;
	}

	public void setEeNameEn(String eeNameEn) {
		this.eeNameEn = eeNameEn;
	}

	public String getEeValue() {
		return this.eeValue;
	}

	public void setEeValue(String eeValue) {
		this.eeValue = eeValue;
	}

	public String getEeLoginName() {
		return this.eeLoginName;
	}

	public void setEeLoginName(String eeLoginName) {
		this.eeLoginName = eeLoginName;
	}

	public String getEeOprtime() {
		return this.eeOprtime;
	}

	public void setEeOprtime(String eeOprtime) {
		this.eeOprtime = eeOprtime;
	}

	public String getEeRemark() {
		return this.eeRemark;
	}

	public void setEeRemark(String eeRemark) {
		this.eeRemark = eeRemark;
	}

	public Set<ExtraRoleEle> getExtraRoleEles() {
		return this.extraRoleEles;
	}

	public void setExtraRoleEles(Set<ExtraRoleEle> extraRoleEles) {
		this.extraRoleEles = extraRoleEles;
	}

	public int getEeLength() {
		return eeLength;
	}

	public void setEeLength(int eeLength) {
		this.eeLength = eeLength;
	}
	

}