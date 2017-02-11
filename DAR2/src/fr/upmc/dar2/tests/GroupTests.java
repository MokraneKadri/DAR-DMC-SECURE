package fr.upmc.dar2.tests;

import java.util.ArrayList;

import javax.persistence.Persistence;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.upmc.dar2.dao.GroupDao;
import fr.upmc.dar2.dao.UserDao;
import fr.upmc.dar2.entities.Group;
import fr.upmc.dar2.entities.User;

/**
 * 
 * Test unitaires de la class Group et des quelques prérequis
 * 
 * @author Daniel
 *
 */

public class GroupTests {
	
	private static GroupDao dao;
	private static Group grp;
	private static User usr;
	
	@BeforeClass
	public static void reset() {
		Persistence.createEntityManagerFactory("JUnit").createEntityManager();
	}
	
	@Before
	public void initialize() {		
		dao = new GroupDao();
		grp = new Group();
		usr = new User();
		System.out.println("\n\n\n");
	}
	
	@After
	public void destroy() {
		Persistence.createEntityManagerFactory("JUnit").createEntityManager();
	}
	
	@Test
	public void nothing() {}
	
	@Test
	public void createOwner() {
		usr.setCursus("M2 STL");
		usr.seteMail("d.radeau@gmail.com");
		usr.setEtablissement("UPMC");
		usr.setFirstName("Daniel");
		usr.setPassword("123456789");
		usr.setUserName("RADEAU");
	}
		
	@Test
	public void putOwner() {
		createOwner();
		
		try {
			 new UserDao().createUser(usr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void insertGroup() {
		
		putOwner();
		
		ArrayList<User> members = new ArrayList<>();
		members.add(usr);
		
		grp.setGroupDescription("Groupe de test");
		grp.setGroupName("Groupe TEST");
		grp.setOwner(usr);
		grp.setMembers(members);
		
		try {
			dao.createGroup(grp);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void getGroupById() {
		insertGroup();
		Group g = dao.getGroupById(1);
		System.out.println(g.getGroupName());
		
		for (User u : g.getMembers()) {
			System.out.println(u.getFirstName());
			System.out.println(u.getUserName());
		}
		
		System.out.println();
	}
	
	@Test
	public void getGroupByOwner() {
		System.out.println("getGroupByOwner()");
		insertGroup();
		ArrayList<Group> grps = (ArrayList<Group>) dao.getGroupsOwnedBy(usr);
		
		System.out.println(grps.size());
		
		for (Group g : grps) {
			for (User u : g.getMembers()) {
				System.out.println(u.getFirstName());
				System.out.println(u.getUserName());
			}
		}
		
		System.out.println();
	}
	
	@Test
	public void getGroupByName() {
		System.out.println("getGroupByName()");
		insertGroup();
		ArrayList<Group> grps = (ArrayList<Group>) dao.getGroupsByName(grp.getGroupName());
		
		System.out.println(grps.size());
		
		for (Group g : grps) {
			for (User u : g.getMembers()) {
				System.out.println(u.getFirstName());
				System.out.println(u.getUserName());
			}
		}
		
		System.out.println();
	}
	
}
