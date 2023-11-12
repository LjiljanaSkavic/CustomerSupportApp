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
<%@ page import="java.util.List"%>
<jsp:useBean id="messageBean" class="beans.MessageBean" scope="session"></jsp:useBean>
<jsp:useBean id="accountBean" class="beans.AccountBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messages</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/messages.css" rel="stylesheet">
</head>
<body>
	<div class=container>
		<form method="GET" action="messages.jsp">
			<div class="logout-container">
				<button type="submit" name="logout" class="logout-button">Logout</button>
			</div>
			<div class="search-wrapper">
				<input type="text" placeholder="Search by message" name="search"
					value='<%=request.getParameter("search") == null ? "" : request.getParameter("search")%>'>
				<button type="submit">Search</button>
			</div>
		</form>

		<%
		List<dto.Message> messages = messageBean.getMessages(request.getParameter("search"));
		if (!messages.isEmpty()) {
		%>
		<table class="table" id="table">
			<tr>
				<th>Username</th>
				<th>First Name</th>
				<th>Last Name</th>
				<th>Email</th>
				<th>Message</th>
				<th>Status</th>
				<th></th>
			</tr>
			<%
			for (dto.Message message : messages) {
				out.print("<tr>");
				out.print("<td>" + message.getUsername() + "</td>");
				out.print("<td>" + message.getFirstName() + "</td>");
				out.print("<td>" + message.getLastName() + "</td>");
				out.print("<td>" + message.getEmail() + "</td>");
				out.print("<td>" + message.getText() + "</td>");
				out.print("<td>" + message.getIsReadString() + "</td>");
				out.print("<td><a href='replay-message.jsp?messageId=" + message.getId() + "'> Replay</a></td>");
				out.println("</tr>");
			}
			%>
		</table>
		<%
		} else {
		%>
		<div class="search-results" id="noResultsMessage">
			<p class="no-results-message">No results found. Please try
				another search term.</p>
		</div>
		<%
		}
		%>
		<%
		if (request.getParameter("logout") != null) {
			session.invalidate();
			response.sendRedirect("login.jsp");
		}
		%>
	</div>
</body>
</html>