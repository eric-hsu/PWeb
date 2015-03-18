package com.jinxin.model.persistence;

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
 * <p> Title: </p>
 * <p>Description: SysMerRole映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "SYS_MER_ROLE")
public class SysMerRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// mrId自增(1,1);
	@Id
	@Column(name = "MR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_MER_ROLE_SEQ")
	@SequenceGenerator(name = "SYS_MER_ROLE_SEQ", allocationSize=1,initialValue=1, sequenceName = "SYS_MER_ROLE_SEQ")
	private Long mrId;
	
	// 商户号
	@Column(name = "MR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mrMerNo;

	// 角色名称(中文)
	@Column(name = "MR_ROLE_NAME_CN", nullable = false)
	private String mrRoleNameCn;

	// 角色名称(英文)
	@Column(name = "MR_ROLE_NAME_EN", nullable = false)
	private String mrRoleNameEn;

	// 角色类型:0: 默认角色 ;1: 普通角色;
	@Column(name = "MR_ROLE_TYPE", nullable = false, precision = 38, scale = 0)
	private Long mrRoleType;

	// 角色状态:1 : 正常; -1: 删除;
	@Column(name = "MR_STATUS", nullable = false, precision = 38, scale = 0)
	private Long mrStatus;

	// 备注
	@Column(name = "MR_REMARK")
	private String mrRemark;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysMerRole")
	private Set<SysMeradminMerrole> sysMeradminMerroles = new HashSet<SysMeradminMerrole>(
			0);

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysMerRole")
	private Set<SysMerroleMermod> sysMerroleMermods = new HashSet<SysMerroleMermod>(
			0);

	public Long getMrId() {
		return mrId;
	}

	public void setMrId(Long mrId) {
		this.mrId = mrId;
	}

	public Long getMrMerNo() {
		return mrMerNo;
	}

	public void setMrMerNo(Long mrMerNo) {
		this.mrMerNo = mrMerNo;
	}

	public String getMrRoleNameCn() {
		return mrRoleNameCn;
	}

	public void setMrRoleNameCn(String mrRoleNameCn) {
		this.mrRoleNameCn = mrRoleNameCn;
	}

	public String getMrRoleNameEn() {
		return mrRoleNameEn;
	}

	public void setMrRoleNameEn(String mrRoleNameEn) {
		this.mrRoleNameEn = mrRoleNameEn;
	}

	public Long getMrRoleType() {
		return mrRoleType;
	}

	public void setMrRoleType(Long mrRoleType) {
		this.mrRoleType = mrRoleType;
	}

	public Long getMrStatus() {
		return mrStatus;
	}

	public void setMrStatus(Long mrStatus) {
		this.mrStatus = mrStatus;
	}

	public String getMrRemark() {
		return mrRemark;
	}

	public void setMrRemark(String mrRemark) {
		this.mrRemark = mrRemark;
	}

	public Set<SysMeradminMerrole> getSysMeradminMerroles() {
		return sysMeradminMerroles;
	}

	public void setSysMeradminMerroles(
			Set<SysMeradminMerrole> sysMeradminMerroles) {
		this.sysMeradminMerroles = sysMeradminMerroles;
	}

	public Set<SysMerroleMermod> getSysMerroleMermods() {
		return sysMerroleMermods;
	}

	public void setSysMerroleMermods(Set<SysMerroleMermod> sysMerroleMermods) {
		this.sysMerroleMermods = sysMerroleMermods;
	}

}