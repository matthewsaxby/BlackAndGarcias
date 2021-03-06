package edu.unsw.comp9321.logic;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.DefaultFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;

import edu.unsw.comp9321.beans.SessionBean;
import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.jdbc.ActorDAO;
import edu.unsw.comp9321.jdbc.ActorDTO;
import edu.unsw.comp9321.jdbc.BookingDAO;
import edu.unsw.comp9321.jdbc.BookingDTO;
import edu.unsw.comp9321.jdbc.CastDAO;
import edu.unsw.comp9321.jdbc.CinemaDAO;
import edu.unsw.comp9321.jdbc.CinemaDTO;
import edu.unsw.comp9321.jdbc.CommentDTO;
import edu.unsw.comp9321.jdbc.DerbyDAOImpl;
import edu.unsw.comp9321.jdbc.MovieDAO;
import edu.unsw.comp9321.jdbc.MovieDTO;
import edu.unsw.comp9321.jdbc.MySQLDAOImpl;
import edu.unsw.comp9321.jdbc.CharacterDTO;
import edu.unsw.comp9321.jdbc.ReviewDAO;
import edu.unsw.comp9321.jdbc.ReviewDTO;
import edu.unsw.comp9321.jdbc.ShowingDAO;
import edu.unsw.comp9321.jdbc.ShowingDTO;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;
import edu.unsw.comp9321.mail.MailSender;
import edu.unsw.comp9321.mail.MailSenderTest;

/**
 * Servlet implementation class Controller
 */
public class Controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Controller.class.getName());
	private CastDAO cast;
	private MovieDAO movies;
	private ActorDAO actors;
	private UserDAO users;
	private UserDTO currentUser;
	private CinemaDAO cinemas;
	private ShowingDAO showings;
	private SessionBean sessionBean;
	private BookingDAO bookings;
	private ReviewDAO reviews;
	
	DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");
       
	// upload stuff
	private static final String UPLOAD_DIRECTORY = "images";
	private static final int THRESHOLD_SIZE = 1024 * 1024 * 3;    // 3MB
	private static final int MAX_FILE_SIZE = 1024 * 1024 * 40;    // 40MB
	private static final int REQUEST_SIZE = 1024 * 1024 * 50;    // 50MB
	
    /**
     * @throws ServletException 
     * @see HttpServlet#HttpServlet()
     */
    public Controller() throws ServletException {
    	// TODO Auto-generated constructor stub
        super();
        try {
        	
        	movies = new MovieDAO();
        	users = new UserDAO();
        	actors = new ActorDAO();
//			cast = new DerbyDAOImpl();
			cinemas = new CinemaDAO();
			showings = new ShowingDAO();
			bookings = new BookingDAO();
			reviews = new ReviewDAO();
		} catch (ServiceLocatorException e) {
			logger.severe("Trouble connecting to database "+e.getStackTrace());
			throw new ServletException();
		} catch (SQLException e) {
			logger.severe("Trouble connecting to database "+e.getStackTrace());
			throw new ServletException();
		}
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request,response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String forwardPage = "";
		sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		request.setAttribute("genreList", movies.getGenres());
		request.setAttribute("actorList", actors.getAll());
		if (request.getParameter("action") == null) {
			List<MovieDTO> nowShowing = movies.findNowShowing(3);
			request.setAttribute("nowShowing",  nowShowing);
			List<MovieDTO> comingSoon = movies.findComingSoon(3);
			request.setAttribute("comingSoon",  comingSoon);
			forwardPage = "index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if(request.getParameter("action").equals("nowShowing")){
			
			List<MovieDTO> resSet = movies.findNowShowing(10);
			request.setAttribute("movieDeets",  resSet);
			forwardPage = "nowShowing.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			System.out.println("serving home page");
			dispatcher.forward(request, response);
		} else if(request.getParameter("action").equals("comingSoon")){
			
			List<MovieDTO> resSet = movies.findComingSoon(10);
			request.setAttribute("movieDeets",  resSet);
			forwardPage = "comingSoon.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if(request.getParameter("action").equals("signup")){
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String confirmPassword = request.getParameter("confirmPassword");
			String email = request.getParameter("email");
			String confirmEmail = request.getParameter("confirmEmail");
			
			//Check is the data that the user has entered is valid
			String errorMessage = "";
			boolean error = false;
			if(!email.equals(confirmEmail)){
				errorMessage = "Emails don't match";
				error = true;
			}
			if(!password.equals(confirmPassword)){
				errorMessage = "Passwords don't match";
				error = true;
			}
			if(username == "" && password == "" && email == ""){
				errorMessage = "Not all values completed";
				error = true;
			}
			if(users.checkUsername(username)){
				errorMessage = "Username already in use, please choose a different username";
				error = true;
			}
			
			if(!error){
				System.out.println("i made it");
				//store data to database
				// userttype to 0, need to validate to get to 1, presumably, admins added by direct insert
				users.addUserDetails(username, 0, password, confirmPassword, email, confirmEmail); 
				//send email
				//TODO: what does 'confirming' actually do? nothing?
				MailSenderTest ms = new MailSenderTest();
				ms.sendConfirmationEmail(username, email);
				//return to check email page
				List<MovieDTO> nowShowing = movies.findNowShowing(3);
				request.setAttribute("nowShowing",  nowShowing);
				List<MovieDTO> comingSoon = movies.findComingSoon(3);
				request.setAttribute("comingSoon",  comingSoon);
				forwardPage = "index.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			} else {
				//return to check email page
				request.setAttribute("message",  errorMessage);
				forwardPage = "signupError.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			}
		} else if(request.getParameter("action").equals("confirmSignup")){
			
			if (users.getUserDetails(request.getParameter("username")).getUserType() == 0) {
				users.validateUser(request.getParameter("username"));
			}
			forwardPage = "confirmSignup.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if(request.getParameter("action").equals("editProfile")){
			int state;
			if (request.getParameter("state") == null) {
				state = 0;
				request.setAttribute("state", state);
			} else {
				state = Integer.parseInt(request.getParameter("state"));
				request.setAttribute("state", state);
			}
			boolean errors = false;
			if (state > 0) {
				//Get data from form
				String username = sessionBean.getUser().getUsername();
				String password;
				if (request.getParameter("password") != "" && request.getParameter("password") != null) {
					if (!request.getParameter("password").equals(request.getParameter("confirmPassword"))) {
						password = sessionBean.getUser().getPassword();
						request.setAttribute("errormsg", "Passwords dont match.");
						request.setAttribute("state", 0);
						forwardPage = "editProfile.jsp";
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
						dispatcher.forward(request, response);
						errors = true;
					} else {
						password = request.getParameter("password");
					}
				} else {
					password = sessionBean.getUser().getPassword();
				}
				String firstName;
				if (request.getParameter("firstName") != "" && request.getParameter("firstName") != null) {
					firstName = request.getParameter("firstName");
				} else {
					if (sessionBean.getUser().getFirstName() != null) {
						firstName = sessionBean.getUser().getFirstName();
					} else {
						firstName = null;
					}
				}
				
				String lastName;
				if ( request.getParameter("lastName") != "" && request.getParameter("lastName") != null) {
					lastName = request.getParameter("lastName");
				} else {
					if (sessionBean.getUser().getLastName() != null) {
						lastName = sessionBean.getUser().getLastName();
					} else {
						lastName = null;
					}
				}
	
				String email;
				if ( request.getParameter("emailAddress") != "" && request.getParameter("emailAddress") != null) {
					email = request.getParameter("emailAddress");
				} else {
					if (sessionBean.getUser().getEmailAddress() != null) {
						email = sessionBean.getUser().getEmailAddress();
					} else {
						email = null;
					}
				}
				
				String nickName;
				if ( request.getParameter("nickName") != "" && request.getParameter("nickName") != null) {
					nickName = request.getParameter("nickName");
				} else {
					if (sessionBean.getUser().getNickName() != null) {
						nickName = sessionBean.getUser().getNickName();
					} else {
						nickName = null;
					}
				}
				
				int yearOfBirth;
				if (request.getParameter("yearOfBirth") != "" && request.getParameter("yearOfBirth") != null) {
					yearOfBirth = Integer.parseInt(request.getParameter("yearOfBirth"));
				} else {
					if (sessionBean.getUser().getYearOfBirth() != 0) {
						yearOfBirth = sessionBean.getUser().getYearOfBirth();
					} else {
						yearOfBirth = 0;
					}
				}
				
				//send the data to the database
				users.updateUserDetails(username, password, email, firstName, lastName, nickName, yearOfBirth);
				sessionBean.setUser(users.getUserDetails(username));
			}
			//send user to confirmation page
			if (!errors) {
				forwardPage = "editProfile.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			}
		} else if(request.getParameter("action").equals("viewProfile")){
			//Get data from form
			//send the data to the database
			//send user to confirmation page
			forwardPage = "myProfile.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("addCinema")){
			String name = request.getParameter("name");
			String location = request.getParameter("location");
			int capacity = Integer.parseInt(request.getParameter("capacity"));
			String[] amenities = request.getParameterValues("amenities");
			
			cinemas.addCinema(name, location, capacity, amenities);
			
			request.setAttribute("adminResponse", new String("Cinema Added!"));
			forwardPage = "admin.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("addMovie")){
			
			String title = request.getParameter("title");
			String poster = request.getParameter("poster");
			String actors = request.getParameter("actors");
			String genres = request.getParameter("genres");
			String director = request.getParameter("director");
			String synopsis = request.getParameter("synopsis");
			String ageRating = request.getParameter("agerating");
			String releaseDate = request.getParameter("releasedate");
			
			movies.addMovieAndActors(title, poster, actors, director, genres, synopsis, ageRating, releaseDate);
			
			
//			file upload stuff:
//			http://stackoverflow.com/questions/2422468/how-to-upload-files-to-server-using-jsp-servlet
			
			request.setAttribute("adminResponse", new String("Movie Added!"));
			forwardPage = "admin.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("login")) {
			UserDTO attempedLogin = users.getUserDetails(request.getParameter("username"));
			if (!request.getParameter("password").equals(attempedLogin.getPassword())) {
				request.setAttribute("failedLogin", new String("Incorrect Password! Please try again"));
				List<MovieDTO> nowShowing = movies.findNowShowing(3);
				request.setAttribute("nowShowing",  nowShowing);
				List<MovieDTO> comingSoon = movies.findComingSoon(3);
				request.setAttribute("comingSoon",  comingSoon);
				forwardPage = "index.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			} else {
				sessionBean.setUserType(attempedLogin.getUserType());
				sessionBean.setUser(attempedLogin);
				request.getSession().setAttribute("sessionBean", sessionBean);
				List<MovieDTO> nowShowing = movies.findNowShowing(3);
				request.setAttribute("nowShowing",  nowShowing);
				List<MovieDTO> comingSoon = movies.findComingSoon(3);
				request.setAttribute("comingSoon",  comingSoon);
				forwardPage = "index.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			}
		} else if (request.getParameter("action").equals("logout")) {
			List<MovieDTO> nowShowing = movies.findNowShowing(3);
			request.setAttribute("nowShowing",  nowShowing);
			List<MovieDTO> comingSoon = movies.findComingSoon(3);
			request.setAttribute("comingSoon",  comingSoon);
			forwardPage = "logout.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("search")) {
			
			if (request.getParameter("state") != null) {
				
				// if needed : .equals("results")
				// collect input
				String yearRange = request.getParameter("yearrange");
				String title = request.getParameter("title");
				String actor = request.getParameter("actor");
				String genre = request.getParameter("genre");
				//build results object
				List<MovieDTO> results = movies.searchOn(yearRange, title, actor, genre);
				//send results as attribute
				request.setAttribute("resultData", results);
			}
			// otherwise just serve the page as per normal
			
			
			forwardPage = "advancedSearch.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("details")) {
			String targetPage = request.getParameter("viewDetailsOnMovie");

			List<CinemaDTO> relatedCinemas  = new ArrayList<CinemaDTO>();
			// find the movie that details have been requested for
			MovieDTO targetMovie = movies.getMovieByIdIgnoreReleaseDate(Integer.parseInt(targetPage));
			List<ShowingDTO> showingList = showings.getShowingsFor(targetMovie.getId());
			List<Integer> added = new ArrayList<Integer>();
			for (ShowingDTO showing : showingList) {
				if (!added.contains(showing.getCinema_id())) {
					Date curDate = new Date();	
					if (showing.getShowingDate().after(curDate)) {
						relatedCinemas.add(cinemas.getCinemaByID(showing.getCinema_id()));
						added.add(showing.getCinema_id());
					}
				}
			}
			
			
			// find the reviews for this movie
			List<ReviewDTO> reviewList = reviews.getReviewsFor(targetMovie.getId());
			// get user that did review
			
			// set this as session data so the details page can access it.
			Calendar currentDate = Calendar.getInstance();
			if (targetMovie.getReleaseDate().after(currentDate.getTime())) {
				request.setAttribute("released", false);
			} else {
				request.setAttribute("released", true);
			}
			request.setAttribute("targetMovie", targetMovie);
			request.setAttribute("relatedCinemas", relatedCinemas);
			request.setAttribute("reviews", reviewList);
			
			// need actor + cinema data too
			
			forwardPage = "details.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("mapMtoC")) {

			int state;
			if (request.getParameter("state") == null) {
				state = 0;
				request.setAttribute("state", state);
			} else {
				state = Integer.parseInt(request.getParameter("state"));
				request.setAttribute("state", state);
			}
			
			if (state > 0) {
				// process input.
				
				// get movie
				int movieId = Integer.parseInt(request.getParameter("movie"));
				MovieDTO movieToLink = movies.getMovieByIdIgnoreReleaseDate(movieId); // do i even need this?
				
				// get cinema(s)
				String[] selectedCinemas = request.getParameterValues("cinemaSelect");
				int i = 0;
				for (String cinemaID : selectedCinemas) {
					if (!cinemaID.equals("")) {
						CinemaDTO cinemaToLink = cinemas.getCinemaByID(Integer.parseInt(cinemaID));
						// for each get showtimes
						String[] showtimes = request.getParameterValues("showtime" + i);
							// build a link in database for each showtime 
							for (String showtime : showtimes) {
								// build links
								if (!showtime.equals("")) {
									showings.createShowing(showtime, cinemaToLink.getCapacity(), cinemaToLink.getId(), movieToLink.getId());
								}
							}
					}
					i++;
				}
			} else {
				// set session attribute w/ coming soon movieDTO list
				request.setAttribute("movies", movies.findAll());
				// set session attribute w/ all cinemas
				request.setAttribute("cinemas", cinemas.getAll());
			}
			forwardPage = "mapMtoC.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} else if (request.getParameter("action").equals("bookingDetails")) {

			int cinemaID = Integer.parseInt(request.getParameter("buyTicketsCinemaId"));
			int movieID = Integer.parseInt(request.getParameter("buyTicketsMovieId"));

			
			//Get movie and cinema title
			MovieDTO movie = movies.getMovieById(movieID);
			CinemaDTO cinema = cinemas.getCinemaByID(cinemaID);
			
			//Get session times given the above movie and cinema
			List<ShowingDTO> showTimes = showings.getShowingsFor(movieID, cinemaID);
			
			
			request.setAttribute("showTimes", showTimes);
			request.setAttribute("movie", movie);
			request.setAttribute("cinema", cinema);
			forwardPage = "checkout.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
			
			
		} else if (request.getParameter("action").equals("checkout")) {
			int numAdults = Integer.parseInt(request.getParameter("numAdults"));
			int numConcessions = Integer.parseInt(request.getParameter("numConcessions"));
			int numChildren = Integer.parseInt(request.getParameter("numChildren"));
			int cinemaID = Integer.parseInt(request.getParameter("cinemaID"));
			int movieID = Integer.parseInt(request.getParameter("movieID"));
			String creditcardNum = request.getParameter("creditcardNumber");
			String csv = request.getParameter("creditcardCSV");
			String cardName = request.getParameter("creditcardName");
			String showTime = request.getParameter("showTime");
			
			//Get movie and cinema title
			MovieDTO movie = movies.getMovieById(movieID);
			CinemaDTO cinema = cinemas.getCinemaByID(cinemaID);
			int showingID = bookings.findShowingID(movie, cinema, showTime);
			int totalSeatsRequired = numAdults + numChildren + numConcessions;
			
			//check for errors in form input
			boolean error = false;
			String errorMessage = "";
			if(showingID == -1){
				error = true;
				errorMessage = "An error has occured, please reutrn to the home page and try again";
			} else if (csv == "" || cardName == "" || creditcardNum == ""){
				error = true;
				errorMessage = "Your credit card details have been entered incorrectly";
			} else if (totalSeatsRequired < 1){
				error = true;
				errorMessage = "You need to select at least one ticket to make a booking";
			} else if (bookings.noSeatsAvailable(totalSeatsRequired, showingID)){
				error = true;
				errorMessage = "This session does not have enough seats to support your order";
			}
			
			
			if(error == false){
				//get user id
				int userID = sessionBean.getUser().getId();
				//Make the actual booking
				BookingDTO myBooking = bookings.makeBooking(movie, cinema, showTime, numAdults, numConcessions, numChildren, userID);
				
				request.setAttribute("booking", myBooking);
				forwardPage = "confirmBooking.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);
			} else {
				List<ShowingDTO> showTimes = showings.getShowingsFor(movieID, cinemaID);
				request.setAttribute("error", errorMessage);
				request.setAttribute("isError", 1);
				request.setAttribute("showTimes", showTimes);
				request.setAttribute("movie", movie);
				request.setAttribute("cinema", cinema);
				forwardPage = "checkout.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);

			}
			
			
		} else if(request.getParameter("action").equals("viewBookings")) {
				int userID = sessionBean.getUser().getId();
				List<BookingDTO> myBookings = bookings.getBookings(userID, cinemas, movies);
				
							
				request.setAttribute("bookings", myBookings);
				forwardPage = "viewBookings.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);	
		
		} else if(request.getParameter("action").equals("addReview")) {
				// get info
				UserDTO reviewer = sessionBean.getUser();
				int rating = Integer.parseInt(request.getParameter("rating"));
				String comment = request.getParameter("comment");
				int reviewedMovie = Integer.parseInt(request.getParameter("reviewedMovie"));
				
				// update review, table
				reviews.addReview(comment, rating, reviewer.getId(), reviewedMovie); 
				
				// update average rating + user ratings in movie
				movies.updateRating(rating, reviewedMovie);
				
				// redirect to thanks page
				forwardPage = "reviewThanks.jsp";
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
				dispatcher.forward(request, response);

		} else if (request.getParameter("action").equals("home")) {
			List<MovieDTO> nowShowing = movies.findNowShowing(3);
			request.setAttribute("nowShowing",  nowShowing);
			List<MovieDTO> comingSoon = movies.findComingSoon(3);
			request.setAttribute("comingSoon",  comingSoon);
			forwardPage = "index.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/"+forwardPage);
			dispatcher.forward(request, response);
		} 
	}
	
}
