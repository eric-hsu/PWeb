<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	out.println("orderNo="+request.getParameter("orderNo"));
	out.println("orderAmount="+request.getParameter("orderAmount"));
	out.println("orderStatus="+request.getParameter("orderStatus"));
	out.println("tradeNo="+request.getParameter("tradeNo"));
	out.println("signInfo="+request.getParameter("signInfo"));
%>