<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizweb.*"%>
<%@ page import="quizweb.message.*"%>
<%@ page import="java.util.ArrayList"%>

<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
<script type="text/javascript" src="resources/scripts/functions.js"></script>
<script type="text/javascript" src="resources/scripts/sendFriendRequest.js"></script>

<h4 class="title-style-minor">Friend Requests</h4>
<hr />
<div class=".three_column_content">
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
			out.println("<form name='friend_confirm' action=''>");
			out.println("<input type='hidden' id='senderName' value='" + senderName + "'/>");
			out.println("<input type='hidden' id='senderID' value='" + friendRequest.fromUser + "'/>");
			out.println("<input type='hidden' id='receiverID' value='" + homeUser.userID + "'/>");
			out.println("<input type='submit' id='confirm_btn' value='confirm'/>");
			out.println("<input type='submit' id='reject_btn' value='reject'/>");
			out.println("</form>");
			out.println("</div>");
		}
		out.println("</ul>");
	%>
</div>

