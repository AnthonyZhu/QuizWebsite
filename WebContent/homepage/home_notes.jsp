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
	<link rel="stylesheet" href="/QuizWebsite/resources/css/quiz.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/three_column_layout.css" type="text/css" />
</head>
<body>
<div class="three_column_content">
<h2 class="title_style_minor">Notes</h2>
<br >
<ul>
	<%
		User homeUser = (User) session.getAttribute("user");
		ArrayList<NoteMessage> allNotes = NoteMessage.getMessagesByUserID(homeUser.userID);
		if(allNotes != null){
			if(allNotes.size() != 0){
				for(int i = 0; i < allNotes.size(); i++) {
					NoteMessage note = allNotes.get(i);
					out.println("<div class=\"feed_block\">");
					out.println("<div><img src=\"/QuizWebsite/images/message.png\" class=\"medium\"></div>");
					out.println("<div class=\"feed_container\"><span>");
					out.println("<a class=\"link-style-dominant\" href=\"#\">" + User.getUserByUserID(note.fromUser).username + " </a><br /><span>" + note.content+ "</span>");
					out.println("<form name=\"note_reply\" action=\"/QuizWebsite/ReplyNoteServlet\" method=\"post\">");
					out.println("<input type=\"hidden\" name=\"senderID\" value=\""+ homeUser.userID +"\">");
					out.println("<input type=\"hidden\" name=\"receiverID\" value=\""+ note.fromUser +"\">");
					out.println("<input class=\"large\" type=\"text\" class=\"large\" name=\"replyNote\"><br />");
					out.println("<input type=\"submit\" name=\"sendNote\" value=\"reply\">");
					out.println("</form></div></div>");
					note.readMessage();
				}
			}else{
				out.println("I don't have any note yet.");
			}
		}else{
			out.println("I don't have any note yet.");
		}
	%>
	</ul>
</div>	
</body>
</html>

