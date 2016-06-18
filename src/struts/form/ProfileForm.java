package struts.form;

import org.apache.struts.action.ActionForm;

@SuppressWarnings("serial")
public class ProfileForm extends ActionForm {
	private String username;
	private String fullname;
	private String cmnd;
	private String dob;
	private String gender;
	private String email;
	private String phonenumber;
	private String address;
	private String cccode;
	private String soDuTaiKhoan;
	private String diemTichLuy;
	
	

	public String getDiemTichLuy() {
		return diemTichLuy;
	}

	public void setDiemTichLuy(String diemTichLuy) {
		this.diemTichLuy = diemTichLuy;
	}

	public String getSoDuTaiKhoan() {
		return soDuTaiKhoan;
	}

	public void setSoDuTaiKhoan(String soDuTaiKhoan) {
		this.soDuTaiKhoan = soDuTaiKhoan;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
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

	public String getCccode() {
		return cccode;
	}

	public void setCccode(String cccode) {
		this.cccode = cccode;
	}

}
