package fr.upmc.dar2.dao.interfaces;

import java.util.List;

import fr.upmc.dar2.entities.Friends;
import fr.upmc.dar2.entities.FriendsRequests;
import fr.upmc.dar2.entities.User;

public interface IFriendsDao {
	public List<Friends> getFriendsOf(User user);
	public void createFriends(Friends friends);
	public void createFriends(User user1,User user2) ;
	public void removeFriends(Friends friends);
	List<Friends> getAllFriends();
	List<Friends> getFriendsOf(User user1, User user2);

	void createFriendsRequests(FriendsRequests friendsrequests);


	List<FriendsRequests> getFriendsRequestsOf(User user);

	List<Friends> getAllFriendsRequests();

	void removeFriendsRequests(FriendsRequests friendsRequests);

	void createFriendsRequests(User from, User to);
	boolean areFriends(String user1, String user2) throws NumberFormatException, Exception;
	List<Friends> getFriendsOfID(String user1, String user2);
	
}
