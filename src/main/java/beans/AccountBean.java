package beans;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

import dao.AccountDAO;
import dto.Account;

public class AccountBean implements Serializable {

	private static final long serialVersionUID = 5022669426687458041L;
	private Account account = null;
	private boolean isLoggedIn = false;
	private static final int ADMIN = 2;

	public AccountBean() {
	}

	public boolean checkLogin(String username, String password) throws NoSuchAlgorithmException {
		this.account = AccountDAO.selectByUsernameAndPassword(username, password);
		if (this.account != null) {
			if (this.account.getAccountType() == ADMIN) {
				this.isLoggedIn = true;
			}
		}
		return this.isLoggedIn;
	}
}
