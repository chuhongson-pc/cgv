package struts.bean;

import java.util.ArrayList;

public class Session {

	private String maSuat;
	private Room roomOfSession;
	private Movie movieOfSession;
	private ArrayList<TicketTypeSetting> ticketTypeSettingList = new ArrayList<TicketTypeSetting>();

	private String gioChieu;
	private String ngayChieu;
	private String thoiGianKetThuc;
	private int SLDaDat;
	private boolean statusFull;
	private boolean type3d;

	public ArrayList<TicketTypeSetting> getTicketTypeSettingList() {
		return ticketTypeSettingList;
	}

	public void setTicketTypeSettingList(
			ArrayList<TicketTypeSetting> ticketTypeSettingList) {
		this.ticketTypeSettingList = ticketTypeSettingList;
	}

	public Room getRoomOfSession() {
		return roomOfSession;
	}

	public void setRoomOfSession(Room roomOfSession) {
		this.roomOfSession = roomOfSession;
	}

	public Movie getMovieOfSession() {
		return movieOfSession;
	}

	public void setMovieOfSession(Movie movieOfSession) {
		this.movieOfSession = movieOfSession;
	}

	public boolean isType3d() {
		return type3d;
	}

	public void setType3d(boolean type3d) {
		this.type3d = type3d;
	}

	public boolean isStatusFull() {
		return statusFull;
	}

	public void setStatusFull(boolean statusFull) {
		this.statusFull = statusFull;
	}

	private int trangThai;

	public String getMaSuat() {
		return maSuat;
	}

	public void setMaSuat(String maSuat) {
		this.maSuat = maSuat;
	}

	public String getGioChieu() {
		return gioChieu;
	}

	public void setGioChieu(String gioChieu) {
		this.gioChieu = gioChieu;
	}

	public String getNgayChieu() {
		return ngayChieu;
	}

	public void setNgayChieu(String ngayChieu) {
		this.ngayChieu = ngayChieu;
	}

	public String getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(String thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public int getSLDaDat() {
		return SLDaDat;
	}

	public void setSLDaDat(int sLDaDat) {
		SLDaDat = sLDaDat;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

}
