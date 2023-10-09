package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.AccountType;

public class AccountTypeDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ACCOUNT_TYPES = "SELECT * FROM account_type";
	
	public static ArrayList<AccountType> getAccountTypes(){
		ArrayList<AccountType> accountTypes = new ArrayList<AccountType>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ACCOUNT_TYPES, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				accountTypes.add(new AccountType(rs.getInt("id"), rs.getString("name")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return accountTypes;
	}

}
