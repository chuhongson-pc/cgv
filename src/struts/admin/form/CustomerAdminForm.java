package struts.admin.form;

import org.apache.struts.action.ActionForm;

import struts.admin.bean.Customer;

public class CustomerAdminForm extends ActionForm {

	private Customer currentCustomer = new Customer();

	public Customer getCurrentCustomer() {
		return currentCustomer;
	}

	public void setCurrentCustomer(Customer currentCustomer) {
		this.currentCustomer = currentCustomer;
	}

	public void reset() {
		currentCustomer = new Customer();
	}
	

}
