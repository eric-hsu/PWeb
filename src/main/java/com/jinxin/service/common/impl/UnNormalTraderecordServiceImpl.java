package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.UnNormalProcess;
import com.jinxin.model.persistence.UnNormalTraderecord;
import com.jinxin.service.common.UnNormalTraderecordServiceI;

@Service("unNormalTraderecordService")
@Scope("prototype")
public class UnNormalTraderecordServiceImpl implements
		UnNormalTraderecordServiceI {
	private static final Logger logger = Logger.getLogger(UnNormalProcessServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	

	public UnNormalTraderecord getUnNormalTraderecord(String trNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("utrNo", trNo);
    	UnNormalTraderecord unNormalProcess = (UnNormalTraderecord) baseDao.get("from UnNormalTraderecord t where  t.utrNo = :utrNo ",params);
		return unNormalProcess;
	}


	public OperateResult saveUnNormalTraderecord(UnNormalTraderecord unNormalProcess) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		Long count =  (Long) baseDao.save(unNormalProcess);
		if(count<1){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}


	public OperateResult saveOrUpdateUnNormalTraderecord(
			UnNormalTraderecord unNormalProcess) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		baseDao.saveOrUpdate(unNormalProcess);
		return operateResult;
	}

}
