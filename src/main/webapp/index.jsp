<%@page import="java.util.Date"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
	//System.out.println(new Date());
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
	<center>
	<h1>Jsan 测试样板（当前时间：<%=new Date()%>）</h1>
	</center>
	<hr>
	<ul>
	<li><a target="_blank" href="test2/form/list">表单转换测试</a></li>
	<li><a target="_blank" href="test2/json/list">JSON测试</a></li>
	<li><a target="_blank" href="test2/upload/list">文件上传测试</a></li>
	<li><a target="_blank" href="test2/download/list">文件下载测试</a></li>
	<li><a target="_blank" href="test2/download/list">文件下载测试</a></li>
	<li><a target="_blank" href="test2/cache/list">View缓存测试</a></li>
	<li><a target="_blank" href="test/curd/list">CURD测试</a></li>
	</ul>
</body>
</html>