package com.jinxin.service.common.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.MerTranElement;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.MerTranElementServiceI;


@Service("merTranElementService")
@Scope("prototype")
public class MerTranElementServiceImpl implements MerTranElementServiceI {
	private static final Logger logger = Logger.getLogger(MerTranElementServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public List<MerTranElement> getMerTranElement(String mrMerNo, String mrGwNo) {
		Map<String, Object> params = new HashMap<String, Object>();
    	params.put("mrMerNo", Long.valueOf(mrMerNo));
    	params.put("mrGwNo", Long.valueOf(mrGwNo));
    	List<MerTranElement> list = new ArrayList<MerTranElement>();
    	list = (List<MerTranElement>) baseDao.find("from MerTranElement t where  t.mrMerNo = :mrMerNo and t.mrGwNo = :mrGwNo",params);
		return list;
	}
	
	public OperateResult riskControl(List<MerTranElement> list,Traderecord traderecord,int position){
		OperateResult operateResult = new OperateResult();
		int level = 0;
		//Java反射机制实现监控
		for(MerTranElement Tran : list){
			//监控位置
			if(Tran.getTransactionElement().getTePosition() == position){
				try{
					Class<?> clas = Class.forName(Tran.getTransactionElement().getTeUrl());
					Object object = clas.newInstance();
					Method method = clas.getMethod(Tran.getTransactionElement().getTeMethodName(),Traderecord.class);
					operateResult = (OperateResult) method.invoke(object, traderecord);
					level = Integer.parseInt(operateResult.getMessage())+level;
				}catch(Exception ex){
					logger.error("未找到:"+Tran.getTransactionElement().getTeUrl()+" 下的"+Tran.getTransactionElement().getTeMethodName());
				}
			}
			//判断是否返回
			if(!operateResult.isSuccess()){
				return operateResult;
			}
		}
		operateResult.setMessage(level+"");
		operateResult.setSuccess(true);
		return operateResult;
	}

	@Override
	public OperateResult saveMerTranElement(MerTranElement agentMer) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OperateResult saveOrUpdateMerTranElement(MerTranElement agentMer) {
		// TODO Auto-generated method stub
		return null;
	}

}
