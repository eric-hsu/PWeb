<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/jsp/common/taglib.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>商户测试支付完成页面</title>
    
    <link rel="icon" href="${ctx}/images/icon.png" type="image/x-icon">
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
      body{
			background: url("${ctx}/images/bodyBG.gif") repeat scroll 0 0 rgba(0, 0, 0, 0);
    		color: #333;
    		font: 12px "微软雅黑","宋体",Verdana,Arial;
		}
	</style>
  	</head>
  <body>
		<%@ include file="/jsp/tradeManage/top.jsp"%>
		<br>
		<form action="${ctx}/payment/testNotifyMer.do" id="notifyForm" method="post"  target="_blank">
		<input type="hidden" value="${informalTraderecord.ttrMerOrderno}" name="ttrMerOrderno" id = "ttrMerOrderno"/>
		<input type="hidden" value="${informalTraderecord.ttrNo}" name="ttrNo" id = "ttrNo"/>
		
		<div class="container">
			<div class="row">
				<div class="span12">
				    <div class="hero-unit" style="background-color:#ccffcc;">
					  <h1 style="font-size:30px;color:#33cc00;line-height: 50px;font-family: serif;">订单付款成功</h1>
					  <p>订单号：${informalTraderecord.ttrMerOrderno}  &nbsp;平台流水号：${informalTraderecord.ttrNo}  &nbsp;订单金额：<font style="color:red;"><strong>￥${informalTraderecord.ttrAmount}</strong></font></p>
					  <p>
					    <button class="btn btn-primary btn-large" type="submit" id="s_btn">返回商家网站</button>
					  </p>
					</div>
				</div>
			</div>
		</div>
		</form>
	
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
