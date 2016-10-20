package fr.upmc.dar.dao;

import fr.upmc.dar.dao.interfaces.IEventDao;
import fr.upmc.dar.dao.interfaces.IGroupDao;
import fr.upmc.dar.dao.interfaces.IUserDao;

public class DAOFactory {



	public static IUserDao createUserDao(){

		return new UserDao();
	}

	public static IEventDao createEventDao(){

		return new EventDao();
	}

	public static IGroupDao createGroupDao(){

		return new GroupDao();
	}
}
