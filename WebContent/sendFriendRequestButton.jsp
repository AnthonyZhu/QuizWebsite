<%@page import="quizweb.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/sendFriendRequest.js"></script>
</head>
<body>
	<% 
	session = request.getSession();
	User user = (User)session.getAttribute("user");
	%>
	<div id="friend_request_form">
	<form name="friend_request" action="">
		<input type="hidden" id="sender" value="1"/>
		<input type="hidden" id="receiver" value="2"/>
		<input type="submit" id="submit_btn" value="add as friend"/>
	</form>
	</div>
</body>
</html>