<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!--无缓存，必须从服务器重新获取，防止回退产生的信息差错  -->
<% response.setHeader("Cache-Control","no-store");%>

<!-- 设置全局参数 -->
<%-- <c:set var="ctx" value="${pageContext.request.contextPath }"/> --%><!-- 工程根目录 -->
<%-- <c:set var="amountPerBag" value="100"></c:set> --%><!-- 每个卡包中卡的数据 -->

<!-- 引入相关script -->
<script type="text/javascript">
	if(!window.base)window.basepath = "${base}";
	if(!window.imgbase)window.imgPath = "${imgbase}";
</script>
<script src="${base}/resource/js/jquery-1.7.2.min.js" type="text/javascript"></script>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">

