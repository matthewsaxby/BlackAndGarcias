package edu.unsw.comp9321.beans;

public class Review {
	private String comment;
	private int rating;
	private Movie reviewedMovie;
	private User writter; 
	
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Movie getReviewedMovie() {
		return reviewedMovie;
	}
	public void setReviewedMovie(Movie reviewedMovie) {
		this.reviewedMovie = reviewedMovie;
	}
	public User getWritter() {
		return writter;
	}
	public void setWritter(User writter) {
		this.writter = writter;
	}
	
}
