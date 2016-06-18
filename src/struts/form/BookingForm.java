package struts.form;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionMapping;

import struts.bean.FastFood;
import struts.bean.Session;

public class BookingForm extends ActionForm {

	private static final long serialVersionUID = 1L;
	private Session sessionOfBooking;
	private String ticketTypeSelected;
	private String fastFoodTypeSelected;
	private String seatsBooked;
	private ArrayList<FastFood> fastFoodList = new ArrayList<FastFood>();
	
	private String seatsYour;
	private int soGheThuongDuocChon;
	private int soGheVipDuocChon;
	private int tongCong;	
	private String transactionCode;
	
	private int soDuTK;
	
	public int getSoDuTK() {
		return soDuTK;
	}

	public void setSoDuTK(int soDuTK) {
		this.soDuTK = soDuTK;
	}

	public String getTransactionCode() {
		return transactionCode;
	}

	public void setTransactionCode(String transactionCode) {
		this.transactionCode = transactionCode;
	}

	public ArrayList<FastFood> getFastFoodList() {
		return fastFoodList;
	}

	public void setFastFoodList(ArrayList<FastFood> fastFoodList) {
		this.fastFoodList = fastFoodList;
	}

	public String getSeatsYour() {
		return seatsYour;
	}

	public void setSeatsYour(String seatsYour) {
		this.seatsYour = seatsYour;
	}

	public int getTongCong() {
		return tongCong;
	}

	public void setTongCong(int tongCong) {
		this.tongCong = tongCong;
	}

	public int getSoGheThuongDuocChon() {
		return soGheThuongDuocChon;
	}

	public void setSoGheThuongDuocChon(int soGheThuongDuocChon) {
		this.soGheThuongDuocChon = soGheThuongDuocChon;
	}

	public int getSoGheVipDuocChon() {
		return soGheVipDuocChon;
	}

	public void setSoGheVipDuocChon(int soGheVipDuocChon) {
		this.soGheVipDuocChon = soGheVipDuocChon;
	}

	public Session getSessionOfBooking() {
		return sessionOfBooking;
	}

	public void setSessionOfBooking(Session sessionOfBooking) {
		this.sessionOfBooking = sessionOfBooking;
	}

	public String getTicketTypeSelected() {
		return ticketTypeSelected;
	}

	public void setTicketTypeSelected(String ticketTypeSelected) {
		this.ticketTypeSelected = ticketTypeSelected;
	}

	public String getFastFoodTypeSelected() {
		return fastFoodTypeSelected;
	}

	public void setFastFoodTypeSelected(String fastFoodTypeSelected) {
		this.fastFoodTypeSelected = fastFoodTypeSelected;
	}

	public String getSeatsBooked() {
		return seatsBooked;
	}

	public void setSeatsBooked(String seatsBooked) {
		this.seatsBooked = seatsBooked;
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {

		ticketTypeSelected="";
		fastFoodTypeSelected="";
		seatsBooked="";
		fastFoodList = new ArrayList<FastFood>();
		
		seatsYour="";
		soGheThuongDuocChon=0;
		soGheVipDuocChon=0;
		tongCong=0;
		
		transactionCode="";
	}


}
