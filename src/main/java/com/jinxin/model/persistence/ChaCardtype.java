package com.jinxin.model.persistence;

// default package

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
 * ChaCardtype entity. @author MyEclipse Persistence Tools
 */
@Entity
@Table(name = "CCPS_CHA_CARDTYPE")
public class ChaCardtype implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields
	// 自增(1,1)
	@Id
	@Column(name = "CC_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_CHA_CARDTYPE_SEQ")
	@SequenceGenerator(name = "CCPS_CHA_CARDTYPE_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_CHA_CARDTYPE_SEQ")
	private Long ccId;
	// 通道表
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CC_CHA_CODE", nullable = false)
	private Channel channel;
//	// 1 visa 2 master
//	@Column(name = "CC_CARDTYPE", nullable = false, precision = 38, scale = 0)
//	private Long ccCardtype;
	
	// 1 visa 2 master
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "CC_CARDTYPE", nullable = false)
	private CardType cardType;
	
	// 通道扣率
	@Column(name = "CC_CHA_RATE", nullable = false, precision = 18)
	private BigDecimal ccChaRate;

	public ChaCardtype() {
	}

	public ChaCardtype(Channel channel, CardType cardType, BigDecimal ccChaRate) {
		this.channel = channel;
		this.cardType = cardType;
		this.ccChaRate = ccChaRate;
	}
	
	

	public ChaCardtype(Long ccId, Channel channel, CardType cardType,
			BigDecimal ccChaRate) {
		super();
		this.ccId = ccId;
		this.channel = channel;
		this.cardType = cardType;
		this.ccChaRate = ccChaRate;
	}

	/**
	 * @return the ccChaRate
	 */
	public BigDecimal getCcChaRate() {
		return ccChaRate;
	}

	/**
	 * @param ccChaRate the ccChaRate to set
	 */
	public void setCcChaRate(BigDecimal ccChaRate) {
		this.ccChaRate = ccChaRate;
	}

	/**
	 * @return the ccId
	 */
	public Long getCcId() {
		return ccId;
	}

	/**
	 * @param ccId
	 *            the ccId to set
	 */
	public void setCcId(Long ccId) {
		this.ccId = ccId;
	}

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public CardType getCardType() {
		return cardType;
	}

	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}

}