package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import struts.admin.bean.Poster;
import struts.dao.DAO;

public class PosterAdminDAO extends DAO {

	public ArrayList<Poster> getPosterList() {
		
		ArrayList<Poster> list = new ArrayList<Poster>();
		
		String query = "select * from Poster";
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				Poster p = new Poster();
				
				p.setMaPoster(resultSet.getString(1));
				p.setPoster(resultSet.getString(2));
				p.setMoTa(resultSet.getString(3));
				p.setLink(resultSet.getString(4));
				
				list.add(p);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	public boolean deletePoster(String posterId) {
		
		String query = "delete from Poster where MaPoster = '"+posterId+"'";
		
		try {
			Statement st = connection.createStatement();
			st.execute(query);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
		
	}

	public Poster getPosterInfo(String posterId) {
		Poster p = new Poster();
		
		String query = "select * from Poster where MaPoster = '"+posterId+"'";
		
		try {
			Statement st = connection.createStatement();
			ResultSet resultSet = st.executeQuery(query);
			
			while(resultSet.next()){
				p.setMaPoster(resultSet.getString(1));
				p.setPoster(resultSet.getString(2));
				p.setMoTa(resultSet.getString(3));
				p.setLink(resultSet.getString(4));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return p;
	}

	public boolean updatePoster(Poster poster) {
		// TODO Auto-generated method stub
		String query = "update Poster SET Poster = ?, MoTa = ?, Link = ? where MaPoster = ?";
		int row = 0;
		try {
			PreparedStatement pre = connection.prepareStatement(query);
			
			pre.setString(1,poster.getPoster());
			pre.setString(2,poster.getMoTa());
			pre.setString(3,poster.getLink());
			pre.setString(4,poster.getMaPoster());
			
			row = pre.executeUpdate();
			
			
			pre.close();
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(row != 0) return true; else return false;
	}

	public String getNewPosterId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaPoster FROM Poster order by MaPoster;");
			int id=0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(2,idDo.length()));
				if(temp > id) id=temp;
			}
			//
			if (idDo == null) return "PT1";
			id++;
			return "PT" + id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public String addPoster(Poster poster) {
		
		String query = "insert Poster VALUES(?, ?, ?, ?)";
		String newid = getNewPosterId();
		try {
			PreparedStatement pre = connection.prepareStatement(query);
			
			pre.setString(1, newid);
			pre.setString(2, poster.getPoster());
			pre.setString(3, poster.getMoTa());
			pre.setString(4, poster.getLink());
			
			pre.executeUpdate();
			
			pre.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return newid;
		
	}

}
