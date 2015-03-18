package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.io.File;
import java.util.Calendar;

public class Test1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File file = new File("F:\\some.txt");
		MyUtil.sysFormat(file);
		
//		System.out.println(getToday());

	}
	static public String getToday() {
        Calendar calendar = Calendar.getInstance();
        int tYear = calendar.get(Calendar.YEAR);
        int tMonth = calendar.get(Calendar.MONTH) + 1;
        int tDate = calendar.get(Calendar.DATE);
        return String.valueOf(tYear * 10000 + tMonth * 100 + tDate);
    }

}
