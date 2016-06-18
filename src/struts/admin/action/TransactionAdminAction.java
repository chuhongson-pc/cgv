package struts.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONException;
import org.json.JSONObject;

import struts.admin.bo.TransactionAdminBO;

public class TransactionAdminAction extends DispatchAction {
	
	TransactionAdminBO transactionAdminBO = new TransactionAdminBO();

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
      
		String[] columnNames = { "MaGD", "Username", "TenPhim", "ThoiGianGD"};
		
		HashMap<String,String> info = new HashMap<String,String>();

		JSONObject jsonResult = new JSONObject();
		
		int listDisplayAmount = 10;
		int start = 0;
		int column = 0;
		String dir = "asc";
		
		String pageNo = request.getParameter("iDisplayStart");
		String pageSize = request.getParameter("iDisplayLength");
		String colIndex = request.getParameter("iSortCol_0");
		String sortDirection = request.getParameter("sSortDir_0");

		if (pageNo != null) {
			start = Integer.parseInt(pageNo);
			if (start < 0) {
				start = 0;
			}
		}

		if (pageSize != null) {
			listDisplayAmount = Integer.parseInt(pageSize);
			if (listDisplayAmount < 10 || listDisplayAmount > 50) {
				listDisplayAmount = 10;
			}
		}

		if (colIndex != null) {
			column = Integer.parseInt(colIndex);
			if (column < 0 || column > 3)
				column = 0;
		}

		if (sortDirection != null) {
			if (!sortDirection.equals("asc"))
				dir = "desc";
		}

		
		
	    info.put("kichThuoc",listDisplayAmount+"");	    
	    info.put("timKiem",request.getParameter("sSearch"));
	    info.put("maGD",request.getParameter("sSearch_0"));
	    info.put("username",request.getParameter("sSearch_1"));
	    info.put("phim",request.getParameter("sSearch_2"));	    
	    info.put("thoiGianGD",request.getParameter("sSearch_3"));
	    //not search for total

	    
	    String colName = columnNames[column];
	    info.put("tenCot",colName);
	    info.put("DIRECTION",dir);
	    info.put("INITIAL",start+"");
	    
	    //
	    
	    jsonResult = transactionAdminBO.getTransactionList(info, role);
	    
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
	
	public ActionForward info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		

		String transactionId = request.getParameter("id");

		System.out.println("transactionId=" + transactionId);
		JSONObject result = transactionAdminBO.getTicketInfo(transactionId);
		try {
			result.put("transactionId", transactionId);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
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
		
		
		String transactionId = request.getParameter("id");

		JSONObject result = new JSONObject();
		try {
			if (transactionAdminBO.deleteTransaction(transactionId)) {
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
	
	

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}

		return mapping.findForward("toTransactionList");
	}
	
	public void sendJSON(HttpServletResponse response, JSONObject jsonResult) {
		PrintWriter out;
		response.setContentType("application/json;charset=UTF-8");

		try {
			out = response.getWriter();
			System.out.println("-->"+jsonResult);
			out.print(jsonResult);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
