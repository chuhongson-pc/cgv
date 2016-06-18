package struts.admin.bo;

import org.json.JSONObject;

import struts.admin.bean.FastFood;
import struts.admin.dao.FastFoodAdminDAO;

public class FastFoodAdminBO {
	FastFoodAdminDAO fastFoodAdminDAO = new FastFoodAdminDAO();

	public JSONObject getFastFoodList() {
		// TODO Auto-generated method stub
		return fastFoodAdminDAO.getFastFoodList();
	}

	public boolean addFastFood(FastFood ff) {
		// TODO Auto-generated method stub
		return fastFoodAdminDAO.addFastFood(ff);
	}

	public boolean deleteFastFood(String ffId) {
		// TODO Auto-generated method stub
		return fastFoodAdminDAO.deleteFastFood(ffId);
	}

	public boolean updateFastFood(FastFood ff) {
		// TODO Auto-generated method stub
		return fastFoodAdminDAO.updateFastFood(ff);
	}

	public FastFood getFastFoodInfo(String ffId) {
		// TODO Auto-generated method stub
		return fastFoodAdminDAO.getFastFoodInfo(ffId);
	}
}
