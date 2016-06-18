package struts.admin.bo;

import java.util.ArrayList;

import struts.admin.dao.DateDAO;

public class DateBO {
	DateDAO dateDAO = new DateDAO();

	public boolean saveSetting(int suatChieuSom, int suatCuoiTuan,
			int thu4VuiVe, String ngayLe, int giaNgayLe) {
		// TODO Auto-generated method stub
		return dateDAO.saveSetting(suatChieuSom, suatCuoiTuan, thu4VuiVe, ngayLe, giaNgayLe);
	}

	public ArrayList<String> getSetting() {
		// TODO Auto-generated method stub
		return dateDAO.getSetting();
	}
}
