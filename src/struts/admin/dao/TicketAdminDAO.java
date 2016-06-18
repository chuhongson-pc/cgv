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
import struts.admin.bean.TicketType;
import struts.dao.DAO;

public class TicketAdminDAO extends DAO {

	public JSONObject getTicketList() {
		
		
		JSONArray array = new JSONArray();
		JSONObject result = new JSONObject();
		
		String query = "select LoaiVe.MaLoaiVe, LoaiVe.TenLoaiVe, LoaiVe.LoaiSuatApDung, LoaiVe.MoTa "
				+ ", LoaiVe.SoGheThuong, LoaiVe.SoGheVip, FastFood.TenFF, LoaiVe.GiaTien, LoaiVe.TrangThai from LoaiVe "
				+ " left join FastFood on FastFood.MaFF = LoaiVe.MaFF where LoaiVe.TrangThai != '0'";
		
		try {
			PreparedStatement stmt = connection.prepareStatement(query);
			ResultSet resultSet = stmt.executeQuery();
			
			while(resultSet.next()){
				
				JSONArray ja = new JSONArray();
				
				String ticket_name = "<div class=\"ticket-name\">"+resultSet.getString(2)+"</div>"
                                      +"<div class=\"description-ticket\">"+resultSet.getString(4)+"</div>";
				ja.put(ticket_name);
				
				
				ja.put(resultSet.getString(3));
				
				String number_seats = "<button class=\"btn btn-primary btn-xs number-seat\" title=\"Số ghế thường\">"+resultSet.getString(5)+"</button>"
                                       +"<button class=\"btn btn-warning btn-xs number-seat\" title=\"Số ghế VIP\">"+resultSet.getString(6)+"</button>";
				
				ja.put(number_seats);
				
				String maFF = resultSet.getString(7);
				if(maFF == null) ja.put("");
				else ja.put(maFF);
				
				
				ja.put(util.insertComma(resultSet.getInt(8)));
				
				String button_status = "<button type=\"button\" class=\"btn btn-success btn-xs\">Activated</button>";
				
				if(resultSet.getString(9).equals("2"))
					button_status = "<button type=\"button\" class=\"btn btn-danger btn-xs\">Blocked</button>";
				
				
				ja.put(button_status);
				
                String button = "<button type=\"button\"  data-toggle=\"modal\" data-target=\"#infoTicketModal\" class=\"btn btn-primary\" data-ticket-id=\""+resultSet.getString(1)+"\">Chi tiết</button>"
                				+"<button type=\"button\" class=\"btn btn-danger\" data-ticket-id=\""+resultSet.getString(1)+"\">Xóa</button>";
				
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

	public ArrayList<FastFood> getFFList() {
		
		String query = "select * from FastFood where TrangThai != '0' and TrangThai != '3'";
		ArrayList<FastFood> list = new ArrayList<FastFood>();

		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				FastFood ff = new FastFood();
				ff.setMaFF(resultSet.getString(1));
				ff.setTenFF(resultSet.getString(2));
				ff.setMoTa(resultSet.getString(4));
				
				list.add(ff);
			}
			
			resultSet.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public TicketType getTicketInfo(String ticketId) {
		TicketType t = new TicketType();
		String query = "select LoaiVe.*, FastFood.TenFF from LoaiVe "
						+" left join FastFood on FastFood.MaFF = LoaiVe.MaFF where LoaiVe.TrangThai != '0' and LoaiVe.MaLoaiVe = '"+ticketId+"'"; 
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			while(resultSet.next()){
				
				t.setMaLoaiVe(resultSet.getString(1));
				t.setTenLoaiVe(resultSet.getString(2));
				t.setLoaiSuatApDung(resultSet.getString(3));
				t.setMoTa(resultSet.getString(4));
				t.setSoGheThuong(resultSet.getInt(5));
				
				t.setSoGheVip(resultSet.getInt(6));
				if(resultSet.getString(7) != null) t.setMaFF(resultSet.getString(7));
				else t.setMaFF("0");
				
				t.setGiaTien(resultSet.getInt(8));
				t.setTrangThai(resultSet.getInt(9));
				t.setTenFF(resultSet.getString(10));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return t;
	}

	public boolean updateTicket(TicketType ticket) {
		
		
		String query = "update LoaiVe SET TenLoaiVe =?, LoaiSuatApDung = ?, MoTa =?, SoGheThuong =?, SoGheVip =?, MaFF = ?, GiaTien = ?, TrangThai = ?"
				+ " where MaLoaiVe = ?";

		PreparedStatement pr;
		try {
			
			String maFF = ticket.getMaFF();		
			pr = connection.prepareStatement(query);
				
			pr.setString(1, ticket.getTenLoaiVe());
			pr.setString(2, ticket.getLoaiSuatApDung());
			pr.setString(3, ticket.getMoTa());
			pr.setInt(4, ticket.getSoGheThuong());
			pr.setInt(5, ticket.getSoGheVip());
			
			if(!maFF.equals("0")){				
				pr.setString(6, ticket.getMaFF());
			} else {
				pr.setString(6,null);
			} 
			
			pr.setInt(7, ticket.getGiaTien());
			pr.setInt(8, ticket.getTrangThai());
			
			System.out.println("update MaLoaiVe="+ticket.getMaLoaiVe());
			
			pr.setString(9, ticket.getMaLoaiVe());		
			pr.executeUpdate();
			
			pr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean addTicket(TicketType ticket) {
		
		String query = "insert into LoaiVe VALUES(?,?,?,?,?,?,?,?,?)";
		
		PreparedStatement pr;
		try {
			
	
			pr = connection.prepareStatement(query);
			pr.setString(1, getNewTicketId());	
			pr.setString(2, ticket.getTenLoaiVe());
			pr.setString(3, ticket.getLoaiSuatApDung());
			pr.setString(4, ticket.getMoTa());
			pr.setInt(5, ticket.getSoGheThuong());
			pr.setInt(6, ticket.getSoGheVip());
			
			String maFF = ticket.getMaFF();	
			if(!maFF.equals("0")){				
				pr.setString(7, ticket.getMaFF());
			} else {
				pr.setString(7,null);
			} 
			
			pr.setInt(8, ticket.getGiaTien());
			pr.setInt(9, 1);
				
			pr.executeUpdate();
			
			pr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}
	
	private String getNewTicketId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaLoaiVe FROM LoaiVe order by MaLoaiVe;");
			int id=0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(2,idDo.length()));
				if(temp > id) id=temp;
			}
			//
			if (idDo == null) return "LV1";
			id++;
			System.out.println("newID="+id);
			return "LV" + id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean deleteTicket(String ticketId) {
		
		String query = "update LoaiVe SET TrangThai = '0' where MaLoaiVe = '"+ticketId+"'";
		
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
