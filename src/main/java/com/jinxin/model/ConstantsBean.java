package com.jinxin.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 
 * <p>Title: </p>
 * <p>Description: 公共常量定义类
 * 此类为常量定义类 修改请谨慎
 * </p>
 * <p>Copyright: jinxin (c) 2013 版权</p>
 * <p>Company: </p>
 * @author 
 * @version V1.0 
 * @date 2013-6-28下午03:52:10
 */
public class ConstantsBean implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	//保存returnBean的session
	public static final String SESSION_RETURN_MERCHANT = "returnMerchant";
	
	//保存paramBean的session
	public static final String SESSION_PARAM = "paramBean";

	//正常成功返回代码
	public static final String SUCCESS_CODE = "0000";
	
	
	//查询成功
	public static final String QUERY_SUCCESS_CODE = "S";
	
	//查询失败
	public static final String QUERY_FAIL_CODE = "F";
	
	//查询成功
	public static final String REFUND_SUCCESS_CODE = "S";
	
	//退款失败
	public static final String REFUND_FAIL_CODE = "F";
	
	//字符编码
	public static final String REQ_CHARACTER_ENCODING = "UTF-8";
	
	//2方
	public static final int PARTY_TWO = 2;
	
	//2.5方
	public static final int PARTY_TWO_FIVE = 1;

	//3方
	public static final int PARTY_THREE = 3;
	
	//正式的
	public static final String FORMAL_INTERFACE = "formal";
	
	//测试的
	public static final String TEST_INTERFACE = "test";
	
	//商户号 网关接入号删除状态
	public static final int STATUS_DEL = -2;
	
	//商户号 网关接入号停用状态
	public static final int STATUS_STOP = -1;
	
	//商户号 网关接入号未激活状态
	public static final int STATUS_INACTIVE = 0;
	
	//商户号 网关接入号正常状态
	public static final int STATUS_FORMAL = 1;
	
	//商户号 网关接入号测试状态
	public static final int STATUS_TEST = 2;
	
	//交易状态:待确认
	public static final int TRADE_STATUS_CONFIRM = -2;
	
	//交易状态:待处理
	public static final int TRADE_STATUS_PROCESS = -1;
	
	//交易状态:失败
	public static final int TRADE_STATUS_FAIL = 0;
	
	//交易状态:成功
	public static final int TRADE_STATUS_SUCCESS = 1;
	
	//交易状态:支付中
	public static final int TRADE_STATUS_PAY_ING = 2;
	
	//交易状态:已退款 
	public static final int TRADE_STATUS_REFUND = 3;
	
	//交易状态:退款中
	public static final int TRADE_STATUS_REFUND_ING = 4;
	
	//交易状态: 退款失败  
	public static final int TRADE_STATUS_REFUND_FAIL = 5;
	
	//商户退款状态: 2:审核成功已处理 
	public static final int REFUND_STATUS_UN_SUCCESS = 2;
	
	//商户退款状态: 1审核成功未处理
	public static final int REFUND_STATUS_UN_ING = 1;
	
	//商户退款状态:-1:失败; 
	public static final int REFUND_STATUS_UN_FAIL = -1;
	
	//流水订单号规则
	public static final String TRADE_NO_RULE = "yyyyMMddHHmmssSSS";
	
	//信用卡号有效期（单位:年）
	public static final int CREDIT_YEAR = 10;
	
	//测试状态生成的流水订单号前缀
	public static final String TEST_TRADENO_PREFIX = "T";
	
	//生成的流水订单号随机数位数
	public static final int GET_TRADENO_LENGTH = 5;
	
	//跳转错误页面
	public static final String RETURN_ERROR_PATH = "jsp/tradeManage/info.jsp";
	
	//支付结果前端通知post页面配置
	public static final String RETURN_FRONT_NOTIFY_PATH = "jsp/tradeManage/sendtomer.jsp";
	
	//跳转发送银行路径
	public static final String SEND_BANK_PATH = "SendBankInterface";
	
	//跳转发送银行方法
	public static final String SEND_BANK_METHOD = "sendBank";
	
	//接收发送银行路径
	public static final String RECEIVE_BANK_PATH = "ReceiveBankInterface";
	
	//接收发送银行方法
	public static final String RECEIVE_BANK_METHOD = "receiveBank";
	
	//接收发送银行ID的名称
	public static final String SEND_CHANNEL_CODE = "LOT_CHANNEL_CODE";
	
	//是
	public static final int YES_STATUS = 1;
	
	//否
	public static final int NO_STATUS = 0;
	
	//Visa卡
	public static final int CARD_VISA = 1;
	
	//master卡
	public static final int CARD_MASTER = 2;
	
	//存储卡种与对应首字母的区分 
	public static final Map<Integer, String> CARD_TYPE_MAP = new HashMap<Integer, String>();
	
	//存储币种
	public static final List<String> CURRENCY_INFO = new ArrayList<String>();
	
	//风控位置1
	public static final int RISK_POSITION_1 = 1;
	
	//风控位置2
	public static final int RISK_POSITION_2 = 2;
	
	//交易监控位置1
	public static final int TRAN_POSITION_1 = 1;
		
	//交易监控位置2
	//public static final int TRAN_POSITION_2 = 2;
		
	//maxmind默认分数
	public static final double MAXMIND_DEFAULT_SCORE = 5.0;
	
	//RSA加密的Key的名称
	public static final String RSA_PUBLIC_KEY_NAME = "RSAPublicKey.dat";
	
	//时区
	public static final String TIME_ZONE = "GMT+8";

	// Yespayment交易类型：正式交易
	public static final int YESPAYMENT_TRANSACTION_TYPE = 20;

	// Yespayment交易状态：00（成功）
	public static final String YESPAYMENT_SUCCESS_CODE = "00";
	
	// 支付结果
	public static final String NOTIFY_TYPE_PAY = "1";
	
	// 查询结果
	public static final String NOTIFY_TYPE_QUERY = "2";
	
	// 退款结果
	public static final String NOTIFY_TYPE_REFUND = "3";
	
	// 查询结果
	public static final String NOTIFY_TYPE_BATCHQUERY = "4";
	

}
