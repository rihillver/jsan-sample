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
表单转Map测试、表单转Bean测试
<hr>
	<form action="" method="post">
	<p>---- user ----</p>
	<p>id:<input type="text" name="id" value="123"></p>
	<p>name:<input type="text" name="name" value="张三"></p>
	<p>sex:<input type="radio" name="sex" value="0">男 <input type="radio" name="sex" value="1">女</p>
	<p>age:<input type="text" name="age" value="28"></p>
	<p>height:<select name="height">
	 	<option></option>
	 	<option value ="1.6">1米6</option>
	  	<option value="1.65">1米65</option>
	  	<option value="1.7">1米7</option>
	  	<option value="1.75">1米75</option>
		<option value ="1.8">1米8</option>
		<option value ="1.85">1米85</option>
		<option value ="1.90">1米9</option>
	</select></p>
	<p>address:<input type="text" name="address" value="中国"></p>
	<p>address:<input type="text" name="address" value="北京市"></p>
	<p>address:<input type="text" name="address" value="中南海"></p>
	<p>address:<input type="text" name="address" value="紫光阁"></p>
	<p>address:<input type="text" name="address"></p>
	
	<p>---- school ----</p>
	<p>number:<input type="text" name="number" value="01"></p>
	<p>school.name:<input type="text" name="school.name" value="北京大学"></p>
	<p>shortName:<input type="text" name="shortName" value="北大"></p>
	<p>school.address:<textarea rows="5" cols="100" name=school.address> 中国 , 北京市          ,海淀区       , 颐和园路     </textarea></p>
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>