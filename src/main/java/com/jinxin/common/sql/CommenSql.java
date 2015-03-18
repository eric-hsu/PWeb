package com.jinxin.common.sql;

public class CommenSql {
	/** 查询网关接入号通道绑定列表 **/
	public static final String FIND_MER_CHANNEL_INFO = "select m.mcMerNo,m.mcGwNo,c.bank.bankName,c.bank.bankCode ,c.chaCode,c.chaName,c.chaStauts,c.chaEffectDate,c.chaCardSupportType,c.chaType from Channel c,MerChannel m where c.chaCode = m.mcChaCode and m.mcStatus=1";

}
