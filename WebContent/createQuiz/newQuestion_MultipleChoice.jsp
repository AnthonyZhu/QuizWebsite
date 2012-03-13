<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<!-- CSS -->
	<link href="resources/css/main.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/quiz.css" rel="stylesheet">
	
	<!-- JavaScript -->
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/QuestionCreation.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>

		<form>
		<ul>
			<li class="highlight">
				<label class="quiz_title" >Please input question text below:</label>
				<div>
					<textarea id="questionText" name="Field1" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field1">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp; 
					<em class="currently" style="display: inline; ">Currently Used: <var id="rangeUsedMsg8">?</var> characters.</em></label>
				</div>
			</li>
			<li class="highlight">
				<label class="quiz_title">Please input choices below, one line for each choice:</label>
				<div>
					<textarea id="questionChoice" name="Field2" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
				</div>
			</li>
			<li class="highlight">
				<label class="quiz_title">Please input the label of correct choice(label first choice as 1):</label>
				<div>
					<input id="questionAnswer" name="Field3" type="text" class="field text small" onkeyup="validateRange(8, 'character');">
				</div>
			</li>
		</ul>
		</form>