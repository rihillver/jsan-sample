<%@page import="java.util.Date"%>
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
最多允许上传3个文件，单个文件最大2M
<hr>
	<form action="" method="post" enctype="multipart/form-data">
	<p>id:<input type="text" name="id"></p>
	<p>name:<input type="text" name="name"></p>
	<p>sex:<input type="radio" name="sex" value="0">男 <input type="radio" name="sex" value="1">女</p>
	
	<p>address:<input type="text" name="address"></p>
	<p>address:<input type="text" name="address"></p>
	<p>address:<input type="text" name="address"></p>
	<p>address:<input type="text" name="address"></p>
	<p>address:<input type="text" name="address"></p>
	
	<p>file1:<input type="file" name="file1"></p>
	<p>file2:<input type="file" name="file2"></p>
	<p>file3:<input type="file" name="file3"></p>
	<p>file4:<input type="file" name="file4"></p>
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>