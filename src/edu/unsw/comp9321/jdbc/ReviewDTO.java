package edu.unsw.comp9321.jdbc;

public class ReviewDTO {
	
	private int review_id;
	private String comment;
	private double rating;
	private int user_id; 
	private int movie_id;
	private String reviewer;
	
	
	public ReviewDTO(int review_id, String comment, double rating, int user_id,
			int movie_id) {
		super();
		this.review_id = review_id;
		this.comment = comment;
		this.rating = rating;
		this.user_id = user_id;
		this.movie_id = movie_id;
	}
	public int getReview_id() {
		return review_id;
	}
	public void setReview_id(int review_id) {
		this.review_id = review_id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		this.rating = rating;
	}
	public int getUser_id() {
		return user_id;
	}
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	public int getMovie_id() {
		return movie_id;
	}
	public void setMovie_id(int movie_id) {
		this.movie_id = movie_id;
	}
	public String getReviewer() {
		return reviewer;
	}
	public void setReviewer(String reviewer) {
		this.reviewer = reviewer;
	}

}
