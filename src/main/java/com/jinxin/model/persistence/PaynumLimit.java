package com.jinxin.model.persistence;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
 * <p>Description: CCPS_PAYNUM_LIMIT映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_PAYNUM_LIMIT")

public class PaynumLimit implements java.io.Serializable {

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
     @Id 
     @Column(name="PL_ID", unique=true, nullable=false, precision=38, scale=0)
     @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_PAYNUM_LIMIT_SEQ")      
     @SequenceGenerator(name="CCPS_PAYNUM_LIMIT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_PAYNUM_LIMIT_SEQ")
     private Long plId;
     
     //商户号
     @Column(name="PL_MER_NO", nullable=false, precision=38, scale=0)
     private Long plMerNo;
     
     //网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
     @Column(name="PL_GW_NO", nullable=false, precision=38, scale=0)
     private Long plGwNo;
     
     //指在一个时间范围内,充许支付次数.单位:小时
     @Column(name="PL_HOUR", nullable=false, precision=38, scale=0)
     private double plHour;
     
     //指在一个时间范围内,充许支付次数.单位:毫秒
     @Column(name="PL_MINSECOND", nullable=false, length=0)
     private Long plMinsecond;
     
     //支付成功次数限定
     @Column(name="PL_SUC_COUNT", nullable=false, precision=38, scale=0)
     private Long plSucCount;
     
     //支付总次数限定
     @Column(name="PL_TOTAL_COUNT", nullable=false, precision=38, scale=0)
     private Long plTotalCount;
     
     //添加人
     @Column(name="PL_LOGIN_NAME", nullable=false)
     private String plLoginName;
     
     //添加时间
     @Column(name="PL_OPRTIME", nullable=false)
     private Date plOprtime;
     
     //备注
     @Column(name="PL_REMARK")
     private String plRemark;
     
     @Transient
     private String beName;
     
     //支付次数关联黑名单表
 	 @OneToMany(mappedBy = "paynumLimit")
 	 private List<PaynumLimitRefBlackList> paynumLimitRefBlackLists = new ArrayList<PaynumLimitRefBlackList>(0);

	public Long getPlId() {
		return plId;
	}

	public void setPlId(Long plId) {
		this.plId = plId;
	}

	public Long getPlMerNo() {
		return plMerNo;
	}

	public void setPlMerNo(Long plMerNo) {
		this.plMerNo = plMerNo;
	}

	public Long getPlGwNo() {
		return plGwNo;
	}

	public void setPlGwNo(Long plGwNo) {
		this.plGwNo = plGwNo;
	}

	public double getPlHour() {
		return plHour;
	}

	public void setPlHour(double plHour) {
		this.plHour = plHour;
	}

	public Long getPlMinsecond() {
		return plMinsecond;
	}

	public void setPlMinsecond(Long plMinsecond) {
		this.plMinsecond = plMinsecond;
	}

	public Long getPlSucCount() {
		return plSucCount;
	}

	public void setPlSucCount(Long plSucCount) {
		this.plSucCount = plSucCount;
	}

	public Long getPlTotalCount() {
		return plTotalCount;
	}

	public void setPlTotalCount(Long plTotalCount) {
		this.plTotalCount = plTotalCount;
	}

	public String getPlLoginName() {
		return plLoginName;
	}

	public void setPlLoginName(String plLoginName) {
		this.plLoginName = plLoginName;
	}

	public Date getPlOprtime() {
		return plOprtime;
	}

	public void setPlOprtime(Date plOprtime) {
		this.plOprtime = plOprtime;
	}

	public String getPlRemark() {
		return plRemark;
	}

	public void setPlRemark(String plRemark) {
		this.plRemark = plRemark;
	}


	public List<PaynumLimitRefBlackList> getPaynumLimitRefBlackLists() {
		return paynumLimitRefBlackLists;
	}

	public void setPaynumLimitRefBlackLists(
			List<PaynumLimitRefBlackList> paynumLimitRefBlackLists) {
		this.paynumLimitRefBlackLists = paynumLimitRefBlackLists;
	}

	public String getBeName() {
		return beName;
	}

	public void setBeName(String beName) {
		this.beName = beName;
	}
     
}