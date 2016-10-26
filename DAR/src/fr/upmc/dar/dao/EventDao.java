package fr.upmc.dar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;

public class EventDao implements IEventDao {

	protected EntityManager entityManager;
	
	protected final static String ALL_EVENTS = "SELECT events FROM Event events";
	protected final static String EVENTS_NAMED_BY = "SELECT events FROM Event events WHERE events.eventName=:name";
	protected final static String EVENTS_DATED_BY = "SELECT events FROM Event events WHERE events.eventDate=:date";
	protected final static String EVENTS_THEMED_BY = "SELECT events FROM Event events WHERE events.eventTheme=:theme";
	protected final static String EVENTS_OWNED_BY = "SELECT events FROM Event events WHERE events.creator=:owner";
	protected final static String EVENTS_MEMBERED_BY = "SELECT events FROM Event events WHERE events.candidates=:member";
	
	public EventDao() {
		entityManager = Persistence.createEntityManagerFactory("DAR").createEntityManager();
	}
	
	@Override
	public void createEvent(Event event) {
		entityManager.getTransaction().begin();
		entityManager.persist(event);
		entityManager.getTransaction().commit();
	}
	
	@Override
	public List<Event> getAllEvents() {
		return getGroupsFromQuery(entityManager.createQuery(ALL_EVENTS));
	}

	@Override
	public List<Event> getEventsByName(String name) {
		return getGroupsFromQuery(entityManager.createQuery(EVENTS_NAMED_BY).setParameter("name", name));
	}

	@Override
	public List<Event> getEventsByDate(String date) {
		return getGroupsFromQuery(entityManager.createQuery(EVENTS_DATED_BY).setParameter("date", date));
	}

	@Override
	public List<Event> getEventsByTheme(String theme) {
		return getGroupsFromQuery(entityManager.createQuery(EVENTS_THEMED_BY).setParameter("theme", theme));
	}

	@Override
	public List<Event> getEventsByOwner(User owner) {
		return getGroupsFromQuery(entityManager.createQuery(EVENTS_OWNED_BY).setParameter("owner", owner));
	}
	
	@Override
	@Deprecated
	public List<Event> getEventsByMember(User member) {
		List<Event> events = getAllEvents();
		
		for (int index = 0; index < events.size(); index++)
			if (!events.get(index).getCandidates().contains(member))
				events.remove(index);
		return events;
	}
	
	protected List<Event> getGroupsFromQuery(Query query) {
		@SuppressWarnings("unchecked")
		ArrayList<Event> events = (ArrayList<Event>) query.getResultList();
		
		return events;
	}

}
