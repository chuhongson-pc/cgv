package struts.admin.bo;

import java.util.ArrayList;

import org.json.JSONObject;

import struts.admin.dao.StatisticAdminDAO;

public class StatisticAdminBO {

	StatisticAdminDAO statisticAdminDAO = new StatisticAdminDAO();

	public ArrayList<String> getAllYear() {
		// TODO Auto-generated method stub
		return statisticAdminDAO.getAllYear();
	}

	public ArrayList<String> getAllMonth(String year) {
		// TODO Auto-generated method stub
		return statisticAdminDAO.getAllMonth(year);
	}

	public JSONObject getStatistic(String type, String time) {
		// TODO Auto-generated method stub
		return statisticAdminDAO.getStatistic(type, time);
	}

	public JSONObject getStatistic1(String type, String year) {
		// TODO Auto-generated method stub
		return statisticAdminDAO.getStatistic1(type, year);
	}


	
}
