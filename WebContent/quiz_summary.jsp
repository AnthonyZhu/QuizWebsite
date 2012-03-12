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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>
	<%
	Quiz quiz = (Quiz) session.getAttribute("newQuiz");
	String quizName = quiz.name;
	User homeUser = (User) session.getAttribute("user");
	out.println("Quiz Summary of " + quizName);
	//QuizSummary summary = quiz.computeSummaryStats();
	%>
	</title>
	<meta name="Description" content="A smart quiz website" />
	<meta name="robots" content="all, index, follow" />
	<meta name="distribution" content="global" />
	<link rel="shortcut icon" href="/favicon.ico" />
	
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/two_column_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>
<body>
<div class="container" >
	<div class="header">
		<h1>toQuiz.Me</h1>
	</div>
	
	<div class="content-container">
		<div class="two_column_left">
			<h1>
			<%
			out.println("Quiz Name: " + quizName);
			%>
			</h1>
			<p>Creator: 
			<%
			String creator = quiz.creator.username;
			out.println("<a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + quiz.creator.userID + "\">" + creator + "</a>");
			%>
			<br>
			<%--
		    out.println("Total score: " + summary.totalScore);
			--%></p>
		</div>
		<div class="two_column_right">
			<h2>Rate This Quiz</h2>
		</div>

	</div>
	
	<div class="content-container">
		<div class="two_column_left">
			<p><%
			String description = quiz.description;
			out.println("Description of quiz is: " + description);
			%></p>
		</div>
		<div class="two_column_right">
			<button type="submit" class="button_large" name="start_quiz">
					Start Quiz
			</button>
			<button type="submit" class="button_grey" name="start_quiz">
					Practice Mode
			</button>
		</div>
	</div>
	
	<div class="content-container">
		<div class="two_column_left">
			<h2>Quiz Summary</h2>
			<p><span class="dominant_text"><%-- out.println(summary.totalUser); --%></span> users have taken this quiz</p>
			<p>They spend an average of <span class="dominant_text"><%-- out.println(summary.averageTimespan + " mins"); --%></span> on the quiz</p>
			<p>Average score is <span class="dominant_text"><%-- out.println(summary.averageScore); --%></span></p>
		</div>
		<div class="two_column_right">
			<h2>Top Performers</h2>
			<ol>
			</ol>
		</div>
		<div class="two_column_left">
			<h2>My Statistics</h2>
			<p><%
			if(QuizTakenRecord.getQuizHistoryByQuizIDUserID(quiz.quizID,homeUser.userID) != null){
				out.println("I have taken this quiz");
			}else{
				out.println("I have not taken this quiz yet");
			}
			%></p>
		</div>
		<div class="two_column_left">
			<h2>Past Performances</h2>
			<p></p>
		</div>
		

	</div>
	
	<div class="footer">
			Copyright © toQuiz.me, 2012
	</div>
	
</div>
</body>