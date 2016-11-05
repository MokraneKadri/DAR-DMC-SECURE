package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.upmc.dar.dao.DAOFactory;
import fr.upmc.dar.dao.GroupDao;
import fr.upmc.dar.dao.interfaces.IGroupDao;
import fr.upmc.dar.entities.Group;
import fr.upmc.dar.enums.UriMapping;

@WebServlet(urlPatterns="/create_group")
public class GroupCreatorServlet extends HttpServlet {

	private static final long serialVersionUID = -2835816527011278470L;
	private static IGroupDao groupDao = DAOFactory.createGroupDao();
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(UriMapping.CREATE_GROUP.getRessourceUrl()).forward(request, response);
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String group_name = (String) request.getParameter("groupname");
		String group_description = (String) request.getParameter("groupdescription");
		int group_capacity = Integer.parseInt(request.getParameter("groupcapacity"));
		HttpSession session = request.getSession();
		String loggedinuser = (String) session.getAttribute("login");
		
		
		System.out.println(group_name);
		if (this.canCreateGroup(group_name)==false)//le nom du groupe est deja pris
		{  
			request.setAttribute("groupNotAvailable", true);
			request.getRequestDispatcher(UriMapping.CREATE_GROUP.getRessourceUrl()).forward(request, response);
		}
		else{
			
		
		Group grp = new Group();
		grp.setGroupDescription(group_description);
		grp.setGroupName(group_name);
		grp.setGroupeMaxCapacity(group_capacity);
		try {
			grp.setOwner(DAOFactory.createUserDao().findUserByUserName(loggedinuser));
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			groupDao.createGroup(grp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		}
	}
	
	
	public boolean  canCreateGroup(String grpName) {
		
		List<Group> res = groupDao.getGroupsByName(grpName);
		if(!res.isEmpty()) return false;
	
		return true ;
	}

}
