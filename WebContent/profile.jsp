<%@page import="quizweb.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<% 
session = request.getSession();
User user = (User)session.getAttribute("user");
%>
<head>
	<meta http-equiv="content-type" content="text/html; charset=utf-8" />
	<title><%out.println(user.username);%></title>
	<meta name="Description" content="A smart quiz website" />
	<meta name="robots" content="all, index, follow" />
	<meta name="distribution" content="global" />
	<link rel="shortcut icon" href="/favicon.ico" />
	
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/two_column_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/functions.js"></script>
</head>
<body>
<div class="container" >
	<div class="header">
		<h1>toQuiz.me</h1>
	</div>
	
	<div class="content-container">
		<div class="two_column_left">
			<span style="float:right">
				<button type="submit" name="addAsFriend">
						Send Friend Request
				</button>
				<button type="submit" name="addAsFriend">
						Challenge
				</button>
				<button type="submit" name="addAsFriend">
						Send Note
				</button>
			</span>
			<h1><%out.println(user.username);%></h1>
			<p><span class="dominant_text">33</span> friends | <span class="dominant_text">1</span> quiz created | <span class="dominant_text">55</span> quizzes taken</p>
		</div>
		<div class="two_column_right">
			<h2>Badges</h2>
		</div>
	</div>
	
	
	<div class="content-container">
		<div class="two_column_left">
			<h2>Recent Activities</h2>
		</div>
		<div class="two_column_right">
			<h2>Friends (100)</h2>
			
		</div>
		<div class="two_column_left">
			<h2>Quizzes Created</h2>
			<p><a class="link-style-dominant" href="http://toquiz.me/username=?">GRE Test Prep</a></p>
		</div>
		<div class="two_column_left">
			<h2>Quizzes Taken</h2>
			<p><a class="link-style-dominant" href="http://toquiz.me/username=?">TOEFL Test Prep</a>
			</p>
		</div>
		

	</div>
	
	<div class="footer">
			Copyright © toQuiz.me, 2012
	</div>
	
</div>
</body>