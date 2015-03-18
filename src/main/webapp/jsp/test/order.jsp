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
    <title>订单</title>
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
  
  	
  	<script type="text/javascript" src="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/jquery/jquery-validation/1.11.0/jquery.validate.css" type="text/css"></link>
  	<script type="text/javascript">
  		$().ready(function() {
  			$("#orderForm").validate();
  		});
  		
  		function bankChange(){
  			var bankCode = $('#bankCode option:selected') .val();
  			
  			if(bankCode==""){
  				 $("#payTypediv").css("display","none");
  			}else{
  				$("#payTypediv").css("display","block");
  			}
  			
  		}
  	
  	</script>
  	</head>
  	<body >
  	<div class="container">
		<div class="row">
			<div class="span12">
				 <form:form  modelAttribute="order" class="form-horizontal" name="orderForm" id="orderForm" action="${ctx}/payment/orderRequest.do" method="post" target="_blank">
				    <tags:message content="${message}"/>
				    <fieldset>
				      <div id="legend" class="">
				        <legend class="">订单信息</legend>
				      </div>
				    <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">商户号：</label>
				          <div class="controls">
				            <input type="text" value="12762" class="required"  name="merNo"/>
				            <p class="help-block" style="margin-top:10px;">如：支付平台注册的商户号</p>
				          </div>
				        </div>
				        
				     <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">网关接入号：</label>
				          <div class="controls">
				            <input type="text" value="12762001" class="required" name="gatewayNo"/>
				            <p class="help-block" style="margin-top:10px;">如：商户对应分发的网关接入号</p>
				          </div>
				        </div>
				    
				    <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">订单号：</label>
				          <div class="controls">
				             <input type="text" value="" maxlength="15" class="required" name="orderNo"/>
				            <p class="help-block" style="margin-top:10px;">如：1234567891011，由数字或字母组成</p>
				          </div>
				        </div>

				    <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">金额：</label>
				          <div class="controls">
				            <input type="text" value=""  class="required" name="amount"/>
				            <p class="help-block" style="margin-top:10px;">如：100，只能由数字或点号组成</p>
				          </div>
				        </div>
				        
				    <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">币种：</label>
				          <div class="controls">
				            <input type="text" value="CNY" class="required" name="currency"/>
				            <p class="help-block" style="margin-top:10px;">如：CNY</p>
				          </div>
				        </div>
				        
				         <div class="control-group">

				          <!-- Select Basic -->
				          <label class="control-label">银行</label>
				          <div class="controls">
				            <select class="input-xlarge" name="bankCode" id="bankCode" onchange="bankChange();">
				              <option value="">--未选择--</option>
				              <option value="abc">中国农业银行</option>
				              <option value="icbc">中国工商银行</option>
				              <option value="cib">兴业银行</option>
				            </select>
				          </div>
				        </div>
				        <div style="display:none;" id="payTypediv">
				         <div class="control-group">

				          <!-- Select Basic -->
				          <label class="control-label">支付类型</label>
				          <div class="controls">
				            <select class="input-xlarge" name="payType">
				              <option value="0">个人网银</option>
				              <option value="1">快捷支付</option> 
				              <option value="2">企业网银</option> 
				            </select>
				          </div>
				        </div>
				        <div class="control-group">

				          <!-- Select Basic -->
				          <label class="control-label">卡类型</label>
				          <div class="controls">
				            <select class="input-xlarge" name="cardType">
				              <option value="0">借记卡</option>
				              <option value="1">信用卡</option> 
				            </select>
				          </div>
				        </div>
				        
				        <div class="control-group">

				          <!-- Select Basic -->
				          <label class="control-label">是否快捷支付</label>
				          <div class="controls">
				            <select class="input-xlarge" name="isquick">
				              <option value="1">否</option>
				              <option value="0">是</option>
				              
				            </select>
				          </div>
				        </div>
				        
				         
				        </div>
				         <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">订单页面通知地址：</label>
				          <div class="controls">
				            <input type="text" value="http://119.147.217.149/PWeb/jsp/test/mer_result_get.jsp"  name="frontNotifyUrl"/>
				            <p class="help-block" style="margin-top:10px;">页面跳转地址</p>
				          </div>
				        </div>
				        
				         <div class="control-group">
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">订单后台通知地址：</label>
				          <div class="controls">
				            <input type="text" value="http://119.147.217.149/PWeb/payment/backNotifyResult.do" class="required" name="backNotifyUrl"/>
				            <p class="help-block" style="margin-top:10px;">页面后台异步通知地址</p>
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
				
				          <!-- Text input-->
				          <label class="control-label" for="input01">备注：</label>
				          <div class="controls">
				            <input type="text" value="正常订单" maxlength="200"  name="remark"/>
				            <p class="help-block" style="margin-top:10px;">如：备注信息</p>
				          </div>
				        </div>
				
				    <div class="control-group">
				          <!-- Button -->
				          <div class="controls">
				            <button class="btn btn-success" type="submit">提交</button>
				          </div>
				        </div>
				
				    </fieldset>
				  </form:form >
			</div>
		</div>
	</div>
  	
  	
  	</body>
  	</html>