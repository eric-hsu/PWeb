package com.jinxin.model.persistence;

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
 * <p>Description: SysParaInfo映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */

@Entity
@Table(name="SYS_SYSPARA_INFO")
public class SysSysparaInfo implements java.io.Serializable {
	private static final long serialVersionUID = 1L;
	
	//类型ID 
	@Id
	@Column(name = "SI_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_SYSPARA_INFO_SEQ")      
    @SequenceGenerator(name="SYS_SYSPARA_INFO_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_SYSPARA_INFO_SEQ")  
	private Long siId;
	
	//类型代码
	@ManyToOne
    @JoinColumn(name = "SI_PARA_ID")
	private SysSysparaType sysSysparaType;
	
	//属性值
	@Column(name = "SI_PARA_VALUE",nullable=false,length=60,insertable=true,updatable=true)
	private String siParaValue;
	
	//属性名称
	@Column(name = "SI_PARA_NAME",nullable=false,length=60,insertable=true,updatable=true)
	private String siParaName;

	public Long getSiId() {
		return siId;
	}

	public void setSiId(Long siId) {
		this.siId = siId;
	}

	public SysSysparaType getSysSysparaType() {
		return sysSysparaType;
	}

	public void setSysSysparaType(SysSysparaType sysSysparaType) {
		this.sysSysparaType = sysSysparaType;
	}

	public String getSiParaValue() {
		return siParaValue;
	}

	public void setSiParaValue(String siParaValue) {
		this.siParaValue = siParaValue;
	}

	public String getSiParaName() {
		return siParaName;
	}

	public void setSiParaName(String siParaName) {
		this.siParaName = siParaName;
	}

}