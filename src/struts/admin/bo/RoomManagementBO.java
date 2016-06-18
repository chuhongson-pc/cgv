package struts.admin.bo;

import java.util.ArrayList;

import struts.admin.bean.Room;
import struts.admin.dao.RoomManagementDAO;

public class RoomManagementBO {
	
	RoomManagementDAO roomManagementDAO = new RoomManagementDAO();
	
	public ArrayList<Room> getRoomList() {
		// TODO Auto-generated method stub
		return roomManagementDAO.getRoomList();
	}

	public Room getRoomById(String roomId) {
		// TODO Auto-generated method stub
		return roomManagementDAO.getRoomById(roomId);
	}

	public boolean updateRoom(String maPhong, String tenPhong,
			String loaiPhong, String soDoGhe, int soGhe, int trangThai) {
		// TODO Auto-generated method stub
		return roomManagementDAO.updateRoom(maPhong, tenPhong, loaiPhong, soDoGhe, soGhe, trangThai);
	}

	public boolean addRoom(String tenPhong, String loaiPhong, String soDoGhe,
			int soGhe, int trangThai) {
		// TODO Auto-generated method stub
		return roomManagementDAO.addRoom(tenPhong, loaiPhong, soDoGhe, soGhe, trangThai);
	}

	public boolean deleteRoom(String maPhong) {
		// TODO Auto-generated method stub
		return roomManagementDAO.deleteRoom(maPhong);
	}



}
