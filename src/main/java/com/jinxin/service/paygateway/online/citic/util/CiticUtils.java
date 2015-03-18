package com.jinxin.service.paygateway.online.citic.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.commons.lang.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Node;
import com.jinxin.common.utils.DateTimeUtils;
import com.jinxin.common.valueobj.OperateResult;
import com.jinxin.model.BankReturnBean;
import com.jinxin.model.ConstantsBean;
import com.lsy.baselib.crypto.processor.ECCryptoProcessor;

public class CiticUtils {
	
	private static final String ENCODEING="GBK";
	public static String readFileToString(String fileName) throws Exception {

		FileInputStream in = null;
		byte[] bs = null;
		ByteArrayOutputStream out = null;
		String ret = "";
		try {
			in = new FileInputStream(fileName);
			out = new ByteArrayOutputStream(1024 * 1024);
			int i = 0;
			int size = 1024 * 10;
			bs = new byte[size];
			
				while ((i = in.read(bs, 0, size)) != -1) {
					out.write(bs, 0, i);
				}
				ret = new String(out.toByteArray(), ENCODEING);
		} catch (Exception e) {
			throw e;
		}finally {
			try {
				if (out != null) {
					out.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try{
				if (in != null) {
					in.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
				
			
		}
		return ret;
	
	}
}
