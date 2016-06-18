package struts.admin.bean;

import java.util.ArrayList;

public class AvailableSessionByRoom {
	private Room room = new Room();
	private Movie movie_select = new Movie();
	
	
	private ArrayList<SessionTime> sessionTimeList = new ArrayList<SessionTime>();
	
	

	public Movie getMovie_select() {
		return movie_select;
	}

	public void setMovie_select(Movie movie_select) {
		this.movie_select = movie_select;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public ArrayList<SessionTime> getSessionTimeList() {
		return sessionTimeList;
	}

	public void setSessionTimeList(ArrayList<SessionTime> sessionTimeList) {
		this.sessionTimeList = sessionTimeList;
	}}
