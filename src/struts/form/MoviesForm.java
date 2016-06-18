package struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import struts.bean.Movie;

public class MoviesForm extends ActionForm {
	private ArrayList<Movie> movies = new ArrayList<Movie>();

	private String listType;

	public String getListType() {
		return listType;
	}

	public void setListType(String listType) {
		this.listType = listType;
	}

	public ArrayList<Movie> getMovies() {
		return movies;
	}

	public void setMovies(ArrayList<Movie> movies) {
		this.movies = movies;
	}

}
