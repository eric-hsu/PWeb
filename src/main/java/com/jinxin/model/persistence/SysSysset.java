package com.jinxin.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: SysSysset映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "SYS_SYSSET")
public class SysSysset implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// SS_ID 自增(1,1);
	@Id
	@Column(name = "SS_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_SYSSET_SEQ")
	@SequenceGenerator(name = "SYS_SYSSET_SEQ",  allocationSize=1,initialValue=1, sequenceName = "SYS_SYSSET_SEQ")
	private Long ssId;

	// 参数名称
	@Column(name = "SS_PARA_NAME", unique = true, nullable = false)
	private String ssParaName;

	// 参数值
	@Column(name = "SS_PARA_VALUE", nullable = false)
	private String ssParaValue;

	// 参数说明
	@Column(name = "SS_REMARK")
	private String ssRemark;

	public Long getSsId() {
		return ssId;
	}

	public void setSsId(Long ssId) {
		this.ssId = ssId;
	}

	public String getSsParaName() {
		return ssParaName;
	}

	public void setSsParaName(String ssParaName) {
		this.ssParaName = ssParaName;
	}

	public String getSsParaValue() {
		return ssParaValue;
	}

	public void setSsParaValue(String ssParaValue) {
		this.ssParaValue = ssParaValue;
	}

	public String getSsRemark() {
		return ssRemark;
	}

	public void setSsRemark(String ssRemark) {
		this.ssRemark = ssRemark;
	}

}