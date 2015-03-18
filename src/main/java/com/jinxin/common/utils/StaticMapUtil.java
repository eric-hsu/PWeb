package com.jinxin.common.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.BankConfig;
import com.jinxin.model.persistence.Bank;
import com.jinxin.model.persistence.SysSysset;
import com.jinxin.model.persistence.TBank;
import com.jinxin.model.persistence.TBankTag;
import com.jinxin.service.common.MerChannelServiceI;
import com.jinxin.service.common.SysSyssetServiceI;

/**
 * @className:StaticMapUtil.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-26 下午11:24:27
 */

public class StaticMapUtil {
	  // 证件 类型/名称 列表(有序)
    public static Map<String, String> certificateTypeLinkedMap = new LinkedHashMap<String, String>();
    // 异常记录编码
    public static Map<String, String> exceptionMap = new LinkedHashMap<String, String>();
    // 银行编码（CDM）和名称转换
    public static Map<String, String> bankCdmCodeName = new HashMap<String, String>();
    // 在线支付actionMap
    public static Map<String, String> onlineControllerMap = new HashMap<String, String>();
    
    // 离线信用卡支付serviceMap
    public static Map<String, String> offlineCreditcardServiceMap = new HashMap<String, String>();
    
    // 离线信用卡支付serviceMap
    public static Map<String, String> offlinedebitcardServiceMap = new HashMap<String, String>();
    
    // 客户配置
    public static Map<String, String> customerMap = new HashMap<String, String>();
    
    // 支付中心咔种配置
    public static List<TBankTag> bankTagConfigMap =new ArrayList<TBankTag>();
    
    // 参数配置map
    public static  Map<String, String> paramMap = new HashMap<String, String>();
    
   
    // 配置在线支付controller跳转
    public static Map<String, String> getOnlineControllerMap() {
        if(onlineControllerMap.size()==0){
        	onlineControllerMap.put("opmabc", "abcController");
        	onlineControllerMap.put("opmcib", "cibController");
        	onlineControllerMap.put("opmicbc", "icbcController");
        	onlineControllerMap.put("opmhxb", "hxbController");
        	onlineControllerMap.put("opmcgb", "cgbController");
        	onlineControllerMap.put("opmspdb", "spdbController");
        }
        return onlineControllerMap;
    }
    
    // 配置信用卡离线支付servicemap
    public static Map<String, String> getOfflineCreditcardServiceMap() {
        if(offlineCreditcardServiceMap.size()==0){
        	//offlineCreditcardServiceMap.put("opmabc", "abcCreditcardService");
        	//offlineCreditcardServiceMap.put("opmpost", "postCreditcardService");
        }
        return offlineCreditcardServiceMap;
    }
    
 // 配置借记卡离线支付servicemap
    public static Map<String, String> getOfflineDebitcardServiceMap() {
        if(offlinedebitcardServiceMap.size()==0){
        	//offlinedebitcardServiceMap.put("opmabc", "abcDebitcardService");
        	//offlinedebitcardServiceMap.put("opmpost", "postDebitcardService");
        }
        return offlinedebitcardServiceMap;
    }
    
    // 配置在线支付controller跳转
    public static Map<String, String> getCustomerMap() {
        if(customerMap.size()==0){
        	customerMap.put("1000", "金信易贷");
        	customerMap.put("1001", "其他商户");
        }
        return customerMap;
    }

	public static void bankConfig(List<BankConfig> list,Model model){
		List<BankConfig> personalPaylist = new ArrayList<BankConfig>();
		List<BankConfig> quickPayCreditcardlist = new ArrayList<BankConfig>();
		List<BankConfig> quickPayDebitcardlist = new ArrayList<BankConfig>();
		List<BankConfig> enterprisePaylist = new ArrayList<BankConfig>();

		for(int i=0;i<list.size();i++){
			BankConfig bankConfig = list.get(i);

			if("0".equals(bankConfig.getChaType())){
				personalPaylist.add(bankConfig);
			}else if("1".equals(bankConfig.getChaType())){
				String cardSupportTypes = bankConfig.getCardSupportType();
				if(cardSupportTypes.contains("0")){
					quickPayDebitcardlist.add(bankConfig);
				}
				if(cardSupportTypes.contains("1")){
					quickPayCreditcardlist.add(bankConfig);
				}
			}else if("2".equals(bankConfig.getChaType())){
				enterprisePaylist.add(bankConfig);
			}
		}
		
    	model.addAttribute("personalPaylist", personalPaylist);
    	model.addAttribute("quickPayCreditcardlist", quickPayCreditcardlist);
    	model.addAttribute("quickPayDebitcardlist", quickPayDebitcardlist);
    	model.addAttribute("enterprisePaylist", enterprisePaylist);
	}
}
