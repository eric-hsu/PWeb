package com.jinxin.model.persistence;


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
 * <p>Title:</p>
 * <p>Description: OpButtons映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version
 * @date 
 */
@Entity
@Table(name="SYS_AGENT_LOGINLOG")

public class SysAgentLoginlog  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

    @Id
	@Column(name = "ALO_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SYS_AGENT_LOGINLOG_SEQ")
	@SequenceGenerator(name = "SYS_AGENT_LOGINLOG_SEQ", allocationSize=1,initialValue=1, sequenceName = "SYS_AGENT_LOGINLOG_SEQ")
     private Long aloId;
    
    //代理商号;
    @ManyToOne
    @JoinColumn(name="ALO_AGENT_NO" )
     private Agent agent;
    
    //登入IP
    @Column(name="ALO_IP")
     private String aloIp;
    
    //登入时间
    @Column(name="ALO_DATE", nullable=false)
     private Date aloDate;

	public Long getAloId() {
		return aloId;
	}

	public void setAloId(Long aloId) {
		this.aloId = aloId;
	}

	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public String getAloIp() {
		return aloIp;
	}

	public void setAloIp(String aloIp) {
		this.aloIp = aloIp;
	}

	public Date getAloDate() {
		return aloDate;
	}

	public void setAloDate(Date aloDate) {
		this.aloDate = aloDate;
	}
    
}