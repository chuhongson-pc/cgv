package struts.bo;

import java.util.ArrayList;

import struts.bean.ShowTimesRow;
import struts.dao.ShowTimesDAO;

public class ShowTimesBO {
	ShowTimesDAO showTimesDAO = new ShowTimesDAO();
	
	public ArrayList<String> getAllScheduledDates() {
		// TODO Auto-generated method stub
		return showTimesDAO.getAllScheduledDates();
	}

	public ArrayList<ShowTimesRow> getShowTimes(String current_date) {
		// TODO Auto-generated method stub
		return showTimesDAO.getShowTimes(current_date);
	}

}
