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
	<center>请求映射测试（StandardMappingAdapter）参数方法键为“med”</center>
	<hr>
	<ul>
		<li>news --> <a href="news">News.index()</a></li>
		<li>news.html --> <a href="news.html">News.index()</a></li>
		<li>news.do --> <a href="news.do">News.index()</a></li>
		<li>news.action --> <a href="news.action">News.index()</a></li>
		<li>news.action?med=index --> <a href="news.action?med=index">News.index()</a></li>
		<li></li>
		<li>news?med=foo --> <a href="news?med=foo">News.foo()</a></li>
		<li>news.html?med=bar --> <a href="news.html?med=bar">News.bar()</a></li>
		<li>news.do?med=baz --> <a href="news.do?med=baz">News.baz()</a></li>
		<li>news.action?med=qux --> <a href="news.action?med=qux">News.qux()</a></li>
		<li></li>
		<li>news?med=xxx-yyy --> <a href="news?med=xxx-yyy">News.ultimate()</a></li>
		<li>news?med=123_456 --> <a href="news?med=123_456">News.ultimate()</a></li>
		<li>news?med=dfg51234T6eD-skdGE_h762 --> <a href="news?med=dfg51234T6eD-skdGE_h762">News.ultimate()</a></li>
		<li></li>
		<li>news?id=123&name=jack&sex=1 --> <a href="news?id=123&name=jack&sex=1">News.index()</a></li>
		<li>news?med=foo&id=123&name=jack&sex=1 --> <a href="news?med=foo&id=123&name=jack&sex=1">News.foo()</a></li>
		<li></li>
		<li></li>
		<li>sports --> <a href="sports">Sports.ultimate()</a></li>
		<li>sports.html --> <a href="sports.html">Sports.ultimate()</a></li>
		<li>sports.do --> <a href="sports.do">Sports.ultimate()</a></li>
		<li>sports.action --> <a href="sports.action">Sports.ultimate()</a></li>
		<li>sports?name=san&age=28 --> <a href="sports?name=san&age=28">Sports.ultimate()</a></li>
		<li>sports?med=index --> <a href="sports?med=index">Sports.ultimate()</a></li>
		<li>sports?med=foo --> <a href="sports?med=foo">Sports.ultimate()</a></li>
		<li>sports?med=bar --> <a href="sports?med=bar">Sports.ultimate()</a></li>
		<li>sports?med=qux --> <a href="sports?med=qux">Sports.qux()</a></li>
		<li>sports?med=qux&id=123 --> <a href="sports?med=qux&id=123">Sports.qux()</a></li>
		<li></li>
		
		
		<li>testFoo --> <a href="testFoo">TestFoo.index()</a></li>
		<li>TestFoo --> <a href="TestFoo">TestFoo.index()</a></li>
		<li>TESTFOO --> <a href="TESTFOO">TestFoo.index()</a></li>
		<li></li>
		<li>foo_bar --> <a href="foo_bar">Foo_bar.ultimate()</a></li>
		<li>Foo_Bar --> <a href="Foo_Bar">Foo_bar.ultimate()</a></li>
		<li>FOO_BAR --> <a href="FOO_BAR">Foo_bar.ultimate()</a></li>

		<li></li>
		<li>finance --> <a href="finance">Finance.index()</a></li>
		<li>finance?med=foo --> <a href="finance?med=foo">Finance.foo()</a></li>
		<li>finance?med=abcd --> <a href="finance?med=abcd">404</a></li>
		<li>finance/ --> <a href="finance/">(finance/)Index.index()</a></li>
		<li>finance/?med=bar --> <a href="finance/?med=bar">(finance/)Index.bar()</a></li>
		<li>finance/?med=abcd --> <a href="finance/?med=abcd">404</a></li>
		<li></li>
		<li>fashion --> <a href="fashion">Fashion.ultimate()</a></li>
		<li>fashion?med=baz --> <a href="fashion?med=baz">Fashion.baz()</a></li>
		<li>fashion?med=abcd --> <a href="fashion?med=abcd">Fashion.ultimate()</a></li>
		<li>fashion/ --> <a href="fashion/">(fashion/)Index.ultimate()</a></li>
		<li>fashion/?med=qux --> <a href="fashion/?med=qux">(fashion/)Index.qux()</a></li>
		<li>fashion/?med=abcd --> <a href="fashion/?med=abcd">(fashion/)Index.ultimate()</a></li>
		
		<li></li>
		<li>fashion?id=123 --> <a href="fashion?id=123">Fashion.ultimate()</a></li>
		<li>fashion?med=baz&id=123 --> <a href="fashion?med=baz&id=123">Fashion.baz()</a></li>
		<li>fashion?med=abcd&id=123 --> <a href="fashion?med=abcd&id=123">Fashion.ultimate()</a></li>
		<li>fashion/?id=123 --> <a href="fashion/?id=123">(fashion/)Index.ultimate()</a></li>
		<li>fashion/?med=qux&id=123 --> <a href="fashion/?med=qux&id=123">(fashion/)Index.qux()</a></li>
		<li>fashion/?med=abcd&id=123 --> <a href="fashion/?med=abcd&id=123">(fashion/)Index.ultimate()</a></li>
	</ul>
</body>
</html>