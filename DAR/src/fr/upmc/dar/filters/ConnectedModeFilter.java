package fr.upmc.dar.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.upmc.dar.enums.RestrictedAccesUris;

/**
 * 
 * Est exécuté à chaque appel à une page correspondant au pattern,
 * redirige vers le portail si on tente d'accéder à une page qui nécessite une
 * connexion.
 * 
 * @author Daniel
 *
 */

@WebFilter(urlPatterns = {"/*"} )
public class ConnectedModeFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		HttpServletResponse httpresponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();

		String requestedURI = httpRequest.getRequestURI();

		String login = (String) httpSession.getAttribute("login");

		System.out.println("filtering ....");

		System.out.println("request for ...."+requestedURI);
		System.out.println("session "+login);


		//filtre des utilisateurs non connectés
		if ((login == null || login=="")&&
				(requestedURI.equals(RestrictedAccesUris.CREATEEVENT.getRessourceUrl())|| requestedURI.equals(RestrictedAccesUris.CREATEGROUP.getRessourceUrl()))){

			httpresponse.sendRedirect("/DAR/signin");

		}

		//filtre des utilisateurs connectés

		else if ((login != null )&&
				(requestedURI.equals(RestrictedAccesUris.USERLOGIN.getRessourceUrl())|| requestedURI.equals(RestrictedAccesUris.USERSIGNUP.getRessourceUrl()))){
			System.out.println("acces should be denied");
			httpresponse.sendRedirect("/DAR/home");

		}
		else  
			chain.doFilter(request, response);
	}
	
}