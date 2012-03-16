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
<jsp:include page="/modules/head.jsp" />
	<form id="form1" action="TakingQuizServlet" name="form1" method="post" >

	<div class="one_column">
		You are taking the quiz <b>
		<% 
			int position = (Integer) session.getAttribute("position");
			Quiz quiz = (Quiz) session.getAttribute("quiz");
			ArrayList<Question> questions = (ArrayList<Question>) session.getAttribute("questions");
			ArrayList<Object> userAnswers = (ArrayList<Object>) session.getAttribute("userAnswers");
			ArrayList<Integer> indices = (ArrayList<Integer>) session.getAttribute("indices");
			boolean isPractice = (Boolean) session.getAttribute("ispractice");
			boolean isFeedback = (Boolean) session.getAttribute("isfeedback");
			boolean isOnepage = quiz.isOnepage;
			ArrayList<Integer> correctCount = null;;
			if (isPractice) {
				correctCount = (ArrayList<Integer>) session.getAttribute("correct_count");
			}
			String quizName = quiz.name;
			out.println(quizName);
		%></b> created by <a>
		<%
			String creator = quiz.creator.username;
			out.println("<a class=\"link-style-dominant\" href=\"userpage.jsp?id=" + quiz.creator.userID + "\">" + creator + "</a>");
		%></a>
		<span style="float: right"> 
			Time spent: <%
				long startTime = (Long) session.getAttribute("start_time");
				long endTime = new Date().getTime();
				long duration = endTime - startTime;
				if (duration > 3600000)
					out.println("More than 1 hour");
				else {
					long min = duration / 60000;
					long sec = (duration % 60000) / 1000;
					out.println(min + " min   " + sec + " sec");
				}
			%>
		</span>
		<hr />
		<ul>
		<%
		while (true) {
			if (isPractice) {
				if (correctCount.get(position-1).intValue() >= 3) {
					if (position >= questions.size())
						break;
					position++;
					continue;
				}
			}
			out.println("<li id=\"foli1\" class=\"highlight\"><br />");		
			
			out.println("<span class=\"dominant_text\">" + position + ".</span><br /><br />");
			out.println("<span class=\"quiz_title\">");	
			out.println("<span class=\"dominant_text\">Q:</span> ");
			int index = indices.get(position-1).intValue();
			Question question = questions.get(index);
			int type = -1;
			if (question instanceof ResponseQuestion) {
				type = Question.TYPE_RESPONSE;
				ResponseQuestion newQuestion = (ResponseQuestion) question;
				String questionWhole = (String) newQuestion.question;
				out.println(questionWhole);
			} else if (question instanceof FillInBlankQuestion) {
				type = Question.TYPE_BLANK;
				FillInBlankQuestion newQuestion = (FillInBlankQuestion) question;
				ArrayList<String> questionWhole = (ArrayList<String>) newQuestion.question;
				String questionContent = "";
				for (int i = 0; i < questionWhole.size(); i++) {
					questionContent += questionWhole.get(i);
                       if (i<questionWhole.size()-1) {
                       	questionContent += "_______________";
                       }
				}
				out.println(questionContent);
			} else if (question instanceof PictureQuestion) {
				type = Question.TYPE_PICTURE;
				PictureQuestion newQuestion = (PictureQuestion) question;
				String questionWhole = (String) newQuestion.question;
				if (!questionWhole.isEmpty())
					out.println(questionWhole);
				out.println("<img src=\"" + newQuestion.questionURL + "\"" + " />");
			} else if (question instanceof MultipleChoiceQuestion) {
				type = Question.TYPE_CHOICE;
				MultipleChoiceQuestion newQuestion = (MultipleChoiceQuestion) question;
				ArrayList<String> questionWhole = (ArrayList<String>) newQuestion.question;
				out.println(questionWhole.get(0));
				out.println("<ul>");
				for (int i=1;i<questionWhole.size();i++){
					out.println("<li>" + i + ". " + questionWhole.get(i) + "</li>");
				}
				out.println("</ul>");
			} else if (question instanceof MultiAnswerQuestion) {
				type = Question.TYPE_MULTIANSWER;
				MultiAnswerQuestion newQuestion = (MultiAnswerQuestion) question;
				String questionWhole = (String) newQuestion.question;
				out.println(questionWhole);
			} else if (question instanceof MatchingQuestion){
				type = Question.TYPE_MATCHING;
				MatchingQuestion newQuestion = (MatchingQuestion) question;
				ArrayList<String> questionWhole = (ArrayList<String>) newQuestion.question;
				out.println(questionWhole.get(0));
				out.println("<ul>");
				for (int i = 1; i < questionWhole.size() / 2 + 1; i++) {
					out.println("<li>" + i + ". " + questionWhole.get(i) + "</li>");
				}
				out.println("</ul>");
				out.println("<br />");
				out.println("<ul>");
				for (int i = questionWhole.size() / 2 + 1; i < questionWhole.size(); i++) {
					out.println("<li>" + (i-questionWhole.size()/2) + ". " + questionWhole.get(i) + "</li>");
				}
				out.println("</ul>");
			} else if (question instanceof MultiChoiceMultiAnswerQuestion){
				type = Question.TYPE_MULTICHOICEMULTIANSWER;
				MultiChoiceMultiAnswerQuestion newQuestion = (MultiChoiceMultiAnswerQuestion) question;
				ArrayList<String> questionWhole = (ArrayList<String>) newQuestion.question;
				out.println(questionWhole.get(0));
				out.println("<ul>");
				for(int i = 1; i < questionWhole.size(); i++) {
					out.println("<li>" + i + ". " + questionWhole.get(i) + "</li>");
				}
				out.println("</ul>");
			}
			session.setAttribute("question_type" + position, type);
			out.println("</span>");	
			out.println("<br /><br /><p>Please answer below: ");
			if (type == Question.TYPE_MATCHING || type == Question.TYPE_MULTIANSWER || type == Question.TYPE_MULTICHOICEMULTIANSWER)
				out.println(" (Use \";\" to separate your answers)");
			out.println("</p>");		
			out.println("<div>");	
			out.println("<input id=\"Field1\" name=\"user_answer" + position + "\" autofocus=\"autofocus\" type=\"text\" class=\"field text large\" value=\"\" maxlength=\"50\" tabindex=\"1\" onkeyup=\"validateRange(2, 'character');\" />");
			out.println("</div>");
			
			out.println("</li>");
			
			out.println("<li style=\"float:right\">");
			out.println(position + " out of " + questions.size() + " questions"); 
			out.println("</li>");
			if (!isOnepage) {
				break;
			} else {
				if (position >= questions.size())
					break;
				position++;
			}
		}
		session.setAttribute("position", position);
		%>
			<li>
				<!-- <span><input id="lastQuestion" name="lastQuestion" class="btTxt submit" type="submit" value="<<back"/></span> -->
				<span style="float:right"><input id="nextQuestion" name="nextQuestion" class="btTxt submit" type="submit" value="<% if (position >= questions.size()) {out.println("Submit Quiz");} else out.println("next>>"); %>"/></span>
			</li>
		</ul>
	</div>
	</form>
	<jsp:include page="/modules/foot.html" />
</div><!--container-->
	
</body>

</html>
