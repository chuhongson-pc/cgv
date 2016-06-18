package struts.admin.bo;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONObject;

import struts.admin.bean.Staff;
import struts.admin.dao.StaffAdminDAO;

public class StaffAdminBO {
	
	StaffAdminDAO staffAdminDAO = new StaffAdminDAO();

	public ArrayList<Staff> getStaffList() {
		// TODO Auto-generated method stub
		return staffAdminDAO.getStaffList();
	}

	public int getTotalRecordCountOfStaff() {
		// TODO Auto-generated method stub
		return staffAdminDAO.getTotalRecordCountOfStaff();
	}

	public JSONObject getStaffDetails(HashMap<String, String> info) {
		// TODO Auto-generated method stub
		return staffAdminDAO.getStaffDetails(info);
	}

	public boolean addStaff(Staff currentStaff) {
		// TODO Auto-generated method stub
		return staffAdminDAO.addStaff(currentStaff);
	}

	public boolean checkExistUser(String username) {
		// TODO Auto-generated method stub
		return staffAdminDAO.checkExistUser(username);
	}

	public boolean deleteStaff(String username) {
		// TODO Auto-generated method stub
		return staffAdminDAO.deleteStaff(username);
	}

	public Staff getStaffInfo(String username) {
		// TODO Auto-generated method stub
		return staffAdminDAO.getStaffInfo(username);
	}

	public boolean updateStaff(Staff currentStaff) {
		// TODO Auto-generated method stub
		return staffAdminDAO.updateStaff(currentStaff);
	}
	
	

}
