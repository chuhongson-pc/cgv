package struts.admin.bo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import struts.admin.bean.Customer;
import struts.admin.dao.CustomerAdminDAO;

public class CustomerAdminBO {
	
	CustomerAdminDAO customerAdminDAO = new CustomerAdminDAO();

	public ArrayList<Customer> getCustomerList() {
		// TODO Auto-generated method stub
		return customerAdminDAO.getCustomerList();
	}

	public int getTotalRecordCountOfCustomer() {
		// TODO Auto-generated method stub
		return customerAdminDAO.getTotalRecordCountOfCustomer();
	}

	public JSONObject getCustomerDetails(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		return customerAdminDAO.getCustomerDetails(info);
	}

	public boolean addCustomer(Customer currentCustomer) {
		// TODO Auto-generated method stub
		return customerAdminDAO.addCustomer(currentCustomer);
	}

	public boolean checkExistUser(String username) {
		// TODO Auto-generated method stub
		return customerAdminDAO.checkExistUser(username);
	}

	public boolean deleteCustomer(String username) {
		// TODO Auto-generated method stub
		return customerAdminDAO.deleteCustomer(username);
	}

	public Customer getCustomerInfo(String username) {
		// TODO Auto-generated method stub
		return customerAdminDAO.getCustomerInfo(username);
	}

	public boolean updateCustomer(Customer currentCustomer) {
		// TODO Auto-generated method stub
		return customerAdminDAO.updateCustomer(currentCustomer);
	}
	
	

}
