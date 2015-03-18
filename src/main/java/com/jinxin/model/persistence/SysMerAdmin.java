package com.jinxin.model.persistence;


import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * SysMerAdmin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="SYS_MER_ADMIN")

public class SysMerAdmin  implements java.io.Serializable {

	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	@Id 
    @Column(name="MA_ID", unique=true, nullable=false, precision=38, scale=0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_MER_ADMIN_SEQ")      
    @SequenceGenerator(name="SYS_MER_ADMIN_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_MER_ADMIN_SEQ")
     private Long maId;

	// 商户表
	@ManyToOne
	@JoinColumn(name = "MA_MER_NO", nullable = false)
	private Merchant merchant;
    
    //管理员名称
    @Column(name="MA_NAME", nullable=false)
     private String maName;
    
    //登陆名称
    @Column(name="MA_MER_LGGIN_ACCOUNT", nullable=false)
     private String maMerLgginAccount;
    
    //登陆密码
    @Column(name="MA_LOGIN_PWD", nullable=false)
     private String maLoginPwd;
    
    //1: 正常 ; -1: 代表停用; -2: 代表删除;
    @Column(name="MA_STATUS", nullable=false, precision=38, scale=0)
     private Long maStatus;
    
    //添加人
    @Column(name="MA_LOGIN_NAME")
     private String maLoginName;
    
    //添加时间
    @Column(name="MA_OPRTIME")
     private Date maOprtime;
    
    //备注
    @Column(name="MA_REMARK")
     private String maRemark;
    
    //0 :系统默认管理员; 1: 系统新建;
    @Column(name="MA_IS_DEFAULT", nullable=false, precision=38, scale=0)
     private Long maIsDefault;
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="sysMerAdmin")
     private Set<SysMerOperatelog> sysMerOperatelogs = new HashSet<SysMerOperatelog>(0);
     
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="sysMerAdmin")
    private Set<SysMerLoginlog> sysMerLoginlogs = new HashSet<SysMerLoginlog>(0);
    
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="sysMerAdmin")
    private Set<SysMeradminMerrole> sysMeradminMerroles = new HashSet<SysMeradminMerrole>(0);
    
	public SysMerAdmin() {
	}

	public SysMerAdmin(Long maId) {
		this.maId = maId;
	}

	public Long getMaId() {
		return maId;
	}

	public void setMaId(Long maId) {
		this.maId = maId;
	}

	public String getMaName() {
		return maName;
	}

	public void setMaName(String maName) {
		this.maName = maName;
	}

	public String getMaMerLgginAccount() {
		return maMerLgginAccount;
	}

	public void setMaMerLgginAccount(String maMerLgginAccount) {
		this.maMerLgginAccount = maMerLgginAccount;
	}

	public String getMaLoginPwd() {
		return maLoginPwd;
	}

	public void setMaLoginPwd(String maLoginPwd) {
		this.maLoginPwd = maLoginPwd;
	}

	public Long getMaStatus() {
		return maStatus;
	}

	public void setMaStatus(Long maStatus) {
		this.maStatus = maStatus;
	}

	public String getMaLoginName() {
		return maLoginName;
	}

	public void setMaLoginName(String maLoginName) {
		this.maLoginName = maLoginName;
	}

	public Date getMaOprtime() {
		return maOprtime;
	}

	public void setMaOprtime(Date maOprtime) {
		this.maOprtime = maOprtime;
	}

	public String getMaRemark() {
		return maRemark;
	}

	public void setMaRemark(String maRemark) {
		this.maRemark = maRemark;
	}

	public Long getMaIsDefault() {
		return maIsDefault;
	}

	public void setMaIsDefault(Long maIsDefault) {
		this.maIsDefault = maIsDefault;
	}

	public Set<SysMerOperatelog> getSysMerOperatelogs() {
		return sysMerOperatelogs;
	}

	public void setSysMerOperatelogs(Set<SysMerOperatelog> sysMerOperatelogs) {
		this.sysMerOperatelogs = sysMerOperatelogs;
	}

	public Set<SysMerLoginlog> getSysMerLoginlogs() {
		return sysMerLoginlogs;
	}

	public void setSysMerLoginlogs(Set<SysMerLoginlog> sysMerLoginlogs) {
		this.sysMerLoginlogs = sysMerLoginlogs;
	}

	public Set<SysMeradminMerrole> getSysMeradminMerroles() {
		return sysMeradminMerroles;
	}

	public void setSysMeradminMerroles(Set<SysMeradminMerrole> sysMeradminMerroles) {
		this.sysMeradminMerroles = sysMeradminMerroles;
	}

	public Merchant getMerchant() {
		return merchant;
	}

	public void setMerchant(Merchant merchant) {
		this.merchant = merchant;
	}


}