package struts.admin.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import struts.admin.bo.SessionsAdminBO;
import struts.util.Utilities;

public class SessionsAdminAction extends DispatchAction{
	
	SessionsAdminBO sessionsAdminBO = new SessionsAdminBO();
	Utilities util = new Utilities();
	

	public ActionForward show(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		return mapping.findForward("toAddSession");
		
	}
	
	public ActionForward movielist(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
        
        
		//lấy danh sách phim đang chiếu
		System.out.println("movie-list");
	
		String current_date = request.getParameter("date");
		
		toSend(response, sessionsAdminBO.getMovieList(current_date));
			
		return null;
	}
	
	public ActionForward sessiontime(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		System.out.println("session-time");

		String current_date = request.getParameter("date");
		
		String movieId = request.getParameter("movieId");
		
		toSend(response, sessionsAdminBO.getSessionTimeAvailable(current_date, movieId));
			
		return null;
	}
	
	
	public ActionForward tickettype(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		System.out.println("ticket-type");
		
		int type = Integer.parseInt(request.getParameter("type"));
		
		toSend(response, sessionsAdminBO.getTicketType(type));
			
		return null;
	}
	
	public ActionForward datesetting(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		JSONObject result = sessionsAdminBO.getDateSetting();
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(result);
			
			out.print(result);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward toSessionList(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		return mapping.findForward("toSessionList");
	}
	
	public ActionForward sessionlist(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}


		System.out.println("session-list");
		
		String current_date = request.getParameter("date");
	
		toSend(response, sessionsAdminBO.getSessionList(current_date));
			
		return null;
	}
	
	public ActionForward sessioninfo(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		

		System.out.println("session-info");
		
		String sessionId = request.getParameter("id");
		System.out.println("sessionId="+sessionId);
	
		toSend(response, sessionsAdminBO.getSessionInfo(sessionId));
			
		return null;
	}
	
	public ActionForward confirmsession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		System.out.println("confirm-session");
		
		String sessionId = request.getParameter("id");
		
		JSONObject result = new JSONObject();
		try {
			if (sessionsAdminBO.confirmSession(sessionId)) {
				result.put("result", 1);
			} else {
				result.put("result", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sendJSON(response, result);

		return null;
	}
	
	public ActionForward deletesession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		System.out.println("delete-session");
		
		String sessionId = request.getParameter("id");
		
		JSONObject result = new JSONObject();
		try {
			if (sessionsAdminBO.deleteSession(sessionId)) {
				result.put("result", 1);
			} else {
				result.put("result", 0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		sendJSON(response, result);
		
		return null;
	}
	
	
	public ActionForward addsession(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		System.out.println("ticket-type");
		
		String session_str = request.getParameter("time");
		String ticket_str = request.getParameter("ticket");
		
		session_str = session_str.trim();
		session_str = session_str.replaceAll("\\s+", " ").trim();
		
		System.out.println("session_str:"+session_str);
		System.out.println("ticket_str:"+ticket_str);
		
		if(sessionsAdminBO.addSession(session_str, ticket_str)){
			System.out.println("INSERT OK");
			toSend(response, null);
		}
			
		return null;
	}
	
	//
	public void toSend(HttpServletResponse response, Object list){
		JSONArray json = JSONArray.fromObject(list);
		
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(json);
			
			out.print(json);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendJSON(HttpServletResponse response, JSONObject jsonResult) {
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
