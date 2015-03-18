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

    <title>借记卡信息确认</title>
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
  
  	<link rel="stylesheet" href="${ctx}/css/debitcard/debitcard.css" type="text/css"></link>
  	<script type="text/javascript" src="${ctx}/js/debitcard/debitcard.js"></script>
  	<script type="text/javascript" src="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.css" type="text/css"></link>
  	<script type="text/javascript">
  		$(document).ready(function() {
  			showLeftTime();
  			$("#paymentForm").validate();
  		});
  	
  	</script>
  	</head>
  	<body >
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
					<div class="mc">
				    	<s class="succ04"></s>
				    	<h3 class="orderinfo">填写支付信息，请您尽快付款！</h3>
				    	<ul class="list-orderinfo">
							<li>订单号：${traderecord.trMerOrderno}</li>
							<li>流水号：${traderecord.trNo}</li>
							<li class="li-last">应付金额：<strong class="ftx-01">${traderecord.trAmount}</strong> 元</li>
				        </ul><br>
				        <p class="mb-tip">请您在提交订单后<span class="ftx-04">24小时</span>内完成支付，否则订单会自动取消。</p>
				    </div>
				</div>
			</div>
		</div>
		<br>
		<div class="container">
			<div class="row">
				<div class="span12">
					<form class="form-horizontal" id="paymentForm" action="${ctx}/debitcardController/quickPay.do" method="post">
					    <fieldset>
					      <div id="legend" class="">
					        <legend class="">储蓄卡快捷支付信息 <a href="javascript:history.go(-1);" style="float:right; font-size: 14px;">重新选择支付方式</a></legend>
					      </div>
					      
					      <div class="control-group">
							 <label class="control-label" for="input01">付款方式</label>
							 <div class="controls">
					            <img alt="${traderecord.trBankCode}" src="${ctx}/images/bank_logo/bank_${traderecord.trBankCode}.png">
					            <input type="hidden" value="${traderecord.trBankCode}" name="bankCode">
					          </div>
					       </div>

					      <div class="control-group">
					
					          <!-- Text input-->
					          <label class="control-label" for="input01">姓名</label>
					          <div class="controls">
					            <input type="text" class="required" name="accountName">
					            <p class="help-block" style="margin-top:10px;">如：张三丰，办卡注册姓名</p>
					          </div>
					       </div><div class="control-group">
					       
					          <!-- Select Basic -->
					          <label class="control-label">证件类型</label>
					          <div class="controls">
					            <select class="input-xlarge" name="certificateTypeCode" style=" width: 130px;">
							      <option>身份证</option>
							      <option>军官证</option>
							      <option>护照</option>
							      <option>居住证</option>
							     </select>
							     <input type="text" class="required" name="certificateNo">
							     <p class="help-block" style="margin-top:10px;">注：证件号码必须正确核对填写</p>
					          </div>
					
					        </div>
					
					    <div class="control-group">
					
					          <!-- Text input-->
					          <label class="control-label" for="input01">储蓄卡卡号</label>
					          <div class="controls">
					            <input type="text" class="required" name="accountNo">
					          </div>
					        </div><div class="control-group">
					
					
					          <!-- Text input-->
					          <label class="control-label" for="input01">手机号码</label>
					          <div class="controls">
					            <input type="text" class="required" name="telephone">
					            <p class="help-block" style="margin-top:10px;">注：储蓄卡注册手机号码</p>
					          </div>
					        </div>
					
					    <div class="control-group">
					
					          <!-- Appended input-->
					          <label class="control-label">验证码</label>
					          <div class="controls">
					            <div class="input-append">
					              <input class="span2"  type="text">
					              <span class="add-on">免费获取</span>
					            </div>
					            <p class="help-block" style="margin-top:10px;">查看手机短信并填写相应的验证信息</p>
					          </div>
					
					        </div>
							<div class="control-group">
					          <label class="control-label"></label>

					          <!-- Button -->
					          <div class="controls">
					          	<input type="hidden" value="${traderecord.trMerOrderno}" name="trMerOrderno">
					          	<input type="hidden" value="${traderecord.trNo}" name="trNo">
					          	<input type="hidden" value="${traderecord.trAmount}" name="trAmount">
					            <button class="button button-pill button-primary">提交</button>
					          </div>
					        </div>
					        
					    </fieldset>
					  </form>
				</div>
			</div>
	</div>
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