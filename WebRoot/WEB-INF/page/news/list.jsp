<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ include file="/WEB-INF/page/common/rhcbase.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>私人定制  - 最懂你</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!-- self-adaption -->
	<meta name="viewport" content="width=device-width, initial-scale=1,maximum-scale=1.0, user-scalable=0" />
	<style type="text/css">
		img, object { width: 100%;}
	</style>
	<!-- / -->
	<link href="${base }/resource/slider3/5icool.org.css" rel="stylesheet" type="text/css">
	<style type="text/css">
		body{margin: 0;padding: 0;}
		
		/*导航*/
		.header{
			background-color: #FF8800;line-height: 45px;text-align: center;color: #FFFFFF;font-weight: bolder;font-size: 18px;
		}
		.subnav{
			color: #262b31;font-size: 18px;line-height:26px; margin:10px 5px 5px 5px;
		}
		.subnav a{
			padding: 0 7px 0 7px;display: inline-block;
			/* border-right: 1px solid #b8bac0; */
			/*border-bottom: 1px solid #FF8800;color: #FF8800; */
		}
		
		
		/*滚动图片*/
		
		
		/*资讯内容*/
		a{color: #111;text-decoration: none;}
		.newslist ul li{list-style-type:none;margin-left: -2em;margin-right:0.5em;margin-top: 1em;border-bottom: 1px solid #f6f6f6;}
		
		.imgc{
			display:inline-block;*display:inline;*zoom:1;vertical-align:top; margin-right: 5px;
		}
		.imglist{
			display: inline-block;
			margin-left: 0px;
		}
		/*.imglist img{width:7em;height:4.5em;}*/
		/* .imglist img{height: 78px;width: 112px;} */
		.imglist img{height: 62px;width: 62px;}
		
		.rightc{
			display: inline-block;*display:inline;*zoom:1;vertical-align:top;margin-top: -3px;margin-left: -1.5px;
		}
		.titlec{
			color: #262b31;
		    font-size: 16px;
		    line-height: 22px;
		    max-height: 44px;
		    _height:expression((document.documentElement.clientHeight||document.body.clientHeight)<44?"44px":"");
		    overflow: hidden;
		}
		.date{
			color: #b8bac0;
		    font-size: 12px;
		    font-weight: 400;
		    line-height: 22px;
		   	max-height: 22px;
		    _height:expression((document.documentElement.clientHeight||document.body.clientHeight)<22?"22px":"");
		    overflow: hidden;
		}
		
		/* .nav span{
			display:inline-block;
			font-size: 1.7em;
			margin-bottom: 0.5em;
		} */
		
		
		
		.footer{
			background-color: #EBEDEF;border:1px solid #EBEDEF;line-height: 45px;text-align: center;
			color: #FF8800;font-weight: bolder;font-size: 18px;
			display: block;
		}
		
	</style>

  </head>
  
  <body>
  	
  	<!-- 导航 -->
  	<div class="nav">
  		<div class="header">
  			<a href="${base }/news/list" style="color: #FFFFFF;">私人定制</a>
  		</div>
  		<div class="subnav">
		  	<a name="dgnewsabs" href="${base }/news/list?tableName=dgnewsabs"><span>东莞</span></a>
		  	<a name="" href="javascript:void(0);"><span>财经</span></a>
		  	<a name="" href="javascript:void(0);"><span>互联网</span></a>
		  	<a name="" href="javascript:void(0);"><span>服务器</span></a>
		  	<a name="" href="javascript:void(0);"><span>文史</span></a>
		  	<a name="" href="javascript:void(0);"><span>法律</span></a>
		  	<a name="ylnewsabs" href="${base }/news/list?tableName=ylnewsabs"><span>娱乐</span></a>
		  	<a name="" href="javascript:void(0);"><span>体育</span></a>
	  	</div>
  	</div>
  	
  	
  	
  	<%-- <div>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="page(${newsabs.toPage-1})">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="page(${newsabs.toPage+1})">下一页</a></div> --%>
  	<!-- 滚动 -->
	<div class="section-focus-pic" id="section-focus-pic">
	  	<div class="pages" data-scro="list" log-type="toppic">
			<ul>
				<li class="item">
					<a href="${base }/news/list" target="_blank" title="天安门广场&quot;史上最大花果篮&quot;竣工" style="height:200px;overflow: hidden;position: relative;">
					<img src="${base}/resource/slidebox/uploads/1.jpg" height="200px"></a>
					<h3><a href="${base }/news/list" target="_blank" style="color: #FFF; text-decoration:none;">天安门广场"史上最大花果篮"竣工</a></h3>
					<div></div>
				</li>
				<li class="item">
					<a href="${base }/news/list" target="_blank" title="广州:10万元得来一座&quot;圆大厦&quot;" style="height:200px;overflow: hidden;position: relative;">
					<img src="${base}/resource/slidebox/uploads/2.jpg" height="200px"></a>
					<h3><a href="${base }/news/list" target="_blank" style="color: #FFF; text-decoration:none;">广州:10万元得来一座"圆大厦"</a></h3>
					<div></div>
				</li>
				<li class="item" style="left: 0px;">
					<a href="${base }/news/list" target="_blank" title="百度视频移动端亿万用户的背后" style="height:200px;overflow: hidden;position: relative;">
					<img src="${base}/resource/slidebox/uploads/1.jpg" height=200px"></a>
					<h3><a href="${base }/news/list" target="_blank" style="color: #FFF; text-decoration:none;">百度视频移动端亿万用户的背后</a></h3>
					<div></div>
				</li>
			</ul>
		</div>
		<div style="display:none;" class="controler" data-scro="controler"><b class="">1</b><b class="">2</b><b class="">3</b></div>
		<div class="controler2" data-scro="controler2">
			<a href="javascript:;" class="prev"><i></i></a>
			<a href="javascript:;" class="next"><i></i></a>
		</div>
	</div>
  	
  	
  	
  	<!-- 正文 -->
  	<div class="newslist">
  	<ul> 
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
   		
   		
   			<%-- <a href="javascript:void(0);" onclick="newsdetail('${newslist.url}','${newslist.absid }')">
   			<c:if test="${fn:length(newslist.imglist)>0}">
   			<div class="imgc">
	   			<c:forEach var="img" items="${newslist.imglist }" varStatus="c">
	    			<c:if test="${c.count <=1}">
						<span class="imglist"><img src="${img}"/></span>    			
	    			</c:if>
	   			</c:forEach>
   			</div>
   			</c:if>
   			
   			<div class="rightc">
	   			<div class="titlec">${newslist.title }</div>
	   			<div class="date">${newslist.auth}&nbsp;<fmt:formatDate value="${newslist.date}" type="both"/></div>
   			</div>
   			</a> --%>
   			
   		</li>
    </c:forEach>
    </ul>
    </div> 
    
    <!-- 分页 -->
    <%-- <div>&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="page(${newsabs.toPage-1})">上一页</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0);" onclick="page(${newsabs.toPage+1})">下一页</a></div> --%>
    <a class="footatag" href="javascript:void(0);" onclick="getMore()"><span class="footer">点击加载更多</span></a>
    <input style="display: none;" id="toPage" type="text" value="1"/>
    <input style="display: none;" id="totalPage" type="text" value="${newsabs.totalPage}"/>
    
  </body>
  
  <script type="text/javascript" src="${base }/resource/slider3/5icool.org.js"></script>
  <script type="text/javascript">
  		var base = '${base}';
  		var tn = '${tableName}';
  		function newsdetail(url,absid){
  			/* window.open(base+"/news/detail?url="+url+"&tableName="+tn); */
  			window.open(base+"/news/detail?absid="+absid+"&tableName="+tn);
  		}
  		
  		function page(topage){
  			window.location=base+"/news/list?toPage="+topage+"&tableName="+tn;
  		}
  		
  		$(".subnav a[name="+tn+"]").css({"border-bottom":"1px solid #FF8800","color":"#FF8800"});
  		
		function getMore(){
			if(parseInt($("#toPage").val()) < parseInt($("#totalPage").val())){
				var topa = (parseInt($("#toPage").val())+1);
				$("#toPage").val(topa);
				$.ajax({  
				    type: "POST",  
				    dataType: "html",
				    data:{toPage:topa,tableName:tn},
				    url: base+"/news/getMoreList",  
				    success: function (data) {
				     	$(".newslist ul").append(data);
				     	if(parseInt($("#toPage").val()) == parseInt($("#totalPage").val())){
				     		$(".footatag").removeAttr("onclick");
				     		$(".footer").text("没有更多啦");
				     	}
				    },  
				    error: function (XMLHttpRequest, textStatus, errorThrown) {
				    	alert("error");
				    }  
				}); 
			}
			
		}
		
		$(function(){
			if(parseInt($("#toPage").val()) >= parseInt($("#totalPage").val())){
				$(".footatag").removeAttr("onclick");
				$(".footer").text("没有更多啦");
			}
		});
		
		
  </script>
  
</html>
