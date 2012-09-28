package edu.unsw.comp9321.jdbc;


public class UserDTO {
	private int id;
	private String username;
	private int userType;
	private String nickName;
	private String firstName;
	private String lastName;
	private int yearOfBirth;
	private String password;
	private String emailAddress;
	
	public UserDTO(String username, int userType, String nickName, String firstName, String lastName, int yearOfBirth, String password, String emailAddress) {
		super();
		this.setUsername(username);
		this.setUserType(userType);
		this.setNickName(nickName);
		this.setFirstName(firstName);
		this.setLastName(lastName);
		this.setYearOfBirth(yearOfBirth);
		this.setPassword(password);
		this.setEmailAddress(emailAddress);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public int getUserType() {
		return userType;
	}

	public void setUserType(int userType) {
		this.userType = userType;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public int getYearOfBirth() {
		return yearOfBirth;
	}

	public void setYearOfBirth(int yearOfBirth) {
		this.yearOfBirth = yearOfBirth;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	
}
