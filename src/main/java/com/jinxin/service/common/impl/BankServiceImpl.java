package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.Bank;
import com.jinxin.model.persistence.Channel;
import com.jinxin.service.common.BankServiceI;

@Service("bankService")
@Scope("prototype")
public class BankServiceImpl implements BankServiceI {
	private static final Logger logger = Logger.getLogger(BankServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public Bank getBank(String bankCode) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("bankCode",  bankCode);
    	Bank bank = (Bank) baseDao.get("from Bank t where  t.bankCode = :bankCode ",params);
		return bank;
	}

	@Override
	public OperateResult saveBank(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateBank(Bank bank) {
		// TODO Auto-generated method stub
		return null;
	}

}
