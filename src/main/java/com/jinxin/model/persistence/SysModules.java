package com.jinxin.model.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p>Title:</p>
 * <p>Description: OpModules映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version
 * @date
 */
@Entity
@Table(name="SYS_MODULES")
public class SysModules implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	// Constructors
	// 模块ID
	@Id
	@Column(name = "MODULE_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_MODULES_SEQ")      
    @SequenceGenerator(name="SYS_MODULES_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_MODULES_SEQ")
	private Long moduleId;

	// 模块名称(中文)
	@Column(name = "MODULE_NAME_CN", nullable = false, length = 200)
	private String moduleNameCn;
	
	// 模块名称(英文)
	@Column(name = "MODULE_NAME_EN", nullable = false, length = 200)
	private String moduleNameEn;

	// 模块链接url
	@Column(name = "MODULE_URL",nullable=true,length=200,insertable=true,updatable=true)
	private String moduleUrl;

	// 模块父ID
	@Column(name = "MODULE_FID",nullable=false,insertable=true,updatable=true)
	private Long moduleFid;
	
	// 模块排列顺序
	@Column(name = "MODULE_ORDER",nullable=false,insertable=true,updatable=true)
	private Long moduleOrder;
	
	//模块类型 0 系统默认 1 用户维护
	@Column(name = "MODULE_TYPE",nullable=false,insertable=true,updatable=true)
	private Long moduleType;
	
	//模块状态 0 启用 1 停用
	@Column(name = "MODULE_STATUS",nullable=false,insertable=true,updatable=true)
		private Long moduleStatus;
	
	//父模块名称 不映射
	@Transient
	private String moduleFName;
	
	//模块关联角色
	@OneToMany(mappedBy = "sysModules")
	private Set<SysRoleRefModule> sysRoleRefModules = new HashSet<SysRoleRefModule>(0);
	
	// 模块关联按钮
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "sysModules")
	private Set<SysButtons> sysButtonses = new HashSet<SysButtons>(0);

	//按钮列表 不映射
	@Transient
	private List<SysButtons> buttonList = new ArrayList<SysButtons>();

	public SysModules() {
	}

	public SysModules(Long moduleId) {
		this.moduleId = moduleId;
	}

	public SysModules(Long moduleId, String moduleNameCn,
			String moduleNameEn, String moduleUrl, Long moduleFid,
			Long moduleOrder, Long moduleType,
			Set<SysRoleRefModule> sysRoleRefModules,
			Set<SysButtons> sysButtonses) {
		this.moduleId = moduleId;
		this.moduleNameCn = moduleNameCn;
		this.moduleNameEn = moduleNameEn;
		this.moduleUrl = moduleUrl;
		this.moduleFid = moduleFid;
		this.moduleOrder = moduleOrder;
		this.moduleType = moduleType;
		this.sysRoleRefModules = sysRoleRefModules;
		this.sysButtonses = sysButtonses;
	}
	public Long getModuleId() {
		return moduleId;
	}

	public void setModuleId(Long moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleNameCn() {
		return moduleNameCn;
	}

	public void setModuleNameCn(String moduleNameCn) {
		this.moduleNameCn = moduleNameCn;
	}

	public String getModuleNameEn() {
		return moduleNameEn;
	}

	public void setModuleNameEn(String moduleNameEn) {
		this.moduleNameEn = moduleNameEn;
	}

	public String getModuleUrl() {
		return moduleUrl;
	}

	public void setModuleUrl(String moduleUrl) {
		this.moduleUrl = moduleUrl;
	}

	public Long getModuleFid() {
		return moduleFid;
	}

	public void setModuleFid(Long moduleFid) {
		this.moduleFid = moduleFid;
	}

	public Long getModuleOrder() {
		return moduleOrder;
	}

	public void setModuleOrder(Long moduleOrder) {
		this.moduleOrder = moduleOrder;
	}

	public Long getModuleType() {
		return moduleType;
	}

	public void setModuleType(Long moduleType) {
		this.moduleType = moduleType;
	}

	public String getModuleFName() {
		return moduleFName;
	}

	public void setModuleFName(String moduleFName) {
		this.moduleFName = moduleFName;
	}

	public Set<SysRoleRefModule> getSysRoleRefModules() {
		return sysRoleRefModules;
	}

	public void setSysRoleRefModules(Set<SysRoleRefModule> sysRoleRefModules) {
		this.sysRoleRefModules = sysRoleRefModules;
	}

	public Set<SysButtons> getSysButtonses() {
		return sysButtonses;
	}

	public void setSysButtonses(Set<SysButtons> sysButtonses) {
		this.sysButtonses = sysButtonses;
	}

	public List<SysButtons> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<SysButtons> buttonList) {
		this.buttonList = buttonList;
	}

	public Long getModuleStatus() {
		return moduleStatus;
	}

	public void setModuleStatus(Long moduleStatus) {
		this.moduleStatus = moduleStatus;
	}
	
}
