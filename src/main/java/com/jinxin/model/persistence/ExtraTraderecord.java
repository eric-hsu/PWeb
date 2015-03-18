package com.jinxin.model.persistence;
// default package

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;/**
 * <p> Title: </p>
 * <p>Description: EmailTemplate映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author   
 * @version 
 * @date 2013623
 */
@Entity
@Table(name="CCPS_EXTRA_TRADERECORD")

public class ExtraTraderecord  implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	// Fields    
	//记录ID
	@Id 
    @Column(name="ET_ID", nullable=false, precision=38, scale=0)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_EXTRA_TRADERECORD_SEQ")
	@SequenceGenerator(name = "CCPS_EXTRA_TRADERECORD_SEQ", allocationSize=1,initialValue=1, sequenceName = "CCPS_EXTRA_TRADERECORD_SEQ")
    private Long etId;
	
	//流水订单号
	 @Column(name="ET_TR_NO", unique=true, nullable=false) 
     private String etTrNo; 
     
	 //持卡人注册的用户名
	  @Column(name="ET_USERNAME")
     private String etUsername;    
     
	  //用户名注册时间
	  @Column(name="ET_REGISTERDATE")
     private String etRegisterdate;    
     
	  //加密存储
	  @Column(name="ET_PASSWORD")
     private String etPassword;    
     
	  //检测 密码是否复制粘贴
	  @Column(name="ET_ISCOPY", precision=38, scale=0)
     private String etIscopy;    
     
	  //性别 1: 男 0:女
	  @Column(name="ET_FALE", precision=38, scale=0)
//	  private Long etFale;    
	  private String etFale;    	  
 

	//机器唯一值
	  @Column(name="ET_FINGERPRINT")
     private String etFingerprint;    
     
	  //购买的商品或者服务名称
	  @Column(name="ET_GOODSNAME")
     private String etGoodsname;    
     
	  //商户或者服务数量
	  @Column(name="ET_GOODSNUM", precision=38, scale=0)
     private String etGoodsnum;    
     
	 //购买的商品或者服务在该网站上的所有商品中的价格区间 （1:高、2:中、3:低）
	  @Column(name="ET_PRICETYPE", precision=38, scale=0)
//     private Long etPricetype;    Integer
	  private String etPricetype;    
	  
	  //发货方式
	  @Column(name="ET_DELIVERYWAY")
     private String etDelivery;    
     
	  //发货费用
	  @Column(name="ET_DELIVERYPRICE")
	  private String etDeliveryPrice;
	  
	  //发收货地址
	  @Column(name="ET_RECEIVING_ADD")
     private String etReceivingAdd;    
     
	  //是否使用代金券 1 :是 0: 否
	  @Column(name="ET_COUPONS", precision=38, scale=0)
//     private Long etCoupons;    
	  private String etCoupons;    	  
	  
     
	  //是否索要礼品 1 :是 0 : 否
	  @Column(name="ET_GIFT", precision=38, scale=0)
//     private Long etGift;    
	  private String etGift;    	  
     
	  //持卡人在网站上的停留时间 精确到毫秒
	  @Column(name="ET_STAYTIME", precision=38, scale=0)
     private String etStaytime;    
	  
	  //聊天记录是否少于10条 1 :是 0 :否
	  @Column(name="ET_CHAT", precision=38, scale=0)
//     private Long etChat;    
	  private String etChat;    
	  
     
	  //客户是否直接输入域名找到本站 1 :是  0 :否
	  @Column(name="ET_FIND", precision=38, scale=0)
//	  private Long etFind;    
	  private String etFind;    	  
	  
     
	  //对商品或服务是否有反馈或意见 1 :是  0 :否
	  @Column(name="ET_OPINION", precision=38, scale=0)
//     private Long etOpinion;    
      private String etOpinion;         
	  
	  //上一次充值对应的游戏名称
	  @Column(name="ET_GAMENAME")
     private String etGamename;    
     
	  //上一次游戏对应的价格区间
	  @Column(name="ET_GAMEPRICE")
     private String etGameprice;    
     
	  //上一次游戏对应的游戏开奖次数
	  @Column(name="ET_GAMETIMES", precision=38, scale=0)
     private String etGametimes;    
     
	  //上一次游戏对应的开奖时间
	  @Column(name="ET_GAMEDATE")
     private String etGamedate;

	  
	 //商户或者服务单价 
	 @Column(name="ET_GOODSPRICE")
	 private String etGoodsPrice;
	  
	/**
	 * @return the etGoodsPrice
	 */
	public String getEtGoodsPrice() {
		return etGoodsPrice;
	}

	/**
	 * @param etGoodsPrice the etGoodsPrice to set
	 */
	public void setEtGoodsPrice(String etGoodsPrice) {
		this.etGoodsPrice = etGoodsPrice;
	}

	public Long getEtId() {
		return etId;
	}

	public void setEtId(Long etId) {
		this.etId = etId;
	}

	public String getEtTrNo() {
		return etTrNo;
	}

	public void setEtTrNo(String etTrNo) {
		this.etTrNo = etTrNo;
	}

	public String getEtUsername() {
		return etUsername;
	}

	public void setEtUsername(String etUsername) {
		this.etUsername = etUsername;
	}

	public String getEtRegisterdate() {
		return etRegisterdate;
	}

	public void setEtRegisterdate(String etRegisterdate) {
		this.etRegisterdate = etRegisterdate;
	}

	public String getEtPassword() {
		return etPassword;
	}

	public void setEtPassword(String etPassword) {
		this.etPassword = etPassword;
	}


	public String getEtFingerprint() {
		return etFingerprint;
	}

	public void setEtFingerprint(String etFingerprint) {
		this.etFingerprint = etFingerprint;
	}

	public String getEtGoodsname() {
		return etGoodsname;
	}

	public void setEtGoodsname(String etGoodsname) {
		this.etGoodsname = etGoodsname;
	}

	public String getEtDelivery() {
		return etDelivery;
	}

	public void setEtDelivery(String etDelivery) {
		this.etDelivery = etDelivery;
	}

	public String getEtReceivingAdd() {
		return etReceivingAdd;
	}

	public void setEtReceivingAdd(String etReceivingAdd) {
		this.etReceivingAdd = etReceivingAdd;
	}


	public String getEtGamename() {
		return etGamename;
	}

	public void setEtGamename(String etGamename) {
		this.etGamename = etGamename;
	}

	public String getEtGameprice() {
		return etGameprice;
	}

	public void setEtGameprice(String etGameprice) {
		this.etGameprice = etGameprice;
	}

	public String getEtGamedate() {
		return etGamedate;
	}

	public void setEtGamedate(String etGamedate) {
		this.etGamedate = etGamedate;
	}

	/**
	 * @return the etIscopy
	 */
	public String getEtIscopy() {
		return etIscopy;
	}

	/**
	 * @param etIscopy the etIscopy to set
	 */
	public void setEtIscopy(String etIscopy) {
		this.etIscopy = etIscopy;
	}

	/**
	 * @return the etFale
	 */
	public String getEtFale() {
		return etFale;
	}

	/**
	 * @param etFale the etFale to set
	 */
	public void setEtFale(String etFale) {
		this.etFale = etFale;
	}

	/**
	 * @return the etGoodsnum
	 */
	public String getEtGoodsnum() {
		return etGoodsnum;
	}

	/**
	 * @param etGoodsnum the etGoodsnum to set
	 */
	public void setEtGoodsnum(String etGoodsnum) {
		this.etGoodsnum = etGoodsnum;
	}

	/**
	 * @return the etPricetype
	 */
	public String getEtPricetype() {
		return etPricetype;
	}

	/**
	 * @param etPricetype the etPricetype to set
	 */
	public void setEtPricetype(String etPricetype) {
		this.etPricetype = etPricetype;
	}

	/**
	 * @return the etCoupons
	 */
	public String getEtCoupons() {
		return etCoupons;
	}

	/**
	 * @param etCoupons the etCoupons to set
	 */
	public void setEtCoupons(String etCoupons) {
		this.etCoupons = etCoupons;
	}

	/**
	 * @return the etGift
	 */
	public String getEtGift() {
		return etGift;
	}

	/**
	 * @param etGift the etGift to set
	 */
	public void setEtGift(String etGift) {
		this.etGift = etGift;
	}

	/**
	 * @return the etStaytime
	 */
	public String getEtStaytime() {
		return etStaytime;
	}

	/**
	 * @param etStaytime the etStaytime to set
	 */
	public void setEtStaytime(String etStaytime) {
		this.etStaytime = etStaytime;
	}

	/**
	 * @return the etChat
	 */
	public String getEtChat() {
		return etChat;
	}

	/**
	 * @param etChat the etChat to set
	 */
	public void setEtChat(String etChat) {
		this.etChat = etChat;
	}

	/**
	 * @return the etFind
	 */
	public String getEtFind() {
		return etFind;
	}

	/**
	 * @param etFind the etFind to set
	 */
	public void setEtFind(String etFind) {
		this.etFind = etFind;
	}

	/**
	 * @return the etOpinion
	 */
	public String getEtOpinion() {
		return etOpinion;
	}

	/**
	 * @param etOpinion the etOpinion to set
	 */
	public void setEtOpinion(String etOpinion) {
		this.etOpinion = etOpinion;
	}

	/**
	 * @return the etGametimes
	 */
	public String getEtGametimes() {
		return etGametimes;
	}

	/**
	 * @param etGametimes the etGametimes to set
	 */
	public void setEtGametimes(String etGametimes) {
		this.etGametimes = etGametimes;
	}

	/**
	 * @return the etDeliveryPrice
	 */
	public String getEtDeliveryPrice() {
		return etDeliveryPrice;
	}

	/**
	 * @param etDeliveryPrice the etDeliveryPrice to set
	 */
	public void setEtDeliveryPrice(String etDeliveryPrice) {
		this.etDeliveryPrice = etDeliveryPrice;
	}


}