package com.jinxin.service.common.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import com.jinxin.common.sql.CommenSql;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.dao.BaseDaoI;
import com.jinxin.model.BankConfig;
import com.jinxin.model.persistence.TBankTag;
import com.jinxin.service.common.BankConfigServiceI;

/**
 * @className:BankConfigImpl.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-27 下午4:33:48
 */
@Service("bankConfigService")
@Scope("prototype")
public class BankConfigServiceImpl implements BankConfigServiceI {
	@Autowired
	private BaseDaoI baseDao;
	
	@Override
	public List<BankConfig> findBankConfig(String merNo, String gwNo) {
		List<BankConfig>  list0 = new ArrayList<BankConfig>();
    	
		
    	StringBuffer infoSql = new StringBuffer(
				CommenSql.FIND_MER_CHANNEL_INFO);
    	if(!StringUtils.isBlank(merNo)){
    		infoSql.append(" and m.mcMerNo = "+ merNo+" ");
    	}
    	
    	if(!StringUtils.isBlank(gwNo)){
    		infoSql.append(" and m.mcGwNo = "+ gwNo+" ");
    	}
    	
		List list = baseDao.find(infoSql.toString());
		for(int i=0;i<list.size();i++){
			BankConfig bankConfig = new BankConfig();
			Object[] datas = (Object[]) list.get(i);
			bankConfig.setMcMerNo(datas[0].toString());
			bankConfig.setMcGwNo(datas[1].toString());
			bankConfig.setBankname(datas[2].toString());
			bankConfig.setBankcode(datas[3].toString());
			bankConfig.setChaCode(datas[4].toString());
			bankConfig.setChaName(datas[5].toString());
			String chaStatus = datas[6].toString();
			String chaEffectDate = String.valueOf(datas[7]);
			String cardSupportType = String.valueOf(datas[8]);
			String chaType = String.valueOf(datas[9]);
			bankConfig.setChaType(chaType);
			bankConfig.setCardSupportType(cardSupportType);
			
			
			boolean flag = true;
			
			if(!"null".equals(chaEffectDate)){
				flag = DateTimeUtils.parseDateTime(chaEffectDate).before(new Date());
			}
			
			if("1".equals(chaStatus) && flag){
				list0.add(bankConfig);
			}
		}
		return list0;
	}

}
