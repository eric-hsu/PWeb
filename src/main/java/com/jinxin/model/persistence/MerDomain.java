package com.jinxin.model.persistence;

// default package

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

/**
 * <p> Title: 商户的各网关接放号和域名绑定关系表</p>
 * <p>Description: CCPS_MER_DOMAIN映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_DOMAIN")
public class MerDomain implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields

	@Id
	@Column(name = "MD_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_MER_DOMAIN_SEQ")
	@SequenceGenerator(name = "CCPS_MER_DOMAIN_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_MER_DOMAIN_SEQ")
	private Long mdId;
	
	// 商户号
	@Column(name = "MD_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long mdMerNo;
	
	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "MD_GW_NO", precision = 38, scale = 0)
	private Long mdGwNo;
	
	//域名信息
	@ManyToOne 
	@JoinColumn(name = "MD_D_ID", nullable = false)
	private Domain domain;
	
	// 添加人
	@Column(name = "MD_LOGIN_NAME", nullable = false)
	private String mdLoginName;
	
	// 添加时间
	@Column(name = "MD_OPRTIME", nullable = false)
	private Date mdOprtime;
	
	@Column(name = "MD_REMARK")
	private String mdRemark;

	public Long getMdId() {
		return mdId;
	}

	public void setMdId(Long mdId) {
		this.mdId = mdId;
	}

	public Long getMdMerNo() {
		return mdMerNo;
	}

	public void setMdMerNo(Long mdMerNo) {
		this.mdMerNo = mdMerNo;
	}

	public Long getMdGwNo() {
		return mdGwNo;
	}

	public void setMdGwNo(Long mdGwNo) {
		this.mdGwNo = mdGwNo;
	}

	public String getMdLoginName() {
		return mdLoginName;
	}

	public void setMdLoginName(String mdLoginName) {
		this.mdLoginName = mdLoginName;
	}

	public Date getMdOprtime() {
		return mdOprtime;
	}

	public void setMdOprtime(Date mdOprtime) {
		this.mdOprtime = mdOprtime;
	}

	public String getMdRemark() {
		return mdRemark;
	}

	public void setMdRemark(String mdRemark) {
		this.mdRemark = mdRemark;
	}

	public Domain getDomain() {
		return domain;
	}

	public void setDomain(Domain domain) {
		this.domain = domain;
	}

}