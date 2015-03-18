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
 * <p>Description: CCPS_MPP_MERDETAIL映射bean</p>
 * <p>Copyright: </p>
 * <p>Company: </p>
 * @author 
 * @version 
 * @date 
 */

@Entity
@Table(name="CCPS_MPP_MERDETAIL")
public class MppMerDetail  implements java.io.Serializable {
    /**
	 * 序列
	 */
	private static final long serialVersionUID = 1L;

	//自增ID
	@Id 
    @Column(name="MPM_ID")
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MPP_MERDETAIL_SEQ")      
    @SequenceGenerator(name="CCPS_MPP_MERDETAIL_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MPP_MERDETAIL_SEQ")
     private Long mpmId;
	
	//与商户表关联
    @Column(name="MPM_MER_NO", nullable=false, precision=38, scale=0)
     private Long mpmMerNo;
    
    //公司名称
    @Column(name="MPM_B_NAME")
     private String mpmBName;
    
    //主要的营业地址
    @Column(name="MPM_B_ADD")
     private String mpmBAdd;
    
    //地址
    @Column(name="MPM_B_ADD1")
     private String mpmBAdd1;
    
    //城市
    @Column(name="MPM_B_CITY")
     private String mpmBCity;
    
    //县
    @Column(name="MPM_B_STATE")
     private String mpmBState;
    
    //邮政编码
    @Column(name="MPM_B_POSTCODE")
     private String mpmBPostcode;
    
    //国家
    @Column(name="MPM_B_COUNTY")
     private String mpmBCounty;
    
    //营业电话
    @Column(name="MPM_B_PHONE")
     private String mpmBPhone;
    
    //营业传真
    @Column(name="MPM_B_FAX")
     private String mpmBFax;
    
    //联系人
    @Column(name="MPM_B_LANKMAN")
     private String mpmBLankman;
    
    //注册法定名称
    @Column(name="MPM_B_LEGAL_NAME")
     private String mpmBLegalName;
    
    //总部资料
    @Column(name="MPM_B_CORPORATE_DETAIL")
     private String mpmBCorporateDetail;
    
    //总部地址
    @Column(name="MPM_C_ADD")
     private String mpmCAdd;
    
    //总部城市
    @Column(name="MPM_C_CITY")
     private String mpmCCity;
    
    //总部县
    @Column(name="MPM_C_STATE")
     private String mpmCState;
    
    //总部邮政编码
    @Column(name="MPM_C_POSTCODE")
     private String mpmCPostcode;
    
    //总部国家
    @Column(name="MPM_C_COUNTY")
     private String mpmCCounty;
    
    //总部电话
    @Column(name="MPM_C_TEL")
     private String mpmCTel;
    
    //传真
    @Column(name="MPM_C_FAX")
     private String mpmCFax;
    
    //商业登记号码
    @Column(name="MPM_REGISTRATION")
     private String mpmRegistration;
    
    //电子邮件
    @Column(name="MPM_EMAIL")
     private String mpmEmail;
    
    //网址
    @Column(name="MPM_WEBSITE")
     private String mpmWebsite;
    
    //开业日期
    @Column(name="MPM_OPENDATE")
     private String mpmOpendate;
    
    //登记日期
    @Column(name="MPM_REG_DATE")
     private String mpmRegDate;
    
    //1 : 独资经营者 ;  2 : 合伙 ;  3 : 有限公司 ;  4 :信托 ;  5 ;协会  ;6 : 其他
    @Column(name="MPM_MODEL", precision=38, scale=0)
     private Long mpmModel;
    
    
	public Long getMpmId() {
		return mpmId;
	}
	public void setMpmId(Long mpmId) {
		this.mpmId = mpmId;
	}
	public Long getMpmMerNo() {
		return mpmMerNo;
	}
	public void setMpmMerNo(Long mpmMerNo) {
		this.mpmMerNo = mpmMerNo;
	}
	public String getMpmBName() {
		return mpmBName;
	}
	public void setMpmBName(String mpmBName) {
		this.mpmBName = mpmBName;
	}
	public String getMpmBAdd() {
		return mpmBAdd;
	}
	public void setMpmBAdd(String mpmBAdd) {
		this.mpmBAdd = mpmBAdd;
	}
	public String getMpmBAdd1() {
		return mpmBAdd1;
	}
	public void setMpmBAdd1(String mpmBAdd1) {
		this.mpmBAdd1 = mpmBAdd1;
	}
	public String getMpmBCity() {
		return mpmBCity;
	}
	public void setMpmBCity(String mpmBCity) {
		this.mpmBCity = mpmBCity;
	}
	public String getMpmBState() {
		return mpmBState;
	}
	public void setMpmBState(String mpmBState) {
		this.mpmBState = mpmBState;
	}
	public String getMpmBPostcode() {
		return mpmBPostcode;
	}
	public void setMpmBPostcode(String mpmBPostcode) {
		this.mpmBPostcode = mpmBPostcode;
	}
	public String getMpmBCounty() {
		return mpmBCounty;
	}
	public void setMpmBCounty(String mpmBCounty) {
		this.mpmBCounty = mpmBCounty;
	}
	public String getMpmBPhone() {
		return mpmBPhone;
	}
	public void setMpmBPhone(String mpmBPhone) {
		this.mpmBPhone = mpmBPhone;
	}
	public String getMpmBFax() {
		return mpmBFax;
	}
	public void setMpmBFax(String mpmBFax) {
		this.mpmBFax = mpmBFax;
	}
	public String getMpmBLankman() {
		return mpmBLankman;
	}
	public void setMpmBLankman(String mpmBLankman) {
		this.mpmBLankman = mpmBLankman;
	}
	public String getMpmBLegalName() {
		return mpmBLegalName;
	}
	public void setMpmBLegalName(String mpmBLegalName) {
		this.mpmBLegalName = mpmBLegalName;
	}
	public String getMpmBCorporateDetail() {
		return mpmBCorporateDetail;
	}
	public void setMpmBCorporateDetail(String mpmBCorporateDetail) {
		this.mpmBCorporateDetail = mpmBCorporateDetail;
	}
	public String getMpmCAdd() {
		return mpmCAdd;
	}
	public void setMpmCAdd(String mpmCAdd) {
		this.mpmCAdd = mpmCAdd;
	}
	public String getMpmCCity() {
		return mpmCCity;
	}
	public void setMpmCCity(String mpmCCity) {
		this.mpmCCity = mpmCCity;
	}
	public String getMpmCState() {
		return mpmCState;
	}
	public void setMpmCState(String mpmCState) {
		this.mpmCState = mpmCState;
	}
	public String getMpmCPostcode() {
		return mpmCPostcode;
	}
	public void setMpmCPostcode(String mpmCPostcode) {
		this.mpmCPostcode = mpmCPostcode;
	}
	public String getMpmCCounty() {
		return mpmCCounty;
	}
	public void setMpmCCounty(String mpmCCounty) {
		this.mpmCCounty = mpmCCounty;
	}
	public String getMpmCTel() {
		return mpmCTel;
	}
	public void setMpmCTel(String mpmCTel) {
		this.mpmCTel = mpmCTel;
	}
	public String getMpmCFax() {
		return mpmCFax;
	}
	public void setMpmCFax(String mpmCFax) {
		this.mpmCFax = mpmCFax;
	}
	public String getMpmRegistration() {
		return mpmRegistration;
	}
	public void setMpmRegistration(String mpmRegistration) {
		this.mpmRegistration = mpmRegistration;
	}
	public String getMpmEmail() {
		return mpmEmail;
	}
	public void setMpmEmail(String mpmEmail) {
		this.mpmEmail = mpmEmail;
	}
	public String getMpmWebsite() {
		return mpmWebsite;
	}
	public void setMpmWebsite(String mpmWebsite) {
		this.mpmWebsite = mpmWebsite;
	}
	public String getMpmOpendate() {
		return mpmOpendate;
	}
	public void setMpmOpendate(String mpmOpendate) {
		this.mpmOpendate = mpmOpendate;
	}
	public String getMpmRegDate() {
		return mpmRegDate;
	}
	public void setMpmRegDate(String mpmRegDate) {
		this.mpmRegDate = mpmRegDate;
	}
	public Long getMpmModel() {
		return StringHandleUtils.null2Zero(false, mpmModel);
	}
	public void setMpmModel(Long mpmModel) {
		this.mpmModel = mpmModel;
	}
}