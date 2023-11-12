package beans;

import java.io.Serializable;

import dao.UserDAO;
import dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 5022669426687458041L;
	private User user = new User();
	private boolean isLoggedIn = false;

	public UserBean() {
	}

	public boolean checkLogin(String username, String password) {
		if ((this.setUser(UserDAO.selectByUsernameAndPassword(username, password))) != null) {
			this.isLoggedIn = true;
		}
		return this.isLoggedIn;
	}

	public User getUser() {
		return user;
	}

	public User setUser(User user) {
		this.user = user;
		return user;
	}
}
