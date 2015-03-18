<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ page import = "com.jinxin.common.valueobj.GatewayResult" %>
<%
	String tradeNo = (String)request.getAttribute("tradeNo");
	String amount = (String)request.getAttribute("amount");
	GatewayResult gatewayResult = (GatewayResult)request.getAttribute("gatewayResult");
	out.println("<root>");
	out.println("<success>");
	out.println(gatewayResult.isSuccess());
	out.println("</success>");
	out.println("<result>");
	out.println("<tradeno>");
   	out.println(tradeNo);
   	out.println("</tradeno>");
   	out.println("<amount>");
   	out.println(amount);
   	out.println("</amount>");
   	out.println("<sign>");
   	out.println(gatewayResult.getSigninfo());
   	out.println("</sign>");
   	out.println("<message>");
   	out.println(gatewayResult.getResult());
   	out.println("</message>");
   	out.println("</result>");
   	out.println("</root>");
%>