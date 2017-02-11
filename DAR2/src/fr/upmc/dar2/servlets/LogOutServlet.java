package fr.upmc.dar2.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@WebServlet(urlPatterns="/logout")
public class LogOutServlet extends HttpServlet {

	
	
private static final long serialVersionUID = 1L;

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
			    HttpSession currentSession = request.getSession();
			    Object o = currentSession.getAttribute("login");
		        if(o != null) {
		        	currentSession.removeAttribute("login");
		            currentSession.invalidate();
		        	
		        }
		        
		        
		        Cookie[] cookies = request.getCookies();
		        Cookie loginCookie = null;
		        Cookie sessionCookie = null;
		        if(cookies !=null){
		        	
		        	for(Cookie cookie :cookies){
		        		
		        		if(cookie.getName().equals("user")){
		        			loginCookie = cookie;
		        		}
		        		if(cookie.getName().equals("uuid")){
		        			sessionCookie = cookie;
		        		}
		        	}
		        	
		        	if(loginCookie!=null){
		        		loginCookie.setMaxAge(0);//invalidating cookie
		        		response.addCookie(loginCookie);
		        	}
		        	if(sessionCookie!=null){
		        		sessionCookie.setMaxAge(0);//invalidating cookie
		        		response.addCookie(sessionCookie);
		        	}
		        }
		        
		        response.sendRedirect("/DAR2/signin");//.getRequestDispatcher("/signin").forward(request, response);
	        	 
	        	 
		        
		
	}
	
	
	



}
