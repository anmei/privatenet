<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'shows.jsp' starting page</title>
    
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
   ${test}
	<div style="width:900px;margin:20px auto;">
	<table border="1">
		<thead>
			<tr>
				<th >是否成功</th>
				<th>提示消息</th>
				<th>原始文件名称</th>
				<th>相对路径名与文件名</th>
				<th>绝对路径名与文件名</th>
				<th>文件内容</th>
			</tr>
		</thead>
		<tbody>
		
		<c:forEach items="${data}" var="item">
			<tr>
				<td width="100px">${item.success}</td>
				<td width="100px">${item.message}</td>
				<td>${item.realFileName}</td>
				<td>${item.webLinkfileName}</td>
				<td>${item.absoluteFileName}</td>
				<td><img src="${imgbase}${item.fileNameInServer}" width="400px" height="200px" /></td>
			</tr>
		
		</c:forEach>
		</tbody>
		
	</table>
	
	
	</div>
  </body>
</html>
