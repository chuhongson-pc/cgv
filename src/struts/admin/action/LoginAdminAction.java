package struts.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import struts.admin.bo.CommonBO;
import struts.admin.form.LoginAdminForm;

public class LoginAdminAction extends Action {
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		//System.out.println("LOGIN ADMIN ACTION");
		
		CommonBO commonBO = new CommonBO();

		LoginAdminForm loginAdminForm = (LoginAdminForm) form;

		String username = loginAdminForm.getUsername();
		String password = loginAdminForm.getPassword();

		ActionForward forward;
		
		if (!username.equals("") || !password.equals("")) {
			
			int role = commonBO.checkLogin(username, password);
			if (role == 2 || role == 3) {
				
				request.getSession(true).setAttribute("useradmin", username);
				request.getSession(true).setAttribute("role", role);
				
				forward = mapping.findForward("toDashboard");

			} else {

	
				ActionErrors errors=new ActionErrors();
				errors.add("loginError", new ActionMessage("error.login.notvalid"));			
			    saveErrors(request, errors);
			    loginAdminForm.reset(mapping, request);
			    
				forward = mapping.findForward("toLoginAdmin");
				
			}
		}
		
		else forward = mapping.findForward("toLoginAdmin");

		return forward;
	}
	
}
