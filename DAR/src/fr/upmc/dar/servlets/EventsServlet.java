package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
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
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

import fr.upmc.dar.dao.ApiDAO;
import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Business;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.University;
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
		String universityId = request.getParameter("university_id");
		University university = new ApiDAO().getUniversity(universityId);

		try {
			user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			event = new Event(
					user, 
					request.getParameter("name"), 
					request.getParameter("privacy"), 
					request.getParameter("description"), 
					request.getParameter("date"),
					request.getParameter("hour"), 
					request.getParameter("theme"), 
					request.getParameter("places"), 
					request.getParameter("address"),
					request.getParameter("place_type"),
					request.getParameter("place_name"),
					university);
			event.addCandidate(user);
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

	/**
	 * Appel à la page event.jsp d'un event particulier. <br/>
	 * 
	 * Soit <em> x </em> un entier naturel > 0 <br/> 
	 * Requête : /DAR/events?mode=event&id=<em>x</em> <br/>
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void event(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getParameter("id") != null)
			request.getRequestDispatcher("jsp/event.jsp?id=" + request.getParameter("id")).forward(request, response);
	}

	/**
	 * Appel à la page actus.jsp proposant une liste d'événements récents. <br/>
	 * 
	 * Soit <em> x </em> un entier naturel > 0 <br/> 
	 * Requête : /DAR/events?mode=actus&limit=<em>x</em> <br/>
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void actus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Event> list = DAOFactory.createEventDao().getAllEvents();
		while (list.size() > Integer.parseInt(request.getParameter("limit")))
			list.remove(0);
		Collections.reverse(list);
		request.setAttribute("actus", list);
		request.getRequestDispatcher("/jsp/actus.jsp").forward(request, response);
	}

	/**
	 * Retourne au client une liste JSON contenant soit tous les events soit qu'un.
	 * 
	 * Soit <em> x </em> un entier naturel > 0 <br/> 
	 * Requête : /DAR/events?mode=list[&id=<em>x</em>]<br/>
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

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
			}
			response.getWriter().print(array);
		} else if (request.getParameter("id") != null) {
			String id = request.getParameter("id");
			try {
				response.getWriter().print(dao.getEventById(Integer.valueOf(id)).toJSONObject());
			} catch (Exception e) {
				e.printStackTrace();
				response.getWriter().print(new Error("Erreur lors du listing par id"));
			}
		} else if (request.getParameter("creator_id") != null) {
			String creatorId = request.getParameter("creator_id");
			
			array = new JSONArray();
			for (Event evt : dao.selectTuplesWhereFieldIs(Event.class, "creator.id", creatorId)) {
				try {
					array.put(evt.toJSONObject());
				} catch (JSONException e) {
					e.printStackTrace();
					response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
					return;
				}
			}
			response.getWriter().print(array);
		} else if (request.getParameter("member_id") != null) {
			String memberId = request.getParameter("member_id");
			
			array = new JSONArray();
			for (Event evt : dao.getAllEvents()) {
				try {
					for (User candidate : evt.getCandidates()) {
						if (candidate.getId() == Integer.parseInt(memberId))
							array.put(evt.toJSONObject());
					}
				} catch (JSONException e) {
					e.printStackTrace();
					response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
					return;
				}
			}
			response.getWriter().print(array);
		}
	}

	/**
	 * Retourne au client une liste JSON contenant des events selon des critères de recherche par mots clef
	 *
	 * Soit <em> # </em> du texte quelconque <br/> 
	 * Requête : /DAR/events?mode=search[&name=#][&privacy=#][&description=#][&date=#][&theme=#][&places=#][&address=#]
	 * 
	 * Pour l'instant private doit être "private" | "public" | "university"
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void search(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HashMap<String, List<Event>> recherche = new HashMap<>();
		IEventDao dao = DAOFactory.createEventDao();
		List<Event> list = new ArrayList<>();
		JSONArray array;

		for (String key : request.getParameterMap().keySet())
			System.out.println(key + ':' + request.getParameter(key));

		if (request.getParameter("name") != null)
			recherche.put("name", (dao.selectTuplesWhereFieldContainsLike(Event.class, "name", request.getParameter("name"))));
		if (request.getParameter("privacy") != null)
			recherche.put("privacy", (dao.selectTuplesWhereFieldContainsLike(Event.class, "privacy", String.valueOf(EventVisibility.stringToEventVisibility(request.getParameter("privacy")).ordinal()))));
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
		for (Event evt : list) {
			try {
				array.put(evt.toJSONObject());
			} catch (JSONException e) {
				e.printStackTrace();
				response.getWriter().print(new Error("Erreur dans la construction de la réponse JSON"));
			}
		}
		response.getWriter().print(array);
	}

	/**
	 * Request : /DAR/events?mode=create&name=#&privacy=#&description=#&date=#&theme=#&places=#&address=#&place_type=#&place_name=#[&business_id=#]
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void create(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			User user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));			
			String businessId = Jsoup.clean(request.getParameter("business_id"), Whitelist.basic()); 
			String universityId = Jsoup.clean(request.getParameter("university_id"), Whitelist.basic());
		
			University university = new ApiDAO().getUniversity(universityId);

			if (businessId == null) {
				Event event = new Event(
						user, 
						Jsoup.clean(request.getParameter("name"), Whitelist.basic()), 
						Jsoup.clean(request.getParameter("privacy"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("description"), Whitelist.basic()), 
						Jsoup.clean(request.getParameter("date"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("hour"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("theme"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("places"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("address"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("place_type"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("place_name"), Whitelist.basic()),
						university);
				event.addCandidate(user);
				DAOFactory.createEventDao().createEvent(event);
			} else {
				Business business = new ApiDAO().getBusiness(businessId); //YelpBusinessSearch.idToBusiness(businessId);
				Event event = new Event(
						user, 
						Jsoup.clean(request.getParameter("name"), Whitelist.basic()), 
						Jsoup.clean(request.getParameter("privacy"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("description"), Whitelist.basic()), 
						Jsoup.clean(request.getParameter("date"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("hour"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("theme"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("places"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("address"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("place_type"), Whitelist.basic()),
						Jsoup.clean(request.getParameter("place_name"), Whitelist.basic()),
						business,
						university);
				event.addCandidate(user);
				DAOFactory.createEventDao().createEvent(event);
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("Echec de création de l'event"));
		}

		response.sendRedirect("/DAR/events?mode=actus&limit=15");

	}

	/**
	 *  Request : /DAR/events?mode=remove&id=#
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

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

	/**
	 *  Request : /DAR/events?mode=update&id=#[&name=#&privacy=#&description=#&date=#&theme=#&places=#&address=#]
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	@Deprecated
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Integer id = Integer.valueOf(request.getParameter("id"));
		try {
			Event event = DAOFactory.createEventDao().getEventById(id);
			event.setName((request.getParameter("name") != null) ? request.getParameter("name") : event.getName());
			if (request.getParameter("privacy") != null)
				event.setPrivacy(EventVisibility.stringToEventVisibility(request.getParameter("privacy")));
			event.setDescription((request.getParameter("description") != null) ? request.getParameter("description") : event.getDescription());
			event.setDate((request.getParameter("date") != null) ? request.getParameter("date") : event.getDateToString());
			event.setTheme((request.getParameter("theme") != null) ? request.getParameter("theme") : event.getTheme());
			event.setPlaces((request.getParameter("places") != null) ? request.getParameter("places") : String.valueOf(event.getPlaces()));
			event.setAddress((request.getParameter("address") != null) ? request.getParameter("address") : event.getAddress());
			DAOFactory.createEventDao().updateEvent(event);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("De la mise à jour de l'event"));
		}
	}

	/**
	 * Request : /DAR/events?mode=comment&id=#&content=#
	 * 
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */

	private void comment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		try {
			Integer id = Integer.valueOf(request.getParameter("id"));
			User user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
			Event event = DAOFactory.createEventDao().getEventById(id);
			List<Comment> comments = event.getComments();
			Comment comment = new Comment(user, Jsoup.clean(request.getParameter("content"), Whitelist.basic()));
			comments.add(comment);
			DAOFactory.createEventDao().updateEvent(event);
			request.getRequestDispatcher("/jsp/event.jsp?id=" + event.getId()).forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print(new Error("De la mise à jour de l'event"));
		}
	}
}
