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
<%@ page import="java.util.ArrayList"%>
<html>
<head>
	<link rel="stylesheet" href="/QuizWebsite/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/three_column_layout.css" type="text/css" />
</head>
<body>
<div class="three_column_content">
<h2 class="title_style_minor">Notes</h2>
	<%
		User homeUser = (User) session.getAttribute("user");
		out.println("<ul>");
		ArrayList<NoteMessage> allNotes = NoteMessage.getMessagesByUserID(homeUser.userID);
		for(int i = 0; i < allNotes.size(); i++) {
			NoteMessage note = allNotes.get(i);
			out.println("<li>" + User.getUserByUserID(note.fromUser).username + " said: \"" + note.content+ "\"</li>");
			out.println("<form name=\"note_reply\" action=\"/QuizWebsite/ReplyNoteServlet\" method=\"post\">");
			out.println("<input type=\"hidden\" name=\"senderID\" value=\""+ homeUser.userID +"\">");
			out.println("<input type=\"hidden\" name=\"receiverID\" value=\""+ note.fromUser +"\">");
			out.println("<input type=\"text\" class=\"large\" name=\"replyNote\">");
			out.println("<input type=\"submit\" name=\"sendNote\" value=\"reply\">");
			out.println("</form>");
		}
		out.println("</ul>");
	%>
</div>	
</body>
</html>

