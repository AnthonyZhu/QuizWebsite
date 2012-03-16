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
<title>Insert title here</title>
</head>
<div class="header">
    <% User homeUser = (User) session.getAttribute("user"); %>
	<a href="\QuizWebsite\homepage\homepage.jsp" ><img src="/QuizWebsite/images/toQuiz-logo-small.png" style="float: left;"></a>
	<h4 style="color:#ccc;float:right; clear:right; ">
	<% out.println("Welcome, " + homeUser.username);%>
	</h4>
</div>
</html>