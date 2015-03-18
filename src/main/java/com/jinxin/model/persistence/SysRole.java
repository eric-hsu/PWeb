package com.jinxin.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p>Title:</p>
 * <p>Description: OpRole映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="SYS_ROLE")
public class SysRole implements java.io.Serializable
{
	/**
	 * @Fields serialVersionUID : 
	 */
	private static final long serialVersionUID = 1L;

	// 角色ID

	@Id
	@Column(name = "ROLE_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ROLE_SEQ")      
    @SequenceGenerator(name="SYS_ROLE_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_ROLE_SEQ")
    
	private Long roleId;

	// 角色名称(中文)
	@Column(name = "ROLE_NAME_CN", nullable = false, length = 60)
	private String roleNameCn;
	
	// 角色名称(英文)
	@Column(name = "ROLE_NAME_EN", nullable = false, length = 60)
	private String roleNameEn;
	
	// 角色类型
	@Column(name = "ROLE_TYPE",nullable=false,insertable=true,updatable=true)
	private Long roleType;//角色类型:0代表默认角色,1代表普通角色,默认角色不可修改删除

	// 0代表正常,-1代表删除
	@Column(name = "ROLE_STATUS", nullable=false, insertable=true, updatable=true)
	private Long roleStatus;

	// 角色备注
	@Column(name = "ROLE_REMARK", nullable=true, length = 2000)
	private String roleRemark;
	
	//管理员关联角色
	@OneToMany(mappedBy = "sysRole")
	private Set<SysAdminRefRole> sysRoleRefModules = new HashSet<SysAdminRefRole>(0);
	
	//模块关联角色
	@OneToMany(mappedBy = "sysRole")
	private Set<SysRoleRefModule> sysAdminRefRoles = new HashSet<SysRoleRefModule>(0);

	public Long getRoleId() {
		return roleId;
	}

	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	public String getRoleNameCn() {
		return roleNameCn;
	}

	public void setRoleNameCn(String roleNameCn) {
		this.roleNameCn = roleNameCn;
	}

	public String getRoleNameEn() {
		return roleNameEn;
	}

	public void setRoleNameEn(String roleNameEn) {
		this.roleNameEn = roleNameEn;
	}

	public Long getRoleType() {
		return roleType;
	}

	public void setRoleType(Long roleType) {
		this.roleType = roleType;
	}

	public Long getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(Long roleStatus) {
		this.roleStatus = roleStatus;
	}

	public String getRoleRemark() {
		return roleRemark;
	}

	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}

	public Set<SysAdminRefRole> getSysRoleRefModules() {
		return sysRoleRefModules;
	}

	public void setSysRoleRefModules(Set<SysAdminRefRole> sysRoleRefModules) {
		this.sysRoleRefModules = sysRoleRefModules;
	}

	public Set<SysRoleRefModule> getSysAdminRefRoles() {
		return sysAdminRefRoles;
	}

	public void setSysAdminRefRoles(Set<SysRoleRefModule> sysAdminRefRoles) {
		this.sysAdminRefRoles = sysAdminRefRoles;
	}
	
}
