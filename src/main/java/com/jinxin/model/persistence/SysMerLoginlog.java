package com.jinxin.model.persistence;

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
 * <p>Description: SysMerLoginlog映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "SYS_MER_LOGINLOG")
public class SysMerLoginlog implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// ML_ID自增(1,1);
	@Id
	@Column(name = "ML_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_MER_LOGINLOG_SEQ")
	@SequenceGenerator(name = "SYS_MER_LOGINLOG_SEQ", allocationSize=1,initialValue=1, sequenceName = "SYS_MER_LOGINLOG_SEQ")
	private Long mlId;

	@ManyToOne()
	@JoinColumn(name = "ML_MA_ID", nullable = false)
	private SysMerAdmin sysMerAdmin;

	// 登入IP
	@Column(name = "ML_IP")
	private String mlIp;

	// 登入时间
	@Column(name = "ML_DATE", nullable = false)
	private Date mlDate;

	public Long getMlId() {
		return mlId;
	}

	public void setMlId(Long mlId) {
		this.mlId = mlId;
	}

	public SysMerAdmin getSysMerAdmin() {
		return sysMerAdmin;
	}

	public void setSysMerAdmin(SysMerAdmin sysMerAdmin) {
		this.sysMerAdmin = sysMerAdmin;
	}

	public String getMlIp() {
		return mlIp;
	}

	public void setMlIp(String mlIp) {
		this.mlIp = mlIp;
	}

	public Date getMlDate() {
		return mlDate;
	}

	public void setMlDate(Date mlDate) {
		this.mlDate = mlDate;
	}

}