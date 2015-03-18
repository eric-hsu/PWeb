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

    <title>支付结果</title>
    
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
		
		  .col-md-3 {
		      width: 25%;
		      position: relative;
			  min-height: 1px;
			  padding-right: 15px;
			  padding-left: 15px;
			  float: left;
			  margin: 2em 0;
		      text-align: center;
		  }
  
		.water_pay_link{
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll 1px -48px rgba(0, 0, 0, 0);
		    display: block;
		    height: 50px;
		    margin: 0 0 0 100px;
		    width: 50px;
		}
		.water_pay_link:HOVER {
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll -48px -48px rgba(0, 0, 0, 0);
		}
		
		.electric_pay_link{
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll 1px 1px rgba(0, 0, 0, 0);
		    display: block;
		    height: 50px;
		    margin: 0 0 0 100px;
		    width: 50px;
		}
		.electric_pay_link:HOVER {
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll -48px 1px rgba(0, 0, 0, 0);
		}
		
		.gas_pay_link{
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll 1px -97px rgba(0, 0, 0, 0);
		    display: block;
		    height: 50px;
		    margin: 0 0 0 100px;
		    width: 50px;
		}
		.gas_pay_link:HOVER {
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll -48px -97px rgba(0, 0, 0, 0);
		}
		
		.phone_pay_link{
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll 1px -195px rgba(0, 0, 0, 0);
		    display: block;
		    height: 50px;
		    margin: 0 0 0 100px;
		    width: 50px;
		}
		.phone_pay_link:HOVER {
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll -48px -195px rgba(0, 0, 0, 0);
		}
		
		.transfer_pay_link{
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll 1px -293px  rgba(0, 0, 0, 0);
		    display: block;
		    height: 50px;
		    margin: 0 0 0 100px;
		    width: 50px;
		}
		.transfer_pay_link:HOVER {
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll -48px -293px  rgba(0, 0, 0, 0);
		}
		
		.enterprise_server_link{
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll 1px -342px   rgba(0, 0, 0, 0);
		    display: block;
		    height: 50px;
		    margin: 0 0 0 100px;
		    width: 50px;
		}
		.enterprise_server_link:HOVER {
			background: url("${ctx}/images/icon_pay.png") no-repeat scroll -48px -342px   rgba(0, 0, 0, 0);
		}
  	</style>
  	
  	</head>
  <body>
		<%@ include file="/jsp/tradeManage/top.jsp"%>
		<br>
		<div class="container">
			<div class="row">
				<div class="span12">
				    <div class="hero-unit" style="background-color:#ccffcc;">
				      <img src="${ctx}/images/success.png" style="height:60px;width:70px;float:left;" />
					  <h1 style="font-size:30px;color:#33cc00;line-height: 50px;font-family: serif;">&nbsp;订单付款成功!</h1>
					  <br>
					  <p>订单号：${returnMerchant.orderNo}  &nbsp;平台流水号：${returnMerchant.tradeNo}  &nbsp;订单金额：<font style="color:red;"><strong>￥${returnMerchant.orderAmount}</strong></font></p>
					</div>
				</div>
			</div>
		</div>

		<!---container--->
		<div class="content-what" id="services">
			<div class="container">
				<div class="content-we">
					<h2 style="font-size: 20px;font-family: serif;color: #6699FF;"><span style="background: none repeat scroll 0 0 #e1e6ea; margin-right: 0.5em;padding: 0 8px;"> </span>
					温馨提示，你可能需要.</h2>
				</div>
				<div class="row">
					<div class="col-md-3 easy-them">
						<a href="#" class="water_pay_link"></a>
						<h4>交水费</h4>
						<p>水费是使用供水工程供应的水单位和个人，按照规定向供水单位缴纳的费用。金信平台贴心为您准备了部分地区的水费网上缴费服务.</p>
					</div>
					<div class="col-md-3 easy-them">
						<a href="#" class="electric_pay_link"></a>
						<h4>交电费</h4>
						<p>目前平台支持部分地区电费缴费服务，范围分布在全国各地，主要经营范围为新疆西部地区一带，请点击咨询并操作.</p>
					</div>
					<div class="col-md-3 easy-them">
						<a href="#" class="gas_pay_link"></a>
						<h4>交煤气费</h4>
						<p>目前平台支持部分地区煤气缴费服务，主要分布在广东及新疆各地区，请点击咨询并操作.</p>
					</div>
					
					<div class="clearfix"> </div>
				</div>
				
				<div class="row">
					<div class="col-md-3 easy-them">
						<a href="#" class="phone_pay_link"></a>
						<h4>充话费</h4>
						<p>充值项目包括，手机充值，固话充值.</p>
					</div>
					<div class="col-md-3 easy-them">
						<a href="#" class="transfer_pay_link"></a>
						<h4>转账</h4>
						<p>支持跨行转账，同行转账不收取手续费.</p>
					</div>
					
					<div class="col-md-3 easy-them">
						<a href="#" class="enterprise_server_link"></a>
						<h4>企业电商服务</h4>
						<p>作为专业的第三方支付平台，支持B2C及B2B相关支付接口服务，详情请点击咨询.</p>
					</div>
					<div class="clearfix"> </div>
				</div>
			</div>	
		</div>
		<!---container--->
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
