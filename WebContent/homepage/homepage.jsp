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
<%@ page import="java.util.*"%>
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
	
	<script type="text/javascript" src="/QuizWebsite/resources/scripts/sendFriendRequest.js"></script>
	<script type="text/javascript" src="/QuizWebsite/resources/scripts/sendNote.js"></script>
	<script type="text/javascript" src="/QuizWebsite/resources/scripts/sendChallenge.js"></script>	
	<script type="text/javascript" src="/QuizWebsite/resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="/QuizWebsite/resources/scripts/functions.js"></script>
</head>
<body>
<div class="container" >
	<jsp:include page="/modules/head.jsp" />
	<% User homeUser = (User) session.getAttribute("user"); %>
	<%
		int unreadNotes = NoteMessage.getUnreadCount(homeUser);
		int unreadChallenges = ChallengeMessage.getUnreadCount(homeUser);
		int unreadRequests = FriendRequestMessage.getUnreadCount(homeUser);
	%>
	<div class="top_container">
		<div class="three_column_left">
			<ul>
				<h4><a class="link-style-dominant" href="/QuizWebsite/userpage.jsp?id=<%=homeUser.userID%>" style="float:right">My Profile</a></h4><br /><br />
				<h4 class="title_style_minor">FAVORITES</h4>
				<li><a class="link-style-dominant" onclick="showNewsFeed()">News Feed</a></li>
				<li><a class="link-style-dominant" onclick="showNotes()">Notes<% if (unreadNotes > 0) out.println("(" + unreadNotes + ")");%></a></li>
				<br />
				<h4 class="title_style_minor">NOTIFICATIONS</h4>
				<li><a class="link-style-dominant" onclick="showFriendRequests()">Friend Requests<% if (unreadRequests > 0) out.println("(" + unreadRequests + ")");%></a></li>
				<li><a class="link-style-dominant" onclick="showChallenges()">Challenges<% if (unreadChallenges > 0) out.println("(" + unreadChallenges + ")");%></a></li>
				<br />
				<h4 class="title_style_minor">MY QUIZZES</h4>
				<li><a class="link-style-dominant" onclick="showQuizTaken()">Took</a></li>
				<li><a class="link-style-dominant" onclick="showQuizCreated()">Created</a></li>
				<br /> <br />
				<li>
                <%
				out.println("<a class=\"link-style-dominant\" href=\"http://localhost:8080/QuizWebsite/createQuiz/new_quiz_settings.jsp\">+ Create Quiz</a>");
				%>
				</li>
				<li>
                <%
                if (homeUser.permission == User.IS_ADMIN){
                	out.println("<a class=\"link-style-dominant\" href=\"http://localhost:8080/QuizWebsite/admin/admin.jsp\">Admin</a>");
                }
				%>
				</li>
				<br />
				
				</ul>
		</div>
		
		<div class="three_column_frame">		
			<iframe id="contentFrame" class="dynamicFrame" frameborder="0" style="min-height:800px" src="\QuizWebsite\homepage\home_feed.jsp"></iframe>
		</div>
	
		<div class="three_column_right">
			<div class="right_block">
				<span><a class="link-style-dominant" style="float:right;margin-top:5px;margin-bottom:1px" onclick="showMoreAnnouncements()">+ More</a></span>
				<span style="float:left"><h4 class="title-style-minor">Announcement</h4></span>
				<br />
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
				    }
				}
				%>
				</ol>
					<br />
				<br />
			</div>
			<div class="right_block">
				<span><a class="link-style-dominant" style="float:right;margin-top:5px;margin-bottom:1px" onclick="showMorePopularQuizzes()">+ More</a></span>
				<span style="float:left"><h4 class="title-style-minor">Most Popular Quizzes</h4></span>
				<br />
				<hr />
				<ol>
				<%
					int maxPopularQuizNumber = 5;
					ArrayList<Quiz> quizList = Quiz.getPopularQuiz();
					for (int i = 0; i < Math.min(quizList.size(), maxPopularQuizNumber); i++) {
						Quiz quiz = quizList.get(i);
						out.println("<li>" + quiz.getQuizStringWithURL(false) + "</li>");
					}				
				%>
				</ol>
				<br />
				<br />
			</div>
			<div class="right_block">
				<span><a class="link-style-dominant" style="float:right;margin-top:5px;margin-bottom:1px" onclick="showMoreRecentlyQuizzes()">+ More</a></span>
				<span style="float:left"><h4 class="title-style-minor">Most Recent Quizzes</h4></span>
				<br />
				<hr />
				<ol>
				<%
					int maxRecentQuizNumber = 5;
				    quizList = Quiz.getRecentQuiz();
					for (int i = 0; i < Math.min(quizList.size(), maxRecentQuizNumber); i++) {
						Quiz quiz = quizList.get(i);
						out.println("<li>" + quiz.getQuizStringWithURL(false) + "</li>");
					}
				
				%>
				</ol>
			
			</div>
		</div>
	<jsp:include page="/modules/foot.html" />
	</div>
</div>
</body>