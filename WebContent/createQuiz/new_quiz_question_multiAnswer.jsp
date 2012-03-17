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
<jsp:include page="/modules/head.jsp" />
	
	<form id="form1" name="form1" action="/QuizWebsite/QuizCreationServlet" method="post">
    <%
    Quiz newQuiz = (Quiz) session.getAttribute("newQuiz");
    %>
	<div class="one_column">
		<%
		String creatorName = newQuiz.creator.username;
		String quizName = newQuiz.name;
		Integer position = (Integer) session.getAttribute("questionPosition");
		String questionType = (String) session.getAttribute("QuestionType"); 
		out.println("<h2>Please add question No." + position + " to \"" + quizName + "\"</h2>");
		out.println("<h3>This question is \"" + questionType + "\" type</h2>");
		
		%>
		<hr />
         
        <ul>
			<li class="highlight">
				<label class="quiz_title" >Please input question text below:<sup>*</sup></label>
				<div>
					<textarea id="questionText" name="Field1" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field1">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp;</label>
					
				</div>
			</li>
			<li class="highlight">
				<label class="quiz_title">Please input your sample answer below: (use comma "," at the end of each answer to specify one answer)<sup>*</sup></label>
				<div>
					<textarea id="questionAnswer" name="Field2" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field2">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp;</label>
				</div>
			</li>
			<li class="highlight">
				<div>
				    <label class="quiz_title">How many right questions need for full credit (should be numeric)<sup>*</sup></label>
					<input id="answerNum" name="Field3" type="text" class="field text large" value="" maxlength="50" tabindex="1" onkeyup="validateRange(2, 'character');" />
				</div>
			</li>
			<li id="foli3" class="highlight">
				<fieldset>
				 	<legend id="title3" class="quiz_title">Should all answers be in order?<sup>*</sup></legend>
					<div>
						<span>
							<input id="Field4_0" name="Field4" type="radio" class="field radio" value="Yes" tabindex="3" checked="checked"  />
							<label class="choice" for="Field4_0" >Yes</label>
						</span>
						<span>
							<input id="Field4_1" name="Field4" type="radio" class="field radio" value="No" tabindex="4" />
							<label class="choice" for="Field4_1" >No</label>
						</span>
					</div>
				</fieldset>
			</li>
		</ul>
		
        <ul>
           <li>
				<div>
				    <label class="quiz_title">Question Score (should be numeric)<sup>*</sup></label>
					<input id="score" name="score" type="text" class="field text large" value="" maxlength="50" tabindex="1" onkeyup="validateRange(2, 'character');" />
				</div>
		  </li>
        </ul>
        
		<ul>
			<li>
				<span><input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Save and Finish"/></span>
				<span><input id="addNewQuestion" name="addNewQuestion" class="btTxt submit" type="submit" value="Add Another Question"/></span>
			</li>
		</ul>
	</div>
	</form>
	<jsp:include page="/modules/foot.html" />
</div><!--container-->
	
</body>



</html>