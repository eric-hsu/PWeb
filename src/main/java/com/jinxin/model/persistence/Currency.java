package com.jinxin.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * Currency entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name="CCPS_CURRENCY")

public class Currency  implements java.io.Serializable {


    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields    
	 @Id 
	    @Column(name="CURR_ID", unique=true, nullable=false, precision=38, scale=0)
	    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CURRENCY_SEQ")
	@SequenceGenerator(name = "CCPS_CURRENCY_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CURRENCY_SEQ")
     private Long currId;
	 @Column(name="CURR_NAME", nullable=false)
     private String currName;
	 @Column(name="CURR_VALUE", nullable=false)
     private String currValue;
	/**
	 * @return the currId
	 */
	public Long getCurrId() {
		return currId;
	}
	/**
	 * @param currId the currId to set
	 */
	public void setCurrId(Long currId) {
		this.currId = currId;
	}
	/**
	 * @return the currName
	 */
	public String getCurrName() {
		return currName;
	}
	/**
	 * @param currName the currName to set
	 */
	public void setCurrName(String currName) {
		this.currName = currName;
	}
	/**
	 * @return the currValue
	 */
	public String getCurrValue() {
		return currValue;
	}
	/**
	 * @param currValue the currValue to set
	 */
	public void setCurrValue(String currValue) {
		this.currValue = currValue;
	}


  
   

   
}