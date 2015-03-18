package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.Traderecord;

public interface TraderecordServiceI {
	public Traderecord getTraderecord(String trNo);
	
	public Traderecord getTraderecordSpdb(String trNo);

	
	public Traderecord getTraderecordByMerOrderNo(String trMerNo,String trGwNo , String trMerOrderno);
	
	public List<Traderecord> getTraderecordByMerTradeDate(String trMerNo,String trGwNo , String tradeDate);
	
	public OperateResult saveTraderecord(Traderecord traderecord);
	
	
	public OperateResult saveOrUpdateTraderecord(Traderecord traderecord);
}
