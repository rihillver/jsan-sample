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
JSON转数组、集合
<hr>
	<form action="" method="post">
	<p>id:<input type="text" name="id" value="123"></p>
	<p>name:<input type="text" name="name" value="张三"></p>
	<p>sex:<input type="radio" name="sex" value="0">男 <input type="radio" name="sex" value="1" checked="checked">女</p>
	
	<p>json1:<textarea rows="5" cols="100" name="json1">[1.12,8.125,9.16,6.20,5.05]</textarea></p>
	<p>json2:<textarea rows="5" cols="100" name="json2">["张三","李四",123.5689, 2000, true, "8888", "国家"]</textarea></p>
	<p>json3:<textarea rows="5" cols="100" name="json3">["张三","李四","王五","赵六","唐七","宋八"]</textarea></p>
	<p>json4:<textarea rows="5" cols="100" name="json4">[[1,2,3,4,5,6],[11,22,33,44],[10,20,30,40,50],[1.11,2.22,3.33]]</textarea></p>
	<p>json5:<textarea rows="5" cols="100" name="json5">[[1,2,3,4,5,6],[11.01,22.02,33.03,44.04],[10,20,30,40,50],[1.11,2.22,3.33]]</textarea></p>
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>