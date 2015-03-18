package com.jinxin.service.paygateway.online.citic.util;

import java.io.IOException;
import java.util.Properties;

import com.jinxin.service.paygateway.online.abc.util.AbcConstants;


/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class CiticConstants {

	//支付地址 测试：https://ec.test.bank.ecitic.com/pec/e3rdplaceorder.do    正式https://b2c.bank.ecitic.com/pec/e3rdplaceorder.do
	public static final String SERVICEURL = "https://ec.test.bank.ecitic.com/pec/e3rdplaceorder.do";
	
	//支付地址 测试：https://ec.test.bank.ecitic.com/WebDLink/e3rdqryorderbyno.do
	public static final String QUERYURL = "https://ec.test.bank.ecitic.com/WebDLink/e3rdqryorderbyno.do";

	//第三方支付平台编号 
	public static final  String E3RDPAYNO="100599";
	
	//订单支付模式 01：直接支付  02：冻结支付   目前固定送01（必输项）
	public static final  String ORDERMODE="01";
	
	//订单支付币种 01：人民币 目前固定送01（必输项）
	public static final  String CURRID="01";

	//通知模式 01：浏览器URL跳转通知  目前固定送01（必输项）
	public static final String  NOTIFYMODE = "01";
	
	//支付结果通知范围 01：只通知成功订单 02：成功和失败订单均通知（必输项）
	public static final String NOTIFYSCOPE = "02";
	
	//风险级别  订单风险级别。00：系统默认级别 01：大额网关级别 具体级别可在业务开通时设定。（必输项）
	public static final String RISKLEVEL = "00";

	//支持卡种 订单支持的支付卡种。00：借记卡和信用卡 01：仅借记卡 02：仅信用卡 商户可以针对订单设定可接受的支付卡种。（必输项）
	public static final String SUPPTCARDTYPE = "00";

	/*是否进行防钓鱼控制  Y：进行防钓鱼控制；
	N：不进行防钓鱼控制。
	防钓鱼控制措施包括：
	1）商户信任域名验证；
	2）订单人信息提示；
	3）支付结果报文返回安全信息：
	调用B2C支付网关的请求URL；
	客户确认支付的MAC地址；
	客户确认支付的IP地址。*/
	public static final String ISSAFEINF = "N";
	
}
