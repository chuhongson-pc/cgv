package struts.admin.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import struts.admin.bean.Room;

public class RoomManagementForm extends ActionForm {

	private ArrayList<Room> roomList = new ArrayList<Room>();

	private String maPhong;
	private String tenPhong;
	private String loaiPhong;
	private int soGhe;
	private String soDoGhe;
	private int trangThai;
	



	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public ArrayList<Room> getRoomList() {
		return roomList;
	}

	public void setRoomList(ArrayList<Room> roomList) {
		this.roomList = roomList;
	}

	public String getTenPhong() {
		return tenPhong;
	}

	public void setTenPhong(String tenPhong) {
		this.tenPhong = tenPhong;
	}

	public String getLoaiPhong() {
		return loaiPhong;
	}

	public void setLoaiPhong(String loaiPhong) {
		this.loaiPhong = loaiPhong;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public String getSoDoGhe() {
		return soDoGhe;
	}

	public void setSoDoGhe(String soDoGhe) {
		this.soDoGhe = soDoGhe;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		
		maPhong = "";
		tenPhong = "";
		loaiPhong = "";
		soDoGhe = "";
		soGhe = 0;
		trangThai = 0;	
	}
	
	

}
