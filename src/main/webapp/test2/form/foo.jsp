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
常用表单提交测试
<hr>
	<form action="" method="post">
	<p>id:<input type="text" name="id" value="123"></p>
	<p>name:<input type="text" name="name" value="张三"></p>
	<p>sex:<input type="radio" name="sex" value="0">男 <input type="radio" name="sex" value="1">女</p>
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

	<p>interest:
	<input type="checkbox" name="interest" value="足球" checked="checked">足球
	<input type="checkbox" name="interest" value="篮球" checked="checked">篮球
	<input type="checkbox" name="interest" value="羽毛球" checked="checked">羽毛球
	<input type="checkbox" name="interest" value="乒乓球">乒乓球
	<input type="checkbox" name="interest" value="棒球">棒球
	<input type="checkbox" name="interest" value="橄榄球">橄榄球
	</p>
	
	<p>like:<textarea rows="5" cols="100" name="like"> 读书 , 看 报纸          ,下棋       , 音  乐,       跳舞      , 看电影      </textarea></p>
	<p>score:<textarea rows="5" cols="100" name="score">123,144, 255 ,366 ,477, 588 </textarea></p>
	<p>family:<textarea rows="5" cols="100" name="family">爷爷: 张二,父亲 : 张三 , 母亲 :李芳, 哥哥:张五, 姐姐: 张艳, 弟弟 : 张六</textarea></p>
	<p>jsonArray:<textarea rows="5" cols="100" name="jsonArray"> [123,456.01,789.0 ,100, 120] </textarea></p>
	<p>jsonObject:<textarea rows="5" cols="100" name="jsonObject"> {"id":123,"name":"张三","sex":1,"age":28,"address":["中国","北京市","清华大学"]}</textarea></p>
	
	<hr>
	<p><input type="submit" value="提交"></p>
	</form>
</body>
</html>