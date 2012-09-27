package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	
	public CinemaDTO getCinemaByID(int cinemaID) {
		CinemaDTO cinema = null;
		
		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM cinema where movie_id = " + cinemaID;
			ResultSet res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				cinema = getDeets(res);
			}
			stmnt.close();
			res.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return cinema;
	}
	
	private CinemaDTO getDeets(ResultSet res) throws SQLException {
		int id = res.getInt("cinema_id");
		logger.info(" " + id);
		String name = res.getString("name");
		logger.info(name);
		String address = res.getString("address");
		logger.info(address);
		int capacity = res.getInt("seating_capacity");
		logger.info(" " + id);
		String facilities = res.getString("facilities");
		String[] amenities = facilities.split(", ");
		
		return new CinemaDTO(name, address, capacity, amenities);
	}
}


	
