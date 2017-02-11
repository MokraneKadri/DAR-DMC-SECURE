package fr.upmc.dar2.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import fr.upmc.dar2.dao.DAOFactory;
import fr.upmc.dar2.dao.interfaces.IUserDao;
import fr.upmc.dar2.entities.User;
import fr.upmc.dar2.enums.UriMapping;
import fr.upmc.dar2.tools.CookiesHelper;
import fr.upmc.dar2.tools.PasswordEncryptor;
import fr.upmc.dar2.tools.SignInValidator;
import fr.upmc.dar2.tools.UUidGenerator;
import net.sf.json.*;

import javax.servlet.http.Cookie;

@WebServlet(urlPatterns = "/signin" )
public class SignInServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected SignInValidator validator;
	protected  Map<String,String> formErrors;

	@Override
	public void init() throws ServletException {
		super.init();
		formErrors = new HashMap<String,String>();

	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		Cookie[] cookies = request.getCookies();
		HttpSession session = request.getSession();
		Cookie tmpuuid = null,tmpUser=null;
		for(Cookie cookie:cookies){
			
			if(cookie.getName().equals("uuid")){
				tmpuuid=cookie;
			}
			if(cookie.getName().equals("user")){
				tmpUser=cookie;
			}
		}
			if(tmpuuid!=null && tmpUser!=null){
			
				try {
					User user = validator.getUser().findUserByUserName(tmpUser.getValue());
				if(session.getAttribute("login")==null){
					session.setAttribute("login", tmpUser.getValue());
					session.setAttribute("idlogin", user.getId());
				}
					
				} catch (Exception e) {
					System.out.println("data cannot be matched to existing user");
					request.getRequestDispatcher(UriMapping.LOGIN.getRessourceUrl()).forward(request, response);
					
				}
				

				
			}
		else 
		
		request.getRequestDispatcher(UriMapping.LOGIN.getRessourceUrl()).forward(request, response);
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String userlogin = request.getParameter("login");
		String passwd = request.getParameter("password");
		String login;
		User user = null;

		validator = new SignInValidator();
		try {
			validator.validateCredantials(userlogin, passwd);
			formErrors=validator.getCommittedErrors();
			Cookie[] cookies = new Cookie[2];
			if(validator.getCommittedErrors().isEmpty()){
				HttpSession session = request.getSession();
			
				if(validator.IsValidEmailFormat(userlogin)==true){ // connecté avec @mail
					 user = validator.getUser().findUserByEmail(userlogin);
					login = user.getUserName();
					session.setAttribute("login", login);
					session.setAttribute("idlogin", user.getId());
					
					
					
					}
				else {// connection avec username
					user = validator.getUser().findUserByUserName(userlogin);
					login = user.getUserName();
					session.setAttribute("login", login);
					session.setAttribute("idlogin", user.getId());
				
				
				
				}
				
				cookies[0] = CookiesHelper.buildCookie("uuid", UUidGenerator.generateUUID(login).toString(), "elysane.net", this.getServletContext().getContextPath(), 60*60, false);
				cookies[1] = CookiesHelper.buildCookie("user", login, "elysane.net", this.getServletContext().getContextPath(), 60*60, false);
				response.addCookie(cookies[0]);
				response.addCookie(cookies[1]);
				 response.sendRedirect("/DAR2/events?mode=actus&limit=15");
			}
			else {
				request.setAttribute("formErrors", formErrors);
				request.getRequestDispatcher(UriMapping.LOGIN.getRessourceUrl()).forward(request, response);
				formErrors.clear();
				
				}}
			
		 catch (Exception e) {
			System.out.println("user credantials are wrong");
			//System.out.println(formErrors.keySet().size());
			e.printStackTrace();
		}
	}

	
}
