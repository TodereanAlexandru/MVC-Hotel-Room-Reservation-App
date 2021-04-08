package model;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb{

	    private Connection conn;
	    
	   public Connection getConn() {
			return conn;
		}
	private static ConnectionDb instance=null;
	   
	     private  ConnectionDb(){
	    	
	         String connectionUrl = "jdbc:mysql://localhost:3306/hotel_manager?autoReconnect=true&useSSL=false";
	         
	        try {
	        	
	            Class.forName("com.mysql.jdbc.Driver");
	            conn = DriverManager.getConnection(connectionUrl, "root", "1234");
	   
	        } catch (ClassNotFoundException  | SQLException ex) {
	            System.out.println(ex.toString());
	        }
	        
	        if (conn != null) {
	    		System.out.println("You are connected");
	    	} else {
	    		System.out.println("Failed to make connection!");
	    	}
	    }
	     public static ConnectionDb getInstance() {
	    	 if (instance ==null) {
	    		 instance=  new ConnectionDb();
	    	 }
	    	 return instance;
	     }
	     

	}

	    	
	    
	
	
	
