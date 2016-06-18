package struts.admin.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;

import struts.admin.bean.Room;
import struts.admin.bo.RoomManagementBO;
import struts.admin.form.RoomManagementForm;

public class RoomManagementAction extends DispatchAction {
	
	RoomManagementBO roomManagementBO = new RoomManagementBO();

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
        
		
		RoomManagementForm roomManagementForm = (RoomManagementForm) form;
		
		roomManagementForm.setRoomList(roomManagementBO.getRoomList());
		
		return mapping.findForward("toRoomList");
		
	}
	
	public ActionForward view(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		String roomId = request.getParameter("room");
		
		RoomManagementForm roomManagementForm = (RoomManagementForm) form;
		
		Room r = roomManagementBO.getRoomById(roomId);
		roomManagementForm.setMaPhong(r.getMaPhong());
		roomManagementForm.setTenPhong(r.getTenPhong());
		roomManagementForm.setLoaiPhong(r.getLoaiPhong());
		roomManagementForm.setSoDoGhe(r.getSoDoGhe());
		roomManagementForm.setSoGhe(r.getSoGhe());
		roomManagementForm.setTrangThai(r.getTrangThai());

		return mapping.findForward("toEditRoom");
		
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		RoomManagementForm roomManagementForm = (RoomManagementForm) form;
		
		String maPhong = roomManagementForm.getMaPhong();
		String tenPhong = roomManagementForm.getTenPhong();
		String loaiPhong = roomManagementForm.getLoaiPhong();
		String soDoGhe = roomManagementForm.getSoDoGhe();
		int soGhe = roomManagementForm.getSoGhe();
		int trangThai = roomManagementForm.getTrangThai();
		
		if(roomManagementBO.updateRoom(maPhong, tenPhong, loaiPhong, soDoGhe, soGhe, trangThai))
		{
			System.out.println("update ok");			
			ActionMessages  messages = new ActionMessages();			
			messages.add("updateRoomDone", new ActionMessage("update.room.done"));			
			saveMessages(request, messages);
			
		}
		else{
			//update error
			ActionErrors errors=new ActionErrors();
			errors.add("updateRoomError", new ActionMessage("update.room.error"));			
		    saveErrors(request, errors);
			
		}
		
		return mapping.findForward("toEditRoom");
		
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		
		RoomManagementForm roomManagementForm = (RoomManagementForm) form;	
		
		String tenPhong = roomManagementForm.getTenPhong();
		String loaiPhong = roomManagementForm.getLoaiPhong();
		String soDoGhe = roomManagementForm.getSoDoGhe();
		int soGhe = roomManagementForm.getSoGhe();
		int trangThai = roomManagementForm.getTrangThai();
		
		if(roomManagementBO.addRoom(tenPhong, loaiPhong, soDoGhe, soGhe, trangThai))
		{
			System.out.println("add room done");			
			ActionMessages  messages = new ActionMessages();			
			messages.add("addRoomDone", new ActionMessage("add.room.done"));			
			saveMessages(request, messages);
			
		}
		else{
			//update error
			ActionErrors errors=new ActionErrors();
			errors.add("addRoomError", new ActionMessage("add.room.error"));			
		    saveErrors(request, errors);
			
		}
		
		return mapping.findForward("toAddRoom");
		
	}
	
	public ActionForward toadd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		RoomManagementForm roomManagementForm = (RoomManagementForm) form;	
		roomManagementForm.reset(mapping, request);
		return mapping.findForward("toAddRoom");
		
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		
		RoomManagementForm roomManagementForm = (RoomManagementForm) form;	
		String maPhong = roomManagementForm.getMaPhong();
		
		
		if(roomManagementBO.deleteRoom(maPhong))
		{
			
			System.out.println("Delete room done");
		}
		
		
		return mapping.findForward("toRoomListAction");
		
	}

	
}
