<%@page import="quizweb.User"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link rel="stylesheet" href="resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="resources/css/admin_layout.css" type="text/css" />
<title>Insert title here</title>
</head>
<body>
	<% 
	User homeUser = (User) session.getAttribute("user");
	if (request.getParameterMap().containsKey("keyword")) {
		out.println("<div>");
		String username = request.getParameter("keyword");
		User user = User.getUserByUsername(username);
		if (user == null) {
			out.println("User " + username + " not found!");
		} else {
			out.println("<table class=\"adminTable\">");
			out.println("<tr><td>Id</td><td>Username</td><td>Status</td></tr>");
			String status;
			if (user.permission == User.IS_ADMIN)
				status = "Admin";
			else if (user.isDead)
				status = "Dead";			
			else if (user.isBlocked)
				status = "Blocked";
			else 
				status = "Normal";
			out.println("<tr><td>" + user.userID + "</td><td>" + user.getUserStringWithURL(true) + "</td><td>" + status + "</td></tr>");
			out.println("</table>");			
			out.println("</div>");
			out.println("<div>");
			out.println("<form action=\"/QuizWebsite/ModifyUserServlet\" method=\"post\">");
			out.println("<input type=\"hidden\" name=\"admin_userid\" value=\"" + homeUser.userID + "\">");
			out.println("<input type=\"hidden\" name=\"removed_userid\" value=\"" + user.userID + "\">");
			if (user.isDead) {
				out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Unremove User\">");
			} else { 
				out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Remove User\">");
				if (user.isBlocked) {
					out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Unblock User\">");
				} else {
					out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Block User\">");
					if (!(user.permission == User.IS_ADMIN)) 
						out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Promote User\">");
					else
						out.println("<input type=\"submit\" id=\"submit_btn\" name=\"submit_btn\" value=\"Unpromote User\">");
				}
			}
			out.println("</form>");
		}
	}

	
	out.println("</div>");
	%>
	
</body>
</html>