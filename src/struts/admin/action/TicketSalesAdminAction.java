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
import struts.admin.bean.Movie;
import struts.admin.bean.Room;
import struts.admin.bean.Session;
import struts.admin.bean.TicketTypeSetting;
import struts.admin.bo.TicketSalesAdminBO;

public class TicketSalesAdminAction extends DispatchAction {

	TicketSalesAdminBO ticketSalesAdminBO = new TicketSalesAdminBO();

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		

		return mapping.findForward("toTicketSales");
	}

	public ActionForward nowshowing(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ArrayList<Movie> list = ticketSalesAdminBO.getNowShowingMovie();

		JSONArray result = new JSONArray(list);

		sendJSON(response, result);

		return null;
	}

	public ActionForward dates(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String movieId = request.getParameter("id");

		ArrayList<Session> list = ticketSalesAdminBO.getDatesOfMovie(movieId);

		JSONArray result = new JSONArray(list);

		sendJSON(response, result);

		return null;
	}

	public ActionForward rooms(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String movieId = request.getParameter("movieId");
		String current_date = request.getParameter("date");

		ArrayList<Room> list = ticketSalesAdminBO.getRoomsOfMovie(movieId,
				current_date);

		JSONArray result = new JSONArray(list);

		sendJSON(response, result);

		return null;
	}

	public ActionForward sessions(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String movieId = request.getParameter("movieId");
		String current_date = request.getParameter("date");
		String roomId = request.getParameter("roomId");

		ArrayList<Session> list = ticketSalesAdminBO.getSessionOfMovie(movieId,
				current_date, roomId);

		JSONArray result = new JSONArray(list);

		sendJSON(response, result);

		return null;
	}

	public ActionForward sessioninfo(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String sessionId = request.getParameter("sessionId");

		ArrayList<TicketTypeSetting> list_ticket_type = ticketSalesAdminBO
				.getTicketTypeOfSession(sessionId);

		String seatsBooked = ticketSalesAdminBO.getSeatsBooked(sessionId);

		String seatsMap = ticketSalesAdminBO.getSeatsMap(sessionId);

		JSONObject result = new JSONObject();
		try {

			result.put("ticket", new JSONArray(list_ticket_type));
			result.put("seatsBooked", seatsBooked);
			result.put("seatsMap", seatsMap);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		sendJSON(response, result);

		return null;
	}

	public ActionForward fastfood(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		ArrayList<FastFood> list_ff = ticketSalesAdminBO.getFastFoodList();

		JSONArray result = new JSONArray(list_ff);

		sendJSON(response, result);

		return null;
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}

		// read data from parameter
		String sessionId = request.getParameter("sessionId");
		String fastFoodTypeSelected = request.getParameter("fastFoodTypeSelected").trim();
		String ticketTypeSelected = request.getParameter("ticketTypeSelected").trim();
		String seatsSelected = request.getParameter("seatsSelected").trim();

		String username = (String) request.getSession().getAttribute("useradmin");

		System.out.println("sessionID=" + sessionId);
		System.out.println("fastFoodTypeSelected=" + fastFoodTypeSelected);
		System.out.println("ticketTypeSelected=" + ticketTypeSelected);
		System.out.println("seatsSelected=" + seatsSelected);

		JSONObject result = new JSONObject();
		try {
			if (ticketSalesAdminBO.checkVaildType(sessionId,
					ticketTypeSelected, fastFoodTypeSelected, seatsSelected)) {

				System.out.println("OK");
				String transactionCode = ticketSalesAdminBO.finishTransaction(
						username, sessionId, ticketTypeSelected,
						fastFoodTypeSelected, seatsSelected);
				if (!transactionCode.equals("-1")) {

					System.out.println("Saved DB !");
					System.out.println("Transaction Code:" + transactionCode);

					result.put("result", transactionCode);

				} else {
					System.out.println("ERROR finishTransaction");
					result.put("result", 0);
				}

			} else {
				System.out.println("ERROR");
				result.put("result", 0);
			}
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

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
