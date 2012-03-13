<%@page import="quizweb.User"%>
<%@page import="quizweb.message.NoteMessage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizweb.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h4 class="title-style-minor">Notes</h4>
<hr />
<div class=".three_column_content">
	<%
		User homeUser = (User) session.getAttribute("user");
		out.println("<ul>");
		ArrayList<NoteMessage> allNotes = NoteMessage.getMessagesByUserID(homeUser.userID);
		for(int i = 0; i < allNotes.size(); i++) {
			NoteMessage note = allNotes.get(i);
			out.println("<li>" + note.fromUser + "\t" + note.content+ "</li>");
		}
		out.println("<button id='sendNote' onclick='sendNotePopup()'>reply</button>");
		out.println("</ul>");
	%>
</div>	

