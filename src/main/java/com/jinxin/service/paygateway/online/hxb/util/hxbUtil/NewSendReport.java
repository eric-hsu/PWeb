/*
 * 创建日期 2011-6-29
 *
 * 更改所生成文件模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
package com.jinxin.service.paygateway.online.hxb.util.hxbUtil;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * @author lenovo
 *
 * 更改所生成类型注释的模板为
 * 窗口 > 首选项 > Java > 代码生成 > 代码和注释
 */
public class NewSendReport {
	
	private String timeout="50000";
	private String tcpRecCount="50";
	private String tcpRecPSize="2048";



	public String sendAndReceive(byte send[],String ip,String port) throws Exception {
		OutputStream os = null;
		InputStream is = null;
		String res = "";
		
		int tcpMaxSize =Integer.parseInt(tcpRecCount) * Integer.parseInt(tcpRecPSize);
		int sendWholeLen = send.length;
		int actualLen = 0;
		byte recv[] = new byte[tcpMaxSize];
		byte tcpRcv[] = (byte[]) null;
		int sendNum = 1;
		for (int i = 0; i < sendNum; i++) {
			Socket socket = null;
			try {
				System.out.println("tcp发送ip:"+ip);
				System.out.println("发送端口port:"+port);
				socket = new Socket(ip, Integer.parseInt(port));
				socket.setSoTimeout(Integer.parseInt(timeout));
				os = socket.getOutputStream();
				os.write(send, 0, sendWholeLen);
				os.flush();
				socket.shutdownOutput();
				is = socket.getInputStream();
				is = new DataInputStream(socket.getInputStream());
				res = readData(is);
				socket.shutdownInput();
				System.out.println(res);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					if(null != os)os.close();
					if(null != is)is.close();
					if(null != socket)socket.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
				
			}
//			System.out.println("tcp接收长度:" + actualLen);
//			System.out.println("---后台返回报文new String(tcpRcv):-----" + new String(tcpRcv)); 
//			if (actualLen > 0)
//				break;
		}
		//String res=new String(tcpRcv);
		System.out.println("------后台返回报文res:------" + res);   
		return res;
	}
	/**
	 * 读取接收数据:::::处理字节流
	 * 
	 * @param none
	 * */
	public String readData(InputStream input) throws IOException {
		String reqStr = "";// 初始化为null
		// 接收返回信息
		int avali = 0;
		while (avali == 0) {
			avali = input.available();
			if (avali != 0) {
				System.out.println("tcp接收长度:" + avali);
				// 接收信息####
				reqStr = new String(this.readBytes(input,avali));
				break;
			}
		}
		return reqStr;
	}
	/**
	 * 接收读取.
	 * 
	 * @param in  输入流 .
	 * 
	 * @param length  数据包长度 .
	 * 
	 * */
	public byte[] readBytes(InputStream in, long length)
			throws IOException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int read = 0;
		while (read < length) {
			int cur = in.read(buffer, 0,
					(int) Math.min(1024, length - read));
			if (cur < 0) {
				break;
			}
			read += cur;
			bo.write(buffer, 0, cur);
		}
		return bo.toByteArray();
	}
	
	/**
	 * @return
	 */
	public String getTcpRecCount() {
		return tcpRecCount;
	}

	/**
	 * @return
	 */
	public String getTcpRecPSize() {
		return tcpRecPSize;
	}

	/**
	 * @return
	 */
	public String getTimeout() {
		return timeout;
	}



	/**
	 * @param string
	 */
	public void setTcpRecCount(String string) {
		tcpRecCount = string;
	}

	/**
	 * @param string
	 */
	public void setTcpRecPSize(String string) {
		tcpRecPSize = string;
	}

	/**
	 * @param string
	 */
	public void setTimeout(String string) {
		timeout = string;
	}

}
