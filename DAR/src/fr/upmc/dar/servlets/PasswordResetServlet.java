package fr.upmc.dar.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.enums.Uri;

@WebServlet(urlPatterns = "/passwordreset" )
public class PasswordResetServlet extends HttpServlet {

	 
		private static final long serialVersionUID = 1L;
		IUserDao user;


		@Override
		public void init() throws ServletException {
			super.init();
			user = DAOFactory.createUserDao();
		}
		
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			
			request.getRequestDispatcher(Uri.RESETPASWD.getRessourceUrl()).forward(request, response);
			
		}
		
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		}

		
	}
