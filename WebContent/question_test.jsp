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
	<form id="form1" action="TakingQuizServlet" name="form1" method="post" >

	<div class="one_column">
		You are taking the quiz <b>
		<% 
			Quiz quiz = Quiz.getQuizByQuizName("Bunny Quiz");
			ArrayList<Question> questions = quiz.getQuestions();
			boolean isPractice = false;
			boolean isFeedback = true;
			boolean isOnepage = true;
			String quizName = quiz.name;
			out.println(quizName);
		%></b> created by <a>
		<%
			String creator = quiz.creator.username;
		%></a>
		<span style="float: right"> 
		</span>
		<hr />
		<ul>
		<%
		ArrayList<Object> userAnswers = new ArrayList<Object>();
		String userAnswer1 = "Leporida";
		userAnswers.add(userAnswer1);
		String userAnswer2 = "Watershi";
		userAnswers.add(userAnswer2);
		int choice3 = 2;
		userAnswers.add(((ArrayList<String>)questions.get(2).question).get(choice3));
		
		String userAnswer4 = "Bugs";
		userAnswers.add(userAnswer4);
		
		int[] choice5 = {3,4,2,1};
		ArrayList<String> userAnswer5 = new ArrayList<String>();
		ArrayList<String> question5 = (ArrayList<String>) questions.get(4).question;
		for (int i = 0; i < choice5.length; i++)
			userAnswer5.add(question5.get(question5.size()/2 + choice5[i]));
		userAnswers.add(userAnswer5);
		
		String[] strings6 = {"T", "U", "N"};
		ArrayList<String> userAnswer6 = new ArrayList<String>();
		Collections.addAll(userAnswer6, strings6);
		userAnswers.add(userAnswer6);
		
		
		
		for (int i = 0; i < questions.size(); i++) {
			Question question = questions.get(i);
			out.println("<li id=\"foli1\" class=\"highlight\"><br />");		
			out.println(question.displayQuestion(i+1));
			out.println("</li>");
			
			out.println("<li id=\"foli1\" class=\"highlight\"><br />");		
			out.println(question.displayQuestionWithAnswer(i+1, userAnswers.get(i)));
			out.println("</li>");

			out.println("<li style=\"float:right\">");
			out.println((i+1) + " out of " + questions.size() + " questions"); 
			out.println("</li>");
		}
		%>
			<li>
				<!-- <span><input id="lastQuestion" name="lastQuestion" class="btTxt submit" type="submit" value="<<back"/></span> -->
				<span style="float:right"><input id="nextQuestion" name="nextQuestion" class="btTxt submit" type="submit" value="next>>" /></span>
			</li>
		</ul>
	</div>
	</form>
	<jsp:include page="/modules/foot.html" />
</div><!--container-->
	
</body>

</html>
