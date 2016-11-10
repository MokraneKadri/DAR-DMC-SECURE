package fr.upmc.dar.dao.interfaces;

import java.util.ArrayList;
import java.util.Collection;

import org.json.JSONObject;

import fr.upmc.dar.entities.Event;
import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.LoginType;

public interface IUserDao {

	public void createUser( User utilisateur ) throws Exception;


	public User findUserByEmail( String email ) throws Exception ;

	public User findUserByUserName( String username ) throws Exception ;


	public User findUserByCredantials(String login, String passwd, LoginType mailOrUserName) throws Exception;
	
	public JSONObject getUsersJSONProfileFromIds(ArrayList<Integer> ids);

	
	public void updateUser(User user);
}
