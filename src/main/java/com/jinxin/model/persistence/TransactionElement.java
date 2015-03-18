package com.jinxin.model.persistence;

import java.math.BigDecimal;
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
 * <p>Title:</p>
 * <p>Description: CCPS_TRANSACTION_ELEMENT映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_TRANSACTION_ELEMENT")

public class TransactionElement  implements java.io.Serializable { 

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	 @Id 
	 @Column(name="TE_ID", unique=true, nullable=false, precision=38, scale=0)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_TRANSACTION_ELEMENT_SEQ")      
     @SequenceGenerator(name="CCPS_TRANSACTION_ELEMENT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_TRANSACTION_ELEMENT_SEQ")
     private Long teId;
	 
	 //交易监控元素名
	 @Column(name="TE_ELEMENT_NAME", nullable=false)
     private String teElementName;
	 
	//交易监控元素名(辅助)
	 @Column(name="TE_ELEMENT_NAME_F", nullable=false)
     private String teElementNameF;
	 
	 //风控方法名
	 @Column(name="TE_METHOD_NAME", nullable=false)
     private String teMethodName;
	 
	 //风控路径
	 @Column(name="TE_URL", nullable=false)
     private String teUrl;
     
	 //交易元素值
   	 @Column(name="TE_ELEMENT_VALUE", nullable=false)
     private String teElementValue;
	 
	 //代码执行的位置
	 @Column(name="TE_POSITION", nullable=false, precision=38, scale=0)
      private Integer tePosition;
	 
	 //指同一个位置中,不同风控的执行先后顺序
	 @Column(name="TE_ORDER", nullable=false, precision=38, scale=0)
     private Integer teOrder;
	 
	 //是否初始化 1 是 0 否
	 @Column(name="TE_ISINIT", nullable=false)
     private Long teIsinit;

	 //添加人
	 @Column(name="TE_LOGIN_NAME", nullable=false)
     private String teLoginName;
	 
	 //添加时间
	 @Column(name="TE_OPRTIME", nullable=false)
     private Date teOprtime;
	 
	 //备注
	 @Column(name="TE_REMARK")
     private String teRemark;
	 
	 @OneToMany( mappedBy="transactionElement")
     private Set<MerTranElement> merTranElement = new HashSet<MerTranElement>(0);
	 
	 @Transient
	 private String tePositionName;
	 
	 @Transient
	 private String teOrderValue;//执行顺序名称
	 
	 @Transient
	 private int tradeCount;//正常交易数
	 @Transient
	 private int brUnnCount;//风控异常数
	 @Transient
	 private int brSuccessCount;//成功数
	 @Transient
	 private BigDecimal brTotalAmount;
	 
	 public TransactionElement() {}
	
	 public TransactionElement(String teElementName) {
		this.teElementName = teElementName;
	 }

	public Long getTeId() {
		return teId;
	}

	public void setTeId(Long teId) {
		this.teId = teId;
	}

	public String getTeElementName() {
		return teElementName;
	}

	public void setTeElementName(String teElementName) {
		this.teElementName = teElementName;
	}

	public String getTeElementNameF() {
		return teElementNameF;
	}

	public void setTeElementNameF(String teElementNameF) {
		this.teElementNameF = teElementNameF;
	}

	public String getTeMethodName() {
		return teMethodName;
	}

	public void setTeMethodName(String teMethodName) {
		this.teMethodName = teMethodName;
	}

	public String getTeUrl() {
		return teUrl;
	}

	public void setTeUrl(String teUrl) {
		this.teUrl = teUrl;
	}

	public Integer getTePosition() {
		return tePosition;
	}

	public void setTePosition(Integer tePosition) {
		this.tePosition = tePosition;
	}

	public Integer getTeOrder() {
		return teOrder;
	}

	public void setTeOrder(Integer teOrder) {
		this.teOrder = teOrder;
	}

	public Long getTeIsinit() {
		return teIsinit;
	}

	public void setTeIsinit(Long teIsinit) {
		this.teIsinit = teIsinit;
	}

	public String getTeLoginName() {
		return teLoginName;
	}

	public void setTeLoginName(String teLoginName) {
		this.teLoginName = teLoginName;
	}

	public Date getTeOprtime() {
		return teOprtime;
	}

	public void setTeOprtime(Date teOprtime) {
		this.teOprtime = teOprtime;
	}

	public String getTeRemark() {
		return teRemark;
	}

	public void setTeRemark(String teRemark) {
		this.teRemark = teRemark;
	}

	public Set<MerTranElement> getMerTranElement() {
		return merTranElement;
	}

	public void setMerTranElement(
			Set<MerTranElement> merTranElement) {
		this.merTranElement = merTranElement;
	}

	public String getTePositionName() {
		return tePositionName;
	}

	public void setTePositionName(String tePositionName) {
		this.tePositionName = tePositionName;
	}

	public String getTeOrderValue() {
		return teOrderValue;
	}

	public void setTeOrderValue(String teOrderValue) {
		this.teOrderValue = teOrderValue;
	}

	public int getTradeCount() {
		return tradeCount;
	}

	public void setTradeCount(int tradeCount) {
		this.tradeCount = tradeCount;
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

	public BigDecimal getBrTotalAmount() {
		return brTotalAmount;
	}

	public void setBrTotalAmount(BigDecimal brTotalAmount) {
		this.brTotalAmount = brTotalAmount;
	}

	/**
	 * @return the teElementValue
	 */
	public String getTeElementValue() {
		return this.teElementValue;
	}

	/**
	 * @param teElementValue the teElementValue to set
	 */
	public void setTeElementValue(String teElementValue) {
		this.teElementValue = teElementValue;
	} 
  
}