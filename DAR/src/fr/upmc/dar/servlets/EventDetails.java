package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.enums.UriMapping;

@WebServlet(urlPatterns="/eventDetails")
public class EventDetails extends HttpServlet {

	/**
	 * 
	 */
	
	protected IEventDao event ;
	protected HashMap<String,String> eventdetails;
	private static final long serialVersionUID = 1L;
	
	@Override
	public void init() throws ServletException {
	super.init();
	eventdetails = new HashMap<String,String>();
	
	//comments = new ArrayList<Comment>();
	//members = new ArrayList<User>();
}

	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		String eventname=(String) request.getParameter("evt");
		System.out.println("event name is:"+eventname);
		event = DAOFactory.createEventDao();
		List<Event> e= new ArrayList<Event>();
		e = event.getEventsByName(eventname);
		Event ee  = e.get(0);
		System.out.println(e.size());
		eventdetails.put("name", ee.getName());
		eventdetails.put("adresse", ee.getAddress());
		eventdetails.put("date", ee.getDateToString());
		eventdetails.put("privacy", ee.getPrivacy().toString());
		eventdetails.put("description", ee.getDescription());
		eventdetails.put("place", ee.getPlaces().toString());
		eventdetails.put("theme", ee.getTheme());
		eventdetails.put("participants", Integer.toString(ee.getCandidates().size()));
		eventdetails.put("creator", ee.getCreator().getUserName());
		eventdetails.put("commentCount", Integer.toString(ee.getCommentsCounts()));
		request.setAttribute("details", eventdetails);
		request.getRequestDispatcher(UriMapping.EVENTDETAILS.getRessourceUrl()).forward(request, response);

}
	
}