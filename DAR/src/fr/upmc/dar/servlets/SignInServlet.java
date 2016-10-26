package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	protected IUserDao user;
	protected SignInValidator validator;
	protected  Map<String,String> formErrors;
	protected JSONObject errorsJson;
	
	@Override
	public void init() throws ServletException {
		super.init();
		formErrors = new HashMap<String,String>();
		user = DAOFactory.createUserDao();
		validator = new SignInValidator(user,formErrors );
		errorsJson = new JSONObject();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(UriMapping.LOGIN.getRessourceUrl()).forward(request, response);
		
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String passwd = request.getParameter("password");
	
		
		try {
			validator.validateCredantials(login, passwd);
			if(validator.getCommittedErrors().isEmpty())
			
			System.out.println("user "+login + "is now connected" +"error size"+formErrors.size());
			else {
				System.out.println("user credantials are wrong");{
			
				errorsJson.putAll(formErrors);
				System.out.printf( "JSON: %s", errorsJson.toString() );
				}}}
			
		 catch (Exception e) {
			System.out.println("user credantials are wrong");
			System.out.println(formErrors.keySet().size());
			e.printStackTrace();
		}
	}

	
}
