package com.jinxin.model.persistence;

// default package

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
import javax.persistence.Transient;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 域名管理表bean
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version
 * @date
 */
@Entity
@Table(name = "CCPS_DOMAIN")
public class Domain implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "D_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_DOMAIN_SEQ")
	@SequenceGenerator(name = "CCPS_DOMAIN_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_DOMAIN_SEQ")
	private Long DId;

	// 域名名称
	@Column(name = "D_NAME", nullable = false)
	private String DName;

	// 域名链接
	@Column(name = "D_URL", nullable = false)
	private String DUrl;

	// 域名IP
	@Column(name = "D_IP")
	private String DIp;

	// 状态
	@Column(name = "D_STATUS", nullable = false, precision = 38, scale = 0)
	private int DStatus;

	// 操作人账号
	@Column(name = "D_LOGIN_NAME", nullable = false)
	private String DLoginName;

	// 操作时间
	@Column(name = "D_OPRTIME", nullable = false)
	private Date DOprtime;

	// 备注
	@Column(name = "D_REMARK")
	private String DRemark;

	// 商户域名绑定表
	@OneToMany( mappedBy = "domain")
	private Set<MerDomain> merDomains = new HashSet<MerDomain>(0);
	
	@Transient
	private String status="";//状态
	// Constructors

	/** default constructor */
	public Domain() {
	}

	public Long getDId() {
		return this.DId;
	}

	public void setDId(Long DId) {
		this.DId = DId;
	}

	public String getDName() {
		return this.DName;
	}

	public void setDName(String DName) {
		this.DName = DName;
	}

	public String getDUrl() {
		return this.DUrl;
	}

	public void setDUrl(String DUrl) {
		this.DUrl = DUrl;
	}

	public String getDIp() {
		return this.DIp;
	}

	public void setDIp(String DIp) {
		this.DIp = DIp;
	}


	public String getDLoginName() {
		return this.DLoginName;
	}

	public void setDLoginName(String DLoginName) {
		this.DLoginName = DLoginName;
	}

	public Date getDOprtime() {
		return this.DOprtime;
	}

	public void setDOprtime(Date DOprtime) {
		this.DOprtime = DOprtime;
	}

	public String getDRemark() {
		return this.DRemark;
	}

	public void setDRemark(String DRemark) {
		this.DRemark = DRemark;
	}

	public int getDStatus() {
		return DStatus;
	}

	public void setDStatus(int status) {
		DStatus = status;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Set<MerDomain> getMerDomains() {
		return merDomains;
	}

	public void setMerDomains(Set<MerDomain> merDomains) {
		this.merDomains = merDomains;
	}
	

}