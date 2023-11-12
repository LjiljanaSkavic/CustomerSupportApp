package dao;

import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import dto.Account;
import services.HashService;

public class AccountDAO {

	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ACCOUNT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM account WHERE username=? AND password=?";

	public static Account selectByUsernameAndPassword(String username, String password)
			throws NoSuchAlgorithmException {
		String passwordHash = HashService.toHexString(HashService.getSHA(password));
		Account account = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username, passwordHash };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ACCOUNT_BY_USERNAME_AND_PASSWORD,
					false, values);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				account = new Account(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("first_name"), rs.getString("last_name"), rs.getInt("account_type_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return account;
	}

}
