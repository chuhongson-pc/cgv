package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.sun.org.apache.regexp.internal.recompile;

import struts.admin.bean.News;
import struts.dao.DAO;
import struts.util.Utilities;

public class NewsAdminDAO extends DAO {
	
	Utilities util = new Utilities();

	public JSONObject getNewList() {
		
		String query = "select * from TinTuc where TrangThai != '0'";
		
		
		JSONArray array = new JSONArray();
		JSONObject result = new JSONObject();
		
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				JSONArray ja = new JSONArray();
				
				
				String thumbnail = "<img src=\"../"+resultSet.getString(7)+"\" alt=\""+resultSet.getString(2)+"\" class=\"thumbail-new\">";
				
				ja.put(thumbnail);
				
				
				String news_time ="";
				if(resultSet.getString(4) != null){
					news_time += "Từ " + util.convertDate(resultSet.getString(4));
					if(resultSet.getString(5) != null){
						news_time += " đến " + util.convertDate(resultSet.getString(5));
					}
				}
				
				String content = resultSet.getString(6);
				
				if(content.length() > 300){
					content = content.substring(0,300) + "...";
				}
				
								
				String ticket_name = "<div class=\"ticket-name\">"+resultSet.getString(2)+"</div>"
								+"<div class=\"description-ticket\">"+content+"</div>"
								+"<div class=\"news-time\">"+news_time+"</div>";
				ja.put(ticket_name);
				
				if(resultSet.getString(3).equals("N")) ja.put("Tin tức");
				else ja.put("Tin khuyến mãi");
				

				String button_status = "<button type=\"button\" class=\"btn btn-success btn-xs\">Activated</button>";
				if(resultSet.getString(8).equals("2"))
					button_status = "<button type=\"button\" class=\"btn btn-danger btn-xs\">Blocked</button>";
				
				
				ja.put(button_status);
				
				
				
				String button = "<a href=\"../news.html?action=info&id="+resultSet.getString(1)+"\">"
						+ "<button type=\"button\" class=\"btn btn-primary\">Chi tiết</button></a>"
								+"<a href=\"../news.html?action=delete&id="+resultSet.getString(1)+"\">"
								+ "<button type=\"button\" class=\"btn btn-danger\">Xóa</button></a>";
		
                ja.put(button);
		
                array.put(ja);
							
			}
			
			resultSet.close();
			st.close();
			
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

	public News getNewsInfo(String newsId) {
		
		News n = new News();
		
		String query = "select * from TinTuc where TrangThai !='0' and MaTT = '"+newsId+"'";
		
		System.out.println(query);
		
		Statement st;
		try {
			st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				n.setMaTT(resultSet.getString(1));
				n.setTieuDe(resultSet.getString(2));
				n.setLoaiTT(resultSet.getString(3));
				if(resultSet.getString(4) != null)
				n.setBatDau(util.convertDate(resultSet.getString(4)));
				
				if(resultSet.getString(5) != null)
				n.setKetThuc(util.convertDate(resultSet.getString(5)));
				
				n.setNoiDung(resultSet.getString(6));
				n.setThumbnail(resultSet.getString(7));
				n.setTrangThai(resultSet.getInt(8));

			}
			
			resultSet.close();
			st.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
		
	}

	public boolean updateNews(News news) {
		
		String query = "update TinTuc SET TieuDe =?, LoaiTT = ?, TGBatDau =?, TGKetThuc =?, NoiDung =?, Thumbnail = ?, TrangThai = ?"
				+ " where MaTT = ?";

		PreparedStatement pr;
		try {
				
			pr = connection.prepareStatement(query);
				
			pr.setString(1, news.getTieuDe());
			pr.setString(2, news.getLoaiTT());
			
			if(news.getLoaiTT().equals("N")){
				pr.setString(3, null);
				pr.setString(4, null);
			}
			else {
				if(!news.getBatDau().equals(""))
				pr.setString(3, util.convertDate(news.getBatDau()));
				else pr.setString(3, null);
				
				if(!news.getKetThuc().equals(""))
				pr.setString(4, util.convertDate(news.getKetThuc()));
				else pr.setString(4, null);
			}
			
			pr.setString(5, news.getNoiDung());
			pr.setString(6, news.getThumbnail());
			
			pr.setInt(7, news.getTrangThai());
			
			pr.setString(8, news.getMaTT());
						
			
			pr.executeUpdate();
			
			pr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public String getNextId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaTT FROM TinTuc order by MaTT;");
			int id=0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(2,idDo.length()));
				if(temp > id) id=temp;
			}
			//
			resultSet.close();
			
			if (idDo == null) return "TT1";
			id++;
			System.out.println("newID="+id);
			return "TT" + id;
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean addNews(News news) {
		String query = "insert into TinTuc VALUES(?,?,?,?,?,?,?,?)";
		
		PreparedStatement pr;
		try {
			
	
			pr = connection.prepareStatement(query);
			pr.setString(1, news.getMaTT());	
			pr.setString(2, news.getTieuDe());
			pr.setString(3, news.getLoaiTT());
			
			if(news.getBatDau() != null) pr.setString(4, util.convertDate(news.getBatDau())); else pr.setString(4, null);
			if(news.getKetThuc() != null) pr.setString(5, util.convertDate(news.getKetThuc())); else pr.setString(5, null);
			
			pr.setString(6, news.getNoiDung());
			pr.setString(7,news.getThumbnail());
			pr.setInt(8, news.getTrangThai());

				
			pr.executeUpdate();
			
			pr.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		return true;
	}

	public boolean deleteNews(String newsId) {
		String query = "update TinTuc SET TrangThai = '0' where MaTT = '"+newsId+"'";
		
		int result = 0;
		try {
			Statement st = connection.createStatement();
			result = st.executeUpdate(query);
			
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		
		if(result == 0) return false;
		else return true;
	}

}
