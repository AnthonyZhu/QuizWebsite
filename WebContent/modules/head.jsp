<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="quizweb.*"%>
<%@ page import="quizweb.accountmanagement.*"%>
<%@ page import="quizweb.achievement.*"%>
<%@ page import="quizweb.announcement.*"%>
<%@ page import="quizweb.database.*"%>
<%@ page import="quizweb.message.*"%>
<%@ page import="quizweb.question.*"%>
<%@ page import="quizweb.record.*"%>
<%@ page import="servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<div class="header">
	<% User homeUser = (User) session.getAttribute("user"); %>
	<a href="http://localhost:8080/QuizWebsite/homepage/homepage.jsp" ><img src="/QuizWebsite/images/toQuiz-logo-small.png" style="float: left;"></a>
	<form action="/QuizWebsite/LogoutServlet" method="post">
		<button style="float:right; clear:right">logout</button>
	</form>
	<h4 style="color:#ccc;float:right">
	<% out.println("Welcome, " + homeUser.username);%>
	</h4>
</div>
</html>