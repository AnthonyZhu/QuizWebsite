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
	<h2 class="title_style_minor">My News Feed</h2>
	<br >
	<%
		int maxDisplayNumber = 20;
		User homeUser = (User) session.getAttribute("user");
		ArrayList<Record> newsfeeds = homeUser.getNewsfeed();
		for (int i = 0; i < Math.min(maxDisplayNumber, newsfeeds.size()); i++) {
			out.println("<p>");
			Record record = newsfeeds.get(i);
			if (record instanceof AchievementRecord) {
				AchievementRecord achievementRecord = (AchievementRecord) record; 
				out.println(SimpleTime.getTime(record.timestamp) + " " + record.user.getUserStringWithURL(true) + " achieved <span class=\"dominant_text\">" + achievementRecord.achievement.name + "</span>");
			} else if (record instanceof QuizTakenRecord) {
				QuizTakenRecord quizTakenRecord = (QuizTakenRecord) record;
				long min = quizTakenRecord.timeSpan / 60000;
				long sec = (quizTakenRecord.timeSpan % 60000) / 1000;
				out.println(SimpleTime.getTime(record.timestamp) + " " + record.user.getUserStringWithURL(true) + " scored <span class=\"dominant_text\">" + quizTakenRecord.score + "</span> on quiz <span class=\"dominant_text\">" + quizTakenRecord.quiz.getQuizStringWithURL(true) + "</span>" );
			} else if (record instanceof QuizCreatedRecord) {
				QuizCreatedRecord quizCreatedRecord = (QuizCreatedRecord) record;
				out.println(SimpleTime.getTime(record.timestamp) + " " + record.user.getUserStringWithURL(true) + " created quiz : <span class=\"dominant_text\">" + quizCreatedRecord.quiz.getQuizStringWithURL(true) + "</span>");
			}
			out.println("</p>");
		}
	%>
</div>	
</body>
</html>
