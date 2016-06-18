package struts.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.json.JSONArray;
import org.json.JSONObject;

import struts.admin.bean.News;
import struts.bo.NewsBO;
import struts.form.NewsForm;

public class NewsAction extends DispatchAction {
	
	NewsBO newsBO = new NewsBO();

	public ActionForward toShow(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		return mapping.findForward("toAticlesList");
	}

	public ActionForward list(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		int type = Integer.parseInt(request.getParameter("type"));
		
		ArrayList<News> list = newsBO.getNewsList(type);

		JSONArray ja = new JSONArray(list);
		
		PrintWriter out;
		response.setContentType("application/json;charset=UTF-8");

		try {
			out = response.getWriter();
			System.out.println(ja);
			out.print(ja);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ActionForward info(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		String articleId = request.getParameter("id");
		
		NewsForm newsForm = (NewsForm) form;
		
		newsForm.setCurrentNew(newsBO.getNewsInfo(articleId));

		return mapping.findForward("toArticleInfo");
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
	
	
}
