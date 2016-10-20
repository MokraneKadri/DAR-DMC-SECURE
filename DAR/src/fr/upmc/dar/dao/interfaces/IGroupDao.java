package fr.upmc.dar.dao.interfaces;

import fr.upmc.dar.entities.Group;
import fr.upmc.dar.entities.User;

public interface IGroupDao {

	

	public void creaeGroup( Group group ) throws Exception;


	public User findGroupByGroupName( String groupeName ) throws Exception ;
}
