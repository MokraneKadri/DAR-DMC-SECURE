package fr.upmc.dar.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.UriMapping;



@WebServlet(urlPatterns = "/create_event" )
public class EventCreatorServlet extends HttpServlet {
	 
	private static final long serialVersionUID = 1L;
	IUserDao user;


	@Override
	public void init() throws ServletException {
		super.init();
		user = DAOFactory.createUserDao();
	}
	
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(UriMapping.CREATE_EVENT.getRessourceUrl()).forward(request, response);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String email = (String) session.getAttribute("login");
		try {
			User user = DAOFactory.createUserDao().findUserByEmail(email);
			
			String name = request.getParameter("name");
			String date = request.getParameter("eventDate");
			String place = request.getParameter("eventPlace");
			String adress = request.getParameter("eventAdress");
			String theme = request.getParameter("eventTheme");
			String description = "SOMETHING NO INPUT IN CREATE EVENT FORM";
			
			Event event = new Event(user, name, description, date, theme, place, adress);
			DAOFactory.createEventDao().createEvent(event);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
