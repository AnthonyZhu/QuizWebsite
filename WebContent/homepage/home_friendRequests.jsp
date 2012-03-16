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
<h2 class="title_style_minor">Friend Requests</h2>
	<%
		User homeUser = (User) session.getAttribute("user");
		out.println("<ul>");
		ArrayList<FriendRequestMessage> allFriendRequest = FriendRequestMessage.getMessagesByUserID(homeUser.userID);
		for(int i = 0; i < allFriendRequest.size(); i++) {
			FriendRequestMessage friendRequest = allFriendRequest.get(i);
			if(!friendRequest.isPending())
				continue;
			String senderName = User.getUserByUserID(friendRequest.fromUser).username;
			out.println("<div id='" + friendRequest.fromUser + "'>");
			out.println("<li>" + senderName + "</li>");
			out.println("<p id='confirm_msg'></p>");
			out.println("<form name='friend_confirm' action='/QuizWebsite/ConfirmFriendServlet'>");
			out.println("<input type='hidden' name='senderName' value='" + senderName + "'/>");
			out.println("<input type='hidden' name='senderID' value='" + friendRequest.fromUser + "'/>");
			out.println("<input type='hidden' name='receiverID' value='" + homeUser.userID + "'/>");
			out.println("<input type='submit' name='confirm_btn' value='confirm'/>");
			out.println("<input type='submit' name='reject_btn' value='reject'/>");
			out.println("</form>");
			out.println("</div>");
		}
		out.println("</ul>");
	%>
</div>
</body>
</html>

