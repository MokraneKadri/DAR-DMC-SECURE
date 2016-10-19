package fr.upmc.dar.servlets;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.upmc.dar.entities.User;

@WebServlet("/connection")
public class Connection extends HttpServlet {

	private static final long serialVersionUID = 9084285697361335795L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("It works !!!");

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("DAR");
		EntityManager em = emf.createEntityManager();
		em.getTransaction().begin();
		User user = new User();
		user.setName("PIERRE");
		user.setFirstname("Jean");
		em.persist(user);
		em.getTransaction().commit();
		
	}

}
