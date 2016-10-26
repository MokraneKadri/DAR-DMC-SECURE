package fr.upmc.dar.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * 
 * Est exécuté à chaque appel à une page correspondant au pattern,
 * redirige vers le portail si on tente d'accéder à une page qui nécessite une
 * connexion.
 * 
 * @author Daniel
 *
 */

@WebFilter(urlPatterns="/restricted/*")
public class ConnectedModeFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpSession httpSession = httpRequest.getSession();
		String userId = (String) httpSession.getAttribute("userId");
		
		if (userId == null)
			httpRequest.getRequestDispatcher("/DAR/index.jsp");
		if (userId.equals(""))
			httpRequest.getRequestDispatcher("/DAR/index.jsp");
		
		chain.doFilter(request, response);
	}

}
