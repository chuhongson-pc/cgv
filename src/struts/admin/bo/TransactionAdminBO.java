package struts.admin.bo;

import java.util.HashMap;

import org.json.JSONObject;
import org.json.JSONTokener;

import struts.admin.dao.TransactionAdminDAO;

public class TransactionAdminBO {
	TransactionAdminDAO transactionAdminDAO = new TransactionAdminDAO();

	public JSONObject getTransactionList(HashMap<String, String> info, String role) {
		// TODO Auto-generated method stub
		return transactionAdminDAO.getTransactionList(info, role);
	}

	public JSONObject getTicketInfo(String transactionId) {
		// TODO Auto-generated method stub
		return transactionAdminDAO.getTicketInfo(transactionId);
	}

	public boolean deleteTransaction(String transactionId) {
		// TODO Auto-generated method stub
		return transactionAdminDAO.deleteTransaction(transactionId);
	}


}
