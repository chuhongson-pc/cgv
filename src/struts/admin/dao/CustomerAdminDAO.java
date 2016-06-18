package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import org.json.*;
import struts.admin.bean.Customer;
import struts.dao.DAO;
import struts.util.Utilities;

public class CustomerAdminDAO extends DAO {

	Utilities util = new Utilities();

	public ArrayList<Customer> getCustomerList() {

		ArrayList<Customer> list = new ArrayList<Customer>();

		String query = "select * from TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username where TaiKhoan.TrangThai != '0' and TaiKhoan.Role = '1'";

		try {
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				Customer c = new Customer();
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
				c.setSoDuTaiKhoan(resultSet.getInt(14));
				c.setDiemTichLuy(resultSet.getInt(15));

				list.add(c);
			}
			;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return list;

	}

	public int getTotalRecordCountOfCustomer() {

		String query = "select count(Username) from TaiKhoan where TrangThai = '1' and Role = '1'";

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

	public JSONObject getCustomerDetails(HashMap<String, String> info) {
		
		System.out.println("getCustomerDetails");

		int totalRecords = getTotalRecordCountOfCustomer();
		
		System.out.println("getTotalRecordCountOfCustomer="+totalRecords);
		
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

		String sql = "select * from TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username where TaiKhoan.TrangThai != '0' and TaiKhoan.Role = '1' ";

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
		
		sql = "SELECT * FROM( SELECT  TaiKhoan.*, TTKhachHang.SoDuTK, TTKhachHang.DiemTichLuy,  ROW_NUMBER() OVER("+"order by TaiKhoan." + columnName + " " + direction+") RN FROM TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username WHERE "+searchSQL+" TrangThai != '0' and Role = '1' ) A "
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
				
				
				
				String button = "<a href=\"../customer.html?action=info&user="+rs.getString(1)+"\" class=\"button-info\">"
						+ "<button type=\"button\" class=\"btn btn-primary\">Chi tiết</button></a>"
								+"<a href=\"../customer.html?action=delete&user="+rs.getString(1)+"\" class=\"button-delete\">"
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

			String query = "select COUNT(*) as count from TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username where "+searchSQL+" TaiKhoan.TrangThai != '0' and TaiKhoan.Role = '1' ";
			
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
	
	public boolean addCustomer(Customer currentCustomer) {
		
		if(addCustomerToTaiKhoanTable(currentCustomer)){
			
			if(addCustomerToTTKhachHangTable(currentCustomer)){
				return true;
			} else {
				//rollback
				rollBackAddCustomer(currentCustomer);
				return false;
			}
		}
		else {
			//rollback
			rollBackAddCustomer(currentCustomer);
			return false;
		}
	}
	
	private void rollBackAddCustomer(Customer currentCustomer){
		
		System.out.println("rollBackAddCustomer");
		
		try {
			
			Statement rollback_statemnet = connection.createStatement();
			
			rollback_statemnet.addBatch("delete from TTKhachHang where Username = '"+currentCustomer.getUsername()+"'");
			rollback_statemnet.addBatch("delete from TaiKhoan where Username = '"+currentCustomer.getUsername()+"'");
			
			rollback_statemnet.executeBatch();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	

	private boolean addCustomerToTTKhachHangTable(Customer currentCustomer) {
		
		System.out.println("addCustomerToTTKhachHangTable");
		
		String query = "INSERT INTO TTKhachHang VALUES(?,?,?)";
		
		try {
			pstatement = connection.prepareStatement(query);
			pstatement.setString(1, currentCustomer.getUsername());			
			pstatement.setInt(2, currentCustomer.getSoDuTaiKhoan());			
			pstatement.setInt(3, 0);
			
			
			pstatement.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	public boolean addCustomerToTaiKhoanTable(Customer currentCustomer) {
		
		System.out.println("addCustomerToTaiKhoanTable");
		
		String query_insert = "INSERT INTO TaiKhoan VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";

		try {
			pstatement = connection.prepareStatement(query_insert);
			pstatement.setString(1, currentCustomer.getUsername());			
			pstatement.setString(2, currentCustomer.getFullname());			
			pstatement.setString(3, util.convertDate(currentCustomer.getDob()));
			pstatement.setString(4, currentCustomer.getGender());
			pstatement.setString(5, currentCustomer.getAddress());
			pstatement.setString(6, currentCustomer.getPhonenumber());
			pstatement.setString(7, currentCustomer.getEmail());
			pstatement.setString(8, currentCustomer.getCmnd());
			pstatement.setString(9, currentCustomer.getPassword());
			pstatement.setString(10, util.getTimeNow());
			pstatement.setString(11, "1");
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

	public boolean deleteCustomer(String username) {
		
		System.out.println("delete customer");
		
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

	public Customer getCustomerInfo(String username) {
		
		String query = "select TaiKhoan.*, TTKhachHang.SoDuTK, TTKhachHang.DiemTichLuy from "
				+ "TaiKhoan join TTKhachHang on TTKhachHang.Username = TaiKhoan.Username where TaiKhoan.Username = '"+username+"'";
		
		Customer current_customer = new Customer();
		
		try {
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
				
				current_customer.setUsername(resultSet.getString(1));
				current_customer.setFullname(resultSet.getString(2));
				current_customer.setDob(util.convertDate(resultSet.getString(3)));
				current_customer.setGender(resultSet.getString(4));
				current_customer.setAddress(resultSet.getString(5));
				current_customer.setPhonenumber(resultSet.getString(6));
				current_customer.setEmail(resultSet.getString(7));
				current_customer.setCmnd(resultSet.getString(8));
				current_customer.setPassword(resultSet.getString(9));
				String date_create = resultSet.getString(10);
				date_create = date_create.substring(0, 10);
				current_customer.setNgayKhoiTao(util.convertDate(date_create));
				current_customer.setTrangThai(resultSet.getInt(12) + "");
				current_customer.setSoDuTaiKhoan(resultSet.getInt(13));
				current_customer.setDiemTichLuy(resultSet.getInt(14));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return current_customer;
	}

	public boolean updateCustomer(Customer currentCustomer) {
		
		String query = "update TaiKhoan SET  HoTen = ?, NgaySinh = ?, GioiTinh = ?, DiaChi =?, "
				+ "SoDienThoai = ?, Email = ?, CMND = ?, Password = ?, TrangThai = ? where Username = ?";
		
		String query1 = "update TTKhachHang SET SoDuTK = ?, DiemTichLuy = ? where Username = ?";
		
		try {
			PreparedStatement pr = connection.prepareStatement(query);
			
			pr.setString(1, currentCustomer.getFullname());
			pr.setString(2, util.convertDate(currentCustomer.getDob()));
			pr.setString(3, currentCustomer.getGender());
			pr.setString(4, currentCustomer.getAddress());
			pr.setString(5, currentCustomer.getPhonenumber());
			pr.setString(6, currentCustomer.getEmail());
			pr.setString(7, currentCustomer.getCmnd());
			pr.setString(8, currentCustomer.getPassword());
			pr.setString(9, currentCustomer.getTrangThai());
			
			pr.setString(10, currentCustomer.getUsername());
			
			pr.executeUpdate();
			
			//=========
			pr = connection.prepareStatement(query1);
			pr.setInt(1, currentCustomer.getSoDuTaiKhoan());
			pr.setInt(2, currentCustomer.getDiemTichLuy());
			pr.setString(3, currentCustomer.getUsername());
			
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
