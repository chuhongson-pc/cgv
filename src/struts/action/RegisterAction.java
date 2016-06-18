package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.actions.DispatchAction;

import struts.bo.HomeBO;
import struts.form.RegisterForm;
import struts.util.Utilities;

public class RegisterAction extends DispatchAction {

	static HomeBO homeBO = new HomeBO();
	static Utilities util = new Utilities();
	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//hiển thị
		if (request.getSession().getAttribute("user") != null) {
			return mapping.findForward("toHome");
		}
		return mapping.findForward("toRegister");
	}
	
	
	public ActionForward register(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		//
		RegisterForm registerForm = (RegisterForm) form;
		
		//kiểm tra username
		if(homeBO.checkExistUser(registerForm.getUsername())){
			//đã tồn tại
			ActionErrors errors=new ActionErrors();
			errors.add("usernameMessageError", new ActionMessage("error.existusername"));			
		    saveErrors(request, errors);

			return mapping.findForward("toRegister");
		}
		else{
			//đăng ký
			String username = registerForm.getUsername();
			String password = registerForm.getPassword();
			String fullname = registerForm.getFullname();
			String cmnd = registerForm.getCmnd();
			String dob = util.convertDate(registerForm.getDob());
			String gender = registerForm.getGender();
			System.out.println("chen vao Gender:"+gender);
			String email = registerForm.getEmail();
			String phonenumber = registerForm.getPhonenumber();
			String address = registerForm.getAddress();
			
			
			
			if(homeBO.addNewUser(fullname, dob, gender, address, phonenumber, email, cmnd, username, password)){
				
				//create session
				request.getSession(true).setAttribute("user", username);
				
				//
				return mapping.findForward("toHome");
				
			}
			else
			{
				//return loi trong qua trinh chen 
				ActionErrors errors=new ActionErrors();
				errors.add("usernameMessageError", new ActionMessage("error.existusername"));			
			    saveErrors(request, errors);
				return mapping.findForward("toRegister");
			}
			
			
		}

	}

}
