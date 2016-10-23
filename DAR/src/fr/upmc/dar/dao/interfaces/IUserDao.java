package fr.upmc.dar.dao.interfaces;

import fr.upmc.dar.entities.User;
import fr.upmc.dar.enums.LoginType;

public interface IUserDao {

	public void createUser( User utilisateur ) throws Exception;


	public User findUserByEmail( String email ) throws Exception ;

	public User findUserByUserName( String username ) throws Exception ;


	User findUserByCredantials(String login, String passwd, LoginType mailOrUserName) throws Exception;

}
