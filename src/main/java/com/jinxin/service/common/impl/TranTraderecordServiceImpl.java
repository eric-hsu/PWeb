package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.model.persistence.TranTraderecord;
import com.jinxin.service.common.TranTraderecordServiceI;
@Service("tranTraderecordService")
@Scope("prototype")
public class TranTraderecordServiceImpl implements TranTraderecordServiceI{

	private static final Logger logger = Logger.getLogger(TranTraderecordServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public TranTraderecord getTranTraderecord(String trNo) {
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("ttrNo", trNo);
    	TranTraderecord tranTraderecord = (TranTraderecord) baseDao.get("from TranTraderecord t where  t.ttrNo = :ttrNo ",params0);
    	return tranTraderecord;
	}

	@Override
	public OperateResult saveTranTraderecord(TranTraderecord tranTraderecord) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		String count =  (String) baseDao.save(tranTraderecord);
		if("".equals(count)){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}

	@Override
	public OperateResult saveOrUpdateTranTraderecord(
			TranTraderecord tranTraderecord) {
		// TODO Auto-generated method stub
		return null;
	}

}
