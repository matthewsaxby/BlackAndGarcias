package edu.unsw.comp9321.jdbc;

import java.util.ArrayList;
import java.util.List;

public class ActorDTO {
	
	//fields
	private List<MovieDTO> moviesBeenIn;
	private String firstName;
	private String lastName;
	
	public ActorDTO() {
		moviesBeenIn = new ArrayList<MovieDTO>();
		firstName = "";
		lastName = "";
	}
	
	public ActorDTO(List<MovieDTO> movies, String first, String last) {
		moviesBeenIn = movies;
		firstName = first;
		lastName = last;
	}
	
	public ActorDTO(String first, String last) {
		firstName = first;
		lastName = last;
	}

	public List<MovieDTO> getMoviesBeenIn() {
		return moviesBeenIn;
	}

	public void setMoviesBeenIn(List<MovieDTO> moviesBeenIn) {
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
