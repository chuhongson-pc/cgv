package struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import struts.bo.HomeBO;

public class OrderHistoryAction extends DispatchAction{
	
	HomeBO homeBO = new HomeBO();

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}

		return mapping.findForward("toOrderHistory");
	}
	
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		
		String username = (String) request.getSession().getAttribute("user");

		JSONObject jsonResult = homeBO.getOrderList(username);

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
	
	
}
