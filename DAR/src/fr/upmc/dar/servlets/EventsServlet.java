package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;

import fr.upmc.dar.api.helpers.YelpBusinessSearch;
import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Business;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.Place;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.EventVisibility;
import fr.upmc.dar.json.Warning;
import fr.upmc.dar.json.Error;

/**
 * Servlet REST pour la gestion des events
 * 
 * TODO Déplacer les methodes type POST dans le doPost
 * TODO Ajouter un check voir si user n'est pas null
 * 
 * @author Daniel RADEAU
 *
 */

@WebServlet(urlPatterns = {"/events"})
public class EventsServlet extends HttpServlet {

	private static final long serialVersionUID = 7431335976870937616L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws 
			ServletException, 
			IOException 
	{
		String mode = request.getParameter("mode");

		if (mode == null || mode.equals(""))
			response.getWriter().print(new Warning("L'appel à la servlet n'est pas correctement formulée"));

		switch (mode) {
		case "event":
			event(request, response);
			break;
		case "actus":
			actus(request, response);
			break;
		case "create":
			createAndSomeComments(request, response);
			break;
		case "list":
			list(request, response);
			break;
		case "search":
			search(request, response);
			break;
		default:
			break;
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws 
			ServletException, 
			IOException 
	{
		String mode = request.getParameter("mode");

		switch (mode) {
		case "create":
			create(request, response);
			break;
		case "remove":
			remove(request, response);
			break;
		case "update":
			update(request, response);
			break;
		case "comment":
			comment(request, response);
			break;
		default:
			break;
		}
	}
	
	private void createAndSomeComments(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = null;
		Event event = null;
	
		try {
			user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Place place = new Place(request.getParameter("place_type"), request.getParameter("place_name"));
			event = new Event(
					user, 
					request.getParameter("name"), 
					request.getParameter("privacy"), 
					request.getParameter("description"), 
					request.getParameter("date"), 
					request.getParameter("theme"), 
					request.getParameter("places"), 
					request.getParameter("address"),
					place);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("Echec de création de l'event"));
			return;
		}

		Comment com = new Comment(user, "J'aime commenter tout !");
		Comment com2 = new Comment(user, "J'aime commenter tout !");
		Comment com3 = new Comment(user, "J'aime commenter tout !");
		Comment com4 = new Comment(user, "J'aime commenter tout !");
		ArrayList<Comment> coms = new ArrayList<>();
		coms.add(com);
		coms.add(com2);
		coms.add(com3);
		coms.add(com4);
		event.setComments(coms);
		DAOFactory.createEventDao().createEvent(event);
	}
	
	private void event(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null)
			request.getRequestDispatcher("jsp/event.jsp?id=" + request.getParameter("id")).forward(request, response);
	}
	
	private void actus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Event> list = DAOFactory.createEventDao().getAllEvents();
		while (list.size() > Integer.parseInt(request.getParameter("limit")))
			list.remove(0);
		request.setAttribute("actus", list);
		request.getRequestDispatcher("/jsp/actus.jsp").forward(request, response);
	}
	
	private void list(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		JSONArray array;
		IEventDao dao = DAOFactory.createEventDao();
		
		if (request.getParameterMap().keySet().size() == 1) {
			array = new JSONArray();
			for (Event evt : dao.getAllEvents()) {
				try {
					array.put(evt.toJSONObject());
				} catch (JSONException e) {
					e.printStackTrace();
					response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
					return;
				}
				System.out.println("NAME : " + evt.getDescription());
			}
			response.getWriter().print(array);
		} else {
			String id = request.getParameter("id");
			if (id != null) {
				try {
					response.getWriter().print(dao.getEventById(Integer.valueOf(id)).toJSONObject());
				} catch (Exception e) {
					e.printStackTrace();
					response.getWriter().print(new Error("Erreur lors du listing par id"));
				}
			}
		}
	}
	
	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, List<Event>> recherche = new HashMap<>();
		IEventDao dao = DAOFactory.createEventDao();
		List<Event> list = new ArrayList<>();
		JSONArray array;

		if (request.getParameter("name") != null)
			recherche.put("name", (dao.selectTuplesWhereFieldContainsLike(Event.class, "name", request.getParameter("name"))));
		if (request.getParameter("privacy") != null)
			recherche.put("privacy", (dao.selectTuplesWhereFieldContainsLike(Event.class, "privacy", request.getParameter("privacy"))));
		if (request.getParameter("description") != null)
			recherche.put("description", (dao.selectTuplesWhereFieldContainsLike(Event.class, "description", request.getParameter("description"))));
		if (request.getParameter("date") != null)
			recherche.put("date", (dao.selectTuplesWhereFieldContainsLike(Event.class, "date", request.getParameter("date"))));
		if (request.getParameter("theme") != null)
			recherche.put("theme", (dao.selectTuplesWhereFieldContainsLike(Event.class, "theme", request.getParameter("theme"))));
		if (request.getParameter("places") != null)
			recherche.put("places", (dao.selectTuplesWhereFieldContainsLike(Event.class, "places", request.getParameter("places"))));
		if (request.getParameter("address") != null)
			recherche.put("address", (dao.selectTuplesWhereFieldContainsLike(Event.class, "address", request.getParameter("address"))));

		HashSet<Integer> ids = new HashSet<>();

		for (List<Event> l : recherche.values()) {
			for (Event evt : l) {
				boolean isSelected = true;
				boolean isIn = false;
				for (List<Event> comp : recherche.values()) {
					for (Event evt2 : comp) {
						if (evt.getId() == evt2.getId()) {
							isIn = true;
						}
					}
					isSelected &= isIn;
				}
				if (isSelected && !ids.contains(evt.getId())) {
					ids.add(evt.getId());
					list.add(evt);
				}
			}
		}

		array = new JSONArray();
		for (Event evt : list)
			try {
				array.put(evt.toJSONObject());
				response.getWriter().print(array);
			} catch (JSONException e) {
				e.printStackTrace();
				response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
			}
	}
	
	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
			Place place = new Place(request.getParameter("place_type"), request.getParameter("place_name"));			
			String businessId = request.getParameter("business_id"); 

			if (businessId == null) {
				Event event = new Event(
						user, 
						request.getParameter("name"), 
						request.getParameter("privacy"), 
						request.getParameter("description"), 
						request.getParameter("date"), 
						request.getParameter("theme"), 
						request.getParameter("places"), 
						request.getParameter("address"),
						place);
				DAOFactory.createEventDao().createEvent(event);
			} else {
				Business business = YelpBusinessSearch.idToBusiness(businessId);
				Event event = new Event(
						user, 
						request.getParameter("name"), 
						request.getParameter("privacy"), 
						request.getParameter("description"), 
						request.getParameter("date"), 
						request.getParameter("theme"), 
						request.getParameter("places"), 
						request.getParameter("address"),
						place,
						business);
				DAOFactory.createEventDao().createEvent(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("Echec de création de l'event"));
		}

		

	}
	
	private void remove(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
			List<Event> events = DAOFactory.createEventDao().getEventsByOwner(user);
			Integer id = Integer.valueOf(request.getParameter("id"));
			for (Event e : events) {
				if (e.getId() == id && e.getCreator().getId() == user.getId()) {
					DAOFactory.createEventDao().removeEvent(e);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("Echec de la suppression de l'event"));
		}
	}
	
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			Event event = DAOFactory.createEventDao().getEventById(id);
			event.setName((request.getParameter("name") != null) ? request.getParameter("name") : event.getName());
			if (request.getParameter("privacy") != null)
				event.setPrivacy(EventVisibility.stringToEventVisibility(request.getParameter("privacy")));
			event.setDescription((request.getParameter("description") != null) ? request.getParameter("description") : event.getDescription());
			event.setDate((request.getParameter("date") != null) ? request.getParameter("date") : event.getDate());
			event.setTheme((request.getParameter("theme") != null) ? request.getParameter("theme") : event.getTheme());
			event.setPlaces((request.getParameter("places") != null) ? request.getParameter("places") : event.getPlaces());
			event.setAddress((request.getParameter("address") != null) ? request.getParameter("address") : event.getAddress());
			DAOFactory.createEventDao().updateEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("De la mise à jour de l'event"));
		}
	}
	
	private void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			User user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
			Event event = DAOFactory.createEventDao().getEventById(id);
			List<Comment> comments = event.getComments();
			Comment comment = new Comment(user, request.getParameter("content"));
			comments.add(comment);
			DAOFactory.createEventDao().updateEvent(event);
			request.getRequestDispatcher("/jsp/event.jsp?id=" + event.getId()).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("De la mise à jour de l'event"));
		}
	}
}
