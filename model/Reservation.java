package model;

import java.util.Date;

import controller.Utils;

public class Reservation {
	private int id;
	private Date dateStart;
	private int nrDays;
	private double price;
	private String client;
	private Room room;


	public Reservation(int id, Room room, Date dateStart, int nrDays, double price, String client) {
		super();
		this.room = room;
		this.id = id;
		this.dateStart = dateStart;
		this.nrDays = nrDays;
		this.price = price;
		this.client = client;
	}
	
	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getDateStart() {
		return dateStart;
	}
	public void setDateStart(Date dateStart) {
		this.dateStart = dateStart;
	}
	public int getNrDays() {
		return nrDays;
	}
	public void setNrDays(int nrDays) {
		this.nrDays = nrDays;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	
	public String buildInvoice() {
		return "Invoice \n" + client + "\n" + Utils.dateToString(dateStart) + "\n Factura ....";
	}

}
