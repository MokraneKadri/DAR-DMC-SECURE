package fr.upmc.dar.dao.interfaces;

import java.util.List;

import fr.upmc.dar.entities.Comment;
import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;

public interface IEventDao {
	
	public void createEvent(Event event);
	public List<Event> getAllEvents();
	public List<Event> getEventsByName(String name);
	public List<Event> getEventsByDate(String date);
	public List<Event> getEventsByTheme(String theme);
	public List<Event> getEventsByOwner(User owner);
	public List<Event> getEventsByMember(User member);
	public List<Comment> getCommentsList(int eventId);
	int getNbComments(int eventid);
}
