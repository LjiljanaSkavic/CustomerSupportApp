package beans;

import java.io.Serializable;

public class EmailBean implements Serializable {

	private static final long serialVersionUID = 6297449985072140063L;
	private String subject;
	private String msgBody;
	private String recipient;

	public EmailBean() {
		super();
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return msgBody;
	}

	public void setBody(String body) {
		this.msgBody = body;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}

}
