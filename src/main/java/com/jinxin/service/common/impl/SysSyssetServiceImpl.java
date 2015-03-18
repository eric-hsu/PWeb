package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.Rate;
import com.jinxin.model.persistence.SysSysset;
import com.jinxin.service.common.SysSyssetServiceI;
@Service("sysSyssetService")
@Scope("prototype")
public class SysSyssetServiceImpl implements SysSyssetServiceI {
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public SysSysset getSysSysset(String ssParaName) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("ssParaName", ssParaName);
    	SysSysset sysSysset = (SysSysset) baseDao.get("from SysSysset t where  t.ssParaName = :ssParaName ",params);
		return sysSysset;
	}

	@Override
	public List<SysSysset> getAllSysSysset() {
		List<SysSysset> list = (List<SysSysset>) baseDao.find("from SysSysset t ");
		return list;
	}

	@Override
	public OperateResult saveSysSysset(SysSysset sysSysset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateSysSysset(SysSysset sysSysset) {
		// TODO Auto-generated method stub
		return null;
	}

}
