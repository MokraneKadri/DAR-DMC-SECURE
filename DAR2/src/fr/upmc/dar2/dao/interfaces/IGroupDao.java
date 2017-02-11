package fr.upmc.dar2.dao.interfaces;

import java.util.List;

import fr.upmc.dar2.entities.Group;
import fr.upmc.dar2.entities.User;

public interface IGroupDao {

	public void createGroup( Group group ) throws Exception;
	public Group getGroupById (Integer groupId);
	public List<Group> getGroupsByName( String groupeName );
	public List<Group> getGroupsOwnedBy (User user);
}
