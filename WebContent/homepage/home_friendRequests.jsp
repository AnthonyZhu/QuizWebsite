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
<h2 class="title_style_minor">Friend Requests</h2>
	<br />
	<ul>
	<%
		User homeUser = (User) session.getAttribute("user");
		ArrayList<FriendRequestMessage> allFriendRequest = FriendRequestMessage.getMessagesByUserID(homeUser.userID);
		for(int i = 0; i < allFriendRequest.size(); i++) {
			FriendRequestMessage friendRequest = allFriendRequest.get(i);
			if (homeUser.isFriend(User.getUserByUserID(friendRequest.fromUser)) == User.IS_FRIEND) {
			} else {
				if(!friendRequest.isPending())
					continue;
				String senderName = User.getUserByUserID(friendRequest.fromUser).username;
				out.println("<span><img src=\"/QuizWebsite/images/add.png\" style=\"float: left;margin-right:5px\" width=\"40\" height=\"40\"></span>");
				out.println("<div><a target=\"_blank\" class=\"link-style-dominant\" href=\"/QuizWebsite/userpage.jsp?id=" + friendRequest.fromUser + "\">");
				out.println(senderName + "</a>&#160;wants to add you as a friend");
				out.println("<p id='confirm_msg'></p>");
				out.println("<form name=\"friend_confirm\" action=\"/QuizWebsite/ConfirmFriendServlet\" method=\"post\">");
				out.println("<input type=\"hidden\" name=\"senderName\" value=\"" + senderName + "\">");
				out.println("<input type=\"hidden\" name=\"senderID\" value=\"" + friendRequest.fromUser + "\">");
				out.println("<input type=\"hidden\" name=\"receiverID\" value=\"" + homeUser.userID + "\">");
				out.println("<input type=\"submit\" name=\"confirm_btn\" value=\"Confirm\">");
				out.println("<input type=\"submit\" name=\"reject_btn\" value=\"Reject\">");
				out.println("</form>");
				out.println("</div>");
			}
		}
	%>
	</ul>
</div>
</body>
</html>

