package struts.admin.form;

import org.apache.struts.action.ActionForm;

public class DateAdminForm extends ActionForm {
	private String suatChieuSom;
	private String suatChieuCuoiTuan;
	private String thuTuVuiVe;
	private String ngayLe;
	private String giaNgayLe;
	public String getSuatChieuSom() {
		return suatChieuSom;
	}
	public void setSuatChieuSom(String suatChieuSom) {
		this.suatChieuSom = suatChieuSom;
	}
	public String getSuatChieuCuoiTuan() {
		return suatChieuCuoiTuan;
	}
	public void setSuatChieuCuoiTuan(String suatChieuCuoiTuan) {
		this.suatChieuCuoiTuan = suatChieuCuoiTuan;
	}
	public String getThuTuVuiVe() {
		return thuTuVuiVe;
	}
	public void setThuTuVuiVe(String thuTuVuiVe) {
		this.thuTuVuiVe = thuTuVuiVe;
	}
	public String getNgayLe() {
		return ngayLe;
	}
	public void setNgayLe(String ngayLe) {
		this.ngayLe = ngayLe;
	}
	public String getGiaNgayLe() {
		return giaNgayLe;
	}
	public void setGiaNgayLe(String giaNgayLe) {
		this.giaNgayLe = giaNgayLe;
	}

	

}
