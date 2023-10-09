<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<jsp:useBean id="accountBean" class="beans.AccountBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<%
	if (request.getParameter("submit") != null) {
		boolean checkLogin = accountBean.checkLogin(request.getParameter("username"), request.getParameter("password"));
		if (checkLogin) {
			session.setAttribute("notification", "");
			response.sendRedirect("messages.jsp");
		} else {
			session.setAttribute("notification", "Invalid credentials");
		}
	}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
	<form action="login.jsp" method="post">
	   <div class="form-wrapper">
         <div class="field-wrapper">
            <p class="field-name">Username</p>
            <input type="text" name="username" id="username" class="field-value"/>
         </div>
         <div class="field-wrapper">
            <p class="field-name">Password</p>
            <input type="password" name="password" id="password" class="field-value"/>
         </div>
         <button type="submit" name="submit">Login</button>
      </div>
	</form>
</body>
</html>