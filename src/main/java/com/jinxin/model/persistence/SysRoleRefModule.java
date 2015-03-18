package com.jinxin.model.persistence;

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
 * <p>Title: </p>
 * <p>Description: OpRoleRefModule映射bean</p>
 * <p>Copyright:</p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="SYS_ROLE_REF_MODULE")
public class SysRoleRefModule implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;

	//ID
	@Id
	@Column(name = "RRM_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ROLE_REF_MODULE_SEQ")      
    @SequenceGenerator(name="SYS_ROLE_REF_MODULE_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_ROLE_REF_MODULE_SEQ")
	private Long rrmId;

	// 模块ID
	@ManyToOne
    @JoinColumn(name = "RRM_MODULE_ID")
	private SysModules sysModules;

	// 按钮ID
	@ManyToOne
    @JoinColumn(name = "RRM_BUTTON_ID")
	private SysButtons sysButtons;

	// 角色ID
	@ManyToOne
    @JoinColumn(name = "RRM_ROLE_ID")
	private SysRole sysRole;

	public Long getRrmId() {
		return rrmId;
	}

	public void setRrmId(Long rrmId) {
		this.rrmId = rrmId;
	}

	public SysModules getSysModules() {
		return sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

	public SysButtons getSysButtons() {
		return sysButtons;
	}

	public void setSysButtons(SysButtons sysButtons) {
		this.sysButtons = sysButtons;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

}
