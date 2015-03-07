<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>aaa</title>
</head>
<body>
	<div class="wrapper">
		<div style="height:180px;width:650px;padding-top:100px;padding-bottom:100px;margin:0px auto;">
			<img src="${basepath}/resources/images/common/registeFailure.jpg"
				style="float:left;border:0px;margin-top:8px;">
			<div style="width:400px;float:left;margin-top:20px;margin-left:10px;">
				<p style="font-size:18px; font-family:'微软雅黑';color:#C31202;font-weight:bold;">无法找到该网页！</p>
				<p style="font-size:14px;color:#4D4D4D;margin-top:10px;">
					抱歉！服务器出错
				</p>
				<p style="color:#4D4D4D;margin-top:8px;font-size:13px;"> 尊敬的用户，由于服务器出错，对您造成的困扰我们感到很抱歉，我们会尽快解决，希望您见谅！</p>
				<p style="margin-top:15px;">
					<a href='javascript:history.back();'>【返回上页】</a>
					<a href='${basepath}/common/navigate/navg-indx.html'>【返回首页】</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
