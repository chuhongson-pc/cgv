package struts.admin.action;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import struts.admin.bo.DateBO;
import struts.admin.form.DateAdminForm;
import struts.util.Utilities;

public class DateAdminAction extends DispatchAction{
	
	DateBO dateBO = new DateBO();
	Utilities util = new Utilities();

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(role.equals("2")) return mapping.findForward("toNotValidRole");
		
		DateAdminForm dateAdminForm = (DateAdminForm) form;
		
		ArrayList<String> settings = dateBO.getSetting();
		
		dateAdminForm.setSuatChieuSom(settings.get(0));
		dateAdminForm.setSuatChieuCuoiTuan(settings.get(1));
		dateAdminForm.setThuTuVuiVe(settings.get(2));
		dateAdminForm.setNgayLe(settings.get(3));
		dateAdminForm.setGiaNgayLe(settings.get(4));
		
	
		return mapping.findForward("toDateSetting");
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(role.equals("2")) return mapping.findForward("toNotValidRole");
        
		
		DateAdminForm dateAdminForm = (DateAdminForm) form;
		
		int suatChieuSom = Integer.parseInt(util.removeComma(dateAdminForm.getSuatChieuSom()));
		int suatCuoiTuan	= Integer.parseInt(util.removeComma(dateAdminForm.getSuatChieuCuoiTuan()));
		int thu4VuiVe = Integer.parseInt(util.removeComma(dateAdminForm.getThuTuVuiVe()));
		String ngayLe = dateAdminForm.getNgayLe();
		int giaNgayLe = Integer.parseInt(util.removeComma(dateAdminForm.getGiaNgayLe()));
		
		
		if(dateBO.saveSetting(suatChieuSom, suatCuoiTuan, thu4VuiVe, ngayLe, giaNgayLe)){
			System.out.println("update ok");			
			ActionMessages  messages = new ActionMessages();			
			messages.add("insertDone", new ActionMessage("insert.done"));			
			saveMessages(request, messages);
		}
		
		return mapping.findForward("toDateSetting");
	}
	
}
