<%@page import="java.util.Arrays"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
	double[][] json5Array = (double[][])request.getAttribute("json5Array");
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
	<p>id: ${id}</p>
	<p>name: ${name }</p>
	<p>sex: ${sex }</p>
	<p>json1: ${json1 }</p>
	<p>json2: ${json2 }</p>
	<p>json3: ${json3 }</p>
	<p>json4: ${json4 }</p>
	<p>json5: ${json5 }</p>
	<p>json5: <br>
	<% for(double[] ds: json5Array){ %>
		<%=Arrays.toString(ds) %><br>
	<%} %>
	</p>
</body>
</html>