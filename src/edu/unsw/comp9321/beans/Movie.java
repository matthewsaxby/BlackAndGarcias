package edu.unsw.comp9321.beans;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

public class Movie {
	private String title;
	private List<MovieShowTimes> showing;
	private String poster;
	private List<Actor> mainActors;
	private String movieSynopsis;
	private int currentUserRating;
	private int ratingCount;
	private Time releaseDate;
	private List<Review> reviews;
	private String[] genres;
	private String director;
	private String ageRating;
	
	
	public Movie(){
		reviews = new ArrayList<Review>();
	}
}
