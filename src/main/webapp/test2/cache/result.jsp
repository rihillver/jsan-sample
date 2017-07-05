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
	result
	<hr>
	<p>one（缓存30秒）: ${one }</p>
	<p>two（缓存30秒）: ${two }</p>
	<p>three（不缓存）: ${three.randomStr }</p>
	<hr>
	<p>执行耗时：${etime }</p>
</body>
</html>