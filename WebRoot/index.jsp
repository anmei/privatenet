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
    
    <title>privatenet</title>
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
  	<%-- <jsp:forward page="${base}/jqgrid/toMainPage"></jsp:forward>  --%>
  	
  	<!-- response.sendRedirect(request.getContextPath() + "/blogMainPage/blogmainpage"); -->

  	
  	<%--
  	/* response.sendRedirect(request.getContextPath() + "/news/list"); */
  	response.sendRedirect(request.getContextPath() + "/photoswipe.jsp");
  	--%>
  	
  	<h6><a href="${base}/photoswipe.jsp">photoGallery</a></h6>
  	
  	

  </body>
</html>
