package edu.unsw.comp9321.jdbc;

import java.sql.Time;
import java.util.List;

import edu.unsw.comp9321.jdbc.ActorDTO;
import edu.unsw.comp9321.beans.Genre;
import edu.unsw.comp9321.beans.MovieShowTimes;
import edu.unsw.comp9321.beans.Review;

public class movieDTO {
	
	private String title;
	private List<MovieShowTimes> showing;
	private String poster;
	private List<ActorDTO> mainActors;
	private String movieSynopsis;
	private int currentUserRating;
	private Time releaseDate;
	private List<Review> reviews;
	private List<Genre> movieGenres;
	private String director;
	private String ageRating;
	
	public movieDTO(String title, List<MovieShowTimes> showing, String poster,
			List<ActorDTO> mainActors, String movieSynopsis,
			int currentUserRating, Time releaseDate, List<Review> reviews,
			List<Genre> movieGenres, String director, String ageRating) {
		super();
		this.title = title;
		this.showing = showing;
		this.poster = poster;
		this.mainActors = mainActors;
		this.movieSynopsis = movieSynopsis;
		this.currentUserRating = currentUserRating;
		this.releaseDate = releaseDate;
		this.reviews = reviews;
		this.movieGenres = movieGenres;
		this.director = director;
		this.ageRating = ageRating;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public List<MovieShowTimes> getShowing() {
		return showing;
	}

	public void setShowing(List<MovieShowTimes> showing) {
		this.showing = showing;
	}

	public String getPoster() {
		return poster;
	}

	public void setPoster(String poster) {
		this.poster = poster;
	}

	public List<ActorDTO> getMainActors() {
		return mainActors;
	}

	public void setMainActors(List<ActorDTO> mainActors) {
		this.mainActors = mainActors;
	}

	public String getMovieSynopsis() {
		return movieSynopsis;
	}

	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}

	public int getCurrentUserRating() {
		return currentUserRating;
	}

	public void setCurrentUserRating(int currentUserRating) {
		this.currentUserRating = currentUserRating;
	}

	public Time getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Time releaseDate) {
		this.releaseDate = releaseDate;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}

	public List<Genre> getMovieGenres() {
		return movieGenres;
	}

	public void setMovieGenres(List<Genre> movieGenres) {
		this.movieGenres = movieGenres;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getAgeRating() {
		return ageRating;
	}

	public void setAgeRating(String ageRating) {
		this.ageRating = ageRating;
	}
	
	

}
