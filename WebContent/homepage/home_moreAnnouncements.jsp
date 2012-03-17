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
		<h2 class="title_style_minor">More Announcements</h2>
		<br /><br />
		<ol>
		<% 
		for(int i=0;i<Announcement.allAnnouncements.size();i++){
			out.println("<div class=\"feed_block\">");
			out.println("<div><img src=\"/QuizWebsite/images/announcement.png\" class=\"medium\"></div>");
			out.println("<div class=\"feed_container\"><span>" + Announcement.allAnnouncements.get(i).title + ": <span class=\"stress\">" + Announcement.allAnnouncements.get(i).content + "</span><br />");
			out.println("<span class=\"desc\">" +SimpleTime.getTime(Announcement.allAnnouncements.get(i).timestamp)+"</span></div></div>");
	    }
		%>
		</ol>
	</div>
</body>
</html>