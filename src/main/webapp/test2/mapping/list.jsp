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
	<center>请求映射测试（SimpleRestMappingAdapter）</center>
	<hr>
	<ul>
		<li>/test2/mapping/foo --> <a href="/test2/mapping/foo">foo()</a></li>
		<li>/test2/mapping/foo/ --> <a href="/test2/mapping/foo/">foo()</a></li>
		<li>/test2/mapping/bar?id=123&name=jack&sex=1 --> <a href="/test2/mapping/bar?id=123&name=jack&sex=1">bar()</a></li>
		<li>/test2/mapping/bar/?id=123&name=jack&sex=1 --> <a href="/test2/mapping/bar/?id=123&name=jack&sex=1">bar()</a></li>
		<li></li>
		<li>/test2/mapping/testFoo --> <a href="/test2/mapping/testFoo">testFoo()</a></li>
		<li>/test2/mapping/TestFoo --> <a href="/test2/mapping/TestFoo">testFoo()</a></li>
		<li>/test2/mapping/TESTFOO --> <a href="/test2/mapping/TESTFOO">testFoo()</a></li>
		<li></li>
		<li>/test2/mapping/foo_bar --> <a href="/test2/mapping/foo_bar">foo_bar()</a></li>
		<li>/test2/mapping/Foo_Bar --> <a href="/test2/mapping/Foo_Bar">foo_bar()</a></li>
		<li>/test2/mapping/FOO_BAR --> <a href="/test2/mapping/FOO_BAR">foo_bar()</a></li>

		<li></li>
		<li>/test2/mapping/xxx-yyy --> <a href="/test2/mapping/xxx-yyy">ultimate()</a></li>
		<li>/test2/mapping/123_456 --> <a href="/test2/mapping/123_456">ultimate()</a></li>
		<li>/test2/mapping/dfg51234T6eD-skdGE_h762 --> <a href="/test2/mapping/dfg51234T6eD-skdGE_h762">ultimate()</a></li>
		<li></li>
		<li>/test2/mapping/dj-nu12 --> <a href="/test2/mapping/dj-nu12">dj-nu12</a></li>
		<li>/test2/mapping/14523265 --> <a href="/test2/mapping/14523265">14523265</a></li>
		<li>/test2/mapping/SfgerSdSdfef --> <a href="/test2/mapping/SfgerSdSdfef">SfgerSdSdfef</a></li>
		<li>/test2/mapping/125sSdGf541SDF4 --> <a href="/test2/mapping/125sSdGf541SDF4">125sSdGf541SDF4</a></li>
		<li></li>
		<li>/test2/mapping/12345 --> <a href="/test2/mapping/12345">12345</a></li>
		<li>/test2/mapping/12345/ --> <a href="/test2/mapping/12345/">12345</a></li>
		<li>/test2/mapping/abcde --> <a href="/test2/mapping/abcde">abcde</a></li>
		<li>/test2/mapping/abcde/ --> <a href="/test2/mapping/abcde/">abcde</a></li>
	</ul>
</body>
</html>