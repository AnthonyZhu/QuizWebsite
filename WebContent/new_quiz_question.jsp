<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</head>

<body>
<div class="container">
	<div class="header">
		<h1>toQuiz.Me</h1>
	</div>
	
	<form id="form1" name="form1" method="post" >

	<div class="one_column">
		<h2>Create Quiz: Add Questions</h2>
		<hr />

		<ul>
			<li id="foli7" class="highlight">
				<label class="quiz_title" id="title7" for="Field7">Please choose a question type</label>
				<div>
					<select id="newQuestionType" name="newQuestionType" onchange="showQuestionFrame()" class="field select medium" tabindex="6"> 
						<option value="" selected="selected"></option>
						<option value="Question-Response" >Question-Response</option>
						<option value="Fill in the Blank" >Fill in the Blank</option>
						<option value="Multiple Choice" >Multiple Choice</option>
						<option value="Picture-Response Questions" >Picture-Response Questions</option>
						<option value="Multiple-Answer Questions" >Multiple-Answer Questions</option>
						<option value="Multiple Choice with Multiple Answers" >Multiple Choice with Multiple Answers</option>
						<option value="Matching" >Matching</option>
					</select>
				</div>
			</li>
			</ul>

	<div><iframe id="QuestionFrame" frameborder=0  class="dynamicFrame"  src=""></iframe></div>

		<ul>
			<li>
				<div><input id="saveForm" name="saveForm" class="btTxt submit" type="submit" value="Submit"/></div>
			</li>
		</ul>
	</div>
	</form>
</div><!--container-->
	
</body>



</html>