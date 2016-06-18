package struts.admin.bean;

public class Customer {

	private String username;
	private String password;
	private String fullname;
	private String cmnd;
	private String dob;
	private String email;
	private String phonenumber;
	private int soDuTaiKhoan;
	private String address;
	private String gender;
	private String trangThai;
	private int diemTichLuy;
	private String ngayKhoiTao;

	public int getSoDuTaiKhoan() {
		return soDuTaiKhoan;
	}

	public void setSoDuTaiKhoan(int soDuTaiKhoan) {
		this.soDuTaiKhoan = soDuTaiKhoan;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public int getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(int diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public String getNgayKhoiTao() {
		return ngayKhoiTao;
	}

	public void setNgayKhoiTao(String ngayKhoiTao) {
		this.ngayKhoiTao = ngayKhoiTao;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getCmnd() {
		return cmnd;
	}

	public void setCmnd(String cmnd) {
		this.cmnd = cmnd;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhonenumber() {
		return phonenumber;
	}

	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

}
