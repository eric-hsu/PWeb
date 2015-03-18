package com.jinxin.service.common.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.TraderecordServiceI;

@Service("traderecordService")
@Scope("prototype")
public class TraderecordServiceImpl implements TraderecordServiceI {
	private static final Logger logger = Logger.getLogger(TraderecordServiceImpl.class);
	@Autowired
	private BaseDaoI baseDao;
	@Override
	public Traderecord getTraderecord(String trNo) {
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("trNo", trNo);
    	Traderecord traderecord = (Traderecord) baseDao.get("from Traderecord t where  t.trNo = :trNo ",params0);
    	return traderecord;
	}
	
	public Traderecord getTraderecordSpdb(String trReference) {
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("trReference", trReference);
    	Traderecord traderecord = (Traderecord) baseDao.get("from Traderecord t where  t.trReference = :trReference ",params0);
    	return traderecord;
	}
	
	public Traderecord getTraderecordByMerOrderNo(String trMerNo,String trGwNo , String trMerOrderno){
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("trMerNo", Long.valueOf(trMerNo));
    	params0.put("trGwNo", Long.valueOf(trGwNo));
    	params0.put("trMerOrderno", trMerOrderno);
    	Traderecord traderecord = (Traderecord) baseDao.get("from Traderecord t where  t.trMerNo = :trMerNo and  t.trGwNo = :trGwNo  and  t.trMerOrderno = :trMerOrderno ",params0);
    	return traderecord;
	}
	
	public List<Traderecord> getTraderecordByMerTradeDate(String trMerNo,String trGwNo , String tradeDate){   
		Date queryStartDate = DateTimeUtils.parseDate(tradeDate, "yyyy-MM-dd");
		
		Calendar cal = Calendar.getInstance(); //得到日历
		cal.setTime(queryStartDate);//把当前时间赋给日历
		cal.add(Calendar.DATE, 1);  //设置为前一天
	    Date queryEndDate = cal.getTime();   //得到前一天的时间
		
		Map<String, Object> params0 = new HashMap<String, Object>();
    	params0.put("trMerNo", Long.valueOf(trMerNo));
    	params0.put("trGwNo", Long.valueOf(trGwNo));
    	params0.put("queryStartDate", queryStartDate);
    	params0.put("queryEndDate", queryEndDate);
    	
		List<Traderecord> list = (List<Traderecord>) baseDao.find("from Traderecord t where  t.trMerNo = :trMerNo and  t.trGwNo = :trGwNo  and  t.trDatetime > :queryStartDate and t.trDatetime < :queryEndDate ",params0);
		
		return list;
	}

	@Override
	public OperateResult saveTraderecord(Traderecord traderecord) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		String count =  (String) baseDao.save(traderecord);
		if("".equals(count)){
			operateResult.setSuccess(false);
		}
		return operateResult;
	}

	@Override
	public OperateResult saveOrUpdateTraderecord(Traderecord traderecord) {
		OperateResult operateResult = new OperateResult();
		operateResult.setSuccess(true);
		try{
			baseDao.saveOrUpdate(traderecord);
		}catch(Exception e){
			logger.error(e.getMessage());
			operateResult.setSuccess(false);
			operateResult.setMessage(e.getMessage());
		}
		return operateResult;
	}

}
