package dao; 

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.User;

public class UserDAO {
	
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_BY_USERNAME_AND_PASSWORD = "SELECT * FROM user WHERE username=? AND password=?";
	private static final String SQL_SELECT_BY_USERNAME = "SELECT * FROM user WHERE username = ?";
	private static final String SQL_SELECT_BY_ID = "SELECT * FROM user WHERE id = ?";
	private static final String SQL_SELECT_USERS = "SELECT * FROM user";
	private static final String SQL_INSERT = "INSERT INTO user (username, password, firstName, lastName, email, country_id, location_id ) VALUES (?,?,?,?,?,?,?)";
	private static final String SQL_DELETE_USER = "DELETE from user where id=?";
	private static final String SQL_UPDATE_USER = "UPDATE user SET username=?, password=?, firstName=?, lastName=?, email=?, country_id=?, location_id=? WHERE id=?";
	
	
	public UserDAO() {}
	
	public static User selectByUsernameAndPassword(String username, String password){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {username, password};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_USERNAME_AND_PASSWORD, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getInt("isLoggedIn"), 
						rs.getInt("country_id"), rs.getInt("location_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	
	public static ArrayList<User> getUsers(){
		ArrayList<User> users = new ArrayList<User>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_USERS, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getInt("isLoggedIn"), rs.getInt("country_id"), rs.getInt("location_id")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return users;
	}
	
	public static User selectById(int id){
		User user = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_BY_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getInt("id"), rs.getString("username"), rs.getString("password"),
						rs.getString("firstName"), rs.getString("lastName"), rs.getString("email"), rs.getInt("isLoggedIn"), rs.getInt("country_id"), rs.getInt("location_id"));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return user;
	}
	
	public static boolean selectByUsername(String username) {
		boolean doestExist = false;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { username };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection,
					SQL_SELECT_BY_USERNAME, false, values);
			rs = pstmt.executeQuery();
			if (rs.next()){
				doestExist = true;
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return doestExist;
	}
	
	public static boolean insert(User user) {
		boolean isInserted = false;
		Connection connection = null;
		ResultSet generatedKeys = null;
		Object values[] = { user.getUsername(), user.getPassword(), 
				user.getFirstName(), user.getLastName(), user.getEmail(), 
				user.getCountryId(), user.getLocationId()};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_INSERT, true, values);
			pstmt.executeUpdate();
			generatedKeys = pstmt.getGeneratedKeys();
			if(pstmt.getUpdateCount()>0) {
				isInserted = true;
			}
			if (generatedKeys.next())
				user.setId(generatedKeys.getInt(1));
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return isInserted;
	}
	
	public static boolean update(int id, User user) {
		System.out.println("updating in dao");
		boolean isUpdated = false;
		Connection connection = null;
		System.out.println(user.getUsername() + " " + user.getPassword() + " " + user.getFirstName() + " " + user.getLastName()+ " " + user.getEmail()+ " " + user.getCountryId()+ " " + user.getLocationId()+ " " + id);
		Object values[] = { user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail(), user.getCountryId(), user.getLocationId(), user.getId() };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_USER, false, values);
			pstmt.executeUpdate();
			if(pstmt.getUpdateCount()>0) {
				isUpdated = true;
			}
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		System.out.println(isUpdated);
		return isUpdated;
	}
	
	public static boolean delete(int id) {
		boolean idDeleted = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_DELETE_USER, false, values);
			pstmt.executeUpdate();
			idDeleted = true;
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return idDeleted;
	}

}
