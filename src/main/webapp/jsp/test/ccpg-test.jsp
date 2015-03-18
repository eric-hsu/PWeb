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

    <title>CCGP商户测试单</title>
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
  
  	
  	<script type="text/javascript" src="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.css" type="text/css"></link>
  	<script type="text/javascript">
  		$().ready(function() {
  			$("#orderForm").validate();
  		});
  	
  	</script>
  	</head>
  	<body >
  	<div class="container">
		<div class="row">
			<div class="span12">
				 <form  modelAttribute="order" class="form-horizontal" name="orderForm" id="orderForm" action="http://127.0.0.1:8080/CCPG/PaymentServlet" method="post">
				    <tags:message content="${message}"/>
				    <fieldset>
				      <div id="legend" class="">
				        <legend class="">订单信息</legend>
				      </div>
				   <div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">merNo：</label>
					  <div class="controls">
						<input  maxlength="15" class="required" name="merNo" value="10000"/>
					  </div>
					</div>
					
					 <div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">gatewayNo：</label>
					  <div class="controls">
						<input  maxlength="15" class="required" name="gatewayNo" value="10000001"/>
					  </div>
					</div>
					
					 <div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">referer：</label>
					  <div class="controls">
						<input  maxlength="100" class="required" name="referer" value="http://127.0.0.1:8080/CCPG/PaymentServlet"/>
					  </div>
					</div>
					
					 <div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">orderNo：</label>
					  <div class="controls">
						<input  maxlength="100" class="required" name="orderNo" value="1360124410245414"/>
					  </div>
					</div>
					
					<div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">orderAmount：</label>
					  <div class="controls">
						<input  maxlength="15" class="required" name="orderAmount" value="100"/>
					  </div>
					</div>
					
					<div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">orderCurrency：</label>
					  <div class="controls">
						<input  maxlength="15" class="required" name="orderCurrency" value="CNY"/>
					  </div>
					</div>
					
					<div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">returnUrl：</label>
					  <div class="controls">
						<input  maxlength="100" class="required" name="returnUrl" value="http://localhost:8080/PWeb/jsp/test/ccpg-test.jsp"/>
					  </div>
					</div>
					
					<div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">signInfo：</label>
					  <div class="controls">
						<input  maxlength="100" class="required" name="signInfo" value="1415641654156485163465531650561658"/>
					  </div>
					</div>
					
					<div class="control-group">	
					  <!-- Text input-->
					  <label class="control-label" for="input01">remark：</label>
					  <div class="controls">
						<input  maxlength="15" class="required" name="remark" value="测试"/>
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