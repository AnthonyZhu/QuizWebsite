<%@page import="quizweb.announcement.Announcement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<% 
	String title = request.getParameter("title");
	String annoucement = request.getParameter("announcement");
	Announcement announce = new Announcement(title, annoucement);
	announce.addAnnouncementToDB();
	%>