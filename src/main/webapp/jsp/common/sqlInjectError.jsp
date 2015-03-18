<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>系统异常</title>
    
    <link rel="icon" href="images/logo_icon.png" type="image/x-icon">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/bootstrap/2.3.1/css_default/bootstrap.min.css" type="text/css"></link>
  	<script type="text/javascript" src="${ctx}/plugin/bootstrap/2.3.1/js/bootstrap.min.js"></script>
  	<!-- 普通按钮 -->
  	<link rel="stylesheet" href="${ctx}/plugin/bootstrap/Buttons-master/css/buttons.css" type="text/css"></link>
  	<link rel="stylesheet" href="${ctx}/css/credicard/credicard.css" type="text/css"></link>
  	<script type="text/javascript" src="${ctx}/js/creditcard/creditcard.js"></script>
  	<script type="text/javascript" src="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.css" type="text/css"></link>
  	<script type="text/javascript">
  		$(document).ready(function() {
  			showLeftTime();
  		});
  	
  	</script>
  	
  	<style type="text/css">
  	.ns-icon{
	  	 background: url("${ctx}/images/warn.png") no-repeat scroll 0 2px rgba(0, 0, 0, 0);
	   	 display: block;
	     float: left;
	     height: 32px;
	     width: 32px;
	     margin-top:3px;
     }
  	</style>

  	</head>
  <body>
		<div style="height:80px; border-bottom: 1px solid #cccccc;">
		    <div class="container">
				<div class="row">
					<div class="span12" >
						<img src="${ctx}/images/logo.gif"></img>
						
						<label id="timeLabel" style="float:right;margin-top:55px;">显示时间的位置</label>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="container">
			<div class="row">
				<div class="span12">
				    <div class="hero-unit">
				    	<span class="ns-icon"></span>
					  <h1 style="font-size:25px;">&nbsp;系统异常</h1><br>
					  <p>错误信息：<font style="color:red;"><strong>存在非法输入参数！</strong></font></p>
					</div>
				</div>
			</div>
		</div>
		<br>
		<div class="container-fluid" style=" border-top:  1px solid #dddddd;">
		<div class="row-fluid">
			<div class="span12">
				<div class="footer">
					<br>
					<p>Copyrights  2012 金信集团 All rights reserved 粤ICP备14020013号</p>
					<p>地址：深圳市福田区金田路2028号皇岗商务中心19层</p>
				</div>
			</div>
		</div>
	</div>
  </body>
</html>
