package fr.upmc.dar.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.upmc.dar.dao.GroupDao;
import fr.upmc.dar.entities.Group;
import fr.upmc.dar.enums.UriMapping;

@WebServlet(urlPatterns="/create_group")
public class GroupCreatorServlet extends HttpServlet {

	private static final long serialVersionUID = -2835816527011278470L;
	private static GroupDao groupDao = new GroupDao();
	
	

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher(UriMapping.CREATEGROUP.getRessourceUrl()).forward(request, response);
	
	}
	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String group_name = (String) request.getParameter("group_name");
		String group_description = (String) request.getParameter("group_description");
		
		if (	group_name.equals("") ||
				group_description.equals(""))
		{
			response.getWriter().println("Maybe some parameters empty");
		}
		
		Group grp = new Group();
		grp.setGroupDescription(group_description);
		grp.setGroupName(group_name);
		
		try {
			groupDao.createGroup(grp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		response.getWriter().println("Group successfully created");
	}

}
