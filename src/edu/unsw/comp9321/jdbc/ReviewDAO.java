package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;

public class ReviewDAO {
	
	static Logger logger = Logger.getLogger(ReviewDAO.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public ReviewDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	public List<ReviewDTO> getReviewsFor(int id) {
		List<ReviewDTO> results = new ArrayList<ReviewDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "select * from review where movie_id = " + id;
			System.out.println(query_cast);
			ResultSet res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				addDBDeets(results, res);
			}
			res.close();
			stmnt.close();
			
			// add actors
			stmnt = connection.createStatement();
			query_cast = "select user_id, username from user_tbl";
			res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				// find matching movie (by movieID
				for (ReviewDTO review : results) {
					if (review.getUser_id() == res.getInt("user_id")) {
						String username = res.getString("username");
						review.setReviewer(username);
					}
				}
			}
			
			res.close();
			stmnt.close();
			
		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		
		return results;
	}
	
//	review_id int not null generated always as identity constraint review_pk primary key,
//	comment long varchar,
//	rating int,
//	user_id int constraint user_fk4 references user_tbl, 
//	movie_id int constraint movie_fk3 references movie
	
	private void addDBDeets(List<ReviewDTO> reviews, ResultSet res)
			throws SQLException, ParseException {
		int id = res.getInt("review_id");
		String comment = res.getString("comment");
		double rating = res.getInt("rating");
		int user_id = res.getInt("user_id");
		int movie_id = res.getInt("movie_id");
		
		reviews.add(new ReviewDTO(id, comment, rating, user_id, movie_id));
	}
}
