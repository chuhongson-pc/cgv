package struts.form;

import java.util.ArrayList;

import org.apache.struts.action.ActionForm;

import struts.bean.ScheduledDate;
import struts.bean.ShowTimesRow;
import struts.util.Utilities;

public class ShowTimesForm extends ActionForm {

	Utilities util = new Utilities();
	
	private String currentDate = util.getCurrentDate();
	private ArrayList<ScheduledDate> scheduledDates = new ArrayList<ScheduledDate>();
	private ArrayList<ShowTimesRow> showTimes = new ArrayList<ShowTimesRow>();

	public String getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(String currentDate) {
		this.currentDate = currentDate;
	}

	public ArrayList<ScheduledDate> getScheduledDates() {
		return scheduledDates;
	}

	public void setScheduledDates(ArrayList<ScheduledDate> scheduledDates) {
		this.scheduledDates = scheduledDates;
	}

	public ArrayList<ShowTimesRow> getShowTimes() {
		return showTimes;
	}

	public void setShowtimes(ArrayList<ShowTimesRow> showTimes) {
		this.showTimes = showTimes;
	}

}
