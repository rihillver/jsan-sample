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
	<center>View缓存测试</center>
	<hr>
	<ul>
		<li><a href="/test2/cache/foo">缓存10秒</a></li>
		<li><a href="/test2/cache/bar">缓存30秒</a></li>
		<li><a href="/test2/cache/baz">不缓存</a></li>
		<li><a href="/test2/cache/qux">指定不缓存</a></li>
		<li><a href="/test2/cache/foobar">不适用使用缓存</a></li>
	</ul>
</body>
</html>