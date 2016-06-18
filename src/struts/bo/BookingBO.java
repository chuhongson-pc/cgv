package struts.bo;

import java.util.ArrayList;

import struts.bean.FastFood;
import struts.bean.Session;
import struts.dao.BookingDAO;

public class BookingBO {
	BookingDAO bookingDAO = new BookingDAO();

	public Session getSession(String sessionId) {
		// TODO Auto-generated method stub
		return bookingDAO.getSession(sessionId);
	}

	public ArrayList<FastFood> getFastFoodList() {
		// TODO Auto-generated method stub
		return bookingDAO.getFastFoodList();
	}

	public String getSeatsBooked(String sessionId) {
		// TODO Auto-generated method stub
		return bookingDAO.getSeatsBooked(sessionId);
	}

	public boolean checkVaildType(String sessionId, String ticketTypeSelected,
			String fastFoodTypeSelected, String seatsSelected) {
		// TODO Auto-generated method stub
		return bookingDAO.checkVaildType(sessionId, ticketTypeSelected, fastFoodTypeSelected, seatsSelected);
	}

	public String finishTransaction(String username, String sessionId,
			String ticketTypeSelected, String fastFoodTypeSelected,
			String seatsSelected) {
		// TODO Auto-generated method stub
		return bookingDAO.finishTransaction(username, sessionId, ticketTypeSelected, fastFoodTypeSelected, seatsSelected);
	}

	public int getSoDuTK(String username) {
		// TODO Auto-generated method stub
		return bookingDAO.getSoDuTK(username);
	}

}
