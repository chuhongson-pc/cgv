package struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import struts.bean.Movie;
import struts.bean.Poster;

public class HomeForm extends ActionForm{
	ArrayList<Movie> topMovies = new ArrayList<Movie>();
	
	ArrayList<Poster> posterList = new ArrayList<Poster>();
	
	public ArrayList<Poster> getPosterList() {
		return posterList;
	}

	public void setPosterList(ArrayList<Poster> posterList) {
		this.posterList = posterList;
	}

	public ArrayList<Movie> getTopMovies() {
		return topMovies;
	}

	public void setTopMovies(ArrayList<Movie> topMovies) {
		this.topMovies = topMovies;
	}
	
	
	
}
