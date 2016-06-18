package struts.admin.action;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import struts.admin.bean.FastFood;
import struts.admin.bo.FastFoodAdminBO;

public class FastFoodAdminAction extends DispatchAction{
	
	FastFoodAdminBO fastFoodAdminBO = new FastFoodAdminBO();
	
	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		return mapping.findForward("toFastFoodList");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		JSONObject jsonResult = fastFoodAdminBO.getFastFoodList();

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


	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		
		FastFood ff = new FastFood();
		
		ff.setTenFF(request.getParameter("tenFF"));
		ff.setLoaiFF(request.getParameter("loaiFF"));
		ff.setMoTa(request.getParameter("moTa"));
		ff.setGiaTien(request.getParameter("giaTien"));
		ff.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

		JSONObject result = new JSONObject();
		try {
			if (fastFoodAdminBO.addFastFood(ff)) {
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
		
		

		String ffId = request.getParameter("id");

		JSONObject result = new JSONObject();
		try {
			if (fastFoodAdminBO.deleteFastFood(ffId)) {
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
		
		
		

		FastFood ff = new FastFood();
		
		ff.setMaFF(request.getParameter("maFF"));
		ff.setTenFF(request.getParameter("tenFF"));
		ff.setLoaiFF(request.getParameter("loaiFF"));
		ff.setMoTa(request.getParameter("moTa"));
		ff.setGiaTien(request.getParameter("giaTien"));
		ff.setTrangThai(Integer.parseInt(request.getParameter("trangThai")));

		JSONObject result = new JSONObject();
		try {
			if (fastFoodAdminBO.updateFastFood(ff)) {
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
		
		

		String ffId = request.getParameter("id");

		System.out.println("ffId=" + ffId);

		JSONObject jsonResult;
	
		jsonResult = new JSONObject(fastFoodAdminBO.getFastFoodInfo(ffId));
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
}
