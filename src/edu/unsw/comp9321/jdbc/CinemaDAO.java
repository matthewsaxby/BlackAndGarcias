package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class CinemaDAO {

	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public CinemaDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	
	public void addCinema (String name, String location, int capacity, String[] amenities) {
		
		String facilities = "";
		for (String amenity : amenities) {
			facilities = facilities.concat(amenity + ", ");
		}
		facilities = facilities.substring(0, facilities.length() - 2); // drop the last ', ' for proness
		
		try {
			Statement stmnt = connection.createStatement();
			
			String query_cast = "INSERT INTO cinema (name, address, seating_capacity, facilities)\nVALUES('" + name + "', '" + location +  "', " + capacity + ", '" + facilities + "')";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
}
