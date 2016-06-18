package struts.admin.bean;

public class SessionTime {

	private String gioChieu;
	private String maPhim;
	private String tenPhim;
	private String maPhong;
	private int thoiLuong;
	private String fullTime;
	private boolean type3d;
	private String maSuat;
	private int trangThai;
	
	
	

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaSuat() {
		return maSuat;
	}

	public void setMaSuat(String maSuat) {
		this.maSuat = maSuat;
	}

	public boolean isType3d() {
		return type3d;
	}

	public void setType3d(boolean type3d) {
		this.type3d = type3d;
	}

	public String getMaPhong() {
		return maPhong;
	}

	public void setMaPhong(String maPhong) {
		this.maPhong = maPhong;
	}

	public String getFullTime() {
		return fullTime;
	}

	public void setFullTime(String fullTime) {
		this.fullTime = fullTime;
	}

	public int getThoiLuong() {
		return thoiLuong;
	}

	public void setThoiLuong(int thoiLuong) {
		this.thoiLuong = thoiLuong;
	}

	public String getGioChieu() {
		return gioChieu;
	}

	public void setGioChieu(String gioChieu) {
		this.gioChieu = gioChieu;
	}

	public String getMaPhim() {
		return maPhim;
	}

	public void setMaPhim(String maPhim) {
		this.maPhim = maPhim;
	}

	public String getTenPhim() {
		return tenPhim;
	}

	public void setTenPhim(String tenPhim) {
		this.tenPhim = tenPhim;
	}

}
