package com.jinxin.model.persistence;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBankTag entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "t_bank_tag")
public class TBankTag implements java.io.Serializable {

	// Fields

	private Long id;
	private String configtype;
	private String configname;
	private Timestamp createtime;
	private String createtor;
	
	private Set<TBank> tBank;

	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@GeneratedValue(generator="generator",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="generator",allocationSize=1,initialValue=1,sequenceName="T_BANK_TAG_SEQ")
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "configtype", length = 40)
	public String getConfigtype() {
		return this.configtype;
	}

	public void setConfigtype(String configtype) {
		this.configtype = configtype;
	}

	@Column(name = "configname", length = 20)
	public String getConfigname() {
		return this.configname;
	}

	public void setConfigname(String configname) {
		this.configname = configname;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Timestamp getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Timestamp createtime) {
		this.createtime = createtime;
	}

	@Column(name = "createtor", length = 20)
	public String getCreatetor() {
		return this.createtor;
	}

	public void setCreatetor(String createtor) {
		this.createtor = createtor;
	}

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "t_bank_tag_config", joinColumns=@JoinColumn(name="tag_id"),inverseJoinColumns=@JoinColumn(name="bank_id"))
	public Set<TBank> gettBank() {
		return this.tBank;
	}

	public void settBank(Set<TBank> tBank) {
		this.tBank = tBank;
	}
	
	

}