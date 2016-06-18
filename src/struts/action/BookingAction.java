package struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import struts.bo.BookingBO;
import struts.form.BookingForm;

public class BookingAction extends DispatchAction {

	BookingBO bookingBO = new BookingBO();

	public ActionForward step1(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}

		BookingForm bookingForm = (BookingForm) form;
		String sessionId = request.getParameter("session");
		
		String username = (String) request.getSession().getAttribute("user");
		
		// checking session

		// get Session info
		if (sessionId != null) {

			bookingForm.reset(mapping, request);

			bookingForm.setSessionOfBooking(bookingBO.getSession(sessionId));
			
			bookingForm.setSoDuTK(bookingBO.getSoDuTK(username));

		}

		return mapping.findForward("toBookingStep1");

	}

	public ActionForward step2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}

		BookingForm bookingForm = (BookingForm) form;

		String ticketTypeSelected = bookingForm.getTicketTypeSelected();

		System.out.println("selected Type Ticket " + ticketTypeSelected);

		// get seatsBooked
		String sessionId = bookingForm.getSessionOfBooking().getMaSuat();

		System.out.println("sessionId=" + sessionId);

		bookingForm.setSeatsBooked(bookingBO.getSeatsBooked(sessionId));

		return mapping.findForward("toBookingStep2");

	}

	public ActionForward step3(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		//
		BookingForm bookingForm = (BookingForm) form;

		bookingForm.setFastFoodList(bookingBO.getFastFoodList());

		System.out.println("fastFood Selected:" + bookingForm.getFastFoodTypeSelected());

		return mapping.findForward("toBookingStep3");

	}

	public ActionForward step4(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}

		// kiểm tra và lưu CSDL
		BookingForm bookingForm = (BookingForm) form;

		String sessionId = bookingForm.getSessionOfBooking().getMaSuat();
		String fastFoodTypeSelected = bookingForm.getFastFoodTypeSelected().trim();
		String ticketTypeSelected = bookingForm.getTicketTypeSelected().trim();
		String seatsSelected = bookingForm.getSeatsYour().trim();

		System.out.println("sessionID=" + sessionId);
		System.out.println("fastFoodTypeSelected=" + fastFoodTypeSelected);
		System.out.println("ticketTypeSelected=" + ticketTypeSelected);
		System.out.println("seatsSelected=" + seatsSelected);

		bookingForm.setFastFoodList(bookingBO.getFastFoodList());

		// kiểm tra hợp lệ
		// kiểm tra danh sách ghế chọn có phù hợp với số ghê VIP - THƯỜNG cho
		// phép.
//		if (bookingBO.checkVaildType(sessionId, ticketTypeSelected,
//				fastFoodTypeSelected, seatsSelected)) {
//
//			System.out.println("OK");
//
//			// System.out.println(bookingForm.getFastFoodList().get(0).getTenFF());
//
//		} else {
//			System.out.println("ERROR");
//		}

		return mapping.findForward("toBookingStep4");
	}

	public ActionForward step5(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		if (request.getSession().getAttribute("user") == null) {
			return mapping.findForward("login");
		}
		
		// kiểm tra và lưu CSDL
		BookingForm bookingForm = (BookingForm) form;

		String sessionId = bookingForm.getSessionOfBooking().getMaSuat();
		String fastFoodTypeSelected = bookingForm.getFastFoodTypeSelected().trim();
		String ticketTypeSelected = bookingForm.getTicketTypeSelected().trim();
		String seatsSelected = bookingForm.getSeatsYour().trim();
		String username = (String) request.getSession().getAttribute("user");

		System.out.println("sessionID=" + sessionId);
		System.out.println("fastFoodTypeSelected=" + fastFoodTypeSelected);
		System.out.println("ticketTypeSelected=" + ticketTypeSelected);
		System.out.println("seatsSelected=" + seatsSelected);

		bookingForm.setFastFoodList(bookingBO.getFastFoodList());
		


		// kiểm tra hợp lệ
		// kiểm tra danh sách ghế chọn có phù hợp với số ghê VIP - THƯỜNG cho phép.
		if (bookingBO.checkVaildType(sessionId, ticketTypeSelected, fastFoodTypeSelected, seatsSelected)) {

			System.out.println("OK");
			String transactionCode = bookingBO.finishTransaction(username, sessionId, ticketTypeSelected, fastFoodTypeSelected, seatsSelected);
			if(!transactionCode.equals("-1")){
				
				System.out.println("Saved DB !");
				System.out.println("Transaction Code:"+transactionCode);
				bookingForm.setTransactionCode(transactionCode);
			}
			else {
				System.out.println("ERROR finishTransaction");
				
				return mapping.findForward("toError");
				
			}
			

		} else {
			System.out.println("ERROR");
			return mapping.findForward("toError");
		}
		
		System.out.println("tongCong="+bookingForm.getTongCong());

		return mapping.findForward("toSuccess");
	}

	public ActionForward cancel(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		
		BookingForm bookingForm = (BookingForm) form;
		bookingForm.reset(mapping, request);

		return mapping.findForward("toCancel");
	}
}
