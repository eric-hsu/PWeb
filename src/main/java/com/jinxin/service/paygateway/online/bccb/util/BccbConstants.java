package com.jinxin.service.paygateway.online.bccb.util;

import java.io.IOException;
import java.util.Properties;

import com.jinxin.service.paygateway.online.abc.util.AbcConstants;


/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class BccbConstants {

	//商户号
	public static final String merchantID = "000000000002";
	
	//证书号码
	public static final String certNumber = "0211681a";

	//0表示支付，1批量对帐，2单笔对帐
	public static final  String actionType="0";
	
	//商品类型 付选择，0有取货地址，带冲正，1无取货地址不带冲正，2有取货地址，不带冲正
	public static final  String goodsType="0";
	
	//通知类型
	public static final  String notifyType="0";
}
