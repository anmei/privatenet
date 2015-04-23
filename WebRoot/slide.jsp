<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'slide.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		base = '<%=path %>';
	</script>
	<script type="text/javascript" src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/js/jquery-1.6.4.min.js"></script>
	<script type="text/javascript" src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/js/jquery.lightbox.js"></script>
	<link rel="stylesheet" href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/css/lightbox.css" type="text/css" media="screen" />
	<script type="text/javascript">
		$(document).ready(function(){
			/* $(".lightbox").lightbox({
			    fitToScreen: true,
			    imageClickClose: false
		    }); */
			$(".lightbox-2").lightbox({
			    fitToScreen: true,
			    scaleImages: true,
			    xScale: 1.2,
			    yScale: 1.2,
			    displayDownloadLink: false,
				imageClickClose: false
		    });
		});
	</script>
  </head>
  
  <body>
	<h2>我的照片</h2>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/c.jpg" class="lightbox-2" rel="flowers" title="l">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/c.jpg" alt="i" height="40" />
	</a>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/b.jpg" class="lightbox-2" rel="flowers" title="o">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/b.jpg" alt="i" height="40" />
	</a>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/d.jpg" class="lightbox-2" rel="flowers" title="v">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/d.jpg" alt="i" height="40" />
	</a>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/e.jpg" class="lightbox-2" rel="flowers" title="e">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/e.jpg" alt="i" height="40" />
	</a>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/f.jpg" class="lightbox-2" rel="flowers" title="y">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/f.jpg" alt="i" height="40" />
	</a>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/a.jpg" class="lightbox-2" rel="flowers" title="o">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/a.jpg" alt="i" height="40" />
	</a>
	<a href="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/g.jpg" class="lightbox-2" rel="flowers" title="u">
	  <img src="<%=path %>/resource/krewenki-jquery-lightbox-3eba280/images/me/g.jpg" alt="i" height="40" />
	</a>
  </body>
</html>
