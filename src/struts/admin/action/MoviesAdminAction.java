package struts.admin.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import struts.admin.bean.Movie;
import struts.admin.bo.MoviesAdminBO;
import struts.admin.form.MoviesAdminForm;

public class MoviesAdminAction extends DispatchAction {

	MoviesAdminBO moivesAdminBO = new MoviesAdminBO();
	static int pagesize = 10;

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		MoviesAdminForm moviesAdminForm = (MoviesAdminForm) form;
		
		String page_str = request.getParameter("page");
		
		int page = 1;
		
		if(page_str != null)
		{
			page = Integer.parseInt(page_str);
		}
		
		System.out.println("Page="+page);
		
		
		int total_page = (int) Math.ceil(moivesAdminBO.getNumberRowOfMovie() * 1.0 / pagesize);
		System.out.println("soTrang="+total_page);
		moviesAdminForm.setTotalPage(total_page);
		
		moviesAdminForm.setCurrentPage(page);
	
		moviesAdminForm.setMovieList(moivesAdminBO.getMovieList( 0, pagesize, page));//pagesize - pagenumber
		
		
		return mapping.findForward("toMovieList");

	}

	public ActionForward info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		

		MoviesAdminForm moviesAdminForm = (MoviesAdminForm) form;

		String movieId = request.getParameter("movieId");
		
		if(movieId == null){
			System.out.println("is NULL movieId");
			movieId = moviesAdminForm.getCurrentMovie().getMaPhim();
		}
		
		System.out.println("Action Movie Info:"+movieId);

		moviesAdminForm.setCurrentMovie(moivesAdminBO.getMovieInfo(movieId));

		return mapping.findForward("toInfoMovie");

	}

	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		

		MoviesAdminForm moviesAdminForm = (MoviesAdminForm) form;

		String movieId = request.getParameter("movieId");

		moviesAdminForm.setCurrentMovie(moivesAdminBO.getMovieInfo(movieId));

		return mapping.findForward("toEditMovie");

	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		String movieId = request.getParameter("movieId");

		// to delete
		if(moivesAdminBO.deleteMovie(movieId)){
			System.out.println("Delete ok");
		}
		
		return mapping.findForward("toMovieListAction");

	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		MoviesAdminForm moviesAdminForm = (MoviesAdminForm) form;

		Movie movie = moviesAdminForm.getCurrentMovie();
		
		// to upload file
		FormFile thumbnailFile = moviesAdminForm.getThumbnailFile();
		FormFile bannerFile = moviesAdminForm.getBannerFile();
		
		String filePath = getServlet().getServletContext().getRealPath("/")+ "resources\\movies\\" + movie.getMaPhim();
		System.out.println(filePath);

		uploadImage(filePath,thumbnailFile,"200x300.jpg");
		uploadImage(filePath,bannerFile,"1000x320.jpg");
		
		movie.setBanner("resources/movies/"+movie.getMaPhim()+"/1000x320.jpg");
		movie.setThumbnail("resources/movies/"+movie.getMaPhim()+"/200x300.jpg");
		
		//save data
		System.out.println(movie.getTenPhim());
		
		if(moivesAdminBO.updateMovie(movie)){
			System.out.println("UPDATED");
		}
		else {
			System.out.println("ERROR");
		}
		

		return mapping.findForward("toEditMovie");

	}
	
	public ActionForward reloadlist(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		int type = Integer.parseInt(request.getParameter("type"));
		System.out.println("type="+type);
		
		
		ArrayList<Movie> list = moivesAdminBO.getMovieList(type,0,0);
		JSONArray json = JSONArray.fromObject(list);
		
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(json);	
			out.print(json);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public ActionForward search(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		String content = request.getParameter("content");
		
		ArrayList<Movie> list = moivesAdminBO.searchMovie(content);
		JSONArray json = JSONArray.fromObject(list);
		
		try {
			response.setContentType("application/json;charset=UTF-8");
			PrintWriter out = response.getWriter();
			System.out.println(json);	
			out.print(json);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		MoviesAdminForm moviesAdminForm = (MoviesAdminForm) form;

		Movie movie = moviesAdminForm.getCurrentMovie();
		
		// to upload file
		FormFile thumbnailFile = moviesAdminForm.getThumbnailFile();
		FormFile bannerFile = moviesAdminForm.getBannerFile();
		
		String filePath = getServlet().getServletContext().getRealPath("/")+ "resources\\movies\\" + moivesAdminBO.getNewMovieId();
		System.out.println(filePath);

		uploadImage(filePath,thumbnailFile,"200x300.jpg");
		uploadImage(filePath,bannerFile,"1000x320.jpg");
		
		movie.setBanner("resources/movies/"+moivesAdminBO.getNewMovieId()+"/1000x320.jpg");
		movie.setThumbnail("resources/movies/"+moivesAdminBO.getNewMovieId()+"/200x300.jpg");
		
		//save data
		System.out.println(movie.getTenPhim());
		String return_strString = moivesAdminBO.addMovie(movie);
		if( return_strString != null ){
			System.out.println("SAVED: "+return_strString);
			moviesAdminForm.getCurrentMovie().setMaPhim(return_strString);
		}
		else {
			System.out.println("ERROR");
		}

		return mapping.findForward("toInfoMovieAction");
			
	}
	
	public ActionForward toShow(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		return mapping.findForward("toMovieList");
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
				
		MoviesAdminForm moviesAdminForm = (MoviesAdminForm) form;
		
		moviesAdminForm.reset();
		
		return mapping.findForward("toAddMovie");
	}
	
	

	private boolean createFolder(String path) {
		
		File targetFile = new File(path);
		if (!targetFile.exists() && !targetFile.mkdirs()) {
			return false;
		}
		return true;
	}
	
	private void uploadImage(String path, FormFile file, String fileName){
		
		System.out.println(path);

		if ((file.getFileSize() != 0) && (!("").equals(file.getFileName()))) {

			if (createFolder(path)) {
				System.out.println("new");
			} else {
				System.out.println("exist folder");
			}
			File image = new File(path,fileName);

			try {
				FileOutputStream fos = new FileOutputStream(image);
				fos.write(file.getFileData());
				fos.flush();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


		}
	}

}
