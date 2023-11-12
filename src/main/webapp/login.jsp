<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
%>
<%
response.setHeader("Pragma", "no-cache");
%>
<%
response.setDateHeader("Expires", 0);
%>
<jsp:useBean id="accountBean" class="beans.AccountBean" scope="session"></jsp:useBean>
<%@ page import="java.io.IOException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/login.css" rel="stylesheet">
</head>
<body>
	<form action="login.jsp" method="post">
		<div class="form-wrapper">
			<div class="field-wrapper">
				<p class="field-name">Username</p>
				<input type="text" name="username" id="username" class="field-value" />
			</div>
			<div class="field-wrapper">
				<p class="field-name">Password</p>
				<input type="password" name="password" id="password"
					class="field-value" />
			</div>
			<button type="submit" name="submit" class="login-button">Login</button>
		</div>
	</form>
	<%
	if (request.getParameter("submit") != null) {
		boolean checkLogin = accountBean.checkLogin(request.getParameter("username"), request.getParameter("password"));
		if (checkLogin) {
			response.sendRedirect("messages.jsp");
		} else {
			response.sendRedirect("login.jsp");
			session.setAttribute("notification", "Invalid credentials");
		}
	}
	%>
</body>
</html>