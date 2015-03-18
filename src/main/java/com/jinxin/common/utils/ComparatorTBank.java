package com.jinxin.common.utils;

import java.util.Comparator;

import com.jinxin.model.persistence.TBank;

/**
 * @className:ComparatorTBank.java
 * @classDescription:
 * @author:erichu
 * @createTime:2014-6-27 下午11:53:03
 */

public class ComparatorTBank  implements Comparator{
	public int compare(Object arg0, Object arg1) {
		  TBank bank0=(TBank)arg0;
		  TBank bank1=(TBank)arg1;

		   //首先比较年龄，如果年龄相同，则比较名字

		  int flag=bank0.getId().compareTo(bank1.getId());
		  if(flag==0){
		   return bank0.getBankname().compareTo(bank1.getBankname());
		  }else{
		   return flag;
		  }  
	}
}
