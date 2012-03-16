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
		Quiz quiz = (Quiz) session.getAttribute("quiz");
		String quizName = quiz.name;
		User homeUser = (User) session.getAttribute("user");
		out.println("Quiz Result of " + quizName);
		QuizSummary summary = quiz.getQuizSummary();
		
		ArrayList<Object> userAnswers = (ArrayList<Object>) session.getAttribute("userAnswers");
		boolean isFeedback = (Boolean) session.getAttribute("isfeedback");
		boolean isPractice = (Boolean) session.getAttribute("ispractice");			
		long startTime = (Long) session.getAttribute("start_time");
		long endTime = new Date().getTime();
		long duration = endTime - startTime;
		long min = duration / 60000;
		long sec = (duration % 60000) / 1000;
		double totalScore = quiz.getTotalScore();
		double myScore = quiz.getScore(userAnswers);
		QuizTakenRecord record = new QuizTakenRecord(quiz, homeUser, duration, myScore, isFeedback, isPractice);
		boolean isQuizResultStored = (Boolean) session.getAttribute("is_quiz_result_stored");
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		if (!isQuizResultStored) {
			if (isPractice) {
				homeUser.practiceNumber++;
				homeUser.updateCurrentUser();
				newAchievements.addAll(PracticeAchievement.updateAchievement(homeUser));				
			} else {
				record.addRecordToDB();
				session.setAttribute("is_quiz_result_stored", true);	
				if (myScore >= quiz.getBestScore()) {
					homeUser.highScoreNumber++;
					homeUser.updateCurrentUser();
					newAchievements.addAll(HighScoreAchievement.updateAchievement(homeUser));
				}
				newAchievements.addAll(QuizTakenAchievement.updateAchievement(homeUser));
			}
		}
	%>
	</title>
	
	<meta name="Description" content="A smart quiz website" />
	<meta name="robots" content="all, index, follow" />
	<meta name="distribution" content="global" />
	<link rel="shortcut icon" href="/favicon.ico" />
	
	<link rel="stylesheet" href="/QuizWebsite/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/rating.css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/two_column_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>


<body>
<div class="container" >
<jsp:include page="/modules/head.jsp" />
	
	<div class="content-container">
		<div class="two_column_left">
			<span><img src="/QuizWebsite/images/star.png" style="float: left" width="150" height="150"></span>
			<span><h4>Congratulations! You have finished</h4></span>
			<span><h1><%=quizName%></h1></span>
			Rate this quiz:
			<ul class="rate  nostar" style="float:right">
				<li class="one"><a href="#" title="1 Star">1</a></li>
				<li class="two"><a href="#" title="2 Stars">2</a></li>
				<li class="three"><a href="#" title="3 Stars">3</a></li>
				<li class="four"><a href="#" title="4 Stars">4</a></li>
				<li class="five"><a href="#" title="5 Stars">5</a></li>
			</ul>
			<br/>
			<hr/>
			<p>
			<%
		    out.println("You scored <span class=\"dominant_text\">" + Math.round(myScore*100)/100.0 + "</span> out of total <span class=\"dominant_text\">" + Math.round(totalScore*100)/100.0 + "</span>");
			%>
			<br />
			<%
			out.println("You spent <span class=\"dominant_text\">" + min + "</span> minutes and <span class=\"dominant_text\">" + sec + "</span> seconds on this quiz.");
			%>
			<br />
			</p>
			
		</div>

		<div class="two_column_right">
			<h2>Top 3 Performers</h2>
			<ul>
			<%
			ArrayList<QuizTakenRecord> topRecords= quiz.getAllTopRecord();
			if(topRecords == null){
				out.println("<li>No one has taken it yet.</li>");
			} else if(topRecords.size() == 0){
				out.println("<li>No one has taken it yet.</li>");
			} else {
				if(topRecords.size()<=3){
					for(int i=0;i<topRecords.size();i++){
						User oneUser = topRecords.get(i).user;
						out.println("<li>" + (i+1) + ": <a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + oneUser.userID + "\">" + oneUser.username + "</a></li>");
					}
				} else {
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
			<h2>New Achievements</h2>
			<p>
			<%
			if (newAchievements.size() == 0) {
				out.println("You have no new achievement. Keep up your progress!");
			} else {
				out.println("Well Done! You have <span class=\"dominant_text\">" + newAchievements.size() + "</span> NEW Achievements!");
				out.println("<br />");
				for (int i = 0; i < newAchievements.size(); i++) 
					out.println("<span class=\"dominant_text\">" + newAchievements.get(i).name + " : </span>  " + newAchievements.get(i).description + "<br />");					
			}
			%>
			</p>
		</div>
		
		<div class="two_column_left">
			<h2>My Statistics</h2>
			<p><%
			ArrayList<QuizTakenRecord> records = QuizTakenRecord.getQuizHistoryByQuizIDUserID(quiz.quizID,homeUser.userID);
			if (records != null){
				if (records.size() != 0){
					QuizSummary mySummary = quiz.getQuizSummary(homeUser);
					out.println("<ul>");
					out.println("<li>My average score: " + Math.round(mySummary.averageScore*100)/100.0 + ". Average time: " + Math.round(mySummary.averageTimespan/60000*100)/100.0 + " mins</li>");
					out.println("<li>My best score: " + Math.round(100*mySummary.bestScore)/100.0 + ". My fastest time: " + Math.round(100*mySummary.bestTimespan/60000)/100.0 + " mins</li>");
					out.println("</ul>");
				} else {
					out.println("I have not taken this quiz yet");
				}
			} else {
				out.println("I have not taken this quiz yet");
			}
			%></p>
		</div>
		<div class="two_column_left">
			<h2>My Past Performances</h2>
			<p>
			<%
			ArrayList<QuizTakenRecord> quizHistory = quiz.getUserHistory(homeUser);
			if (quizHistory == null) {
				out.println("You haven't taken this quiz before.");
			} else if (quizHistory.size() == 0) {
				out.println("You haven't taken this quiz before.");
			} else {
				Collections.sort(quizHistory, new QuizTakenRecordSortByQuality());
				for (int i = 0; i < Math.min(quizHistory.size(),5); i++) {
					QuizTakenRecord myRecord = quizHistory.get(i);
					out.println("<span>At " + myRecord.timestamp.toString() + "</span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span>You achieved " + Math.round(myRecord.score*100)/100.0 + " score in " + Math.round(myRecord.timeSpan/60000.0*100)/100.0 + " mins </span><br />");
				}
			}
			%>
			
			</p>
		</div>
		

	</div>
	
<jsp:include page="/modules/foot.html" />
	
</div>
</body>