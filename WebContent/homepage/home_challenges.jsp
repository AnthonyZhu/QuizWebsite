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
<h2 class="title_style_minor">Challenges From Friends</h2>
		<br /><br />
	<%
		User homeUser = (User) session.getAttribute("user");
		ArrayList<ChallengeMessage> challengeMessages = ChallengeMessage.getMessagesByUserID(homeUser.userID);
		if(challengeMessages == null){
			out.println("I don't have any challenge.");
		}else if(challengeMessages.size() == 0){
			out.println("I don't have any challenge.");
		}else{
			out.println("<ul>");
			for(int i=0;i<challengeMessages.size();i++){
				ChallengeMessage challenge = challengeMessages.get(i);
				out.println("<li>" + User.getUserByUserID(challenge.fromUser).username + " sent this challenge to you</li>");
			}
			out.println("</ul>");
		}
	%>
</div>	
</body>
</html>
