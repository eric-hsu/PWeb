package com.jinxin.common.test;
import java.io.FileOutputStream;  

import org.dom4j.Document;  
import org.dom4j.DocumentHelper;  
import org.dom4j.Element;  
import org.dom4j.io.OutputFormat;  
import org.dom4j.io.XMLWriter;  
public class XmlCreateTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	try{
		// TODO Auto-generated method stub
		 //1.第一种 创建文档及设置根元素节点的方式  
        
        //创建文档的根节点  
//      Document document = DocumentHelper.createDocument();  
//      //创建文档的 根元素节点  
//      Element root = DocumentHelper.createElement("Person");  
//        document.setRootElement(root);  
          
        //2.第二种 创建文档及设置根元素节点的方式  
        Element root = DocumentHelper.createElement("response");  
        Document document = DocumentHelper.createDocument(root);  
        //给根节点添加属性  
//        root.addAttribute("学校", "南大").addAttribute("位置", "江西");  
          
        //给根节点添加孩子节点  
        Element element1 = root.addElement("success");  
        element1.addElement("status").addText("s");  
        element1.addElement("message").addText("操作成功");  
        Element element2 = root.addElement("order");  
        for(int i=0;i<10;i++){

        	element2.addElement("row");
        	Element ele = (Element) element2.elements().get(i);
        	ele.addElement("amount").addText("100");
        	ele.addElement("orderNo").addText("12645456"); 
        
        
        }
        
        
        System.out.println(document.asXML().toString());
        
//        把生成的xml文档存放在硬盘上  true代表是否换行  
//        OutputFormat format = new OutputFormat("    ",true);  
//        format.setEncoding("GBK");//设置编码格式  
//        XMLWriter xmlWriter = new XMLWriter(new FileOutputStream("Person.xml"),format);  
//      
//        xmlWriter.write(document);  
//        xmlWriter.close();  
	}catch(Exception e){
		e.printStackTrace();
	}
	}
	

}
