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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8856-1">
	<title>Create Your Own Quiz</title>
	
	<!-- CSS -->
	<link href="resources/css/main.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/quiz.css" rel="stylesheet">
	
	<!-- JavaScript -->
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/QuestionCreation.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
	<script type="text/javascript" src="resources/scripts/readFromIframe.js"></script>
	
</head>

<body>
<div class="container">
	<div class="header">
		<h1>toQuiz.Me</h1>
	</div>
	
	<form id="form1" name="form1" action="QuizCreationServlet" method="post">
    <%
    Quiz newQuiz = (Quiz) session.getAttribute("newQuiz");
    %>
	<div class="one_column">
		<%
		String creatorName = newQuiz.creator.username;
		String quizName = newQuiz.name;
		Integer posistion = (Integer) session.getAttribute("questionPosistion");
		String questionType = (String) session.getAttribute("QuestionType"); 
		out.println("<h2>Hi, " + creatorName + ", please add question No." + posistion + " to \"" + quizName + "\"</h2>");
		out.println("<h3>This question is \"" + questionType + "\" type</h2>");
		
		%>
		<hr />
         
        <ul>
			<li class="highlight">
				<label class="quiz_title" >Please input question text below:</label>
				<div>
					<textarea id="questionText" name="Field1" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field1">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp;</label>
					
				</div>
			</li>
			<li class="highlight">
				<label class="quiz_title">Please input choices below, use semicolon ";" at the end of each choice to specify one choice:</label>
				<div>
					<textarea id="questionChoice" name="Field2" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field2">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp;</label>
				</div>
			</li>
			<li class="highlight">
				<label class="quiz_title" >Please input the label of correct choice(label first choice as 1), use comma "," at the end of each answer to specify one answer:</label>
				<div>
					<textarea id="answerText" name="Field3" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field3">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp;</label>
					
				</div>
			</li>
		</ul>
		
        <ul>
           <li>
				<div>
				    <label class="quiz_title">Question Score</label>
					<input id="score" name="score" type="text" class="field text large" value="" maxlength="50" tabindex="1" onkeyup="validateRange(2, 'character');" />
				</div>
		  </li>
        </ul>
        
		<ul>
			<li>
				<div><input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Save and Finish"/></div>
				<div><input id="addNewQuestion" name="addNewQuestion" class="btTxt submit" type="submit" value="Add Another Question"/></div>
			</li>
		</ul>
	</div>
	</form>
</div><!--container-->
	
</body>



</html>