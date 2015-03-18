package com.jinxin.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: SysMerButtons映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "SYS_MER_BUTTONS")
public class SysMerButtons implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// mbId自增(1,1)
	@Id
	@Column(name = "MB_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_MER_BUTTONS_SEQ")      
    @SequenceGenerator(name="SYS_MER_BUTTONS_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_MER_BUTTONS_SEQ") 
	private Long mbId;
	
	// 模块ID
	@ManyToOne
    @JoinColumn(name = "MB_MM_ID")
	private SysMerModules sysMerModules = new SysMerModules();
	
	// 按钮名称(中文)
	@Column(name = "MB_NAME_CN", nullable = false)
	private String mbNameCn;
	
	// 按钮名称(英文)
	@Column(name = "MB_NAME_EN", nullable = false)
	private String mbNameEn;
	
	// 按钮图片地址
	@Column(name = "MB_PATH")
	private String mbPath;
	
	// 按钮触发事件,点击该按钮时调用该方法,为空时类型为下拉框
	@Column(name = "MB_ONCLICK")
	private String mbOnclick;
	// 按钮类型 0 :系统默认 ; 1: 用户维护 ;
	@Column(name = "MB_TYPE", nullable = false, precision = 38, scale = 0)
	private Long mbType;
	
	// 按钮备注
	@Column(name = "MB_REMARK")
	private String mbRemark;
	
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysMerButtons")
	private Set<SysMerroleMermod> sysMerroleMermods = new HashSet<SysMerroleMermod>(0);
	
	public SysMerButtons() {
	}

	public SysMerButtons(Long mbId, SysMerModules sysMerModules,
			String mbNameCn, String mbNameEn, String mbPath, String mbOnclick,
			Long mbType, String mbRemark,
			Set<SysMerroleMermod> sysMerroleMermods) {
		super();
		this.mbId = mbId;
		this.sysMerModules = sysMerModules;
		this.mbNameCn = mbNameCn;
		this.mbNameEn = mbNameEn;
		this.mbPath = mbPath;
		this.mbOnclick = mbOnclick;
		this.mbType = mbType;
		this.mbRemark = mbRemark;
		this.sysMerroleMermods = sysMerroleMermods;
	}



	public Long getMbId() {
		return mbId;
	}

	public void setMbId(Long mbId) {
		this.mbId = mbId;
	}

	public SysMerModules getSysMerModules() {
		return sysMerModules;
	}

	public void setSysMerModules(SysMerModules sysMerModules) {
		this.sysMerModules = sysMerModules;
	}

	public String getMbNameCn() {
		return mbNameCn;
	}

	public void setMbNameCn(String mbNameCn) {
		this.mbNameCn = mbNameCn;
	}

	public String getMbNameEn() {
		return mbNameEn;
	}

	public void setMbNameEn(String mbNameEn) {
		this.mbNameEn = mbNameEn;
	}

	public String getMbPath() {
		return mbPath;
	}

	public void setMbPath(String mbPath) {
		this.mbPath = mbPath;
	}

	public String getMbOnclick() {
		return mbOnclick;
	}

	public void setMbOnclick(String mbOnclick) {
		this.mbOnclick = mbOnclick;
	}

	public Long getMbType() {
		return mbType;
	}

	public void setMbType(Long mbType) {
		this.mbType = mbType;
	}

	public String getMbRemark() {
		return mbRemark;
	}

	public void setMbRemark(String mbRemark) {
		this.mbRemark = mbRemark;
	}

	public Set<SysMerroleMermod> getSysMerroleMermods() {
		return sysMerroleMermods;
	}

	public void setSysMerroleMermods(Set<SysMerroleMermod> sysMerroleMermods) {
		this.sysMerroleMermods = sysMerroleMermods;
	}

}