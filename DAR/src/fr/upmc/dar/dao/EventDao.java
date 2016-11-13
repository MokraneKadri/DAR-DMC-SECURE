package fr.upmc.dar.dao;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.upmc.dar.api.YelpBusinessSearch;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.entities.Business;
import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.tools.Localisation;

public class EventDao implements IEventDao {

	protected EntityManager em;

	protected final static String ALL_EVENTS = "SELECT events FROM Event events";
	protected final static String EVENTS_NAMED_BY = "SELECT events FROM Event events WHERE events.name=:name";
	protected final static String EVENTS_NAMED1_BY = "SELECT event FROM Event event WHERE event.name=:name";
	protected final static String EVENTS_DATED_BY = "SELECT events FROM Event events WHERE events.date=:date";
	protected final static String EVENTS_THEMED_BY = "SELECT events FROM Event events WHERE events.theme=:theme";
	protected final static String EVENTS_OWNED_BY = "SELECT events FROM Event events WHERE events.creator=:owner";
	protected final static String EVENTS_MEMBERED_BY = "SELECT events FROM Event events WHERE events.candidates=:member";
	protected final static String EVENTS_COMMENTS_BY_EVENTID = "SELECT events.comments FROM Event events WHERE events.id=:id";
	protected final static String ALL_EVENTS_NOT_ENDED = "SELECT events FROM Event events WHERE events.date>:date";
	//protected final static String EVENTS_COMMENTS_COUNT = "SELECT events.comments FROM Event events WHERE events.id=:id";


	public EventDao() {
		em = EMF.getInstance().getEntityManagerFactory().createEntityManager();
	}

	public EntityManager getEm(){
		if(em==null || !em.isOpen())
			return EMF.getInstance().getEntityManagerFactory().createEntityManager();
		return em;
	}

	@Override
	protected void finalize() throws Throwable {
		em.close();
		super.finalize();
	}

	@Override
	public void createEvent(Event event) {
		EntityManager en = getEm();
		try{
			en.getTransaction().begin();
			en.persist(event);
			en.getTransaction().commit();
		}catch(Exception e){}finally{en.close();}
	}

	@Override
	public List<Event> getAllEvents() {
		EntityManager en=getEm();
		List<Event> x =null;
		try{
			x= getGroupsFromQuery(en.createQuery(ALL_EVENTS));
		}catch(Exception e){}finally{en.close();}
		return x;
	}

	@Override
	public List<Event> getEventsByName(String name) {
		EntityManager en=getEm();
		List<Event> x =null;
		try{
			x= getGroupsFromQuery(en.createQuery(EVENTS_NAMED_BY).setParameter("name", name));
		}catch(Exception e){}finally{en.close();}
		return x;
	}

	@Override
	public Event getEventsByName1(String name) {
		EntityManager en=getEm();
		Event x =null;
		try{
			x= (Event) en.createQuery(EVENTS_NAMED1_BY).setParameter("name", name).getSingleResult();
		}catch(Exception e){}finally{en.close();}
		return x;
	}
	@Override
	public List<Event> getEventsByDate(String date) {
		EntityManager en=getEm();
		List<Event> x =null;
		try{
			x= getGroupsFromQuery(en.createQuery(EVENTS_DATED_BY).setParameter("date", date));
		}catch(Exception e){}finally{en.close();}
		return x;
	}

	@Override
	public List<Event> getEventsByTheme(String theme) {
		EntityManager en=getEm();
		List<Event> x =null;
		try{
			return getGroupsFromQuery(en.createQuery(EVENTS_THEMED_BY).setParameter("theme", theme));
		}catch(Exception e){}finally{en.close();}
		return x;
	}

	@Override
	public List<Event> getEventsByOwner(User owner) {
		EntityManager en=getEm();
		List<Event> x =null;
		try{
			return getGroupsFromQuery(en.createQuery(EVENTS_OWNED_BY).setParameter("owner", owner));
		}catch(Exception e){}finally{en.close();}
		return x;
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
		EntityManager en=getEm();
		List<Comment> x =null;
		try{
		x = (ArrayList<Comment>) en.createQuery(EVENTS_COMMENTS_BY_EVENTID).setParameter("id", eventId);
		}catch(Exception e){}finally{en.close();}
		return x;
	}

	@Override
	public void removeEvent(Event event) {
		EntityManager en=getEm();
		try{
		en.getTransaction().begin();
		en.remove(event);
		en.getTransaction().commit();
	}catch(Exception e){}finally{en.close();}
	}

	@Override
	public void updateEvent(Event event) {
		EntityManager en=getEm();
		try{
		en.getTransaction().begin();
		en.merge(event);
		en.getTransaction().commit();
	}catch(Exception e){}finally{en.close();}
	}

	@Override
	public Event getEventById(Integer id) {
		EntityManager en=getEm();
		Event event=null;
		try{
		en.getTransaction().begin();
		event = en.find(Event.class, id);
		en.getTransaction().commit();
	}catch(Exception e){}finally{en.close();}
		return event;
	}

	public List<Event> getEventsNear(double lat, double lon) {
		int offset = 0;
		List<Event> es;
		List<Event> res=new ArrayList<Event>();
		while ((es = getEventsNearIteration(offset, 100)).size() > 0)
		{
			try{
				for (Event e : es){
					if(e.getBusiness()!=null)
						if(Localisation.distance(lat,lon,Double.parseDouble(e.getBusiness().getLatitude()),Double.parseDouble(e.getBusiness().getLongitude()),"K")<5)
							res.add(e);
				}
			}catch(Exception e){};
			offset += es.size();
		}
		/*End of Request*/
		return res;
	}

	@SuppressWarnings("unchecked")
	private List<Event> getEventsNearIteration(int offset, int max)
	{
		EntityManager en=getEm();
		List<Event> x=null;
		try{
		x= en.createQuery(ALL_EVENTS_NOT_ENDED).setParameter("date", new Date()).setFirstResult(offset).setMaxResults(max).getResultList();
		}catch(Exception e){}finally{en.close();}
		return x;
	}

}
