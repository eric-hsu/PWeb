package com.jinxin.common.test;

public class ArrayTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[][] data = {{"MCTJNLNO", "STT", "RSTCODE", "BSNNO", "PAYERACCNO", "PAYEEACCNO", "TRANAMT", "CRYCODE", "ORDERNO"}, {"20150206111604455612", "01", "AAAAAAA", "00", "7111010182600334546", "7111010182600250700", "1", "CNY", "541214"}};
	
		System.out.println(data[0].length);
		if(data.length!=2){
			
		}
		for(int i=0;i<data[0].length;i++){
			
			System.out.println(data[0][i]+"="+data[1][i]);
			
		}
	}

}
