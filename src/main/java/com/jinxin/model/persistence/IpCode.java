package com.jinxin.model.persistence;

// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: 记录ip段</p>
 * <p>Description: CCPS_IP_CODE映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name = "CCPS_IP_CODE")
public class IpCode implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// Fields
	// 记录ip段 ID
	@Id
	@Column(name = "IP_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_IP_CODE_SEQ")
	@SequenceGenerator(name = "CCPS_IP_CODE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_IP_CODE_SEQ")
	private Long ipId;

	// IP开始值
	@Column(name = "IP_FROM")
	private String ipFrom;

	// IP结束值
	@Column(name = "IP_TO")
	private String ipTo;
	
	// IP所在国家
	@Column(name = "IP_COUNTRY")
	private String ipCountry;
	
	// 省
	@Column(name = "IP_STATE")
	private String ipState;
	
	// 市
	@Column(name = "IP_CITY")
	private String ipCity;
	
	// ipIsp
	@Column(name = "IP_ISP")
	private String ipIsp;

	public Long getIpId() {
		return ipId;
	}

	public void setIpId(Long ipId) {
		this.ipId = ipId;
	}

	public String getIpFrom() {
		return ipFrom;
	}

	public void setIpFrom(String ipFrom) {
		this.ipFrom = ipFrom;
	}

	public String getIpTo() {
		return ipTo;
	}

	public void setIpTo(String ipTo) {
		this.ipTo = ipTo;
	}

	public String getIpCountry() {
		return ipCountry;
	}

	public void setIpCountry(String ipCountry) {
		this.ipCountry = ipCountry;
	}

	public String getIpState() {
		return ipState;
	}

	public void setIpState(String ipState) {
		this.ipState = ipState;
	}

	public String getIpCity() {
		return ipCity;
	}

	public void setIpCity(String ipCity) {
		this.ipCity = ipCity;
	}

	public String getIpIsp() {
		return ipIsp;
	}

	public void setIpIsp(String ipIsp) {
		this.ipIsp = ipIsp;
	}

}