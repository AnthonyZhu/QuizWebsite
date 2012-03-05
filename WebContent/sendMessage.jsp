<%@page import="quizweb.CurrentTime"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<p>Your message: </p>
	<form action="SendMessageServlet" method="post">
		Message: <input type="text" name="note"><br />
		<input type="hidden" value="1" name="uid1">
		<input type="hidden" value="3" name="uid2">
		<input type="submit" value="send"><br />
	</form>
</body>
</html>