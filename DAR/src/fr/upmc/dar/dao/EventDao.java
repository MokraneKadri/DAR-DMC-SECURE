package fr.upmc.dar.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;

public class EventDao implements IEventDao {

	protected EntityManager entityManager;

	protected final static String ALL_EVENTS = "SELECT events FROM Event events";
	protected final static String EVENTS_NAMED_BY = "SELECT events FROM Event events WHERE events.name=:name";
	protected final static String EVENTS_NAMED1_BY = "SELECT event FROM Event event WHERE event.name=:name";
	protected final static String EVENTS_DATED_BY = "SELECT events FROM Event events WHERE events.date=:date";
	protected final static String EVENTS_THEMED_BY = "SELECT events FROM Event events WHERE events.theme=:theme";
	protected final static String EVENTS_OWNED_BY = "SELECT events FROM Event events WHERE events.creator=:owner";
	protected final static String EVENTS_MEMBERED_BY = "SELECT events FROM Event events WHERE events.candidates=:member";
	protected final static String EVENTS_COMMENTS_BY_EVENTID = "SELECT events.comments FROM Event events WHERE events.id=:id";
	//protected final static String EVENTS_COMMENTS_COUNT = "SELECT events.comments FROM Event events WHERE events.id=:id";


	public EventDao() {
		entityManager = EMF.getEntityManagerFactory().createEntityManager();
	}

	@Override
	protected void finalize() throws Throwable {
		entityManager.close();
		super.finalize();
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
	public Event getEventsByName1(String name) {
		return (Event) entityManager.createQuery(EVENTS_NAMED1_BY).setParameter("name", name).getSingleResult();
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

	@Override
	public int getNbComments(int eventid) {
		return getCommentsList(eventid).size();
	}

	@Override
	public List<Comment> getCommentsList(int eventId) {
		ArrayList<Comment> comments = (ArrayList<Comment>) entityManager.createQuery(EVENTS_COMMENTS_BY_EVENTID).setParameter("id", eventId);
		return comments;
	}

	@Override
	public void removeEvent(Event event) {
		entityManager.getTransaction().begin();
		entityManager.remove(event);
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateEvent(Event event) {
		entityManager.getTransaction().begin();
		entityManager.merge(event);
		entityManager.getTransaction().commit();
	}

	@Override
	public Event getEventById(Integer id) {
		entityManager.getTransaction().begin();
		Event event = entityManager.find(Event.class, id);
		entityManager.getTransaction().commit();
		return event;
	}

}
