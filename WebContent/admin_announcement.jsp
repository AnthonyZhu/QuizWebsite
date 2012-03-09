<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table class="adminTable">
		<tr><td>Id</td><td>Content</td><td>Time stamp</td><td>Action</td></tr>
		<tr id="row1">
			<td>1</td><td>Hello, this is 1 announcement</td><td>2012/2/29</td>
			<td><input type="button" class="deleteButton" value="Delete" onclick="deleteAnnouncement(1)"/></td>
		</tr>
		<tr id="row2">
			<td>2</td><td>Hello, this is 2 announcement</td><td>2012/2/30</td>
			<td><input type="button" class="deleteButton" value="Delete" onclick="deleteAnnouncement(2)"/></td>
		</tr>
		<tr id="row3">
			<td>3</td><td>Hello, this is 3 announcement</td><td>2012/2/31</td>
			<td><input type="button" class="deleteButton" value="Delete" onclick="deleteAnnouncement(3)"/></td>
		</tr>
	</table>
</body>
<head>
	<script type="text/javascript" src="resources/scripts/jquery-1.4.2.min.js"></script>
	<script type="text/javascript" src="resources/scripts/AdminFrame.js"></script>
</head>
</html>