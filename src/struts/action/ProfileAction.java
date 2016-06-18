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

import struts.bean.Customer;
import struts.bo.HomeBO;
import struts.form.ProfileForm;
import struts.util.Utilities;

public class ProfileAction extends DispatchAction{
	
	static HomeBO homeBO = new HomeBO();
	static Utilities util = new Utilities();

	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		else 
		{
			String username = (String) request.getSession().getAttribute("user");
			ProfileForm profileForm = (ProfileForm) form;
			
			Customer khachHang = homeBO.getUserInfo(username);
			profileForm.setUsername(khachHang.getUsername());
			profileForm.setSoDuTaiKhoan(khachHang.getSoDuTaiKhoan());
			profileForm.setDiemTichLuy(khachHang.getDiemTichLuy());
			profileForm.setAddress(khachHang.getAddress());
			profileForm.setCccode(khachHang.getCccode());
			profileForm.setCmnd(khachHang.getCmnd());
			profileForm.setDob(util.convertDate(khachHang.getDob()));
			profileForm.setEmail(khachHang.getEmail());
			profileForm.setFullname(khachHang.getFullname());
			profileForm.setGender(khachHang.getGender());
			profileForm.setPhonenumber(khachHang.getPhonenumber());
			
			return mapping.findForward("toProfile");
		}
		
	}
	
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}else
		{
			//update
			ProfileForm profileForm = (ProfileForm) form;
			String username = profileForm.getUsername();
			String fullname = profileForm.getFullname();
			String cmnd = profileForm.getCmnd();
			String dob = util.convertDate(profileForm.getDob());
			String gender = profileForm.getGender();
			String email = profileForm.getEmail();
			String phonenumber = profileForm.getPhonenumber();
			String address = profileForm.getAddress();
			//String cccode = profileForm.getCccode();
			
			//
			
			if(homeBO.updateProfile(username, fullname, cmnd, dob, gender, email, phonenumber, address))
			{
				System.out.println("update ok");
				
				ActionMessages  messages = new ActionMessages();
				messages.add("aMsg", new ActionMessage("update.profile.done"));
				
				saveMessages(request, messages);
				return mapping.findForward("toProfile");
			}
			else{
				//update error
				ActionErrors errors=new ActionErrors();
				errors.add("updateMessageError", new ActionMessage("error.update.profile"));			
			    saveErrors(request, errors);
				return mapping.findForward("toRegister");
			}
		}
	}
}
