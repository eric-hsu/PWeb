package com.jinxin.service.paygateway.online.citic.util.citicUtil;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsy.baselib.xml.XmlDocument;
import com.lsy.baselib.xml.element.XmlEBStream;
import com.lsy.baselib.xml.element.XmlField;


public class RetXmlHandle {
	private static HashMap xmlcontenthash = new HashMap();
	 private static long seed = 0;
	 /**
	     * ���뷽ʽ
	     */
	    private final static String charSet = "ISO8859_1";
public static void processRetXml(String returnMsg){
	XmlEBStream ebResPkg = XmlDocument.parseXmlEBStream(returnMsg.trim());
	List fldlist = ebResPkg.getAllField();
	if (fldlist.size() > 0) {
		for (int i = 0; i < fldlist.size(); i++) {
			try {
				processField((XmlField) fldlist.get(i));
			} catch (Exception e) {
				
				e.printStackTrace();
			}

		}

	}
}
	    private static void processField(XmlField field) throws Exception {
			String key = field.getName();
			String value = field.getValue();
			xmlcontenthash.put(key, value);

		}
	    public static String generateRandomNumber(int length) {
	        byte[] randomSerial = new byte[length];

	        String charactors = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
	        int charSetLength = charactors.length();

	        seed = seed ^ System.currentTimeMillis();
	        java.util.Random randomNumber = new java.util.Random(seed);

	        for (int i = 0; i < length; i++) {
	            randomNumber.setSeed(seed);
	            int j = (int) (randomNumber.nextFloat() * charSetLength);
	            randomSerial[i] = (byte) charactors.charAt(j);
	            seed = seed ^ randomNumber.nextInt();
	        }
	        return convertSting(randomSerial);
	    }
	    /**
	     * �ֽ�ת�����ַ�
	     * @param bs
	     * @return
	     */
	    private final static String convertSting(byte[] bs) {
	    	
	    	
	    	

	        try {
	            return (new String(bs, charSet));
	        } catch (UnsupportedEncodingException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return null;
	    }
	  public static HashMap getXmlcontenthash() {
		return xmlcontenthash;
	}




	public static void setXmlcontenthash(HashMap xmlcontenthash) {
		RetXmlHandle.xmlcontenthash = xmlcontenthash;
	}


	/**
	 * @param args
	 */
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"GBK\"?><stream>");
        sb.append("<MERID>302451079940001</MERID>");
        sb.append("<ORDERNO>20140829105832195482</ORDERNO>");//Ҫ��ѯ�Ķ�����
        sb.append("<MERJNLNO>20140829105832195482</MERJNLNO>");
        sb.append("<TRANTYPE>00</TRANTYPE>");
        sb.append("<REQRESERVED>test</REQRESERVED>");
        sb.append("</stream>");

       System.out.println(sb.toString());
    
	}

}
