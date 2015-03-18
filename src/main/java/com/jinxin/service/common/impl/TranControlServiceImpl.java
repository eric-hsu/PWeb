package com.jinxin.service.common.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.jinxin.common.utils.SpringContextUtil;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.persistence.MerTranElement;
import com.jinxin.model.persistence.Rate;
import com.jinxin.model.persistence.Traderecord;
import com.jinxin.service.common.MerTranElementServiceI;
import com.jinxin.service.common.RateServiceI;
import com.jinxin.service.common.TranControlServiceI;


/**
 * @className:TranControlServiceImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-8-10 下午8:43:12
 */
@Service("tranControlService")
@Scope("prototype")
public class TranControlServiceImpl implements TranControlServiceI {
	private static final Logger logger = Logger.getLogger(TranControlServiceImpl.class);
	
	/**
	 * 
	 * @author  
	 * @Title maxMoneyCheck
	 * @Time: 2011-9-14上午11:17:55
	 * @Description: KM005:交易单笔交易金额大于5000元，且购买的商品种类只为1种
	 * @return: OperateResult 
	 * @throws: 
	 * @param Traderecord
	 * @return
	 */
	public OperateResult maxMoneyCheck(Traderecord traderecord) {
		OperateResult operateResult  = new OperateResult();
		operateResult.setSuccess(true);
		logger.info("KM005:交易单笔交易金额大于5000元，且购买的商品种类只为1种");
		logger.info("支付金额为:"+traderecord.getTrAmount()+" "+traderecord.getTrCurrency());
		//获取设置的支付金额
		MerTranElementServiceI merTranElementService = (MerTranElementServiceI) SpringContextUtil.getBean("merTranElementService");
		RateServiceI rateService = (RateServiceI) SpringContextUtil.getBean("rateService");

		List<MerTranElement> list = merTranElementService.getMerTranElement(String.valueOf(traderecord.getTrMerNo()), String.valueOf(traderecord.getTrGwNo()));
		if(list.size()>0){
			//获取所有的汇率转换
			List<Rate> listrate = rateService.getAllRate("1");
			MerTranElement merTranElement = list.get(0);
			//单笔限额
			//判断币种是否相同,不相同则需要转换汇率   OrderCurrency商户订单币种   LimitCurrency限定币种
			if(traderecord.getTrCurrency().equalsIgnoreCase("CNY")){
				//判断金额   OrderAmount商户订单金额
				if(Double.valueOf(merTranElement.getTransactionElement().getTeElementValue()) < Double.valueOf(String.valueOf(traderecord.getTrAmount()))){
					logger.info("符合监控条件——KM005:交易单笔交易金额大于5000元，且购买的商品种类只为1种");
					operateResult.setMessage("1");
				}else{
					operateResult.setMessage("0");
				}
			}else{
				//
				Map<String, String> currency = new HashMap<String, String>();
				currency.put(traderecord.getTrCurrency(), "CNY");
				Map<Map<String, String>, Double> rateMap = new HashMap<Map<String, String>, Double>();
				for(Rate rate:listrate){
					 Map<String, String> currencyMap = new HashMap<String, String>();
					 currencyMap.put(rate.getRateOriginalCurrency(), rate.getRateTargetCurrency());
					 rateMap.put(currencyMap, Double.valueOf(String.valueOf(rate.getRateValue())));
				}
				
				//判断汇率是否设置				
				if( rateMap.get(currency) == null){
					//汇率未设置,不阻止
					logger.error(traderecord.getTrCurrency()+" 转换为:"+ "CNY"+"的汇率未设置");
				}else{
					//判断金额
					if(Double.valueOf(merTranElement.getTransactionElement().getTeElementValue()) < Double.valueOf(String.valueOf(traderecord.getTrAmount())) * rateMap.get(currency)){
						logger.info("符合监控条件——KM005:交易单笔交易金额大于5000元，且购买的商品种类只为1种");
						operateResult.setMessage("1");	
					}else{
						operateResult.setMessage("0");
					}
				}
			}
		}
		return operateResult;
	}

}
