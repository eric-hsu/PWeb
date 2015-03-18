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
 * <p> Title: 记录商户各网关接入号 对 支付IP段 的限定</p>
 * <p>Description: CCPS_MER_IP_LIMIT映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_MER_IP_LIMIT")
public class MerIpLimit implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "IL_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MER_IP_LIMIT_SEQ")      
    @SequenceGenerator(name="CCPS_MER_IP_LIMIT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MER_IP_LIMIT_SEQ")
	private Integer ilId;
	
	// 商户号
	@Column(name = "IL_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long ilMerNo;
	
	// 网关接入号,若非0表示对具体网关接入号限制,否则作用于整个商户号
	@Column(name = "IL_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long ilGwNo;
	
	// 开始IP段
	@Column(name = "IL_START_IP", nullable = false)
	private String ilStartIp;
	
	// 开始IP段的值
	@Column(name = "IL_START_IP_VALUE", nullable = false, precision = 38, scale = 0)
	private Long ilStartIpValue;
	
	// 结束IP段
	@Column(name = "IL_END_IP", nullable = false)
	private String ilEndIp;
	
	// 结束IP段的值
	@Column(name = "IL_END_IP_VALUE", nullable = false, precision = 38, scale = 0)
	private Long ilEndIpValue;
	
	// 添加人
	@Column(name = "IL_LOGIN_NAME", nullable = false)
	private String ilLoginName;
	
	// 添加时间
	@Column(name = "IL_OPRTIME", nullable = false)
//	private String ilOprtime;
	private Date ilOprtime;
	
	/**
	 * @return the ilOprtime
	 */
	public Date getIlOprtime() {
		return ilOprtime;
	}

	/**
	 * @param ilOprtime the ilOprtime to set
	 */
	public void setIlOprtime(Date ilOprtime) {
		this.ilOprtime = ilOprtime;
	}

	@Column(name = "IL_REMARK")
	private String ilRemark;

	public Integer getIlId() {
		return ilId;
	}

	public void setIlId(Integer ilId) {
		this.ilId = ilId;
	}

	public Long getIlMerNo() {
		return ilMerNo;
	}

	public void setIlMerNo(Long ilMerNo) {
		this.ilMerNo = ilMerNo;
	}

	public Long getIlGwNo() {
		return ilGwNo;
	}

	public void setIlGwNo(Long ilGwNo) {
		this.ilGwNo = ilGwNo;
	}

	public String getIlStartIp() {
		return ilStartIp;
	}

	public void setIlStartIp(String ilStartIp) {
		this.ilStartIp = ilStartIp;
	}

	public Long getIlStartIpValue() {
		return ilStartIpValue;
	}

	public void setIlStartIpValue(Long ilStartIpValue) {
		this.ilStartIpValue = ilStartIpValue;
	}

	public String getIlEndIp() {
		return ilEndIp;
	}

	public void setIlEndIp(String ilEndIp) {
		this.ilEndIp = ilEndIp;
	}

	public Long getIlEndIpValue() {
		return ilEndIpValue;
	}

	public void setIlEndIpValue(Long ilEndIpValue) {
		this.ilEndIpValue = ilEndIpValue;
	}

	public String getIlLoginName() {
		return ilLoginName;
	}

	public void setIlLoginName(String ilLoginName) {
		this.ilLoginName = ilLoginName;
	}

	public String getIlRemark() {
		return ilRemark;
	}

	public void setIlRemark(String ilRemark) {
		this.ilRemark = ilRemark;
	}

}