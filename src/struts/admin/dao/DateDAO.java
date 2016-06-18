package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import struts.dao.DAO;

public class DateDAO extends DAO {

	public boolean saveSetting(int suatChieuSom, int suatCuoiTuan,
			int thu4VuiVe, String ngayLe, int giaNgayLe) {
		
		
		String query = "update CaiDatNgay SET SuatChieuSom = ?, SuatCuoiTuan = ?, Thu4VuiVe = ?, NgayLe = ?, GiaNgayLe = ?";
		
		try {
			PreparedStatement pre = connection.prepareStatement(query);
			
			pre.setInt(1, suatChieuSom);
			pre.setInt(2, suatCuoiTuan);
			pre.setInt(3, thu4VuiVe);
			pre.setString(4,ngayLe);
			pre.setInt(5, giaNgayLe);
			
			pre.executeUpdate();
			
			pre.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
			return true;
	}

	public ArrayList<String> getSetting() {
		ArrayList<String> settings = new ArrayList<String>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery("select * from CaiDatNgay");
			
			while(rs.next()){
				settings.add(rs.getInt(1) +"");
				settings.add(rs.getInt(2) +"");
				settings.add(rs.getInt(3) +"");
				settings.add(rs.getString(4));
				settings.add(rs.getInt(5)+"");
			}
			
			
			rs.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return settings;
		
	}

}
