<%@page import="quizweb.message.ChallengeMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String sender = request.getParameter("sender");
	String receiver = request.getParameter("receiver");
	String qid = request.getParameter("quizID");
	String bestScore = request.getParameter("bestScore");
	ChallengeMessage newChllenge = new ChallengeMessage(
			Integer.parseInt(sender), Integer.parseInt(receiver),
			Integer.parseInt(qid), Double.parseDouble(bestScore));
	newChllenge.addMessageToDB();
%>