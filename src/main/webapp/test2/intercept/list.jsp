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
	<center>拦截器测试</center>
	<hr>
	<ul>
		<li><a href="/test2/intercept/foo">测试1</a></li>
		<li><a href="/test2/intercept/bar?id=123">测试2</a></li>
		<li>&nbsp;</li>
		<li><a href="/test2/intercept/baz">测试3</a></li>
		<li><a href="/test2/intercept/baz?id=-1">测试4</a></li>
		<li><a href="/test2/intercept/baz?id=100">测试5</a></li>
		<li><a href="/test2/intercept/baz?id=0">测试6</a></li>
		<li>&nbsp;</li>
		<li><a href="/test2/intercept/qux?id=123&name=jack">测试6</a></li>
	</ul>
</body>
</html>