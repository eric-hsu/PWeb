package com.jinxin.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p>Title:</p>
 * <p>Description: OpButtons映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version
 * @date 
 */
@Entity
@Table(name="SYS_BUTTONS")
public class SysButtons implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	// 按钮ID
	@Id
	@Column(name = "BUTTON_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_BUTTONS_SEQ")      
    @SequenceGenerator(name="SYS_BUTTONS_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_BUTTONS_SEQ")
	private Long buttonId;

	// 模块ID
	@ManyToOne
    @JoinColumn(name = "BUTTON_MODULE_ID")
	private SysModules sysModules = new SysModules();
	
	// 按钮名称(中文)
	@Column(name = "BUTTON_NAME_CN", nullable = false, length = 60)
	private String buttonNameCn;
	
	// 按钮名称(英文)
	@Column(name = "BUTTON_NAME_EN", nullable = false, length = 60)
	private String buttonNameEn;

	// 按钮图片地址
	@Column(name = "BUTTON_IMAGE_PATH",nullable=true,length=200,insertable=true,updatable=true)
	private String buttonImagePath;

	// 按钮备注
	@Column(name = "BUTTON_REMARK",nullable=true,length=2000,insertable=true,updatable=true)
	private String buttonRemark;

	// 按钮触发事件,点击该按钮时调用该方法
	@Column(name = "BUTTON_ONCLICK",nullable=true,length=200,insertable=true,updatable=true)
	private String buttonOnclick;

	//按钮类型 0 系统默认 1 用户维护
	@Column(name = "BUTTON_TYPE",nullable=false,insertable=true,updatable=true)
	private Long buttonType;
	
	//按钮关联模块
	@OneToMany(mappedBy = "sysButtons")
	private Set<SysRoleRefModule> sysRoleRefModules = new HashSet<SysRoleRefModule>(0);

	public SysButtons() {
	}

	public SysButtons(Long buttonId, SysModules sysModules,
			String buttonNameCn, String buttonNameEn,
			String buttonImagePath, String buttonOnclick, Long buttonType,
			String buttonRemark, Set<SysRoleRefModule> sysRoleRefModules) {
		this.buttonId = buttonId;
		this.sysModules = sysModules;
		this.buttonNameCn = buttonNameCn;
		this.buttonNameEn = buttonNameEn;
		this.buttonImagePath = buttonImagePath;
		this.buttonOnclick = buttonOnclick;
		this.buttonType = buttonType;
		this.buttonRemark = buttonRemark;
		this.sysRoleRefModules = sysRoleRefModules;
	}
	public Long getButtonId() {
		return buttonId;
	}

	public void setButtonId(Long buttonId) {
		this.buttonId = buttonId;
	}

	public SysModules getSysModules() {
		return sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

	public String getButtonNameCn() {
		return buttonNameCn;
	}

	public void setButtonNameCn(String buttonNameCn) {
		this.buttonNameCn = buttonNameCn;
	}

	public String getButtonNameEn() {
		return buttonNameEn;
	}

	public void setButtonNameEn(String buttonNameEn) {
		this.buttonNameEn = buttonNameEn;
	}

	public String getButtonImagePath() {
		return buttonImagePath;
	}

	public void setButtonImagePath(String buttonImagePath) {
		this.buttonImagePath = buttonImagePath;
	}

	public String getButtonRemark() {
		return buttonRemark;
	}

	public void setButtonRemark(String buttonRemark) {
		this.buttonRemark = buttonRemark;
	}

	public String getButtonOnclick() {
		return buttonOnclick;
	}

	public void setButtonOnclick(String buttonOnclick) {
		this.buttonOnclick = buttonOnclick;
	}

	public Long getButtonType() {
		return buttonType;
	}

	public void setButtonType(Long buttonType) {
		this.buttonType = buttonType;
	}

	public Set<SysRoleRefModule> getSysRoleRefModules() {
		return sysRoleRefModules;
	}

	public void setSysRoleRefModules(Set<SysRoleRefModule> sysRoleRefModules) {
		this.sysRoleRefModules = sysRoleRefModules;
	}

}
