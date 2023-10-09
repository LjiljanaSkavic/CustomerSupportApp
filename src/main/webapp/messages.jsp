<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <jsp:useBean id="messageBean" class="beans.MessageBean" scope="session"></jsp:useBean>
<!DOCTYPE html>
<%
if (request.getParameter("submit") != null) {
	session.setAttribute("notification", "");
	response.sendRedirect("messages.jsp");
}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Messages</title>
</head>
<body>
	<div align="right">
			<form method="GET" action="messages.jsp">
				<input type="text" placeholder="Type text" name="search"
					value='<%=request.getParameter("search") == null ? "" : request.getParameter("search")%>'>
				<button type="submit">Search</button>
			</form>
		</div>

	<table border="1" width="100%">
   <tr>
      <th>Username</th>
      <th>First Name</th>
      <th>Last Name</th>
      <th>Email</th>
      <th>Message</th>
      <th>Status</th>
   </tr>
   <%
      for (dto.Message message : messageBean.getMessages(request.getParameter("search"))) {
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
</body>
</html>