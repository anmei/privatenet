<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  </head>
  
  <body>
  	<c:forEach var="newslist" items="${newsabs.objLists }">
    <li>
 		<table>
  			<tr>
  				<c:if test="${fn:length(newslist.imglist)>0}">
  				<td>
  					<a href="javascript:void(0);" onclick="newsdetail('${newslist.url}','${newslist.absid }')">
	   			<div class="imgc">
		   			<c:forEach var="img" items="${newslist.imglist }" varStatus="c">
		    			<c:if test="${c.count <=1}">
							<span class="imglist"><img src="${img}"/></span>    			
		    			</c:if>
		   			</c:forEach>
	   			</div>
	   			</a>
  				</td>
  				</c:if>
  				
  				<td valign="top">
  					<a href="javascript:void(0);" onclick="newsdetail('${newslist.url}','${newslist.absid }')">
  					<div class="rightc">
		   			<div class="titlec">${newslist.title }</div>
		   			<div class="date">${newslist.auth}&nbsp;<fmt:formatDate value="${newslist.date}" type="both"/></div>
	   			</div>
	   			</a>
  				</td>
 			</tr>
 		</table>
 	</li>
 	</c:forEach>
  </body>
</html>
