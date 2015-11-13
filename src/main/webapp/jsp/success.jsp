<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<% String userName = (String)session.getAttribute("USERNAME"); %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login Success</title>
</head>
<body bgcolor="#f3f3f3">
	<TABLE cellSpacing=0 cellPadding=0 width="100%" border=0>
		<tr>
			<td align=center height="150">&nbsp;</td>
		</tr>
		<tr>
			<td align=center><%=userName %>：你好，欢迎您！</td>
			<a href="main.do">全部用户列表</a>
		</tr>
	</TABLE>
</body>
</html>