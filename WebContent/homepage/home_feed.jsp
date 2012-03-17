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
			Record record = newsfeeds.get(i);
			out.println("<div class=\"feed_block\">");
			if (record instanceof AchievementRecord) {
				AchievementRecord achievementRecord = (AchievementRecord) record; 
				out.println("<div><img src=\"/QuizWebsite/images/medal.png\" class=\"medium\"></div>");
				out.println("<div class=\"feed_container\"><span>"+record.user.getUserStringWithURL(true) + " achieved <span class=\"stress\">" + achievementRecord.achievement.name + "</span><br />");
				out.println("<span class=\"desc\">" +SimpleTime.getTime(record.timestamp)+"</span></div>");
			} else if (record instanceof QuizTakenRecord) {
				QuizTakenRecord quizTakenRecord = (QuizTakenRecord) record;
				long min = quizTakenRecord.timeSpan / 60000;
				long sec = (quizTakenRecord.timeSpan % 60000) / 1000;
				out.println("<div><img src=\"/QuizWebsite/images/star.png\" class=\"medium\"></div>");
				out.println("<div class=\"feed_container\"><span>"+record.user.getUserStringWithURL(true) + " scored <span class=\"stress\">" + quizTakenRecord.score + "</span> on quiz <span>" + quizTakenRecord.quiz.getQuizStringWithURL(true) + "</span><br />");
				out.println("<span class=\"desc\">" +SimpleTime.getTime(record.timestamp)+"</span></div>");
			} else if (record instanceof QuizCreatedRecord) {
				QuizCreatedRecord quizCreatedRecord = (QuizCreatedRecord) record;
				out.println("<div><img src=\"/QuizWebsite/images/quiz.png\" class=\"medium\"></div>");
				out.println("<div class=\"feed_container\"><span>"+record.user.getUserStringWithURL(true) + " created quiz : <span>" + quizCreatedRecord.quiz.getQuizStringWithURL(true) + "</span><br />");
				out.println("<span class=\"desc\">" +SimpleTime.getTime(record.timestamp)+"</span></div>");
			}
			out.println("</div>");
		}
	%>
</div>	
</body>
</html>
