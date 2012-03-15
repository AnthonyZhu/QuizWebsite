<%@page import="quizweb.User"%>
<%@page import="quizweb.message.NoteMessage"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizweb.*"%>
<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/functions.js"></script>

<h4 class="title-style-minor">Notes</h4>
<hr />
<div class=".three_column_content">
	<%
		User homeUser = (User) session.getAttribute("user");
		out.println("<ul>");
		ArrayList<NoteMessage> allNotes = NoteMessage.getMessagesByUserID(homeUser.userID);
		for(int i = 0; i < allNotes.size(); i++) {
			NoteMessage note = allNotes.get(i);
			out.println("<li>" + User.getUserByUserID(note.fromUser).username + "\t" + note.content+ "</li>");
			out.println("<form name='note_reply' action=''>");
			out.println("<input type='hidden' id='senderID' value='"+ note.fromUser +"'/>");
			out.println("<input type='submit' id='sendNote' onclick='sendNotePopup()' value='reply'/>");
			out.println("</form>");
		}
		out.println("</ul>");
	%>
</div>	

