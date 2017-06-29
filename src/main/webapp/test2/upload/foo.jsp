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
${value}
<hr>
	<form action="" method="post" enctype="multipart/form-data">
	<p>id:<input type="text" name="id" value="123"></p>
	<p>name:<input type="text" name="name" value="张三"></p>
	<p>sex:<input type="radio" name="sex" value="0">男 <input type="radio" name="sex" value="1">女</p>
	
	<p>address:<input type="text" name="address" value="中国"></p>
	<p>address:<input type="text" name="address" value="北京市"></p>
	<p>address:<input type="text" name="address" value="中南海"></p>
	<p>address:<input type="text" name="address" value="紫光阁"></p>
	<p>address:<input type="text" name="address"></p>
	
	<p>file1:<input type="file" name="file1"></p>
	<p>file2:<input type="file" name="file2"></p>
	<p>file3:<input type="file" name="file3"></p>
	<p>file4:<input type="file" name="file4"></p>
	<p>file5:<input type="file" name="file5"></p>
	<p>file6:<input type="file" name="file6"></p>
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>