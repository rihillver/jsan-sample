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
<style type="text/css">
	input {width: 300px}
</style>
</head>

<body>
日期时间、数字格式测试
<hr>
	<form action="" method="post">
	<p>date:<input type="text" name="date" value="2008-05-16 10:20:30"></p>
	<p>date0:<input type="text" name="date0" value="2008-05-16"></p>
	<p>date1:<input type="text" name="date1" value="2001年6月18日"></p>
	<p>date2:<input type="text" name="date2" value="2001年6月18日 11时22分33秒"></p>
	<p>date3:<input type="text" name="date3" value="2018#11#30"></p>
	<p>calendar:<input type="text" name="calendar" value="2008-05-16 10:20:30.888"></p>
	<p>&nbsp;<p>
	<p>number:<input type="text" name="number" value="123"></p>
	<p>number1:<input type="text" name="number1" value="123.45"></p>
	<p>number2:<input type="text" name="number2" value="1,234,567.15"></p>
	<p>number3:<input type="text" name="number3" value="00005.67"></p>
	<p>number4:<input type="text" name="number4" value="1.23E12"></p>
	<p>number5:<input type="text" name="number5" value="20.58%"></p>
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>