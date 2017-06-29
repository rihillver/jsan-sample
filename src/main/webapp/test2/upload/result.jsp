<%@page import="java.util.List"%>
<%@page import="com.jsan.util.upload.FileInfo"%>
<%@page language="java" contentType="text/html; charset=UTF-8"%>
<%
	List<FileInfo> list = (List<FileInfo>) request.getAttribute("fileInfoList");
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
	<h3>文本表单</h3>
	<p>id: ${id}</p>
	<p>name: ${name }</p>
	<p>sex: ${sex }</p>
	<p>address: ${address }</p>

	<h3>上传文件</h3>
	<p>
		成功上传文件数：
		<%=list.size()%></p>
	<%
		for (FileInfo info : list) {
	%>
	<p>
	<table>
		<tr>
			<td>原始文件名</td>
			<td><%=info.getPrimitiveName()%></td>
		</tr>
		<tr>
			<td>文件名</td>
			<td><%=info.getName()%></td>
		</tr>
		<tr>
			<td>文件名（不含扩展名）</td>
			<td><%=info.getNameWithoutExt()%></td>
		</tr>
		<tr>
			<td>路径</td>
			<td><%=info.getPath()%></td>
		</tr>
		<tr>
			<td>保存路径</td>
			<td><%=info.getSavePath()%></td>
		</tr>
		<tr>
			<td>保存目录</td>
			<td><%=info.getSaveDirectory()%></td>
		</tr>
		<tr>
			<td>表单字段名</td>
			<td><%=info.getFieldName()%></td>
		</tr>
		<tr>
			<td>contentType</td>
			<td><%=info.getContentType()%></td>
		</tr>
		<tr>
			<td>文件类型</td>
			<td><%=info.getType()%></td>
		</tr>
		<tr>
			<td>文件大小</td>
			<td><%=info.getSize()%></td>
		</tr>
		<tr>
			<td>bytes</td>
			<td><%=info.getBytes()%></td>
		</tr>
		<tr>
			<td>width</td>
			<td><%=info.getWidth()%></td>
		</tr>
		<tr>
			<td>height</td>
			<td><%=info.getHeight()%></td>
		</tr>
		<tr>
			<td>describe</td>
			<td><%=info.getDescribe()%></td>
		</tr>
	</table>
	</p>
	<%
		}
	%>
</body>
</html>