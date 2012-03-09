<%@page import="quizweb.message.FriendRequestMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String sender = request.getParameter("sender");
	String receiver = request.getParameter("receiver");
	FriendRequestMessage newFriendRequest = new FriendRequestMessage(
			Integer.parseInt(sender), Integer.parseInt(receiver));
	newFriendRequest.addMessageToDB();
%>