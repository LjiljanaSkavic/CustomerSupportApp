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
	private int isRidden;

	public Message() {}
	
	public Message(int id, int userId, String username, String firstName, String lastName, String email, String text, int isRidden) {
		this.id = id;
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.text = text;
		this.isRidden = isRidden;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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

	public int getIsRidden() {
		return isRidden;
	}

	public void setIsRidden(int isRidden) {
		this.isRidden = isRidden;
	}
	
	public String getIsRiddenString() {
		return isRidden == 1 ? "Read" : "Unread";
	}

	@Override
	public String toString() {
		return "Message [id=" + id + ", userId=" + userId + ", username=" + username + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", email=" + email + ", text=" + text + ", isRidden=" + isRidden + "]";
	}
}
