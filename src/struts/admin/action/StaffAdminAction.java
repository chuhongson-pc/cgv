package struts.admin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONObject;

import struts.admin.bo.StaffAdminBO;
import struts.admin.form.StaffAdminForm;

public class StaffAdminAction extends DispatchAction {

	StaffAdminBO staffAdminBO = new StaffAdminBO();

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		String[] columnNames = { "Username", "HoTen", "SoDienThoai", "Email", "CMND", "TrangThai"};
		
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
			if (column < 0 || column > 5)
				column = 0;
		}

		if (sortDirection != null) {
			if (!sortDirection.equals("asc"))
				dir = "desc";
		}

		
		
	    info.put("RECORD_SIZE",listDisplayAmount+"");	    
	    info.put("GLOBAL_SEARCH",request.getParameter("sSearch"));
	    info.put("USERNAME_SEARCH",request.getParameter("sSearch_0"));
	    info.put("FULLNAME_SEARCH",request.getParameter("sSearch_1"));
	    info.put("PHONE_SEARCH",request.getParameter("sSearch_2"));
	    info.put("EMAIL_SEARCH",request.getParameter("sSearch_3"));
	    info.put("CMND_SEARCH",request.getParameter("sSearch_4"));
//	    info.put("PHONE_SEARCH",request.getParameter("sSearch_5"));
//	    info.put("EMAIL_SEARCH",request.getParameter("sSearch_6"));
//	    info.put("CMND_SEARCH",request.getParameter("sSearch_7"));
	    
	    String colName = columnNames[column];
	    info.put("COLUMN_NAME",colName);
	    info.put("DIRECTION",dir);
	    info.put("INITIAL",start+"");
	    
	    //
	    
	    jsonResult = staffAdminBO.getStaffDetails(info);
	    
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
	
	

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		return mapping.findForward("toStaffList");
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		StaffAdminForm staffAdminForm = (StaffAdminForm) form;
		
		staffAdminForm.reset();

		return mapping.findForward("toAddStaff");
	}
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		
		StaffAdminForm staffAdminForm = (StaffAdminForm) form;
		
		//kiểm tra tồn tại
		if(staffAdminBO.checkExistUser(staffAdminForm.getCurrentStaff().getUsername())){
			//đã tồn tại
			ActionErrors errors=new ActionErrors();
			errors.add("usernameMessageError", new ActionMessage("error.existusername"));			
		    saveErrors(request, errors);
		}
		
		else 
		{
			if(staffAdminBO.addStaff(staffAdminForm.getCurrentStaff())){
				System.out.println("update ok");			
				ActionMessages  messages = new ActionMessages();			
				messages.add("insertDone", new ActionMessage("insert.done"));			
				saveMessages(request, messages);
			}
			else {
				System.out.println("ERROR ADD");
				//update error
				ActionErrors errors=new ActionErrors();
				errors.add("insertError", new ActionMessage("insert.error"));			
			    saveErrors(request, errors);
			}
		}
		

		return mapping.findForward("toAddStaff");
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		
		String username = request.getParameter("user");
		
		if(staffAdminBO.deleteStaff(username))
		{
			System.out.println("Deleted");
		}

		return mapping.findForward("toShowAction");
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
			StaffAdminForm staffAdminForm = (StaffAdminForm) form;
		
			if(staffAdminBO.updateStaff(staffAdminForm.getCurrentStaff())){
				System.out.println("update ok");			
				ActionMessages  messages = new ActionMessages();			
				messages.add("updateDone", new ActionMessage("update.done"));			
				saveMessages(request, messages);
			}
			else {
				System.out.println("ERROR UPDATE");
				//update error
				ActionErrors errors=new ActionErrors();
				errors.add("updateError", new ActionMessage("update.error"));			
			    saveErrors(request, errors);
			}

		return mapping.findForward("toEditStaff");
	}
	
	public ActionForward info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		String username = request.getParameter("user");
		
		StaffAdminForm staffAdminForm = (StaffAdminForm) form;
		
		staffAdminForm.setCurrentStaff(staffAdminBO.getStaffInfo(username));

		return mapping.findForward("toEditStaff");
	}
	
	

	//
	public void toSend(HttpServletResponse response, Object list) {
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
}
