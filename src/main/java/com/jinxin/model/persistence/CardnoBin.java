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
 * CardnoBin entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CARDNO_BIN")
public class CardnoBin implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1);
	@Id
	@Column(name = "CB_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CARDNO_BIN_SEQ")
	@SequenceGenerator(name = "CCPS_CARDNO_BIN_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CARDNO_BIN_SEQ")
	private Long cbId;
	// 1: VISA; 2: MASTER; 3..
	@Column(name = "CB_CARDTYPE", nullable = false, precision = 38, scale = 0)
	private Integer cbCardtype;
	// 卡bin来源
	@Column(name = "CB_FLAG", nullable = false, precision = 38, scale = 0)
	private Integer cBFlag;
	// 起始卡号
	@Column(name = "CB_START_CARDNO", nullable = false, precision = 38, scale = 0)
	private Long cbStartCardno;
	// 截止卡号
	@Column(name = "CB_END_CARDNO", nullable = false, precision = 38, scale = 0)
	private Long cbEndCardno;

	// 此项由于完全按照原始数据记录, 需要进行相应的转换才能得到记录时间
	@Column(name = "CB_ADDTIME")
	private String cbAddtime;
	// 添加人
	@Column(name = "CB_LOGIN_NAME", nullable = false)
	private String cbLoginName;
	// 添加时间
	@Column(name = "CB_OPRTIME", nullable = false)
	private Date cbOprtime;
	// 备注
	@Column(name = "CB_REMARK")
	private String cbRemark;
	
	/**
	 * @return the cbId
	 */
	public Long getCbId() {
		return cbId;
	}

	/**
	 * @param cbId
	 *            the cbId to set
	 */
	public void setCbId(Long cbId) {
		this.cbId = cbId;
	}

	/**
	 * @return the cbCardtype
	 */
	public Integer getCbCardtype() {
		return cbCardtype;
	}

	/**
	 * @param cbCardtype
	 *            the cbCardtype to set
	 */
	public void setCbCardtype(Integer cbCardtype) {
		this.cbCardtype = cbCardtype;
	}

	/**
	 * @return the cbStartCardno
	 */
	public Long getCbStartCardno() {
		return cbStartCardno;
	}

	/**
	 * @param cbStartCardno
	 *            the cbStartCardno to set
	 */
	public void setCbStartCardno(Long cbStartCardno) {
		this.cbStartCardno = cbStartCardno;
	}

	/**
	 * @return the cbEndCardno
	 */
	public Long getCbEndCardno() {
		return cbEndCardno;
	}

	/**
	 * @param cbEndCardno
	 *            the cbEndCardno to set
	 */
	public void setCbEndCardno(Long cbEndCardno) {
		this.cbEndCardno = cbEndCardno;
	}

	/**
	 * @return the cbAddtime
	 */
	public String getCbAddtime() {
		return cbAddtime;
	}

	/**
	 * @param cbAddtime
	 *            the cbAddtime to set
	 */
	public void setCbAddtime(String cbAddtime) {
		this.cbAddtime = cbAddtime;
	}

	/**
	 * @return the cbLoginName
	 */
	public String getCbLoginName() {
		return cbLoginName;
	}

	/**
	 * @param cbLoginName
	 *            the cbLoginName to set
	 */
	public void setCbLoginName(String cbLoginName) {
		this.cbLoginName = cbLoginName;
	}

	/**
	 * @return the cbOprtime
	 */
	public Date getCbOprtime() {
		return cbOprtime;
	}

	/**
	 * @param cbOprtime
	 *            the cbOprtime to set
	 */
	public void setCbOprtime(Date cbOprtime) {
		this.cbOprtime = cbOprtime;
	}

	/**
	 * @return the cbRemark
	 */
	public String getCbRemark() {
		return cbRemark;
	}

	/**
	 * @param cbRemark
	 *            the cbRemark to set
	 */
	public void setCbRemark(String cbRemark) {
		this.cbRemark = cbRemark;
	}

	public Integer getCBFlag() {
		return cBFlag;
	}

	public void setCBFlag(Integer flag) {
		cBFlag = flag;
	}

}