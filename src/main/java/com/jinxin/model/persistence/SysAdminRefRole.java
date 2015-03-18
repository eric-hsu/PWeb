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
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: OpAdminRefRole映射bean
 * </p>
 * <p>
 * Copyright: 
 * </p>
 * <p>
 * Company: 
 * </p>
 * @author
 * @version
 * @date
 */
@Entity
@Table(name="SYS_ADMIN_REF_ROLE")
public class SysAdminRefRole implements java.io.Serializable
{

	private static final long serialVersionUID = 1L;

	//ID号
	@Id
	@Column(name = "ARR_ID")	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ADMIN_REF_ROLE_SEQ")      
    @SequenceGenerator(name="SYS_ADMIN_REF_ROLE_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_ADMIN_REF_ROLE_SEQ")  
	private Long arrId;

	// 角色ID
	@ManyToOne
    @JoinColumn(name = "ARR_ROLE_ID")
	private SysRole sysRole;

	// 管理员ID
	@ManyToOne
    @JoinColumn(name = "ARR_ADMIN_ID")
	private SysAdmin sysAdmin;

	public Long getArrId() {
		return arrId;
	}

	public void setArrId(Long arrId) {
		this.arrId = arrId;
	}

	public SysRole getSysRole() {
		return sysRole;
	}

	public void setSysRole(SysRole sysRole) {
		this.sysRole = sysRole;
	}

	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}
}