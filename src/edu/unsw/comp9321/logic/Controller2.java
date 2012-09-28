package edu.unsw.comp9321.logic;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import javax.servlet.http.*;
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

import org.apache.tomcat.util.http.fileupload.DefaultFileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileItem;

import edu.unsw.comp9321.beans.SessionBean;
import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.exception.EmptyResultException;
import edu.unsw.comp9321.jdbc.ActorDAO;
import edu.unsw.comp9321.jdbc.ActorDTO;
import edu.unsw.comp9321.jdbc.CastDAO;
import edu.unsw.comp9321.jdbc.CinemaDAO;
import edu.unsw.comp9321.jdbc.CommentDTO;
import edu.unsw.comp9321.jdbc.DerbyDAOImpl;
import edu.unsw.comp9321.jdbc.MovieDAO;
import edu.unsw.comp9321.jdbc.MovieDTO;
import edu.unsw.comp9321.jdbc.MySQLDAOImpl;
import edu.unsw.comp9321.jdbc.CharacterDTO;
import edu.unsw.comp9321.jdbc.UserDAO;
import edu.unsw.comp9321.jdbc.UserDTO;
import edu.unsw.comp9321.mail.MailSender;
import edu.unsw.comp9321.mail.MailSenderTest;

/**
 * Servlet implementation class Controller
 */
public class Controller2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Logger logger = Logger.getLogger(Controller2.class.getName());
	private CastDAO cast;
	private MovieDAO movies;
	private ActorDAO actors;
	private UserDAO users;
	private UserDTO currentUser;
	private CinemaDAO cinemas;
	private SessionBean sessionBean;
	
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
    public Controller2() throws ServletException {
    	// TODO Auto-generated constructor stub
        super();
        try {
        	
        	movies = new MovieDAO();
        	users = new UserDAO();
        	actors = new ActorDAO();
			cast = new DerbyDAOImpl();
			cinemas = new CinemaDAO();
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
		System.out.println("now hwew");
		
		
		
	}
	
	private String handlePostcomment(HttpServletRequest request, HttpServletResponse response){
		String forwardPage = "";
		String character = (String) request.getParameter("character");
		logger.info("Comment on character: "+character);
		try{
			CharacterDTO mchar = cast.findChar(character);
			String commentString = request.getParameter("comments");
			CommentDTO comment = new CommentDTO(mchar.getId(), mchar.getName(), "SKV", new Date(), commentString);
			cast.storeComment(comment);
			request.setAttribute("comments", cast.getComments(character));
			forwardPage = "success.jsp";
		}catch(Exception e){
			e.printStackTrace();
			forwardPage = "error.jsp";
		}
		return forwardPage;
	}

}
