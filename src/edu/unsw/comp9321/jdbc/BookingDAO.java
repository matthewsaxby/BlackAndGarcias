package edu.unsw.comp9321.jdbc;

import java.sql.Connection;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
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
	
	public BookingDTO makeBooking(MovieDTO movie, CinemaDTO cinema, String showTime, int numAdults, int numConcessions, int numChildren, int userID){
		
		//Generate Booking Reference
		Random randomGenerator = new Random();
		int bookingRef = numAdults + numConcessions + numChildren + movie.getId() + cinema.getId() + randomGenerator.nextInt(100);
			
		
		//Reduce number of session seats by 1
		//Get current capacity
		int showingID = findShowingID(movie, cinema, showTime);
		int numSeats = 0;
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "select * from showing where showing_id = " + showingID;
			System.out.println(query_cast);
			ResultSet res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				numSeats = res.getInt("availableSeats");
			}
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		//Update to represent new capacity
		numSeats = numSeats - numAdults - numChildren - numConcessions;
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "update showing set availableSeats = " + numSeats + " where showing_id = " + showingID;
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		
		//Separate showTime 
				java.util.Date result = null; 
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
				try {
					result = formatter.parse (showTime);
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				java.sql.Date sqlDate = new java.sql.Date(result.getTime()); 
				java.sql.Time sqlTime = new java.sql.Time(result.getTime());

		//Calculate cost
		int cost = numAdults * 15 + numChildren * 10 + numConcessions * 12;		
				
		//Add booking to booking database
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "INSERT INTO booking (numAdultTickets, numConcessionTickets, numChildTickets, sessionTime, sessionDate, cost, uniqueID, user_id, movie_id, cinema_id) " +
					"\nVALUES(" + numAdults + ", " + numConcessions + ", " + numChildren + ", '" + sqlTime + "', '"+ sqlDate + "', " + cost + ", " + bookingRef + ", " + userID + ", " + movie.getId() + ", " + cinema.getId() + ")";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		//Add booking to DTO
		BookingDTO myBooking = new BookingDTO();
		myBooking.setCinema(cinema.getName());
		myBooking.setMovie(movie.getTitle());
		myBooking.setBookingRef(bookingRef);
		myBooking.setCost(cost);
		myBooking.setNumAdults(numAdults);
		myBooking.setNumChildren(numChildren);
		myBooking.setNumConcessions(numConcessions);
		try {
			myBooking.setSession(formatter.parse (showTime));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return myBooking;
		
	}

	public int findShowingID(MovieDTO movie, CinemaDTO cinema, String showTime) {
		int showingID = -1;
		//Separate showTime 
		java.util.Date result = null; 
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss"); 
		try {
			result = formatter.parse (showTime);
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		java.sql.Date sqlDate = new java.sql.Date(result.getTime()); 
		java.sql.Time sqlTime = new java.sql.Time(result.getTime());

		
		
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "select * from showing where movie_id = " + movie.getId() + " and cinema_id = " + cinema.getId() + " and showingDate = '" + sqlDate + "' and showingTime = '" + sqlTime + "'";
			System.out.println(query_cast);
			ResultSet res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				showingID = res.getInt("showing_id");
			}
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return showingID;
	}

	public boolean noSeatsAvailable(int totalSeatsRequired, int showingID) {
		boolean returnVal = true;
		int numSeats = 0;
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "select * from showing where showing_id = " + showingID;
			System.out.println(query_cast);
			ResultSet res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				numSeats = res.getInt("availableSeats");
			}
			stmnt.close();
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		if(numSeats >= totalSeatsRequired){
			returnVal = false;
		}
		
		
		return returnVal;
	}

	@SuppressWarnings("deprecation")
	public List<BookingDTO> getBookings(int userID, CinemaDAO cinemas, MovieDAO movies) {
		ArrayList<BookingDTO> bookings = new ArrayList<BookingDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM booking WHERE user_id = " + userID;
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				int numAdultTickets = res.getInt("numAdultTickets");
				int numChildTickets = res.getInt("numChildTickets");
				int numConcessionTickets = res.getInt("numConcessionTickets");
				Time sessionTime = res.getTime("sessionTime");
				Date sessionDate = res.getDate("sessionDate");
				int cost = res.getInt("cost");
				int uniqueID = res.getInt("uniqueID");
				String cinemaTitle = cinemas.getCinemaByID(res.getInt("cinema_id")).getName();
				String movieTitle = movies.getMovieById(res.getInt("movie_id")).getTitle();
				
				//sessionDate.setHours(sessionTime.getHours());
				//sessionDate.setMinutes(sessionTime.getMinutes());

				
				
				Date date = new Date();
				if(sessionDate.after(date)){
					bookings.add(new BookingDTO(movieTitle, cinemaTitle, sessionDate, numAdultTickets, numConcessionTickets, numChildTickets, cost, uniqueID));
				}
			}

			res.close();
			stmnt.close();


		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return bookings;
	}
	
	
}
