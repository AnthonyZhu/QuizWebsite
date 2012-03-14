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
<%@page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h4 class="title-style-minor">Quiz Created</h4>
<hr />
<div class=".three_column_content">
	<%
	User homeUser = (User) session.getAttribute("user");
	ArrayList<QuizCreatedRecord> createdRecord = QuizCreatedRecord.getCreatedQuizByUserID(homeUser.userID);
	if(createdRecord == null){
		out.println("I haven't created any quiz yet.");
	}else if(createdRecord.size() == 0){
		out.println("I haven't created any quiz yet.");
	}else{
		out.println("<ul>");
		for(int i=0;i<createdRecord.size();i++){
			out.println("<li><a href=\"quiz_summary.jsp?id=" + createdRecord.get(i).quiz.quizID + "\">" + createdRecord.get(i).quiz.name + "</a></li>");
		}
		out.println("</ul>");
	}
	%>
</div>	
