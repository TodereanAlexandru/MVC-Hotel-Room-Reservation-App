package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.ConnectionDb;
import model.Room;
import model.User;

public class ManageRoom {
	
	private static final String QUERY_FIND = "SELECT * FROM rooms WHERE room_number=? ";
	
	public static Room find(String roomNumber) {
		ConnectionDb db = ConnectionDb.getInstance();
		Connection conn = db.getConn();
		Room room = null;
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_FIND);
			statement.setString(1, roomNumber);	
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				int id = resultSet.getInt("idrooms");
				String roomN=resultSet.getString("room_number");
				Double basePrice=resultSet.getDouble("base_price");
				room = new Room(id,roomN,basePrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}
	
	private static final String QUERY_FIND_BY_ID = "SELECT * FROM rooms WHERE idrooms=? ";
	
	public static Room findById(int id) {
		ConnectionDb db = ConnectionDb.getInstance();
		Connection conn = db.getConn();
		Room room = null;
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_FIND_BY_ID);
			statement.setInt(1, id);	
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				String roomN=resultSet.getString("room_number");
				Double basePrice=resultSet.getDouble("base_price");
				room = new Room(id,roomN,basePrice);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return room;
	}

}
