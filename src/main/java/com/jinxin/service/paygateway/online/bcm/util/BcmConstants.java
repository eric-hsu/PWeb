package com.jinxin.service.paygateway.online.bcm.util;



/**
 * @className:Constants.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午3:20:47
 */

public class BcmConstants {

	 public static String INIT_FILE_PATH = "F:/bank/bcm/bocommjava/B2CMerchantSocket.xml";
	 
	  /**
	     * 交易类别: 0 B2C
	     */
	 public static String SEND_TRAN_TYPE = "0";
	 
	 /**
	     * 消息版本号: 固定为 1.0.0.0
	     */
	 public static String SEND_INTERFACE_VERSION = "1.0.0.0";
	 
	 /**
	     * 订单币种: 人民币 CNY
	     */
	    public static String SEND_CUR_TYPE = "CNY";

	    /**
	     * 通知方式: 0 不通知
	     */
	    public static String SEND_NOTIFY_TYPE_NOT = "0";

	    /**
	     * 通知方式: 1 通知
	     */
	    public static String SEND_NOTIFY_TYPE_YES = "1";

	    /**
	     * 通知方式: 2 转页面
	     */
	    public static String SEND_NOTIFY_TYPE_HTML = "2";

	    /**
	     * 渠道编号：固定填0 （html 渠道）
	     */
	    public static String SEND_NET_TYPE = "0";

	    /**
	     * 网关返回结果标记 1:成功
	     */
	    public static String TRAN_RESULT = "1";

	    /**
	     * 网关查询是否成功标记 0:成功
	     */
	    public static String QUERY_RESULT = "0";

	    /**
	     * 订单状态
	     */
	    /**
	     * 未支付
	     */
	    public static String NO_PAY = "0";

	    /**
	     * 已支付
	     */
	    public static String PAY_SUCCESS = "1";

	    /**
	     * 已撤销
	     */
	    public static String CANCEL = "2";

	    /**
	     * 部分退货
	     */
	    public static String REFUND_PART = "3";

	    /**
	     * 退货处理中
	     */
	    public static String REFUNDING = "4";

	    /**
	     * 全额退货
	     */
	    public static String REFUND_ALL = "5";
}
