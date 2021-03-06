<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Welcome</title>
	<link rel="stylesheet" href="/QuizWebsite/resources/css/main.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/login.css" type="text/css" />
	<link rel="stylesheet" href="/QuizWebsite/resources/css/quiz.css" type="text/css" />
</head>
<body>
	<div class="login_header">
		<ul>
			<li>
				<img src="/QuizWebsite/images/toQuiz-logo-big.png">
			</li>
		</ul>
		
		<div class="login_area">
			<ul>
				<li>
					<h4>Have an account? Please log in.</h4>
				</li>
				<li>
					<form action="/QuizWebsite/LoginServlet" method="post">
						User Name: <br />
						<input type="text" class="large" name="username"><br />
						<br />
						Password: <br /><input type="password" class="large" name="password"><br />
						<br />
						<button>Login</button><br />
					</form>
				</li>
				
			</ul>
		</div>
		
		<div class="new_account">
			<ul>
				<li>
					<h4>The Name 
					<%
					String username = request.getParameter("username");
					out.println(username); %> is Already In Use</h4>
				</li>
				<li>
					<form action="/QuizWebsite/NewAccountServlet" method="post">
						User Name: <br />
						<input type="text" class="large" name="username"><br />
						<br />
						Password: <br /><input type="text" class="large" name="password"><br />
						<br />
						<button>Create</button><br />
					</form>
				</li>
			</ul>
		</div>
	</div>
</body>
</html>