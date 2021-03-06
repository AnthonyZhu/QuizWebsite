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
<%@ page import="quizweb.quiz.*"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Website Stats</title>
<link rel="stylesheet" href="resources/css/main.css" type="text/css" />

</head>
<body>
	<div class="content-container">
		<div>
		<h3>Website Stats</h3>
		<%
		int totalUsers = User.getTotalUsers();
		out.println("<p>Total Users : " + totalUsers + "</p>");
		int totalQuiz = Quiz.getTotalQuiz();
		out.println("<p>Total Quiz: " + totalQuiz + "</p>");
		int totalTakenQuiz = QuizTakenRecord.getTotalQuizTakenRecord();
		out.println("<p>Total Quiz Taken: " + totalTakenQuiz + "</p>");
		int totalCreatedQuiz = QuizCreatedRecord.getTotalQuizCreatedRecord();
		out.println("<p>Total Quiz Created: " + totalCreatedQuiz + "</p>");
		int totalAchievements = AchievementRecord.getTotalAchievementRecord();
		out.println("<p>Total Achievement Earned: " + totalAchievements + "</p>");
		
		%>
		</div>
	</div>
</body>
</html>