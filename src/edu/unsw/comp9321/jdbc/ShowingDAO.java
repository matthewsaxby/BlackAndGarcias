package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

	public List<ShowingDTO> getShowingsFor(int id) {
		List<ShowingDTO> results = new ArrayList<ShowingDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "select * from showing where movie_id = " + id;
			System.out.println(query_cast);
			ResultSet res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				addDBDeets(results, res);
			}
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return results;
	}
	
//	showing_id int not null generated always as identity constraint showing_pk primary key,
//	showingTime time,
//	showingDate date,
//	availableSeats int,
//	cinema_id int constraint cinema_fk2 references cinema,
//	movie_id int constraint movie_fk2 references movie
	
	private void addDBDeets(List<ShowingDTO> showings, ResultSet res)
			throws SQLException, ParseException {
		int id = res.getInt("showing_id");
		Time showingTime = res.getTime("showingTime");
		Date showingDate = res.getDate("showingDate");
		int availableSeats = res.getInt("availableSeats");
		int cinema_id = res.getInt("cinema_id");
		int movie_id = res.getInt("movie_id");
		
		showings.add(new ShowingDTO(id, showingTime, showingDate, availableSeats,
				cinema_id, movie_id));
	}

}
