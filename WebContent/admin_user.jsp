<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/admin_layout.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<div><%= request.getParameter("keyword") %></div>
	<table class="adminTable">
		<tr><td>Id</td><td>Username</td><td>Type</td></tr>
		<tr><td>1</td><td>Anthony Zhu</td><td>Normal student</td></tr>
		<tr><td>2</td><td>Yiye Jin</td><td>Artistic student</td></tr>
		<tr><td>3</td><td>Jack Guo</td><td>?? student</td></tr>
	</table>
</body>
</html>