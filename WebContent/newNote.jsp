<%@page import="quizweb.message.NoteMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%
	String note = request.getParameter("note");
	String sender = request.getParameter("sender");
	String receiver = request.getParameter("receiver");
	NoteMessage newNote = new NoteMessage(Integer.parseInt(sender), Integer.parseInt(receiver), note);
	newNote.addMessageToDB();
%>