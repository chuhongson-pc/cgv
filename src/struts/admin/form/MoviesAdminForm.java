package struts.admin.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import struts.admin.bean.Movie;

public class MoviesAdminForm extends ActionForm {

	private static final long serialVersionUID = 1L;

	private ArrayList<Movie> movieList = new ArrayList<Movie>();

	private int currentPage;
	private int totalPage;

	private Movie currentMovie = new Movie();

	private String searchContent;

	private int showType;

	private FormFile thumbnailFile;
	private FormFile bannerFile;
	
	

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public FormFile getThumbnailFile() {
		return thumbnailFile;
	}

	public void setThumbnailFile(FormFile thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}

	public FormFile getBannerFile() {
		return bannerFile;
	}

	public void setBannerFile(FormFile bannerFile) {
		this.bannerFile = bannerFile;
	}

	public int getShowType() {
		return showType;
	}

	public void setShowType(int showType) {
		this.showType = showType;
	}

	public String getSearchContent() {
		return searchContent;
	}

	public void setSearchContent(String searchContent) {
		this.searchContent = searchContent;
	}

	public ArrayList<Movie> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<Movie> movieList) {
		this.movieList = movieList;
	}

	public Movie getCurrentMovie() {
		return currentMovie;
	}

	public void setCurrentMovie(Movie currentMovie) {
		this.currentMovie = currentMovie;
	}


	public void reset() {
		System.out.println("Reset moviesAdminForm");
		movieList = new ArrayList<Movie>();
		currentPage=0;
		totalPage=0;
		currentMovie = new Movie();
		searchContent="";
		showType=0;
		
	}
	
}
