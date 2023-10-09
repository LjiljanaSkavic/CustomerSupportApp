package dto;

import java.io.Serializable;

public class Message implements Serializable {
	
	private static final long serialVersionUID = -8089546464690389739L;
	private int id;
	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String email;
	private String text;
	private int isRead;

	public Message() {}
	
	public Message(int id, int senderUserId, String username, String firstName, String lastName, String email, String text, int isRead) {
		this.id = id;
		this.userId = senderUserId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.text = text;
		this.isRead = isRead;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSenderUserId() {
		return userId;
	}

	public void setSenderUserId(int senderUserId) {
		this.userId = senderUserId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public int getIsRead() {
		return isRead;
	}

	public void setIsRidden(int isRead) {
		this.isRead = isRead;
	}
	
	public String getIsReadString() {
		return isRead == 1 ? "Read" : "Unread";
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", senderUserId=" + userId + ", username=" + username + ", firstName="
				+ firstName + ", lastName=" + lastName + ", email=" + email + ", text=" + text + ", isRead=" + isRead
				+ "]";
	}

}
