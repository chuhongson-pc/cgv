package struts.admin.bean;

public class News {

	private String tieuDe;
	private String maTT;
	private String loaiTT;
	private String noiDung;
	private int trangThai;
	
	private String thumbnail;
	
	private String batDau;
	
	private String ketThuc;
	
	
	

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public String getBatDau() {
		return batDau;
	}

	public void setBatDau(String batDau) {
		this.batDau = batDau;
	}

	public String getKetThuc() {
		return ketThuc;
	}

	public void setKetThuc(String ketThuc) {
		this.ketThuc = ketThuc;
	}

	public String getTieuDe() {
		return tieuDe;
	}

	public void setTieuDe(String tieuDe) {
		this.tieuDe = tieuDe;
	}

	public String getMaTT() {
		return maTT;
	}

	public void setMaTT(String maTT) {
		this.maTT = maTT;
	}

	public String getLoaiTT() {
		return loaiTT;
	}

	public void setLoaiTT(String loaiTT) {
		this.loaiTT = loaiTT;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public int getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(int trangThai) {
		this.trangThai = trangThai;
	}

}
