package struts.bean;

public class TicketType {

	private String maLoaiVe;
	private String tenLoaiVe;
	private String moTa;
	private int soGheThuong;
	private int soGheVip;

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
