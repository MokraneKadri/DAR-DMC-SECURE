package fr.upmc.dar.dao.interfaces;

import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;

public interface IEventDao {

	

	public void creaeEvent( Event event ) throws Exception;


	public User findEventByEventName( String eventName ) throws Exception ;
}
