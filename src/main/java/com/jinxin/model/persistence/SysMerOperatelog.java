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
 * <p> Title: </p>
 * <p>Description: SysMerOperatelog映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "SYS_MER_OPERATELOG")
public class SysMerOperatelog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// aoId自增(1,1);
	@Id
	@Column(name = "AO_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_MER_OPERATELOG_SEQ")
	@SequenceGenerator(name = "SYS_MER_OPERATELOG_SEQ", allocationSize=1,initialValue=1, sequenceName = "SYS_MER_OPERATELOG_SEQ")
	private Long aoId;

	@ManyToOne()
	@JoinColumn(name = "AO_MA_ID", nullable = false)
	private SysMerAdmin sysMerAdmin;

	// 操作模块
	@Column(name = "AO_MODELNAME")
	private String aoModelname;

	// 操作类型1 : 增加; 2 : 修改 ; 3 : 删除;
	@Column(name = "AO_OPERATETYPE", nullable = false, precision = 38, scale = 0)
	private Long aoOperatetype;

	// 操作前值
	@Column(name = "AO_VALUE_BEFORE")
	private String aoValueBefore;

	// 操作后值
	@Column(name = "AO_VALUE_AFTER")
	private String aoValueAfter;

	// 操作内容,即对本次操作的详细说明
	@Column(name = "AO_OPRETATE_CONTENT", nullable = false)
	private String aoOpretateContent;
	
	
	//操作内容(辅助)
	@Column(name = "AO_OPRETATE_CONTENT_F")
	private String aoOpretateContentF;
	
	//操作前值(辅助)
	@Column(name = "AO_VALUE_BEFORE_F")
	private String aoValueBeforeF;
	
	//操作后值(辅助)
	@Column(name = "AO_VALUE_AFTER_F")
	private String aoValueAfterF;

	// 操作时间
	@Column(name = "AO_OPRETATETIME", nullable = false)
	private Date aoOpretatetime;

	// 操作时的IP
	@Column(name = "AO_OPRETATE_IP")
	private String aoOpretateIp;

	// 备注
	@Column(name = "AO_REMARK")
	private String aoRemark;
	
	//操作模块ID
	@Transient
	private String moduleId;

	public SysMerOperatelog() {
	}
	
	public SysMerOperatelog(Long aoId, SysMerAdmin sysMerAdmin,
			String aoModelname, Long aoOperatetype, String aoValueBefore,
			String aoValueAfter, String aoOpretateContent, Date aoOpretatetime,
			String aoOpretateIp, String aoRemark, String moduleId) {
		this.aoId = aoId;
		this.sysMerAdmin = sysMerAdmin;
		this.aoModelname = aoModelname;
		this.aoOperatetype = aoOperatetype;
		this.aoValueBefore = aoValueBefore;
		this.aoValueAfter = aoValueAfter;
		this.aoOpretateContent = aoOpretateContent;
		this.aoOpretatetime = aoOpretatetime;
		this.aoOpretateIp = aoOpretateIp;
		this.aoRemark = aoRemark;
		this.moduleId = moduleId;
	}

	public SysMerOperatelog(String moduleId, Long aoOperatetype,
			String aoOpretateContent) {
		super();
		this.aoOperatetype = aoOperatetype;
		this.aoOpretateContent = aoOpretateContent;
		this.moduleId = moduleId;
	}

	public Long getAoId() {
		return aoId;
	}

	public void setAoId(Long aoId) {
		this.aoId = aoId;
	}

	public SysMerAdmin getSysMerAdmin() {
		return sysMerAdmin;
	}

	public void setSysMerAdmin(SysMerAdmin sysMerAdmin) {
		this.sysMerAdmin = sysMerAdmin;
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

	public String getAoOpretateContent() {
		return aoOpretateContent;
	}

	public void setAoOpretateContent(String aoOpretateContent) {
		this.aoOpretateContent = aoOpretateContent;
	}

	public Date getAoOpretatetime() {
		return aoOpretatetime;
	}

	public void setAoOpretatetime(Date aoOpretatetime) {
		this.aoOpretatetime = aoOpretatetime;
	}

	public String getAoOpretateIp() {
		return aoOpretateIp;
	}

	public void setAoOpretateIp(String aoOpretateIp) {
		this.aoOpretateIp = aoOpretateIp;
	}

	public String getAoRemark() {
		return aoRemark;
	}

	public void setAoRemark(String aoRemark) {
		this.aoRemark = aoRemark;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}



	/**
	 * @return the aoOpretateContentF
	 */
	public String getAoOpretateContentF() {
		return aoOpretateContentF;
	}

	/**
	 * @param aoOpretateContentF the aoOpretateContentF to set
	 */
	public void setAoOpretateContentF(String aoOpretateContentF) {
		this.aoOpretateContentF = aoOpretateContentF;
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