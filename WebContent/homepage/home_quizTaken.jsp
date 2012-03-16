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
		<h2 class="title_style_minor">Quizzes I Took</h2>
		<br /><br />
		<%
	
		User homeUser = (User) session.getAttribute("user");
		ArrayList<QuizTakenRecord> takenRecord = QuizTakenRecord.getQuizHistoryByUserID(homeUser.userID);
		if(takenRecord == null){
			out.println("I haven't taken any quiz yet.");
		}else if(takenRecord.size() == 0){
			out.println("I haven't taken any quiz yet.");
		}else{
			out.println("<ul>");
			for(int i=0;i<takenRecord.size();i++){
				out.println("<li><img src=\"/QuizWebsite/images/check.png\" style=\"float: left\" width=\"16\" height=\"16\">"+
				"&#160;<a target=\"_blank\" class=\"link-style-dominant\" href=\"\\QuizWebsite\\quiz_summary.jsp?id=" + takenRecord.get(i).quiz.quizID + "\">" + takenRecord.get(i).quiz.name + "</a></li>");
			}
			out.println("</ul>");
		}
		%>
	</div>	
</body>
</html>
