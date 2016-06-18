package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import struts.bo.ShowTimesBO;
import struts.form.ShowTimesForm;
import struts.util.Utilities;

public class ShowTimesAction extends Action {
	
	Utilities util = new Utilities();
	ShowTimesBO showTimesBO = new ShowTimesBO();
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		ShowTimesForm showTimesForm = (ShowTimesForm) form;

		String current_date = request.getParameter("date");
		
		if(current_date==null) 
		{
			String selected_date = showTimesForm.getCurrentDate();
			
			if(selected_date!= null) current_date = selected_date;
			
			else current_date = util.getCurrentDate();
		}
		
		else showTimesForm.setCurrentDate(current_date);
		
		
		//lấy danh sách lịch sắp tới
		showTimesForm.setScheduledDates(util.getScheduledDates(showTimesBO.getAllScheduledDates()));
		
		showTimesForm.setShowtimes(showTimesBO.getShowTimes(current_date));
		
		return mapping.findForward("toShowTimes");
	}

}
