package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.UriMapping;
import fr.upmc.dar.tools.SignUpValidator;




@WebServlet(urlPatterns = "/signup" )
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String SINUP_JSP  = "/JSP/signup.jsp";
	
	

	protected SignUpValidator validator;
	protected  Map<String,String> formErrors;
	protected JSONObject errors;
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		formErrors = new HashMap<String,String>();
	
		validator = new SignUpValidator();
		errors= new JSONObject();
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		request.getRequestDispatcher(UriMapping.REGISTER.getRessourceUrl()).forward(request, response);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String fname = request.getParameter("firstname");
		 String lname = request.getParameter("lastname");
		 String userName = request.getParameter("username");
		 String eMail = request.getParameter("email");
		 String password = request.getParameter("password");
		 String confirmationPassword = request.getParameter("confirm_password");
		 String etablissement = request.getParameter("university");
		 String cursus = request.getParameter("cursus");
		 System.out.println("received from form : \n name :"+fname
				 										+"username: "+userName
				 										
				 										+"email:"+eMail
				 										+"pass :"+password
				 										+"confpass:"+confirmationPassword
				 										+"etablissement:"+etablissement
				 										+"cursus :"+cursus);
		 try{
			 validator.canRegisterUser(fname,lname, userName, eMail, password, confirmationPassword, etablissement, cursus);
			 formErrors=validator.getCommittedErrors();
			 if(formErrors.isEmpty()){
				 User utilisateur = new User(fname,lname,userName,eMail,password,etablissement,cursus);
			
				 validator.getUser().createUser(utilisateur);
				 response.setHeader("Refresh", "5;url="+UriMapping.LOGIN.getRessourceUrl());
				 response.sendRedirect("/DAR/signin");
				 //response.set.getRequestDispatcher(UriMapping.LOGIN.getRessourceUrl()).forward(request, response);
					//System.out.println(errors.toJSONString());
				// request.getRequestDispatcher(UriMapping.HOME.getRessourceUrl()).forward(request, response);
				 
			}
		 
			
			else 
				{
					
					errors.putAll(formErrors);
					request.setAttribute("formErrors", formErrors);
					getServletContext().getRequestDispatcher(UriMapping.REGISTER.getRessourceUrl()).forward(request, response);
				    System.out.println(errors.toJSONString());
				    formErrors.clear();
				}
				//response.getWriter().write("l'emeil deja pris");
		 }catch(Exception e){
			 e.printStackTrace();
		 }

	}

}
