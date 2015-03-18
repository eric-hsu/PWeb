package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;


public class SocketClient {

//	private static final Logger log = Logger.getLogger(SocketClient.class);
	
	public static int SEVBUFFERSIZE = 1024;
	
	public static byte[] sendInfo(byte[] reqBytes,int timeOut,String address,int port) throws IOException{
		Socket socket = null;
		InputStream dis = null;//DataInputStream
		OutputStream dos = null;//DataOutputStream
		try {
			socket = new Socket(address, port);
			dis = socket.getInputStream();//new DataInputStream(
			dos = socket.getOutputStream();//new DataOutputStream(
			
			socket.setSoTimeout(timeOut);
			
			dos.write(reqBytes, 0, reqBytes.length);
            dos.flush();
//            socket.shutdownOutput();           
            int totallen=0;
            int count;
           
            List<byte[]> l = new ArrayList<byte[]>();
            
	        while(true){
	            byte[] tmp = new byte[SEVBUFFERSIZE];
	            try{
	            	count = dis.read(tmp);
	            }catch(Throwable e){
		            e.printStackTrace();
		            break;
	            }
	            if(count<=0){
	            	break;
	            }
	            totallen += count;
	            byte[] tmp2 = new byte[count];
	
	            System.arraycopy(tmp,0,tmp2,0,count);
	            l.add(tmp2);
	        }
            byte[] arecv = new byte[totallen];
            int pos = 0;
            for(byte[] tmp:l){
	            System.arraycopy(tmp,0,arecv,pos,tmp.length);
	            pos += tmp.length;
            }
            return arecv;
            
            
		} catch (Throwable e) {
//			log.error("error",e);
			throw new IOException(e);
		} finally{
			try{
				if(dos != null){
				   dos.close();				   
				}
			}catch(Throwable e){}
			dos = null;
			 
			//	 
			try{
				if(dis != null){
				   dis.close();			   
				}
			}catch(Throwable e){}
		    dis = null;				 
			// 
			try{
				if(socket != null){
				  socket.close();				   
				}
			 }catch(Throwable e){}
			 socket = null;		  
		}
	}
}
