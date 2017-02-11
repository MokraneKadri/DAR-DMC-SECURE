package fr.upmc.dar2.filters;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

import fr.upmc.dar2.enums.RestrictedAccesUris;
import fr.upmc.dar2.enums.UriMapping;

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








	/**
	 * les listes de lien accessible ou pas par 
	 * les utilisateurs est a mettre a jours au fure et a mesure 
	 * 
	 * 
	 * 
	 */

	String redirectURI = null ;

	//pour les user non connectés 


	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		redirectURI="";

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {


		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		HttpSession httpSession = httpRequest.getSession();

		//String requestedURI = httpRequest.getRequestURI();

		String login = (String) httpSession.getAttribute("login");

		//pour les user non connectés 
		ArrayList<String> nonConnectedUserRestrictedURLs = new ArrayList<String>();
		nonConnectedUserRestrictedURLs.add(RestrictedAccesUris.CREATEEVENT.getRessourceUrl());
		nonConnectedUserRestrictedURLs.add(RestrictedAccesUris.CREATEGROUP.getRessourceUrl());
		nonConnectedUserRestrictedURLs.add(RestrictedAccesUris.PROFIL.getRessourceUrl());
		nonConnectedUserRestrictedURLs.add(RestrictedAccesUris.FRIENDSLOOKUP.getRessourceUrl());
		nonConnectedUserRestrictedURLs.add(RestrictedAccesUris.MYFRIENDS.getRessourceUrl());

		//pour les user connectés (logique de navigation)
		ArrayList<String> ConnectedUserRestrictedURLs = new ArrayList<String>();
		ConnectedUserRestrictedURLs.add(RestrictedAccesUris.USERLOGIN.getRessourceUrl());
		ConnectedUserRestrictedURLs.add(RestrictedAccesUris.USERSIGNUP.getRessourceUrl());
		ConnectedUserRestrictedURLs.add("/DAR2/cookies/product.html");
		String urltoaccesp ;

		urltoaccesp=httpRequest.getRequestURI();
		System.out.println(urltoaccesp);
		if(httpRequest.getQueryString()!=null && httpRequest.getQueryString()!="")
		{ urltoaccesp = httpRequest.getRequestURI()+"?"+httpRequest.getQueryString();
		System.out.println(urltoaccesp);

		if(urltoaccesp.contains("/DAR2/events")  &&  !(httpRequest.getQueryString().contains("actus")||httpRequest.getQueryString().contains("search")))



			nonConnectedUserRestrictedURLs.add(urltoaccesp);			

		}
		//TODO Add cookies managment here
		if(login !=null && isAccesGranted4ConnectedUsers( urltoaccesp, ConnectedUserRestrictedURLs)!=true){

			//	String para = httpRequest.getParameter("mode");


			redirectURI = "/DAR2/events?mode=actus&limit=15";
			//request.getRequestDispatcher(redirectURI).forward(request, response);
			httpresponse.sendRedirect(redirectURI);// redirection 

			//System.out.println("redirecting to "+redirectURI);


		}
		else {


			if ((login ==null ||login =="")&& isAccesGranted4NonConnectedUsers( urltoaccesp, nonConnectedUserRestrictedURLs)!=true)
			{

				redirectURI = "/DAR2/signin";

				//request.getRequestDispatcher(redirectURI).forward(request, response);
				httpresponse.sendRedirect(redirectURI);// redirection 
			}

			else 
				chain.doFilter(request, response);
		}
		}

		public boolean isAccesGranted4ConnectedUsers(String urlToAcces,List<String> Restriction4connected){

			if (Restriction4connected.contains(urlToAcces)==true ){
				//user non connecté mais veut acceder au page restreinte -->non

				System.out.println("use already coo");
				return false;

			}
			return true;
		}

		public boolean isAccesGranted4NonConnectedUsers( String urlToAcces,List<String> Restriction4Nonconnected){
			//if(urlToAcces=="/DAR/events?mode=actus&limit=15") return true;
			if ( Restriction4Nonconnected.contains(urlToAcces)==true ){//
				//user connecté mais veut se reco ou se reinscrire -->non

				System.out.println("acces denied , user is not connected");
				return false;

			}
			//on est dans aucun des cas , dofilter
			return true ;

		}
	}