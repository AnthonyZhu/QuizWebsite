<%@page import="java.util.Date"%>
<%@page import="quizweb.message.Message"%>
<%@page import="java.util.ArrayList"%>
<%@page import="quizweb.message.NoteMessage"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% 
	Date timestamp = new Date();
	NoteMessage nm = new NoteMessage(timestamp,1,2,"");
	ArrayList<Message> noteMessageQueue = nm.getMessagesByUserID(1);
	for(int i =  0; i < noteMessageQueue.size(); i++) {
		Message n = noteMessageQueue.get(i);
		out.println(n.getContent());
		out.println(n.getSender());
		out.println(n.getSender());
	}
	%>
</body>
</html>