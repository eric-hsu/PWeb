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
 * Description: 接口附加角色元素关联表bean
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
@Table(name="CCPS_EXTRA_ROLE_ELE")

public class ExtraRoleEle  implements java.io.Serializable {


	 private static final long serialVersionUID = 1L;
	 // Fields
	 // 记录ID
	 @Id
	 @Column(name="ERE_ID", unique=true, nullable=false, precision=38, scale=0)
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_EXTRA_ROLE_ELE_SEQ")
	 @SequenceGenerator(name = "CCPS_EXTRA_ROLE_ELE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_EXTRA_ROLE_ELE_SEQ")
     private Long ereId;
	 
	 //元素
	 @ManyToOne
     @JoinColumn(name="EE_ID", nullable=false)
     private ExtraElement extraElement;
     
     //角色
	 @ManyToOne
     @JoinColumn(name="ER_ID", nullable=false)
     private Extra_Role extra_Role;
     
     
     
     //操作人账号
	 @Column(name="ERE_LOGIN_NAME")
     private String ereLoginName;
     
     //操作时间
	 @Column(name="ERE_OPRTIME")
     private Date ereOprtime;
     
     //备注
	 @Column(name="ERE_REMARK")
     private String ereRemark;


    // Constructors

    /** default constructor */
    public ExtraRoleEle() {
    }
    
    

    public Long getEreId() {
        return this.ereId;
    }
    
    public void setEreId(Long ereId) {
        this.ereId = ereId;
    }
	

    public ExtraElement getExtraElement() {
        return this.extraElement;
    }
    
    public void setExtraElement(ExtraElement extraElement) {
        this.extraElement = extraElement;
    }
	

    public Extra_Role getExtra_Role() {
        return this.extra_Role;
    }
    
    public void setExtra_Role(Extra_Role extra_Role) {
        this.extra_Role = extra_Role;
    }
    
    

  
    

    public String getEreLoginName() {
        return this.ereLoginName;
    }
    
    public void setEreLoginName(String ereLoginName) {
        this.ereLoginName = ereLoginName;
    }
    
    

    public Date getEreOprtime() {
        return this.ereOprtime;
    }
    
    public void setEreOprtime(Date ereOprtime) {
        this.ereOprtime = ereOprtime;
    }
    
    

    public String getEreRemark() {
        return this.ereRemark;
    }
    
    public void setEreRemark(String ereRemark) {
        this.ereRemark = ereRemark;
    }
   








}