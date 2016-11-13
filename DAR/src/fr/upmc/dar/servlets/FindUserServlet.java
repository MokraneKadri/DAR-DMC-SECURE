package fr.upmc.dar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;

import fr.upmc.dar.dao.UserDao;

@WebServlet(urlPatterns = "/finduser" )
public class FindUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao UserDao=new UserDao();
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		try{
			response.setContentType("text/html");
			String search=request.getParameter("search");
			String value=request.getParameter("value");
			JSONObject js=new JSONObject();
			if(value==null || search==null)
				js.put("error", "Un des champs est vide.");
			else{
				System.out.println("request : "+search+ " : "+value);
				JSONObject rep=UserDao.getUsersJSONProfileWhere(search,value);
				js.put("result", rep);
				js.put("idv", request.getSession().getAttribute("idlogin"));
				js.put("response", 1);
				//System.out.println(rep);
			}
			response.getWriter().print(js);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
}
