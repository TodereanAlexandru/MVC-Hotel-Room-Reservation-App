package model;

public class Room {
	private int id;
	private String roomNumber;
	private double basePrice;
	
	
	public Room(int id, String roomNumber, double basePrice) {
		super();
		this.id = id;
		this.roomNumber = roomNumber;
		this.basePrice = basePrice;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getRoomNumber() {
		return roomNumber;
	}
	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}
	public double getBasePrice() {
		return basePrice;
	}
	public void setBasePrice(double basePrice) {
		this.basePrice = basePrice;
	}
	
	

}
