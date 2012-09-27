package edu.unsw.comp9321.beans;

import java.util.ArrayList;
import java.util.List;

import edu.unsw.comp9321.jdbc.ActorDTO;
import edu.unsw.comp9321.jdbc.UserDTO;

public class SessionBean {
	
	private int userType;
	private UserDTO user;
	

	// default to guest
	public SessionBean() {
		userType = 0;
	}
	public SessionBean(int type) {
		userType = type;
	}

	
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	
	
	public UserDTO getUser() {
		return user;
	}
	public void setUser(UserDTO user) {
		this.user = user;
	}
	
}
