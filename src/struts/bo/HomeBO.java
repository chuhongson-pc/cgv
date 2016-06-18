package struts.bo;

import java.util.ArrayList;

import org.json.JSONObject;

import struts.bean.Customer;
import struts.bean.Movie;
import struts.bean.Poster;
import struts.dao.HomeDAO;

public class HomeBO {
		
	static HomeDAO homeDAO = new HomeDAO();

	public ArrayList<Movie> getMoviesList(int i) {
		// TODO Auto-generated method stub
		return homeDAO.getMovies(i);
	}
	
	public boolean checkLogin(String username, String password) {
		// TODO Auto-generated method stub
		return homeDAO.checkLogin(username, password);
	}

	public boolean checkExistUser(String username) {
		// TODO Auto-generated method stub
		return homeDAO.checkExistUser(username);
	}

	public boolean addNewUser(String fullname, String dob, String gender,
			String address, String phonenumber, String email, String cmnd,
			String username, String password) {
		// TODO Auto-generated method stub
		return homeDAO.addNewUser(fullname, dob, gender, address, phonenumber, email, cmnd, username, password);
	}

	public ArrayList<Movie> getTopMoives() {
		// TODO Auto-generated method stub
		return homeDAO.getTopMovies();
	}

	public boolean updatePassword(String username, String password) {
		// TODO Auto-generated method stub
		return homeDAO.updatePassword(username, password);
	}

	public Customer getUserInfo(String username) {
		// TODO Auto-generated method stub
		return homeDAO.getUserInfo(username);
	}

	public boolean updateProfile(String username, String fullname, String cmnd,
			String dob, String gender, String email, String phonenumber,
			String address) {
		// TODO Auto-generated method stub
		return homeDAO.updateProfile(username, fullname, cmnd, dob, gender, email, phonenumber, address);
	}

	public JSONObject getOrderList(String username) {
		// TODO Auto-generated method stub
		return homeDAO.getOrderList(username);
	}

	public ArrayList<Poster> getPosterList() {
		// TODO Auto-generated method stub
		return homeDAO.getPosterList();
	}

}
