package fr.upmc.dar2.servlets;

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

import fr.upmc.dar2.dao.DAOFactory;
import fr.upmc.dar2.entities.User;
import fr.upmc.dar2.json.Error;


@WebServlet(urlPatterns="/profil")
public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = 7431335976870937616L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws 
			ServletException, 
			IOException 
	{
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		User user=null;
		String username = request.getParameter("user");
		HttpSession session = request.getSession();
		String loggedinuser = (String) session.getAttribute("login");
		System.out.println("request for:"+username);
		//String param;
		if(loggedinuser!=username){
			try {
				user = DAOFactory.createUserDao().findUserByUserName(username);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		else{
			try {
				user = DAOFactory.createUserDao().findUserByUserName(loggedinuser);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
		
			Gson gson = new Gson();
			Map<String,String> userInfos = new HashMap<String,String>();
			
			try {
				
				userInfos.put("name", user.getUserName());
				userInfos.put("fname", user.getFirstName());
				userInfos.put("lname", user.getLastName());
				userInfos.put("mail", user.geteMail());
				userInfos.put("university", user.getEtablissement());
				userInfos.put("cursus", user.getCursus());
				userInfos.put("adresse", user.getStreet()+" "+user.getZip()+" "+user.getCity());
				request.setAttribute("userinfos", userInfos);
				request.getRequestDispatcher("/jsp/viewprofil.jsp").forward(request, response);
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		
}
	
}