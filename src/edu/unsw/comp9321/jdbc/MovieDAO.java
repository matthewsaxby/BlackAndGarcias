package edu.unsw.comp9321.jdbc;

import java.sql.Connection;
import java.util.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
				addDBDeets(movies, res);
			}

			res.close();
			stmnt.close();
			
			// add actors
			stmnt = connection.createStatement();
			query_cast = "select * from actor join movie_actors using (actor_id)";
			res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				// find matching movie (by movieID
				for (MovieDTO movie : movies) {
					if (movie.getId() == res.getInt("movie_id")) {
						String firstName = res.getString("first_name");
						String lastName = res.getString("last_name");
						ActorDTO actor = new ActorDTO(firstName, lastName);
						movie.addActor(actor);
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

	private void addDBDeets(List<MovieDTO> movies, ResultSet res)
			throws SQLException, ParseException {
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
		
		movies.add(new MovieDTO(id, title, poster, synopsis,
				currentUserRating, ratingCount, releaseDate,
				genres, director, ageRating, cinemaId));
	}

	public List<MovieDTO> findNowShowing() {

		ArrayList<MovieDTO> movies = new ArrayList<MovieDTO>();
		try {
			Statement stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM movie order by user_rating desc";
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
					System.out.println("size" + movies.size());
					movies.add(new MovieDTO(id, title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
					if (movies.size() == 10){
						break;
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
			String query_cast = "SELECT * FROM movie order by release_date";
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
					System.out.println("size" + movies.size());
					movies.add(new MovieDTO(id, title, poster, synopsis,
									currentUserRating, ratingCount, releaseDate,
									genres, director, ageRating, cinemaId));
					if (movies.size() == 10){
						break;
					}
				}
			}

			res.close();
			stmnt.close();
			
			// add actors
			stmnt = connection.createStatement();
			query_cast = "select * from actor join movie_actors using (actor_id)";
			res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				// find matching movie (by movieID
				for (MovieDTO movie : movies) {
					if (movie.getId() == res.getInt("movie_id")) {
						String firstName = res.getString("first_name");
						String lastName = res.getString("last_name");
						ActorDTO actor = new ActorDTO(firstName, lastName);
						movie.addActor(actor);
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
	
	public List<String> getGenres() {
		List<String> genres = new ArrayList<String>();

		Statement stmnt;
		try {
			stmnt = connection.createStatement();
			String query_cast = "SELECT * FROM movie";
			ResultSet res = stmnt.executeQuery(query_cast);

			while (res.next()) {
				String[] genreList = res.getString("genres").split(", ");
				for (String genre : genreList) {
					if (genres.indexOf(genre) == -1) {
					genres.add(genre);
					}
				}
			}
			
			res.close();
			stmnt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return genres;
	}
	
	public List<MovieDTO> searchOn(String yearRange, String title, String actor, String genre) {
		List<MovieDTO> results = new ArrayList<MovieDTO>();
		
		Statement stmnt;
		try {
			String query_cast;
			ResultSet res;
			//search actors
			ArrayList<Integer> movieIdsToAdd = new ArrayList<Integer>();
			List<String> actorToMovie = new ArrayList<String>();
			if (actor != null && actor.length() > 0 ) {
				stmnt = connection.createStatement();
				query_cast = "select * from actor join movie_actors using (actor_id)";
				res = stmnt.executeQuery(query_cast);
				while (res.next()) {
					if (res.getString("first_name").equals(actor.split(" ")[0])) {
						if (res.getString("last_name").equals(actor.split(" ")[1])) {
							actorToMovie.add(actor + "=" + res.getInt("movie_id"));
						}
					}
				}
				res.close();
				stmnt.close();
			}
			
			stmnt = connection.createStatement();
			query_cast = "SELECT * FROM movie";
			res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				boolean addFlag = true;
				if (title != null && title.length() > 0) {
					if (!res.getString("title").toLowerCase().contains(title.toLowerCase())) {
						addFlag = false;
					} 
				}
				if (actor != null && actor.length() > 0 ) {
					boolean actorFlag = false;
					for (String line : actorToMovie) {
						if (Integer.parseInt(line.split("=")[1]) == res.getInt("movie_id")) {
							if (actor.equals(line.split("=")[0])) {
								actorFlag = true;
							}
						}
					}
					if (!actorFlag) {
						addFlag = false;
					}
				}
				
				if (genre != null && genre.length() > 0) {
					boolean genreFlag = false;
					String[] genreList = res.getString("genres").split(", ");
					for (String moviesGenre : genreList) {
						if (moviesGenre.toLowerCase().equals(genre.toLowerCase())) {
							genreFlag = true;
						}
					}
					if (!genreFlag) {
						addFlag = false;
					}
				}
				if (yearRange != null && yearRange.length() > 0) {
					int year = Integer.parseInt(res.getString("release_date").substring(0, 4));
					int start = Integer.parseInt(yearRange.substring(0, 4));
					int end = Integer.parseInt(yearRange.substring(5, 9));
					if (year <= start || year >= end) {
						addFlag = false;
					}
				}
				if (addFlag) {
					movieIdsToAdd.add(res.getInt("movie_id"));
				}
			}
			res.close();
			stmnt.close();
		
			stmnt = connection.createStatement();
			query_cast = "SELECT * FROM movie";
			res = stmnt.executeQuery(query_cast);
			while (res.next()) {
				if (movieIdsToAdd.contains(res.getInt("movie_id"))) {
					addDBDeets(results, res);
				}
			}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return results;
	}
}
