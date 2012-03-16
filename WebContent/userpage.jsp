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
	<%
		int visitUserID = Integer.parseInt(request.getParameter("id"));
		User visitUser = User.getUserByUserID(visitUserID);
		User homeUser = (User) session.getAttribute("user");
	%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>
		<%=visitUser.username%>
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
	<jsp:include page="/modules/head.jsp" />
	
	<div class="content-container">
		<div class="two_column_left">
			<span style="float:right; margin-top:10px">
			<%
			if(homeUser.userID == visitUserID){
			}else{
				out.println("<jsp:include page=\"/sendFriendRequestButton.jsp\"></jsp:include>");
			}
			%>
			<span style="float:right">

			<jsp:include page="/sendFriendRequestButton.jsp"></jsp:include>
			<jsp:include page="/sendChallengeButton.jsp"></jsp:include>
			</span>
			</span>
			<img src="/QuizWebsite/images/user.png" style="float: left" width="150" height="150">
			<h1><%= visitUser.username %></h1>
			<span class="dominant_text">
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
			</span> created | 
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
			</span>taken
			<br /><hr /><br />
			<jsp:include page="/sendNoteButton.jsp"></jsp:include>
		</div>
		<div class="two_column_right">
			<h2>Achievements</h2>
			<p><ul>
			<%
			if(AchievementRecord.getAchievementsByUserID(visitUserID,0) == null){
				out.println("no achievement yet");
			}else if(AchievementRecord.getAchievementsByUserID(visitUserID,0).size() == 0){
				out.println("no achievement yet");
			}else{
				if(AchievementRecord.getAchievementsByUserID(visitUserID,0).size()<=10){
					for(int i=0;i<AchievementRecord.getAchievementsByUserID(visitUserID,0).size();i++){
						out.println("<li><img src=\"/QuizWebsite/images/check.png\" style=\"float: left\" width=\"16\" height=\"16\">" + AchievementRecord.getAchievementsByUserID(visitUserID,0).get(i).name + "</li>");
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
			<ul><%
			if(QuizCreatedRecord.getCreatedQuizByUserID(visitUserID) == null){
				out.println("This user hasn't created any quiz so far");
			}else if(QuizCreatedRecord.getCreatedQuizByUserID(visitUserID).size() == 0){
				out.println("This user hasn't created any quiz so far");
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
			%></ul>
		</div>
		<div class="two_column_left">
			<h2>Quizzes Taken</h2>
			<ul><% 
			if(QuizTakenRecord.getQuizHistoryByUserID(visitUserID) == null){
				out.println("This user hasn't taken any quiz so far");
			}else if(QuizTakenRecord.getQuizHistoryByUserID(visitUserID).size() == 0){
				out.println("This user hasn't taken any quiz so far");
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
			%></ul>
		</div>
		

	</div>
	
<jsp:include page="/modules/foot.html" />
	
</div>
</body>
