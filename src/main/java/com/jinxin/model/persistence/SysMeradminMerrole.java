package com.jinxin.model.persistence;


import java.math.BigDecimal;
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
 * <p>Description: SYS_MERADMIN_MERROLE映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="SYS_MERADMIN_MERROLE")

public class SysMeradminMerrole  implements java.io.Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
    @Id 
    @Column(name="MAMR_ID", unique=true, nullable=false, precision=38, scale=0)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SYS_MERADMIN_MERROLE_SEQ")      
    @SequenceGenerator(name="SYS_MERADMIN_MERROLE_SEQ",allocationSize=1,initialValue=1, sequenceName="SYS_MERADMIN_MERROLE_SEQ")  
     private Long mamrId;
    
    //角色ID
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MAMR_MR_ID", nullable=false)
     private SysMerRole sysMerRole;
    
    //管理员ID
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="MAMR_MA_ID", nullable=false)
     private SysMerAdmin sysMerAdmin;

	public Long getMamrId() {
		return mamrId;
	}

	public void setMamrId(Long mamrId) {
		this.mamrId = mamrId;
	}

	public SysMerRole getSysMerRole() {
		return sysMerRole;
	}

	public void setSysMerRole(SysMerRole sysMerRole) {
		this.sysMerRole = sysMerRole;
	}

	public SysMerAdmin getSysMerAdmin() {
		return sysMerAdmin;
	}

	public void setSysMerAdmin(SysMerAdmin sysMerAdmin) {
		this.sysMerAdmin = sysMerAdmin;
	}

}