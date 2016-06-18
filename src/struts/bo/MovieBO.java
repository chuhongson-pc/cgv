package struts.bo;

import java.util.ArrayList;

import struts.bean.Movie;
import struts.bean.ShowTimesRow;
import struts.dao.MovieDAO;

public class MovieBO {
	static MovieDAO movieDAO = new MovieDAO();
	public Movie getMovieInfo(String idMovie) {
		// TODO Auto-generated method stub
		return movieDAO.getMovieInfo(idMovie);
	}
	
	public ArrayList<String> getScheduledDatesByMovie(String idMovie) {
		// TODO Auto-generated method stub
		return movieDAO.getScheduledDatesByMovie(idMovie);
	}
	
	public ArrayList<ShowTimesRow> getShowtimes(String idMovie,
			String date_current) {
		// TODO Auto-generated method stub
		return movieDAO.getShowtimes(idMovie,date_current);
	}

}
