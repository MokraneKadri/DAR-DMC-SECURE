package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.json.Error;


@WebServlet(urlPatterns="/profil")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 7431335976870937616L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws 
			ServletException, 
			IOException 
	{
		String mode = request.getParameter("mode");
		HttpSession session = request.getSession();
		String loggedinuser = (String) session.getAttribute("login");
		
		switch(mode){
		
		case"view":{
			
			Gson gson = new Gson();
			Map<String,String> userInfos = new HashMap<String,String>();
			User user=null;
			try {
				user = DAOFactory.createUserDao().findUserByUserName(loggedinuser);
				userInfos.put("name", user.getUserName());
				userInfos.put("fname", user.getFirstName());
				userInfos.put("lname", user.getLastName());
				userInfos.put("mail", user.geteMail());
				userInfos.put("university", user.getEtablissement());
				userInfos.put("cursus", user.getCursus());
				request.setAttribute("userinfos", userInfos);
				request.getRequestDispatcher("/jsp/viewprofil.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String profil =gson.toJson(user, User.class);
			response.getWriter().print(new Error(profil));
		}
		
		
		case"update":{
			
			
			
		}
		}
}
	
}