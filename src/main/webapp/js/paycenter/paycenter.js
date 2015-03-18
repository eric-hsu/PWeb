//0， tag标签选择记录
var checkTag="";
function innitag(check){
	checkTag = check;
}

//1， 默认“下一步”奠基事件
function defaultNextBtn(){
	if(checkTag==""){
		return;
	}
	var cardType;
	var bankcode = $("input[name='"+checkTag+"_default']:checked").val();
	if(bankcode.indexOf("Creditcard") >= 0 )  
	{
		$("#cardType").attr("value", "Creditcard");
		bankcode = bankcode.replace("_Creditcard","");
		cardType="Creditcard";
	}else if(bankcode.indexOf("Debitcard") >= 0 ){
		$("#cardType").attr("value", "Debitcard");
		bankcode = bankcode.replace("_Debitcard","");
		cardType="Debitcard";
	}
	$("#chaType").attr("value", checkTag);
	$("#bankcode").attr("value", bankcode);
	//alert("chaType="+checkTag+",bankcode="+bankcode+",cardType="+cardType);
	$("#payForm").submit();
}

//2， 全部“下一步”奠基事件
function layerNextBtn(name){
	if(checkTag==""){
		return;
	}
	var cardType="";
	var bankcode = $("input[name='"+name+"']:checked").val();
	if(name.indexOf("Creditcard") >= 0 )  
	{   cardType = "Creditcard";
		$("#cardType").attr("value", "Creditcard");
	}else if(name.indexOf("Debitcard") >= 0 ) {
		cardType = "Debitcard";
		$("#cardType").attr("value", "Debitcard");
	}
	$("#chaType").attr("value", checkTag);
	$("#bankcode").attr("value", bankcode);
	//alert("checkTag="+checkTag+",bankcode="+bankcode+",cardType="+cardType);
	$("#payForm").submit();
}


function showLeftTime()
{
var now=new Date();
var year=now.getFullYear();
var month=now.getMonth();
var day=now.getDate();
var hours=now.getHours();
var minutes=now.getMinutes();
var seconds=now.getSeconds();
document.all.timeLabel.innerHTML=""+year+"年"+month+"月"+day+"日 "+hours+":"+minutes+":"+seconds+"";
//一秒刷新一次显示时间
var timeID=setTimeout(showLeftTime,1000);
}
