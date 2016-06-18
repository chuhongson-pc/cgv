package struts.admin.action;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import struts.admin.bean.News;
import struts.admin.bo.NewsAdminBO;
import struts.admin.form.NewsAdminForm;

public class NewsAdminAction extends DispatchAction {
	
	
	NewsAdminBO newsAdminBO = new NewsAdminBO();
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		

		JSONObject jsonResult = newsAdminBO.getNewList();

		response.setContentType("application/json;charset=UTF-8");
		PrintWriter out;

		try {
			out = response.getWriter();
			System.out.println(jsonResult);
			out.print(jsonResult);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		return mapping.findForward("toNewsList");
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form, 
			HttpServletRequest request, HttpServletResponse response){
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		NewsAdminForm newsAdminForm = (NewsAdminForm) form;
		
		newsAdminForm.reset();
		
		return mapping.findForward("toAddNews");
	}
	
	
	public ActionForward info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		String newsId = request.getParameter("id");
		
		NewsAdminForm newsAdminForm = (NewsAdminForm) form;
		
		newsAdminForm.setCurrentNews(newsAdminBO.getNewsInfo(newsId));

		return mapping.findForward("toEditNews");
	}
	
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		System.out.println("update news action");

		NewsAdminForm newsAdminForm = (NewsAdminForm) form;

		News news = newsAdminForm.getCurrentNews();
		
		// to upload file
		FormFile thumbnailFile = newsAdminForm.getThumbnailFile();
		
		System.out.println("File size:"+thumbnailFile.getFileSize());

		String filePath = getServlet().getServletContext().getRealPath("/")+ "resources\\news\\" + news.getMaTT();
		System.out.println(filePath);

		uploadImage(filePath,thumbnailFile,"200x300.jpg");
		
		news.setThumbnail("resources/news/"+news.getMaTT()+"/200x300.jpg");
		

		if(newsAdminBO.updateNews(news)){
			System.out.println("update ok");			
			ActionMessages  messages = new ActionMessages();			
			messages.add("insertDone", new ActionMessage("insert.done"));			
			saveMessages(request, messages);
		}
		else {
			System.out.println("ERROR ADD");
			//update error
			ActionErrors errors=new ActionErrors();
			errors.add("insertError", new ActionMessage("insert.error"));			
		    saveErrors(request, errors);
		}
		

		return mapping.findForward("toEditNews");
	}
	
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		System.out.println("add news action");

		NewsAdminForm newsAdminForm = (NewsAdminForm) form;

		News news = newsAdminForm.getCurrentNews();
		
		// to upload file
		FormFile thumbnailFile = newsAdminForm.getThumbnailFile();
		
		System.out.println("File size:"+thumbnailFile.getFileSize());
		
		String newId = newsAdminBO.getNextId();
		
		news.setMaTT(newId);

		String filePath = getServlet().getServletContext().getRealPath("/")+ "resources\\news\\" + newId;
		System.out.println(filePath);

		uploadImage(filePath,thumbnailFile,"200x300.jpg");
		
		news.setThumbnail("resources/news/"+newId+"/200x300.jpg");
		

		if(newsAdminBO.addNews(news)){
			System.out.println("update ok");			
			ActionMessages  messages = new ActionMessages();			
			messages.add("insertDone", new ActionMessage("insert.done"));			
			saveMessages(request, messages);
		}
		else {
			System.out.println("ERROR ADD");
			//update error
			ActionErrors errors=new ActionErrors();
			errors.add("insertError", new ActionMessage("insert.error"));			
		    saveErrors(request, errors);
		}
		

		return mapping.findForward("toEditNews");
	}
	
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		

		String newsId = request.getParameter("id");

		// to delete
		if(newsAdminBO.deleteNews(newsId)){
			System.out.println("Delete ok");
		}
		
		return mapping.findForward("toShowAction");

	}
	

	public void sendJSON(HttpServletResponse response, JSONObject jsonResult) {
		PrintWriter out;
		response.setContentType("application/json;charset=UTF-8");

		try {
			out = response.getWriter();
			System.out.println(jsonResult);
			out.print(jsonResult);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
