package fr.upmc.dar.servlets;

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

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.UriMapping;
import fr.upmc.dar.tools.PasswordEncryptor;
import fr.upmc.dar.tools.SignInValidator;

import net.sf.json.*;


@WebServlet(urlPatterns = "/signin" )
public class SignInServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	protected SignInValidator validator;
	protected  Map<String,String> formErrors;
	protected JSONObject errorsJson;
	
	@Override
	public void init() throws ServletException {
		super.init();
		formErrors = new HashMap<String,String>();
		
		validator = new SignInValidator();
		errorsJson = new JSONObject();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(UriMapping.LOGIN.getRessourceUrl()).forward(request, response);
		
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userlogin = request.getParameter("login");
		String passwd = request.getParameter("password");
		String login;
		
		try {
			validator.validateCredantials(userlogin, passwd);
			formErrors=validator.getCommittedErrors();

			if(validator.getCommittedErrors().isEmpty()){
				HttpSession session = request.getSession();
			
				if(validator.IsValidEmailFormat(userlogin)==true){ // connecté avec @mail
					User user = validator.getUser().findUserByEmail(userlogin);
					login = user.getUserName();
					session.setAttribute("login", login);
					session.setAttribute("idlogin", user.getId());
				}
				else {// connection avec username
					User user = validator.getUser().findUserByUserName(userlogin);
					login = user.getUserName();
					session.setAttribute("login", login);
					session.setAttribute("idlogin", user.getId());
				
				}
				//getServletContext().getRequestDispatcher(UriMapping.HOME.getRessourceUrl()).forward(request, response);
				//request.getRequestDispatcher(UriMapping.HOME.getRessourceUrl()).forward(request, response);
				 response.sendRedirect("/DAR/home");
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
