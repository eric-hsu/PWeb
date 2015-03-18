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
 * <p>
 * Title:
 * </p>
 * <p>
 * Description: 客服日报表bean
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
@Table(name = "CCPS_DAY_REPORT")
public class DayReport implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	// Fields
	// 记录ID
	@Id
	@Column(name = "DR_ID", unique = true, nullable = false, precision = 38, scale = 0)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CCPS_DAY_REPORT_SEQ")
	@SequenceGenerator(name = "CCPS_DAY_REPORT_SEQ", allocationSize = 1, initialValue = 1, sequenceName = "CCPS_DAY_REPORT_SEQ")
	private Long drId;

	// 商户号
	@Column(name = "DR_MER_NO", nullable = false, precision = 38, scale = 0)
	private Long drMerNo;
	
	// 网关接入号
	@Column(name = "DR_GW_NO", nullable = false, precision = 38, scale = 0)
	private Long drGwNo;

	// 商户交易币种
	@Column(name = "DR_TS_CURRENCY", nullable = false)
	private String drTsCurrency;

	// 年份
	@Column(name = "DR_YEAR", nullable = false, precision = 38, scale = 0)
	private Long drYear;

	// 月份
	@Column(name = "DR_MONTH", nullable = false, precision = 38, scale = 0)
	private Long drMonth;

	// 1号金额
	@Column(name = "DR_ONE", precision = 18)
	private BigDecimal drOne;

	// 2号金额
	@Column(name = "DR_TWO", precision = 18)
	private BigDecimal drTwo;

	// 3号金额
	@Column(name = "DR_THREE", precision = 18)
	private BigDecimal drThree;

	// 4号金额
	@Column(name = "DR_FOUR", precision = 18)
	private BigDecimal drFour;

	// 5号金额
	@Column(name = "DR_FIVE", precision = 18)
	private BigDecimal drFive;

	// 6号金额
	@Column(name = "DR_SIX", precision = 18)
	private BigDecimal drSix;

	// 7号金额
	@Column(name = "DR_SEVEN", precision = 18)
	private BigDecimal drSeven;

	// 8号金额
	@Column(name = "DR_EIGHT", precision = 18)
	private BigDecimal drEight;

	// 9号金额
	@Column(name = "DR_NINE", precision = 18)
	private BigDecimal drNine;

	// 10号金额
	@Column(name = "DR_TEN", precision = 18)
	private BigDecimal drTen;

	// 11号金额
	@Column(name = "DR_ELEVEN", precision = 18)
	private BigDecimal drEleven;

	// 12号金额
	@Column(name = "DR_TWELVE", precision = 18)
	private BigDecimal drTwelve;

	// 13号金额
	@Column(name = "DR_THIRTEEN", precision = 18)
	private BigDecimal drThirteen;

	// 14号金额
	@Column(name = "DR_FOURTEEN", precision = 18)
	private BigDecimal drFourteen;

	// 15号金额
	@Column(name = "DR_FIFTEEN", precision = 18)
	private BigDecimal drFifteen;

	// 16号金额
	@Column(name = "DR_SIXTEEN", precision = 18)
	private BigDecimal drSixteen;

	// 17号金额
	@Column(name = "DR_SEVENTEEN", precision = 18)
	private BigDecimal drSeventeen;

	// 18号金额
	@Column(name = "DR_EIGHTTEEN", precision = 18)
	private BigDecimal drEightteen;

	// 19号金额
	@Column(name = "DR_NINETEEN", precision = 18)
	private BigDecimal drNineteen;

	// 20号金额
	@Column(name = "DR_TWENTY", precision = 18)
	private BigDecimal drTwenty;

	// 21号金额
	@Column(name = "DR_TWENTYONE", precision = 18)
	private BigDecimal drTwentyone;

	// 22号金额
	@Column(name = "DR_TWENTYTWO", precision = 18)
	private BigDecimal drTwentytwo;

	// 23号金额
	@Column(name = "DR_TWENTYTHREE", precision = 18)
	private BigDecimal drTwentythree;

	// 24号金额
	@Column(name = "DR_TWENTYFOUR", precision = 18)
	private BigDecimal drTwentyfour;

	// 25号金额
	@Column(name = "DR_TWENTYFIVE", precision = 18)
	private BigDecimal drTwentyfive;

	// 26号金额
	@Column(name = "DR_TWENTYSIX", precision = 18)
	private BigDecimal drTwentysix;

	// 27号金额
	@Column(name = "DR_TWENTYSEVEN", precision = 18)
	private BigDecimal drTwentyseven;

	// 28号金额
	@Column(name = "DR_TWENTYEIGHT", precision = 18)
	private BigDecimal drTwentyeight;

	// 29号金额
	@Column(name = "DR_TWENTYNINE", precision = 18)
	private BigDecimal drTwentynine;

	// 30号金额
	@Column(name = "DR_THIRTY", precision = 18)
	private BigDecimal drThirty;

	// 31号金额
	@Column(name = "DR_THIRTYONE", precision = 18)
	private BigDecimal drThirtyone;

	// Constructors

	/** default constructor */
	public DayReport() {
	}

	public Long getDrId() {
		return this.drId;
	}

	public void setDrId(Long drId) {
		this.drId = drId;
	}

	public Long getDrMerNo() {
		return this.drMerNo;
	}

	public void setDrMerNo(Long drMerNo) {
		this.drMerNo = drMerNo;
	}

	public String getDrTsCurrency() {
		return this.drTsCurrency;
	}

	public void setDrTsCurrency(String drTsCurrency) {
		this.drTsCurrency = drTsCurrency;
	}

	public Long getDrYear() {
		return this.drYear;
	}

	public void setDrYear(Long drYear) {
		this.drYear = drYear;
	}

	public Long getDrMonth() {
		return this.drMonth;
	}

	public void setDrMonth(Long drMonth) {
		this.drMonth = drMonth;
	}

	public BigDecimal getDrOne() {
		return this.drOne;
	}

	public void setDrOne(BigDecimal drOne) {
		this.drOne = drOne;
	}

	public BigDecimal getDrTwo() {
		return this.drTwo;
	}

	public void setDrTwo(BigDecimal drTwo) {
		this.drTwo = drTwo;
	}

	public BigDecimal getDrThree() {
		return this.drThree;
	}

	public void setDrThree(BigDecimal drThree) {
		this.drThree = drThree;
	}

	public BigDecimal getDrFour() {
		return this.drFour;
	}

	public void setDrFour(BigDecimal drFour) {
		this.drFour = drFour;
	}

	public BigDecimal getDrFive() {
		return this.drFive;
	}

	public void setDrFive(BigDecimal drFive) {
		this.drFive = drFive;
	}

	public BigDecimal getDrSix() {
		return this.drSix;
	}

	public void setDrSix(BigDecimal drSix) {
		this.drSix = drSix;
	}

	public BigDecimal getDrSeven() {
		return this.drSeven;
	}

	public void setDrSeven(BigDecimal drSeven) {
		this.drSeven = drSeven;
	}

	public BigDecimal getDrEight() {
		return this.drEight;
	}

	public void setDrEight(BigDecimal drEight) {
		this.drEight = drEight;
	}

	public BigDecimal getDrNine() {
		return this.drNine;
	}

	public void setDrNine(BigDecimal drNine) {
		this.drNine = drNine;
	}

	public BigDecimal getDrTen() {
		return this.drTen;
	}

	public void setDrTen(BigDecimal drTen) {
		this.drTen = drTen;
	}

	public BigDecimal getDrEleven() {
		return this.drEleven;
	}

	public void setDrEleven(BigDecimal drEleven) {
		this.drEleven = drEleven;
	}

	public BigDecimal getDrTwelve() {
		return this.drTwelve;
	}

	public void setDrTwelve(BigDecimal drTwelve) {
		this.drTwelve = drTwelve;
	}

	public BigDecimal getDrThirteen() {
		return this.drThirteen;
	}

	public void setDrThirteen(BigDecimal drThirteen) {
		this.drThirteen = drThirteen;
	}

	public BigDecimal getDrFourteen() {
		return this.drFourteen;
	}

	public void setDrFourteen(BigDecimal drFourteen) {
		this.drFourteen = drFourteen;
	}

	public BigDecimal getDrFifteen() {
		return this.drFifteen;
	}

	public void setDrFifteen(BigDecimal drFifteen) {
		this.drFifteen = drFifteen;
	}

	public BigDecimal getDrSixteen() {
		return this.drSixteen;
	}

	public void setDrSixteen(BigDecimal drSixteen) {
		this.drSixteen = drSixteen;
	}

	public BigDecimal getDrSeventeen() {
		return this.drSeventeen;
	}

	public void setDrSeventeen(BigDecimal drSeventeen) {
		this.drSeventeen = drSeventeen;
	}

	public BigDecimal getDrEightteen() {
		return this.drEightteen;
	}

	public void setDrEightteen(BigDecimal drEightteen) {
		this.drEightteen = drEightteen;
	}

	public BigDecimal getDrNineteen() {
		return this.drNineteen;
	}

	public void setDrNineteen(BigDecimal drNineteen) {
		this.drNineteen = drNineteen;
	}

	public BigDecimal getDrTwenty() {
		return this.drTwenty;
	}

	public void setDrTwenty(BigDecimal drTwenty) {
		this.drTwenty = drTwenty;
	}

	public BigDecimal getDrTwentyone() {
		return this.drTwentyone;
	}

	public void setDrTwentyone(BigDecimal drTwentyone) {
		this.drTwentyone = drTwentyone;
	}

	public BigDecimal getDrTwentytwo() {
		return this.drTwentytwo;
	}

	public void setDrTwentytwo(BigDecimal drTwentytwo) {
		this.drTwentytwo = drTwentytwo;
	}

	public BigDecimal getDrTwentythree() {
		return this.drTwentythree;
	}

	public void setDrTwentythree(BigDecimal drTwentythree) {
		this.drTwentythree = drTwentythree;
	}

	public BigDecimal getDrTwentyfour() {
		return this.drTwentyfour;
	}

	public void setDrTwentyfour(BigDecimal drTwentyfour) {
		this.drTwentyfour = drTwentyfour;
	}

	public BigDecimal getDrTwentyfive() {
		return this.drTwentyfive;
	}

	public void setDrTwentyfive(BigDecimal drTwentyfive) {
		this.drTwentyfive = drTwentyfive;
	}

	public BigDecimal getDrTwentysix() {
		return this.drTwentysix;
	}

	public void setDrTwentysix(BigDecimal drTwentysix) {
		this.drTwentysix = drTwentysix;
	}

	public BigDecimal getDrTwentyseven() {
		return this.drTwentyseven;
	}

	public void setDrTwentyseven(BigDecimal drTwentyseven) {
		this.drTwentyseven = drTwentyseven;
	}

	public BigDecimal getDrTwentyeight() {
		return this.drTwentyeight;
	}

	public void setDrTwentyeight(BigDecimal drTwentyeight) {
		this.drTwentyeight = drTwentyeight;
	}

	public BigDecimal getDrTwentynine() {
		return this.drTwentynine;
	}

	public void setDrTwentynine(BigDecimal drTwentynine) {
		this.drTwentynine = drTwentynine;
	}

	public BigDecimal getDrThirty() {
		return this.drThirty;
	}

	public void setDrThirty(BigDecimal drThirty) {
		this.drThirty = drThirty;
	}

	public BigDecimal getDrThirtyone() {
		return this.drThirtyone;
	}

	public void setDrThirtyone(BigDecimal drThirtyone) {
		this.drThirtyone = drThirtyone;
	}

	public Long getDrGwNo() {
		return drGwNo;
	}

	public void setDrGwNo(Long drGwNo) {
		this.drGwNo = drGwNo;
	}

}