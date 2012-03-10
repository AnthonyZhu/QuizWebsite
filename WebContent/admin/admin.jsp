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
	
	<link rel="stylesheet" href="../resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="../resources/css/admin_layout.css" type="text/css" />
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
			<div id="searchArea">
				<span>Keyword </span>
				<input type="text"  id="inputBox" onkeydown="if(event.keyCode==13) search()" />
				<input type="submit" value="Search" onclick="search()"/>
			</div>
			<div id="createAncmt">
				<form id="create_announcement" action="">
					title: <input type="text" id="title" />
					<textarea id="new_announcement" rows="10" cols="30"></textarea>
					<span id="error1" style="display:none"> title cannot be empty.</span>
					<span id="error2" style="display:none"> announcement cannot be empty.</span>
					<input type="submit" id="createButton" value="Create" onclick="createAnnouncement()" /><!--
					<input type="button" id="submitButton" value="Submit" onclick="submitAnnouncement()" />
				--></form>
			</div>
			<iframe id="adminFrame" class="adminFrame" frameborder="0" src="admin_user.jsp"></iframe>
		</div>
		
		<div class="footer">
			Copyright © toQuiz.me, 2012
		</div>
	</div>
</div>
</body>

<head>
	<script type="text/javascript" src="../resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="../resources/scripts/AdminFrame.js"></script>
</head>

</html>
