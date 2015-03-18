package com.jinxin.model.persistence;

// default package

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: TrackingnoBills映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "CCPS_TRACKINGNO_BILLS")
public class TrackingnoBills implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	@Id
	@Column(name = "TB_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_TRACKINGNO_BILLS_SEQ")
	@SequenceGenerator(name = "CCPS_TRACKINGNO_BILLS_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_TRACKINGNO_BILLS_SEQ")
	private Long tbId;

	// 流水订单号
	@ManyToOne
	@JoinColumn(name = "TB_TR_NO", nullable = false)
	private Traderecord traderecord;

	// 跟踪单号
	@Column(name = "TB_TRACKINGNO")
	private String tbTrackingno;

	// 物流网站
	@Column(name = "TB_LOGISTICS_WEBSITE")
	private String tbLogisticsWebsite;

	// 图片地址
	@Column(name = "TB_PIC_ADD")
	private String tbPicAdd;

	// 添加人
	@Column(name = "TB_LOGIN_NAME", nullable = false)
	private String tbLoginName;

	// 添加时间
	@Column(name = "TB_OPRTIME", nullable = false)
	private Date tbOprtime;

	// 备注
	@Column(name = "TB_REMARK")
	private String tbRemark;

	public Long getTbId() {
		return tbId;
	}

	public void setTbId(Long tbId) {
		this.tbId = tbId;
	}

	public Traderecord getTraderecord() {
		return traderecord;
	}

	public void setTraderecord(Traderecord traderecord) {
		this.traderecord = traderecord;
	}

	public String getTbTrackingno() {
		return tbTrackingno;
	}

	public void setTbTrackingno(String tbTrackingno) {
		this.tbTrackingno = tbTrackingno;
	}

	public String getTbLogisticsWebsite() {
		return tbLogisticsWebsite;
	}

	public void setTbLogisticsWebsite(String tbLogisticsWebsite) {
		this.tbLogisticsWebsite = tbLogisticsWebsite;
	}

	public String getTbPicAdd() {
		return tbPicAdd;
	}

	public void setTbPicAdd(String tbPicAdd) {
		this.tbPicAdd = tbPicAdd;
	}

	public String getTbLoginName() {
		return tbLoginName;
	}

	public void setTbLoginName(String tbLoginName) {
		this.tbLoginName = tbLoginName;
	}

	public Date getTbOprtime() {
		return tbOprtime;
	}

	public void setTbOprtime(Date tbOprtime) {
		this.tbOprtime = tbOprtime;
	}

	public String getTbRemark() {
		return tbRemark;
	}

	public void setTbRemark(String tbRemark) {
		this.tbRemark = tbRemark;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}