package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import struts.bo.HomeBO;
import struts.form.ChangePasswordForm;

public class ChangePasswordAction extends DispatchAction {

	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		
		
		ChangePasswordForm changePasswordForm = (ChangePasswordForm) form;
		
		changePasswordForm.reset(mapping, request);
		
		return mapping.findForward("toChangePassword");

	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//kiem tra quen dang nhap
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		
		HomeBO homeBO = new HomeBO();

		// check
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		// kiểm tra mật khẩu hiện tại
		ChangePasswordForm changePasswordForm = (ChangePasswordForm) form;
		String current_password = changePasswordForm.getCurrent_password();

		String username = (String) request.getSession().getAttribute("user");

		if (!homeBO.checkLogin(username, current_password)) {
			// reload
			ActionErrors errors = new ActionErrors();
			errors.add("passwordMessageError", new ActionMessage(
					"error.password.notvalid"));

			saveErrors(request, errors);

			changePasswordForm.reset(mapping, request);

			return mapping.findForward("toChangePassword");
		} else {
			// update new password
			if (homeBO.updatePassword(username,
				changePasswordForm.getPassword())) {
				// update thanh cong
				
				ActionMessages  messages = new ActionMessages();
				messages.add("aMsg", new ActionMessage("update.password.done"));
				saveMessages(request, messages);
			
			} else {
				// update ko thanh cong
			}
			return mapping.findForward("toChangePassword");
		}

	}

}
