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

import struts.dao.DAO;

public class TransactionAdminDAO extends DAO {
	
	public int getTotalRecord() {

		String query = "select count(MaGD) from GiaoDich where TrangThai = '1'";

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

	public JSONObject getTransactionList(HashMap<String, String> info, String role) {
		
		System.out.println("getTransactionList");

		int totalRecords = getTotalRecord();
		
		System.out.println("total="+totalRecords);
		
		int totalAfterSearch = totalRecords;
		
		
		JSONObject result = new JSONObject();
		JSONArray array = new JSONArray();
		String searchSQL = "";
		
		String timKiem = info.get("timKiem");
		String maGD = info.get("maGD");
		String username = info.get("username");
		String phim = info.get("phim");
		String thoiGianGD = info.get("thoiGianGD");
		
		
		String tenCot = info.get("tenCot");
		String direction = info.get("DIRECTION");
		String initial = info.get("INITIAL");
		String recordsize = info.get("kichThuoc");
		
		System.out.println("init="+initial);
		System.out.println("record="+recordsize);
		
		

		String globeSearch = " GiaoDich.MaGD like '%" + maGD + "%' or GiaoDich.Username like '%" + username +"%'"
				+ " or Phim.TenPhim like '%" + phim + "%'" + " or GiaoDich.ThoiGianGD like '%"
				+ thoiGianGD + "%'";
		
		String timMaGD = " GiaoDich.MaGD like '%" + maGD + "%'";
		String timUsername = " GiaoDich.Username like '%" + username + "%'";
		String timPhim = " Phim.TenPhim like '%" + phim + "%'";
		String timThoiGianGD = " GiaoDich.ThoiGianGD like '%" + thoiGianGD + "%'";
		
		
		if (!timKiem.equals("")) {

			searchSQL = globeSearch;

		} else if (!maGD.equals("")) {
			searchSQL = timMaGD;
		} else if (!username.equals("")) {
			searchSQL = timUsername;
		} else if (!phim.equals("")) {
			searchSQL = timPhim;
		} else if (!thoiGianGD.equals("")) {
			searchSQL = timThoiGianGD;
		}
		
		
		if(!searchSQL.equals("")) searchSQL += " and ";
		
		String sql = "select * from "
					+"( "
					+"select GiaoDich.MaGD, GiaoDich.Username , Phim.TenPhim, CAST(LichChieu.TGBatDau as DATE) N  "
						+", CAST(LichChieu.TGBatDau as TIME) G, PhongChieu.TenPhong, GiaoDich.ThoiGianGD, GiaoDich.TongTien "
						+", ROW_NUMBER() OVER( order by GiaoDich."+tenCot+" "+direction+" ) RN "
						+"from GiaoDich join LichChieu on GiaoDich.MaSuat = LichChieu.MaSuat "
						+"join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
						+"join Phim on Phim.MaPhim = LichChieu.MaPhim  "
						+ "where "+searchSQL+" GiaoDich.TrangThai = '1'"
					+") A "
						+"where RN BETWEEN "+(initial)+" AND "+(Integer.parseInt(initial)+Integer.parseInt(recordsize))+"  "
						+"order by " + tenCot + " " + direction;
		
		System.out.println("query for search:"+sql);
		
		
		PreparedStatement stmt;
		try {
			stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();

			while (rs.next()) {
				JSONArray ja = new JSONArray();
				ja.put(rs.getString(1));
				
				ja.put(rs.getString(2));
				
				String gioChieu = rs.getString(5);
				gioChieu = gioChieu.substring(0,5);
				
				String phim_str = "<div class=\"ticket-name\" data-id=\""+rs.getString(1)+"\">"+rs.getString(3)+"</div>"
								+"<div class=\"description-ticket\" data-id=\""+rs.getString(1)+"\">"+util.convertDate(rs.getString(4)) +" | "+gioChieu+" | "+rs.getString(6)+"</div>";
				
				ja.put(phim_str);
				
				String ngayGD = rs.getString(7);
				ngayGD = util.convertDate(ngayGD.substring(0,10));
				String gioGD = rs.getString(7);
				gioGD = gioGD.substring(11,19);
				
				ja.put(ngayGD + " " + gioGD);
				
				ja.put(util.insertComma(rs.getInt(8)));
				
				String button = "";
				
				if(role.equals("3")){
					button = "<button type=\"button\"  data-toggle=\"modal\" data-target=\"#infoTransactionModal\" class=\"btn btn-primary\" data-ticket-id=\""+rs.getString(1)+"\">Chi tiết</button>"
							
							+ "<button type=\"button\" class=\"btn btn-danger\"  data-ticket-id=\""+rs.getString(1)+"\">Hủy</button>";
				}
				
				else 
				{
					button = "<button type=\"button\"  data-toggle=\"modal\" data-target=\"#infoTransactionModal\" class=\"btn btn-primary\" data-ticket-id=\""+rs.getString(1)+"\">Chi tiết</button>";			
				}
	
				
				ja.put(button);
				
				array.put(ja);
		
			}
			
			
			stmt.close();
			rs.close();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
		//--------
		if (timKiem != "" || phim != "" || maGD != "" || thoiGianGD != "" || username != "" ) {
			
			
			String query = "select count(*)  "	
					+"from GiaoDich join LichChieu on GiaoDich.MaSuat = LichChieu.MaSuat "
					+"join PhongChieu on PhongChieu.MaPhong = LichChieu.MaPhong "
					+"join Phim on Phim.MaPhim = LichChieu.MaPhim  "
					+ "where "+searchSQL+" GiaoDich.TrangThai = '1'";
			
			System.out.println("query for pagination:"+query);
			
			try {
				PreparedStatement st = connection.prepareStatement(query);
				ResultSet results = st.executeQuery();

				if (results.next()) {
					totalAfterSearch = results.getInt(1);
				}
				
				st.close();
				results.close();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		
		//--------------
		try 
		{
			result.put("iTotalRecords", totalRecords);
			result.put("iTotalDisplayRecords", totalAfterSearch);
			result.put("aaData", array);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return result;

	}

	public JSONObject getTicketInfo(String transactionId) {
		
		System.out.println("getTicketInfo");
		
		JSONObject result = new JSONObject();
		
		try 
		{
			ArrayList<String[]> ticket_list = getTicketOfTransaction(transactionId);
			
			JSONArray ticket_jo = new JSONArray(ticket_list);
		
			
			ArrayList<String[]> ff_list = getFFOfTransaction(transactionId);
			
			JSONArray ff_jo = new JSONArray(ff_list);
			
			String seatList = getSeatList(transactionId);
			
			int tongTien = getTotal(transactionId);
			
			System.out.println("size:"+ticket_list.size() +" "+ ff_list.size());
			
			result.put("ticket", ticket_jo);
			result.put("ff", ff_jo);
			result.put("seats", seatList);
			result.put("tongTien", util.insertComma(tongTien));
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		System.out.println("rs="+result);
		return result;
		

	}

	private int getTotal(String transactionId) {
		int tongTien = 0;
		
		String query = "select TongTien from GiaoDich where MaGD = '"+transactionId+"'";
		
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				tongTien += rs.getInt(1);
			}
			
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tongTien;
		
	}

	private String getSeatList(String transactionId) {
		
		String list ="";
		String query = "select MaGhe from ChiTietViTri where MaGD = '"+transactionId+"'";
		
		
		try {
			Statement st = connection.createStatement();
			ResultSet rs = st.executeQuery(query);
			while(rs.next()){
				list += rs.getString(1) + " ";
			}
			
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list.trim();
	}

	private ArrayList<String[]> getTicketOfTransaction(String transactionId) {
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		String query = "select LoaiVe.TenLoaiVe, LoaiVeGiaoDich.SL from LoaiVeGiaoDich "
				+"join LoaiVe on LoaiVe.MaLoaiVe = LoaiVeGiaoDich.MaLoaiVe "
				+"where MaGD = '"+transactionId+"'";
		
		
		try {
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){
				String[] e = new String[2];
				e[0] = rs.getString(1);
				e[1] = rs.getString(2);
				
				list.add(e);
			}
			
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	private ArrayList<String[]> getFFOfTransaction(String transactionId) {
		
		ArrayList<String[]> list = new ArrayList<String[]>();
		
		String query = "select FastFood.TenFF, ChiTietFF.SL from ChiTietFF "
				+"join FastFood on FastFood.MaFF = ChiTietFF.MaFF "
				+"where MaGD = '"+transactionId+"'";		
		
		try {
			Statement st = connection.createStatement();
			
			ResultSet rs = st.executeQuery(query);
			
			while(rs.next()){

				String[] e = new String[2];
				e[0] = rs.getString(1);
				e[1] = rs.getString(2);
				
				list.add(e);
			}
			
			rs.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}

	public boolean deleteTransaction(String transactionId) {
		
		String query = "update GiaoDich SET TrangThai = '0' where MaGD = '"+transactionId+"'";
		
		System.out.println("query deleteTransaction:"+query);
		
		try 
		{
			Statement st = connection.createStatement();
			st.execute(query);
			
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	

}
