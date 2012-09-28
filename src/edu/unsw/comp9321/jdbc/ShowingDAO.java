package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class ShowingDAO {
	
	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public ShowingDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	public void createShowing(String showtime, int seats, int cinema_id, int movie_id ) {
		try {
			Date showtimeDate = java.sql.Date.valueOf((showtime.split(" ")[0]));
			Time showtimeTime = java.sql.Time.valueOf(showtime.split(" ")[1].concat(":00"));
			Statement stmnt = connection.createStatement();
			
			String query_cast = "INSERT INTO showing (showingTime, showingDate, availableSeats, cinema_id, movie_id)\nVALUES('" + showtimeTime + "', '" + showtimeDate + "', " + seats +  ", " + cinema_id + ", " + movie_id + ")";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

}
