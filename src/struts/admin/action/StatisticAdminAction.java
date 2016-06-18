package struts.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;

import struts.admin.bo.StatisticAdminBO;

public class StatisticAdminAction extends DispatchAction{
	
	StatisticAdminBO statisticAdminBO = new StatisticAdminBO();
	
	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
        

		return mapping.findForward("toStatistic");
	}
	
	
	public ActionForward month(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		String year = request.getParameter("year");
		
		System.out.println("year="+year);
		
		ArrayList<String> list = statisticAdminBO.getAllMonth(year);

		JSONArray result = new JSONArray(list);

		sendJSON(response, result);

		return null;
	}
	
	public ActionForward statistic(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		String type = request.getParameter("type");
		String time = request.getParameter("time");
		
		
		JSONObject result = statisticAdminBO.getStatistic(type, time);
		
		sendJSON(response, result);
		
		return null;
	}
	
	public ActionForward statistic1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		String year = request.getParameter("year");
		String type = request.getParameter("type");
		
		System.out.println("type="+type+", year="+year);
		
		JSONObject result = statisticAdminBO.getStatistic1(type, year);
		
		sendJSON(response, result);
		
		return null;
	}
	
	
	public ActionForward year(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		


		ArrayList<String> list = statisticAdminBO.getAllYear();

		JSONArray result = new JSONArray(list);

		sendJSON(response, result);

		return null;
	}
	
	public void sendJSON(HttpServletResponse response, Object jsonResult) {
		PrintWriter out;
		response.setContentType("application/json;charset=UTF-8");

		try {
			out = response.getWriter();
			System.out.println(jsonResult);
			out.print(jsonResult);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
