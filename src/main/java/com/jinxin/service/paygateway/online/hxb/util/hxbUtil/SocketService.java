package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ResourceBundle;

public class SocketService {
	private  static String socketip;
	private  static String socketport;
	static{
		ResourceBundle rb = ResourceBundle.getBundle("Socket");
		socketip = rb.getString("ip");
		socketport = rb.getString("port");
	}
	
	public static String process(String xmlString) throws Exception{

//		Socket tSocket = null;
    	String tResponseMessage="";
    	NewSendReport nsr=new NewSendReport();
		byte[] b=xmlString.getBytes();
		tResponseMessage=nsr.sendAndReceive(b,socketip,socketport);
//		try {
//			 //1.建立客户端socket连接，指定服务器位置及端口
//            Socket socket =new Socket(socketip,Integer.parseInt(socketport));
//            //2.得到socket读写流
//            OutputStream os=socket.getOutputStream();
//            PrintWriter pw=new PrintWriter(os);
//            //输入流
//            InputStream is=socket.getInputStream();
//            BufferedReader br=new BufferedReader(new InputStreamReader(is));
//            //3.利用流按照一定的操作，对socket进行读写操作
////            StringBuffer info=new StringBuffer();
////            for (int i = 0; i <2000; i++) {
////				info.append("<node>TEST00001</node>"); 
////			}
////            pw.write(info.toString());
//            
//            os.write(b, 0,b.length);
//            pw.write(xmlString);
//            pw.flush();
//            socket.shutdownOutput();
//            //接收服务器的相应
//            String reply=null;
//            while(!((reply=br.readLine())==null)){
//                System.out.println("接收服务器的信息："+reply);
//                tResponseMessage = reply;
//            }
//            //4.关闭资源
//            br.close();
//            is.close();
//            pw.close();
//            os.close();
//            socket.close();
//
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}
//		try {
//			tSocket = new Socket(InetAddress.getByName(socketip), Integer.parseInt(socketport));
//			PrintWriter tOut = new PrintWriter(new BufferedWriter(
//					new OutputStreamWriter(tSocket.getOutputStream())));
//			tOut.write(xmlString);
//			tOut.flush();
//			
//			BufferedReader tIn = new BufferedReader(new InputStreamReader(
//					tSocket.getInputStream()));
//			String tLine = null;
//			while ((tLine = tIn.readLine()) != null) {
//				tResponseMessage += tLine;
//			}
//			tSocket.close();
//		} catch (NumberFormatException e1) {
//		    e1.printStackTrace();
//		} catch (UnknownHostException e1) {
//			e1.printStackTrace();
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		
		return tResponseMessage;
	}
	
	public static void main(String arg[]){
	//	SocketService.process("aaa");
		System.out.println("OK");
	}
	
}
