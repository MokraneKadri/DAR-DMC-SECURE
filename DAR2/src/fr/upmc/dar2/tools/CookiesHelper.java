package fr.upmc.dar2.tools;

import javax.servlet.http.Cookie;

public class CookiesHelper {

	
	public static Cookie buildCookie(String cookieName,String cookieValue,String domaine,String uriPath,int expiry,boolean isHttpOnly){
		
		Cookie result = new Cookie(cookieName,cookieValue);
		result.setMaxAge(expiry);
		result.setDomain(domaine);
		result.setPath(uriPath);
		result.setHttpOnly(true);
		return result;
		
	}
	
	
	
	
}
