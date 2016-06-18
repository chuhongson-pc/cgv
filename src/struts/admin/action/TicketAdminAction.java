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
import org.json.JSONException;
import org.json.JSONObject;

import struts.admin.bean.FastFood;
import struts.admin.bean.TicketType;
import struts.admin.bo.TicketAdminBO;

public class TicketAdminAction extends DispatchAction {

	TicketAdminBO ticketAdminBO = new TicketAdminBO();

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
        

		return mapping.findForward("toTicketList");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		JSONObject jsonResult = ticketAdminBO.getTicketList();

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out;

		try {
			out = response.getWriter();
			System.out.println(jsonResult);
			out.print(jsonResult);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	public ActionForward fflist(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		ArrayList<FastFood> listFF = ticketAdminBO.getFFList();

		JSONArray jsonResult = new JSONArray(listFF);

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

		return null;
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		TicketType ticket = new TicketType();

		ticket.setTenLoaiVe(request.getParameter("tenLoaiVe"));
		ticket.setLoaiSuatApDung(request.getParameter("loaiSuatApDung"));
		ticket.setMoTa(request.getParameter("moTa"));
		ticket.setSoGheThuong(Integer.parseInt(request.getParameter("soGheThuong")));
		ticket.setSoGheVip(Integer.parseInt(request.getParameter("soGheVip")));
		ticket.setGiaTien(Integer.parseInt(request.getParameter("giaTien")));
		ticket.setMaFF(request.getParameter("maFF"));

		JSONObject result = new JSONObject();
		try {
			if (ticketAdminBO.addTicket(ticket)) {
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

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		String ticketId = request.getParameter("id");

		JSONObject result = new JSONObject();
		try {
			if (ticketAdminBO.deleteTicket(ticketId)) {
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

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		TicketType ticket = new TicketType();

		ticket.setMaLoaiVe(request.getParameter("maLoaiVe"));
		System.out.println("update="+ticket.getMaLoaiVe());
		
		ticket.setTenLoaiVe(request.getParameter("tenLoaiVe"));
		ticket.setLoaiSuatApDung(request.getParameter("loaiSuatApDung"));
		ticket.setMoTa(request.getParameter("moTa"));
		ticket.setSoGheThuong(Integer.parseInt(request.getParameter("soGheThuong")));
		ticket.setSoGheVip(Integer.parseInt(request.getParameter("soGheVip")));
		ticket.setGiaTien(Integer.parseInt(request.getParameter("giaTien")));
		ticket.setMaFF(request.getParameter("maFF"));
		ticket.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));
		
		

		JSONObject result = new JSONObject();
		try {
			if (ticketAdminBO.updateTicket(ticket)) {
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

	public ActionForward info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		String ticketId = request.getParameter("id");

		System.out.println("tickedId=" + ticketId);

		JSONObject jsonResult = new JSONObject(
				ticketAdminBO.getTicketInfo(ticketId));

		sendJSON(response, jsonResult);

		return null;
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

	//
	public void toSend(HttpServletResponse response, Object list) {
		JSONArray json = null;
		try {
			json = new JSONArray(list);
		} catch (JSONException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

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
}
