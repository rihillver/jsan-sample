<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
	
%>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=Edge">
<meta name="renderer" content="webkit">
<title></title>
</head>

<body>
	<center>JSON测试</center>
	<hr>
	<ul>
		<li><a href="/test2/json/one">JSON --> 数组、集合</a></li>
		<li><a href="/test2/json/two">JSON --> Map</a></li>
		<li><a href="/test2/json/three">JSON --> Bean</a></li>
		<li><a href="/test2/json/four?type=array">array --> JSON</a></li>
		<li><a href="/test2/json/four?type=list">list --> JSON</a></li>
		<li><a href="/test2/json/four?type=set">set --> JSON</a></li>
		<li><a href="/test2/json/four?type=collection">collection --> JSON</a></li>
		<li><a href="/test2/json/five">Map --> JSON</a></li>
		<li><a href="/test2/json/six">Bean --> JSON</a></li>
		<li><a href="/test2/json/seven">JSONP测试1</a></li>
		<li><a href="/test2/json/eight?callback=myCallback">JSONP测试2</a></li>
	</ul>
</body>
</html>