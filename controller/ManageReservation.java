package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


import model.ConnectionDb;
import model.Reservation;
import model.Room;

public class ManageReservation {

	private static final String QUERY_INSERT = "INSERT INTO reservations (id_room,date_start, nr_days, client, price) VALUES (?, ?, ?, ?, ?)";
	public static void insert(int idRoom, Date dateStart, int nrDays, String client, double price) {
		ConnectionDb db = ConnectionDb.getInstance();
		Connection conn = db.getConn();
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_INSERT);
			statement.setInt(1,idRoom);
			statement.setDate(2, new java.sql.Date(dateStart.getTime()));
			statement.setInt(3,nrDays);
			statement.setString(4,client);
			statement.setDouble(5,price);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
    private static final String QUERY_FIND_ALL = "SELECT * FROM reservations";
	public static List<Reservation> findAll() {
		ConnectionDb db= ConnectionDb.getInstance();
		Connection conn = db.getConn();
		List<Reservation> allReservations = new ArrayList<Reservation>();
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_FIND_ALL);
			ResultSet resultSet = statement.executeQuery();
			while (resultSet.next()) {
				int id = resultSet.getInt("idreservations");
				int idRoom=resultSet.getInt("id_room");
				Room room = ManageRoom.findById(idRoom);
				Date dateStart=resultSet.getDate("date_start");
				double price=resultSet.getDouble("price");
				String client=resultSet.getString("client");
				int nrDays=resultSet.getInt("nr_days");
				
				Reservation res = new Reservation(id,room,dateStart,nrDays,price,client);
				allReservations.add(res);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return allReservations;
	}
	
	private static final String QUERY_COUNT_BY_CLIENT = "SELECT count(*) FROM reservations WHERE client = ?";
	public static int countByClient(String client) {
		ConnectionDb db = ConnectionDb.getInstance();
		Connection conn = db.getConn();
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_COUNT_BY_CLIENT);
			statement.setString(1, client);
			ResultSet resultSet = statement.executeQuery();
			if (resultSet.next()) {
				return resultSet.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private static final String QUERY_DELETE = "DELETE FROM reservations WHERE idreservations = ?";
	public static void delete(int id) {
		ConnectionDb db = ConnectionDb.getInstance();
		Connection conn = db.getConn();
		try {
			java.sql.PreparedStatement statement = conn.prepareStatement(QUERY_DELETE);
			statement.setInt(1, id);
			statement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
