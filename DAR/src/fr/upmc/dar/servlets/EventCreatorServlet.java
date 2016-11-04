package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.EventVisibility;
import fr.upmc.dar.enums.UriMapping;
import fr.upmc.dar.tools.FormSelectors;



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
		String creator = (String) session.getAttribute("login");
		try {
			User user = DAOFactory.createUserDao().findUserByUserName(creator);
			
			int placeOtp = Integer.parseInt(request.getParameter("eventplace"));
			int themeOpt = Integer.parseInt(request.getParameter("eventtheme"));
			int privacyOpt = Integer.parseInt(request.getParameter("eventpolicy"));
			String name = request.getParameter("eventname");
			String date = request.getParameter("eventdate");
			String place = FormSelectors.EVENTPLACES[placeOtp];
			String adress = request.getParameter("eventaddress");
			String theme = FormSelectors.EVENTTHEME[themeOpt];
			EventVisibility privacy = FormSelectors.EVENTPRIVACY[privacyOpt];
			String description = request.getParameter("eventdescription");
			
			Event event = new Event(user, name,privacy, description, date, theme, place, adress,new ArrayList<>());
			DAOFactory.createEventDao().createEvent(event);
			
			Gson ee = new Gson();
			String res ;
			res = ee.toJson(event,Event.class);
			System.out.println(res);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
