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
<h4 class="title-style-minor">Challenges</h4>
<hr />
<div class=".three_column_content">
	<%
	User homeUser = (User) session.getAttribute("user");
	ArrayList<ChallengeMessage> challengeMessages = ChallengeMessage.getMessagesByUserID(homeUser.userID);
	if(challengeMessages == null){
		out.println("I don't have any challenge.");
	}else if(challengeMessages.size() == 0){
		out.println("I don't have any challenge.");
	}else{
		out.println("<ul>");
		for(int i=0;i<challengeMessages.size();i++){
			ChallengeMessage challenge = challengeMessages.get(i);
			out.println("<li>" + User.getUserByUserID(challenge.fromUser).username + " sent this challenge to you</li>");
		}
		out.println("</ul>");
	}
	%>
</div>	
