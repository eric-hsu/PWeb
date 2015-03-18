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
@Table(name="CCPS_MPP_OWNER")
public class MppOwner  implements java.io.Serializable {
	 
	/**
	 * 序列
	 */
	private static final long serialVersionUID = 1L;

	//自增
	@Id 
	@Column(name="MOP_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CCPS_MPP_OWNER_SEQ")      
	@SequenceGenerator(name="CCPS_MPP_OWNER_SEQ",allocationSize=1,initialValue=1, sequenceName="CCPS_MPP_OWNER_SEQ")
	private Long mopId;
    
	//与商户表关联
	@Column(name="MOP_MER_NO",nullable=false, precision=38, scale=0)
	private Long mopMerNo;
     
     //集团标题
	 @Column(name="MOP_TITLE")
     private String mopTitle;
     
     //名字
	 @Column(name="MOP_FIRST_NAME")
     private String mopFirstName;
     
     //中间名字
	 @Column(name="MOP_MIDDLE_NAME")
     private String mopMiddleName;
     
     //姓名
	 @Column(name="MOP_SURNAME")
     private String mopSurname;
     
     //职位
	 @Column(name="MOP_POSITION")
     private String mopPosition;
     
     //生日
	 @Column(name="MOP_BIRTH")
     private String mopBirth;
     
     //住址
	 @Column(name="MOP_ADDRESS")
     private String mopAddress;
     
     //城市、县、邮政编码
	 @Column(name="MOP_STATE")
     private String mopState;
     
     //联系电话
	 @Column(name="MOP_TEL")
     private String mopTel;
     
     //1: 身份证 ;  2 : 护照 ;  3 :  其他 ;
	 @Column(name="MOP_ID_TYPE", precision=38, scale=0)
     private Long mopIdType;
     
     //证件号码
	 @Column(name="MOP_IDNO")
     private String mopIdno;
     
     //1 : 是; 0 : 否;
	 @Column(name="MOP_PROPETY_OWNER", precision=38, scale=0)
     private Long mopPropetyOwner;
     
     //集团标题1
	 @Column(name="MPO_TITLE1")
     private String mpoTitle1;
     
     //名字1
	 @Column(name="MOP_FIRST_NAME1")
     private String mopFirstName1;
        
     //中间名字1
	 @Column(name="MOP_MIDDLE_NAME1")
     private String mopMiddleName1;
     
     //姓名1
	 @Column(name="MOP_SURNAME1")
     private String mopSurname1;
     
     //职位1
	 @Column(name="MOP_POSITION1")
     private String mopPosition1;
     
     //生日1
	 @Column(name="MOP_BIRTH1")
     private String mopBirth1;
     
     //住址1
	 @Column(name="MOP_ADDRESS1")
     private String mopAddress1;
     
     //城市、县、邮政编码1
	 @Column(name="MOP_STATE1")
     private String mopState1;
     
     //联系电话1
	 @Column(name="MOP_TEL1")
     private String mopTel1;
     
     //1: 身份证 ;  2 : 护照 ;  3 :  其他 ;
	 @Column(name="MOP_ID_TYPE1", precision=38, scale=0)
     private Long mopIdType1;
     
     //证件号码1
	 @Column(name="MOP_IDNO1")
     private String mopIdno1;
     
     //1 : 是; 0 : 否;
	 @Column(name="MOP_PROPETY_OWNER1", precision=38, scale=0)
     private Long mopPropetyOwner1;
     
     //集团标题2
	 @Column(name="MPO_TITLE2")
     private String mpoTitle2;
     
     //名字2
	 @Column(name="MOP_FIRST_NAME2")
     private String mopFirstName2;
     
     
     //中间名字2
	 @Column(name="MOP_MIDDLE_NAME2")
     private String mopMiddleName2;
     
     //姓名2
	 @Column(name="MOP_SURNAME2")
     private String mopSurname2;
     
     //职位2
	 @Column(name="MOP_POSITION2")
     private String mopPosition2;
     
     //生日2
	 @Column(name="MOP_BIRTH2")
     private String mopBirth2;
     
     //住址2
	 @Column(name="MOP_ADDRESS2")
     private String mopAddress2;
     
     //城市、县、邮政编码2
	 @Column(name="MOP_STATE2")
     private String mopState2;
     
     //联系电话2
	 @Column(name="MOP_TEL2")
     private String mopTel2;
     
     //1: 身份证 ;  2 : 护照 ;  3 :  其他 ;
	 @Column(name="MOP_ID_TYPE2", precision=38, scale=0)
     private Long mopIdType2;
     
     //证件号码2
	 @Column(name="MOP_IDNO2")
     private String mopIdno2;
     
     //1 : 是; 0 : 否;
	 @Column(name="MOP_PROPETY_OWNER2", precision=38, scale=0)
     private Long mopPropetyOwner2;

	public Long getMopMerNo() {
		return mopMerNo;
	}

	public void setMopMerNo(Long mopMerNo) {
		this.mopMerNo = mopMerNo;
	}

	public Long getMopId() {
		return mopId;
	}

	public void setMopId(Long mopId) {
		this.mopId = mopId;
	}

	public String getMopTitle() {
		return mopTitle;
	}

	public void setMopTitle(String mopTitle) {
		this.mopTitle = mopTitle;
	}

	public String getMopFirstName() {
		return mopFirstName;
	}

	public void setMopFirstName(String mopFirstName) {
		this.mopFirstName = mopFirstName;
	}

	public String getMopMiddleName() {
		return mopMiddleName;
	}

	public void setMopMiddleName(String mopMiddleName) {
		this.mopMiddleName = mopMiddleName;
	}

	public String getMopSurname() {
		return mopSurname;
	}

	public void setMopSurname(String mopSurname) {
		this.mopSurname = mopSurname;
	}

	public String getMopPosition() {
		return mopPosition;
	}

	public void setMopPosition(String mopPosition) {
		this.mopPosition = mopPosition;
	}

	public String getMopBirth() {
		return mopBirth;
	}

	public void setMopBirth(String mopBirth) {
		this.mopBirth = mopBirth;
	}

	public String getMopAddress() {
		return mopAddress;
	}

	public void setMopAddress(String mopAddress) {
		this.mopAddress = mopAddress;
	}

	public String getMopState() {
		return mopState;
	}

	public void setMopState(String mopState) {
		this.mopState = mopState;
	}

	public String getMopTel() {
		return mopTel;
	}

	public void setMopTel(String mopTel) {
		this.mopTel = mopTel;
	}

	public Long getMopIdType() {
		return StringHandleUtils.null2Zero(false, mopIdType);
	}

	public void setMopIdType(Long mopIdType) {
		this.mopIdType = mopIdType;
	}

	public String getMopIdno() {
		return mopIdno;
	}

	public void setMopIdno(String mopIdno) {
		this.mopIdno = mopIdno;
	}

	public Long getMopPropetyOwner() {
		return StringHandleUtils.null2Zero(false, mopPropetyOwner);
	}

	public void setMopPropetyOwner(Long mopPropetyOwner) {
		this.mopPropetyOwner = mopPropetyOwner;
	}

	public String getMpoTitle1() {
		return mpoTitle1;
	}

	public void setMpoTitle1(String mpoTitle1) {
		this.mpoTitle1 = mpoTitle1;
	}

	public String getMopFirstName1() {
		return mopFirstName1;
	}

	public void setMopFirstName1(String mopFirstName1) {
		this.mopFirstName1 = mopFirstName1;
	}

	public String getMopMiddleName1() {
		return mopMiddleName1;
	}

	public void setMopMiddleName1(String mopMiddleName1) {
		this.mopMiddleName1 = mopMiddleName1;
	}

	public String getMopSurname1() {
		return mopSurname1;
	}

	public void setMopSurname1(String mopSurname1) {
		this.mopSurname1 = mopSurname1;
	}

	public String getMopPosition1() {
		return mopPosition1;
	}

	public void setMopPosition1(String mopPosition1) {
		this.mopPosition1 = mopPosition1;
	}

	public String getMopBirth1() {
		return mopBirth1;
	}

	public void setMopBirth1(String mopBirth1) {
		this.mopBirth1 = mopBirth1;
	}

	public String getMopAddress1() {
		return mopAddress1;
	}

	public void setMopAddress1(String mopAddress1) {
		this.mopAddress1 = mopAddress1;
	}

	public String getMopState1() {
		return mopState1;
	}

	public void setMopState1(String mopState1) {
		this.mopState1 = mopState1;
	}

	public String getMopTel1() {
		return mopTel1;
	}

	public void setMopTel1(String mopTel1) {
		this.mopTel1 = mopTel1;
	}

	public Long getMopIdType1() {
		return StringHandleUtils.null2Zero(false, mopIdType1);
	}

	public void setMopIdType1(Long mopIdType1) {
		this.mopIdType1 = mopIdType1;
	}

	public String getMopIdno1() {
		return mopIdno1;
	}

	public void setMopIdno1(String mopIdno1) {
		this.mopIdno1 = mopIdno1;
	}

	public Long getMopPropetyOwner1() {
		return StringHandleUtils.null2Zero(false, mopPropetyOwner1);
	}

	public void setMopPropetyOwner1(Long mopPropetyOwner1) {
		this.mopPropetyOwner1 = mopPropetyOwner1;
	}

	public String getMpoTitle2() {
		return mpoTitle2;
	}

	public void setMpoTitle2(String mpoTitle2) {
		this.mpoTitle2 = mpoTitle2;
	}

	public String getMopFirstName2() {
		return mopFirstName2;
	}

	public void setMopFirstName2(String mopFirstName2) {
		this.mopFirstName2 = mopFirstName2;
	}

	public String getMopMiddleName2() {
		return mopMiddleName2;
	}

	public void setMopMiddleName2(String mopMiddleName2) {
		this.mopMiddleName2 = mopMiddleName2;
	}

	public String getMopSurname2() {
		return mopSurname2;
	}

	public void setMopSurname2(String mopSurname2) {
		this.mopSurname2 = mopSurname2;
	}

	public String getMopPosition2() {
		return mopPosition2;
	}

	public void setMopPosition2(String mopPosition2) {
		this.mopPosition2 = mopPosition2;
	}

	public String getMopBirth2() {
		return mopBirth2;
	}

	public void setMopBirth2(String mopBirth2) {
		this.mopBirth2 = mopBirth2;
	}

	public String getMopAddress2() {
		return mopAddress2;
	}

	public void setMopAddress2(String mopAddress2) {
		this.mopAddress2 = mopAddress2;
	}

	public String getMopState2() {
		return mopState2;
	}

	public void setMopState2(String mopState2) {
		this.mopState2 = mopState2;
	}

	public String getMopTel2() {
		return mopTel2;
	}

	public void setMopTel2(String mopTel2) {
		this.mopTel2 = mopTel2;
	}

	public Long getMopIdType2() {
		return StringHandleUtils.null2Zero(false, mopIdType2);
	}

	public void setMopIdType2(Long mopIdType2) {
		this.mopIdType2 = mopIdType2;
	}

	public String getMopIdno2() {
		return mopIdno2;
	}

	public void setMopIdno2(String mopIdno2) {
		this.mopIdno2 = mopIdno2;
	}

	public Long getMopPropetyOwner2() {
		return StringHandleUtils.null2Zero(false, mopPropetyOwner2);
	}

	public void setMopPropetyOwner2(Long mopPropetyOwner2) {
		this.mopPropetyOwner2 = mopPropetyOwner2;
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

}