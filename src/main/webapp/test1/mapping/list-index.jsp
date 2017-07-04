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
	<center>请求映射测试（StandardMappingAdapter）方法分隔符为“-”</center>
	<hr>
	<ul>
		<li>news --> <a href="news">News.index()</a></li>
		<li>news.html --> <a href="news.html">News.index()</a></li>
		<li>news.do --> <a href="news.do">News.index()</a></li>
		<li>news.action --> <a href="news.action">News.index()</a></li>
		<li>news-index.action --> <a href="news-index.action">News.index()</a></li>
		<li></li>
		<li>news-foo --> <a href="news-foo">News.foo()</a></li>
		<li>news-bar.html --> <a href="news-bar.html">News.bar()</a></li>
		<li>news-baz.do --> <a href="news-baz.do">News.baz()</a></li>
		<li>news-qux.action --> <a href="news-qux.action">News.qux()</a></li>
		<li></li>
		<li>news-xxx-yyy --> <a href="news-xxx-yyy">News.ultimate()</a></li>
		<li>news-123_456 --> <a href="news-123_456">News.ultimate()</a></li>
		<li>news-dfg51234T6eD-skdGE_h762 --> <a href="news-dfg51234T6eD-skdGE_h762">News.ultimate()</a></li>
		<li></li>
		<li>news?id=123&name=jack&sex=1 --> <a href="news?id=123&name=jack&sex=1">News.index()</a></li>
		<li>news-foo?id=123&name=jack&sex=1 --> <a href="news-foo?id=123&name=jack&sex=1">News.foo()</a></li>
		<li></li>
		<li></li>
		<li>sports --> <a href="sports">Sports.ultimate()</a></li>
		<li>sports.html --> <a href="sports.html">Sports.ultimate()</a></li>
		<li>sports.do --> <a href="sports.do">Sports.ultimate()</a></li>
		<li>sports.action --> <a href="sports.action">Sports.ultimate()</a></li>
		<li>sports?name=san&age=28 --> <a href="sports?name=san&age=28">Sports.ultimate()</a></li>
		<li>sports-index --> <a href="sports-index">Sports.ultimate()</a></li>
		<li>sports-foo --> <a href="sports-foo">Sports.ultimate()</a></li>
		<li>sports-bar --> <a href="sports-bar">Sports.ultimate()</a></li>
		<li>sports-qux --> <a href="sports-qux">Sports.qux()</a></li>
		<li>sports-qux?id=123 --> <a href="sports-qux?id=123">Sports.qux()</a></li>
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
		<li>finance-foo --> <a href="finance-foo">Finance.foo()</a></li>
		<li>finance-abcd --> <a href="finance-abcd">404</a></li>
		<li>finance/ --> <a href="finance/">(finance/)Index.index()</a></li>
		<li>finance/index-bar --> <a href="finance/index-bar">(finance/)Index.bar()</a></li>
		<li>finance/index-abcd --> <a href="finance/index-abcd">404</a></li>
		<li></li>
		<li>fashion --> <a href="fashion">Fashion.ultimate()</a></li>
		<li>fashion-baz --> <a href="fashion-baz">Fashion.baz()</a></li>
		<li>fashion-abcd --> <a href="fashion-abcd">Fashion.ultimate()</a></li>
		<li>fashion/ --> <a href="fashion/">(fashion/)Index.ultimate()</a></li>
		<li>fashion/index-qux --> <a href="fashion/index-qux">(fashion/)Index.qux()</a></li>
		<li>fashion/index-abcd --> <a href="fashion/index-abcd">(fashion/)Index.ultimate()</a></li>
		
		<li></li>
		<li>fashion?id=123 --> <a href="fashion?id=123">Fashion.ultimate()</a></li>
		<li>fashion-baz?id=123 --> <a href="fashion-baz?id=123">Fashion.baz()</a></li>
		<li>fashion-abcd?id=123 --> <a href="fashion-abcd?id=123">Fashion.ultimate()</a></li>
		<li>fashion/?id=123 --> <a href="fashion/?id=123">(fashion/)Index.ultimate()</a></li>
		<li>fashion/index-qux?id=123 --> <a href="fashion/index-qux?id=123">(fashion/)Index.qux()</a></li>
		<li>fashion/index-abcd?id=123 --> <a href="fashion/index-abcd?id=123">(fashion/)Index.ultimate()</a></li>
	</ul>
</body>
</html>