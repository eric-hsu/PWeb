package com.jinxin.model.persistence;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: TestCard映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_TEST_CARD")
public class TestCard implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// tcId自增(1,1);
	@Id
	@Column(name = "TC_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_TEST_CARD_SEQ")
	@SequenceGenerator(name = "CCPS_TEST_CARD_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_TEST_CARD_SEQ")
	private Long tcId;

	// 商户号
	@Column(name = "TC_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long tcMerNo;

	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "TC_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long tcGwNo;

	// 卡号
	@Column(name = "TC_CARDNO", nullable = false)
	private String tcCardno;

	// 指操作人登入账号
	@Column(name = "TC_LOGIN_NAME", nullable = false)
	private String tcLoginName;

	// 添加时间
	@Column(name = "TC_OPRTIME", nullable = false)
	private Date tcOprtime;

	// 备注
	@Column(name = "TC_REMARK")
	private String tcRemark;

	public Long getTcId() {
		return tcId;
	}

	public void setTcId(Long tcId) {
		this.tcId = tcId;
	}

	public Long getTcMerNo() {
		return tcMerNo;
	}

	public void setTcMerNo(Long tcMerNo) {
		this.tcMerNo = tcMerNo;
	}

	public Long getTcGwNo() {
		return tcGwNo;
	}

	public void setTcGwNo(Long tcGwNo) {
		this.tcGwNo = tcGwNo;
	}

	public String getTcCardno() {
		return tcCardno;
	}

	public void setTcCardno(String tcCardno) {
		this.tcCardno = tcCardno;
	}

	public String getTcLoginName() {
		return tcLoginName;
	}

	public void setTcLoginName(String tcLoginName) {
		this.tcLoginName = tcLoginName;
	}

	public Date getTcOprtime() {
		return tcOprtime;
	}

	public void setTcOprtime(Date tcOprtime) {
		this.tcOprtime = tcOprtime;
	}

	public String getTcRemark() {
		return tcRemark;
	}

	public void setTcRemark(String tcRemark) {
		this.tcRemark = tcRemark;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}