package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import struts.bo.HomeBO;
import struts.form.MoviesForm;

public class MoviesAction extends DispatchAction {
	
	public ActionForward showing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		HomeBO homeBO = new HomeBO();
		MoviesForm moviesForm = (MoviesForm) form;
		moviesForm.setMovies(homeBO.getMoviesList(1));
		moviesForm.setListType("showing");
		
		return mapping.findForward("toMovies");
	}

	public ActionForward coming_soon(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		HomeBO homeBO = new HomeBO();
		MoviesForm moviesForm = (MoviesForm) form;
		moviesForm.setMovies(homeBO.getMoviesList(2));
		moviesForm.setListType("coming_soon");
		
		return mapping.findForward("toMovies");

	}
	
	
}
