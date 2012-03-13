<%@page import="java.sql.Timestamp"%>
<%@page import="quizweb.message.FriendRequestMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	int senderID = Integer.parseInt(request.getParameter("senderID"));
	int receiverID = Integer.parseInt(request.getParameter("receiverID"));
	FriendRequestMessage.rejectFriendRequest(senderID, receiverID);
%>