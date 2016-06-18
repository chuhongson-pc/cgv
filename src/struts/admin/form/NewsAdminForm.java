package struts.admin.form;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import struts.admin.bean.News;

public class NewsAdminForm extends ActionForm {

	private News currentNews = new News();
	
	private FormFile thumbnailFile;

	public News getCurrentNews() {
		return currentNews;
	}

	public void setCurrentNews(News currentNews) {
		this.currentNews = currentNews;
	}

	public FormFile getThumbnailFile() {
		return thumbnailFile;
	}

	public void setThumbnailFile(FormFile thumbnailFile) {
		this.thumbnailFile = thumbnailFile;
	}

	public void reset() {
		currentNews = new News();
	}


}
