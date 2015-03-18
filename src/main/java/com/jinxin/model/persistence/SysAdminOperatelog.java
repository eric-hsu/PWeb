package com.jinxin.model.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>Title:</p>
 * <p>Description: Operatelog映射bean</p>
 * <p>Copyright:</p>
 * <p>Company:</p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="SYS_ADMIN_OPERATELOG")
public class SysAdminOperatelog implements java.io.Serializable
{
	private static final long serialVersionUID = 1L;

	// 日志ID    
	@Id
	@Column(name = "AO_ID")
	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_ADMIN_OPERATELOG_SEQ")      
    @SequenceGenerator(name="SYS_ADMIN_OPERATELOG_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_ADMIN_OPERATELOG_SEQ")
	private Long aoId;

	//管理员ID
	@ManyToOne
    @JoinColumn(name = "AD_ADMIN_ID",nullable=true)
	private SysAdmin sysAdmin=new SysAdmin(); 
	
	//操作模块名称 
	@Column(name = "AO_MODELNAME", nullable = true)
	private String aoModelname;
	
	//操作类型
	@Column(name = "AO_OPERATETYPE", nullable = false)
	private Long aoOperatetype;
	
	//操作内容
	@Column(name = "AO_OPE_CONTENT", nullable = false)
	private String aoOpeContent;
	
	//操作前值
	@Column(name = "AO_VALUE_BEFORE")
	private String aoValueBefore;
	
	//操作后值
	@Column(name = "AO_VALUE_AFTER")
	private String aoValueAfter;
	
	
	//操作内容(辅助)
	@Column(name = "AO_OPE_CONTENT_F")
	private String aoOpeContentF;
	
	//操作前值(辅助)
	@Column(name = "AO_VALUE_BEFORE_F")
	private String aoValueBeforeF;
	
	//操作后值(辅助)
	@Column(name = "AO_VALUE_AFTER_F")
	private String aoValueAfterF;
	
	//操作IP
	@Column(name = "AO_IP")
	private String aoIp;
	
	//操作时间
	@Column(name = "AO_OPRETATETIME",nullable=false,insertable=true,updatable=true,columnDefinition="DATE")
	private Date aoOpretatetime;
	
	//备注
	@Column(name = "AO_REMARK", length = 2000)
	private String aoRemark;

	//操作模块ID
	@Transient
	private String moduleId;
	
	public SysAdminOperatelog() {
		super();
	}
	
	public SysAdminOperatelog(String moduleId, Long aoOperatetype,
			String aoOpeContent, String aoOpeContentF) {
		super();
		this.aoOperatetype = aoOperatetype;
		this.aoOpeContent = aoOpeContent;
		this.aoOpeContentF = aoOpeContentF;
		this.moduleId = moduleId;
	}

	public SysAdminOperatelog(Long aoId, SysAdmin sysAdmin, String aoModelname,
			Long aoOperatetype, String aoOpeContent, String aoValueBefore,
			String aoValueAfter, String aoIp, Date aoOpretatetime,
			String aoRemark) {
		super();
		this.aoId = aoId;
		this.sysAdmin = sysAdmin;
		this.aoModelname = aoModelname;
		this.aoOperatetype = aoOperatetype;
		this.aoOpeContent = aoOpeContent;
		this.aoValueBefore = aoValueBefore;
		this.aoValueAfter = aoValueAfter;
		this.aoIp = aoIp;
		this.aoOpretatetime = aoOpretatetime;
		this.aoRemark = aoRemark;
	}

	public Long getAoId() {
		return aoId;
	}

	public void setAoId(Long aoId) {
		this.aoId = aoId;
	}

	public SysAdmin getSysAdmin() {
		return sysAdmin;
	}

	public void setSysAdmin(SysAdmin sysAdmin) {
		this.sysAdmin = sysAdmin;
	}

	public String getAoModelname() {
		return aoModelname;
	}

	public void setAoModelname(String aoModelname) {
		this.aoModelname = aoModelname;
	}

	public Long getAoOperatetype() {
		return aoOperatetype;
	}

	public void setAoOperatetype(Long aoOperatetype) {
		this.aoOperatetype = aoOperatetype;
	}

	public String getAoOpeContent() {
		return aoOpeContent;
	}

	public void setAoOpeContent(String aoOpeContent) {
		this.aoOpeContent = aoOpeContent;
	}

	public String getAoValueBefore() {
		return aoValueBefore;
	}

	public void setAoValueBefore(String aoValueBefore) {
		this.aoValueBefore = aoValueBefore;
	}

	public String getAoValueAfter() {
		return aoValueAfter;
	}

	public void setAoValueAfter(String aoValueAfter) {
		this.aoValueAfter = aoValueAfter;
	}

	public String getAoIp() {
		return aoIp;
	}

	public void setAoIp(String aoIp) {
		this.aoIp = aoIp;
	}

	public Date getAoOpretatetime() {
		return aoOpretatetime;
	}

	public void setAoOpretatetime(Date aoOpretatetime) {
		this.aoOpretatetime = aoOpretatetime;
	}

	public String getAoRemark() {
		return aoRemark;
	}

	public void setAoRemark(String aoRemark) {
		this.aoRemark = aoRemark;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	/**
	 * @return the aoOpeContentF
	 */
	public String getAoOpeContentF() {
		return aoOpeContentF;
	}

	/**
	 * @param aoOpeContentF the aoOpeContentF to set
	 */
	public void setAoOpeContentF(String aoOpeContentF) {
		this.aoOpeContentF = aoOpeContentF;
	}

	/**
	 * @return the aoValueBeforeF
	 */
	public String getAoValueBeforeF() {
		return aoValueBeforeF;
	}

	/**
	 * @param aoValueBeforeF the aoValueBeforeF to set
	 */
	public void setAoValueBeforeF(String aoValueBeforeF) {
		this.aoValueBeforeF = aoValueBeforeF;
	}

	/**
	 * @return the aoValueAfterF
	 */
	public String getAoValueAfterF() {
		return aoValueAfterF;
	}

	/**
	 * @param aoValueAfterF the aoValueAfterF to set
	 */
	public void setAoValueAfterF(String aoValueAfterF) {
		this.aoValueAfterF = aoValueAfterF;
	}
	

}