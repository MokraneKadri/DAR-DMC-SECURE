package fr.upmc.dar.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.EventDao;
import fr.upmc.dar.dao.FriendsDao;
import fr.upmc.dar.dao.UserDao;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.dao.interfaces.IFriendsDao;
import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.EventVisibility;
import fr.upmc.dar.enums.UriMapping;
import fr.upmc.dar.tools.SignInValidator;


@WebServlet(urlPatterns="/eventList")
public class EventListServlet  extends HttpServlet{


	
	
	protected IFriendsDao friendship;
	protected IUserDao  user;
	protected IEventDao eventlist;
	
	protected Gson gson;
	String res ;
	
	JSONObject jObject = new JSONObject();
	JSONArray jArray = new JSONArray();
	
	
	ArrayList<User> members;
	ArrayList<Event> events;
	ArrayList<Event> visibleEvents;
	ArrayList<Comment> comments;
	@Override
	public void init() throws ServletException {
		super.init();
		comments = new ArrayList<Comment>();
		members = new ArrayList<User>();
		events = new ArrayList<Event>();
		
		eventlist = DAOFactory.createEventDao();
		user = DAOFactory.createUserDao();
		friendship = DAOFactory.createFriendDao();
		
		//on recupère tout les évents de la table
		
			}




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//request.setAttribute("nbEvents", events.keySet().size());
		request.getRequestDispatcher(UriMapping.EVENTSLIST.getRessourceUrl()).forward(request, response);

	}

	@SuppressWarnings("unchecked")
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String loggedinuser = (String) session.getAttribute("login");
		
		
		ArrayList<Event> tmpEvents =new ArrayList<Event>() ;
		tmpEvents= (ArrayList<Event>) eventlist.getAllEvents();
		System.out.println("total devent:"+tmpEvents.size());
		for(Event e : tmpEvents){
			if(e.getEventprivacy()==EventVisibility.PUBLIC){ // si l'evenement est public nporte qui peut le voir
				events.add(e);
			}
			if(e.getCreator().getUserName().equals(loggedinuser)&& !events.contains(e)){// on a acces a notre propore evenement qu'on a crée
				
				events.add(e);
				System.out.println("my own event");
			}
			
			// evenement privé --> lien d'amitié
			 if(e.getEventprivacy()==EventVisibility.PRIVATE){
				 String creator = e.getCreator().getUserName(); 
				
				System.out.println("creator is :"+creator +"loggedinuser is "+loggedinuser);
				try {
					if(friendship.areFriends(loggedinuser,creator)){
						events.add(e);
					} 
					else System.out.println("evenement privée on a pas acces");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		// evenement group --> lien dans le meme groupe?
		
		gson = new Gson();
		
		for(int j=0;j<events.size();j++){
			String tmp = gson.toJson(events.get(j),Event.class);
			JSONObject eventJson = new JSONObject();
			eventJson.put("event", tmp);
			
			jArray.add(eventJson);
			
		
		}
		
		System.out.println("nombre total d'evenement est:"+events.size());
		jObject.put("listEvents", jArray);
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
		res = jObject.toJSONString();
		String tt  = gson.toJson(jObject);
		System.out.println(res);
		System.out.println(tt);


		
		
		
		response.setContentType("application/json");
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		
		pw.print(res);
		pw.flush();
		res=null;
		jArray.clear();
		jObject.clear();
		events.clear();
		pw.close();
	}
}
