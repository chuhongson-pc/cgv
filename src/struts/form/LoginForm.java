package struts.form;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

public class LoginForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private String username;
	private String password;

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

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		// TODO Auto-generated method stub
		username = "";
		password = "";
	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		// create a new instance of actionerrors
		ActionErrors actionErrors = new ActionErrors();

		// call Utilities
//		Utilities utilities = new Utilities();

		// valdiate name
		if (username.equals(""))
			actionErrors.add("usernameMessageError", new ActionMessage(
					"error.username.null"));
		else if (username.length() < 3 || username.length() > 20) {
			//actionErrors.add("usernameMessageError", new ActionMessage("error.username.length"));
		}

		// valdiate password
		if (password.equals(""))
			actionErrors.add("passwordMessageError", new ActionMessage(
					"error.password.null"));
		else if (password.length() < 3 || password.length() > 50) {
			//actionErrors.add("passwordMessageError", new ActionMessage("error.password.length"));
		}

		// return collection of action messages
		return actionErrors;
	}

}
