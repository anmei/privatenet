<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
 
    <title>My JSP 'login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="${base}/resource/css/base.css">
	

  </head>
  
  <body>
    login page!<br>
    ${haha}
    ${base}
    
    <!--######## 公共分页 ########-->
	<form id="pageForm" action="${base}/test/paginationTest.html" method="post">
		<input type="hidden" value="1" name="orderStatus"/> <!-- 订单状态 -->
		
			<!-- currentPage：当前页  对应 PagingResults的 currentPage
			pageSize：每页显示的条数  对应 PagingResults的 pageSize
			totalSize：总记录条数  对应 PagingResults的 totalSize
			pages：总记录页数  对应 PagingResults的 totalPages
		 	-->
		<jsp:include page="/WEB-INF/page/common/paging.jsp" flush="true">
			<jsp:param value="${pagetest.toPage }" name="currentPage"/>
			<jsp:param value="${pagetest.pageSize }" name="pageSize"/>
			<jsp:param value="${pagetest.totalRows }" name="totalSize"/>
			<jsp:param value="${pagetest.totalPage}" name="pages"/>
		</jsp:include>
	</form>
	<!--######## 公共分页 ########-->
    
  </body>
</html>
