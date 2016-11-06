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

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
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
		IEventDao dao = DAOFactory.createEventDao();
		List<Event> list = null;

		User user = null;

		if (mode == null || mode.equals(""))
			response.getWriter().print(new Warning("L'appel à la servlet n'est pas correctement formulée"));
			
		
		try {
			user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Warning("Vous êtes déconnecté, impossible de poursuivre"));
			return;
		}
		
		JSONArray array = null;

		switch (mode) {
		
		case "create":
			Event event = null;
			try {
				event = new Event(
						user, 
						request.getParameter("name"), 
						request.getParameter("privacy"), 
						request.getParameter("description"), 
						request.getParameter("date"), 
						request.getParameter("theme"), 
						request.getParameter("places"), 
						request.getParameter("address"));
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(new Error("Echec de création de l'event"));
				return;
			}
			
			Comment com = new Comment(user, "J'aime commenter tout !", "Maintenant");
			ArrayList<Comment> coms = new ArrayList<>();
			coms.add(com);
			event.setComments(coms);
			dao.createEvent(event);

			break;
		
		case "list":
			
			if (request.getParameterMap().keySet().size() == 1) {
				array = new JSONArray();
				for (Event evt : dao.getAllEvents())
					try {
						array.put(evt.toJSONObject());
					} catch (JSONException e) {
						e.printStackTrace();
						response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
						return;
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
			break;
		
		/**
		 * Génération d'un JSONArray possèdant les Event répondants aux critères de recherche
		 */
		case "search":
			HashMap<String, List<Event>> recherche = new HashMap<>();
			
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

			list = new ArrayList<>();
			
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
				} catch (JSONException e) {
					e.printStackTrace();
					response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
					return;
				}
			response.getWriter().print(array);
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
		User user = null;

		try {
			user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		IEventDao dao = DAOFactory.createEventDao();
		Event event = null;
		List<Event> events = null;
		Integer id = null;

		switch (mode) {
		case "create":
			try {
				event = new Event(
						user, 
						request.getParameter("name"), 
						request.getParameter("privacy"), 
						request.getParameter("description"), 
						request.getParameter("date"), 
						request.getParameter("theme"), 
						request.getParameter("places"), 
						request.getParameter("address"));
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(new Error("Echec de création de l'event"));
				return;
			}

			dao.createEvent(event);

			break;
		case "remove":
			events = dao.getEventsByOwner(user);
			id = Integer.valueOf(request.getParameter("id"));
			for (Event e : events) {
				if (e.getId() == id && e.getCreator().getId() == user.getId()) {
					dao.removeEvent(e);
				}
			}
			break;
		case "update":
			id = Integer.valueOf(request.getParameter("id"));
			try {
				event = dao.getEventById(id);
				event.setEventName((request.getParameter("name") != null) ? request.getParameter("name") : event.getEventName());
				if (request.getParameter("privacy") != null)
					event.setEventprivacy(EventVisibility.stringToEventVisibility(request.getParameter("privacy")));
				event.setEventDescription((request.getParameter("description") != null) ? request.getParameter("description") : event.getEventDescription());
				event.setEventDate((request.getParameter("date") != null) ? request.getParameter("date") : event.getEventDate());
				event.setEventTheme((request.getParameter("theme") != null) ? request.getParameter("theme") : event.getEventTheme());
				event.setEventPlace((request.getParameter("places") != null) ? request.getParameter("places") : event.getEventPlace());
				event.setEventAdresse((request.getParameter("address") != null) ? request.getParameter("address") : event.getEventAdresse());
				dao.updateEvent(event);
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(new Error("De la mise à jour de l'event"));
			}
			break;
		default:
			break;
		}
	}
}
