<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
	<!-- CSS -->
	<link href="resources/css/main.css" type="text/css" rel="stylesheet" />
	<link href="resources/css/quiz.css" rel="stylesheet">
</head>

		<form>
		<ul>
			<li id="foli8" class="highlight">
				<label class="quiz_title" >Please input question text below, and replace blank with [ ]:</label>
				<div>
					<textarea id="questionText" name="Field8" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field8">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp; 
					<em class="currently" style="display: inline; ">Currently Used: <var id="rangeUsedMsg8">?</var> characters.</em></label>
				</div>
			</li>
			<li id="foli8" class="highlight">
				<label class="quiz_title">Please input answer (words to be filled in the blank) below:</label>
				<div>
					<textarea id="questionAnswer" name="Field8" class="field textarea small" rows="10" cols="50" tabindex="7" onkeyup="validateRange(8, 'character');"></textarea>
					<label for="Field8">Must be between <var id="rangeMinMsg8">10</var> and <var id="rangeMaxMsg8">500</var> characters.&nbsp;&nbsp;&nbsp; 
					<em class="currently" style="display: inline; ">Currently Used: <var id="rangeUsedMsg8">?</var> characters.</em></label>
				</div>
			</li>
		</ul>
		</form>

