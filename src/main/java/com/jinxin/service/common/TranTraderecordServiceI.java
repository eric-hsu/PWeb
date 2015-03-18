package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.model.persistence.TranTraderecord;

public interface TranTraderecordServiceI {
	public TranTraderecord getTranTraderecord(String trNo);
	
	
	public OperateResult saveTranTraderecord(TranTraderecord tranTraderecord);
	
	
	public OperateResult saveOrUpdateTranTraderecord(TranTraderecord tranTraderecord);
}
