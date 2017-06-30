<%@page import="java.util.Arrays"%>
<%@page import="java.util.Map"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
	Map<String, Double[]> json3Map = (Map<String, Double[]>)request.getAttribute("json3Map");
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
	<p>json1: ${json1 }</p>
	<p>json2: ${json2 }</p>
	<p>json3: ${json3 }</p>
	<p>json3: <br>
	<% for(Map.Entry<String, Double[]> entry:json3Map.entrySet()){ %>
		<%=entry.getKey() %> = <%=Arrays.toString(entry.getValue()) %><br>
	<%} %>
	</p>
	
</body>
</html>