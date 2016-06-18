package struts.admin.bean;

public class TicketType {

	private String maLoaiVe;
	private String tenLoaiVe;
	private String moTa;
	private int soGheThuong;
	private int soGheVip;
	private String loaiSuatApDung;
	private String maFF;
	private String tenFF;
	private int trangThai;
	
	
	
	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaFF() {
		return maFF;
	}

	public void setMaFF(String maFF) {
		this.maFF = maFF;
	}

	public String getTenFF() {
		return tenFF;
	}

	public void setTenFF(String tenFF) {
		this.tenFF = tenFF;
	}

	public String getLoaiSuatApDung() {
		return loaiSuatApDung;
	}

	public void setLoaiSuatApDung(String loaiSuatApDung) {
		this.loaiSuatApDung = loaiSuatApDung;
	}

	private FastFood fastFoodOfTicketType;
	private int giaTien;

	public String getMaLoaiVe() {
		return maLoaiVe;
	}

	public void setMaLoaiVe(String maLoaiVe) {
		this.maLoaiVe = maLoaiVe;
	}

	public String getTenLoaiVe() {
		return tenLoaiVe;
	}

	public void setTenLoaiVe(String tenLoaiVe) {
		this.tenLoaiVe = tenLoaiVe;
	}

	public String getMoTa() {
		return moTa;
	}

	public void setMoTa(String moTa) {
		this.moTa = moTa;
	}

	public int getSoGheThuong() {
		return soGheThuong;
	}

	public void setSoGheThuong(int soGheThuong) {
		this.soGheThuong = soGheThuong;
	}

	public int getSoGheVip() {
		return soGheVip;
	}

	public void setSoGheVip(int soGheVip) {
		this.soGheVip = soGheVip;
	}

	public FastFood getFastFoodOfTicketType() {
		return fastFoodOfTicketType;
	}

	public void setFastFoodOfTicketType(FastFood fastFoodOfTicketType) {
		this.fastFoodOfTicketType = fastFoodOfTicketType;
	}

	public int getGiaTien() {
		return giaTien;
	}

	public void setGiaTien(int giaTien) {
		this.giaTien = giaTien;
	}

}
