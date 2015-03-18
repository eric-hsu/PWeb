package com.jinxin.model.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.jinxin.common.utils.StringHandleUtils;

/**
 * <p>Title:</p>
 * <p>Description: CCPS_MPP_OWNER映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */

@Entity
@Table(name="CCPS_MPP_SERVICES")
public class MppServices  implements java.io.Serializable {
	 
	 /**
	 * Description: 序列号
	 */
	private static final long serialVersionUID = 1L;

	//自增列
	 @Id 
	 @Column(name="MS_ID")
	 @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MPP_SERVICES_SEQ")      
     @SequenceGenerator(name="CCPS_MPP_SERVICES_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MPP_SERVICES_SEQ")
     private Long msId;
	 
	 //与商户表关联
	 @Column(name="MS_MER_NO", precision=38, scale=0)
     private Long msMerNo;
	 
	 
	 //1: visa卡;   2 :master卡;   3 : AE卡;
	 @Column(name="MS_CREDITCRADS", precision=38, scale=0)
     private Long msCreditcrads;
	 
	 //1 : 信用卡  2: 其他付款方式 
	 @Column(name="MS_PAYTYPE", precision=38, scale=0)
     private Long msPaytype;
	 
	 //姓名1
	 @Column(name="MS_NAME1")
     private String msName1;
	 
	 
	 @Column(name="MS_SE1")
     private String msSe1;
	 
	 //姓名2
	 @Column(name="MS_NAME2")
     private String msName2;
	 
	 @Column(name="MS_SE2")
     private String msSe2;
	 
	 //姓名3
	 @Column(name="MS_NAME3")
     private String msName3;
	 
	 @Column(name="MS_SE3")
     private String msSe3;
	 
	 //核心服务或产品的描述
	 @Column(name="MS_DESCRIPTION")
     private String msDescription;
	 
	 //包括现金、信用卡、借记卡
	 @Column(name="MS_YEAR_AMOUNT")
     private String msYearAmount;
	 
	 //visa、master
	 @Column(name="MS_CREDIT_YEAR_AMOUNT")
     private String msCreditYearAmount;
	 
	 //visa、master
	 @Column(name="MS_AVERAGE_TICKET")
     private String msAverageTicket;
	 
	 //平均每张单的价值
	 @Column(name="MS_CUP_AMOUNT")
     private String msCupAmount;
	 
	 //每年JCB生意
	 @Column(name="MS_JCB_AMOUNT")
     private String msJcbAmount;
	 
	 //每年DCC生意
	 @Column(name="MS_DCC_AMOUNT")
     private String msDccAmount;
	 
	 //平均每张单的价值 (CUP/JCB/DCC)
	 @Column(name="MS_CJD_AVERAGE")
     private String msCjdAverage;
	 
	 //1:互联网 ;  2: 邮递、电话落收单 ;   3; 面对面信用卡收单   如果有使用
	 @Column(name="MS_TRADE_TYPE", precision=38, scale=0)
     private Long msTradeType;
	 
	 //互联网占销售总额的百分比
	 @Column(name="MS_INTERNET_OPTIONAL")
     private String msInternetOptional;
	 
	 //邮递、电话落收单占销售总额的百分比
	 @Column(name="MS_MOTO_OPTIONAL")
     private String msMotoOptional;
	 
	 //面对面信用卡收单占销售总额的百分比
	 @Column(name="MS_FACE_OPTIONAL")
     private String msFaceOptional;
	 
	 //B对B的百分比
	 @Column(name="MS_B_TO_B")
     private String msBToB;
	 
	 //B对C的百分比
	 @Column(name="MS_B_TO_C")
     private String msBToC;
	 
	 //B对B+B对C的销售额百分比（一定要等于100%）
	 @Column(name="MS_TOTAL")
     private String msTotal;
	 
	 //0天的百分比
	 @Column(name="MS_ZERODAYS")
     private String msZerodays;
	 
	 //1-7天的百分比
	 @Column(name="MS_ONE_DAYS")
     private String msOneDays;
	 
	 //8-14天的百分比
	 @Column(name="MS_EIGHT_DAYS")
     private String msEightDays;
	 
	 //15-30天的百分比
	 @Column(name="MS_FIFTEEN_DAYS")
     private String msFifteenDays;
	 
	 //超过30天的百分比
	 @Column(name="MS_OVER_THIRTY_DAYS")
     private String msOverThirtyDays;
	 
	 //总的百分比
	 @Column(name="MS_PER_TOTAL")
     private String msPerTotal;
	 
	 //你的客户需要付定金/分期/先付   0否 1是
	 @Column(name="MS_ISDEPOSIT", precision=38, scale=0)
     private Long msIsdeposit;
	 
	 //需付定金交易百份比
	 @Column(name="MS_PER_DEPOSIT")
     private String msPerDeposit;
	 
	 //需付分期交易百份比
	 @Column(name="MS_PER_PAY")
     private String msPerPay;
	 
	 //付款后需要多久送货
	 @Column(name="MS_DELIVERY")
     private String msDelivery;
	 
	 //1 : 是; 0 : 否;
	 @Column(name="MS_TRANSACTIONS", precision=38, scale=0)
     private Long msTransactions;
	 
	 //1:全额退款 ; 2:以物易物 ;  3:不退款;
	 @Column(name="MS_REFUNDMENT", precision=38, scale=0)
     private Long msRefundment;
	 
	 //1: 0-3 ; 2 :4-7 ;   3: 8-14 ;   4:超过14天;
	 @Column(name="MS_SUB_REFUND", precision=38, scale=0)
     private Long msSubRefund;
	 
	 //1 : 是; 0 : 否;
	 @Column(name="MS_ACCEPT", precision=38, scale=0)
     private Long msAccept;
	 
	 //请提供信用卡处理机构名称
	 @Column(name="MS_PRO_NAME")
     private String msProName;
	 
	 //以往的信用卡处理机构商户号
	 @Column(name="MS_MER_NUMBER")
     private String msMerNumber;
	 
	 //1 : 是; 0 : 否;   如果是,才有该项
	 @Column(name="MS_PROCESS_CARD", precision=38, scale=0)
     private Long msProcessCard;
	 
	 //第三方姓名
	 @Column(name="MS_PARTY_NAME")
     private String msPartyName;
	 
	 //网址
	 @Column(name="MS_URL")
     private String msUrl;
	 
	 //登记日期
	 @Column(name="MS_REG_DATE")
     private String msRegDate;
	 
	 //服务器地址
	 @Column(name="MS_HOST_LOCAL")
     private String msHostLocal;
	 
	 //技术部人员
	 @Column(name="MS_TECH")
     private String msTech;
	 
	 //电话
	 @Column(name="MS_TEL")
     private String msTel;
	 
	 //后台管理员
	 @Column(name="MS_ADM_CONTACT")
     private String msAdmContact;
	 
	 //联系电话
	 @Column(name="MS_ADM_TEL")
     private String msAdmTel;
	 
	 //1:人手检察  ; 2. : 反欺騙系统 ; 3.: 没有;
	 @Column(name="MS_FRAUD_SYSTEM", precision=38, scale=0)
     private Long msFraudSystem;
	 
	 //数据分析
	 @Column(name="MS_OUTNUMBER")
     private String msOutnumber;
	 
	 //系统名
	 @Column(name="MS_SYSTEMNAME")
     private String msSystemname;
	 
	 //0 :不同意 ; 1: 同意;
	 @Column(name="MS_NATURE", precision=38, scale=0)
     private Long msNature;
	 
	 //0 :不同意 ; 1: 同意;
	 @Column(name="MS_WEBSITE", precision=38, scale=0)
     private Long msWebsite;
	 
	 //0 :不同意 ; 1: 同意;
	 @Column(name="MS_PAYSYSTEM", precision=38, scale=0)
     private Long msPaysystem;
	 
	 //申请人签署及公司印章
	 @Column(name="MS_CHOP")
     private String msChop;
	 
	 //名称
	 @Column(name="MS_NAME")
     private String msName;
	 
	 //标题
	 @Column(name="MS_TITLE")
     private String msTitle;
	 
	 //日期
	 @Column(name="MS_TIME")
     private String msTime;

	public Long getMsId() {
		return msId;
	}

	public void setMsId(Long msId) {
		this.msId = msId;
	}

	public Long getMsMerNo() {
		return msMerNo;
	}

	public void setMsMerNo(Long msMerNo) {
		this.msMerNo = msMerNo;
	}

	public Long getMsCreditcrads() {
		return StringHandleUtils.null2Zero(false, msCreditcrads);
	}

	public void setMsCreditcrads(Long msCreditcrads) {
		this.msCreditcrads = msCreditcrads;
	}

	public Long getMsPaytype() {
		return StringHandleUtils.null2Zero(false, msPaytype);
	}

	public void setMsPaytype(Long msPaytype) {
		this.msPaytype = msPaytype;
	}

	public String getMsName1() {
		return msName1;
	}

	public void setMsName1(String msName1) {
		this.msName1 = msName1;
	}

	public String getMsSe1() {
		return msSe1;
	}

	public void setMsSe1(String msSe1) {
		this.msSe1 = msSe1;
	}

	public String getMsName2() {
		return msName2;
	}

	public void setMsName2(String msName2) {
		this.msName2 = msName2;
	}

	public String getMsSe2() {
		return msSe2;
	}

	public void setMsSe2(String msSe2) {
		this.msSe2 = msSe2;
	}

	public String getMsName3() {
		return msName3;
	}

	public void setMsName3(String msName3) {
		this.msName3 = msName3;
	}

	public String getMsSe3() {
		return msSe3;
	}

	public void setMsSe3(String msSe3) {
		this.msSe3 = msSe3;
	}

	public String getMsDescription() {
		return msDescription;
	}

	public void setMsDescription(String msDescription) {
		this.msDescription = msDescription;
	}

	public String getMsYearAmount() {
		return msYearAmount;
	}

	public void setMsYearAmount(String msYearAmount) {
		this.msYearAmount = msYearAmount;
	}

	public String getMsCreditYearAmount() {
		return msCreditYearAmount;
	}

	public void setMsCreditYearAmount(String msCreditYearAmount) {
		this.msCreditYearAmount = msCreditYearAmount;
	}

	public String getMsAverageTicket() {
		return msAverageTicket;
	}

	public void setMsAverageTicket(String msAverageTicket) {
		this.msAverageTicket = msAverageTicket;
	}

	public String getMsCupAmount() {
		return msCupAmount;
	}

	public void setMsCupAmount(String msCupAmount) {
		this.msCupAmount = msCupAmount;
	}

	public String getMsJcbAmount() {
		return msJcbAmount;
	}

	public void setMsJcbAmount(String msJcbAmount) {
		this.msJcbAmount = msJcbAmount;
	}

	public String getMsDccAmount() {
		return msDccAmount;
	}

	public void setMsDccAmount(String msDccAmount) {
		this.msDccAmount = msDccAmount;
	}

	public String getMsCjdAverage() {
		return msCjdAverage;
	}

	public void setMsCjdAverage(String msCjdAverage) {
		this.msCjdAverage = msCjdAverage;
	}

	public Long getMsTradeType() {
		return StringHandleUtils.null2Zero(false, msTradeType);
	}

	public void setMsTradeType(Long msTradeType) {
		this.msTradeType = msTradeType;
	}

	public String getMsInternetOptional() {
		return msInternetOptional;
	}

	public void setMsInternetOptional(String msInternetOptional) {
		this.msInternetOptional = msInternetOptional;
	}

	public String getMsMotoOptional() {
		return msMotoOptional;
	}

	public void setMsMotoOptional(String msMotoOptional) {
		this.msMotoOptional = msMotoOptional;
	}

	public String getMsFaceOptional() {
		return msFaceOptional;
	}

	public void setMsFaceOptional(String msFaceOptional) {
		this.msFaceOptional = msFaceOptional;
	}

	public String getMsBToB() {
		return msBToB;
	}

	public void setMsBToB(String msBToB) {
		this.msBToB = msBToB;
	}

	public String getMsBToC() {
		return msBToC;
	}

	public void setMsBToC(String msBToC) {
		this.msBToC = msBToC;
	}

	public String getMsTotal() {
		return msTotal;
	}

	public void setMsTotal(String msTotal) {
		this.msTotal = msTotal;
	}

	public String getMsZerodays() {
		return msZerodays;
	}

	public void setMsZerodays(String msZerodays) {
		this.msZerodays = msZerodays;
	}

	public String getMsOneDays() {
		return msOneDays;
	}

	public void setMsOneDays(String msOneDays) {
		this.msOneDays = msOneDays;
	}

	public String getMsEightDays() {
		return msEightDays;
	}

	public void setMsEightDays(String msEightDays) {
		this.msEightDays = msEightDays;
	}

	public String getMsFifteenDays() {
		return msFifteenDays;
	}

	public void setMsFifteenDays(String msFifteenDays) {
		this.msFifteenDays = msFifteenDays;
	}

	public String getMsOverThirtyDays() {
		return msOverThirtyDays;
	}

	public void setMsOverThirtyDays(String msOverThirtyDays) {
		this.msOverThirtyDays = msOverThirtyDays;
	}

	public String getMsPerTotal() {
		return msPerTotal;
	}

	public void setMsPerTotal(String msPerTotal) {
		this.msPerTotal = msPerTotal;
	}

	public Long getMsIsdeposit() {
		return StringHandleUtils.null2Zero(false, msIsdeposit);
	}

	public void setMsIsdeposit(Long msIsdeposit) {
		this.msIsdeposit = msIsdeposit;
	}

	public String getMsPerDeposit() {
		return msPerDeposit;
	}

	public void setMsPerDeposit(String msPerDeposit) {
		this.msPerDeposit = msPerDeposit;
	}

	public String getMsPerPay() {
		return msPerPay;
	}

	public void setMsPerPay(String msPerPay) {
		this.msPerPay = msPerPay;
	}

	public String getMsDelivery() {
		return msDelivery;
	}

	public void setMsDelivery(String msDelivery) {
		this.msDelivery = msDelivery;
	}

	public Long getMsTransactions() {
		return StringHandleUtils.null2Zero(false, msTransactions);
	}

	public void setMsTransactions(Long msTransactions) {
		this.msTransactions = msTransactions;
	}

	public Long getMsRefundment() {
		return StringHandleUtils.null2Zero(false, msRefundment);
	}

	public void setMsRefundment(Long msRefundment) {
		this.msRefundment = msRefundment;
	}

	public Long getMsSubRefund() {
		return StringHandleUtils.null2Zero(false, msSubRefund);
	}

	public void setMsSubRefund(Long msSubRefund) {
		this.msSubRefund = msSubRefund;
	}

	public Long getMsAccept() {
		return StringHandleUtils.null2Zero(false, msAccept);
	}

	public void setMsAccept(Long msAccept) {
		this.msAccept = msAccept;
	}

	public String getMsProName() {
		return msProName;
	}

	public void setMsProName(String msProName) {
		this.msProName = msProName;
	}

	public String getMsMerNumber() {
		return msMerNumber;
	}

	public void setMsMerNumber(String msMerNumber) {
		this.msMerNumber = msMerNumber;
	}

	public Long getMsProcessCard() {
		return StringHandleUtils.null2Zero(false, msProcessCard);
	}

	public void setMsProcessCard(Long msProcessCard) {
		this.msProcessCard = msProcessCard;
	}

	public String getMsPartyName() {
		return msPartyName;
	}

	public void setMsPartyName(String msPartyName) {
		this.msPartyName = msPartyName;
	}

	public String getMsUrl() {
		return msUrl;
	}

	public void setMsUrl(String msUrl) {
		this.msUrl = msUrl;
	}

	public String getMsRegDate() {
		return msRegDate;
	}

	public void setMsRegDate(String msRegDate) {
		this.msRegDate = msRegDate;
	}

	public String getMsHostLocal() {
		return msHostLocal;
	}

	public void setMsHostLocal(String msHostLocal) {
		this.msHostLocal = msHostLocal;
	}

	public String getMsTech() {
		return msTech;
	}

	public void setMsTech(String msTech) {
		this.msTech = msTech;
	}

	public String getMsTel() {
		return msTel;
	}

	public void setMsTel(String msTel) {
		this.msTel = msTel;
	}

	public String getMsAdmContact() {
		return msAdmContact;
	}

	public void setMsAdmContact(String msAdmContact) {
		this.msAdmContact = msAdmContact;
	}

	public String getMsAdmTel() {
		return msAdmTel;
	}

	public void setMsAdmTel(String msAdmTel) {
		this.msAdmTel = msAdmTel;
	}

	public Long getMsFraudSystem() {
		return StringHandleUtils.null2Zero(false, msFraudSystem);
	}

	public void setMsFraudSystem(Long msFraudSystem) {
		this.msFraudSystem = msFraudSystem;
	}

	public String getMsOutnumber() {
		return msOutnumber;
	}

	public void setMsOutnumber(String msOutnumber) {
		this.msOutnumber = msOutnumber;
	}

	public String getMsSystemname() {
		return msSystemname;
	}

	public void setMsSystemname(String msSystemname) {
		this.msSystemname = msSystemname;
	}

	public Long getMsNature() {
		return StringHandleUtils.null2Zero(false, msNature);
	}

	public void setMsNature(Long msNature) {
		this.msNature = msNature;
	}

	public Long getMsWebsite() {
		return StringHandleUtils.null2Zero(false, msWebsite);
	}

	public void setMsWebsite(Long msWebsite) {
		this.msWebsite = msWebsite;
	}

	public Long getMsPaysystem() {
		return StringHandleUtils.null2Zero(false, msPaysystem);
	}

	public void setMsPaysystem(Long msPaysystem) {
		this.msPaysystem = msPaysystem;
	}

	public String getMsChop() {
		return msChop;
	}

	public void setMsChop(String msChop) {
		this.msChop = msChop;
	}

	public String getMsName() {
		return msName;
	}

	public void setMsName(String msName) {
		this.msName = msName;
	}

	public String getMsTitle() {
		return msTitle;
	}

	public void setMsTitle(String msTitle) {
		this.msTitle = msTitle;
	}

	public String getMsTime() {
		return msTime;
	}

	public void setMsTime(String msTime) {
		this.msTime = msTime;
	}

}