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
<jsp:useBean id="messageBean" class="beans.MessageBean" scope="session"></jsp:useBean>
<jsp:useBean id="emailBean" class="beans.EmailBean" scope="session"></jsp:useBean>
<jsp:useBean id="emailService" class="services.EmailService"
	scope="application"></jsp:useBean>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Replay message</title>
<link href="styles/shared.css" rel="stylesheet">
<link href="styles/replay-message.css" rel="stylesheet">
</head>
<body>
	<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	dto.Message message = messageBean.getMessage(messageId);
	if (request.getParameter("cancel") != null) {
		response.sendRedirect("messages.jsp");
	} else if (request.getParameter("save") != null) {
		if (request.getParameter("replayMessage") != "") {
			emailBean.setBody(request.getParameter("replayMessage"));
			emailBean.setRecipient("ljiljana.skavic@hotmail.com");
			emailBean.setSubject("Customer Support Center");
			emailService.replayEmail(emailBean);
			session.setAttribute("resultMessage", "Email sent.");
			response.sendRedirect("messages.jsp");
		} else {
			System.out.println("Please type message");
		}
	} else {
		System.out.println("Message marked as read");
		messageBean.setMessageAsRead(Integer.parseInt(request.getParameter("messageId")));
		session.setAttribute("resultMessage", "");
	}
	%>
	<div class="container">
		<div class="send-email-card">
			<h3>Replay email</h3>
			<label> User: <%=message.getFirstName() + " " + message.getLastName() + " - " + message.getUsername()%>
			</label> <label> Email: <%=message.getEmail()%>
			</label> <label> Message: <%=message.getText()%></label>
			<form method="post">
				<label> Type response : </label>
				<div class="text-wrapper">
					<textarea cols="2" rows="5" id="replayMessage" name="replayMessage"></textarea>
				</div>
				<div class="button-wrapper">
					<button type="submit" name="cancel" class="cancel-button">Cancel</button>
					<button type="submit" name="save" class="save-button">Save</button>
				</div>
			</form>
		</div>
	</div>
</body>
</html>