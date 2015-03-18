package com.jinxin.model.persistence;
// default package

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


/**
 * <p> Title: 记录交易信息的maxmid的返回信息</p>
 * <p>Description: CCPS_MAXMIND_OUTPUTS映射bean </p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */
@Entity
@Table(name="CCPS_MAXMIND_OUTPUTS")
public class MaxmindOutputs implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Fields

	@Id
	@Column(name = "MOP_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MAXMIND_OUTPUTS_SEQ")      
    @SequenceGenerator(name="CCPS_MAXMIND_OUTPUTS_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MAXMIND_OUTPUTS_SEQ")
	private Long mopId;
	
	// 订单流水号
	@Column(name = "MOP_TR_NO", nullable = false)
	private String mopTrNo;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_COUNTRYMATCH")
	private String mopCountrymatch;
	
	// 国家编号
	@Column(name = "MOP_COUNTRYCODE")
	private String mopCountrycode;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_HIGHRISKCOUNTRY")
	private String mopHighriskcountry;
	
	// 距离
	@Column(name = "MOP_DISTANCE")
	private String mopDistance;
	
	// IP范围
	@Column(name = "MOP_IP_REGION")
	private String mopIpRegion;
	
	// IP城市
	@Column(name = "MOP_IP_CITY")
	private String mopIpCity;
	
	// IP开始范围
	@Column(name = "MOP_IP_LATITUDE")
	private String mopIpLatitude;
	
	// IP结束范围
	@Column(name = "MOP_IP_LONGITUDE")
	private String mopIpLongitude;
	
	// IP网络供应商
	@Column(name = "MOP_IP_ISP")
	private String mopIpIsp;
	
	// IP组织
	@Column(name = "MOP_IP_ORG")
	private String mopIpOrg;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_ANONYMOUSPROXY")
	private String mopAnonymousproxy;
	
	// 匿名代理分数
	@Column(name = "MOP_PROXY_SCORE")
	private String mopProxyScore;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_TRANS_PROXY")
	private String mopTransProxy;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_FREEMAIL")
	private String mopFreemail;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_CARDEREMAIL")
	private String mopCarderemail;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_HIGHRISKUSERNAME")
	private String mopHighriskusername;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_HIGHRISKPASSWORD")
	private String mopHighriskpassword;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_BINMATCH")
	private String mopBinmatch;
	
	// bin国家
	@Column(name = "MOP_BINGCOUNTRY")
	private String mopBingcountry;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_BINNAMEMATCH")
	private String mopBinnamematch;
	
	// bin名称
	@Column(name = "MOP_BINNAME")
	private String mopBinname;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_BINPHONEMATCH")
	private String mopBinphonematch;
	
	// bin电话
	@Column(name = "MOP_BINPHONE")
	private String mopBinphone;
	
	// 电话账单
	@Column(name = "MOP_PHONEBILLS")
	private String mopPhonebills;
	
	@Column(name = "MOP_SHIPFORWARD")
	private String mopShipforward;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_CITYPOSTALMATCH")
	private String mopCitypostalmatch;
	
	// 1 : 是; 0 : 否
	@Column(name = "MOP_SHIPCITYPMATCH")
	private String mopShipcitypmatch;
	
	@Column(name = "MOP_SCORE")
	private String mopScore;
	
	@Column(name = "MOP_QUERIESREMAINING")
	private String mopQueriesremaining;
	
	@Column(name = "MOP_MAXMINDID")
	private String mopMaxmindid;
	
	@Column(name = "MOP_ERROR")
	private String mopError;

	/**
	 * @return the mopId
	 */
	public Long getMopId() {
		return mopId;
	}

	/**
	 * @param mopId the mopId to set
	 */
	public void setMopId(Long mopId) {
		this.mopId = mopId;
	}

	/**
	 * @return the mopTrNo
	 */
	public String getMopTrNo() {
		return mopTrNo;
	}

	/**
	 * @param mopTrNo the mopTrNo to set
	 */
	public void setMopTrNo(String mopTrNo) {
		this.mopTrNo = mopTrNo;
	}

	/**
	 * @return the mopCountrymatch
	 */
	public String getMopCountrymatch() {
		return mopCountrymatch;
	}

	/**
	 * @param mopCountrymatch the mopCountrymatch to set
	 */
	public void setMopCountrymatch(String mopCountrymatch) {
		this.mopCountrymatch = mopCountrymatch;
	}

	/**
	 * @return the mopCountrycode
	 */
	public String getMopCountrycode() {
		return mopCountrycode;
	}

	/**
	 * @param mopCountrycode the mopCountrycode to set
	 */
	public void setMopCountrycode(String mopCountrycode) {
		this.mopCountrycode = mopCountrycode;
	}

	/**
	 * @return the mopHighriskcountry
	 */
	public String getMopHighriskcountry() {
		return mopHighriskcountry;
	}

	/**
	 * @param mopHighriskcountry the mopHighriskcountry to set
	 */
	public void setMopHighriskcountry(String mopHighriskcountry) {
		this.mopHighriskcountry = mopHighriskcountry;
	}

	/**
	 * @return the mopDistance
	 */
	public String getMopDistance() {
		return mopDistance;
	}

	/**
	 * @param mopDistance the mopDistance to set
	 */
	public void setMopDistance(String mopDistance) {
		this.mopDistance = mopDistance;
	}

	/**
	 * @return the mopIpRegion
	 */
	public String getMopIpRegion() {
		return mopIpRegion;
	}

	/**
	 * @param mopIpRegion the mopIpRegion to set
	 */
	public void setMopIpRegion(String mopIpRegion) {
		this.mopIpRegion = mopIpRegion;
	}

	/**
	 * @return the mopIpCity
	 */
	public String getMopIpCity() {
		return mopIpCity;
	}

	/**
	 * @param mopIpCity the mopIpCity to set
	 */
	public void setMopIpCity(String mopIpCity) {
		this.mopIpCity = mopIpCity;
	}

	/**
	 * @return the mopIpLatitude
	 */
	public String getMopIpLatitude() {
		return mopIpLatitude;
	}

	/**
	 * @param mopIpLatitude the mopIpLatitude to set
	 */
	public void setMopIpLatitude(String mopIpLatitude) {
		this.mopIpLatitude = mopIpLatitude;
	}

	/**
	 * @return the mopIpLongitude
	 */
	public String getMopIpLongitude() {
		return mopIpLongitude;
	}

	/**
	 * @param mopIpLongitude the mopIpLongitude to set
	 */
	public void setMopIpLongitude(String mopIpLongitude) {
		this.mopIpLongitude = mopIpLongitude;
	}

	/**
	 * @return the mopIpIsp
	 */
	public String getMopIpIsp() {
		return mopIpIsp;
	}

	/**
	 * @param mopIpIsp the mopIpIsp to set
	 */
	public void setMopIpIsp(String mopIpIsp) {
		this.mopIpIsp = mopIpIsp;
	}

	/**
	 * @return the mopIpOrg
	 */
	public String getMopIpOrg() {
		return mopIpOrg;
	}

	/**
	 * @param mopIpOrg the mopIpOrg to set
	 */
	public void setMopIpOrg(String mopIpOrg) {
		this.mopIpOrg = mopIpOrg;
	}

	/**
	 * @return the mopAnonymousproxy
	 */
	public String getMopAnonymousproxy() {
		return mopAnonymousproxy;
	}

	/**
	 * @param mopAnonymousproxy the mopAnonymousproxy to set
	 */
	public void setMopAnonymousproxy(String mopAnonymousproxy) {
		this.mopAnonymousproxy = mopAnonymousproxy;
	}

	/**
	 * @return the mopProxyScore
	 */
	public String getMopProxyScore() {
		return mopProxyScore;
	}

	/**
	 * @param mopProxyScore the mopProxyScore to set
	 */
	public void setMopProxyScore(String mopProxyScore) {
		this.mopProxyScore = mopProxyScore;
	}

	/**
	 * @return the mopTransProxy
	 */
	public String getMopTransProxy() {
		return mopTransProxy;
	}

	/**
	 * @param mopTransProxy the mopTransProxy to set
	 */
	public void setMopTransProxy(String mopTransProxy) {
		this.mopTransProxy = mopTransProxy;
	}

	/**
	 * @return the mopFreemail
	 */
	public String getMopFreemail() {
		return mopFreemail;
	}

	/**
	 * @param mopFreemail the mopFreemail to set
	 */
	public void setMopFreemail(String mopFreemail) {
		this.mopFreemail = mopFreemail;
	}

	/**
	 * @return the mopCarderemail
	 */
	public String getMopCarderemail() {
		return mopCarderemail;
	}

	/**
	 * @param mopCarderemail the mopCarderemail to set
	 */
	public void setMopCarderemail(String mopCarderemail) {
		this.mopCarderemail = mopCarderemail;
	}

	/**
	 * @return the mopHighriskusername
	 */
	public String getMopHighriskusername() {
		return mopHighriskusername;
	}

	/**
	 * @param mopHighriskusername the mopHighriskusername to set
	 */
	public void setMopHighriskusername(String mopHighriskusername) {
		this.mopHighriskusername = mopHighriskusername;
	}

	/**
	 * @return the mopHighriskpassword
	 */
	public String getMopHighriskpassword() {
		return mopHighriskpassword;
	}

	/**
	 * @param mopHighriskpassword the mopHighriskpassword to set
	 */
	public void setMopHighriskpassword(String mopHighriskpassword) {
		this.mopHighriskpassword = mopHighriskpassword;
	}

	/**
	 * @return the mopBinmatch
	 */
	public String getMopBinmatch() {
		return mopBinmatch;
	}

	/**
	 * @param mopBinmatch the mopBinmatch to set
	 */
	public void setMopBinmatch(String mopBinmatch) {
		this.mopBinmatch = mopBinmatch;
	}

	/**
	 * @return the mopBingcountry
	 */
	public String getMopBingcountry() {
		return mopBingcountry;
	}

	/**
	 * @param mopBingcountry the mopBingcountry to set
	 */
	public void setMopBingcountry(String mopBingcountry) {
		this.mopBingcountry = mopBingcountry;
	}

	/**
	 * @return the mopBinnamematch
	 */
	public String getMopBinnamematch() {
		return mopBinnamematch;
	}

	/**
	 * @param mopBinnamematch the mopBinnamematch to set
	 */
	public void setMopBinnamematch(String mopBinnamematch) {
		this.mopBinnamematch = mopBinnamematch;
	}

	/**
	 * @return the mopBinname
	 */
	public String getMopBinname() {
		return mopBinname;
	}

	/**
	 * @param mopBinname the mopBinname to set
	 */
	public void setMopBinname(String mopBinname) {
		this.mopBinname = mopBinname;
	}

	/**
	 * @return the mopBinphonematch
	 */
	public String getMopBinphonematch() {
		return mopBinphonematch;
	}

	/**
	 * @param mopBinphonematch the mopBinphonematch to set
	 */
	public void setMopBinphonematch(String mopBinphonematch) {
		this.mopBinphonematch = mopBinphonematch;
	}

	/**
	 * @return the mopBinphone
	 */
	public String getMopBinphone() {
		return mopBinphone;
	}

	/**
	 * @param mopBinphone the mopBinphone to set
	 */
	public void setMopBinphone(String mopBinphone) {
		this.mopBinphone = mopBinphone;
	}

	/**
	 * @return the mopPhonebills
	 */
	public String getMopPhonebills() {
		return mopPhonebills;
	}

	/**
	 * @param mopPhonebills the mopPhonebills to set
	 */
	public void setMopPhonebills(String mopPhonebills) {
		this.mopPhonebills = mopPhonebills;
	}

	/**
	 * @return the mopShipforward
	 */
	public String getMopShipforward() {
		return mopShipforward;
	}

	/**
	 * @param mopShipforward the mopShipforward to set
	 */
	public void setMopShipforward(String mopShipforward) {
		this.mopShipforward = mopShipforward;
	}

	/**
	 * @return the mopCitypostalmatch
	 */
	public String getMopCitypostalmatch() {
		return mopCitypostalmatch;
	}

	/**
	 * @param mopCitypostalmatch the mopCitypostalmatch to set
	 */
	public void setMopCitypostalmatch(String mopCitypostalmatch) {
		this.mopCitypostalmatch = mopCitypostalmatch;
	}

	/**
	 * @return the mopShipcitypmatch
	 */
	public String getMopShipcitypmatch() {
		return mopShipcitypmatch;
	}

	/**
	 * @param mopShipcitypmatch the mopShipcitypmatch to set
	 */
	public void setMopShipcitypmatch(String mopShipcitypmatch) {
		this.mopShipcitypmatch = mopShipcitypmatch;
	}

	/**
	 * @return the mopScore
	 */
	public String getMopScore() {
		return mopScore;
	}

	/**
	 * @param mopScore the mopScore to set
	 */
	public void setMopScore(String mopScore) {
		this.mopScore = mopScore;
	}

	/**
	 * @return the mopQueriesremaining
	 */
	public String getMopQueriesremaining() {
		return mopQueriesremaining;
	}

	/**
	 * @param mopQueriesremaining the mopQueriesremaining to set
	 */
	public void setMopQueriesremaining(String mopQueriesremaining) {
		this.mopQueriesremaining = mopQueriesremaining;
	}

	/**
	 * @return the mopMaxmindid
	 */
	public String getMopMaxmindid() {
		return mopMaxmindid;
	}

	/**
	 * @param mopMaxmindid the mopMaxmindid to set
	 */
	public void setMopMaxmindid(String mopMaxmindid) {
		this.mopMaxmindid = mopMaxmindid;
	}

	/**
	 * @return the mopError
	 */
	public String getMopError() {
		return mopError;
	}

	/**
	 * @param mopError the mopError to set
	 */
	public void setMopError(String mopError) {
		this.mopError = mopError;
	}

	

}