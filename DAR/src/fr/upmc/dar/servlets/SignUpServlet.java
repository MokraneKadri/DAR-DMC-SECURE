package fr.upmc.dar.servlets;

import java.io.IOException;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.UserDao;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.Uri;




@WebServlet(urlPatterns = "/signup" )
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//private static final String SINUP_JSP  = "/JSP/signup.jsp";
	
	
	private IUserDao user ;
	
	
	
	
	@Override
	public void init() throws ServletException {
		super.init();
		user = DAOFactory.createUserDao();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		System.out.println(Uri.REGISTER.getRessourceUrl());
		request.getRequestDispatcher(Uri.REGISTER.getRessourceUrl()).forward(request, response);
		
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String firstName = request.getParameter("username");
		 String lastName = request.getParameter("username");
		 String userName = request.getParameter("username");
		 String eMail = request.getParameter("username");
		 String password = request.getParameter("username");
		 String etablissement = request.getParameter("username");
		 String cursus = request.getParameter("username");
		 System.out.println("received "+firstName);
		 try{
			User utilisateur = new User(firstName,lastName,userName,eMail,password,etablissement,cursus);
			if(user.findUserByEmail(eMail)==null){
				user.createUser(utilisateur);
				response.getWriter().write("welcome new user");
			}
			
			else 
				response.getWriter().write("l'emeil deja pris");
		 }catch(Exception e){
			 e.printStackTrace();
		 }

	}

}
