package com.jinxin.model.persistence;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * <p>Title:</p>
 * <p>Description: CCPS_OPERATIONS_REPORT映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_OPERATIONS_REPORT")
public class OperationsReport  implements java.io.Serializable { 

	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//自增
	 @Id 
	 @Column(name="O_ID", unique=true, nullable=false, precision=38, scale=0)
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_OPERATIONS_REPORT_SEQ")      
     @SequenceGenerator(name="CCPS_OPERATIONS_REPORT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_OPERATIONS_REPORT_SEQ")
     private int oid;
	 
	 //类型
	 @Column(name="O_TYPE", nullable=false)
     private int otype;
	 
	//内容
	 @Column(name="O_CONTENT", nullable=false)
     private String ocontent;
	 
	 //添加时间
	 @Column(name="O_DATETIME", nullable=false)
     private Date odateTime;
	
	//内容
	 @Column(name="O_CREATED", nullable=false)
     private String ocreated;

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public int getOtype() {
		return otype;
	}

	public void setOtype(int otype) {
		this.otype = otype;
	}

	public String getOcontent() {
		return ocontent;
	}

	public void setOcontent(String ocontent) {
		this.ocontent = ocontent;
	}

	public Date getOdateTime() {
		return odateTime;
	}

	public void setOdateTime(Date odateTime) {
		this.odateTime = odateTime;
	}

	public String getOcreated() {
		return ocreated;
	}

	public void setOcreated(String ocreated) {
		this.ocreated = ocreated;
	}
		 

}