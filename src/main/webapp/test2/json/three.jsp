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
JSON转Bean
<hr>
	<form action="" method="post">
	
	<p>user:<textarea rows="5" cols="100" name="user">{"id":123,"name":"张三","nick_name":"小三","height":1.75,"weight":"55kg","cellphone":13912345678,"sex":true,"age":30,"birth":"2000#10#20","regtime":"2000年12月12日","addresses":["北京市","上海市","广州市","深圳市"],"school":{"number":"1","name":"北京大学","Short_Name":"北大","grade":"ONE","address":"北京市海淀区"}}</textarea></p>
	<p>school:<textarea rows="5" cols="100" name="school">{"number":"2","name":"清华大学","short_name":"清华","grade":"TWO","address":"北京市海淀区"}</textarea></p>
	
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>