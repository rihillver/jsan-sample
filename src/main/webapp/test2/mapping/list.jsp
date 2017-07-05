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
		<li>/test2/mapping/foo --> <a href="/test2/mapping/foo">Index.foo()</a></li>
		<li>/test2/mapping/foo.html --> <a href="/test2/mapping/foo.html">404</a></li>
		<li>/test2/mapping/foo.do --> <a href="/test2/mapping/foo.do">Index.foo()</a></li>
		<li>/test2/mapping/foo.action --> <a href="/test2/mapping/foo.action">404</a></li>
		<li>/test2/mapping/foo/ --> <a href="/test2/mapping/foo/">Index.foo()</a></li>
		<li>/test2/mapping/bar?id=123&name=jack&sex=1 --> <a href="/test2/mapping/bar?id=123&name=jack&sex=1">Index.bar()</a></li>
		<li>/test2/mapping/bar/?id=123&name=jack&sex=1 --> <a href="/test2/mapping/bar/?id=123&name=jack&sex=1">Index.bar()</a></li>
		<li></li>
		<li>/test2/mapping/testFoo --> <a href="/test2/mapping/testFoo">Index.testFoo()</a></li>
		<li>/test2/mapping/TestFoo --> <a href="/test2/mapping/TestFoo">Index.testFoo()</a></li>
		<li>/test2/mapping/TESTFOO --> <a href="/test2/mapping/TESTFOO">Index.testFoo()</a></li>
		<li></li>
		<li>/test2/mapping/foo_bar --> <a href="/test2/mapping/foo_bar">Index.foo_bar()</a></li>
		<li>/test2/mapping/Foo_Bar --> <a href="/test2/mapping/Foo_Bar">Index.foo_bar()</a></li>
		<li>/test2/mapping/FOO_BAR --> <a href="/test2/mapping/FOO_BAR">Index.foo_bar()</a></li>

		<li></li>
		<li>/test2/mapping/xxx-yyy --> <a href="/test2/mapping/xxx-yyy">Index.ultimate():methodValue=xxx-yyy</a></li>
		<li>/test2/mapping/123_456 --> <a href="/test2/mapping/123_456">Index.ultimate():methodValue=123_456</a></li>
		<li>/test2/mapping/dfg51234T6eD-skdGE_h762 --> <a href="/test2/mapping/dfg51234T6eD-skdGE_h762">Index.ultimate():methodValue=dfg51234T6eD-skdGE_h762</a></li>
		<li></li>
		<li>/test2/mapping/dj-nu12 --> <a href="/test2/mapping/dj-nu12">Index.ultimate()</a></li>
		<li>/test2/mapping/14523265 --> <a href="/test2/mapping/14523265">Index.ultimate() - handleNumber()</a></li>
		<li>/test2/mapping/SfgerSdSdfef --> <a href="/test2/mapping/SfgerSdSdfef">Index.ultimate() - handleAlphabet()</a></li>
		<li>/test2/mapping/125sSdGf541SDF4 --> <a href="/test2/mapping/125sSdGf541SDF4">Index.ultimate() - handleNumberAndAlphabet()</a></li>
		<li></li>
		<li>/test2/mapping/12345 --> <a href="/test2/mapping/12345">Index.ultimate() - handleLength()</a></li>
		<li>/test2/mapping/12345/ --> <a href="/test2/mapping/12345/">Index.ultimate() - handleLength()</a></li>
		<li>/test2/mapping/abcde --> <a href="/test2/mapping/abcde">Index.ultimate() - handleLength()</a></li>
		<li>/test2/mapping/abcde/ --> <a href="/test2/mapping/abcde/">Index.ultimate() - handleLength()</a></li>
	</ul>
</body>
</html>