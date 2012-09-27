package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class UserDAO {
	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public UserDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	

	public void addUserDetails(String username, int userType, String password, String confirmPassword, String email, String confirmEmail){
		try {
			Statement stmnt = connection.createStatement();
			
			String query_cast = "INSERT INTO user_tbl (username, usertype, password, emailAddress)\nVALUES('" + username + "', '" + userType +  "', '" + password + "', '" + email + "')";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}

	// TODO: change this to updateUserDetails and change SQL statement to an UPDATE
	//		1) find user via username
	//		2) get the userid for this row
	// 		3) update this row of database w/ extra data
	public void updateUserDetails(String username, String password,
			String email, String firstName,
			String lastName, String nickName, int yearOfBirth) {
		
		int userToUpdate = 0;
		
		try {
			
			// find user
			Statement stmnt = connection.createStatement();
			String query_cast = "select user_id from user_tbl where username = '" + username + "'";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				userToUpdate = res.getInt("user_id");
			}
			res.close();
			stmnt.close();
			
			// update user
			if (userToUpdate != 0) {
				stmnt = connection.createStatement();
				query_cast = "update user_tbl set username='"+username+"', password='"+password+"', emailAddress='"+email+"', " +
						"firstName='"+firstName+"', lastName='"+lastName+"', nickName='"+nickName+"', " +
						"yearOfBirth="+yearOfBirth+" where user_id = " + userToUpdate;
				stmnt.executeUpdate(query_cast);
				stmnt.close();
			}
				
				
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
	}
	
	public boolean checkUsername(String username) {
		boolean usernameExists = false;
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM user_tbl";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				
				String id = res.getString("username");
				logger.info(" " + id);
				if(id.equals(username)){
					usernameExists = true;
				}
				
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return usernameExists;
	}


	public UserDTO getUserDetails(String username) {
		UserDTO user = new UserDTO(null, -1, null, null, null, -1, null, null);
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM user_tbl";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				if(res.getString("username").equals(username)){
					user.setUsername(res.getString("username"));
					user.setEmailAddress(res.getString("emailAddress"));
					user.setPassword(res.getString("password"));
					user.setFirstName(res.getString("firstName"));
					user.setLastName(res.getString("lastName"));
					user.setNickName(res.getString("nickName"));
					user.setUserType(res.getInt("userType"));
					user.setYearOfBirth(res.getInt("yearOfBirth"));
				}
				
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return user;
	}


	
	
	
}
