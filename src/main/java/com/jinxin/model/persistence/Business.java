package com.jinxin.model.persistence;

// default package

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
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Business entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_BUSINESS")
public class Business implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "BUS_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_BUSINESS_SEQ")
	@SequenceGenerator(name = "CCPS_BUSINESS_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_BUSINESS_SEQ")
	private Long busId;
	// 行业名称(中文)
	@Column(name = "BUS_NAME_CN", nullable = false)
	private String busNameCn;
	// 行业名称(英文)
	@Column(name = "BUS_NAME_EN", nullable = false)
	private String busNameEn;
	// 添加人
	@Column(name = "BUS_LOGIN_NAME")
	private String busLoginName;
	// 添加时间
	@Column(name = "BUS_OPRTIME", nullable = false)
	private Date busOprtime;
	// 备注
	@Column(name = "BUS_REMARK")
	private String busRemark;
	// 商户表
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "business")
	private Set<Merchant> merchants = new HashSet<Merchant>(0);

	/**
	 * @return the busId
	 */
	public Long getBusId() {
		return busId;
	}

	/**
	 * @param busId
	 *            the busId to set
	 */
	public void setBusId(Long busId) {
		this.busId = busId;
	}

	/**
	 * @return the busNameCn
	 */
	public String getBusNameCn() {
		return busNameCn;
	}

	/**
	 * @param busNameCn
	 *            the busNameCn to set
	 */
	public void setBusNameCn(String busNameCn) {
		this.busNameCn = busNameCn;
	}

	/**
	 * @return the busNameEn
	 */
	public String getBusNameEn() {
		return busNameEn;
	}

	/**
	 * @param busNameEn
	 *            the busNameEn to set
	 */
	public void setBusNameEn(String busNameEn) {
		this.busNameEn = busNameEn;
	}

	/**
	 * @return the busLoginName
	 */
	public String getBusLoginName() {
		return busLoginName;
	}

	/**
	 * @param busLoginName
	 *            the busLoginName to set
	 */
	public void setBusLoginName(String busLoginName) {
		this.busLoginName = busLoginName;
	}

	/**
	 * @return the busOprtime
	 */
	public Date getBusOprtime() {
		return busOprtime;
	}

	/**
	 * @param busOprtime
	 *            the busOprtime to set
	 */
	public void setBusOprtime(Date busOprtime) {
		this.busOprtime = busOprtime;
	}

	/**
	 * @return the busRemark
	 */
	public String getBusRemark() {
		return busRemark;
	}

	/**
	 * @param busRemark
	 *            the busRemark to set
	 */
	public void setBusRemark(String busRemark) {
		this.busRemark = busRemark;
	}

	/**
	 * @return the merchants
	 */
	public Set<Merchant> getMerchants() {
		return merchants;
	}

	/**
	 * @param merchants
	 *            the merchants to set
	 */
	public void setMerchants(Set<Merchant> merchants) {
		this.merchants = merchants;
	}

}