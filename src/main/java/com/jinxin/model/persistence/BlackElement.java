package com.jinxin.model.persistence;

// default package

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BlackElement entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_BLACK_ELEMENT")
public class BlackElement implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "BE_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_BLACK_ELEMENT_SEQ")
	@SequenceGenerator(name = "CCPS_BLACK_ELEMENT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_BLACK_ELEMENT_SEQ")
	private Long beId;
	// 元素名称
	@Column(name = "BE_NAME", nullable = false)
	private String beName;
	// 元素代码
	@Column(name = "BE_CODE", nullable = false)
	private String beCode;
	// 添加人
	@Column(name = "BE_LOGIN_NAME", nullable = false)
	private String beLoginName;
	// 添加时间
	@Column(name = "BE_OPRTIME", nullable = false)
	private Date beOprtime;
	// 备注
	@Column(name = "BE_REMARK")
	private String beRemark;
	// 黒名单列表
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "blackElement")
	private Set<BlackList> blackLists = new HashSet<BlackList>(0);
	
	 //支付次数关联黑名单表
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "blackElement")
	private List<PaynumLimitRefBlackList> paynumLimitRefBlackLists = new ArrayList<PaynumLimitRefBlackList>(0);

	/**
	 * @return the beId
	 */
	public Long getBeId() {
		return beId;
	}

	/**
	 * @param beId
	 *            the beId to set
	 */
	public void setBeId(Long beId) {
		this.beId = beId;
	}

	/**
	 * @return the beName
	 */
	public String getBeName() {
		return beName;
	}

	/**
	 * @param beName
	 *            the beName to set
	 */
	public void setBeName(String beName) {
		this.beName = beName;
	}

	/**
	 * @return the beCode
	 */
	public String getBeCode() {
		return beCode;
	}

	/**
	 * @param beCode
	 *            the beCode to set
	 */
	public void setBeCode(String beCode) {
		this.beCode = beCode;
	}

	/**
	 * @return the beLoginName
	 */
	public String getBeLoginName() {
		return beLoginName;
	}

	/**
	 * @param beLoginName
	 *            the beLoginName to set
	 */
	public void setBeLoginName(String beLoginName) {
		this.beLoginName = beLoginName;
	}

	/**
	 * @return the beOprtime
	 */
	public Date getBeOprtime() {
		return beOprtime;
	}

	/**
	 * @param beOprtime
	 *            the beOprtime to set
	 */
	public void setBeOprtime(Date beOprtime) {
		this.beOprtime = beOprtime;
	}

	/**
	 * @return the beRemark
	 */
	public String getBeRemark() {
		return beRemark;
	}

	/**
	 * @param beRemark
	 *            the beRemark to set
	 */
	public void setBeRemark(String beRemark) {
		this.beRemark = beRemark;
	}

	/**
	 * @return the blackLists
	 */
	public Set<BlackList> getBlackLists() {
		return blackLists;
	}

	/**
	 * @param blackLists
	 *            the blackLists to set
	 */
	public void setBlackLists(Set<BlackList> blackLists) {
		this.blackLists = blackLists;
	}

	public List<PaynumLimitRefBlackList> getPaynumLimitRefBlackLists() {
		return paynumLimitRefBlackLists;
	}

	public void setPaynumLimitRefBlackLists(
			List<PaynumLimitRefBlackList> paynumLimitRefBlackLists) {
		this.paynumLimitRefBlackLists = paynumLimitRefBlackLists;
	}

	

}