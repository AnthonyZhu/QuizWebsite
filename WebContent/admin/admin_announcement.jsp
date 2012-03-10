<%@page import="java.util.ArrayList"%>
<%@page import="quizweb.announcement.Announcement"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
	<% 
	ArrayList<Announcement> allAnnouncement = Announcement.getAllAnnouncements();
	if(allAnnouncement.size() != 0) {
		out.println("<table class=\"adminTable\">");
		out.println("<tr><td>Id</td><td>title</td><td>Content</td><td>Timestamp</td><td>Action</td></tr>");
		for(int i = 0; i < allAnnouncement.size(); i++) {
			Announcement a = allAnnouncement.get(i);
			out.println("<tr><td>"+ a.announcementID +"</td><td>"+ a.title 
					+"</td><td>"+ a.content +"</td><td>" + a.timestamp +"</td></tr>");
		}
		out.println("</table>");
	}
	%>