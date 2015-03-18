package com.jinxin.model.persistence;
// default package

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 接口附加角色表bean
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
@Table(name="CCPS_EXTRA_ROLE")

public class Extra_Role  implements java.io.Serializable {


	 private static final long serialVersionUID = 1L;
	 // Fields
	 // 记录ID
	 @Id
	 @Column(name="ER_ID", unique=true, nullable=false, precision=38, scale=0)
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_EXTRA_ROLE_SEQ")
	 @SequenceGenerator(name = "CCPS_EXTRA_ROLE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_EXTRA_ROLE_SEQ")
     private Long erId;
	 
	 //角色名称(中文)
	 @Column(name="ER_ROLENAME_CN", nullable=false)
     private String erRolenameCn;
     
     //角色名称(英文)
	 @Column(name="ER_ROLENAME_EN", nullable=false)
     private String erRolenameEn;
     
     //操作人账号
	 @Column(name="ER_LOGIN_NAME", nullable=false)
     private String erLoginName;
     
     //操作时间
	 @Column(name="ER_OPRTIME", nullable=false)
     private Date erOprtime;
     
     //备注
	 @Column(name="ER_REMARK")
     private String erRemark;
     
     //接口附加角色元素关联表
	 @OneToMany(mappedBy="extra_Role")
     private Set<ExtraRoleEle> extraRoleEles = new HashSet<ExtraRoleEle>(0);
     
     //网关接入号接口附加角色绑定表
	 @OneToMany(mappedBy="extra_Role")
     private Set<GatewayRole> gatewayRoles = new HashSet<GatewayRole>(0);


    // Constructors

    /** default constructor */
    public Extra_Role() {
    }
    

    public Long getErId() {
        return this.erId;
    }
    
    public void setErId(Long erId) {
        this.erId = erId;
    }
    
    

    public String getErRolenameCn() {
        return this.erRolenameCn;
    }
    
    public void setErRolenameCn(String erRolenameCn) {
        this.erRolenameCn = erRolenameCn;
    }
    
    

    public String getErRolenameEn() {
        return this.erRolenameEn;
    }
    
    public void setErRolenameEn(String erRolenameEn) {
        this.erRolenameEn = erRolenameEn;
    }
    
    

    public String getErLoginName() {
        return this.erLoginName;
    }
    
    public void setErLoginName(String erLoginName) {
        this.erLoginName = erLoginName;
    }
    
    

    public Date getErOprtime() {
		return erOprtime;
	}


	public void setErOprtime(Date erOprtime) {
		this.erOprtime = erOprtime;
	}


	public String getErRemark() {
        return this.erRemark;
    }
    
    public void setErRemark(String erRemark) {
        this.erRemark = erRemark;
    }


    public Set<ExtraRoleEle> getExtraRoleEles() {
        return this.extraRoleEles;
    }
    
    public void setExtraRoleEles(Set<ExtraRoleEle> extraRoleEles) {
        this.extraRoleEles = extraRoleEles;
    }


    public Set<GatewayRole> getGatewayRoles() {
        return this.gatewayRoles;
    }
    
    public void setGatewayRoles(Set<GatewayRole> gatewayRoles) {
        this.gatewayRoles = gatewayRoles;
    }
   








}