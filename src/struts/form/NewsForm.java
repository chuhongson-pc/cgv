package struts.form;

import org.apache.struts.action.ActionForm;

import struts.bean.News;

public class NewsForm extends ActionForm {

	private News currentNew = new News();

	public News getCurrentNew() {
		return currentNew;
	}

	public void setCurrentNew(News currentNew) {
		this.currentNew = currentNew;
	}
	
}
