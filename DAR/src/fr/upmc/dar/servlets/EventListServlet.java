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

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.EventDao;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.UriMapping;
import fr.upmc.dar.tools.SignInValidator;


//@WebServlet(urlPatterns="/eventList")
public class EventListServlet  extends HttpServlet{


	protected  Map<String, String> eventsMap;
	protected Event event ;
	protected Gson gsonbuilder ;
	protected Gson gson;
	String res ;

	JSONObject jObject = new JSONObject();
	JSONArray jArray = new JSONArray();
	
	
	ArrayList<User> members;
	ArrayList<Event> events;
	ArrayList<Comment> comments;
	@Override
	public void init() throws ServletException {
		super.init();
		comments = new ArrayList<Comment>();
		members = new ArrayList<User>();
		events = new ArrayList<Event>();
		
		members.add(new User("Jean", "PIERRE", "JPIERRE","j.pierre@gmail.com", "123456789", "UPMC", "M2 Info"));
		members.add(new User("Raymond", "HUI", "RHUIT","r.hui@gmail.com", "123456789", "UPMC", "M2 Info"));
		members.add(new User("Cédric", "RIBEIRO","CEDROB", "c.ribeiro@gmail.com", "123456789", "UPMC", "M2 Info"));
		members.add(new User("Raoul", "CHEGMA","RCHEGMA", "r.chegma@gmail.com", "123456789", "UPMC", "M2 Info"));
		
		for (int i =0;i<4;i++){
			User owner = members.get(i);
			comments.add(new Comment(owner, "cette evenement est magnifique", "22/10/2016"));
			
			event = new Event(owner, "Boire un verre", "Ce soir tous au bar", "Vendredi", "Piccolage", "20", "Pas chez moi",comments);
			events.add(event);		
			
		}
		
		
		gson = new Gson();
		//Event e = new Event(new User("eee","hhhhh","hhhhh","hhhhh","ggggg","rfdssss","gggggg"), "gggggg", "ggggggg", "ggggggg", "gggggg", "gggggg", "jhjjj");
		//sonbuilder = new GsonBuilder().create();
		for(int j=0;j<4;j++){
		res = gson.toJson(events.get(j),Event.class);
		 JSONObject eventJson = new JSONObject();
		 eventJson.put("event", res);
		 
		 jArray.add(eventJson);
		
		}
		
		jObject.put("listEvents", jArray);
		res = jObject.toJSONString();
		System.out.println(res);
	

	}




	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		//request.setAttribute("nbEvents", events.keySet().size());
		request.getRequestDispatcher(UriMapping.EVENTSLIST.getRessourceUrl()).forward(request, response);

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		response.setContentType("application/json");
		PrintWriter pw = new PrintWriter(response.getOutputStream());
		
		pw.print(res);
		pw.flush();
	}
}
