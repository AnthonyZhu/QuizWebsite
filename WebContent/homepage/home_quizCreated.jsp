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
	<h2 class="title_style_minor">Quizzes I Created</h2>
		<br /><br />
	<%
	User homeUser = (User) session.getAttribute("user");
	ArrayList<QuizCreatedRecord> createdRecord = QuizCreatedRecord.getCreatedQuizByUserID(homeUser.userID);
	if(createdRecord == null){
		out.println("I haven't created any quiz yet.");
	}else if(createdRecord.size() == 0){
		out.println("I haven't created any quiz yet.");
	}else{
		for(int i=0;i<createdRecord.size();i++){
			out.println("<div class=\"feed_block\">");
			out.println("<div><img src=\"/QuizWebsite/images/quiz.png\" class=\"medium\"></div>");
			out.println("<div class=\"feed_container\">I created the quiz" +
					"&#160;<a target=\"_blank\" class=\"link-style-dominant\" href=\"\\QuizWebsite\\quiz_summary.jsp?id=" + 
					createdRecord.get(i).quiz.quizID + "\">" + createdRecord.get(i).quiz.name + "</a></div>");
			out.println("</div>");	
		}
	}
	%>
</div>
</body>
</html>	
