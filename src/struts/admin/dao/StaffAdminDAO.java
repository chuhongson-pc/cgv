package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import struts.admin.bean.Staff;
import struts.dao.DAO;
import struts.util.Utilities;

public class StaffAdminDAO extends DAO {

	Utilities util = new Utilities();

	public ArrayList<Staff> getStaffList() {

		ArrayList<Staff> list = new ArrayList<Staff>();

		String query = "select * from TaiKhoan where TaiKhoan.TrangThai != '0' and TaiKhoan.Role = '2'";

		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				Staff c = new Staff();
				c.setUsername(resultSet.getString(1));
				c.setFullname(resultSet.getString(2));
				c.setDob(util.convertDate(resultSet.getString(3)));
				c.setGender(resultSet.getString(4));
				c.setAddress(resultSet.getString(5));
				c.setPhonenumber(resultSet.getString(6));
				c.setEmail(resultSet.getString(7));
				c.setCmnd(resultSet.getString(8));
				c.setPassword(resultSet.getString(9));
				String date_create = resultSet.getString(10);
				date_create = date_create.substring(0, 10);
				c.setNgayKhoiTao(util.convertDate(date_create));
				c.setTrangThai(resultSet.getInt(11) + "");
				
				list.add(c);
			}
			;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int getTotalRecordCountOfStaff() {

		String query = "select count(Username) from TaiKhoan where TrangThai = '1' and Role = '2'";

		int count = -1;

		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				count = resultSet.getInt(1);
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public JSONObject getStaffDetails(HashMap<String, String> info) {
		
		System.out.println("getStaffDetails");

		int totalRecords = getTotalRecordCountOfStaff();
		
		System.out.println("getTotalRecordCountOfStaff="+totalRecords);
		
		int totalAfterSearch = totalRecords;

		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String searchSQL = "";

		String global_S = info.get("GLOBAL_SEARCH");
		String username_S = info.get("USERNAME_SEARCH");
		//String dob_S = info.get("DOB_SEARCH");
		//String gender_S = info.get("GENDER_SEARCH");
		//String address_S = info.get("ADDRESS_SEARCH");
		String phone_S = info.get("PHONE_SEARCH");
		String name_S = info.get("FULLNAME_SEARCH");
		String email_S = info.get("EMAIL_SEARCH");
		String cmnd_S = info.get("CMND_SEARCH");
		

		String columnName = info.get("COLUMN_NAME");
		String direction = info.get("DIRECTION");
		String initial = info.get("INITIAL");
		String recordsize = info.get("RECORD_SIZE");

		String sql = "";

		String globeSearch = " TaiKhoan.Username like '%" + global_S + "%'"
				+ " or TaiKhoan.HoTen like '%" + global_S + "%'" + " or TaiKhoan.Email like '%"
				+ global_S + "%'" + " or TaiKhoan.CMND like '%" + global_S + "%'"
				+ " or TaiKhoan.SoDienThoai like  '%" + global_S + "%'";
		

		String usernameSearch = " TaiKhoan.Username like '%" + username_S + "%'";
		String fullnameSearch = " TaiKhoan.HoTen like '%" + name_S + "%'";
		String cmndSearch = " TaiKhoan.CMND like '%" + cmnd_S + "%'";
		String emailSearch = " TaiKhoan.Email like '%" + phone_S + "%'";
		String phoneSearch = " TaiKhoan.SoDienThoai like '%" + phone_S + "%'";
		

		if (!global_S.equals("")) {

			searchSQL = globeSearch;

		} else if (!username_S.equals("")) {
			searchSQL = usernameSearch;
		} else if (!name_S.equals("")) {
			searchSQL = fullnameSearch;
		} else if (!cmnd_S.equals("")) {
			searchSQL = cmndSearch;
		} else if (!phone_S.equals("")) {
			searchSQL = phoneSearch;
		} else if (!email_S.equals("")) {
			searchSQL = emailSearch;
		}
		//
		
		if(!searchSQL.equals("")) searchSQL += " and ";

//		SELECT * FROM( 
//				SELECT  TaiKhoan.*, TTKhachHang.SoDuTK, TTKhachHang.DiemTichLuy, TTKhachHang.LoaiTV ,  ROW_NUMBER() OVER(order by TaiKhoan.Username asc) RN 
//				FROM TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username 
//				WHERE  TrangThai = '1' and Role = '1' ) A WHERE RN BETWEEN 0 AND 10 order by Username asc
		
		sql = "SELECT * FROM( SELECT  TaiKhoan.*,  ROW_NUMBER() OVER("+"order by TaiKhoan." + columnName + " " + direction+") RN FROM TaiKhoan WHERE "+searchSQL+" TrangThai != '0' and Role = '2' ) A "
				+"WHERE RN BETWEEN "+(initial)+" AND "+(Integer.parseInt(initial)+Integer.parseInt(recordsize))+" "
				+"order by " + columnName + " " + direction;
		
		// for searching
		System.out.println("query for search:"+sql);
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JSONArray ja = new JSONArray();

				ja.put(rs.getString(1)); //username
				ja.put(rs.getString(2)); //fullname
				//ja.put(util.convertDate(rs.getString(3))); //dob
				//ja.put(rs.getString(4)); //gender
				//ja.put(rs.getString(5)); //address
				ja.put(rs.getString(6)); //phone
				
				ja.put(rs.getString(7)); //email
				ja.put(rs.getString(8)); //cmnd
				//ja.put(rs.getString(9)); //sodu
//				String date_create = rs.getString(10);
//				date_create = date_create.substring(0, 10);
//				ja.put(util.convertDate(date_create));
				
				//ja.put(rs.getInt(13));//sdTK
				//ja.put(rs.getInt(14));//diemTichLuy
				
				//ja.put(rs.getString(9)); //pass
				
				
				String button_status = "<button type=\"button\" class=\"btn btn-success btn-xs\">Activated</button>";
				if(rs.getString(12).equals("2"))
					button_status = "<button type=\"button\" class=\"btn btn-danger btn-xs\">Blocked</button>";
				
				
				ja.put(button_status);
				
				
				
				String button = "<a href=\"../staff.html?action=info&user="+rs.getString(1)+"\" >"
						+ "<button type=\"button\" class=\"btn btn-primary\">Chi tiết</button></a>"
								+"<a href=\"../staff.html?action=delete&user="+rs.getString(1)+"\" >"
								+ "<button type=\"button\" class=\"btn btn-danger\">Xóa</button></a>";
				
				ja.put(button);
				
				

				array.put(ja);
			}

			stmt.close();
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

		

		
		
		if (global_S != "" || username_S != "" || name_S != "" || email_S != "" || phone_S != "" || cmnd_S != "") {

			// for pagination

			String query = "select COUNT(*) as count from TaiKhoan where "+searchSQL+" TaiKhoan.TrangThai != '0' and TaiKhoan.Role = '2' ";
			
			System.out.println("query for pagination:"+query);
			
			try {
				PreparedStatement st = connection.prepareStatement(query);
				ResultSet results = st.executeQuery();

				if (results.next()) {
					totalAfterSearch = results.getInt("count");
				}
				
				st.close();
				results.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		
		try 
		{
			result.put("iTotalRecords", totalRecords);
			result.put("iTotalDisplayRecords", totalAfterSearch);
			result.put("aaData", array);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		System.out.println("result="+result);
		
		return result;

	}
	
	public boolean addStaff(Staff currentStaff) {
		
		System.out.println("addStaffToTaiKhoanTable");
		
		String query_insert = "INSERT INTO TaiKhoan VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			pstatement = connection.prepareStatement(query_insert);
			pstatement.setString(1, currentStaff.getUsername());			
			pstatement.setString(2, currentStaff.getFullname());			
			pstatement.setString(3, util.convertDate(currentStaff.getDob()));
			pstatement.setString(4, currentStaff.getGender());
			pstatement.setString(5, currentStaff.getAddress());
			pstatement.setString(6, currentStaff.getPhonenumber());
			pstatement.setString(7, currentStaff.getEmail());
			pstatement.setString(8, currentStaff.getCmnd());
			pstatement.setString(9, currentStaff.getPassword());
			pstatement.setString(10, util.getTimeNow());
			pstatement.setString(11, "2");
			pstatement.setString(12, "1");
			
			pstatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	


	public boolean checkExistUser(String username) {
			ResultSet resultSet = null;
			try {
				resultSet = statement.executeQuery("SELECT Username FROM TaiKhoan WHERE Username='"+ username + "'");

				if (resultSet.next()) { // neu co

					resultSet.close();

					return true;
				} else {
					resultSet.close();
					return false;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
	}

	public boolean deleteStaff(String username) {
		
		System.out.println("delete staff");
		
		try {
			
			String query ="update TaiKhoan SET TrangThai = '0' where Username = '"+username+"'";
			
			statement.execute(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Staff getStaffInfo(String username) {
		
		String query = "select TaiKhoan.* from "
				+ "TaiKhoan where TaiKhoan.Username = '"+username+"'";
		
		Staff current_staff = new Staff();
		
		try {
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				
				current_staff.setUsername(resultSet.getString(1));
				current_staff.setFullname(resultSet.getString(2));
				current_staff.setDob(util.convertDate(resultSet.getString(3)));
				current_staff.setGender(resultSet.getString(4));
				current_staff.setAddress(resultSet.getString(5));
				current_staff.setPhonenumber(resultSet.getString(6));
				current_staff.setEmail(resultSet.getString(7));
				current_staff.setCmnd(resultSet.getString(8));
				current_staff.setPassword(resultSet.getString(9));
				String date_create = resultSet.getString(10);
				date_create = date_create.substring(0, 10);
				current_staff.setNgayKhoiTao(util.convertDate(date_create));
				current_staff.setTrangThai(resultSet.getInt(12) + "");
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current_staff;
	}

	public boolean updateStaff(Staff currentStaff) {
		
		String query = "update TaiKhoan SET  HoTen = ?, NgaySinh = ?, GioiTinh = ?, DiaChi =?, "
				+ "SoDienThoai = ?, Email = ?, CMND = ?, Password = ?, TrangThai = ? where Username = ?";
		
		
		
		try {
			PreparedStatement pr = connection.prepareStatement(query);
			
			pr.setString(1, currentStaff.getFullname());
			pr.setString(2, util.convertDate(currentStaff.getDob()));
			pr.setString(3, currentStaff.getGender());
			pr.setString(4, currentStaff.getAddress());
			pr.setString(5, currentStaff.getPhonenumber());
			pr.setString(6, currentStaff.getEmail());
			pr.setString(7, currentStaff.getCmnd());
			pr.setString(8, currentStaff.getPassword());
			pr.setString(9, currentStaff.getTrangThai());
			
			pr.setString(10, currentStaff.getUsername());
			
			pr.executeUpdate();
			

			
			pr.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
}
