package com.jinxin.service.paygateway.online.cgb.util;

import java.io.IOException;
import java.util.Properties;


/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class CgbConstants {

	//商户号
	public static final  String MERCHID="002100121800018";
	
	//支付地址
	public static final  String PAYURL="http://113.108.207.154:8087/spayment/ent_payment.jsp";
	
	//查询地址
	public static final  String QUERYURL="http://113.108.207.154:8087/spayment/servlet/gfbank.payment.PLQuery";
	
	//私钥名称
	public static final  String PRIVATEKEY="merkey002100121800018.private";
	
	//公钥名称
	public static final  String PUBLICKEY="paykey.public";
}
