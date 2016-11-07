package fr.upmc.dar.tests;

import java.util.ArrayList;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.EventDao;
import fr.upmc.dar.dao.UserDao;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.EventVisibility;

public class EventTests {
	
	User owner;
	ArrayList<User> members;
	ArrayList<Comment> comments;
	Event event;
	
	EventDao dao;
	
	@BeforeClass
	public static void reset() {
		Persistence.createEntityManagerFactory("JUnit").createEntityManager();
	}
	
	//@After
	public void cleanup() {
		Persistence.createEntityManagerFactory("JUnit").createEntityManager();
	}
	
	@Test
	public void nothing() {
		
	}
	
	//@Test
	public void createOwner() {
		owner = new User("Daniel", "RADEAU","D.RADEAU", "d.radeau@gmail.com", "123456789", "UPMC", "M2 Info");
	}
	
	//@Test
	public void createMembers() {
		members = new ArrayList<>();
		if (owner != null)
			members.add(owner);
		members.add(new User("Jean", "PIERRE", "JPIERRE","j.pierre@gmail.com", "123456789", "UPMC", "M2 Info"));
		members.add(new User("Raymond", "HUI", "RHUIT","r.hui@gmail.com", "123456789", "UPMC", "M2 Info"));
		members.add(new User("Cédric", "RIBEIRO","CEDROB", "c.ribeiro@gmail.com", "123456789", "UPMC", "M2 Info"));
		members.add(new User("Raoul", "CHEGMA","RCHEGMA", "r.chegma@gmail.com", "123456789", "UPMC", "M2 Info"));
		
		
	}
	public void createComments() {
	
	 comments = new ArrayList<Comment>();
	comments.add(new Comment(members.get(0), "cette evenement est magnifique"));
	comments.add(new Comment(members.get(2), "cette evenement est magnifique"));
	}
	
	//@Test
	public void createEvent() {
		createOwner();
		createMembers();
		event = new Event(owner, "Boire un verre", EventVisibility.GROUP,"Ce soir tous au bar", "Vendredi", "Piccolage", "20", "Pas chez moi",comments);
		event.setCandidates(members);
	}
	
	//@Test
	public void instanciateDao() {
		dao = new EventDao();
	}
	
	//@Test 
	public void persistEvent() {
		createEvent();
		instanciateDao();
		dao.createEvent(event);
	}
	
//	@Test
//	public void getEventsByName() {
//		persistEvent();
//		dao.getEventsByName(event.getEventName());
//	}
//	
//	@Test
//	public void getEventsByDate() {
//		persistEvent();
//		dao.getEventsByDate(event.getEventDate());
//	}
//	
//	@Test
//	public void getEventsByTheme() {
//		persistEvent();
//		dao.getEventsByTheme(event.getEventTheme());
//	}
//	
//	@Test
//	public void getEventsByOwner() {
//		persistEvent();
//		dao.getEventsByOwner(event.getCreator());
//	}
	
//	@Test
//	public void getEventsByMember() {
//		persistEvent();
//		dao.getEventsByMember(members.get(0));
//	}
	

}
