package edu.unsw.comp9321.jdbc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.unsw.comp9321.beans.Movie;

public class ActorDTO {
	
	//fields
	private List<Movie> moviesBeenIn;
	private String firstName;
	private String lastName;
	
	public ActorDTO() {
		moviesBeenIn = new ArrayList<Movie>();
		firstName = "";
		lastName = "";
	}
	
	public ActorDTO(List<Movie> movies, String first, String last) {
		moviesBeenIn = movies;
		firstName = first;
		lastName = last;
	}

	public List<Movie> getMoviesBeenIn() {
		return moviesBeenIn;
	}

	public void setMoviesBeenIn(List<Movie> moviesBeenIn) {
		this.moviesBeenIn = moviesBeenIn;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
}
