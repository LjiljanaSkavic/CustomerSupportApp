package beans;

import java.io.Serializable;

import dao.UserDAO;
import dto.User;

public class UserBean implements Serializable {

	private static final long serialVersionUID = 5022669426687458041L;
	private User user = new User();
	private boolean isLoggedIn = false;
	
	public UserBean() {}
	
	public boolean checkLogin(String username, String password) {
		if ((user = UserDAO.selectByUsernameAndPassword(username, password)) != null) {
			isLoggedIn = true;
			return true;
		}
		return false;
	}
}
