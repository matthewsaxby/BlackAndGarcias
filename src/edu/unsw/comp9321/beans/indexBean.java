package edu.unsw.comp9321.beans;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.unsw.comp9321.common.ServiceLocatorException;
import edu.unsw.comp9321.jdbc.MovieDAO;
import edu.unsw.comp9321.jdbc.MovieDTO;

public class indexBean {
	
	private List<MovieDTO> nowShowing;
	private List<MovieDTO> comingSoon;
	
	public indexBean() {
		MovieDAO movies;
		try {
			movies = new MovieDAO();
			nowShowing  = movies.findComingSoon(2);
			comingSoon = movies.findNowShowing(2);
		} catch (ServiceLocatorException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}


	public List<MovieDTO> getNowShowing() {
		return nowShowing;
	}


	public void setNowShowing(List<MovieDTO> nowShowing) {
		this.nowShowing = nowShowing;
	}


	public List<MovieDTO> getComingSoon() {
		return comingSoon;
	}


	public void setComingSoon(List<MovieDTO> comingSoon) {
		this.comingSoon = comingSoon;
	}
	
	

}
