package struts.admin.bo;

import java.util.ArrayList;

import org.json.JSONObject;

import struts.admin.bean.FastFood;
import struts.admin.bean.TicketType;
import struts.admin.dao.TicketAdminDAO;

public class TicketAdminBO {
	TicketAdminDAO ticketAdminDAO = new TicketAdminDAO();

	public JSONObject getTicketList() {
		// TODO Auto-generated method stub
		return ticketAdminDAO.getTicketList();
	}

	public ArrayList<FastFood> getFFList() {
		// TODO Auto-generated method stub
		return ticketAdminDAO.getFFList();
	}

	public TicketType getTicketInfo(String ticketId) {
		// TODO Auto-generated method stub
		return ticketAdminDAO.getTicketInfo(ticketId);
	}

	public boolean updateTicket(TicketType ticket) {
		// TODO Auto-generated method stub
		return ticketAdminDAO.updateTicket(ticket);
	}

	public boolean addTicket(TicketType ticket) {
		// TODO Auto-generated method stub
		return ticketAdminDAO.addTicket(ticket);
	}

	public boolean deleteTicket(String ticketId) {
		// TODO Auto-generated method stub
		return ticketAdminDAO.deleteTicket(ticketId);
	}
}
