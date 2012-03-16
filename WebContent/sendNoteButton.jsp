<%@page import="quizweb.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/sendNote.js"></script>
	<link rel="stylesheet" href="/QuizWebsite/resources/css/quiz.css" type="text/css" />
	
</head>
<body>
	<% 
		session = request.getSession();
		User user = (User)session.getAttribute("user");
		
	%>
	<div id="note_form">
	<form name="note" action="">
		<p style="float:right"><input type="submit" id="note_btn" value="Leave a note"/><p>
		<br />
		<div><textarea id="note" class="small" style="width:75%; float:right"></textarea></div>
		<input type="hidden" id="sender" value=<%=user.userID%>/>
		<!--<input type="hidden" id="receiver" value="<%//out.print(senderID); %>"/>-->
	</form>
	<span class="error" style="display:none"> Empty note!</span>
	</div>
</body>
</html>