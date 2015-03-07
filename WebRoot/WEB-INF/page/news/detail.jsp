<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/rhcbase.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>私人定制  - 最懂你</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<!-- self-adaption -->
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<style type="text/css">
		img, object { width: 100%;}
	</style>
	<!-- / -->
	
	<style type="text/css">
		.title{color: #3a3a3a;font-weight: bolder;font-size: 20px !important;}
		.date{
			font-size: 0.7em;font-weight:lighter;margin-top: 1em;margin-bottom:3em; color: #aaa;
		}
		
	</style>
	
	
  </head>
  
  <body>
  	<div class="title"></div>
  	<div class="date"></div>
    <div class="content"></div>
    <div class="webUrl"><a href="" class="weburlhref" target="_blank">原文地址</a></div>
  </body>
  <script type="text/javascript">
  	Date.prototype.format =function(format)
	{
		var o = {
		"M+" : this.getMonth()+1, //month
		"d+" : this.getDate(), //day
		"h+" : this.getHours(), //hour
		"m+" : this.getMinutes(), //minute
		"s+" : this.getSeconds(), //second
		"q+" : Math.floor((this.getMonth()+3)/3), //quarter
		"S" : this.getMilliseconds() //millisecond
		};
		if(/(y+)/.test(format)) format=format.replace(RegExp.$1,
		(this.getFullYear()+"").substr(4- RegExp.$1.length));
		for(var k in o)if(new RegExp("("+ k +")").test(format))
		format = format.replace(RegExp.$1,
		RegExp.$1.length==1? o[k] :
		("00"+ o[k]).substr((""+ o[k]).length));
		return format;
	};
  </script>
  <script type="text/javascript">
  		var base = '${base}';
  		var url = '${url}';
  		var absid = '${absid}';
  		var tn = '${tableName}';
  		$.ajax({
			url:base+"/news/getContent.json",
			type:"POST",
			data:{url:url,absid:absid,tableName:tn},
			async: false,	//不允许异步,与覆盖层不能同时使用
			dataType:"json",
			beforeSend: function(XMLHttpRequest){
			},
			error:function(XMLHttpRequest, textStatus, errorThrown){
				alert("请求出现异常"+textStatus);
			},
			success:function(data, textStatus, jqXHR){
				$(".title").html(data.newsabs.title);
				$(".date").html(data.newsabs.auth+"&nbsp;"+new Date(data.newsabs.date).format("yyyy-MM-dd hh:mm"));
				$(".content").html(data.content);
				$(".weburlhref").attr("href",data.newsabs.url);
			},
			complete: function(XMLHttpRequest, textStatus){
			}

		});
  	
  </script>
  
  
</html>
