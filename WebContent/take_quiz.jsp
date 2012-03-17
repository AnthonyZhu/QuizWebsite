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
			int feedbackPosition = -1;
			int feedbackIndex = -1;
			if (isFeedback) {
				feedbackPosition = (Integer) session.getAttribute("feedback_position");
				if (feedbackPosition >= 1)
					feedbackIndex = indices.get(feedbackPosition-1);
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
		if (isFeedback && feedbackIndex >= 0) {
			out.println("<li id=\"foli1\" class=\"highlight\"><br />");
			out.println(questions.get(feedbackIndex).displayQuestionWithAnswer(feedbackPosition, userAnswers.get(feedbackIndex)));
			out.println("</li>");
		}
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
			int index = indices.get(position-1).intValue();
			Question question = questions.get(index);		
			out.println(question.displayQuestion(position));
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
		if (isFeedback) {
			feedbackPosition = position;
			session.setAttribute("feedback_position", feedbackPosition);
		}
		%>

				<!-- <span><input id="lastQuestion" name="lastQuestion" class="btTxt submit" type="submit" value="<<back"/></span> -->
			<li>
				<span style="float:right"><input id="nextQuestion" name="nextQuestion" class="btTxt submit" type="submit" value="<% if (position >= questions.size()) {out.println("Submit Quiz");} else out.println("next>>"); %>"/></span>
			</li>
		</ul>
					<br />
								<br />
								<br />
		<span style="float:right"><input id="nextQuestion" name="nextQuestion" class="btTxt submit" type="submit" value="<% if (position >= questions.size()) {out.println("Submit Quiz");} else out.println("next>>"); %>"/></span>
		<br />
								<br />
	</div>
	</form>
	<jsp:include page="/modules/foot.html" />
</div><!--container-->
	
</body>

</html>
