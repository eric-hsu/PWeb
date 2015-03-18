package com.jinxin.service.paygateway.online.hxb.util;

/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class HxbConstants {
	public static final String merchantId = "0870000001";//测试：0450000095 生产：0870000001
	
	public static final String currency = "CNY";
	
	// 交易类型 //0 个人，1单位
	public static final String custType = "0";
	
	// 01 一般支付 06预授权 10冻结 29电话支付
	public static final String trnxType = "01";
	
	// 16 撤销支付   18 实时退款
	public static final String refundtrnxType = "16";
	
	// 交易吗：本行卡电子支付（订单上送） MP001
	public static final String PayTrnxCode = "MP001";
	
	
	// 交易吗：本行卡电子支付（订单上送） MQ009  MQ001
	public static final String QueryTrnxCode = "MQ009";
	
	// 交易吗：退货
	public static final String RefundTrnxCode = "MP002";
	
	// 交报文唯一标识
	public static final String Identification = "111";
	
	// 渠道
	public static final String Channel = "123";
	
	// 查询类型 0当日，1历史
	public static final String searchType = "0";

}
