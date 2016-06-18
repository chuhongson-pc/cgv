package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.bo.HomeBO;
import struts.form.HomeForm;

public class HomeAction extends Action {

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HomeBO homeBO = new HomeBO();
		
		HomeForm homeForm = (HomeForm) form;
		
		homeForm.setTopMovies(homeBO.getTopMoives());
		homeForm.setPosterList(homeBO.getPosterList());

		return mapping.findForward("toHome");
	}

}
