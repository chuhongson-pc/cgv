package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import struts.dao.DAO;

public class StatisticAdminDAO extends DAO {

	public ArrayList<String> getAllYear() {
		// TODO Auto-generated method stub
		ArrayList<String> list = new ArrayList<String>();
		String query = "select distinct YEAR(TGBatDau) from LichChieu where TrangThai = '1'";
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				list.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public ArrayList<String> getAllMonth(String year) {
		// TODO Auto-generated method stub
		String query = "select distinct cast(cast(TGBatDau as date) as varchar(7)) from LichChieu where YEAR(TGBatDau) = '"+year+"'";
		ArrayList<String> list = new ArrayList<String>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				list.add(resultSet.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public JSONObject getStatistic(String type, String time) {
		// TODO Auto-generated method stub
		String query  = "";
		if(type.equals("soVe")){
			query = "select Phim.TenPhim , SUM(SLDaDat) as SLVe "
					+"from LichChieu "
					+"join Phim on Phim.MaPhim = LichChieu.MaPhim "
					+"where cast(cast(LichChieu.TGBatDau as date) as varchar(7)) = '"+time+"' "
					+"group by Phim.TenPhim "
					+"having SUM(SLDaDat) != 0 "
					+"order by SLVe desc";
		}
		else if(type.equals("doanhThu")){
			query = "select Phim.TenPhim, SUM(TongTien) as TongTien from GiaoDich "
					+"join LichChieu on LichChieu.MaSuat = GiaoDich.MaSuat "
					+"join Phim on Phim.MaPhim = LichChieu.MaPhim "
					+"where cast(cast(ThoiGianGD as date) as varchar(7)) = '"+time+"' and GiaoDich.TrangThai = '1'"
					+"group by Phim.TenPhim "
					+"order by TongTien desc";
		}
		
		System.out.println("query statistic:"+query);
		
		JSONObject result = new JSONObject();
		JSONArray list = new JSONArray();
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				JSONObject item = new JSONObject();
				try {
					item.put("key", resultSet.getString(1));
					item.put("value", resultSet.getInt(2));
					list.put(item);
					
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			resultSet.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result.put("data", list);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

	public JSONObject getStatistic1(String type, String year) {

		String query  = "";
		if(type.equals("doanhThu")){
			query = "SELECT MONTH(ThoiGianGD) as T, SUM(TongTien) FROM GiaoDich "
					+"WHERE YEAR(ThoiGianGD)= ? and GiaoDich.TrangThai = '1' "
					+"GROUP BY MONTH(ThoiGianGD) HAVING MONTH(ThoiGianGD) = ? ";
		}
		else if(type.equals("soVe")){
			query = "select MONTH(LichChieu.TGBatDau) as T, SUM(SLDaDat) "
					+"from LichChieu "
					+"where YEAR(LichChieu.TGBatDau)= ? "
					+"group by MONTH(LichChieu.TGBatDau) having MONTH(LichChieu.TGBatDau) = ? ";
		}
		
		System.out.println("query statistic1:"+query);
		
		JSONObject result = new JSONObject();
		JSONArray list = new JSONArray();
		
		try {
			PreparedStatement prsm = connection.prepareStatement(query);
			
			for(int i=1; i<=12 ; i++){
				prsm.setString(1, year);
				prsm.setString(2, i + "");
				
				ResultSet resultSet = prsm.executeQuery();
				try {
					if(resultSet.next()){
						JSONObject item = new JSONObject();
						item.put("key", "Tháng " + i);
						item.put("value", resultSet.getInt(2));
						list.put(item);
						
					}
					else {
						JSONObject item = new JSONObject();
						item.put("key", "Tháng " + i);
						item.put("value", 0);
						list.put(item);	
					}
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				resultSet.close();
				
			}
			
			prsm.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			result.put("data", list);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}
	
}
