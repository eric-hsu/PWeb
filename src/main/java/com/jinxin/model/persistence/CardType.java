package com.jinxin.model.persistence;

import java.util.HashSet;
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
 * CardType entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CARDTYPE")
public class CardType implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1409479322744663218L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "CT_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CARDTYPE_SEQ")
	@SequenceGenerator(name = "CCPS_CARDTYPE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CARDTYPE_SEQ")
	private Long ctId;
	@Column(name = "CT_NAME", nullable = false)
	private String ctName;
	@Column(name = "CT_VALUE", nullable = false, precision = 38, scale = 0)
	private Long ctValue;
	
	// 通道卡种表
	@OneToMany(cascade = CascadeType.ALL,  mappedBy = "cardType")
	private Set<ChaCardtype> chaCardtypes = new HashSet<ChaCardtype>(0);
	
	
	public CardType() {
	}

	public CardType(Long ctId) {
		this.ctId = ctId;
	}

	public CardType(Long ctId, String ctName) {
		super();
		this.ctId = ctId;
		this.ctName = ctName;
	}

	/**
	 * @return the ctId
	 */
	public Long getCtId() {
		return ctId;
	}

	/**
	 * @param ctId
	 *            the ctId to set
	 */
	public void setCtId(Long ctId) {
		this.ctId = ctId;
	}

	/**
	 * @return the ctName
	 */
	public String getCtName() {
		return ctName;
	}

	/**
	 * @param ctName
	 *            the ctName to set
	 */
	public void setCtName(String ctName) {
		this.ctName = ctName;
	}

	/**
	 * @return the ctValue
	 */
	public Long getCtValue() {
		return ctValue;
	}

	/**
	 * @param ctValue
	 *            the ctValue to set
	 */
	public void setCtValue(Long ctValue) {
		this.ctValue = ctValue;
	}

	public Set<ChaCardtype> getChaCardtypes() {
		return chaCardtypes;
	}

	public void setChaCardtypes(Set<ChaCardtype> chaCardtypes) {
		this.chaCardtypes = chaCardtypes;
	}

}