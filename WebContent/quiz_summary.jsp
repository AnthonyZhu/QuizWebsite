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
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>
	<%
	int quizID = Integer.parseInt(request.getParameter("id"));
	Quiz quiz = Quiz.getQuizByQuizID(quizID);
	String quizName = quiz.name;
	User homeUser = (User) session.getAttribute("user");
	out.println("Quiz Summary of " + quizName);
	QuizSummary summary = quiz.getQuizSummary();
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
		<h1><a href="homepage/homepage.jsp">toQuiz.me</a></h1>
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
			<%
		    out.println("Total score: " + summary.totalScore);
			%>
			<br>
			<%
			double rating = quiz.getQuizRating();
			out.println("Quiz rating: " + rating);
			%></p>
		</div>
		<!-- <div class="two_column_right">
			
		</div> -->

	</div>
	
	<div class="content-container">
		<div class="two_column_left">
			<p><%
			String description = quiz.description;
			out.println("Description: " + description);
			%></p>
		</div>
		<div class="two_column_right">
			<form action="StartQuiz" method="post">
			<input type="submit" value="Start Quiz"><br />
			<% 
			out.println("<input name =\"quizID\" type=\"hidden\" value=\"" + quiz.quizID + "\">");
			%>
			</form>
		</div>
	</div>
	
	<div class="content-container">
		<div class="two_column_left">
			<h2>Quiz Summary</h2>
			<p><span class="dominant_text">
			<% out.println(summary.totalUser); %>
			</span> users have taken this quiz</p>
			<p>They spend an average of <span class="dominant_text">
			<% out.println(Math.round(summary.averageTimespan/60000 * 100)/100.0 + " mins"); %>
			</span> on the quiz</p>
			<p>Average score is <span class="dominant_text">
			<% out.println(Math.round(summary.averageScore*100)/100.0); %>
			</span></p>
		</div>
		<div class="two_column_right">
			<h2>Top Three Performers</h2>
			<ul>
			<%
			ArrayList<QuizTakenRecord> topRecords= quiz.getAllTopRecord();
			if(topRecords == null){
				out.println("<li>No one has taken it yet.</li>");
			}else if(topRecords.size() == 0){
				out.println("<li>No one has taken it yet.</li>");
			}else{
				if(topRecords.size()<=3){
					for(int i=0;i<topRecords.size();i++){
						User oneUser = topRecords.get(i).user;
						out.println("<li>" + (i+1) + ": <a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + oneUser.userID + "\">" + oneUser.username + "</a></li>");
					}
				}else{
					for(int i=0;i<3;i++){
						User oneUser = topRecords.get(i).user;
						out.println("<li>" + (i+1) + ": <a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + oneUser.userID + "\">" + oneUser.username + "</a></li>");
					}
				}
			}
			%>
			</ul>
		</div>
		<div class="two_column_left">
			<h2>My Statistics</h2>
			<p><%
			ArrayList<QuizTakenRecord> records = QuizTakenRecord.getQuizHistoryByQuizIDUserID(quiz.quizID,homeUser.userID);
			if(records != null){
				if(records.size() != 0){
					QuizSummary mySummary = quiz.getQuizSummary(homeUser);
					out.println("<ul>");
					out.println("<li>My average score: " + Math.round(mySummary.averageScore*100)/100.0 + ". Average time: " + Math.round(mySummary.averageTimespan/60000*100)/100.0 + " mins</li>");
					out.println("<li>My best score: " + Math.round(100*mySummary.bestScore)/100.0 + ". My fastest time: " + Math.round(100*mySummary.bestTimespan/60000)/100.0 + " mins</li>");
					out.println("</ul>");
				}else{
					out.println("I have not taken this quiz yet");
				}
			}else{
				out.println("I have not taken this quiz yet");
			}
			%></p>
		</div>
		<div class="two_column_left">
			<h2>Past Performances</h2>
			<p>
			<%
			ArrayList<QuizTakenRecord> quizHistory = quiz.getAllHistory();
			if(quizHistory == null){
				out.println("No one has taken this quiz yet.");
			}else if(quizHistory.size() == 0){
				out.println("No one has taken this quiz yet.");
			}else{
				QuizTakenRecord pastHistory = quizHistory.get(0);
				out.println("<a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + pastHistory.user.userID + "\">"+ pastHistory.user.username + "</a>" + " has taken this quiz. Score: " + pastHistory.score + " in " + Math.round(pastHistory.timeSpan/60000.0*100)/100.0 + " mins");
			}
			%>
			
			</p>
		</div>
		

	</div>
	
	<div class="footer">
			Copyright © toQuiz.me, 2012
	</div>
	
</div>
</body>