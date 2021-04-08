package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ConnectionDb;
import model.User;

public class ManageUsers {
	
	
	
private static final String QUERY_FIND = "SELECT * FROM users WHERE username=? AND password=?";
	
	public static User find(String name, String password) {
		ConnectionDb db = ConnectionDb.getInstance();
		Connection conn = db.getConn();
		User user = null;
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_FIND);
			statement.setString(1, name);
			statement.setString(2, password);
			
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("idusers");
				String role=resultSet.getString("role");
				user = new User(id,name,password,role);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	

}
