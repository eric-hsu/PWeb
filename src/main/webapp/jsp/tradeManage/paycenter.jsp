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
    <title>金信支付</title>
    <link rel="icon" href="${ctx}/images/icon.png" type="image/x-icon">
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE10"/>
	<meta http-equiv="X-UA-Compatible"content="IE=9"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=7"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE6"/>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
  	<link rel="stylesheet" href="${ctx}/plugin/bootstrap/2.3.1/css_default/bootstrap.min.css" type="text/css"></link>
  	<link rel="stylesheet" href="${ctx}/css/paycenter/paycenter.css" type="text/css"></link>
  	<script type="text/javascript" src="${ctx}/plugin/bootstrap/2.3.1/js/bootstrap.min.js"></script>
  	<script type="text/javascript" src="${ctx}/js/paycenter/paycenter.js"></script>
  	<!-- 普通按钮 -->
  	<link rel="stylesheet" href="${ctx}/plugin/bootstrap/Buttons-master/css/buttons.css" type="text/css"></link>
  	<!-- 单选按钮 -->
  	<link rel="stylesheet" href="${ctx}/plugin/bootstrap/iCheck-master/skins/square/blue.css" type="text/css"></link>
  	<script type="text/javascript" src="${ctx}/plugin/bootstrap/iCheck-master/icheck.js"></script>
	<script>
		$(document).ready(function(){
		  $('input').iCheck({
		    checkboxClass: 'icheckbox_square-blue',
		    radioClass: 'iradio_square-blue',
		    increaseArea: '20%' // optional
		  });
		  
		  innitag('personalPay');
		  
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
	<br><br>
	<div class="container">
		<div class="row">
			<div class="span12">
			    <div>
			    <img src="${ctx}/images/order.png" style="height:40px;width:40px;float:left;" />
			    <table style="margin-left: 60px;">
			    	<tr style="line-height:10px;">
			    		<td style="padding-right:8px;width:75px;text-align:right;">订单号：</td>
			    		<td style="border-bottom: 1px solid #008db2;width: 100px;">${traderecord.trMerOrderno}</td>
			    		<td style="padding-right:8px;width:75px;text-align:right;">应付金额：</td>
			    		<td style="border-bottom: 1px solid #008db2;width: 100px;"><strong class="ftx-01">${traderecord.trAmount}</strong>元</td>
			    		<td style="padding-right:8px;width:75px;text-align:right;">商户来源：</td>
			    		<td style="border-bottom: 1px solid #008db2;width: 100px;">${merchant.merName}</td>
			    		<td style="padding-right:8px;width:75px;text-align:right;">订单时间：</td>
			    		<td style="border-bottom: 1px solid #008db2;width: 200px;">${traderecord.trDatetime}</td>
			    	</tr>
			    	<tr>
			    		<td colspan="8" style="padding-right:8px;padding-left:30px;padding-top:10px;"><p style="color: #999999;font: 12px/150% Arial,Verdana;">注：请您在提交订单后<strong class="ftx-01">2小时</strong>内完成支付，否则订单会自动取消。</p></td>
			    	</tr>
			    </table>

			    </div>
			</div>
		</div>
	</div>
	<br>
	<div class="container">
		<div class="row">
			<div class="span12">
				<div class="tabbable"> <!-- Only required for left/right tabs -->
				  <ul class="nav nav-tabs">
				     <li class="active"><a href="#tab1" onfocus="this.blur();" data-toggle="tab" onclick="innitag('personalPay');">个人网银</a></li>
				    <li><a href="#tab3" onfocus="this.blur();" data-toggle="tab" onclick="innitag('enterprisePay');">企业网银</a></li>
				    <li><a href="#tab2" onfocus="this.blur();" data-toggle="tab" onclick="innitag('quickPay');">快捷支付</a></li>
				    <li class="dropdown pull-right">
						 <a href="javascript:;" data-toggle="dropdown" class="dropdown-toggle">下拉<strong class="caret"></strong></a>
						<ul class="dropdown-menu">
							<li>
								<a href="#tab1" data-toggle="tab" onclick="innitag('personalPay');">个人网银</a>
							</li>
							
							<li>
								<a href="#tab3" data-toggle="tab" onclick="innitag('enterprisePay');">企业网银</a>
							</li>
							<li class="divider">
							</li>
							<li>
								<a href="#tab2" data-toggle="tab" onclick="innitag('quickPay');">快捷支付</a>
							</li>
						</ul>
					</li>
				  </ul>
				  <div style="margin-top:-20px;padding-bottom:20px;border-bottom: 1px solid #00a1cb;border-left: 1px solid #00a1cb;border-right: 1px solid #00a1cb;background-color:#ffffff;">
				  <div class="tab-content">
				    <!--1，个人网银支付 -->
				    <div class="tab-pane active" id="tab1">
					  	<!--1.3，个人网银支付 -->
					  	<div id="personalPay_div" >
						  	<div class="clear">
							  <br>
							  <span class="mb-tip"><strong>个人网银：</strong>需要跳转到个人网银支付支付（信用卡网银或借记卡网银）！</span>
						  	</div>
						  	<ul class="bank_ul">
							  	<c:forEach items="${personalPaylist}" var="bank" begin="0" end="6" varStatus="status">
								  	<li class=""><label for="lab_personalPay_default_${bank.bankcode}"><a  onfocus="this.blur();" href="javascript:;">
								  		<input type="radio" id="lab_personalPay_default_${bank.bankcode}" value="${bank.bankcode}" name="personalPay_default" <c:if test="${status.index==0}">checked </c:if>>
								  		<img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
								  		</a></label>
								  	</li>
							  	</c:forEach>
								<c:if test="${fn:length(personalPaylist)>6}">
							  		<li class="" style="border:none;border:0;padding-top:6px;padding-left:20px;">
							  			<a id="personalPay_more" href="#personalPay_all" role="button" class="btn" data-toggle="modal">更多</a>
							  		</li>
							  	</c:if>
							  	<c:if test="${fn:length(personalPaylist)==0}">
						  			<br>
						  			<div style="margin-left: 40px;">
						  				<span>暂无该支付方式！稍后上线，敬请期待...</span>
						  			</div>
						  		</c:if>
						  	</ul>
						  	
						  	<div id="personalPay_all" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h3 id="myModalLabel">
										全部网银支付方式
									</h3>
								</div>
								<div class="modal-body">
									<p>
									 <ul class="bank_ul">
									 	<c:forEach items="${personalPaylist}" var="bank" varStatus="status">
									 	<li class=""><label for="lab_personalPay_all_${bank.bankcode}"><a  onfocus="this.blur();" href="javascript:;">
									  		<input type="radio" id="lab_personalPay_all_${bank.bankcode}" value="${bank.bankcode}" name="personalPay_all" <c:if test="${status.index==0}">checked </c:if>>
									  		<img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
									  		</a></label>
									  	</li>
									 	</c:forEach>
								  	</ul>
									</p>
								</div>
								<div class="modal-footer">
									 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <button class="btn btn-primary" onclick="layerNextBtn('personalPay_all');">下一步</button>
								</div>
							</div>
					  	</div>
				    </div>
				    
				    <!--2，快捷支付模块-->
				    <div class="tab-pane" id="tab2">
				    
				    	<!--2.1，借记卡快捷支付 -->
					    <div class="quickPayDebitcard_div">
						  <div class="clear">
							  <br>
							  <span class="mb-tip"><strong>借记卡快捷支付：</strong>需要填写个人的借记卡注册信息完成快捷支付！</span>
						  	</div>
					      <ul class="bank_ul">
							   <c:forEach items="${quickPayDebitcardlist}" var="bank" begin="0" end="2" varStatus="status">
							   	<li><label for="lab_quickPayDebitcard_default_${bank.bankcode}"><a onfocus="this.blur();" href="javascript:;">
							  		  <input type="radio" id="lab_quickPayDebitcard_default_${bank.bankcode}" value="${bank.bankcode}_Debitcard" name="quickPay_default" <c:if test="${status.index==0}">checked </c:if>>
							  		  <img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
							  	  </a></label>
							  	</li>
							   </c:forEach>
							   <c:if test="${fn:length(quickPayDebitcardlist)>3}">
							   <li class="" style="border:none;border:0;padding-top:6px;padding-left:20px;">
							  		<a id="quickPayDebitcard_more" href="#quickPayDebitcard_all" role="button" class="btn" data-toggle="modal">更多</a>
							  	</li>
							  	</c:if>
							  	
							  	<c:if test="${fn:length(quickPayDebitcardlist)==0}">
						  			<br>
						  			<div style="margin-left: 40px;">
						  				<span>暂无该支付方式！稍后上线，敬请期待...</span>
						  			</div>
						  		</c:if>
						  	</ul>
						  	<div id="quickPayDebitcard_all" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h3 id="myModalLabel">
										全部快捷支付方式
									</h3>
								</div>
								<div class="modal-body">
									<p>
									 <ul class="bank_ul">
									 	<c:forEach items="${quickPayDebitcardlist}" var="bank" varStatus="status">
									 	<li class=""><label for="lab_quickPayDebitcard_all_${bank.bankcode}"><a  onfocus="this.blur();" href="javascript:;">
									  		<input type="radio" id="lab_quickPayDebitcard_all_${bank.bankcode}" value="${bank.bankcode}" name="quickPayDebitcard_all" <c:if test="${status.index==0}">checked </c:if>>
									  		<img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
									  		</a></label>
									  	</li>
									 	</c:forEach>
								  	</ul>
									</p>
								</div>
								<div class="modal-footer">
									 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <button class="btn btn-primary" onclick="layerNextBtn('quickPayDebitcard_all');">下一步</button>
								</div>
							</div>
						</div>
						
						<!--2.2，信用卡水平线 -->
					  	<div class="clear">
					  		<br />
					  		<hr style="height:1px;border:none;border-top:1px dashed #0066CC;" />
					  	</div>
					  	
					  	<!--2.3，借记卡网银支付模块-->
					  	<div id="quickPayCreditcard_div">
							 <div class="clear">
							  <br>
							  <span class="mb-tip"><strong>信用卡快捷支付：</strong>需要填写个人的信用卡注册信息完成快捷支付！</span>
						  	</div>
						  	<ul class="bank_ul">
						  		 <c:forEach items="${quickPayCreditcardlist}" var="bank" begin="0" end="2" varStatus="status">
						  		 <li class=""><label for="lab_quickPayCreditcard_default_${bank.bankcode}"><a  onfocus="this.blur();" href="javascript:;">
							  		<input type="radio" id="lab_quickPayCreditcard_default_${bank.bankcode}" value="${bank.bankcode}_Creditcard" name="quickPay_default" <c:if test="${status.index==0}">checked </c:if>>
							  		<img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
							  		</a></label>
							  	</li>
						  		</c:forEach>
						  		<c:if test="${fn:length(quickPayCreditcardlist)>3}">
							  		<li class="" style="border:none;border:0;padding-top:6px;padding-left:20px;">
							  			<a id="quickPayCreditcard_more" href="#quickPayCreditcard_all" role="button" class="btn" data-toggle="modal">更多</a>
							  		</li>
							  	</c:if>
							  	<c:if test="${fn:length(quickPayCreditcardlist)==0}">
						  			<br>
						  			<div style="margin-left: 40px;">
						  				<span>暂无该支付方式！稍后上线，敬请期待...</span>
						  			</div>
						  		</c:if>
						  	</ul>
						  	<div id="quickPayCreditcard_all" class="modal hide fade" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
								<div class="modal-header">
									 <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
									<h3 id="myModalLabel">
										全部网银支付方式
									</h3>
								</div>
								<div class="modal-body">
									<p>
									 <ul class="bank_ul">
									 	 <c:forEach items="${quickPayCreditcardlist}" var="bank" varStatus="status">
									 	 <li class=""><label for="lab_quickPayCreditcard_all_${bank.bankcode}"><a onfocus="this.blur();" href="javascript:;">
									  		<input type="radio" id="lab_quickPayCreditcard_all_${bank.bankcode}" value="${bank.bankcode}" name="quickPayCreditcard_all" class="banking" <c:if test="${status.index==0}">checked </c:if>>
									  		<img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
									  		</a></label>
									  	 </li>
									 	 </c:forEach>
								  	</ul>
									</p>
								</div>
								<div class="modal-footer">
									 <button class="btn" data-dismiss="modal" aria-hidden="true">关闭</button> <button class="btn btn-primary" onclick="layerNextBtn('quickPayCreditcard_all');">下一步</button>
								</div>
							</div>
					  	</div>
				    </div>
				    
				    <!--3，企业网银支付-->
				    <div class="tab-pane" id="tab3">
				      <div class="clear">
						  <br>
						  <span class="mb-tip"><strong>企业网银：</strong>需要跳转到企业网银支付支付！</span>
					  </div>
				      <!--3.1，第三方支付平台全部支付方式-->
				      <ul class="bank_ul">
				      
				      	<c:forEach items="${enterprisePaylist}" var="bank" varStatus="status">
				      	<li><label for="lab_other_${bank.bankcode}"><a onfocus="this.blur();" href="javascript:;">
					  		  <input type="radio" id="lab_other_${bank.bankcode}" value="${bank.bankcode}" name="enterprisePay_default" <c:if test="${status.index==0}">checked </c:if>>
					  		  <img alt="${bank.bankname}" src="${ctx}/images/bank_logo/bank_${bank.bankcode}.png">
					  	  </a></label>
					  	</li>
				      	</c:forEach>

				      	<c:if test="${fn:length(enterprisePaylist)==0}">
				  			<br>
				  			<div style="margin-left: 50px;">
				  				<span>暂无该支付方式！稍后上线，敬请期待...</span>
				  			</div>
				  		</c:if>
				      	
					  </ul>
				    </div>
				  </div>
				  
				  <!-- 提交按钮 -->
				  <div style="margin-top:30px;">
				  	<a href="javascript:;" class="button button-block button-rounded button-primary button-large" onclick="defaultNextBtn();">下一步</a>
				  </div>
				  </div>
				</div>

			</div>
		</div>
	</div>
	<div style="display:none;">
		<form action="${ctx}/payment/orderPay.do" id="payForm" method="post" target="_blank">
			<input type="hidden" value="" name="cardType" id = "cardType"/>
			<input type="hidden" value="" name="chaType" id = "chaType"/>
			<input type="hidden" value="" name="bankcode" id = "bankcode"/>
			<input type="hidden" value="${traderecord.trNo}" name="trNo" id = "trNo"/>
			<button type="submit" id="s_btn">submit</button>
		</form>
	</div>
	
	<br><br>

	<div class="container-fluid" style="border-top: 1px solid #cccccc;">
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
