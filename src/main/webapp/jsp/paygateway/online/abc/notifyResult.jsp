<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
  <head>

    <title>农行订单支付结果</title>
    
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
  </head>
  
  <body>
    	<div class="container">
		<div class="row">
			<div class="span12">
				 <form:form>
				    <fieldset>
				    <div id="legend" class="">
				        <legend class="">农行订单支付结果</legend>
				      </div>
				    <br>
				    
				      <div class="control-group">
				          <label class="control-label">返回信息：</label>${tResult.result}
				        </div>
				        
				        <div class="control-group">
				          <label class="control-label"></label>
				          <div class="controls">
				           	<a href="javascript:history.go(-1);" style="float:right; font-size: 14px;">返回</a>
				          </div>
				        </div>
				        
				    </fieldset>
				  </form:form>
			</div>
		</div>
	</div>
  	
  </body>
</html>
