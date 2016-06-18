package struts.admin.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import struts.dao.DAO;

public class CommonDAO extends DAO {

	public int checkLogin(String username, String password) {
		
		ResultSet resultSet = null;

		try {
			resultSet = statement.executeQuery("SELECT Role FROM  TaiKhoan WHERE Username='"+ username + "' and Password='" + password + "' and Role != '1' and TrangThai ='1' ");
			if (resultSet.next())
				return Integer.parseInt(resultSet.getString(1));
			else
				return 0;

		} catch (SQLException e) {
			e.printStackTrace();
			return 0;

		}
	}

	public String getPassword(String userName) {
		
		ResultSet resultSet = null;
		String password="";
		try {
			resultSet = statement.executeQuery("SELECT Password FROM TaiKhoan WHERE TrangThai = '1' and Role != '1' and UserName='" + userName + "' ");
			while (resultSet.next()) {
				password=resultSet.getString(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return password;
	}

	public boolean editMatKhau(String userName, String matKhauCu, String matKhauMoi) {
		
		String query_update = "UPDATE TaiKhoan SET Password = '" + matKhauMoi
				+ "' WHERE Username='" + userName + "' and Password='"+matKhauCu+"'";
		
		try {
			System.out.println("update");
			statement.executeUpdate(query_update);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}
	
}
