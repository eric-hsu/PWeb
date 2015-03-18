package com.jinxin.model.persistence;
// default package

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;


/**
 * <p>Title:</p>
 * <p>Description: CCPS_RATE映射汇率bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_RATE")

public class Rate  implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	@Id 
    @Column(name="RATE_ID", unique=true, nullable=false, precision=38, scale=0)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_RATE_SEQ")      
     @SequenceGenerator(name="CCPS_RATE_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_RATE_SEQ")
    private Long rateId;
	
	//原始币种
	@Column(name="RATE_ORIGINAL_CURRENCY", nullable=false)
     private String rateOriginalCurrency;
	
	//目标币种
	@Column(name="RATE_TARGET_CURRENCY", nullable=false)
     private String rateTargetCurrency;
	
	//1: 交易汇率; 2:划款汇率;
	@Column(name="RATE_TYPE", nullable=false, precision=38, scale=0)
      private Integer rateType;
	
	//汇率
	@Column(name="RATE_VALUE", nullable=false, precision=18, scale=4)
     private BigDecimal rateValue;
	
	//添加人
	@Column(name="RATE_LOGIN_NAME", nullable=false)
     private String rateLoginName;
	
	//添加时间
	@Column(name="RATE_OPRTIME", nullable=false)
     private Date rateOprtime;
	
	//备注
	@Column(name="RATE_REMARK")
     private String rateRemark;
	
	@Transient
	private String rateTypeName;

	public Long getRateId() {
		return rateId;
	}

	public void setRateId(Long rateId) {
		this.rateId = rateId;
	}

	public String getRateOriginalCurrency() {
		return rateOriginalCurrency;
	}

	public void setRateOriginalCurrency(String rateOriginalCurrency) {
		this.rateOriginalCurrency = rateOriginalCurrency;
	}

	public String getRateTargetCurrency() {
		return rateTargetCurrency;
	}

	public void setRateTargetCurrency(String rateTargetCurrency) {
		this.rateTargetCurrency = rateTargetCurrency;
	}


	public BigDecimal getRateValue() {
		return rateValue;
	}

	public void setRateValue(BigDecimal rateValue) {
		this.rateValue = rateValue;
	}

	public String getRateLoginName() {
		return rateLoginName;
	}

	public void setRateLoginName(String rateLoginName) {
		this.rateLoginName = rateLoginName;
	}

	public Date getRateOprtime() {
		return rateOprtime;
	}

	public void setRateOprtime(Date rateOprtime) {
		this.rateOprtime = rateOprtime;
	}

	public String getRateRemark() {
		return rateRemark;
	}

	public void setRateRemark(String rateRemark) {
		this.rateRemark = rateRemark;
	}
 

	public String getRateTypeName() {
		return rateTypeName;
	}

	public void setRateTypeName(String rateTypeName) {
		this.rateTypeName = rateTypeName;
	}

	public Integer getRateType() {
		return rateType;
	}

	public void setRateType(Integer rateType) {
		this.rateType = rateType;
	}
	

}