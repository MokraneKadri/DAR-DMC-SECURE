package fr.upmc.dar2.dao.interfaces;

import java.util.List;

import fr.upmc.dar2.entities.Comment;
import fr.upmc.dar2.entities.Event;
import fr.upmc.dar2.entities.User;

public interface IEventDao extends IDao<Event> {
	
	public void createEvent(Event event);
	public List<Event> getAllEvents();
	public List<Event> getEventsByName(String name);
	public List<Event> getEventsByDate(String date);
	public List<Event> getEventsByTheme(String theme);
	public List<Event> getEventsByOwner(User owner);
	public List<Event> getEventsByMember(User member);
	public List<Comment> getCommentsList(int eventId);
	int getNbComments(int eventid);
	public Event getEventsByName1(String name);
	void removeEvent(Event event);
	void updateEvent(Event event);
	Event getEventById(Integer id);
}
