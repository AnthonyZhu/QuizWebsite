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
	<title>Administration</title>
	
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/admin_layout.css" type="text/css" />
	
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/AdminFrame.js"></script>
</head>
<body>
<div class="container">
	<div >
		<h1>toQuiz.Me Admin</h1>
	</div>
	<div class="content-container">
		<div class="navigation">
			<ul>
				<li><a class="link-style-dominant" onclick="showAdminUser()">User</a></li>
				<li><a class="link-style-dominant" onclick="showAdminQuiz()">Quiz</a></li>
				<li><a class="link-style-dominant" onclick="showAdminAnnouncement()">Announcement</a></li>
			</ul>
		</div>
		
		<div class="admin_area">		
			<iframe id="adminFrame" class="adminFrame" frameborder="0" src="admin_user.jsp"></iframe>
		</div>
		
		<div class="footer">
			Copyright © toQuiz.me, 2012
		</div>
	</div>
</div>
</body>