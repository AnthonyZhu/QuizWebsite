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
				if(homeUser.isFriend(visitUser) == 0){
					out.println("<form action=\"/QuizWebsite/AddFriendServlet\" method=\"post\">");
					out.println("<input type=\"hidden\" name=\"sender\" value=\"" + homeUser.userID + "\">");
					out.println("<input type=\"hidden\" name=\"receiver\" value=\"" + visitUserID + "\">");
					out.println("<input type=\"submit\" id=\"submit_btn\" value=\"Add as friend\">");
					out.println("</form>");
				}
			}
			%>
			
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
			<%
			if(homeUser.userID == visitUserID){
			}else{
				out.println("<form action=\"/QuizWebsite/SendNoteServlet\" method=\"post\">");
				out.println("<input type=\"hidden\" name=\"sender\" value=\"" + homeUser.userID + "\">");
				out.println("<input type=\"hidden\" name=\"receiver\" value=\"" + visitUserID + "\">");
				out.println("<input type=\"text\" class=\"large\" name=\"note\">");
				out.println("<input type=\"submit\" id=\"submit_btn\" value=\"Send a Note\">");
				out.println("</form>");
			}
			%>
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
						out.println("<li>" + AchievementRecord.getAchievementsByUserID(visitUserID,0).get(i).name + "</li>");
					}
				}else{
					for(int i=0;i<=10;i++){
						out.println("<li>"+ AchievementRecord.getAchievementsByUserID(visitUserID,0).get(i).name + "</li>");
					}
				}
			}
			%>
			</ul></p>
			
		</div>
	
		<div class="two_column_right">
			<h2>Friends</h2>
			<p><ul>
			<%
			if(visitUser.getFriendList() == null){
				out.println("I don't have any friend yet.");
			}else if(visitUser.getFriendList().size() == 0){
				out.println("I don't have any friend yet.");
			}else{
				for(int i=0;i<visitUser.getFriendList().size();i++){
					User friend = visitUser.getFriendList().get(i);
					out.println("<li><a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + friend.userID + "\">" + friend.username + "</a></li>");
				}
			}
			%>
			</ul></p>
			
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
