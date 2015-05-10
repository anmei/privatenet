<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>服务器线程信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <%
  	for(Map.Entry<Thread,StackTraceElement[]> stackTrace:Thread.getAllStackTraces().entrySet()){
  		Thread thread = (Thread)stackTrace.getKey();
  		StackTraceElement[] stacktraceele = (StackTraceElement[])stackTrace.getValue();
  		/* if(thread.equals(Thread.currentThread())){
  			continue;
  		} */
  		out.print("</br>线程名："+thread.getName()+"</br>");
  		for(StackTraceElement ele:stacktraceele){
  			out.print("\t"+ele+"</br>");
  		}
  	}
  
  
   %>
  
  </body>
</html>
