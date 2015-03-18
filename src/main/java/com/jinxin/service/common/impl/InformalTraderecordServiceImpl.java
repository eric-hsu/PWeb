package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.InformalTraderecord;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.InformalTraderecordServiceI;

@Service("informalTraderecordService")
@Scope("prototype")
public class InformalTraderecordServiceImpl implements InformalTraderecordServiceI {
	@Autowired
	private BaseDaoI baseDao;
	
	public InformalTraderecord getInformalTraderecord(String trNo) {
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("trNo", trNo);
    	InformalTraderecord traderecord = (InformalTraderecord) baseDao.get("from InformalTraderecord t where  t.ttrNo = :trNo ",params0);
    	return traderecord;
	}
	
	public InformalTraderecord getformalTraderecordByMerOrderNo(String trMerNo,String trGwNo , String trNo){
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("ttrMerNo", trMerNo);
    	params0.put("ttrGwNo", trGwNo);
    	params0.put("ttrNo", trNo);
    	InformalTraderecord traderecord = (InformalTraderecord) baseDao.get("from InformalTraderecord t where  t.ttrMerNo = :ttrMerNo and t.ttrGwNo = :ttrGwNo and t.ttrNo = :ttrNo ",params0);
    	return traderecord;
	}
	
	public List<InformalTraderecord> getformalTraderecordByMerTradeDate(String trMerNo,String trGwNo , String tradeDate){
		
		return null;		
	}

	public OperateResult saveInformalTraderecord(InformalTraderecord traderecord) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		Long count =  (Long) baseDao.save(traderecord);
		if(count<1){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}

	public OperateResult saveOrUpdateInformalTraderecord(
			InformalTraderecord traderecord) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		baseDao.saveOrUpdate(traderecord);
		
		return operateResult;
	}
}
