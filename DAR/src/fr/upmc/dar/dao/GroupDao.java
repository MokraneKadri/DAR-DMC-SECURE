package fr.upmc.dar.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import fr.upmc.dar.dao.interfaces.IGroupDao;
import fr.upmc.dar.entities.Group;
import fr.upmc.dar.entities.User;

/**
 * 
 * Object d'accès données pour les groupes. (Factorisé à mort) 
 * 
 * @author Daniel
 *
 */

public class GroupDao implements IGroupDao {

	protected EntityManager entityManager;
	
	protected final static String GROUPS_OWNED_BY = "SELECT grps FROM groups grps WHERE grps.owner=:user";
	protected final static String GROUPS_NAMED_BY = "SELECT grps FROM groups grps WHERE grps.groupName=:name";
	
	public GroupDao() {
		entityManager = EMF.getInstance().getEntityManagerFactory().createEntityManager();
	}
	
	@Override
	public void createGroup(Group group) throws Exception {
		entityManager.getTransaction().begin();
		entityManager.persist(group);
		entityManager.getTransaction().commit();
	}

	@Override
	public List<Group> getGroupsByName(String groupeName) {
		return getGroupsFromQuery(entityManager.createQuery(GROUPS_NAMED_BY).setParameter("name", groupeName));
	}

	@Override
	public Group getGroupById(Integer groupId) {
		return entityManager.find(Group.class, groupId);
	}
	
	@Override
	public List<Group> getGroupsOwnedBy(User user) {
		return getGroupsFromQuery(entityManager.createQuery(GROUPS_OWNED_BY).setParameter("user", user));
	}
	
	protected List<Group> getGroupsFromQuery(Query query) {
		@SuppressWarnings("unchecked")
		ArrayList<Group> groups = (ArrayList<Group>) query.getResultList();
		
		return groups;
	}

}
