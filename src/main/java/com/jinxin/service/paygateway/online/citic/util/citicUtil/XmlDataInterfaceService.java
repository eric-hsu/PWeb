package com.jinxin.service.paygateway.online.citic.util.citicUtil;
import com.lsy.baselib.comPkg.ResultPackage;
import com.lsy.baselib.xml.XmlDocument;
import com.lsy.baselib.xml.element.XmlEBStream;
import com.lsy.baselib.xml.element.XmlField;
import com.lsy.baselib.xml.element.XmlList;
import com.lsy.baselib.xml.element.XmlRow;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

public class XmlDataInterfaceService {
	private HashMap xmlcontenthash = new HashMap();
	private HashMap datatoxmlhash = new HashMap();
	private ArrayList keylist = new ArrayList();
	public String resultXml;
	
	public void parseResultEBStream(String  xmlcontent)
	throws Exception {
	 if(xmlcontent.equals(""))
    	return;
	XmlEBStream ebResPkg = XmlDocument.parseXmlEBStream(xmlcontent.trim());
	List fldlist = ebResPkg.getAllField();
	if (fldlist.size() > 0) {
		for (int i = 0; i < fldlist.size(); i++) {
			this.processField((XmlField) fldlist.get(i));

		}

	}
	List xmllists = ebResPkg.getAllList();
	if (xmllists.size() > 0) {
		for (int j = 0; j < xmllists.size(); j++) {
			this.processList((XmlList) xmllists.get(j));

		}

	}



  }
   public HashMap getXmlContentHash(){
	   
	   return this.xmlcontenthash;
   }
   	
	
	public String getXmlFieldValue(String fldname) {
		return (String) xmlcontenthash.get(fldname);

	}
	public CommBean getXmlList(String listname) {
		CommBean bean = (CommBean) xmlcontenthash.get(listname);
		return bean;
	}
	private void processField(XmlField field) throws Exception {
		String key = field.getName();
		String value = field.getValue();
		xmlcontenthash.put(key, value);

	}

	private void processList(XmlList xmllist) throws Exception {
		String listname = xmllist.getListName();
		CommBean bean = null;
		String[] header = null;
		for (int i = 0; i < xmllist.size(); i++) {
			XmlRow row = (XmlRow) xmllist.getRow(i);
			List fieldlist = row.listField();
			if (i == 0) {// ��һ�в���Header
				header = new String[fieldlist.size()];
				for (int j = 0; j < fieldlist.size(); j++) {
					XmlField field = (XmlField) fieldlist.get(j);
					header[j] = field.getName();

				}
				bean = new CommBean(header);

			}
			//���������β���
			String[] beanrow = new String[fieldlist.size()];
			for (int j = 0; j < fieldlist.size(); j++) {
				XmlField field = (XmlField) fieldlist.get(j);
				beanrow[j] = field.getValue();

			}
			bean.appendRow(beanrow);

		}
		xmlcontenthash.put(listname, bean);

	}
	public void setXmlField(String name, String value){
		datatoxmlhash.put(name, value);
		keylist.add(name);
		
	}
	public void setXmlList(String beanname, CommBean bean){
		datatoxmlhash.put(beanname, bean);
		keylist.add(beanname);
		
	}
	public XmlEBStream formXml() throws Exception {

		XmlEBStream ebResPkg = XmlDocument.createXmlEBStream();
		for(int i=0;i<this.keylist.size();i++){
			String key = keylist.get(i).toString();
			//System.out.println(key);
			Object value = datatoxmlhash.get(key);
			if (value instanceof String) {
				ebResPkg.addField(key, value.toString());

			}
			if (value instanceof CommBean) {
				CommBean bean = (CommBean)value;
				this.addXMLListToEBStream(ebResPkg, new CommBean(bean.getData()), key);

			}

		}
		//System.out.println(ebResPkg.asXmlDoc());
		this.resultXml = ebResPkg.asXmlDoc();
		return ebResPkg;

	}
	
	private void addXMLListToEBStream(XmlEBStream ebpack, CommBean bean,
			String listname) throws Exception {
		String[][] data = bean.getData();
		String[] header = data[0];
		XmlList xmlList = ebpack.addList(listname);
		
		while (bean.next()) {
			XmlRow xmlRow = xmlList.addRow("row");
			for (int j = 0; j < header.length; j++) {
				xmlRow.addField(header[j], bean.getValue(header[j]));
                
			}
		}

	}

	
	

}
