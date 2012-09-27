package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.exception.EmptyResultException;

public class MovieDAO {

	static Logger logger = Logger.getLogger(DerbyDAOImpl.class.getName());
	private Connection connection;
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

	public MovieDAO() throws ServiceLocatorException, SQLException {
		connection = DBConnectionFactory.getConnection();
		logger.info("Got connection");
	}

	// note: this SHOULD be an interface so that the implementation is seperated
	// from the functionality of the class
	// but since this is an assignment and we don't plan on swapping databases,
	// wha'eva.

	// what functions do we actually want?
	// what info do we want from (or want to store to) the db?
	/*
	 * examples: public List<CharacterDTO> findAll(); public void
	 * storeComment(CommentDTO comment); public List<CommentDTO>
	 * getAllComments(); public CharacterDTO findChar(String value) throws
	 * EmptyResultException; public List<CommentDTO> getComments(String
	 * character);
	 */

	public List<MovieDTO> findAll() {
		ArrayList<MovieDTO> movies = new ArrayList<MovieDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM movie";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				
				int id = res.getInt("movie_id");
				logger.info(" " + id);
				String title = res.getString("title");
				logger.info(title);
				String poster = res.getString("poster");
				logger.info(poster);
				String synopsis = res.getString("synopsis");
				logger.info(synopsis);
				double currentUserRating = Double.parseDouble(res
						.getString("user_rating"));
				logger.info(new String("" + currentUserRating));
				Date releaseDate = fmt.parse(res.getString("release_date"));
				int ratingCount = Integer.parseInt(res
						.getString("rating_count"));
				logger.info(new String("" + ratingCount));
				logger.info(synopsis);
				String director = res.getString("director");
				logger.info(director);
				String ageRating = res.getString("age_rating");
				logger.info(ageRating);
				String genresAsString = res.getString("genres");
				String[] genres = genresAsString.split(", ");
				for (String genre : genres) {
					logger.info(genre);
				}
				int cinemaId = Integer.parseInt(res.getString("cinema_id"));
				logger.info(new String("" + cinemaId));
				movies.add(new MovieDTO(title, poster, synopsis,
						currentUserRating, ratingCount, releaseDate,
						genres, director, ageRating, cinemaId));
				
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return movies;
	}

	public List<MovieDTO> findNowShowing() {

		ArrayList<MovieDTO> movies = new ArrayList<MovieDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM movie";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				Date releaseDate = fmt.parse(res.getString("release_date"));
				Calendar currentDate = Calendar.getInstance();
				if (releaseDate.before(currentDate.getTime())) {
					int id = res.getInt("movie_id");
					logger.info(" " + id);
					String title = res.getString("title");
					logger.info(title);
					String poster = res.getString("poster");
					logger.info(poster);
					String synopsis = res.getString("synopsis");
					logger.info(synopsis);
					double currentUserRating = Double.parseDouble(res
							.getString("user_rating"));
					logger.info(new String("" + currentUserRating));
					int ratingCount = Integer.parseInt(res
							.getString("rating_count"));
					logger.info(new String("" + ratingCount));
					logger.info(synopsis);
					String director = res.getString("director");
					logger.info(director);
					String ageRating = res.getString("age_rating");
					logger.info(ageRating);
					String genresAsString = res.getString("genres");
					String[] genres = genresAsString.split(", ");
					for (String genre : genres) {
						logger.info(genre);
					}
					int cinemaId = Integer.parseInt(res.getString("cinema_id"));
					logger.info(new String("" + cinemaId));
					boolean inserted = false;
					for(int i = 0; i < 10 && inserted == false; i++){
						System.out.println("size" + movies.size());
						if(movies.isEmpty()){
							movies.add(0, new MovieDTO(title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
							inserted = true;
						} else if(movies.size() < i+1){
							movies.add(i, new MovieDTO(title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
							inserted = true;
						} else if(currentUserRating > movies.get(i).getCurrentUserRating()){
							movies.add(i, new MovieDTO(title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
							inserted = true;
						}
					}
					
				}
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return movies;
	}
	public List<MovieDTO> findComingSoon() {

		ArrayList<MovieDTO> movies = new ArrayList<MovieDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM movie";
			ResultSet res = stmnt.executeQuery(query_cast);
			logger.info("The result set size is " + res.getFetchSize());
			while (res.next()) {
				Date releaseDate = fmt.parse(res.getString("release_date"));
				Calendar currentDate = Calendar.getInstance();
				if (releaseDate.after(currentDate.getTime())) {
					int id = res.getInt("movie_id");
					logger.info(" " + id);
					String title = res.getString("title");
					logger.info(title);
					String poster = res.getString("poster");
					logger.info(poster);
					String synopsis = res.getString("synopsis");
					logger.info(synopsis);
					double currentUserRating = Double.parseDouble(res
							.getString("user_rating"));
					logger.info(new String("" + currentUserRating));
					int ratingCount = Integer.parseInt(res
							.getString("rating_count"));
					logger.info(new String("" + ratingCount));
					logger.info(synopsis);
					String director = res.getString("director");
					logger.info(director);
					String ageRating = res.getString("age_rating");
					logger.info(ageRating);
					String genresAsString = res.getString("genres");
					String[] genres = genresAsString.split(", ");
					for (String genre : genres) {
						logger.info(genre);
					}
					int cinemaId = Integer.parseInt(res.getString("cinema_id"));
					logger.info(new String("" + cinemaId));
					boolean inserted = false;
					for(int i = 0; i < 10 && inserted == false; i++){
						if(movies.isEmpty()){
							movies.add(0, new MovieDTO(title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
							inserted = true;
						} else if(movies.size() < i+1){
							movies.add(i, new MovieDTO(title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
							inserted = true;
						} else if(releaseDate.before(movies.get(i).getReleaseDate())){
							movies.add(i, new MovieDTO(title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
							inserted = true;
						}
					}
					
				}
			}

			res.close();
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
		return movies;
	}

	public void addMovieAndActors(String title, String actors, String director, String genres, String synopsis, String ageRating, String releaseDate) {
		
		try {
			Statement stmnt = connection.createStatement();
			
			String query_cast = null; // "INSERT INTO movie (title, address, seating_capacity, facilities)\nVALUES('" + name + "', '" + location +  "', " + capacity + ", '" + facilities + "')";
			System.out.println(query_cast);
			stmnt.executeUpdate(query_cast);
			stmnt.close();

		} catch (Exception e) {
			System.out.println("Caught Exception");
			e.printStackTrace();
		}
	}
}
