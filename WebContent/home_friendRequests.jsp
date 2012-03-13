<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="quizweb.*"%>
<%@ page import="quizweb.message.*"%>
<<<<<<< HEAD
<%@ page import="quizweb.question.*"%>
<%@ page import="quizweb.record.*"%>
<%@ page import="servlet.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>toQuiz.Me</title>
	<meta name="Description" content="A smart quiz website" />
	<meta name="robots" content="all, index, follow" />
	<meta name="distribution" content="global" />
	<link rel="shortcut icon" href="/favicon.ico" />
	
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/three_column_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>
<body>
<div class="container" >
	<div class="header">
		<h1>toQuiz.Me</h1>
	</div>
	<div class="content-container">
		<div class="three_column_left">
		    <ul>
				<h4>
				<% 
				User homeUser = (User) session.getAttribute("user");
	            out.println("Hi, " + homeUser.username);
	            %>
				</h4>
				
			</ul>
			<ul>
				<h4 class="title_style_minor">FAVORITES</h4>
				<li><a class="link-style-dominant" onclick="showNewsFeed()">News Feed</a></li>
				<li><a class="link-style-dominant" onclick="showNotes()">Notes</a></li>
			</ul>
			<ul>
				<h4 class="title_style_minor">NOTIFICATIONS</h4>
				<li><a class="link-style-dominant" onclick="showFriendRequests()">Friend Requests</a></li>
				<li><a class="link-style-dominant" onclick="showChallenges()">Challenges</a></li>
			</ul>
			<ul>
				<h4 class="title_style_minor">MY QUIZZES</h4>
				<li><a class="link-style-dominant" onclick="showQuizTaken()">Took</a></li>
				<li><a class="link-style-dominant" onclick="showQuizCreated()">Created</a></li>
			</ul>
			<hr >
                <%
				out.println("<a href=\"new_quiz_settings.jsp\">+ Create Quiz</a>");
				%>
			<hr >
                <%
				out.println("<a href=\"userpage.jsp?id=" + homeUser.userID + "\">My Profile</a>");
				%>	
				
		</div>
		
		<div class="three_column_content">		

		<h4 class="title-style-minor">Friend Request</h4>
		<hr />
		<div class=".three_column_content">
			<ul>
				<li><!-- fetch 20 most recent records --></li>
			</ul>
		</div>

		</div>
	
		<div class="three_column_right">
			<div class="right_block">
				<h4 class="title-style-minor">Announcement</h4>
				<hr />
				<ol>
				<%
				if(Announcement.allAnnouncements == null){
					out.println("no announcement.");
				}else if(Announcement.allAnnouncements.size()==0){
					out.println("no announcement.");
				}
				else{
					if(Announcement.allAnnouncements.size()<=2){
						out.println(Announcement.allAnnouncements.size());
						for(int i=0;i<Announcement.allAnnouncements.size();i++){
							out.println("<li>" + Announcement.allAnnouncements.get(i).title + ": " + Announcement.allAnnouncements.get(i).content + "</li>");
				        }
				    }else{  
				    	out.println("<li>" + Announcement.allAnnouncements.get(0).title + ": " + Announcement.allAnnouncements.get(0).content + "</li>");
					    out.println("<li>" + Announcement.allAnnouncements.get(1).title + ": " + Announcement.allAnnouncements.get(1).content + "</li>");
					    out.println("<hr ><a class=\"link-style-dominant\" onclick=\"showMoreAnnouncements()\">More Announcements</a>");
				    }
				}
				%>
				</ol>
			</div>
			<div class="right_block">
				<h4 class="title-style-minor">Popular Quizzes</h4>
				<hr />
				<ol>
				<%
				
				%>
				</ol>
			</div>
			<div class="right_block">
				<h4 class="title-style-minor">Recently Created</h4>
				<hr />
				<ol>
				<%
				
				%>
				</ol>
			</div>
		</div>
		<div class="footer">
			Copyright � toQuiz.me, 2012
		</div>
	</div>
</div>
</body>
=======
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
>>>>>>> ajax
