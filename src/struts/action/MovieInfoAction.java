package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import struts.bo.MovieBO;
import struts.form.MovieInfoForm;
import struts.util.Utilities;

public class MovieInfoAction extends DispatchAction {
	
	public ActionForward show(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		System.out.println("show info movie action");
		
		MovieBO movieBO = new MovieBO();
		Utilities util = new Utilities();

		MovieInfoForm movieInfoForm = (MovieInfoForm) form;

		String idMovie = request.getParameter("movie");

		String date_current = movieInfoForm.getCurrentDate();
		
		movieInfoForm.setScheduledDates(util.getScheduledDates(movieBO.getScheduledDatesByMovie(idMovie)));
		
		movieInfoForm.setMovieCurrent(movieBO.getMovieInfo(idMovie));

		movieInfoForm.setShowTimeRows(movieBO.getShowtimes(idMovie,date_current));

		return mapping.findForward("toMovieInfo");

	}

	//
	public ActionForward to(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {

		MovieBO movieBO = new MovieBO();
		MovieInfoForm movieInfoForm = (MovieInfoForm) form;

		String date_current = request.getParameter("date");
		String idMovie = movieInfoForm.getMovieCurrent().getMaPhim();

		movieInfoForm.setCurrentDate(date_current);

		movieInfoForm.setShowTimeRows(movieBO.getShowtimes(idMovie,date_current));

		return mapping.findForward("toMovieInfo");

	}
}
