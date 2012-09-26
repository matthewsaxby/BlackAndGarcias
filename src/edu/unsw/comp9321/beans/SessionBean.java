package edu.unsw.comp9321.beans;

import edu.unsw.comp9321.jdbc.UserDTO;

public class SessionBean {
	
	private int userType;
	private UserDTO user;
	
	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

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
}
