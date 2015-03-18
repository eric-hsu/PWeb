<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>
    
    <title>订单查询</title>
    <link rel="icon" href="images/logo_icon.png" type="image/x-icon">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/bootstrap/2.3.1/css_default/bootstrap.min.css" type="text/css"></link>
  	<script type="text/javascript" src="${ctx}/plugin/bootstrap/2.3.1/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.css" type="text/css"></link>
  	<script type="text/javascript">
  		$().ready(function() {
  			$("#orderForm").validate();
  		});
  	
  	</script>
  </head>
  
  <body>
    	<div class="container">
		<div class="row">
			<div class="span12">
				 <form  id="orderForm" action="${ctx}/payment/queryRequest.do" method="post">
				    <fieldset>
				    <div id="legend" class="">
				        <legend class="">订单查询</legend>
				      </div>
				    <br>
				      <div class="control-group">
				          <label class="control-label">商户订单号：</label>
				          <div class="controls">
				             <input type="text"   value="" class="required" name="orderNo"/>
				            <p class="help-block" style="margin-top:10px;">商户订单号.</p>
				          </div>
				
				        </div>
				     <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">商户号：</label>
				          <div class="controls">
				            <input type="text" value="12538" class="required" name="merNo"/>
				            <p class="help-block" style="margin-top:10px;">如：支付平台注册的商户号</p>
				          </div>
				        </div>
				        
				     <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">网关接入号：</label>
				          <div class="controls">
				            <input type="text" value="12538001" class="required" name="gatewayNo"/>
				            <p class="help-block" style="margin-top:10px;">如：商户对应分发的网关接入号</p>
				          </div>
				        </div>

				     <div class="control-group" style="color:red;">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01" >签名：</label>
				          <div class="controls">
				            <input type="text"  style="color:red;" value="18d288d4fc251963e6641e8528baa607" class="required" name="signInfo"/>
				            <p class="help-block" style="margin-top:10px;color:red;">注：该签名正式发布正式环境之后需要验证</p>
				          </div>
				        </div>
				
				    <div class="control-group">
				          <!-- Button -->
				          <div class="controls">
				            <button class="btn btn-success" type="submit">提交</button>
				          </div>
				        </div>
				    </fieldset>
				  </form>
			</div>
		</div>
	</div>
  	
  </body>
</html>
