package struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import struts.bean.Movie;
import struts.bean.ScheduledDate;
import struts.bean.ShowTimesRow;
import struts.util.Utilities;

public class MovieInfoForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	Utilities util = new Utilities();
	
	private Movie movieCurrent = new Movie();
	
	private ArrayList<ShowTimesRow> showTimeRows = new ArrayList<ShowTimesRow>();
	private ArrayList<ScheduledDate> scheduledDates = new ArrayList<ScheduledDate>();

	private String currentDate = util.getCurrentDate();


	public Movie getMovieCurrent() {
		return movieCurrent;
	}

	public void setMovieCurrent(Movie movieCurrent) {
		this.movieCurrent = movieCurrent;
	}

	public ArrayList<ShowTimesRow> getShowTimeRows() {
		return showTimeRows;
	}

	public void setShowTimeRows(ArrayList<ShowTimesRow> showTimeRows) {
		this.showTimeRows = showTimeRows;
	}

	public ArrayList<ScheduledDate> getScheduledDates() {
		return scheduledDates;
	}

	public void setScheduledDates(ArrayList<ScheduledDate> scheduledDates) {
		this.scheduledDates = scheduledDates;
	}

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

}
