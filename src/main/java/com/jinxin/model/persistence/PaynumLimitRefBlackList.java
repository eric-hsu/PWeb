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
 * 
 * <p>Title: </p>
 * <p>Description: 支付次数限定和黑名单元素中间表pojo</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date Sep 29, 2013 11:06:10 AM
 */
@Entity
@Table(name="CCPS_PAYNUM_LIMIT_ELEMENT")
public class PaynumLimitRefBlackList implements java.io.Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//ID号
	@Id
	@Column(name = "PLE_ID")	
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_PAYNUM_LIMIT_ELEMENT_SEQ")      
    @SequenceGenerator(name="CCPS_PAYNUM_LIMIT_ELEMENT_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_PAYNUM_LIMIT_ELEMENT_SEQ")  
	private Long pleId;
	
	// 黑名单ID
	@ManyToOne
    @JoinColumn(name = "PLE_BE_ID")
	private BlackElement blackElement;
	
	// 支付次数ID
	@ManyToOne
    @JoinColumn(name = "PLE_PL_ID")
	private PaynumLimit paynumLimit;

	public Long getPleId() {
		return pleId;
	}

	public void setPleId(Long pleId) {
		this.pleId = pleId;
	}

	public BlackElement getBlackElement() {
		return blackElement;
	}

	public void setBlackElement(BlackElement blackElement) {
		this.blackElement = blackElement;
	}

	public PaynumLimit getPaynumLimit() {
		return paynumLimit;
	}

	public void setPaynumLimit(PaynumLimit paynumLimit) {
		this.paynumLimit = paynumLimit;
	}

	
}
