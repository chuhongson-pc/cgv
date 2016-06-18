package struts.bo;

import java.util.ArrayList;

import struts.admin.bean.News;
import struts.dao.NewsDAO;

public class NewsBO {
	NewsDAO newsDAO = new NewsDAO();

	public ArrayList<News> getNewsList(int type) {
		// TODO Auto-generated method stub
		return newsDAO.getNewsList(type);
	}

	public struts.bean.News getNewsInfo(String articleId) {
		// TODO Auto-generated method stub
		return newsDAO.getNewsInfo(articleId);
	}
}
