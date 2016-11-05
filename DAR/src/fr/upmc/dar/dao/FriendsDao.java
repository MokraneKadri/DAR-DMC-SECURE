package fr.upmc.dar.dao;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import fr.upmc.dar.dao.interfaces.IFriendsDao;
import fr.upmc.dar.entities.Friends;
import fr.upmc.dar.entities.FriendsRequests;
import fr.upmc.dar.entities.User;

public class FriendsDao implements IFriendsDao {
	private EntityManager entityManager;


	protected final static String ALL_FRIENDS = "SELECT f FROM Friends f";
	protected final static String FRIENDS_OF = "SELECT f FROM Friends f WHERE f.user1=:user OR f.user2=:user";
	protected final static String FRIENDS_OF2 = "SELECT f FROM Friends f WHERE f.user1=:user1 AND f.user2=:user2";

	protected final static String FRIENDS_REQUESTS_OF = "SELECT fr FROM FriendsRequests fr WHERE fr.to=:to";
	protected final static String FRIENDS_REQUESTS = "SELECT fr1,fr2 FROM FriendsRequests fr1,fr2 WHERE fr1.from=:from AND fr2.to=:to";

	public FriendsDao() {
		entityManager = Persistence.createEntityManagerFactory("DAR").createEntityManager();
	}

	@Override
	public void createFriends(Friends friends) {
		entityManager.getTransaction().begin();
		entityManager.persist(friends);
		entityManager.getTransaction().commit();
	}

	@Override
	public void createFriends(User user1,User user2) {
		entityManager.getTransaction().begin();
		entityManager.persist(new Friends(user1,user2));
		entityManager.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friends> getFriendsOf(User user) {
		return entityManager.createQuery(FRIENDS_OF).setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friends> getFriendsOf(User user1,User user2) {
		return entityManager.createQuery(FRIENDS_OF).setParameter("user1", user1).setParameter("user2",user2).getResultList();
	}

	@Override
	public void removeFriends(Friends friends){
		entityManager.remove(friends);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friends> getAllFriends(){
		return entityManager.createQuery(ALL_FRIENDS).getResultList();
	}

	/****** REQUESTS *******/
	@Override
	public void createFriendsRequests(FriendsRequests friendsrequests) {
		entityManager.getTransaction().begin();
		entityManager.persist(friendsrequests);
		entityManager.getTransaction().commit();
	}

	@Override
	public void createFriendsRequests(User from,User to) {
		entityManager.getTransaction().begin();
		entityManager.persist(new FriendsRequests(from,to));
		entityManager.getTransaction().commit();
	}

	public FriendsRequests getFriendsRequest(User from,User to){
		return (FriendsRequests) entityManager.createQuery(FRIENDS_REQUESTS).setParameter("to", to).setParameter("from", from).getSingleResult();
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<FriendsRequests> getFriendsRequestsOf(User to) {
		return entityManager.createQuery(FRIENDS_REQUESTS_OF).setParameter("to", to).getResultList();
	}

	@Override
	public void removeFriendsRequests(FriendsRequests friendsRequests){
		entityManager.remove(friendsRequests);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Friends> getAllFriendsRequests(){
		return entityManager.createQuery(ALL_FRIENDS).getResultList();
	}

	public void  addFriend(String id1,String id2) throws Exception
	{
		entityManager.getTransaction().begin();
		UserDao dao=new UserDao();
		entityManager.persist(new Friends(dao.findUserById(Integer.parseInt(id1)),dao.findUserById(Integer.parseInt(id2))));
		entityManager.getTransaction().commit();

	}

	public void  removeFriend(String user1,String user2) throws Exception
	{
		//String sqlQuery = "DELETE FROM FRIENDS WHERE ((id1='"+user1+"' AND id2='"+user2+"') OR (id1='"+user2+"' AND id2='"+user1+"'));";
		UserDao dao=new UserDao();
//		User userOne = dao.findUserById(Integer.parseInt(user1));
//		User userTwo = dao.findUserById(Integer.parseInt(user2));

		User userOne = dao.findUserByUserName((user1));
		User userTwo = dao.findUserByUserName((user2));
		List<Friends> friends=this.getFriendsOf(userOne, userTwo);
		entityManager.getTransaction().begin();
		for(Friends fr : friends)
			entityManager.remove(fr);

		entityManager.getTransaction().commit();

	}
	@Override
	public boolean areFriends(String user1,String user2) throws NumberFormatException, Exception
	{
		UserDao dao=new UserDao();
		User userOne = dao.findUserByUserName((user1));
		User userTwo = dao.findUserByUserName((user2));

		String sql = "SELECT f FROM Friends f WHERE (f.user1=:user1 AND f.user2=:user2) OR (f.user1=:user2 AND f.user2=:user1)";
		@SuppressWarnings("unchecked")
		List<Friends> fr =(ArrayList<Friends>) entityManager.createQuery(sql).setParameter("user1", userOne).setParameter("user2",userTwo).getResultList();
		return (fr.size()>=1);
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Integer> myFriends(String user) throws NumberFormatException, Exception
	{
		//return entityManager.createQuery(FRIENDS_REQUESTS_OF).setParameter("to", to).getResultList();
		ArrayList<Integer> myFriends = new ArrayList<Integer>();
		int usi = Integer.parseInt(user);
		String uss = user;
		String sql1 = "SELECT f FROM Friends f WHERE f.user1.id=:user";
		String sql2 = "SELECT f FROM Friends f WHERE f.user2.id=:user";
		 Query q1 = entityManager.createQuery(sql1);
		 Query q2 = entityManager.createQuery(sql2);
		 
		 q1.setParameter("user", usi);
		 q2.setParameter("user", usi);
		 
		ArrayList<Friends> fr1 = (ArrayList<Friends>) q1.getResultList();
		ArrayList<Friends> fr2 = (ArrayList<Friends>) q2.getResultList();
		fr2.remove(fr1);
		ArrayList<Friends> fr =new ArrayList<Friends>();
		fr.addAll(fr1);
		fr.addAll(fr2);
		
		User u=new UserDao().findUserById(usi);
		for(Friends f : fr){
			User u1=f.getUser1();
			if(u1!=u)
				myFriends.add(u1.getId());
			else
				myFriends.add(f.getUser2().getId());
		}
			

		return myFriends;
	}

	public void  addRequest(String idfrom,String idto) throws Exception
	{
		entityManager.getTransaction().begin();
		UserDao dao=new UserDao();
		entityManager.persist(new FriendsRequests(dao.findUserById(Integer.parseInt(idfrom)),dao.findUserById(Integer.parseInt(idto))));
		entityManager.getTransaction().commit();

	}

	public void  removeRequest(String idfrom,String idto) throws Exception
	{
		//String sqlQuery = "DELETE FROM FRIENDREQUESTS WHERE ((idto='"+idto+"'AND idfrom='"+idfrom+"') OR (idto='"+idfrom+"'AND idfrom='"+idto+"'));";
		UserDao dao=new UserDao();
		FriendsRequests friendsRequests=this.getFriendsRequest(dao.findUserById(Integer.parseInt(idfrom)), dao.findUserById(Integer.parseInt(idto)));
		entityManager.getTransaction().begin();
		entityManager.remove(friendsRequests);
		entityManager.getTransaction().commit();

	}

	public ArrayList<Integer> pendingRequests(String user) throws Exception
	{
		ArrayList<Integer> pendingRequests=new ArrayList<Integer>();
		//String sqlQuery = "SELECT idfrom FROM FRIENDREQUESTS WHERE idto='"+user+"';";
		UserDao dao=new UserDao();
		User u = dao.findUserById(Integer.parseInt(user));
		
		List<FriendsRequests> fr=this.getFriendsRequestsOf(u);
		for(FriendsRequests x : fr)
			pendingRequests.add(x.getFrom().getId());

		return pendingRequests;	
	}

	public boolean isPending(String idfrom, String idto)
	{
		//String sql = "SELECT * FROM FRIENDREQUESTS WHERE (idfrom='"+idfrom+"' AND idto='"+idto+"');";
		FriendsRequests res=null;
		UserDao dao=new UserDao();
		try{
			res= this.getFriendsRequest(dao.findUserById(Integer.parseInt(idfrom)), dao.findUserById(Integer.parseInt(idto)));
		}catch(Exception e){
			
		}
		return (res!=null);

	}
	
	public JSONObject myFriendsJSON(String user) throws NumberFormatException, Exception
	{
		JSONObject myFriendsJSON=new JSONObject();
		JSONArray ids = new JSONArray();
		ArrayList<Integer> myFriends=this.myFriends(user);
		for(Integer i : myFriends){
			ids.put(i);
		}
		myFriendsJSON.put("friends",ids);
		return myFriendsJSON;
	}
	
	public JSONObject pendingRequestsJSON(String user) throws Exception
	{
		JSONObject pendingRequestsJSON=new JSONObject();
		JSONArray ids = new JSONArray();
		ArrayList<Integer> pending=this.pendingRequests(user);
		for(Integer i : pending){
			ids.put(i);
		}
		pendingRequestsJSON.put("requests",ids);
		return pendingRequestsJSON;
}

	public ArrayList<Integer> myFriendsArray(String userId) throws NumberFormatException, Exception {
		return this.myFriends(userId);
	}

	public ArrayList<Integer> pendingRequestsArray(String userId) throws Exception {
		return this.pendingRequests(userId);
	}

}
