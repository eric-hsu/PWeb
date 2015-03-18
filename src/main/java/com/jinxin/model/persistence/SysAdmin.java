package com.jinxin.model.persistence;

import java.util.Date;
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
import javax.persistence.UniqueConstraint;

/**
 * <p> Title: </p>
 * <p>Description: Admin映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="SYS_ADMIN", uniqueConstraints = @UniqueConstraint(columnNames = "ADMIN_LOGIN_NAME"))
public class SysAdmin implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	// 管理员ID
	@Id
	@Column(name = "ADMIN_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ADMIN_SEQ")      
    @SequenceGenerator(name="SYS_ADMIN_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_ADMIN_SEQ")  
	private Long adminId;

	// 管理员名称
	@Column(name = "ADMIN_NAME",nullable=false,length = 60,insertable=true,updatable=true)
	private String adminName;

	// 登录名称
	@Column(name = "ADMIN_LOGIN_NAME", unique = true, nullable = false, length = 60, insertable=true, updatable=true)
	private String adminLoginName;

	// 登录密码
	@Column(name = "ADMIN_LOGIN_PASS",nullable=false,length=200,insertable=true,updatable=true)
	private String adminLoginPass;

	//登录mac地址
	@Column(name = "ADMIN_LOGIN_MAC", nullable = false, length = 200,insertable=true,updatable=true)
	private String adminLoginMac;
	
	// 登录IP
	@Column(name = "ADMIN_LOGIN_IP",nullable=true,length=100,insertable=true,updatable=true)
	private String adminLoginIp;

	// 开始登录时间
	@Column(name = "ADMIN_LOGIN_BEGIN_TIME",nullable=false,length=60,insertable=true,updatable=true)
	private String adminLoginBeginTime;
	
	// 结束登录时间
	@Column(name = "ADMIN_LOGIN_END_TIME",nullable=false,length=60,insertable=true,updatable=true)
	private String adminLoginEndTime;
	
	// 状态 0代表正常,1代表停用,-1代表删除
	@Column(name = "ADMIN_STATUS",nullable=false,insertable=true,updatable=true)
	private Long adminStatus;

	// 创建时间
	@Column(name = "ADMIN_CREATE_DATE",nullable=false,insertable=true,updatable=false,columnDefinition="DATE")
	private Date adminCreateDate;

	// 手机
	@Column(name = "ADMIN_MOBILEPHONE",nullable=true,length=60,insertable=true,updatable=true)
	private String adminMobilephone;

	// 电话
	@Column(name = "ADMIN_PHONE",nullable=true,length=60,insertable=true,updatable=true)
	private String adminPhone;

	// Email
	@Column(name = "ADMIN_EMAIL",nullable=true,length=100,insertable=true,updatable=true)
	private String adminEmail;

	// 是否默认管理员
	@Column(name = "ADMIN_IS_DEFAULT",nullable=false,insertable=true,updatable=true)
	private Long adminIsDefault;

	//海外
	@Column(name = "ADMIN_OVERSEAS", nullable=true)
	private Long adminOverseas;
	
	//0:否1:是  是否有授权权限
	@Column(name = "ADMIN_RIGHT", nullable=true)
	private Long adminRight;
	
	//登录失败次数
	@Column(name = "ADMIN_ERR_COUNT", nullable=true)
	private Long errCount;
	
	/**
	 * @return the adminRight
	 */
	public Long getAdminRight() {
		return adminRight;
	}

	/**
	 * @param adminRight the adminRight to set
	 */
	public void setAdminRight(Long adminRight) {
		this.adminRight = adminRight;
	}

	//国内
	@Column(name = "ADMIN_DOMESTIC", nullable=true)
	private Long adminDomestic;
	
	// 备注
	@Column(name = "ADMIN_REMARK",nullable=true,length=500,insertable=true,updatable=true)
	private String adminRemark;

	//管理员关联角色
	@OneToMany(mappedBy = "sysAdmin")
	private Set<SysAdminRefRole> sysAdminRefRoles = new HashSet<SysAdminRefRole>(
			0);

	// 操作日志
	@OneToMany(mappedBy = "sysAdmin")
	private Set<SysAdminOperatelog> sysAdminOperatelogs = new HashSet<SysAdminOperatelog>(
			0);
	
	// 登录日志
	@OneToMany(mappedBy = "sysAdmin")
	private Set<SysAdminLoginlog> sysAdminLoginlogs = new HashSet<SysAdminLoginlog>(
			0);

	public Long getAdminId() {
		return adminId;
	}

	public void setAdminId(Long adminId) {
		this.adminId = adminId;
	}

	public String getAdminName() {
		return adminName;
	}

	public void setAdminName(String adminName) {
		this.adminName = adminName;
	}

	public String getAdminLoginName() {
		return adminLoginName;
	}

	public void setAdminLoginName(String adminLoginName) {
		this.adminLoginName = adminLoginName;
	}

	public String getAdminLoginPass() {
		return adminLoginPass;
	}

	public void setAdminLoginPass(String adminLoginPass) {
		this.adminLoginPass = adminLoginPass;
	}

	public String getAdminLoginMac() {
		return adminLoginMac;
	}

	public void setAdminLoginMac(String adminLoginMac) {
		this.adminLoginMac = adminLoginMac;
	}

	public String getAdminLoginIp() {
		return adminLoginIp;
	}

	public void setAdminLoginIp(String adminLoginIp) {
		this.adminLoginIp = adminLoginIp;
	}

	public String getAdminLoginBeginTime() {
		return adminLoginBeginTime;
	}

	public void setAdminLoginBeginTime(String adminLoginBeginTime) {
		this.adminLoginBeginTime = adminLoginBeginTime;
	}

	public String getAdminLoginEndTime() {
		return adminLoginEndTime;
	}

	public void setAdminLoginEndTime(String adminLoginEndTime) {
		this.adminLoginEndTime = adminLoginEndTime;
	}

	public Long getAdminStatus() {
		return adminStatus;
	}

	public void setAdminStatus(Long adminStatus) {
		this.adminStatus = adminStatus;
	}

	public Date getAdminCreateDate() {
		return adminCreateDate;
	}

	public void setAdminCreateDate(Date adminCreateDate) {
		this.adminCreateDate = adminCreateDate;
	}

	public String getAdminMobilephone() {
		return adminMobilephone;
	}

	public void setAdminMobilephone(String adminMobilephone) {
		this.adminMobilephone = adminMobilephone;
	}

	public String getAdminPhone() {
		return adminPhone;
	}

	public void setAdminPhone(String adminPhone) {
		this.adminPhone = adminPhone;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
	}

	public Long getAdminIsDefault() {
		return adminIsDefault;
	}

	public void setAdminIsDefault(Long adminIsDefault) {
		this.adminIsDefault = adminIsDefault;
	}

	public String getAdminRemark() {
		return adminRemark;
	}

	public void setAdminRemark(String adminRemark) {
		this.adminRemark = adminRemark;
	}

	public Set<SysAdminRefRole> getSysAdminRefRoles() {
		return sysAdminRefRoles;
	}

	public void setSysAdminRefRoles(Set<SysAdminRefRole> sysAdminRefRoles) {
		this.sysAdminRefRoles = sysAdminRefRoles;
	}

	public Set<SysAdminOperatelog> getSysAdminOperatelogs() {
		return sysAdminOperatelogs;
	}

	public void setSysAdminOperatelogs(Set<SysAdminOperatelog> sysAdminOperatelogs) {
		this.sysAdminOperatelogs = sysAdminOperatelogs;
	}

	public Set<SysAdminLoginlog> getSysAdminLoginlogs() {
		return sysAdminLoginlogs;
	}

	public void setSysAdminLoginlogs(Set<SysAdminLoginlog> sysAdminLoginlogs) {
		this.sysAdminLoginlogs = sysAdminLoginlogs;
	}

	public Long getAdminOverseas() {
		return null == adminOverseas ? 0 : adminOverseas;
	}

	public void setAdminOverseas(Long adminOverseas) {
		this.adminOverseas = (null == adminOverseas ? 0 : adminOverseas); 
	}

	public Long getAdminDomestic() {
		return null == adminDomestic ? 0 : adminDomestic;
	}

	public void setAdminDomestic(Long adminDomestic) {
		this.adminDomestic = (null == adminDomestic ? 0 : adminDomestic);
	}

	public Long getErrCount() {
		return errCount;
	}

	public void setErrCount(Long errCount) {
		this.errCount = errCount;
	}
	
}