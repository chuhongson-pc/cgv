package struts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import struts.admin.bean.News;

public class NewsDAO extends DAO {

	public ArrayList<News> getNewsList(int type) {
		
		String query = "";
		if(type==1){
			query = "select * from TinTuc where TrangThai = '1' and LoaiTT = 'P';";
		}
		else {
			query = "select * from TinTuc where TrangThai = '1' and LoaiTT = 'N';";
		}
		
		
		ArrayList<News> list = new ArrayList<News>();
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				News n = new News();
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
				
				list.add(n);
			}
			
			
			resultSet.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	public struts.bean.News getNewsInfo(String articleId) {
		
		String query = "select * from TinTuc where MaTT = '"+articleId+"' and TrangThai = '1'";
		
		struts.bean.News n = new struts.bean.News();
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				
				n.setTieuDe(resultSet.getString(2));
				
				String thoiGian = "";
				if(resultSet.getString(4) != null){
					thoiGian = "Từ " + util.convertDate(resultSet.getString(4));
				}
				
				if(resultSet.getString(5) != null){
					
					thoiGian += " đến " + util.convertDate(resultSet.getString(5));
				}
				
				n.setThoiGian(thoiGian);
				
				n.setNoiDung(resultSet.getString(6));
				
			}
			
			resultSet.close();
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return n;
		
	}

}
