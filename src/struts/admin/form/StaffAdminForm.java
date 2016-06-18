package struts.admin.form;

import org.apache.struts.action.ActionForm;

import struts.admin.bean.Staff;

public class StaffAdminForm extends ActionForm {

	private Staff currentStaff = new Staff();

	public Staff getCurrentStaff() {
		return currentStaff;
	}

	public void setCurrentStaff(Staff currentStaff) {
		this.currentStaff = currentStaff;
	}

	public void reset() {
		currentStaff = new Staff();
	}
	

}
