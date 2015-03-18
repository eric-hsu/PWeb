package com.jinxin.model.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * SysAdminModulesHistory entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ADMIN_MODULES_HISTORY")
public class SysAdminModulesHistory implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	@Id
	@Column(name = "AMH_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="sys_admin_modules_history_seq")
	@SequenceGenerator(name="sys_admin_modules_history_seq",allocationSize=1,initialValue=1, sequenceName="sys_admin_modules_history_seq")
	private Long amhId;
	// 模块
	@ManyToOne
	@JoinColumn(name = "AMH_MODULE_ID", nullable = false)
	private SysModules sysModules;
	// 操作员
	@ManyToOne
	@JoinColumn(name = "AMH_ADMIN_ID", nullable = false)
	private SysAdmin sysAdmin;
	// 操作次数
	@Column(name = "AMH_TOTAL", nullable = false, precision = 22, scale = 0)
	private Long amhTotal;
	// 操作时间
	@Column(name = "AMH_TIME", nullable = false)
	private Date amhTime;
	// 备注
	@Column(name = "AMH_REMARK")
	private String amhRemark;

	// Constructors

	/** default constructor */
	public SysAdminModulesHistory() {
	}

	/** minimal constructor */
	public SysAdminModulesHistory(SysModules sysModules, SysAdmin sysAdmin,
			Long amhTotal, Date amhTime) {
		this.sysModules = sysModules;
		this.sysAdmin = sysAdmin;
		this.amhTotal = amhTotal;
		this.amhTime = amhTime;
	}

	/** full constructor */
	public SysAdminModulesHistory(SysModules sysModules, SysAdmin sysAdmin,
			Long amhTotal, Date amhTime, String amhRemark) {
		this.sysModules = sysModules;
		this.sysAdmin = sysAdmin;
		this.amhTotal = amhTotal;
		this.amhTime = amhTime;
		this.amhRemark = amhRemark;
	}

	// Property accessors
	public Long getAmhId() {
		return this.amhId;
	}

	public void setAmhId(Long amhId) {
		this.amhId = amhId;
	}

	public SysModules getSysModules() {
		return this.sysModules;
	}

	public void setSysModules(SysModules sysModules) {
		this.sysModules = sysModules;
	}

	public SysAdmin getSysAdmin() {
		return this.sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public Long getAmhTotal() {
		return this.amhTotal;
	}

	public void setAmhTotal(Long amhTotal) {
		this.amhTotal = amhTotal;
	}

	public Date getAmhTime() {
		return this.amhTime;
	}

	public void setAmhTime(Date amhTime) {
		this.amhTime = amhTime;
	}

	public String getAmhRemark() {
		return this.amhRemark;
	}

	public void setAmhRemark(String amhRemark) {
		this.amhRemark = amhRemark;
	}

}