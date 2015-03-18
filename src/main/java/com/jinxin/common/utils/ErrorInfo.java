package com.jinxin.common.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.jinxin.model.ConstantsBean;
import com.jinxin.model.ReturnBean;


/**
 * 
 * <p>Title: </p>
 * <p>Description: 管理错误异常</p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-7-4下午06:31:56
 */
public class ErrorInfo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//存储错误信息Map(中文)
	private static final Map<String,String[]> ERROR_INFO_MAP_ZH = new HashMap<String, String[]>();
	
	//存储错误信息Map(英文)
	private static final Map<String,String[]> ERROR_INFO_MAP_EN = new HashMap<String, String[]>();
	
	static{
		
		/**
		 * 中文
		 */
		ERROR_INFO_MAP_ZH.put(ConstantsBean.SUCCESS_CODE,new String[]{"成功,继续","成功,继续"});
		
		//接口异常码
		ERROR_INFO_MAP_ZH.put("I0001",new String[]{"商户号不能为空","商户号不能为空"});
		ERROR_INFO_MAP_ZH.put("I0002",new String[]{"网关接入号不能为空","网关接入号不能为空"});
		ERROR_INFO_MAP_ZH.put("I0003",new String[]{"加密值不能为空","加密值不能为空"});
		ERROR_INFO_MAP_ZH.put("I0004",new String[]{"商户号网关接入号错误","商户号网关接入号错误"});
		ERROR_INFO_MAP_ZH.put("I0005",new String[]{"商户号未激活","商户号未激活"});
		ERROR_INFO_MAP_ZH.put("I0006",new String[]{"商户号停用","商户号停用"});
		ERROR_INFO_MAP_ZH.put("I0007",new String[]{"商户号删除","商户号删除"});
		ERROR_INFO_MAP_ZH.put("I0008",new String[]{"商户号状态异常","商户号状态异常"});
		ERROR_INFO_MAP_ZH.put("I0009",new String[]{"网关接入号未激活","网关接入号未激活"});
		ERROR_INFO_MAP_ZH.put("I0010",new String[]{"网关接入号停用","网关接入号停用"});
		ERROR_INFO_MAP_ZH.put("I0011",new String[]{"网关接入号删除","网关接入号删除"});
		ERROR_INFO_MAP_ZH.put("I0012",new String[]{"网关接入号状态异常","网关接入号状态异常"});
		ERROR_INFO_MAP_ZH.put("I0013",new String[]{"加密值错误","加密值错误"});
		ERROR_INFO_MAP_ZH.put("I0014",new String[]{"非正式网关接入号接入正式接口","非正式网关接入号接入正式接口"});
		ERROR_INFO_MAP_ZH.put("I0015",new String[]{"非测试网关接入号接入测试接口","非测试网关接入号接入测试接口"});
		ERROR_INFO_MAP_ZH.put("I0016",new String[]{"该网关接入号未绑定本接口","该网关接入号未绑定本接口"});
		ERROR_INFO_MAP_ZH.put("I0017",new String[]{"商户订单号为空","商户订单号为空"});
		ERROR_INFO_MAP_ZH.put("I0018",new String[]{"订单号不能超过50位","订单号不能超过50位"});
		ERROR_INFO_MAP_ZH.put("I0019",new String[]{"订单金额为空","订单金额为空"});
		ERROR_INFO_MAP_ZH.put("I0020",new String[]{"订单金额不正确","订单金额不正确"});
		ERROR_INFO_MAP_ZH.put("I0021",new String[]{"订单金额小数位数不能超过2位且不能小于0","订单金额小数位数不能超过2位且不能小于0"});
		ERROR_INFO_MAP_ZH.put("I0022",new String[]{"订单币种为空","订单币种为空"});
		ERROR_INFO_MAP_ZH.put("I0023",new String[]{"订单币种错误","订单币种错误"});
		ERROR_INFO_MAP_ZH.put("I0024",new String[]{"订单返回地址为空","订单返回地址为空"});
		ERROR_INFO_MAP_ZH.put("I0025",new String[]{"订单返回地址长度不能超过1000个字符","订单返回地址长度不能超过1000个字符"});
		ERROR_INFO_MAP_ZH.put("I0026",new String[]{"卡号不能为空","卡号不能为空"});
		ERROR_INFO_MAP_ZH.put("I0027",new String[]{"卡号只能输入13、16位","卡号只能输入13、16位"});
		ERROR_INFO_MAP_ZH.put("I0028",new String[]{"卡号只能输入数字","卡号只能输入数字"});
		ERROR_INFO_MAP_ZH.put("I0029",new String[]{"卡号只能以4、5开头","卡号只能以4、5开头"});
		ERROR_INFO_MAP_ZH.put("I0030",new String[]{"卡号不正确","卡号不正确"});
		ERROR_INFO_MAP_ZH.put("I0031",new String[]{"月份不能为空","月份不能为空"});
		ERROR_INFO_MAP_ZH.put("I0032",new String[]{"月份只能输入2位","月份只能输入2位"});
		ERROR_INFO_MAP_ZH.put("I0033",new String[]{"月份只能输入数字","月份只能输入数字"});
		ERROR_INFO_MAP_ZH.put("I0034",new String[]{"月份只能在1-12之间","月份只能在1-12之间"});
		ERROR_INFO_MAP_ZH.put("I0035",new String[]{"年份不能为空","年份不能为空"});
		ERROR_INFO_MAP_ZH.put("I0036",new String[]{"年份只能输入4位","年份只能输入4位"});
		ERROR_INFO_MAP_ZH.put("I0037",new String[]{"年份只能输入数字","年份只能输入数字"});
		ERROR_INFO_MAP_ZH.put("I0038",new String[]{"年月不能小于当前日期,且不能大于10年","年月不能小于当前日期,且不能大于10年"});
		ERROR_INFO_MAP_ZH.put("I0039",new String[]{"验证码不能为空","验证码不能为空"});
		ERROR_INFO_MAP_ZH.put("I0040",new String[]{"验证码只能输入3位","验证码只能输入3位"});
		ERROR_INFO_MAP_ZH.put("I0041",new String[]{"验证码只能输入数字","验证码只能输入数字"});
		ERROR_INFO_MAP_ZH.put("I0042",new String[]{"发卡银行不能为空","发卡银行不能为空"});
		ERROR_INFO_MAP_ZH.put("I0043",new String[]{"发卡银行只能输入2-50个字符","发卡银行只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0044",new String[]{"名不能为空","名不能为空"});
		ERROR_INFO_MAP_ZH.put("I0045",new String[]{"名只能输入2-50个字符","名只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0046",new String[]{"姓不能为空","姓不能为空"});
		ERROR_INFO_MAP_ZH.put("I0047",new String[]{"姓只能输入2-50个字符","姓只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0048",new String[]{"电子邮箱不能为空","电子邮箱不能为空"});
		ERROR_INFO_MAP_ZH.put("I0049",new String[]{"电子邮箱只能输入2-100个字符","电子邮箱只能输入2-100个字符"});
		ERROR_INFO_MAP_ZH.put("I0050",new String[]{"电子邮箱格式不正确","电子邮箱格式不正确"});
		ERROR_INFO_MAP_ZH.put("I0051",new String[]{"持卡人电话不能为空","持卡人电话不能为空"});
		ERROR_INFO_MAP_ZH.put("I0052",new String[]{"持卡人电话只能输入2-50个字符","持卡人电话只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0053",new String[]{"持卡人国家不能为空","持卡人国家不能为空"});
		ERROR_INFO_MAP_ZH.put("I0054",new String[]{"持卡人国家只能输入2-50个字符","持卡人国家只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0055",new String[]{"持卡人地址不能为空","持卡人地址不能为空"});
		ERROR_INFO_MAP_ZH.put("I0056",new String[]{"持卡人地址只能输入2-100个字符","持卡人地址只能输入2-100个字符"});
		ERROR_INFO_MAP_ZH.put("I0057",new String[]{"持卡人邮编不能为空","持卡人邮编不能为空"});
		ERROR_INFO_MAP_ZH.put("I0058",new String[]{"持卡人邮编只能输入2-50个字符","持卡人邮编只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0059",new String[]{"属性{0},不能为空","属性{0},不能为空"});
		ERROR_INFO_MAP_ZH.put("I0060",new String[]{"属性{0},长度大于{1}","属性{0},长度大于{1}"});
		ERROR_INFO_MAP_ZH.put("I0061",new String[]{"商户订单号存在非失败交易","商户订单号存在非失败交易"});
		ERROR_INFO_MAP_ZH.put("I0062",new String[]{"流水订单号存在(只判断正常的状态)","流水订单号存在(只判断正常的状态)"});
		ERROR_INFO_MAP_ZH.put("I0063",new String[]{"持卡人州/省不能为空","持卡人州/省不能为空"});
		ERROR_INFO_MAP_ZH.put("I0064",new String[]{"持卡人州/省只能输入2-50个字符","持卡人州/省只能输入2-50个字符"});
		ERROR_INFO_MAP_ZH.put("I0065",new String[]{"持卡人市不能为空","持卡人市不能为空"});
		ERROR_INFO_MAP_ZH.put("I0066",new String[]{"持卡人市最多只能输入100个字符","持卡人市最多只能输入100个字符"});
		ERROR_INFO_MAP_ZH.put("I0067",new String[]{"备注最多只能输入500个字符","备注最多只能输入500个字符"});
		ERROR_INFO_MAP_ZH.put("I0068",new String[]{"RSA公私钥对验证不不通过","RSA公私钥对验证不不通过"});
		ERROR_INFO_MAP_ZH.put("I0069",new String[]{"商户服务器通知地址不能为空","商户服务器通知地址不能为空"});
		ERROR_INFO_MAP_ZH.put("I0070",new String[]{"支付系统流水号不能为空（用于查询）","支付系统流水号不能为空（用于查询）"});
		ERROR_INFO_MAP_ZH.put("I0071",new String[]{"订单不存在","订单不存在"});
		ERROR_INFO_MAP_ZH.put("I0072",new String[]{"订单未成功支付","订单未成功支付"});
		ERROR_INFO_MAP_ZH.put("I0073",new String[]{"支付网关不存在（平台）","支付网关不存在（平台）"});
		ERROR_INFO_MAP_ZH.put("I0074",new String[]{"退款金额不能大于支付金额","退款金额不能大于支付金额"});
		ERROR_INFO_MAP_ZH.put("I0075",new String[]{"退款金额为空","退款金额为空"});
		ERROR_INFO_MAP_ZH.put("I0076",new String[]{"退款中，不能重复退款","退款中，不能重复退款"});
		ERROR_INFO_MAP_ZH.put("I0077",new String[]{"测试商户暂不支持退款","测试商户暂不支持退款"});
		ERROR_INFO_MAP_ZH.put("I0078",new String[]{"商户不存在","商户不存在"});
		ERROR_INFO_MAP_ZH.put("I0079",new String[]{"订单号错误","订单号错误"});
		ERROR_INFO_MAP_ZH.put("I0080",new String[]{"订单金额错误","订单金额错误"});
		ERROR_INFO_MAP_ZH.put("I0081",new String[]{"商户不在合同期内","商户不在合同期内"});
		ERROR_INFO_MAP_ZH.put("I0082",new String[]{"卡类型错误","卡类型错误"});
		ERROR_INFO_MAP_ZH.put("I0083",new String[]{"支付类型错误","支付类型错误"});

		/** 风控异常码 **/
		//来源网址
		ERROR_INFO_MAP_ZH.put("R0001",new String[]{"来源网址挡掉","来源网址挡掉"});
		//卡BIN
		ERROR_INFO_MAP_ZH.put("R0002",new String[]{"卡号在Visa卡Bin中","卡号在Visa卡Bin中"});
		ERROR_INFO_MAP_ZH.put("R0003",new String[]{"卡号在Master卡Bin中","卡号在Master卡Bin中"});
		//黑名单
		ERROR_INFO_MAP_ZH.put("R0004",new String[]{"卡号在黑名单中","卡号在黑名单中"});
		ERROR_INFO_MAP_ZH.put("R0005",new String[]{"邮箱在黑名单中","邮箱在黑名单中"});
		ERROR_INFO_MAP_ZH.put("R0006",new String[]{"IP在黑名单中","IP在黑名单中"});
		ERROR_INFO_MAP_ZH.put("R0007",new String[]{"其它元素黑名单","其它元素黑名单"});
		//MaxMind
		ERROR_INFO_MAP_ZH.put("R0008",new String[]{"未通过maxmind分数验证","未通过maxmind分数验证"});
		ERROR_INFO_MAP_EN.put("R0018",new String[]{"未通过发卡行限定","未通过发卡行限定"});
		//金额限定
		ERROR_INFO_MAP_ZH.put("R0009",new String[]{"未设置金额限定","未设置金额限定"});
		ERROR_INFO_MAP_ZH.put("R0010",new String[]{"未通过单笔限额","未通过单笔限额"});
		ERROR_INFO_MAP_ZH.put("R0011",new String[]{"未通过日限额","未通过日限额"});
		ERROR_INFO_MAP_ZH.put("R0012",new String[]{"未通过周限额","未通过周限额"});
		ERROR_INFO_MAP_ZH.put("R0013",new String[]{"未通过月限额","未通过月限额"});
		//IP限定
		ERROR_INFO_MAP_ZH.put("R0014",new String[]{"未通过IP国家白名单限定","未通过IP国家白名单限定"});
		ERROR_INFO_MAP_ZH.put("R0015",new String[]{"未通过IP国家黑名单限定","未通过IP国家黑名单限定"});
		ERROR_INFO_MAP_ZH.put("R0016",new String[]{"未通过IP段黑名单限定","未通过IP段黑名单限定"});
		//支付次数限定
		ERROR_INFO_MAP_ZH.put("R0017",new String[]{"未通过支付次数限定","未通过支付次数限定"});
		
		//通道异常码
		ERROR_INFO_MAP_ZH.put("C0001",new String[]{"商户网关接入号未绑定通道","商户网关接入号未绑定通道"});
		ERROR_INFO_MAP_ZH.put("C0002",new String[]{"商户网关接入号未设置扣率","商户网关接入号未设置扣率"});
		ERROR_INFO_MAP_ZH.put("C0003",new String[]{"商户网关接入号通道扣率设置错误","商户网关接入号通道扣率设置错误"});
		ERROR_INFO_MAP_ZH.put("C0004",new String[]{"商户网关接入号通道扣率设置错误","商户网关接入号通道扣率设置错误"});
		ERROR_INFO_MAP_ZH.put("C0005",new String[]{"币种未设置汇率","币种未设置汇率"});
		ERROR_INFO_MAP_ZH.put("C0006",new String[]{"获取汇率失败","获取汇率失败"});
		ERROR_INFO_MAP_ZH.put("C0007",new String[]{"商户未绑定支付域名","商户未绑定支付域名"});
		ERROR_INFO_MAP_ZH.put("C0008",new String[]{"商户网关接入号未绑定 {0} 通道","商户网关接入号未绑定 {0} 通道"});
		ERROR_INFO_MAP_ZH.put("C0009",new String[]{"商户网关接入号未设置扣率","商户网关接入号未设置扣率"});
		
		//系统异常码
		ERROR_INFO_MAP_ZH.put("S0001",new String[]{"保存到异常交易表失败","保存到异常交易表失败"});
		ERROR_INFO_MAP_ZH.put("S0002",new String[]{"保存到持卡人信息表失败","保存到持卡人信息表失败"});
		ERROR_INFO_MAP_ZH.put("S0003",new String[]{"保存到非正式交易表失败","保存到非正式交易表失败"});
		ERROR_INFO_MAP_ZH.put("S0004",new String[]{"保存到附加交易表失败","保存到附加交易表失败"});
		ERROR_INFO_MAP_ZH.put("S0005",new String[]{"获取通道信息异常","获取通道信息异常"});
		ERROR_INFO_MAP_ZH.put("S0006",new String[]{"保存交易信息失败","保存交易信息失败"});
		ERROR_INFO_MAP_ZH.put("S0007",new String[]{"更新测试表失败","更新测试表失败"});
		ERROR_INFO_MAP_ZH.put("S0008",new String[]{"删除异常记录表失败","删除异常记录表失败"});
		ERROR_INFO_MAP_ZH.put("S0009",new String[]{"保存交易记录表失败","保存交易记录表失败"});
		ERROR_INFO_MAP_ZH.put("S0010",new String[]{"获取商户绑定域名信息失败","获取商户绑定域名信息失败"});
		ERROR_INFO_MAP_ZH.put("S0011",new String[]{"保存持卡人信息表失败","保存持卡人信息表失败"});
		ERROR_INFO_MAP_ZH.put("S0012",new String[]{"获取银行通道信息失败","获取银行通道信息失败"});
		ERROR_INFO_MAP_ZH.put("S0013",new String[]{"银行返回修改交易记录表失败","银行返回修改交易记录表失败"});
		ERROR_INFO_MAP_ZH.put("S0014",new String[]{"通道未绑定邮件域名","通道未绑定邮件域名"});
		ERROR_INFO_MAP_ZH.put("S0015",new String[]{"保存交易监控记录失败","保存交易监控记录失败"});
		
		//未知异常码
		ERROR_INFO_MAP_ZH.put("E0001",new String[]{"操作超时","操作超时"});
		ERROR_INFO_MAP_ZH.put("E0002",new String[]{"操作超时","操作超时"});
		ERROR_INFO_MAP_ZH.put("E0003",new String[]{"发送银行失败","发送银行失败"});
		ERROR_INFO_MAP_ZH.put("E0004",new String[]{"调用银行异常","调用银行异常"});
		ERROR_INFO_MAP_ZH.put("E0005",new String[]{"银行返回的通道代码不存在","银行返回的通道代码不存在"});
		ERROR_INFO_MAP_ZH.put("E0006",new String[]{"返回银行失败","返回银行失败"});
		
		//返回商户 
		ERROR_INFO_MAP_ZH.put("T0001", new String[]{"交易成功，测试交易不收取任何费用","交易成功，测试交易不收取任何费用"});
		ERROR_INFO_MAP_ZH.put("T0002", new String[]{"待处理","待处理"});
		ERROR_INFO_MAP_ZH.put("T0003", new String[]{"成功，2方通道，未连接银行","成功，2方通道，未连接银行"});
		
		ERROR_INFO_MAP_ZH.put("KM004", new String[]{"同一客户单日交易金额大于1万元"});
		ERROR_INFO_MAP_ZH.put("KM005", new String[]{"交易单笔交易金额大于5000元，且购买的商品种类只为1种"});
		
		/**
		 * 英文
		 */
		
		ERROR_INFO_MAP_EN.put(ConstantsBean.SUCCESS_CODE,new String[]{"Success","Success"});
		
		//接口异常码
		ERROR_INFO_MAP_EN.put("I0001",new String[]{"Merchant No. can not be empty","Merchant No. can not be empty"});
		ERROR_INFO_MAP_EN.put("I0002",new String[]{"Gateway No.can not empty","Gateway No.can not empty"});
		ERROR_INFO_MAP_EN.put("I0003",new String[]{"Gateway of Merchant No.can not be empty","Gateway of Merchant No.can not be empty"});
		ERROR_INFO_MAP_EN.put("I0004",new String[]{"Gateway of Merchant No.is incorrect","Gateway of Merchant No.is incorrect"});
		ERROR_INFO_MAP_EN.put("I0005",new String[]{"Merchant No.is not actived","Merchant No.is not actived"});
		ERROR_INFO_MAP_EN.put("I0006",new String[]{"Merchant No. is disabled","Merchant No. is disabled"});
		ERROR_INFO_MAP_EN.put("I0007",new String[]{"Merchant No.is canceled","Merchant No.is canceled"});
		ERROR_INFO_MAP_EN.put("I0008",new String[]{"The state of merchant No. is anomalous","The state of merchant No. is anomalous"});
		ERROR_INFO_MAP_EN.put("I0009",new String[]{"Gateway No.is not actived","Gateway No.is not actived"});
		ERROR_INFO_MAP_EN.put("I0010",new String[]{"Gateway No.is disabled","Gateway No.is disabled"});
		ERROR_INFO_MAP_EN.put("I0011",new String[]{"Gateway No.is canceled","Gateway No.is canceled"});
		ERROR_INFO_MAP_EN.put("I0012",new String[]{"The state of gateway No.is anomalous","The state of gateway No.is anomalous"});
		ERROR_INFO_MAP_EN.put("I0013",new String[]{"Encryption value is incorrect","Encryption value is incorrect"});
		ERROR_INFO_MAP_EN.put("I0014",new String[]{"Informal gateway No. accesses to formal interface","Informal gateway No. accesses to formal interface"});
		ERROR_INFO_MAP_EN.put("I0015",new String[]{"Untested gateway No. accesses to test interface","Untested gateway No. accesses to test interface"});
		ERROR_INFO_MAP_EN.put("I0016",new String[]{"The gateway No. does not blind the interface","The gateway No. does not blind the interface"});
		ERROR_INFO_MAP_EN.put("I0017",new String[]{"Merchant order No. is empty","Merchant order No. is empty"});
		ERROR_INFO_MAP_EN.put("I0018",new String[]{"Order No. can not exceed 50 characters","Order No. can not exceed 50 characters"});
		ERROR_INFO_MAP_EN.put("I0019",new String[]{"Order value can not be empty","Order value can not be empty"});
		ERROR_INFO_MAP_EN.put("I0020",new String[]{"Order value is incorrect","Order value is incorrect"});
		ERROR_INFO_MAP_EN.put("I0021",new String[]{"The decimal place of order value should be between 0-2 digit","The decimal place of order value should be between 0-2 digit"});
		ERROR_INFO_MAP_EN.put("I0022",new String[]{"Order currency is empty","Order currency is empty"});
		ERROR_INFO_MAP_EN.put("I0023",new String[]{"Order currency is incorrect","Order currency is incorrect"});
		ERROR_INFO_MAP_EN.put("I0024",new String[]{"Return URL can not be empty","Return URL can not be empty"});
		ERROR_INFO_MAP_EN.put("I0025",new String[]{"The length of returnning URL can not exceed 1000 characters","The length of returnning URL can not exceed 1000 characters"});
		ERROR_INFO_MAP_EN.put("I0026",new String[]{"Card No can not be empty","Card No can not be empty"});
		ERROR_INFO_MAP_EN.put("I0027",new String[]{"Please input 13 or 16 digit","Please input 13 or 16 digit"});
		ERROR_INFO_MAP_EN.put("I0028",new String[]{"Please input number","Please input number"});
		ERROR_INFO_MAP_EN.put("I0029",new String[]{"Card No.should begin with 4 or 5","Card No.should begin with 4 or 5"});
		ERROR_INFO_MAP_EN.put("I0030",new String[]{"Card No. is incorrect","Card No. is incorrect"});
		ERROR_INFO_MAP_EN.put("I0031",new String[]{"Month can not be empty","Month can not be empty"});
		ERROR_INFO_MAP_EN.put("I0032",new String[]{"Please input double digit only","Please input double digit only"});
		ERROR_INFO_MAP_EN.put("I0033",new String[]{"Please input numbers only","Please input numbers only"});
		ERROR_INFO_MAP_EN.put("I0034",new String[]{"The month should be between 1-12","The month should be between 1-12"});
		ERROR_INFO_MAP_EN.put("I0035",new String[]{"Year can not be empty","Year can not be empty"});
		ERROR_INFO_MAP_EN.put("I0036",new String[]{"Please input 4-digit only","Please input 4-digit only"});
		ERROR_INFO_MAP_EN.put("I0037",new String[]{"Please input numbers only","Please input numbers only"});
		ERROR_INFO_MAP_EN.put("I0038",new String[]{"Year and month can not be smaller than current date and greater than 10 year","Year and month can not be smaller than current date and greater than 10 year"});
		ERROR_INFO_MAP_EN.put("I0039",new String[]{"Verification code can not be empty","Verification code can not be empty"});
		ERROR_INFO_MAP_EN.put("I0040",new String[]{"Please input 3-digit Verification code","Please input 3-digit Verification code"});
		ERROR_INFO_MAP_EN.put("I0041",new String[]{"Please input numbers only","Please input numbers only"});
		ERROR_INFO_MAP_EN.put("I0042",new String[]{"Issuing bank can not  be empty","Issuing bank can not  be empty"});
		ERROR_INFO_MAP_EN.put("I0043",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0044",new String[]{"First name can not be empty","First name can not be empty"});
		ERROR_INFO_MAP_EN.put("I0045",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0046",new String[]{"Last name can not empty","Last name can not empty"});
		ERROR_INFO_MAP_EN.put("I0047",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0048",new String[]{"E-mail address can not be empty","E-mail address can not be empty"});
		ERROR_INFO_MAP_EN.put("I0049",new String[]{"Please input between 2-100 characters only","Please input between 2-100 characters only"});
		ERROR_INFO_MAP_EN.put("I0050",new String[]{"E-mail address format is incorrect","E-mail address format is incorrect"});
		ERROR_INFO_MAP_EN.put("I0051",new String[]{"The phone number of card holder can not be empty","The phone number of card holder can not be empty"});
		ERROR_INFO_MAP_EN.put("I0052",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0053",new String[]{"The country of cardholder can not be empty","The country of cardholder can not be empty"});
		ERROR_INFO_MAP_EN.put("I0054",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0055",new String[]{"The address of cardholder can not be empty","The address of cardholder can not be empty"});
		ERROR_INFO_MAP_EN.put("I0056",new String[]{"Please input between 2-100 characters only","Please input between 2-100 characters only"});
		ERROR_INFO_MAP_EN.put("I0057",new String[]{"The zip code of cardholder can not be empty","The zip code of cardholder can not be empty"});
		ERROR_INFO_MAP_EN.put("I0058",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0059",new String[]{"Property{0},can not be empty","Property{0},can not be empty"});
		ERROR_INFO_MAP_EN.put("I0060",new String[]{"Property{0},length is more than {1}","Property{0},length is more than {1}"});
		ERROR_INFO_MAP_EN.put("I0061",new String[]{"Merchant order NO.has unsuccessful transation","Merchant order NO.has unsuccessful transation"});
		ERROR_INFO_MAP_EN.put("I0062",new String[]{"Host index exists","Host index exists"});
		ERROR_INFO_MAP_EN.put("I0063",new String[]{"The state/province of cardholder can not be empty","The state/province of cardholder can not be empty"});
		ERROR_INFO_MAP_EN.put("I0064",new String[]{"Please input between 2-50 characters only","Please input between 2-50 characters only"});
		ERROR_INFO_MAP_EN.put("I0065",new String[]{"The city of cardholder can not be empty","The city of cardholder can not be empty"});
		ERROR_INFO_MAP_EN.put("I0066",new String[]{"Please input no more than 100 characters","Please input no more than 100 characters"});
		ERROR_INFO_MAP_EN.put("I0067",new String[]{"Please input no more than 500 characters","Please input no more than 500 characters"});
		ERROR_INFO_MAP_EN.put("I0068",new String[]{"RSA Key Error","RSA Key Error"});
		ERROR_INFO_MAP_EN.put("I0069",new String[]{"商户服务器通知地址不能为空","商户服务器通知地址不能为空"});
		ERROR_INFO_MAP_EN.put("I0070",new String[]{"支付系统流水号不能为空（用于查询）","支付系统流水号不能为空（用于查询）"});
		ERROR_INFO_MAP_EN.put("I0071",new String[]{"订单不存在","订单不存在"});
		ERROR_INFO_MAP_EN.put("I0072",new String[]{"订单未成功支付","订单未成功支付"});
		ERROR_INFO_MAP_EN.put("I0073",new String[]{"支付网关不存在（平台）","支付网关不存在（平台）"});
		ERROR_INFO_MAP_EN.put("I0074",new String[]{"退款金额不能大于支付金额","退款金额不能大于支付金额"});
		ERROR_INFO_MAP_EN.put("I0075",new String[]{"退款金额为空","退款金额为空"});
		ERROR_INFO_MAP_EN.put("I0076",new String[]{"退款中，不能重复退款","退款中，不能重复退款"});
		ERROR_INFO_MAP_EN.put("I0077",new String[]{"测试商户暂不支持退款","测试商户暂不支持退款"});
		ERROR_INFO_MAP_EN.put("I0078",new String[]{"商户不存在","商户不存在"});
		ERROR_INFO_MAP_EN.put("I0079",new String[]{"订单号错误","订单号错误"});
		ERROR_INFO_MAP_EN.put("I0080",new String[]{"订单金额错误","订单金额错误"});
		ERROR_INFO_MAP_EN.put("I0081",new String[]{"商户不在合同期内","商户不在合同期内"});
		ERROR_INFO_MAP_EN.put("I0082",new String[]{"卡类型错误","卡类型错误"});
		ERROR_INFO_MAP_EN.put("I0083",new String[]{"支付类型错误","支付类型错误"});
		
		/** 风控异常码 **/
		//来源网址
		ERROR_INFO_MAP_EN.put("R0001",new String[]{"Source Website blocked","Source Website blocked"});
		//卡BIN
		ERROR_INFO_MAP_EN.put("R0002",new String[]{"Card number exists in Bin of Visa","Card number exists in Bin of Visa"});
		ERROR_INFO_MAP_EN.put("R0003",new String[]{"Card number exists in Bin of Master","Card number exists in Bin of Master"});
		//黑名单
		ERROR_INFO_MAP_EN.put("R0004",new String[]{"Card number exists in blacklist","Card number exists in blacklist"});
		ERROR_INFO_MAP_EN.put("R0005",new String[]{"E-mail address exists in blacklist","E-mail address exists in blacklist"});
		ERROR_INFO_MAP_EN.put("R0006",new String[]{"IP address exists in blacklist","IP address exists in blacklist"});
		ERROR_INFO_MAP_EN.put("R0007",new String[]{"Other elements blacklist","Other elements blacklist"});
		//MaxMind
		ERROR_INFO_MAP_EN.put("R0008",new String[]{"Do not pass maxmind verification","Do not pass maxmind verification"});
		ERROR_INFO_MAP_EN.put("R0018",new String[]{"Do not pass Issuing Bank limitation","Do not pass Issuing Bank limitation"});
		
		//金额限定
		ERROR_INFO_MAP_EN.put("R0009",new String[]{"Do not set up the limitation of amout","Do not set up the limitation of amout"});
		ERROR_INFO_MAP_EN.put("R0010",new String[]{"Do not pass the sigal limits","Do not pass the sigal limits"});
		ERROR_INFO_MAP_EN.put("R0011",new String[]{"Do not pass the day limits","Do not pass the day limits"});
		ERROR_INFO_MAP_EN.put("R0012",new String[]{"Do not pass the week limits","Do not pass the week limits"});
		ERROR_INFO_MAP_EN.put("R0013",new String[]{"Do not pass the month limits","Do not pass the month limits"});
		//IP限定
		ERROR_INFO_MAP_EN.put("R0014",new String[]{"Do not pass the IP country whitelist limitation","Do not pass the IP country whitelist limitation"});
		ERROR_INFO_MAP_EN.put("R0015",new String[]{"Do not pass the IP country blacklist limitation","Do not pass the IP country blacklist limitation"});
		ERROR_INFO_MAP_EN.put("R0016",new String[]{"Do not pass the IP blacklist limitation","Do not pass the IP blacklist limitation"});
		//支付次数限定
		ERROR_INFO_MAP_EN.put("R0017",new String[]{"Do not pass the number of times that payment are made limitation","Do not pass the number of times that payment are made limitation"});
		
		//通道异常码
		ERROR_INFO_MAP_EN.put("C0001",new String[]{"Merchant gateway No. unbinds the channel","Merchant gateway No. unbinds the channel"});
		ERROR_INFO_MAP_EN.put("C0002",new String[]{"Merchant gateway No. do not set up deduction rate","Merchant gateway No. do not set up deduction rate"});
		ERROR_INFO_MAP_EN.put("C0003",new String[]{"Channel deduction rate of merchant gateway No. is incorrect","Channel deduction rate of merchant gateway No. is incorrect"});
		ERROR_INFO_MAP_EN.put("C0004",new String[]{"Channel deduction rate of merchant gateway No. is incorrect","Channel deduction rate of merchant gateway No. is incorrect"});
		ERROR_INFO_MAP_EN.put("C0005",new String[]{"The exchange rate of currency does not set up","The exchange rate of currency does not set up"});
		ERROR_INFO_MAP_EN.put("C0006",new String[]{"Obtaining currency failed","Obtaining currency failed"});
		ERROR_INFO_MAP_EN.put("C0007",new String[]{"Merchant does not bind payment domain name","Merchant does not bind payment domain name"});
		ERROR_INFO_MAP_EN.put("C0008",new String[]{"Merchant gateway No. do not bind channel {0}","Merchant gateway No. do not bind channel {0}"});
		ERROR_INFO_MAP_EN.put("C0009",new String[]{"Merchant gateway No.do not set up deduction rate","Merchant gateway No.do not set up deduction rate"});
		
		//系统异常码
		ERROR_INFO_MAP_EN.put("S0001",new String[]{"Fail to save to anomalous transation list","Fail to save to anomalous transation list"});
		ERROR_INFO_MAP_EN.put("S0002",new String[]{"Fail to save to the information list of cardholder","Fail to save to the information list of cardholder"});
		ERROR_INFO_MAP_EN.put("S0003",new String[]{"Fail to save to the informal transation list","Fail to save to the informal transation list"});
		ERROR_INFO_MAP_EN.put("S0004",new String[]{"Fail to save to the additional transation list","Fail to save to the additional transation list"});
		ERROR_INFO_MAP_EN.put("S0005",new String[]{"Channel information the system obtained is anomalous","Channel information the system obtained is anomalous"});
		ERROR_INFO_MAP_EN.put("S0006",new String[]{"Fail to save transation information","Fail to save transation information"});
		ERROR_INFO_MAP_EN.put("S0007",new String[]{"Fail to update testing list","Fail to update testing list"});
		ERROR_INFO_MAP_EN.put("S0008",new String[]{"Fail to delete anomalous record","Fail to delete anomalous record"});
		ERROR_INFO_MAP_EN.put("S0009",new String[]{"Fail to save transation record","Fail to save transation record"});
		ERROR_INFO_MAP_EN.put("S0010",new String[]{"Fail to obtain domain name the merchant bound","Fail to obtain domain name the merchant bound"});
		ERROR_INFO_MAP_EN.put("S0011",new String[]{"Fail to save the information list of cardholder","Fail to save the information list of cardholder"});
		ERROR_INFO_MAP_EN.put("S0012",new String[]{"Fail to obtain the information of bank channel","Fail to obtain the information of bank channel"});
		ERROR_INFO_MAP_EN.put("S0013",new String[]{"System Exception","System Exception"});
		ERROR_INFO_MAP_EN.put("S0014",new String[]{"Channel does not bind e-mail domain name","Channel does not bind e-mail domain name"});
		
		//未知异常码
		ERROR_INFO_MAP_EN.put("E0001",new String[]{"The operation has timed out","The operation has timed out"});
		ERROR_INFO_MAP_EN.put("E0002",new String[]{"The operation has timed out","The operation has timed out"});
		ERROR_INFO_MAP_EN.put("E0003",new String[]{"Sending failed","Sending failed"});
		ERROR_INFO_MAP_EN.put("E0004",new String[]{"Invoking failed","Invoking failed"});
		ERROR_INFO_MAP_EN.put("E0005",new String[]{"The channle code that the bank returned does not exist","The channle code that the bank returned does not exist"});
		ERROR_INFO_MAP_EN.put("E0006",new String[]{"Receiving failed","Receiving failed"});
		
		//返回商户 
		ERROR_INFO_MAP_EN.put("T0001", new String[]{"Transation successed, we do not charge any fees for testing transation","Transation successed, we do not charge any fees for testing transation"});
		ERROR_INFO_MAP_EN.put("T0002", new String[]{"Pending","Pending"});
		ERROR_INFO_MAP_EN.put("T0003", new String[]{"Successed,two party does not connect to the bank","Successed,two party does not connect to the bank"});
		
		ERROR_INFO_MAP_EN.put("KM004", new String[]{"KM004","KM004"});
		ERROR_INFO_MAP_EN.put("KM005", new String[]{"KM005","KM005"});
	}
	
	/**
	 *
	 * @author  
	 * @Title getErrorInfo
	 * @Time: 2011-7-8下午12:34:42
	 * @Description: 根据错误代码,返回returnBean 
	 * @return: ReturnBean 
	 * @throws: 
	 * @return
	 */
	public static ReturnBean getErrorInfo(String code){
		
		String[] infos = ERROR_INFO_MAP_EN.get(code);
		//默认
		String defaultCode = "E9999";
		String defaultInfoIn = "Unknown Exception";
		String defaultInfoOut = "Unknown Exception";
		
		ReturnBean returnBean = new ReturnBean();
		returnBean.setReturnCode(infos==null || StringUtils.isBlank(code)?defaultCode:code);
		returnBean.setReturnInfoOut(infos==null || StringUtils.isBlank(infos[0])?defaultInfoIn:infos[0]);
		returnBean.setReturnInfoIn(infos==null || StringUtils.isBlank(infos[1])?defaultInfoOut:infos[1]);
		return returnBean;
	}

}
