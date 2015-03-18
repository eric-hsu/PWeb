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
 * BlackList entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_BLACKLIST")
public class BlackList implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "BV_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_BLACKLIST_SEQ")
	@SequenceGenerator(name = "CCPS_BLACKLIST_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_BLACKLIST_SEQ")
	private Long bvId;
	// 黑名单元素
	@ManyToOne
	@JoinColumn(name = "BV_BE_ID", nullable = false)
	private BlackElement blackElement;
	// 元素的值
	@Column(name = "BV_VALUE", nullable = false)
	private String bvValue;
	// 1 : 使用 ; 0 : 停用 ;
	@Column(name = "BV_STATUS", nullable = false, precision = 38, scale = 0)
	private int bvStatus;
	
	// 11 系统自动添加 2手动添加
	@Column(name = "BV_TYPE", nullable = false, precision = 38, scale = 0)
	private int bvType;
	/**
	 * @return the bvType
	 */
	public int getBvType() {
		return bvType;
	}

	/**
	 * @param bvType the bvType to set
	 */
	public void setBvType(int bvType) {
		this.bvType = bvType;
	}

	// 系统自动添加时可以为空
	@Column(name = "BV_LOGIN_NAME")
	private String bvLoginName;
	// 添加时间
	@Column(name = "BV_OPRTIME", nullable = false)
	private Date bvOprtime;
	// 备注
	@Column(name = "BV_REMARK")
	private String bvRemark;

	/**
	 * @return the bvId
	 */
	public Long getBvId() {
		return bvId;
	}

	/**
	 * @param bvId
	 *            the bvId to set
	 */
	public void setBvId(Long bvId) {
		this.bvId = bvId;
	}

	/**
	 * @return the blackElement
	 */
	public BlackElement getBlackElement() {
		return blackElement;
	}

	/**
	 * @param blackElement
	 *            the blackElement to set
	 */
	public void setBlackElement(BlackElement blackElement) {
		this.blackElement = blackElement;
	}

	/**
	 * @return the bvValue
	 */
	public String getBvValue() {
		return bvValue;
	}

	/**
	 * @param bvValue
	 *            the bvValue to set
	 */
	public void setBvValue(String bvValue) {
		this.bvValue = bvValue;
	}

	/**
	 * @return the bvStatus
	 */
	public int getBvStatus() {
		return bvStatus;
	}

	/**
	 * @param bvStatus
	 *            the bvStatus to set
	 */
	public void setBvStatus(int bvStatus) {
		this.bvStatus = bvStatus;
	}

	/**
	 * @return the bvLoginName
	 */
	public String getBvLoginName() {
		return bvLoginName;
	}

	/**
	 * @param bvLoginName
	 *            the bvLoginName to set
	 */
	public void setBvLoginName(String bvLoginName) {
		this.bvLoginName = bvLoginName;
	}

	/**
	 * @return the bvOprtime
	 */
	public Date getBvOprtime() {
		return bvOprtime;
	}

	/**
	 * @param bvOprtime
	 *            the bvOprtime to set
	 */
	public void setBvOprtime(Date bvOprtime) {
		this.bvOprtime = bvOprtime;
	}

	/**
	 * @return the bvRemark
	 */
	public String getBvRemark() {
		return bvRemark;
	}

	/**
	 * @param bvRemark
	 *            the bvRemark to set
	 */
	public void setBvRemark(String bvRemark) {
		this.bvRemark = bvRemark;
	}

}