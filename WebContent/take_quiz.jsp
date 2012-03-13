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
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8856-1">
	<title>Start Quiz</title>
	
	<!-- CSS -->
	<link href="resources/css/main.css" type="text/css" rel="stylesheet"/>
	<link href="resources/css/quiz.css" rel="stylesheet">
	
	<!-- JavaScript -->
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>

<body>
<div class="container">
	<div class="header">
		<h1>toQuiz.Me</h1>
	</div>
	
	<form id="form1" action="TakingQuizServlet" name="form1" method="post" >

	<div class="one_column">
		You are taking the quiz <b>
		<% 
		int quizID = (Integer) session.getAttribute("quizID");
		int posistion = (Integer) session.getAttribute("question_posistion");
		Quiz quiz = Quiz.getQuizByQuizID(quizID);
		String quizName = quiz.name;
		out.println(quizName);
		%></b> created by <a>
		<%
		String creator = Quiz.getQuizByQuizID(quizID).creator.username;
		out.println("<a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + quiz.creator.userID + "\">" + creator + "</a>");
		%></a>
		<span style="float: right"> 
			Time spent: <!-- show time spent -->
		</span>
		<hr />
		<ul>
			<li id="foli1" class="highlight">
				<span><%
			    out.println("This is No." + posistion + " question.");
				%></span>
				<hr >
				<span class="quiz_title"><%
				
				%></span>
				<div>
					<input id="Field1" name="Field1" type="text" class="field text large" value="" maxlength="50" tabindex="1" onkeyup="validateRange(2, 'character');" />
				</div>
			</li>
			<li style="float:right">
				<% out.println(posistion); %> of <% out.println(quiz.getQuestions().size()); %> questions
			</li>
			<li>
				<!-- <span><input id="lastQuestion" name="lastQuestion" class="btTxt submit" type="submit" value="<<back"/></span> -->
				<span style="float:right"><input id="nextQuestion" name="nextQuestion" class="btTxt submit" type="submit" value="next>>"/></span>
			</li>
		</ul>
	</div>
	</form>
</div><!--container-->
	
</body>

</html>