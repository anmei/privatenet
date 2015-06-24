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
    
    <title>My JSP 'webSocketChart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		var socket;
		if(!window.WebSocket){
			window.WebSocket=window.MozWebSocket;
		}
		if(window.WebSocket){
			socket = new WebSocket("ws://localhost:8080/websocket");
			socket.onmessage = function(event){	
				$("#rewponseTest").val(event.data);
			};
			socket.onopen = function(event){
				$("#rewponseTest").val("开启websocket");
			};
			socket.onclose = function(event){
				$("#rewponseTest").val("关闭websocket");
			}
			
			
		}else{
			alert("您的浏览器不支持websocket协议")
		}
		
		function send(){
			if(!window.WebSocket){return;}
			if(socket.readyState == WebSocket.OPEN){
				socket.send($("#requestText").val());
			}else{
				alert("没有建立websocet连接");
			}
		}
	
	</script>
  </head>
  
  <body>
  	<div style="margin-left: 350;">
    websocket demo. <br>
    <textarea rows="15" cols="77" id="rewponseTest"></textarea><br>
    <textarea rows="2" cols="77" id="requestText"></textarea><br>
    <button onclick="send()">发送</button>
    
    </div>
    
    
  </body>
</html>
