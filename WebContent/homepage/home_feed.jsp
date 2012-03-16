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
	<%
		int maxDisplayNumber = 20;
		User homeUser = (User) session.getAttribute("user");
		ArrayList<Record> newsfeeds = homeUser.getNewsfeed();
		for (int i = 0; i < Math.min(maxDisplayNumber, newsfeeds.size()); i++) {
			out.println("<p>");
			Record record = newsfeeds.get(i);
			if (record instanceof AchievementRecord) {
				AchievementRecord achievementRecord = (AchievementRecord) record; 
				out.println(record.user.username + " has achieved <span class=\"dominant_text\">" + achievementRecord.achievement.name + "</span> &nbsp;&nbsp;" + record.timestamp.toString());
			} else if (record instanceof QuizTakenRecord) {
				QuizTakenRecord quizTakenRecord = (QuizTakenRecord) record;
				long min = quizTakenRecord.timeSpan / 60000;
				long sec = (quizTakenRecord.timeSpan % 60000) / 1000;
				out.println(record.user.username + " has taken quiz : <span class=\"dominant_text\">" + quizTakenRecord.quiz.name);
				out.println("</span> and scored <span class=\"dominant_text\">" + quizTakenRecord.score + "</span> in " + min + " minutes and " + sec + " seconds. &nbsp;&nbsp;" + record.timestamp.toString());
			} else if (record instanceof QuizCreatedRecord) {
				QuizCreatedRecord quizCreatedRecord = (QuizCreatedRecord) record;
				out.println(record.user.username + " created quiz : <span class=\"dominant_text\">" + quizCreatedRecord.quiz.name);
				out.println("</span> &nbsp;&nbsp;" + record.timestamp.toString());
			}
			out.println("</p>");
		}
	%>
</div>	
</body>
</html>
