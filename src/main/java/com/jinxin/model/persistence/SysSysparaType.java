package com.jinxin.model.persistence;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p> Title: </p>
 * <p>Description: SysParaType映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */

@Entity
@Table(name="SYS_SYSPARA_TYPE")
public class SysSysparaType implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	//类型ID
	@Id
	@Column(name = "ST_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_SYSPARA_TYPE_SEQ")      
    @SequenceGenerator(name="SYS_SYSPARA_TYPE_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_SYSPARA_TYPE_SEQ")  
	private Long stId;
	
	//类型代码
	@Column(name = "ST_CODE",nullable=false,length=40,insertable=true,updatable=true)
	private String stCode;
	
	//类型描述
	@Column(name = "ST_TYPE_DES",nullable=false,length=200,insertable=true,updatable=true)
	private String stTypeDes;	
	
	//对应系统参数信息
	@OneToMany(mappedBy = "sysSysparaType")
	private Set<SysSysparaInfo> sysSysparaInfos = new HashSet<SysSysparaInfo>(0);

	public Long getStId() {
		return stId;
	}

	public void setStId(Long stId) {
		this.stId = stId;
	}

	public String getStCode() {
		return stCode;
	}

	public void setStCode(String stCode) {
		this.stCode = stCode;
	}

	public String getStTypeDes() {
		return stTypeDes;
	}

	public void setStTypeDes(String stTypeDes) {
		this.stTypeDes = stTypeDes;
	}

	public Set<SysSysparaInfo> getSysSysparaInfos() {
		return sysSysparaInfos;
	}

	public void setSysSysparaInfos(Set<SysSysparaInfo> sysSysparaInfos) {
		this.sysSysparaInfos = sysSysparaInfos;
	}

}