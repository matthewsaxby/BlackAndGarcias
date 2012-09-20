package comp9321;

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
	private Time releaseDate;
	private List<Review> reviews;
	private List<Genre> movieGenres;
	private String director;
	private String ageRating;
	
	
	public Movie(){
		reviews = new ArrayList<Review>();
	}
}
