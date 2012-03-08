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
	
	<form id="form1" action="QuizSettingServlet" name="form1" method="post" >

	<div class="one_column">
		<h2>
		<% 
		String username = request.getParameter("userName");
		out.println("Welcome " + username + " to create a new Quiz: Quiz Settings");
		out.println("<input name=\"userName\" type=\"hidden\" value=\"" + username + "\" />");
		%>
		</h2>
		<hr />
		<ul>
			<li id="foli1" class="highlight"><span class="quiz_title">Quiz Name</span>
				<div>
					<input id="Field1" name="Field1" type="text" class="field text large" value="" maxlength="50" tabindex="1" onkeyup="validateRange(2, 'character');" />
				</div>
			</li>
			
			<li id="foli9" class="highlight"><span class="quiz_title">Quiz Description</span>
				<div>
					<input id="Field9" name="Field9" type="text" class="field text large" value="" maxlength="50" tabindex="1" onkeyup="validateRange(2, 'character');" />
				</div>
			</li>
			
			<li id="foli2" class="highlight">
				<span id="title2"class="quiz_title">Quiz Category</span>
				<div>
					<select id="Field2" name="Field2" class="field select medium" tabindex="2"> 
						<option value="" selected="selected"></option>
						<option value="History" >History</option>
						<option value="Geography" >Geography</option>
						<option value="Maths" >Math</option>
						<option value="Politics" >Politics</option>
					</select>
				</div>
			</li>
			
			<li id="foli3" class="highlight">
				<fieldset>
				 	<legend id="title3" class="quiz_title">Allow auto-generation from question pool?</legend>
					<div>
						<input id="radioDefault_3" name="Field3" type="hidden" value="" />
						<span>
							<input id="Field3_0" name="Field3" type="radio" class="field radio" value="Yes" tabindex="3" checked="checked"  />
							<label class="choice" for="Field3_0" >Yes</label>
						</span>
						<span>
							<input id="Field3_1" name="Field3" type="radio" class="field radio" value="No" tabindex="4" />
							<label class="choice" for="Field3_1" >No</label>
						</span>
					</div>
				</fieldset>
			</li>
			
			<li id="foli4" class="highlight">
				<fieldset>
					<legend id="title4" class="quiz_title">Question order</legend>
					<div>
						<input id="radioDefault_4" name="Field4" type="hidden" value="" />
						<span>
							<input id="Field4_0" name="Field4" type="radio" class="field radio" value="Allow randomization" tabindex="1" checked="checked"  />
							<label class="choice" for="Field4_0" >Allow randomization</label>
						</span>
						<span>
							<input id="Field4_1" name="Field4" type="radio" class="field radio" value="Set" tabindex="2" />
							<label class="choice" for="Field4_1" >Set</label>
						</span>
					</div>
				</fieldset>
			</li>
			
			<li id="foli5" class="highlight">
				<fieldset><legend id="title5" class="quiz_title">Paging</legend>
					<div>
						<input id="radioDefault_5" name="Field5" type="hidden" value="" />
						<span>
							<input id="Field5_0" name="Field5" type="radio" class="field radio" value="Show all questions on one page" tabindex="3" checked="checked"  />
							<label class="choice" for="Field5_0" >Show all questions on one page</label>
						</span>
						<span>
							<input id="Field5_1" name="Field5" type="radio" class="field radio" value="One page for each question" tabindex="4" />
							<label class="choice" for="Field5_1" >One page for each question</label>
						</span>
					</div>
				</fieldset>
			</li>
			
			<li id="foli6" class="highlight">
				<fieldset>
				 	<legend id="title6" class="quiz_title">Immediate correction?</legend>
					<div>
						<input id="radioDefault_6" name="Field6" type="hidden" value="" />
						<span>
							<input id="Field6_0" name="Field6" type="radio" class="field radio" value="Yes" tabindex="3" checked="checked"  />
							<label class="choice" for="Field6_0" >Yes</label>
						</span>
						<span>
							<input id="Field6_1" name="Field6" type="radio" class="field radio" value="No" tabindex="4" />
							<label class="choice" for="Field6_1" >No</label>
						</span>
					</div>
				</fieldset>
			</li>
			
			<li id="foli7" class="highlight">
				<fieldset>
				 	<legend id="title7" class="quiz_title">Allow practice mode?</legend>
					<div>
						<input id="radioDefault_7" name="Field7" type="hidden" value="" />
						<span>
							<input id="Field7_0" name="Field7" type="radio" class="field radio" value="Yes" tabindex="3" checked="checked"  />
							<label class="choice" for="Field7_0" >Yes</label>
						</span>
						<span>
							<input id="Field7_1" name="Field7" type="radio" class="field radio" value="No" tabindex="4" />
							<label class="choice" for="Field7_1" >No</label>
						</span>
					</div>
				</fieldset>
			</li>
			
			<li id="foli8" class="highlight">
				<label class="quiz_title" id="title8" for="Field8">You can add tags to the quiz(separate by comma; 5 tags maximum)</label>
				<div>
					<input id="Field8" name="Field8" type="text" class="field text large" value="" maxlength="255" tabindex="5" onkeyup="" />
				</div>
			</li>
				
			<li>
				<div><input id="saveSettings" name="saveSettings" class="btTxt submit" type="submit" value="Next"/></div>
			</li>
		</ul>
	</div>
	</form>
</div><!--container-->
	
</body>

</html>