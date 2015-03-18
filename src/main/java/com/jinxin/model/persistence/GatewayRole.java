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
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 网关接入号接口附加角色绑定表bean
 * </p>
 * <p>
 * Copyright:
 * </p>
 * <p>
 * Company:
 * </p>
 * 
 * @author
 * @version
 * @date
 */
@Entity
@Table(name = "CCPS_GATEWAY_ROLE")
public class GatewayRole implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "GR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_GATEWAY_ROLE_SEQ")
	@SequenceGenerator(name = "CCPS_GATEWAY_ROLE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_GATEWAY_ROLE_SEQ")
	private Long grId;

	// 附加角色
	@ManyToOne 
	@JoinColumn(name = "GR_ER_ID", nullable = false)
	private Extra_Role extra_Role;

	// 商户号
	@Column(name = "GR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long grMerNo;
	//状态
	 @Column(name="GR_STATUS", nullable=false, precision=38, scale=0)
    private int grStatus;

	// 网关接入号
	@Column(name = "GR_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long grGwNo;

	// 操作人账号
	@Column(name = "GR_LOGIN_NAME", nullable = false)
	private String grLoginName;

	// 操作时间
	@Column(name = "GR_OPRTIME", nullable = false)
	private Date grOprtime;

	// 备注
	@Column(name = "GR_REMARK")
	private String grRemark;

	// Constructors

	/** default constructor */
	public GatewayRole() {
	}

	// Property accessors

	public Long getGrId() {
		return this.grId;
	}

	public void setGrId(Long grId) {
		this.grId = grId;
	}

	 

	public int getGrStatus() {
		return grStatus;
	}

	public void setGrStatus(int grStatus) {
		this.grStatus = grStatus;
	}

	public Extra_Role getExtra_Role() {
		return this.extra_Role;
	}

	public void setExtra_Role(Extra_Role extra_Role) {
		this.extra_Role = extra_Role;
	}

	public Long getGrMerNo() {
		return this.grMerNo;
	}

	public void setGrMerNo(Long grMerNo) {
		this.grMerNo = grMerNo;
	}

	public Long getGrGwNo() {
		return this.grGwNo;
	}

	public void setGrGwNo(Long grGwNo) {
		this.grGwNo = grGwNo;
	}

	public String getGrLoginName() {
		return this.grLoginName;
	}

	public void setGrLoginName(String grLoginName) {
		this.grLoginName = grLoginName;
	}

	public Date getGrOprtime() {
		return this.grOprtime;
	}

	public void setGrOprtime(Date grOprtime) {
		this.grOprtime = grOprtime;
	}

	public String getGrRemark() {
		return this.grRemark;
	}

	public void setGrRemark(String grRemark) {
		this.grRemark = grRemark;
	}

}