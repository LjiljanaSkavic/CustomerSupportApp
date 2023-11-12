package dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dto.Message;
import dto.User;

public class MessageDAO implements Serializable {

	private static final long serialVersionUID = -3703701232632931628L;
	private static ConnectionPool connectionPool = ConnectionPool.getConnectionPool();
	private static final String SQL_SELECT_ALL_MESSAGES = "SELECT m.id, u.id AS user_id, username, first_name, last_name, email, m.text, m.is_read FROM user u INNER JOIN message m ON u.id = m.sender_user_id";
	private static final String SQL_SELECT_MESSAGE_WITH_ID = "SELECT m.id, u.id AS user_id, username, first_name, last_name, email, m.text, m.is_read FROM user u INNER JOIN message m ON u.id = m.sender_user_id WHERE m.id=?";
	private static final String SQL_UPDATE_MESSAGE_AS_READ = "UPDATE message m SET m.is_read='1' where m.id=?";
	private static final String SQL_FIND_MESSAGE_TEXT = "SELECT m.id, u.id AS user_id, username, first_name, last_name, email, m.text, m.is_read FROM user u INNER JOIN message m ON u.id = m.sender_user_id WHERE m.text like CONCAT( '%',?,'%')";
	
	public MessageDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public static ArrayList<Message> getMessages() {
		ArrayList<Message> messages = new ArrayList<Message>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = {};
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_ALL_MESSAGES, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				messages.add(new Message(rs.getInt("id"), rs.getInt("user_id"), rs.getString("username"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("email"), rs.getString("text"), rs.getInt("is_read")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return messages;
	}
	
	public static ArrayList<Message> getMessagesWithText(String text) {
		ArrayList<Message> messages = new ArrayList<Message>();
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { text };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_FIND_MESSAGE_TEXT, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				messages.add(new Message(rs.getInt("id"), rs.getInt("user_id"), rs.getString("username"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("email"), rs.getString("text"), rs.getInt("is_read")));
			}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return messages;
	}
	
	public static Message getMessage(int id) {
		Message message = null;
		Connection connection = null;
		ResultSet rs = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_SELECT_MESSAGE_WITH_ID, false, values);
			rs = pstmt.executeQuery();
			while (rs.next()) {
				message = new Message(rs.getInt("id"), rs.getInt("user_id"), rs.getString("username"), rs.getString("first_name"),
						rs.getString("last_name"), rs.getString("email"), rs.getString("text"), rs.getInt("is_read"));
			System.out.println(message);
				}
			pstmt.close();
		} catch (SQLException exp) {
			exp.printStackTrace();
		} finally {
			connectionPool.checkIn(connection);
		}
		return message;
	}
	
	public static boolean update(int id) {
		boolean isUpdated = false;
		Connection connection = null;
		Object values[] = { id };
		try {
			connection = connectionPool.checkOut();
			PreparedStatement pstmt = DAOUtil.prepareStatement(connection, SQL_UPDATE_MESSAGE_AS_READ, false, values);
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
		return isUpdated;
	}

}
