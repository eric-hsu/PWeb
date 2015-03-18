package com.jinxin.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: SysMerroleMermod映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 2013623
 */
@Entity
@Table(name = "SYS_MERROLE_MERMOD")
public class SysMerroleMermod implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	// Fields
	//mrmmId 自增(1,1);
	@Id
	@Column(name = "MRMM_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_MERROLE_MERMOD_SEQ")
	@SequenceGenerator(name = "SYS_MERROLE_MERMOD_SEQ", allocationSize=1,initialValue=1, sequenceName = "SYS_MERROLE_MERMOD_SEQ")
	private long mrmmId;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MRMM_MM_ID", nullable = false)
	private SysMerModules sysMerModules;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MRMM_MR_ID", nullable = false)
	private SysMerRole sysMerRole;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "MRMM_MB_ID")
	private SysMerButtons sysMerButtons;

	public long getMrmmId() {
		return mrmmId;
	}

	public void setMrmmId(long mrmmId) {
		this.mrmmId = mrmmId;
	}

	public SysMerModules getSysMerModules() {
		return sysMerModules;
	}

	public void setSysMerModules(SysMerModules sysMerModules) {
		this.sysMerModules = sysMerModules;
	}

	public SysMerRole getSysMerRole() {
		return sysMerRole;
	}

	public void setSysMerRole(SysMerRole sysMerRole) {
		this.sysMerRole = sysMerRole;
	}

	public SysMerButtons getSysMerButtons() {
		return sysMerButtons;
	}

	public void setSysMerButtons(SysMerButtons sysMerButtons) {
		this.sysMerButtons = sysMerButtons;
	}

}