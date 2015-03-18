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
import com.jinxin.service.common.UnNormalProcessServiceI;

@Service("unNormalProcessService")
@Scope("prototype")
public class UnNormalProcessServiceImpl implements UnNormalProcessServiceI {
	private static final Logger logger = Logger.getLogger(UnNormalProcessServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public UnNormalProcess getUnNormalProcess(String trNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("trNo", trNo);
    	UnNormalProcess unNormalProcess = (UnNormalProcess) baseDao.get("from UnNormalProcess t where  t.traderecord.trNo = :trNo ",params);

		return unNormalProcess;
	}

	@Override
	public OperateResult saveUnNormalProcess(UnNormalProcess unNormalProcess) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		Long count =  (Long) baseDao.save(unNormalProcess);
		if(count<1){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}

	@Override
	public OperateResult saveOrUpdateUnNormalProcess(
			UnNormalProcess unNormalProcess) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		baseDao.saveOrUpdate(unNormalProcess);
		return operateResult;
	}

}
