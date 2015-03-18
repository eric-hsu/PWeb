package com.jinxin.model.persistence;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jinxin.common.utils.StringHandleUtils;


/**
 * <p>Title:</p>
 * <p>Description: CCPS_MPP_WAREHOUSE映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_MPP_WAREHOUSE")
public class MppWarehouse  implements java.io.Serializable {  
     
	/**
	 * Description: 序列号
	 */
	private static final long serialVersionUID = 1L;

	//自增
	@Id 
    @Column(name="MW_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MPP_WAREHOUSE_SEQ")      
    @SequenceGenerator(name="CCPS_MPP_WAREHOUSE_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MPP_WAREHOUSE_SEQ")
     private Long mwId;
	
	//商户号
	@Column(name="MW_MER_NO", nullable=false, precision=38, scale=0)
    private Long mwMerNo;
	
	//仓库所在地
	@Column(name="MW_ADDRESS")
    private String mwAddress;
	
	//1:自自己的 ; 2 : 租用;
	@Column(name="MW_TYPE", precision=38, scale=0)
    private Long mwType;
	
	//年租金费用;
	@Column(name="MW_ANNUAL_RENTAFEE", precision=18)
     private BigDecimal mwAnnualRentafee;
	
	//平方米;
	 @Column(name="MW_AREA", precision=18)
     private BigDecimal mwArea;
	
	 
	 //全电脑/吨/双/盒;
	 @Column(name="MW_STOCK")
     private String mwStock;
	 
	 //USD;
	 @Column(name="MW_COST", precision=18)
     private BigDecimal mwCost;

	public Long getMwId() {
		return mwId;
	}

	public void setMwId(Long mwId) {
		this.mwId = mwId;
	}

	public Long getMwMerNo() {
		return mwMerNo;
	}

	public void setMwMerNo(Long mwMerNo) {
		this.mwMerNo = mwMerNo;
	}

	public String getMwAddress() {
		return mwAddress;
	}

	public void setMwAddress(String mwAddress) {
		this.mwAddress = mwAddress;
	}

	public Long getMwType() {
		return StringHandleUtils.null2Zero(false, mwType);
	}

	public void setMwType(Long mwType) {
		this.mwType = mwType;
	}

	public BigDecimal getMwAnnualRentafee() {
		return StringHandleUtils.null2Zero(true, mwAnnualRentafee);
	}

	public void setMwAnnualRentafee(BigDecimal mwAnnualRentafee) {
		this.mwAnnualRentafee = mwAnnualRentafee;
	}

	public BigDecimal getMwArea() {
		return StringHandleUtils.null2Zero(true, mwArea);
	}

	public void setMwArea(BigDecimal mwArea) {
		this.mwArea = mwArea;
	}

	public String getMwStock() {
		return mwStock;
	}

	public void setMwStock(String mwStock) {
		this.mwStock = mwStock;
	}

	public BigDecimal getMwCost() {
		return StringHandleUtils.null2Zero(true, mwCost);
	}

	public void setMwCost(BigDecimal mwCost) {
		this.mwCost = mwCost;
	}

}