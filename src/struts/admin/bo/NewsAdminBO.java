package struts.admin.bo;

import org.json.JSONObject;

import struts.admin.bean.News;
import struts.admin.dao.NewsAdminDAO;

public class NewsAdminBO {

	NewsAdminDAO newsAdminDAO = new NewsAdminDAO();

	public JSONObject getNewList() {
		// TODO Auto-generated method stub
		return newsAdminDAO.getNewList();
	}

	public News getNewsInfo(String newsId) {
		// TODO Auto-generated method stub
		return newsAdminDAO.getNewsInfo(newsId);
	}

	public boolean updateNews(News news) {
		// TODO Auto-generated method stub
		return newsAdminDAO.updateNews(news);
	}

	public String getNextId() {
		// TODO Auto-generated method stub
		return newsAdminDAO.getNextId();
	}

	public boolean addNews(News news) {
		// TODO Auto-generated method stub
		return newsAdminDAO.addNews(news);
	}

	public boolean deleteNews(String newsId) {
		// TODO Auto-generated method stub
		return newsAdminDAO.deleteNews(newsId);
	}
	
}
