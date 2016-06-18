package struts.admin.action;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;

import struts.admin.bean.Poster;
import struts.admin.bo.PosterAdminBO;
import struts.admin.form.PosterAdminForm;


public class PosterAdminAction extends DispatchAction{
	
	PosterAdminBO posterAdminBO = new PosterAdminBO();
	
	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		PosterAdminForm posterAdminForm = (PosterAdminForm) form;
		
		posterAdminForm.setPosterList(posterAdminBO.getPosterList());
		
		return mapping.findForward("toPosterList");
		
	}
	
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		String posterId = request.getParameter("id");
		
		
		if(posterAdminBO.deletePoster(posterId)){
			System.out.println("Delete done");
		}
		
		
		return mapping.findForward("toListAction");
		
	}
	
	public ActionForward edit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		PosterAdminForm posterAdminForm = (PosterAdminForm) form;
		
		Poster poster = posterAdminForm.getCurrentPoster();
		
		// to upload file
		FormFile posterFile = posterAdminForm.getPosterFile();
		
		
		String filePath = getServlet().getServletContext().getRealPath("/")+ "resources\\images\\slides\\";
		System.out.println(filePath);

		uploadImage(filePath,posterFile,poster.getMaPoster()+".jpg");
		
		poster.setPoster("resources/images/slides/"+poster.getMaPoster()+".jpg");
		
		
		if( posterAdminBO.updatePoster(poster) ){
			System.out.println("updated");
		}
		else {
			System.out.println("error update");
		}
		
		
		return mapping.findForward("toEditPoster");
		
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		PosterAdminForm posterAdminForm = (PosterAdminForm) form;
		
		Poster poster = posterAdminForm.getCurrentPoster();
		
		// to upload file
		FormFile posterFile = posterAdminForm.getPosterFile();
		
		
		String filePath = getServlet().getServletContext().getRealPath("/")+ "resources\\images\\slides\\";
		System.out.println(filePath);

		uploadImage(filePath,posterFile,posterAdminBO.getNewPosterId()+".jpg");
		
		poster.setPoster("resources/images/slides/"+posterAdminBO.getNewPosterId()+".jpg");
		
		
		String posterIdNew = posterAdminBO.addPoster(poster);
		
		if( posterIdNew != null ){
			System.out.println("inserted");
			//posterAdminForm.getCurrentPoster().setMaPoster(posterIdNew);
		}
		else {
			System.out.println("error insert");
		}
		
		
		return mapping.findForward("toListAction");
		
	}
	
	public ActionForward toAdd(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		PosterAdminForm posterAdminForm = (PosterAdminForm) form;
		
		posterAdminForm.reset();
		
		return mapping.findForward("toAddPoster");
		
	}
	
	public ActionForward toEdit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		if (request.getSession().getAttribute("useradmin") == null) {
			return mapping.findForward("toAdminCP");
		}
		
		String role = request.getSession().getAttribute("role") + "";
        if(!role.equals("3")) return mapping.findForward("toNotValidRole");
		
		
		
		String posterId = request.getParameter("id");
		
		PosterAdminForm posterAdminForm = (PosterAdminForm) form;
		
		posterAdminForm.setCurrentPoster(posterAdminBO.getPosterInfo(posterId));
		
		return mapping.findForward("toEditPoster");
		
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
