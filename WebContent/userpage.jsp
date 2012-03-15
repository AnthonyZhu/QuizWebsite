<%@page import="quizweb.User"%>
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
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title><%
	int visitUserID = Integer.parseInt(request.getParameter("id"));
	User visitUser = User.getUserByUserID(visitUserID);
	out.println(visitUser.username);
	
	User homeUser = (User) session.getAttribute("user");
	%></title>
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
			<span style="float:right">
			<%
			if(homeUser.userID == visitUserID){
			}else{
				out.println("<button type=\"submit\" name=\"addAsFriend\">Send Friend Request</button>" +
						"<button type=\"submit\" name=\"addAsFriend\">Challenge</button>" +
						"<button type=\"submit\" name=\"addAsFriend\">Send Note</button>");
			}
			%>
			</span>
			<h1>
			<%
			out.println(visitUser.username);
			%>
			</h1>
			<p><span class="dominant_text">
			<% 
			if(visitUser.getFriendList() == null){
				out.println("0");
			}else if(visitUser.getFriendList().size() == 0){
				out.println("0");
			}else{
				out.println(visitUser.getFriendList().size()); 
			}
			%>
			</span> friends | 
			<span class="dominant_text">
			<%
			if(visitUser.getCreatedQuiz() == null){
				out.println("0");
			}else if(visitUser.getCreatedQuiz().size() == 0){
				out.println("0");
			}else{
				out.println(visitUser.getCreatedQuiz().size()); 
			}
			%>
			</span> quiz created | 
			<span class="dominant_text">
			<% 
			if(visitUser.getQuizHistory() == null){
				out.println("0");
			}else if(visitUser.getQuizHistory().size() == 0){
				out.println("0");
			}else{
				out.println(visitUser.getQuizHistory().size()); 
			}
			%>
			</span> quizzes taken</p>
		</div>
		<div class="two_column_right">
			<h2>Achievement</h2>
			<p><ul>
			<%
			if(AchievementRecord.getAchievementsByUserID(visitUserID,0) == null){
				out.println("no achievement yet");
			}else if(AchievementRecord.getAchievementsByUserID(visitUserID,0).size() == 0){
				out.println("no achievement yet");
			}else{
				if(AchievementRecord.getAchievementsByUserID(visitUserID,0).size()<=10){
					for(int i=0;i<AchievementRecord.getAchievementsByUserID(visitUserID,0).size();i++){
						out.println("<li>" + AchievementRecord.getAchievementsByUserID(visitUserID,0).get(i).name + "</li>");
					}
				}else{
					for(int i=0;i<=10;i++){
						out.println("<li>" + AchievementRecord.getAchievementsByUserID(visitUserID,0).get(i).name + "</li>");
					}
				}
			}
			%>
			</ul></p>
		</div>
	</div>
	
	
	<div class="content-container">
		
		<div class="two_column_right">
			<h2></h2>
			
		</div>
		<div class="two_column_left">
			<h2>Quizzes Created</h2>
			<p><ul><%
			if(QuizCreatedRecord.getCreatedQuizByUserID(visitUserID) == null){
				out.println("I haven't created any quiz so far");
			}else if(QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).size() == 0){
				out.println("I haven't created any quiz so far");
			}else{
				if(QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).size() <= 10){
					for(int i=0;i<QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).size();i++){
						out.println("<li><a class=\"link-style-dominant\" href=\"quiz_summary.jsp?id=" + QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).get(i).quiz.quizID + "\">" + QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).get(i).quiz.name + "</a></li>");
					}
				}else{
					for(int i=0;i<=10;i++){
						out.println("<li><a class=\"link-style-dominant\" href=\"quiz_summary.jsp?id=" + QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).get(i).quiz.quizID + "\">" + QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).get(i).quiz.name + "</a></li>");
					}
				}
			}
			%></ul></p>
		</div>
		<div class="two_column_left">
			<h2>Quizzes Taken</h2>
			<p><ul><% 
			if(QuizTakenRecord.getQuizHistoryByUserID(visitUserID) == null){
				out.println("I haven't taken any quiz so far");
			}else if(QuizTakenRecord.getQuizHistoryByUserID(visitUserID).size() == 0){
				out.println("I haven't taken any quiz so far");
			}else{
				if(QuizTakenRecord.getQuizHistoryByUserID(visitUserID).size() <= 10){
					for(int i=0;i<QuizTakenRecord.getQuizHistoryByUserID(visitUserID).size();i++){
						out.println("<li><a class=\"link-style-dominant\" href=\"quiz_summary.jsp?id=" + QuizTakenRecord.getQuizHistoryByUserID(visitUserID).get(i).quiz.quizID + "\">" + QuizTakenRecord.getQuizHistoryByUserID(visitUserID).get(i).quiz.name + "</a></li>");
					}
				}else{
					for(int i=0;i<=10;i++){
						out.println("<li><a class=\"link-style-dominant\" href=\"quiz_summary.jsp?id=" + QuizTakenRecord.getQuizHistoryByUserID(visitUserID).get(i).quiz.quizID + "\">" + QuizTakenRecord.getQuizHistoryByUserID(visitUserID).get(i).quiz.name + "</a></li>");
					}
				}
			}
			%></ul></p>
		</div>
		

	</div>
	
	<div class="footer">
			Copyright © toQuiz.me, 2012
	</div>
	
</div>
</body>