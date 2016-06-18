package struts.admin.bo;

import java.util.ArrayList;

import org.json.JSONObject;

import struts.admin.bean.AvailableSessionByRoom;
import struts.admin.bean.Movie;
import struts.admin.bean.Session;
import struts.admin.bean.TicketTypeSetting;
import struts.admin.dao.SessionsAdminDAO;

public class SessionsAdminBO {
	SessionsAdminDAO sessionsAdminDAO = new SessionsAdminDAO();

	public ArrayList<Movie> getMovieList(String current_date) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.getMovieList(current_date);
	}

	public ArrayList<AvailableSessionByRoom> getSessionTimeAvailable(String current_date, String movieId) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.getSessionTimeAvailable(current_date, movieId);
	}

	public ArrayList<TicketTypeSetting> getTicketType(int type) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.getTicketType(type);
	}

	public boolean addSession(String session_str, String ticket_str) {
		return sessionsAdminDAO.addSession(session_str, ticket_str);
	}

	public JSONObject getDateSetting() {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.getDateSetting();
	}

	public ArrayList<AvailableSessionByRoom> getSessionList(String current_date) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.getSessionList(current_date);
	}

	public Session getSessionInfo(String sessionId) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.getSessionInfo(sessionId);
	}

	public boolean confirmSession(String sessionId) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.confirmSession(sessionId);
	}

	public boolean deleteSession(String sessionId) {
		// TODO Auto-generated method stub
		return sessionsAdminDAO.deleteSession(sessionId);
	}
	
	
	
}
