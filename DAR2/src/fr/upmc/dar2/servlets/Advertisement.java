package fr.upmc.dar2.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.upmc.dar2.enums.UriMapping;
import fr.upmc.dar2.tools.CookiesHelper;
import fr.upmc.dar2.tools.UUidGenerator;

@WebServlet("/darpart2")
public class Advertisement extends HttpServlet {
	private static final long serialVersionUID = 1L;


	public Advertisement() {
		super();

	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		int i =0;
		Cookie[] cookies = request.getCookies();

		Cookie tmpuuid = null,tmpUser=null;
		if(cookies==null){

			cookies =new Cookie[2];
			cookies[0] = CookiesHelper.buildCookie("addsuuid", UUidGenerator.generateUUID("").toString(), "localhost", this.getServletContext().getContextPath(), 60*60, false);
			cookies[1] = CookiesHelper.buildCookie("addsuser", ""+(i++), "localhost", this.getServletContext().getContextPath(), 60*60, false);
			response.addCookie(cookies[0]);
			response.addCookie(cookies[1]);
		}
		else {	

				if(request.getParameterMap().size()==0){
					System.out.println("normal call");
					request.getRequestDispatcher("/cookies/product.html").forward(request, response);
				}
				else {
					String data = request.getParameter("data");
					String newData = request.getParameter("newData");
					if(data==null && !(newData==null)){//received infos about new user 
						System.out.println("new visitor visited host website"+ newData);
						response.getWriter().println("");
					}
					if(data!=null && (newData==null)){//received infos about new user 
						System.out.println("know visitor accessing host website again "+data);	
						response.getWriter().println("");
					}		
					else System.out.println("could not interpret informations");
					response.getWriter().println("");
				}
			
		}
	}}



