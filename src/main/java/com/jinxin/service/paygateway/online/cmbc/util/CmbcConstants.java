package com.jinxin.service.paygateway.online.cmbc.util;

/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class CmbcConstants {
	//测试环境支付地址：http://111.205.207.118:55000/pweb/b2cprelogin.do  正式环境支付地址：https://per.cmbc.com.cn/pweb/b2cprelogin.do
	public static final String SERVICEURL = "http://111.205.207.118:55000/pweb/b2cprelogin.do";
	
	//测试环境查询地址：http://111.205.207.118:55003/epay/connectForQuery.do  生成查询地址：https://pay.cmbc.com.cn/epay/connectForQuery.do
	public static final String QUERYURL = "http://111.205.207.118:55003/epay/connectForQuery.do";
	
	//测试环境退款地址：http://111.205.207.118:55003/epay/connectForRefund.do  生成退款地址：https://pay.cmbc.com.cn/epay/connectForRefund.do
	public static final String REFUNDURL = "http://111.205.207.118:55003/epay/connectForRefund.do";
	
	//商户代码
	public static final String corpID = "66002";
	
	//私钥密钥
	public static final String pxfpassword = "1111";
	
	//商户名称
	public static final String corpName = "金信集团";
	
	//是否实时返回标志
	public static final String CorpRetType = "0";
	
	
	
	
}
