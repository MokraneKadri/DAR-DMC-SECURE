package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.enums.UriMapping;
import fr.upmc.dar.tools.MailHelper;
import fr.upmc.dar.tools.SignInValidator;

@WebServlet(urlPatterns = "/passwordreset" )
public class PasswordResetServlet extends HttpServlet {

	 
		private static final long serialVersionUID = 1L;
		//IUserDao user;
		protected SignInValidator validator ;

		@Override
		public void init() throws ServletException {
			super.init();
			validator = new SignInValidator();
			
			
		}
		
		
		@Override
		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
			
			request.setCharacterEncoding("UTF-8");
			response.setCharacterEncoding("UTF-8");
			request.getRequestDispatcher(UriMapping.RESET_PASSWORD.getRessourceUrl()).forward(request, response);
			
		}
		
		
		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			request.setCharacterEncoding("UTF-8");
			String login = request.getParameter("login");
			try {
				if(validator.doesUserExist(login)==true){
					
					
					MailHelper mailer = new MailHelper("mokrane.kadri@hotmail.fr", "Password reset", "blablabla");	
					mailer.sendEmail();
				}
				Map<String ,String > infos = new HashMap<String ,String>();
				infos.put("user", login);
				infos.put("infos", "Reinitialisation du mot passe : etape 2");
				infos.put("content", "Nous avons pris en charge votre demande !.<p> afin de reinitialiser votre mot de passe .Merci de suivre les instruction que nous vous avons envoyé par Email");
				request.setAttribute("pageInfos", infos);
				
				request.getRequestDispatcher(UriMapping.POSTPASSWDRESET.getRessourceUrl()).forward(request, response);
					
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
		}

		
	}
