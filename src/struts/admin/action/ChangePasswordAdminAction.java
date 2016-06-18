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
import struts.admin.form.ChangePasswordAdminForm;

public class ChangePasswordAdminAction extends Action {
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ChangePasswordAdminForm fo = (ChangePasswordAdminForm) form;
		
		CommonBO bo = new CommonBO();
		
		String userName = (String) request.getSession().getAttribute("useradmin");
		String password = bo.getPassword(userName);
		
		ActionErrors errors = new ActionErrors();
		String matKhauCu = fo.getMatKhauCu();
		String matKhauMoi = fo.getMatKhauMoi();
		String xacNhan = fo.getXacNhan();
		
		int i = 1;
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("login");
		} 
		else 
		{
			if (matKhauCu.trim().length() < 4 || matKhauCu.trim().length() > 14) {
				i = 0;
				errors.add("matKhauCuError", new ActionMessage(
						"errors.matKhauCu.length"));
				saveErrors(request, errors);
			} else if (!password.equals(fo.getMatKhauCu())) {
				i = 0;
				errors.add("matKhauCuError", new ActionMessage(
						"errors.matKhauCu"));
				saveErrors(request, errors);
			}

			if (matKhauMoi.trim().length() < 4
					|| matKhauMoi.trim().length() > 14) {
				i = 0;
				errors.add("matKhauMoiError", new ActionMessage(
						"errors.matKhauMoi.length"));
				saveErrors(request, errors);
			}

			if (!matKhauMoi.trim().equals(xacNhan.trim())) {
				i = 0;
				errors.add("xacNhanError", new ActionMessage(
						"errors.matKhauMoi.equal"));
				saveErrors(request, errors);
			}
			if (i == 1) {
		
				if(!bo.editMatKhau(userName, fo.getMatKhauCu(), fo.getMatKhauMoi()))
				{
					errors.add("alertError", new ActionMessage(
							"errors.alertError.equal"));
					saveErrors(request, errors);
				}
				else
				{
					errors.add("alertSuccess", new ActionMessage(
							"errors.alertSuccess.equal"));
					saveErrors(request, errors);
				}
			}

		}
		return mapping.findForward("success");
	}
}
