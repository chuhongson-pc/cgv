package struts.admin.form;

import org.apache.struts.action.ActionForm;

import struts.admin.bean.Session;

public class SessionsAdminForm extends ActionForm {
	
	private static final long serialVersionUID = 1L;
	
	private Session currentSession = new Session();

	public Session getCurrentSession() {
		return currentSession;
	}

	public void setCurrentSession(Session currentSession) {
		this.currentSession = currentSession;
	}

}
