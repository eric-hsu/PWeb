package com.jinxin.service.common;

import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.persistence.Bank;

public interface BankServiceI {
	public Bank getBank(String bankCode);
	
	
	public OperateResult saveBank(Bank bank);
	
	
	public OperateResult saveOrUpdateBank(Bank bank);
}
