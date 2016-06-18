package struts.admin.bo;

import java.util.ArrayList;

import struts.admin.bean.Poster;
import struts.admin.dao.PosterAdminDAO;

public class PosterAdminBO {

	PosterAdminDAO posterAdminDAO = new PosterAdminDAO();

	public ArrayList<Poster> getPosterList() {
		// TODO Auto-generated method stub
		return posterAdminDAO.getPosterList();
	}

	public boolean deletePoster(String posterId) {
		// TODO Auto-generated method stub
		return posterAdminDAO.deletePoster(posterId);
	}

	public Poster getPosterInfo(String posterId) {
		// TODO Auto-generated method stub
		return posterAdminDAO.getPosterInfo(posterId);
	}

	public boolean updatePoster(Poster poster) {
		// TODO Auto-generated method stub
		return posterAdminDAO.updatePoster(poster);
	}

	public String getNewPosterId() {
		// TODO Auto-generated method stub
		return posterAdminDAO.getNewPosterId();
	}

	public String addPoster(Poster poster) {
		// TODO Auto-generated method stub
		return posterAdminDAO.addPoster(poster);
	}
	
	
	
}
