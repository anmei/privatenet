<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/page/common/rhcbase.jsp" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
	var base = '${base}';
	function getAccount(){
		$.ajax({
			url:base+"/twitter/doAddUser",	//请求url
			type:"POST",	//请求类型
			datatype:"json",//返回数据类型。datatype 有时候会使得js解析为parserError,故出现这种问题时可以不要datatype
			error:function(XMLHttpRequest, textStatus, errorThrown){//当java程序没有捕获抛出的runtime异常时，ajax可以通过error返回抛出的runtime异常
				console.log("请求出现异常"+" "+textStatus);
			},
			success:function(data, textStatus, jqXHR){
				$("#id").val(data);
			}
		});
	}
	
	function dologin(){
		window.location.href=base+"/twitter/dologin?userid="+$("#id").val();
	}
	</script>
	
	
  </head>
  
  <body>
    <form action="">
    	<input value="" name="userid" id="id"/>
    	<button onclick="getAccount()">获取账号</button><button onclick="dologin()">登陆</button>
    </form>
  </body>
</html>
