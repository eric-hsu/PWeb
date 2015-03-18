package com.jinxin.model.persistence;

import java.util.Date;

import javax.persistence.CascadeType;
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
 * SysAdminLoginlog entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "SYS_ADMIN_LOGINLOG")
public class SysAdminLoginlog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// 日志ID    
	@Id
	@Column(name = "AL_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ADMIN_LOGINLOG_SEQ")      
    @SequenceGenerator(name="SYS_ADMIN_LOGINLOG_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_ADMIN_LOGINLOG_SEQ")
	private Long alId;
	
	//管理员ID
	@ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "AL_ADMIN_ID",nullable=true)
	private SysAdmin sysAdmin;
	
	//登录IP
	@Column(name = "AL_IP", length = 60)
	private String alIp;
	
	//登录时间 
	@Column(name = "AL_DATE",nullable=false,insertable=true,updatable=true,columnDefinition="DATE")
	private Date alDate;
	
	//登录状态 0-失败；1-成功
	@Column(name = "AL_TYPE")
	private Long alType;

	public Long getAlId() {
		return alId;
	}

	public void setAlId(Long alId) {
		this.alId = alId;
	}

	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public String getAlIp() {
		return alIp;
	}

	public void setAlIp(String alIp) {
		this.alIp = alIp;
	}

	public Date getAlDate() {
		return alDate;
	}

	public void setAlDate(Date alDate) {
		this.alDate = alDate;
	}

	public Long getAlType() {
		return alType;
	}

	public void setAlType(Long alType) {
		this.alType = alType;
	}

}