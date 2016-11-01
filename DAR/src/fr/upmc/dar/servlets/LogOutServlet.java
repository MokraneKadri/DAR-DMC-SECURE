package fr.upmc.dar.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns="/logout")
public class LogOutServlet extends HttpServlet {

	
	
private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
			    HttpSession currentSession = request.getSession();
			    Object o = currentSession.getAttribute("login");
		        if(o != null) {
		        	currentSession.removeAttribute("login");
		            
		        	 response.sendRedirect("/DAR/signin");//.getRequestDispatcher("/signin").forward(request, response);
		        }
		
	}
	
	
	



}
