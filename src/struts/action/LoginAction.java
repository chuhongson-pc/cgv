package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import struts.bo.HomeBO;
import struts.form.LoginForm;

public class LoginAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception 
	{
		HomeBO homeBO = new HomeBO();
		
		LoginForm loginForm = (LoginForm) form;
		String username = loginForm.getUsername();
		String password = loginForm.getPassword();
		
		if(homeBO.checkLogin(username, password))
		{
			//success 
			request.getSession(true).setAttribute("user", username);
			return mapping.findForward("toHome");
		}
		else
		{
			//reload
			ActionErrors errors=new ActionErrors();
			errors.add("loginMessageError", new ActionMessage("error.login.notvalid"));
			
		    saveErrors(request, errors);

			loginForm.reset(mapping, request);
			
			return mapping.getInputForward();
		}
	}

}
