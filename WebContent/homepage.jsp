<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	               String username = request.getParameter("username");
	               out.println("Hi, " + username);
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

		</div>
		
		<div class="three_column_content">		
			<iframe id="contentFrame" class="dynamicFrame" frameborder="0" src="home_feed.jsp"></iframe>
		</div>
	
		<div class="three_column_right">
			<div class="right_block">
				<h4 class="title-style-minor">Announcement</h4>
				<hr />
				<p>Insert announcement here.</p>
			</div>
			<div class="right_block">
				<h4 class="title-style-minor">Popular Quizzes</h4>
				<hr />
				<ol>
					<li>GRE Test Prep</li>
					<li>TOEFL Test Prep</li>
				</ol>
			</div>
			<div class="right_block">
				<h4 class="title-style-minor">Recently Created</h4>
				<hr />
				<ol>
					<li>GRE Test Prep</li>
					<li>TOEFL Test Prep</li>
				</ol>
			</div>
		</div>
		<div class="footer">
			Copyright © toQuiz.me, 2012
		</div>
	</div>
</div>
</body>