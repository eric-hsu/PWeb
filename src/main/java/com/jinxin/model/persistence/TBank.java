package com.jinxin.model.persistence;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * TBank entity.
 *  @author eric
 */
@Entity
@Table(name = "t_bank")
public class TBank implements java.io.Serializable {

	// Fields

	private Long id;
	private String bankcode;
	private String bankname;
	private Date createtime;
	private String createtor;

	private Set<TBankTag> tBankTag;  
	
	@Id
	//@GeneratedValue(strategy = IDENTITY)
	@GeneratedValue(generator="generator",strategy=GenerationType.SEQUENCE)
	@SequenceGenerator(name="generator",allocationSize=1,initialValue=1,sequenceName="T_BANK_SEQ")
	@Column(name = "id", nullable = false)
	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "bankcode", length = 20)
	public String getBankcode() {
		return this.bankcode;
	}

	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}

	@Column(name = "bankname", length = 20)
	public String getBankname() {
		return this.bankname;
	}

	public void setBankname(String bankname) {
		this.bankname = bankname;
	}

	@Column(name = "createtime", nullable = false, length = 19)
	public Date getCreatetime() {
		return this.createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Column(name = "createtor", length = 20)
	public String getCreatetor() {
		return this.createtor;
	}

	public void setCreatetor(String createtor) {
		this.createtor = createtor;
	}

	@ManyToMany(mappedBy="tBank",cascade=CascadeType.ALL)
	public Set<TBankTag> gettBankTag() {
		return this.tBankTag;
	}

	public void settBankTag(Set<TBankTag> tBankTag) {
		this.tBankTag = tBankTag;
	}

}