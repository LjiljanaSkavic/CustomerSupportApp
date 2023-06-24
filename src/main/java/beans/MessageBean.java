package beans;

import java.io.Serializable;
import java.util.ArrayList;

import dao.MessageDAO;
import dto.Message;

public class MessageBean implements Serializable {

	private static final long serialVersionUID = 967331115079287588L;

	public MessageBean() {
		// TODO Auto-generated constructor stub
	}
	
	public ArrayList<Message> getMessages(String keyword) {
		return keyword == null ? MessageDAO.getMessages() : MessageDAO.getMessagesWithText(keyword);
	}
	
	public Message getMessage(int id) {
		return MessageDAO.getMessage(id);
	}
	
	public boolean setMessageAsRead(int id) {
		return MessageDAO.update(id);
	}

}
