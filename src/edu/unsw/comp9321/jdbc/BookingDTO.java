package edu.unsw.comp9321.jdbc;

import java.util.Date;

public class BookingDTO {

	private String movie;
	private String cinema;
	private Date session;
	private int numAdults;
	private int numConcessions;
	private int numChildren;
	private int cost;
	private int bookingRef;
	
	public BookingDTO() {

	}

	public String getMovie() {
		return movie;
	}

	public void setMovie(String movie) {
		this.movie = movie;
	}

	public String getCinema() {
		return cinema;
	}

	public void setCinema(String cinema) {
		this.cinema = cinema;
	}

	public Date getSession() {
		return session;
	}

	public void setSession(Date session) {
		this.session = session;
	}

	public int getNumAdults() {
		return numAdults;
	}

	public void setNumAdults(int numAdults) {
		this.numAdults = numAdults;
	}

	public int getNumConcessions() {
		return numConcessions;
	}

	public void setNumConcessions(int numConcessions) {
		this.numConcessions = numConcessions;
	}

	public int getNumChildren() {
		return numChildren;
	}

	public void setNumChildren(int numChildren) {
		this.numChildren = numChildren;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public int getBookingRef() {
		return bookingRef;
	}

	public void setBookingRef(int bookingRef) {
		this.bookingRef = bookingRef;
	}
	
	
	
}
