package edu.unsw.comp9321.jdbc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MovieDTO {
	
	private int id;
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private String title;
	private String poster;
	private String movieSynopsis;
	private double currentUserRating;
	private int ratingCount;
	private Date releaseDate;
	private String[] genres;
	private String director;
	private String ageRating;
	private List<ActorDTO> actors;
	private int cinemaId;
	
	
	public MovieDTO(int id, String title, String poster, String movieSynopsis,
			double currentUserRating, int ratingCount, Date releaseDate,
			String[] genres, String director, String ageRating, int cinemaId) {
		super();
		this.id = id;
		this.title = title;
		this.poster = poster;
		this.movieSynopsis = movieSynopsis;
		this.currentUserRating = currentUserRating;
		this.ratingCount = ratingCount;
		this.releaseDate = releaseDate;
		this.genres = genres;
		this.director = director;
		this.ageRating = ageRating;
		this.cinemaId = cinemaId;
		actors = new ArrayList<ActorDTO>();
	}


	public List<ActorDTO> getActors() {
		return actors;
	}

	public void addActor(ActorDTO actor) {
		this.actors.add(actor);
	}
	
	public void setActors(List<ActorDTO> actors) {
		this.actors = actors;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getPoster() {
		return poster;
	}


	public void setPoster(String poster) {
		this.poster = poster;
	}


	public String getMovieSynopsis() {
		return movieSynopsis;
	}


	public void setMovieSynopsis(String movieSynopsis) {
		this.movieSynopsis = movieSynopsis;
	}


	public double getCurrentUserRating() {
		return currentUserRating;
	}


	public void setCurrentUserRating(double currentUserRating) {
		this.currentUserRating = currentUserRating;
	}


	public int getRatingCount() {
		return ratingCount;
	}


	public void setRatingCount(int ratingCount) {
		this.ratingCount = ratingCount;
	}


	public Date getReleaseDate() {
		return releaseDate;
	}


	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}


	public String[] getGenres() {
		return genres;
	}


	public void setGenres(String[] genres) {
		this.genres = genres;
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


	public int getCinemaId() {
		return cinemaId;
	}


	public void setCinemaId(int cinemaId) {
		this.cinemaId = cinemaId;
	}

}
