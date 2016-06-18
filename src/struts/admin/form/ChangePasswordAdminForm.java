package struts.admin.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

public class ChangePasswordAdminForm extends ActionForm {
	private String matKhauCu = "";
	private String matKhauMoi = "";
	private String xacNhan = "";

	public String getMatKhauCu() {
		return matKhauCu;
	}

	public void setMatKhauCu(String matKhauCu) {
		this.matKhauCu = matKhauCu;
	}

	public String getMatKhauMoi() {
		return matKhauMoi;
	}

	public void setMatKhauMoi(String matKhauMoi) {
		this.matKhauMoi = matKhauMoi;
	}

	public String getXacNhan() {
		return xacNhan;
	}

	public void setXacNhan(String xacNhan) {
		this.xacNhan = xacNhan;
	}


	public void reset(ActionMapping mapping, HttpServletRequest request) {
		matKhauCu = "";
		matKhauMoi = "";
		xacNhan = "";
	}
}
