package com.jinxin.model.persistence;

// default package

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 国家名称表bean
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
@Table(name = "CCPS_COUNTRY_NAMES")
public class CountryNames implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	
	// 记录ID
	@Id
	@Column(name = "CN_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_COUNTRY_NAMES_SEQ")
	@SequenceGenerator(name = "CCPS_COUNTRY_NAMES_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_COUNTRY_NAMES_SEQ")
	private Long cnId;

	// 英文简写
	@Column(name = "CN_CODE", nullable = false)
	private String cnCode;

	// 国家名称
	@Column(name = "CN_ZH_NAME")
	private String cnZhName;

	// 国家英文名称
	@Column(name = "CN_EN_ANME")
	private String cnEnAnme;

	// Constructors

	/** default constructor */
	public CountryNames() {
	}

	// Property accessors

	public Long getCnId() {
		return this.cnId;
	}

	public void setCnId(Long cnId) {
		this.cnId = cnId;
	}

	public String getCnCode() {
		return this.cnCode;
	}

	public void setCnCode(String cnCode) {
		this.cnCode = cnCode;
	}

	public String getCnZhName() {
		return this.cnZhName;
	}

	public void setCnZhName(String cnZhName) {
		this.cnZhName = cnZhName;
	}

	public String getCnEnAnme() {
		return this.cnEnAnme;
	}

	public void setCnEnAnme(String cnEnAnme) {
		this.cnEnAnme = cnEnAnme;
	}

}