package com.jinxin.service.paygateway.enterprise.cib.util;

import java.io.IOException;
import java.util.Properties;

import com.jinxin.service.paygateway.online.abc.util.AbcConstants;


/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class CibEConstants {

	//支付地址 测试：https://3gtest.cib.com.cn:38091/payment/entry.action    正式 https://payment.cib.com.cn/payment/entry.action
	public static final String SERVICEURL = "https://payment.cib.com.cn/payment/entry.action";
	
	//支付地址 测试:https://3gtest.cib.com.cn:38091/payment/api/rest    正式: https://payment.cib.com.cn/payment/api/rest
	public static final String QUERYURL = "https://payment.cib.com.cn/payment/api/rest";

	//支付服务名称
	public static final  String SERVICE="cib.netpay.b2b";
	
	//查询服务名称
	public static final  String QUERYSERVICE="cib.netpay.order.get";
	
	//接口版本号，固定01。
	public static final  String VERSION="01";

	//商户代号，定长6位
	public static final String  MRCHNO = "330424";//测试：110004  正式：330424
	
	//二级商户名称，指签约商户下属二级商户名称，可选参数。
	public static final String SUBMERCH = "无";

	//币种
	public static final String CURRENCY = "CNY";

	//商户秘钥
	public static final String KEY = "op349761";//测试：12345678 正式：op349761
	
}
