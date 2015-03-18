<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.jinxin.common.valueobj.MerResult" %>
<%
	MerResult merResult = (MerResult)request.getAttribute("merResult");
	out.println("<root>");
	out.println("	<type>");
	out.println("		"+merResult.getType());
	out.println("	</type>");
	out.println("	<message>");
	out.println("		<merNo>");
	out.println("			"+merResult.getMerNo());
	out.println("		</merNo>");
	out.println("		<gatewayNo>");
	out.println("			"+merResult.getGatewayNo());
	out.println("		</gatewayNo>");
	out.println("		<orderNo>");
	out.println("			"+merResult.getOrderNo());
	out.println("		</orderNo>");
	out.println("		<tradeNo>");
	out.println("			"+merResult.getTradeNo());
	out.println("		</tradeNo>");
	out.println("		<status>");
	out.println("			"+merResult.getStatus());
	out.println("		</status>");
	out.println("		<signInfo>");
	out.println("			"+merResult.getSignInfo());
	out.println("		</signInfo>");
	out.println("		<remark>");
	out.println("			"+merResult.getRemark());
	out.println("		</remark>");
   	out.println("	</message>");
   	out.println("</root>");
%>