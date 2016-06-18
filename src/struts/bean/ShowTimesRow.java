package struts.bean;

import java.util.ArrayList;

public class ShowTimesRow 
{
	private Movie movieOfRow;
	private Room roomOfRow;
	private ArrayList<Session> sessionList = new ArrayList<Session>();

	public Movie getMovieOfRow() {
		return movieOfRow;
	}

	public void setMovieOfRow(Movie movieOfRow) {
		this.movieOfRow = movieOfRow;
	}

	public Room getRoomOfRow() {
		return roomOfRow;
	}

	public void setRoomOfRow(Room roomOfRow) {
		this.roomOfRow = roomOfRow;
	}


	public ArrayList<Session> getSessionList() {
		return sessionList;
	}

	public void setSessionList(ArrayList<Session> sessionList) {
		this.sessionList = sessionList;
	}
	
	
}
