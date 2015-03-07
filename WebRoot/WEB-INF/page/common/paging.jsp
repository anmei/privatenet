<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<script type="text/javascript" src="${base}/resource/js/page.js" charset="UTF-8"></script>

<!-- 上页下页开始 -->
<!--分页开始-->
  <div class="page fs14">
  <c:if test="${param.pages>0}">
   <a href="javascript:page(1)" class="firstPage"></a> 
   <a class="page_up" href="javascript:page(${param.currentPage-1})"></a> 
	<c:choose>
		<c:when test="${param.pages <= 6}">
			<c:forEach begin="1" step="1" end="${param.pages}" varStatus="pageId">
				<c:if test="${param.currentPage == pageId.index}" var="line">
					<a href="javascript:page(${pageId.index})" class="active">${pageId.index}</a>
				</c:if>
				<c:if test="${!line}">
				   <a href="javascript:page(${pageId.index})">${pageId.index}</a>
				 </c:if>
			</c:forEach>
		</c:when>
		<c:when test="${param.pages > 6}">
			<c:if test="${param.currentPage <= 3}">
				<c:forEach begin="1" step="1" end="4" varStatus="pageId">
					<c:if test="${param.currentPage == pageId.index}" var="line">
					<a href="javascript:page(${pageId.index})" class="active">${pageId.index}</a>
					</c:if>
				   <c:if test="${!line}">
				    <a href="javascript:page(${pageId.index})">${pageId.index}</a>
				   </c:if>
				</c:forEach>
				<a href="javascript:void(0);">...</a>
				<a href="javascript:page(${param.pages - 2})">${param.pages - 2}</a>
				<a href="javascript:page(${param.pages - 1})">${param.pages -1}</a>
				<a href="javascript:page(${param.pages})">${param.pages}</a>
			</c:if>
			<c:if test="${param.currentPage > 3 && param.currentPage < param.pages-2}">
				<a href="javascript:void(0);">...</a>
				<a href="javascript:page(${param.currentPage-2})">${param.currentPage-2}</a>
				<a href="javascript:page(${param.currentPage-1})">${param.currentPage-1}</a>
				<a href="javascript:page(${param.currentPage})" class="active">${param.currentPage}</a>
				<a href="javascript:page(${param.currentPage+1})">${param.currentPage+1}</a>
				<a href="javascript:page(${param.currentPage+2})">${param.currentPage+2}</a>
				<a href="javascript:void(0);">...</a>
			</c:if>
			<c:if test="${param.currentPage >= param.pages-2}">
			    <a href="javascript:page(1)">1</a>
				<a href="javascript:page(2)">2</a>
				<a href="javascript:page(3)">3</a>
				<a href="javascript:void(0);">...</a>
				<c:forEach begin="${param.pages-4}" step="1" end="${param.pages}" varStatus="pageId">
					<c:if test="${param.currentPage == pageId.index}" var="line">
					<a href="javascript:page(${pageId.index})" class="active">${pageId.index}</a>
					</c:if>
					<c:if test="${!line}">
				   		<a href="javascript:page(${pageId.index})">${pageId.index}</a>
				   </c:if>
				</c:forEach>
			</c:if>
		</c:when>
	</c:choose>
	<a class="page_down" href="javascript:page(${param.currentPage+1})"></a>
	<a href="javascript:page(${param.pages})" class="lastPage"></a> 
	
	<input type="hidden" value="${param.pages}" id="totalPages"/>
	<input type="hidden" value="${param.pageSize}" id="size" name="pageSize" />
	<input type="hidden" value="" id="pageInput" name="nextPage" />
	
	<%-- <span>转到：</span>
	<input class="page_in w30" type="text" id="pageInput" name="nextPage" value="${param.currentPage}"
		onkeypress="return checkPage(event)" ondragenter="return false"
		onpaste="return false" style="ime-mode:disabled" size="4" /> 页
	<input type="hidden" value="${param.pages}" id="totalPages"/>
	<input type="hidden" value="${param.size}" id="size" name="pageSize" />
	<a id="goPageBnt" href="javascript:pageBnt();" style="float:right;">确定</a> --%>
  </c:if>
  </div>