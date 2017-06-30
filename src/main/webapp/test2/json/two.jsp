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
JSON转Map
<hr>
	<form action="" method="post">
	
	<p>json1:<textarea rows="5" cols="100" name="json1">{1:"a",2:"b",3:"c",4:"d"}</textarea></p>
	<p>json2:<textarea rows="5" cols="100" name="json2">{"a":["2001年1月11日","2002年2月22日"],"b":["2003-03月13日","2004年04月14日","2012年12月12日"]}</textarea></p>
	<p>json3:<textarea rows="5" cols="100" name="json3">{"张三":[true,false,3,4,5,6],"李四":[1.01,2.02,3.03],"王五":[1.111,2.222,3.333,4.444,5.555,6.666]}</textarea></p>
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>