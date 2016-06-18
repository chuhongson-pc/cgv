package struts.admin.bo;

import java.util.ArrayList;

import struts.admin.bean.Movie;
import struts.admin.dao.MoviesAdminDAO;

public class MoviesAdminBO {
	MoviesAdminDAO moviesAdminDAO = new MoviesAdminDAO();

	public ArrayList<Movie> getMovieList(int type,  int pagesize, int pagenumber) {
		// TODO Auto-generated method stub
		return moviesAdminDAO.getMovieList(type, pagesize, pagenumber);
	}

	public Movie getMovieInfo(String movieId) {
		// TODO Auto-generated method stub
		return moviesAdminDAO.getMovieInfo(movieId);
	}

	public boolean updateMovie(Movie movie) {
		// TODO Auto-generated method stub
		return moviesAdminDAO.updateMovie(movie);
	}

	public ArrayList<Movie> searchMovie(String content) {
		// TODO Auto-generated method stub
		return moviesAdminDAO.searchMovie(content);
	}

	public int getNumberRowOfMovie() {
		// TODO Auto-generated method stub
		return moviesAdminDAO.getNumberRowOfMovie();
	}

	public String addMovie(Movie movie) {
		// TODO Auto-generated method stub
		return moviesAdminDAO.addMovie(movie);
	}
	
	public String getNewMovieId(){
		return moviesAdminDAO.getNewMovieId();
	}

	public boolean deleteMovie(String movieId) {
		// TODO Auto-generated method stub
		return moviesAdminDAO.deleteMovie(movieId);
	}
	
}
