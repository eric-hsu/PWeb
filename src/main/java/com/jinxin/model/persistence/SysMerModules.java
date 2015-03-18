package com.jinxin.model.persistence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: SysMerModules映射bean
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
 * @date 2013623
 */
@Entity
@Table(name = "SYS_MER_MODULES")
public class SysMerModules implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields
	// mmId自增(1,1);
	@Id
	@Column(name = "MM_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_MER_MODULES_SEQ")
	@SequenceGenerator(name = "SYS_MER_MODULES_SEQ", allocationSize=1,initialValue=1, sequenceName = "SYS_MER_MODULES_SEQ")
	private Long mmId;

	// 模块名称(中文)
	@Column(name = "MM_NAME_CN", nullable = false)
	private String mmNameCn;

	// 模块名称(英文)
	@Column(name = "MM_NAME_EN", nullable = false)
	private String mmNameEn;

	// 模块链接url
	@Column(name = "MM_URL")
	private String mmUrl;

	// 模块父ID
	@Column(name = "MM_FID", nullable = false, precision = 38, scale = 0)
	private Long mmFid;

	// 模块排列顺序
	@Column(name = "MM_ORDER", precision = 38, scale = 0)
	private Long mmOrder;

	// 模块类型 0 : 系统默认 ; 1: 用户维护 ;
	@Column(name = "MM_TYPE", nullable = false, precision = 38, scale = 0)
	private Long mmType;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "sysMerModules")
	private Set<SysMerroleMermod> sysMerroleMermods = new HashSet<SysMerroleMermod>(0);
	
	// 模块关联按钮
	@OneToMany( fetch = FetchType.LAZY, mappedBy = "sysMerModules")
	private Set<SysMerButtons> sysMerButtonses = new HashSet<SysMerButtons>(0);

	//按钮列表 不映射
	@Transient
	private List<SysMerButtons> buttonList = new ArrayList<SysMerButtons>();
	
	//父模块名称 不映射
	@Transient
	private String mmFName;
	
	public SysMerModules() {
	}
	
	public SysMerModules(Long mmId, String mmNameCn, String mmNameEn,
			String mmUrl, Long mmFid, Long mmOrder, Long mmType,
			Set<SysMerroleMermod> sysMerroleMermods,
			Set<SysMerButtons> sysMerButtonses) {
		super();
		this.mmId = mmId;
		this.mmNameCn = mmNameCn;
		this.mmNameEn = mmNameEn;
		this.mmUrl = mmUrl;
		this.mmFid = mmFid;
		this.mmOrder = mmOrder;
		this.mmType = mmType;
		this.sysMerroleMermods = sysMerroleMermods;
		this.sysMerButtonses = sysMerButtonses;
	}



	public Long getMmId() {
		return mmId;
	}

	public void setMmId(Long mmId) {
		this.mmId = mmId;
	}

	public String getMmNameCn() {
		return mmNameCn;
	}

	public void setMmNameCn(String mmNameCn) {
		this.mmNameCn = mmNameCn;
	}

	public String getMmNameEn() {
		return mmNameEn;
	}

	public void setMmNameEn(String mmNameEn) {
		this.mmNameEn = mmNameEn;
	}

	public String getMmUrl() {
		return mmUrl;
	}

	public void setMmUrl(String mmUrl) {
		this.mmUrl = mmUrl;
	}

	public Long getMmFid() {
		return mmFid;
	}

	public void setMmFid(Long mmFid) {
		this.mmFid = mmFid;
	}

	public Long getMmOrder() {
		return mmOrder;
	}

	public void setMmOrder(Long mmOrder) {
		this.mmOrder = mmOrder;
	}

	public Long getMmType() {
		return mmType;
	}

	public void setMmType(Long mmType) {
		this.mmType = mmType;
	}

	public Set<SysMerroleMermod> getSysMerroleMermods() {
		return sysMerroleMermods;
	}

	public void setSysMerroleMermods(Set<SysMerroleMermod> sysMerroleMermods) {
		this.sysMerroleMermods = sysMerroleMermods;
	}

	public Set<SysMerButtons> getSysMerButtonses() {
		return sysMerButtonses;
	}

	public void setSysMerButtonses(Set<SysMerButtons> sysMerButtonses) {
		this.sysMerButtonses = sysMerButtonses;
	}

	public List<SysMerButtons> getButtonList() {
		return buttonList;
	}

	public void setButtonList(List<SysMerButtons> buttonList) {
		this.buttonList = buttonList;
	}

	public String getMmFName() {
		return mmFName;
	}

	public void setMmFName(String mmFName) {
		this.mmFName = mmFName;
	}

}