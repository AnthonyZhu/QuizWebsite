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
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title>toQuiz.Me</title>
	<meta name="Description" content="A smart quiz website" />
	<meta name="robots" content="all, index, follow" />
	<meta name="distribution" content="global" />
	<link rel="shortcut icon" href="/favicon.ico" />
	
	<link rel="stylesheet" href="/QuizWebsite/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/three_column_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/sendFriendRequest.js"></script>
	<script type="text/javascript" src="resources/scripts/sendNote.js"></script>
	<script type="text/javascript" src="resources/scripts/sendChallenge.js"></script>	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>
<body>
<div class="container" >
	<jsp:include page="/modules/head.jsp" />
	
	<div class="top_container">
		<div class="three_column_left">
		    <ul>	
		    	<li>	
					<h4>
					<% 
						User homeUser = (User) session.getAttribute("user");
			            out.println("Hi, " + homeUser.username);
		            %>
					</h4>
				</li>
			</ul>
			<ul>
				<li>
				<h4 class="title_style_minor">FAVORITES</h4>
				</li>
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
				out.println("<a href=\"createQuiz/new_quiz_settings.jsp\">+ Create Quiz</a>");
				%>
			<hr >
                <%
				out.println("<a href=\"userpage.jsp?id=" + homeUser.userID + "\">My Profile</a>");
				%>	
				
		</div>
		
		<div class="three_column_content">		
			<iframe id="contentFrame" class="dynamicFrame" frameborder="0" src="homepage/home_feed.jsp"></iframe>
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
	<jsp:include page="/modules/foot.html" />
	</div>
</div>
</body>