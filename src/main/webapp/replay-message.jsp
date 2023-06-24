<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <jsp:useBean id="messageBean" class="beans.MessageBean" scope="session"></jsp:useBean>
     <jsp:useBean id="emailBean" class="beans.EmailBean" scope="session"></jsp:useBean>
     <jsp:useBean id="emailService" class="services.EmailService" scope="application"></jsp:useBean>
<!DOCTYPE html>
<%
if (request.getParameter("submit") != null) {
	emailBean.setBody(request.getParameter("replayMessage"));
	emailBean.setRecipient("ljiljana.skavic@hotmail.com");
	emailBean.setSubject("Customer Support Center");
	emailService.replayEmail(emailBean);
	session.setAttribute("resultMessage", "Email sent.");
	response.sendRedirect("messages.jsp");
} else {
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	messageBean.setMessageAsRead(messageId);
	session.setAttribute("resultMessage", "");
}
%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Replay message</title>
</head>
<body>
<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	dto.Message message = messageBean.getMessage(messageId);
	%>

	<div>
		<h2>
		<%=message.getFirstName() + " " + message.getLastName() + " - " + message.getUsername()%>
		(<%=message.getEmail()%>)</h2>
	</div>
	<h3><%=message.getText()%></h3>
	<form method="post" action="replay-message.jsp">
		<h2 align="center">Send message</h2>
		<div style="display: block;">
			<label>Replay:</label>
			<div class="textwrapper">
				<textarea cols="2" rows="10" id="replayMessage" name="replayMessage"></textarea>
			</div>
		</div>
		<input type="hidden" name="messageId" value="<%=request.getParameter("messageId") %>">
		<input type="submit" name="submit" value="Send"
			style="background-color: #d8e2dc">
	</form>

</body>
</html>