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
		<br />
	<%
		User homeUser = (User) session.getAttribute("user");
		ArrayList<ChallengeMessage> challengeMessages = ChallengeMessage.getMessagesByUserID(homeUser.userID);
		if (challengeMessages == null){
			out.println("I don't have any challenges.");
		}else if(challengeMessages.size() == 0){
			out.println("I don't have any challenges.");
		}else{
			out.println("<div class=\"feed_block\">");
			out.println("<div><img src=\"/QuizWebsite/images/challenge.png\" class=\"medium\"></div>");
			for(int i=0;i<challengeMessages.size();i++){
				ChallengeMessage challenge = challengeMessages.get(i);
				User oneUser = User.getUserByUserID(challenge.fromUser);
				out.println("<div class=\"feed_container\"><a target=\"_blank\" class=\"link-style-dominant\" href=\"/QuizWebsite/userpage.jsp?id=" + oneUser.userID + "\">" + oneUser.username + 
						"</a> sent a challenge to you in <a target=\"_blank\" class=\"link-style-dominant\" href=\"/QuizWebsite/quiz_summary.jsp?id=" + 
						challenge.quizID + "\">" + Quiz.getQuizByQuizID(challenge.quizID).name + "</a><br />");			}
			out.println("</div></div>");
		}
	%>
</div>	
</body>
</html>
