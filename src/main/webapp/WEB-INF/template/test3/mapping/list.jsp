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
	<center>请求映射测试（StrictSimpleRestMappingAdapter）</center>
	<p>StrictSimpleRestMappingAdapter 与 SimpleRestMappingAdapter 的主要区别是严格的区别请求路径尾部的反斜杠“/”，http://www.abc.com/test 与  http://www.abc.com/test/ 将被映射到不通的控制器，优点是在视图上的链接可以使用相对路径。</p>
	<hr>
	<ul>
		<li>foo --> <a href="foo">Index.foo()</a></li>
		<li>foo.html --> <a href="foo.html">404</a></li>
		<li>foo.do --> <a href="foo.do">404</a></li>
		<li>foo.action --> <a href="foo.action">404</a></li>
		<li>foo/ --> <a href="foo/">(foo/)Index.index()</a></li>
		<li>bar?id=123&name=jack&sex=1 --> <a href="bar?id=123&name=jack&sex=1">Index.bar()</a></li>
		<li>bar/?id=123&name=jack&sex=1 --> <a href="bar/?id=123&name=jack&sex=1">(bar/)Index.ultimate()</a></li>
		<li></li>
		<li>testFoo --> <a href="testFoo">Index.testFoo()</a></li>
		<li>TestFoo --> <a href="TestFoo">Index.testFoo()</a></li>
		<li>TESTFOO --> <a href="TESTFOO">Index.testFoo()</a></li>
		<li></li>
		<li>foo_bar --> <a href="foo_bar">Index.foo_bar()</a></li>
		<li>Foo_Bar --> <a href="Foo_Bar">Index.foo_bar()</a></li>
		<li>FOO_BAR --> <a href="FOO_BAR">Index.foo_bar()</a></li>

		<li></li>
		<li>xxx-yyy --> <a href="xxx-yyy">Index.ultimate():methodValue=xxx-yyy</a></li>
		<li>123_456 --> <a href="123_456">Index.ultimate():methodValue=123_456</a></li>
		<li>dfg51234T6eD-skdGE_h762 --> <a href="dfg51234T6eD-skdGE_h762">Index.ultimate():methodValue=dfg51234T6eD-skdGE_h762</a></li>
		<li></li>
		<li>dj-nu12 --> <a href="dj-nu12">Index.ultimate()</a></li>
		<li>14523265 --> <a href="14523265">Index.ultimate() - handleNumber()</a></li>
		<li>SfgerSdSdfef --> <a href="SfgerSdSdfef">Index.ultimate() - handleAlphabet()</a></li>
		<li>125sSdGf541SDF4 --> <a href="125sSdGf541SDF4">Index.ultimate() - handleNumberAndAlphabet()</a></li>
		<li></li>
		<li>12345 --> <a href="12345">Index.ultimate() - handleLength()</a></li>
		<li>12345/ --> <a href="12345/">404</a></li>
		<li>abcde --> <a href="abcde">Index.ultimate() - handleLength()</a></li>
		<li>abcde/ --> <a href="abcde/">404</a></li>
	</ul>
</body>
</html>