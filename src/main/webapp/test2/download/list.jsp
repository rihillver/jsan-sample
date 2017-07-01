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
	<center>文件下载测试</center>
	<hr>
	<ul>
		<li><a href="foo?file=a.docx">a.docx 下载</a></li>
		<li><a href="foo?file=b.docx">b.docx 下载</a></li>
		<li><a href="foo?file=a.jpg">jpg 下载</a></li>
		<li><a href="foo?file=a.png">png 下载</a></li>
		<li><a href="foo?file=a.gif">gif 下载</a></li>
		<li><a href="foo?file=a.bmp">bmp 下载</a></li>
		<li><a href="foo?file=a.rar">rar 下载</a></li>
		<li><a href="bar">rar 下载（随机文件名）</a></li>
		
	</ul>
</body>
</html>