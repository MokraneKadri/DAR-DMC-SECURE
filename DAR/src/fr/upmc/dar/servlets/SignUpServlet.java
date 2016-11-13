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
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

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
	protected JSONObject pageInfos;
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		formErrors = new HashMap<String,String>();
		pageInfos= new JSONObject();
		
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		
		request.getRequestDispatcher(UriMapping.REGISTER.getRessourceUrl()).forward(request, response);
		
	}
	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String fname = Jsoup.clean(request.getParameter("firstname"), Whitelist.none());
		 String lname = Jsoup.clean(request.getParameter("lastname"), Whitelist.none());
		 String userName = Jsoup.clean(request.getParameter("username"), Whitelist.none());
		 String eMail = Jsoup.clean(request.getParameter("email"), Whitelist.none());
		 String password = Jsoup.clean(request.getParameter("password"), Whitelist.none());
		 String confirmationPassword = Jsoup.clean(request.getParameter("confirm_password"), Whitelist.none());
		 String etablissement = Jsoup.clean(request.getParameter("university"), Whitelist.none());
		 String street = Jsoup.clean(request.getParameter("street"), Whitelist.none());
		 int zip =Integer.parseInt(Jsoup.clean(request.getParameter("zip"), Whitelist.none()));
		 String city = Jsoup.clean(request.getParameter("city"), Whitelist.none());
		 String cursus = Jsoup.clean(request.getParameter("cursus"), Whitelist.none());
//		 System.out.println("received from form : \n name :"+fname
//				 										+"username: "+userName
//				 										
//				 										+"email:"+eMail
//				 										+"pass :"+password
//				 										+"confpass:"+confirmationPassword
//				 										+"etablissement:"+etablissement
//				 										+"cursus :"+cursus);
		 
		 
		 validator = new SignUpValidator();
		 try{
			 validator.canRegisterUser(fname,lname, userName, eMail, password, confirmationPassword, etablissement, cursus);
			 formErrors=validator.getCommittedErrors();
			 if(formErrors.isEmpty()){
				 User utilisateur = new User(fname,lname,userName,eMail,password,street,zip,city,etablissement,cursus);
			
				 validator.getUser().createUser(utilisateur);
				 pageInfos.put("user", userName);
				 pageInfos.put("infos", "votre compte a été créer avec succes ");
				 pageInfos.put("content", "vous allez maintenant rediriger automatiquement vers la page de connection");
				 request.setAttribute("pageInfos", pageInfos);
				 response.setHeader("Refresh", "3;url="+"/DAR/signin");// redirection a la page de login apres 3 seconde
				 request.getRequestDispatcher(UriMapping.POSTSIGNUPRESET.getRessourceUrl()).forward(request, response);
				
			}
		 
			
			else 
				{
					
					pageInfos.putAll(formErrors);
					request.setAttribute("formErrors", formErrors);
					getServletContext().getRequestDispatcher(UriMapping.REGISTER.getRessourceUrl()).forward(request, response);
				    System.out.println(pageInfos.toJSONString());
				    formErrors.clear();
				}
				//response.getWriter().write("l'emeil deja pris");
		 }catch(Exception e){
			 e.printStackTrace();
		 }

	}

}
