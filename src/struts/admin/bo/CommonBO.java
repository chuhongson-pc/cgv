package struts.admin.bo;

import struts.admin.dao.CommonDAO;

public class CommonBO {
	
	CommonDAO commonDAO = new CommonDAO();

	public int checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		return commonDAO.checkLogin(username, password);
	}

	public String getPassword(String userName) {
		// TODO Auto-generated method stub
		return commonDAO.getPassword(userName);
	}

	public boolean editMatKhau(String userName, String matKhauCu,
			String matKhauMoi) {
		// TODO Auto-generated method stub
		return commonDAO.editMatKhau(userName, matKhauCu, matKhauMoi);
	}

}
