package com.jinxin.service.common;

import java.util.List;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.SysSysset;

public interface SysSyssetServiceI {
	
	public SysSysset getSysSysset(String ssParaName);
	
	public List<SysSysset> getAllSysSysset();
	
	public OperateResult saveSysSysset(SysSysset sysSysset);
	
	public OperateResult saveOrUpdateSysSysset(SysSysset sysSysset);
}
