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
<%@ page import="java.util.ArrayList"%>
<html>
<head>
	<link rel="stylesheet" href="/QuizWebsite/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/three_column_layout.css" type="text/css" />
</head>

<body>
	<div class="three_column_content">
		<h2 class="title_style_minor">More Recent Quizzes</h2>
		<br /><br />
		<ol>
		<%
		ArrayList<Quiz> quizList = Quiz.getRecentQuiz();
		for(int i = 0; i <quizList.size(); i++) {		
			Quiz quiz = quizList.get(i);
			out.println("<div class=\"feed_block\">");
			out.println("<div><img src=\"/QuizWebsite/images/quiz.png\" class=\"medium\"></div>");
			out.println("<div class=\"feed_container\"><span>" + quiz.getQuizStringWithURL(false) + "</span><br /></div></div>");
		}	
	    %>
	    </ol>
	</div>
</body>
</html>