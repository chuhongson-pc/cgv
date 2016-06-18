package struts.admin.bo;

import java.util.ArrayList;

import struts.admin.bean.FastFood;
import struts.admin.bean.Movie;
import struts.admin.bean.Room;
import struts.admin.bean.Session;
import struts.admin.bean.TicketTypeSetting;
import struts.admin.dao.TicketSalesAdminDAO;

public class TicketSalesAdminBO {
	TicketSalesAdminDAO ticketSalesAdminDAO = new TicketSalesAdminDAO();

	public ArrayList<Movie> getNowShowingMovie() {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getNowShowingMovie();
	}

	public ArrayList<Session> getDatesOfMovie(String movieId) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getDatesOfMovie(movieId);
	}

	public ArrayList<Room> getRoomsOfMovie(String movieId, String current_date) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getRoomsOfMovie(movieId, current_date);
	}

	public ArrayList<Session> getSessionOfMovie(String movieId,
			String current_date, String roomId) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getSessionOfMovie(movieId, current_date, roomId);
	}

	public ArrayList<TicketTypeSetting> getTicketTypeOfSession(String sessionId) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getTicketTypeOfSession(sessionId);
	}

	public String getSeatsBooked(String sessionId) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getSeatsBooked(sessionId);
	}

	public String getSeatsMap(String sessionId) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getSeatsMap(sessionId);
	}

	public ArrayList<FastFood> getFastFoodList() {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.getFastFoodList();
	}

	public boolean checkVaildType(String sessionId, String ticketTypeSelected,
			String fastFoodTypeSelected, String seatsSelected) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.checkVaildType(sessionId, ticketTypeSelected, fastFoodTypeSelected, seatsSelected);
	}

	public String finishTransaction(String username, String sessionId,
			String ticketTypeSelected, String fastFoodTypeSelected,
			String seatsSelected) {
		// TODO Auto-generated method stub
		return ticketSalesAdminDAO.finishTransaction(username, sessionId, ticketTypeSelected, fastFoodTypeSelected, seatsSelected);
	}

	


}
