package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class BookingDAO {

	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public BookingDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public BookingDTO makeBooking(String movie, String cinema, String sessionTime, int numAdults, int numConcessions, int numChildren){
		
		//Generate Booking Reference
		int bookingRef = Integer.parseInt(movie + cinema + sessionTime + numAdults + numConcessions + numChildren) + Integer.;
		
		//Find movieID and cinemaID
		
		
		//Reduce number of session seats by 1
		
		//Add booking to booking database
		BookingDTO myBooking = new BookingDTO();
		myBooking.setCinema(cinema);
		myBooking.setMovie(movie);
		myBooking.setBookingRef(bookingRef);
		
		
		return null;
		
	}
	
}
