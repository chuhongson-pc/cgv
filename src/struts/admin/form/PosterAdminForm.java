package struts.admin.form;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;

import struts.admin.bean.Poster;

public class PosterAdminForm extends ActionForm {

	private ArrayList<Poster> posterList = new ArrayList<Poster>();

	private Poster currentPoster = new Poster();
	
	private FormFile posterFile;
	
	

	public FormFile getPosterFile() {
		return posterFile;
	}

	public void setPosterFile(FormFile posterFile) {
		this.posterFile = posterFile;
	}

	public ArrayList<Poster> getPosterList() {
		return posterList;
	}

	public void setPosterList(ArrayList<Poster> posterList) {
		this.posterList = posterList;
	}

	public Poster getCurrentPoster() {
		return currentPoster;
	}

	public void setCurrentPoster(Poster currentPoster) {
		this.currentPoster = currentPoster;
	}

	public void reset() {
				
		currentPoster = new Poster();

	}

}
