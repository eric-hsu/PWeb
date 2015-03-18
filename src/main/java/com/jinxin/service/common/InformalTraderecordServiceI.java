package com.jinxin.service.common;

import java.util.List;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.InformalTraderecord;

public interface InformalTraderecordServiceI {
	public InformalTraderecord getInformalTraderecord(String trNo);
	
	public InformalTraderecord getformalTraderecordByMerOrderNo(String trMerNo,String trGwNo , String trMerOrderno);
	
	public List<InformalTraderecord> getformalTraderecordByMerTradeDate(String trMerNo,String trGwNo , String tradeDate);
	
	public OperateResult saveInformalTraderecord(InformalTraderecord traderecord);
	
	
	public OperateResult saveOrUpdateInformalTraderecord(InformalTraderecord traderecord);
}
