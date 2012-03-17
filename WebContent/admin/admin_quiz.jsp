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
<%@page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/admin_layout.css" type="text/css" />
<title>Insert title here</title>
</head>

<body>
	<% 
	User homeUser = (User) session.getAttribute("user");
	if (request.getParameterMap().containsKey("keyword")) {
		out.println("<div>");
		String keyword = request.getParameter("keyword");
		Quiz quiz = Quiz.getQuizByQuizName(keyword);
		ArrayList<Quiz> quizList = new ArrayList<Quiz>();
		if (quiz != null) {
			quizList.add(quiz);
		} 
		quizList.addAll(Quiz.getQuizListByTag(keyword));
		
		
		if (quizList.size() == 0) {
			out.println("Quiz name or tag : " + keyword + " not found!");
		} else {
			out.println("<table class=\"adminTable\">");
			out.println("<tr><td>Id</td><td>Name</td><td>Category</td><td>Creator</td><td>Random</td><td>Onepage</td><td>Feedback</td><td>Practice</td></tr>");
			for (int i = 0; i < quizList.size(); i++) {
				Quiz curQuiz = quizList.get(i);
				String random = curQuiz.isRandom?"YES":"NO";
				String onepage = curQuiz.isOnepage?"YES":"NO";
				String feedback = curQuiz.opFeedback?"YES":"NO";
				String practice = curQuiz.opPractice?"YES":"NO";
				
				out.println("<tr><td>" + curQuiz.quizID + "</td><td>" + curQuiz.getQuizStringWithURL(true) + "</td><td>" + curQuiz.category + "</td>");
				out.println("<td>" + curQuiz.creator.getUserStringWithURL(true) + "</td><td>" + random + "</td><td>" + onepage + "</td><td>" + feedback + "</td><td>" + practice + "</td></tr>");
				out.println("</table>");			
				out.println("</div>");
				out.println("<div>");
				out.println("<form action=\"/QuizWebsite/ModifyQuizServlet\" method=\"post\">");
				out.println("<input type=\"hidden\" name=\"target_quizid\" value=\"" + curQuiz.quizID + "\">");
				out.println("<input type=\"hidden\" name=\"source_userid\" value=\"" + homeUser.userID + "\">");
				out.println("<input type=\"hidden\" name=\"keyword\" value=\"" + keyword + "\">");
				out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Delete Quiz\">");
				out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Edit Quiz\">");
				out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Clear Quiz History\">");
				out.println("</form>");
			}
		}
	}

	
	out.println("</div>");
	%>
	
	
</body>
</html>