package struts.admin.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import struts.admin.bean.Room;
import struts.dao.DAO;

public class RoomManagementDAO extends DAO {

	public ArrayList<Room> getRoomList() {
		
		ArrayList<Room> list = new ArrayList<Room>();
		
		try {
			ResultSet resultSet = statement.executeQuery("select * from PhongChieu where TrangThai != '0' order by MaPhong;");
			while(resultSet.next()){
				Room r = new Room();
				String maPhong = resultSet.getString(1);
				String tenPhong = resultSet.getString(2);
				String loaiPhong = resultSet.getString(3);
				String soDoGhe = resultSet.getString(4);
				int soGhe = resultSet.getInt(5);
				int trangThai = resultSet.getInt(6);
				r.setMaPhong(maPhong);
				r.setTenPhong(tenPhong);
				r.setLoaiPhong(loaiPhong);
				r.setSoDoGhe(soDoGhe);
				r.setSoGhe(soGhe);
				r.setTrangThai(trangThai);
				list.add(r);
			
			}
			resultSet.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	public Room getRoomById(String roomId) {
		Room r = new Room();
		
		try {
			ResultSet resultSet = statement.executeQuery("select * from PhongChieu where MaPhong = '"+roomId+"'");
			while(resultSet.next()){
				String maPhong = resultSet.getString(1);
				String tenPhong = resultSet.getString(2);
				String loaiPhong = resultSet.getString(3);
				String soDoGhe = resultSet.getString(4);
				int soGhe = resultSet.getInt(5);
				int trangThai = resultSet.getInt(6);
				r.setMaPhong(maPhong);
				r.setTenPhong(tenPhong);
				r.setLoaiPhong(loaiPhong);
				r.setSoDoGhe(soDoGhe);
				r.setSoGhe(soGhe);
				r.setTrangThai(trangThai);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return r;
	}

	public boolean updateRoom(String maPhong, String tenPhong,
			String loaiPhong, String soDoGhe, int soGhe, int trangThai) {
		
		System.out.println(maPhong + " " + tenPhong + " "+loaiPhong+" "+soGhe+" "+trangThai);
		
		int returnQuery = 0;
		try {
			PreparedStatement pre = connection.prepareStatement("update PhongChieu SET TenPhong = ?, LoaiPhong = ?, SoDoGhe = ?, SoGhe = ?, TrangThai = ? where MaPhong = ?");
			
			pre.setString(1, tenPhong);
			pre.setString(2, loaiPhong);
			pre.setString(3, soDoGhe);
			pre.setInt(4, soGhe);
			pre.setInt(5, trangThai);
			pre.setString(6, maPhong);
			
			returnQuery = pre.executeUpdate();
			
			pre.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(returnQuery == 0) return false; else return true;
		
	}

	public boolean addRoom(String tenPhong, String loaiPhong, String soDoGhe,
			int soGhe, int trangThai) {
		int returnQuery = 0 ;
		String roomId = getNewRoomId();
		try {
			PreparedStatement pre = connection.prepareStatement("insert into PhongChieu VALUES(?, ?, ?, ?, ?, ?)");
			
			pre.setString(1, roomId);
			pre.setString(2, tenPhong);
			pre.setString(3, loaiPhong);
			pre.setString(4, soDoGhe);
			pre.setInt(5, soGhe);
			pre.setInt(6, trangThai);
			
			
			returnQuery = pre.executeUpdate();
			
			pre.close();
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		if(returnQuery == 0) return false; else return true;
	}
	
	private String getNewRoomId() {
		String idDo = null;
		try {
			ResultSet resultSet = statement.executeQuery("SELECT MaPhong FROM PhongChieu order by MaPhong;");
			int id=0;
			while (resultSet.next()) {
				idDo = resultSet.getString(1);
				int temp = Integer.parseInt(idDo.substring(2,idDo.length()));
				if(temp > id) id=temp;
			}
			//
			if (idDo == null) return "PC1";
			id++;
			System.out.println("newID="+id);
			return "PC" + id;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}

	}

	public boolean deleteRoom(String maPhong) {
		//delete rom -> delete suat chua chieu
		String query = "update PhongChieu SET TrangThai = '0' where MaPhong = '"+maPhong+"'";
		
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
		else {
			if(deleteSession(maPhong)) return true;
			else return false;
		}
	}
	
	public boolean deleteSession(String maPhong){
		
		String query = "update LichChieu set TrangThai = '0' where MaSuat = ?";
		ArrayList<String> list = getListSessionToDelete(maPhong);
		try {
			PreparedStatement prsm = connection.prepareStatement(query);
			for(int i=0; i< list.size(); i++){
				prsm.setString(1, list.get(i));
				System.out.println("delete Session="+list.get(i));
				prsm.execute();
			}
			prsm.close();
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			return false;
		}
		return true;
		
	}
	
	public ArrayList<String> getListSessionToDelete(String maPhong){
		ArrayList<String> list = new ArrayList<String>();
		String query = "select MaSuat from LichChieu "
				+ "where LichChieu.MaPhong = '"+maPhong+"' and (LichChieu.TrangThai = '1' or LichChieu.TrangThai ='2') "
				+ "and LichChieu.TGBatDau > '"+util.getTimeNow()+"'";
		
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
	
	

}
