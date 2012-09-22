package edu.unsw.comp9321.beans;

import java.util.ArrayList;
import java.util.List;

public class User {
	private String username;
	private int userType;	//viewer = 0, owner = 1
	private String nickName;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private String password;
	private String emailAddress;
	
	
	
	private List<Review> myReviews;
	//user preferences
//	private List<Genre> favouriteGenres; need this commented out for now. Not sure how to deal with beans yet.
	private List<Actor> favouriteActors;
	private List<Movie> favouriteMovies;
	private List<Booking> myBookings;
	
	
	
	public User(){
		favouriteMovies = new ArrayList<Movie>();
		myReviews = new ArrayList<Review>();
	}
	
}
