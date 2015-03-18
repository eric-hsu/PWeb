package com.jinxin.model.persistence;

import java.math.BigDecimal;
import java.util.Date;
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
import javax.persistence.Transient;

/**
 * <p>Title:</p>
 * <p>Description: CCPS_RISK_ELEMENT映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_RISK_ELEMENT")

public class RiskElement  implements java.io.Serializable { 

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	 @Id 
	 @Column(name="RE_ID", unique=true, nullable=false, precision=38, scale=0)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_RISK_ELEMENT_SEQ")      
    @SequenceGenerator(name="CCPS_RISK_ELEMENT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_RISK_ELEMENT_SEQ")
     private Long reId;
	 
	 //风控元素名
	 @Column(name="RE_ELEMENT_NAME", nullable=false)
     private String reElementName;
	 
	//风控元素名(辅助)
	 @Column(name="RE_ELEMENT_NAME_F", nullable=false)
     private String reElementNameF;
	 
	 //风控方法名
	 @Column(name="RE_METHOD_NAME", nullable=false)
     private String reMethodName;
	 
	 //风控路径
	 @Column(name="RE_URL", nullable=false)
     private String reUrl;
	 
	 //代码执行的位置
	 @Column(name="RE_POSITION", nullable=false, precision=38, scale=0)
      private Integer rePosition;
	 
	 //指同一个位置中,不同风控的执行先后顺序
	 @Column(name="RE_ORDER", nullable=false, precision=38, scale=0)
     private Integer reOrder;
	 
	 //是否初始化 1 是 0 否
	 @Column(name="RE_ISINIT", nullable=false)
     private Long reIsinit;
	 
	 @Transient
	 private int tradeCount;//正常交易数
	 @Transient
	 private int brUnnCount;//风控异常数
	 @Transient
	 private int brSuccessCount;//成功数
	 @Transient
	 private BigDecimal brTotalAmount;
	 
	 
	 
	 /**
	 * @return the reIsinit
	 */
	public Long getReIsinit() {
		return reIsinit;
	}

	/**
	 * @param reIsinit the reIsinit to set
	 */
	public void setReIsinit(Long reIsinit) {
		this.reIsinit = reIsinit;
	}

	//添加人
	 @Column(name="RE_LOGIN_NAME", nullable=false)
     private String reLoginName;
	 
	 //添加时间
	 @Column(name="RE_OPRTIME", nullable=false)
     private Date reOprtime;
	 
	 //备注
	 @Column(name="RE_REMARK")
     private String reRemark;
	 
	 @OneToMany( mappedBy="riskElement")
     private Set<MerRiskElement> merRiskElements = new HashSet<MerRiskElement>(0);
	 
	 @Transient
	 private String rePositionName;
	 @Transient
	 private String reOrderValue;//执行顺序名称
	 
	 public RiskElement() {
	}
	
	public RiskElement(String reElementName) {
		this.reElementName = reElementName;
	}

	public Long getReId() {
		return reId;
	}

	public void setReId(Long reId) {
		this.reId = reId;
	}

	public String getReElementName() {
		return reElementName;
	}

	public void setReElementName(String reElementName) {
		this.reElementName = reElementName;
	}

	public String getReMethodName() {
		return reMethodName;
	}

	public void setReMethodName(String reMethodName) {
		this.reMethodName = reMethodName;
	}

	public String getReUrl() {
		return reUrl;
	}

	public void setReUrl(String reUrl) {
		this.reUrl = reUrl;
	}
 
	public String getReLoginName() {
		return reLoginName;
	}

	public void setReLoginName(String reLoginName) {
		this.reLoginName = reLoginName;
	}

	public Date getReOprtime() {
		return reOprtime;
	}

	public void setReOprtime(Date reOprtime) {
		this.reOprtime = reOprtime;
	}

	public String getReRemark() {
		return reRemark;
	}

	public void setReRemark(String reRemark) {
		this.reRemark = reRemark;
	}

	public Set<MerRiskElement> getMerRiskElements() {
		return merRiskElements;
	}

	public void setMerRiskElements(Set<MerRiskElement> merRiskElements) {
		this.merRiskElements = merRiskElements;
	}

	public String getRePositionName() {
		return rePositionName;
	}

	public Integer getRePosition() {
		return rePosition;
	}

	public Integer getReOrder() {
		return reOrder;
	}

	public void setReOrder(Integer reOrder) {
		this.reOrder = reOrder;
	}

	public String getReOrderValue() {
		return reOrderValue;
	}

	public void setReOrderValue(String reOrderValue) {
		this.reOrderValue = reOrderValue;
	}

	public void setRePosition(Integer rePosition) {
		this.rePosition = rePosition;
	}

	public void setRePositionName(String rePositionName) {
		this.rePositionName = rePositionName;
	}

	/**
	 * @return the reElementNameF
	 */
	public String getReElementNameF() {
		return reElementNameF;
	}

	/**
	 * @param reElementNameF the reElementNameF to set
	 */
	public void setReElementNameF(String reElementNameF) {
		this.reElementNameF = reElementNameF;
	}

	public int getBrUnnCount() {
		return brUnnCount;
	}

	public void setBrUnnCount(int brUnnCount) {
		this.brUnnCount = brUnnCount;
	}

	public int getBrSuccessCount() {
		return brSuccessCount;
	}

	public void setBrSuccessCount(int brSuccessCount) {
		this.brSuccessCount = brSuccessCount;
	}

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
	}

	public BigDecimal getBrTotalAmount() {
		return brTotalAmount;
	}

	public void setBrTotalAmount(BigDecimal brTotalAmount) {
		this.brTotalAmount = brTotalAmount;
	}

  
}