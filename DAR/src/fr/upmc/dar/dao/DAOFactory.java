package fr.upmc.dar.dao;

import javax.swing.Icon;

import fr.upmc.dar.dao.interfaces.IComment;
import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.dao.interfaces.IFriendsDao;
import fr.upmc.dar.dao.interfaces.IGroupDao;
import fr.upmc.dar.dao.interfaces.IUserDao;

public class DAOFactory {

	static UserDao udao;


	public static IUserDao createUserDao(){
		if(udao==null)
		return new UserDao();
		else return udao;
	}

	public static IEventDao createEventDao(){

		return new EventDao();
	}

	public static IGroupDao createGroupDao(){

		return new GroupDao();
	}
	
	public static IComment createUCommentDao(){

		return new CommentDao();
	}
	
	public static IFriendsDao createFriendDao(){

		return new FriendsDao();
	}
}
