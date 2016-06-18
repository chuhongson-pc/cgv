package struts.admin.dao;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import struts.admin.bean.FastFood;
import struts.dao.DAO;

public class FastFoodAdminDAO extends DAO {

	public JSONObject getFastFoodList() {
		
		
		JSONArray array = new JSONArray();
		JSONObject result = new JSONObject();
		
		String query = "select * from FastFood where TrangThai != '0'";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()){
				
				JSONArray ja = new JSONArray();
				
				String ff_name = "<div class=\"ticket-name\">"+resultSet.getString(2)+"</div>"
                                      +"<div class=\"description-ticket\">"+resultSet.getString(4)+"</div>";
				ja.put(ff_name);
				
				String loai = "Đồ ăn";
				String type_ff = resultSet.getString(3);
				if(type_ff.equals("drink")){
					loai = "Đồ uống";
				}
				else if(type_ff.equals("combo")){
					loai = "Combo";
				}
				
				ja.put(loai);
				
				
				ja.put(util.insertComma(resultSet.getInt(5)));
				
				String button_status = "<button type=\"button\" class=\"btn btn-success btn-xs\">Giành cho khách</button>";	
				
				if(resultSet.getString(6).equals("3"))
					button_status = "<button type=\"button\" class=\"btn btn-danger btn-xs\">Blocked</button>";

				if(resultSet.getString(6).equals("2")) 
					button_status = "<button type=\"button\" class=\"btn btn-success btn-xs\">Giành cho KM</button>";
				
				ja.put(button_status);
				
		
                String button = "<button type=\"button\"  data-toggle=\"modal\" data-target=\"#infoFFModal\" class=\"btn btn-primary\" data-ff-id=\""+resultSet.getString(1)+"\">Chi tiết</button>"
                				+"<button type=\"button\" class=\"btn btn-danger\" data-ff-id=\""+resultSet.getString(1)+"\">Xóa</button>";
				
				ja.put(button);
				
				array.put(ja);
			}
			
			resultSet.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try 
		{
			result.put("aaData", array);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	

	public FastFood getFastFoodInfo(String ffId) {
		FastFood ff = new FastFood();
		String query = "select * from FastFood where MaFF = '"+ffId+"'";
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()){
				
				ff.setMaFF(resultSet.getString(1));
				ff.setTenFF(resultSet.getString(2));
				ff.setLoaiFF(resultSet.getString(3));
				ff.setMoTa(resultSet.getString(4));	
				ff.setGiaTien(util.insertComma(resultSet.getInt(5)));
				ff.setTrangThai(resultSet.getInt(6));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return ff;
	}

	public boolean updateFastFood(FastFood ff) {
		
		String query = "update FastFood SET TenFF =?, LoaiFF = ?, MoTa =?, GiaTien = ?, TrangThai = ?"
				+ " where MaFF = ?";

		PreparedStatement pr;
		try {
				
			pr = connection.prepareStatement(query);
				
			pr.setString(1, ff.getTenFF());
			pr.setString(2, ff.getLoaiFF());
			pr.setString(3, ff.getMoTa());
			
			String giaTien_str = ff.getGiaTien();
			giaTien_str = giaTien_str.replace(",", "");
			
			pr.setInt(4, Integer.parseInt(giaTien_str));
			
			pr.setInt(5, ff.getTrangThai());
			
			pr.setString(6, ff.getMaFF());
					
			pr.executeUpdate();
			
			pr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean addFastFood(FastFood ff) {
		
		String query = "insert into FastFood VALUES(?,?,?,?,?,?)";
		
		PreparedStatement pr;
		try {
			
	
			pr = connection.prepareStatement(query);
			
			pr.setString(1, getNewFFId());	
			pr.setString(2, ff.getTenFF());
			pr.setString(3, ff.getLoaiFF());
			pr.setString(4, ff.getMoTa());
			
			String giaTien_str = ff.getGiaTien();
			giaTien_str = giaTien_str.replace(",", "");
			
			pr.setInt(5, Integer.parseInt(giaTien_str));
			pr.setInt(6, ff.getTrangThai());
			
			pr.executeUpdate();
			
			pr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private String getNewFFId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaFF FROM FastFood order by MaFF;");
			int id=0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(2,idDo.length()));
				if(temp > id) id=temp;
			}
			//
			if (idDo == null) return "FF1";
			id++;
			System.out.println("newID="+id);
			return "FF" + id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteFastFood(String ffId) {
		
		String query = "update FastFood SET TrangThai = '0' where MaFF = '"+ffId+"'";
		
		try {
			statement.execute(query);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
}
