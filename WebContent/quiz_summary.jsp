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
	<link rel="stylesheet" href="resources/css/quiz.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/two_column_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>
<body>
<div class="container" >
<jsp:include page="/modules/head.jsp" />
	
	<div class="content-container">
		<div class="two_column_left">
			<form action="StartQuiz" method="post">
			<input style="float:right" type="submit" value="Take quiz now!" />
			<% 
			out.println("<input name =\"quizID\" type=\"hidden\" value=\"" + quiz.quizID + "\">");
			%>
			</form>
			
			<form action="StartPractice" method="post">
			<input style="float:right" type="submit" onclick="practiceMode()" value="Practice Mode" /><br />
			<% 
			out.println("<input name =\"quizID\" type=\"hidden\" value=\"" + quiz.quizID + "\">");
			%>
			</form>
			
			<span><img src="/QuizWebsite/images/quiz.png" style="float: left" width="150" height="150"><h1><%=quizName%></h1></span>

			<p>
			<span>Creator: 
			<%
				String creator = quiz.creator.username;
				out.println("<a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + quiz.creator.userID + "\">" + creator + "</a>");
			%> | 
			</span>
			
			<span>Total points: <%=summary.totalScore%> | </span>
			
			<span><%
				double rating = quiz.getQuizRating();
				out.println("Quiz rating: " + rating);
			%></span>
			</p>
			
			<br /><hr /><br />
			<p><%
				String description = quiz.description;
				out.println(description);
			%></p>
		</div>
		<div class="two_column_right">
			<h2>Top 3 Performers</h2>
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
						out.println("<li>" + (i+1) + ". <a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + oneUser.userID + "\">" + oneUser.username + "</a></li>");
					}
				}else{
					for(int i=0;i<3;i++){
						User oneUser = topRecords.get(i).user;
						out.println("<li>" + (i+1) + ". <a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + oneUser.userID + "\">" + oneUser.username + "</a></li>");
					}
				}
			}
			%>
			</ul>
		</div>

	</div>
	
	<div class="content-container">
	
		<div class="two_column_left">
			<h2>Quiz Summary</h2>
			<p><span class="dominant_text"><%=summary.totalUser%></span> users have taken this quiz</p>
			<p>They spent an average of <span class="dominant_text"><%=Math.round(summary.averageTimespan/60000 * 100)/100.0%> minutes</span></p>
			<p>Average score is <span class="dominant_text"><%=Math.round(summary.averageScore*100)/100.0 %></span></p>
		</div>
		
		<div class="two_column_left">
			<h2>My Statistics</h2>
			<p><%
			ArrayList<QuizTakenRecord> records = QuizTakenRecord.getQuizHistoryByQuizIDUserID(quiz.quizID,homeUser.userID);
			if(records != null){
				if(records.size() != 0){
					QuizSummary mySummary = quiz.getQuizSummary(homeUser);
					out.println("<ul>");
					out.println("<li>My average score: " + Math.round(mySummary.averageScore*100)/100.0 + ". Average time: " + Math.round(mySummary.averageTimespan/60000*100)/100.0 + " minutes</li>");
					out.println("<li>My best score: " + Math.round(100*mySummary.bestScore)/100.0 + ". My fastest time: " + Math.round(100*mySummary.bestTimespan/60000)/100.0 + " minutes</li>");
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
			if (quizHistory == null) {
				out.println("No one has taken this quiz yet.");
			} else if (quizHistory.size() == 0) {
				out.println("No one has taken this quiz yet.");
			} else {
				for (int i = 0; i < Math.min(quizHistory.size(), 5); i++) {
					QuizTakenRecord record = quizHistory.get(i);
					out.println("<a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + record.user.userID + "\">"+ record.user.username + "</a>" + " scored " + record.score + " in " + Math.round(record.timeSpan/60000.0*100)/100.0 + " minutes <br />");
				}
			}
			%>
			
			</p>
		</div>
		

	</div>
	
<jsp:include page="/modules/foot.html" />
	
</div>
</body>
