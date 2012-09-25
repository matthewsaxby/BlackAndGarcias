package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Logger;

import edu.unsw.comp9321.beans.Movie;
import edu.unsw.comp9321.common.ServiceLocatorException;

public class ActorDAO {
	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public ActorDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}
	
	public ArrayList<ActorDTO> getAll(){
		ArrayList<ActorDTO> actors = new ArrayList<ActorDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM actor";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				
				String firstName = res.getString("first_name");
				String lastName = res.getString("last_name");
				actors.add(new ActorDTO(new ArrayList<Movie>(), firstName, lastName));
				
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return actors;

		
	}
	
	
}
