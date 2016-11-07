package fr.upmc.dar.api.helpers;


import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

public final class YelpBusinessSearch {


	public static String access_token="";
	public static String token_type= "Bearer";
	public static int expires_in=15551998;
	public static String generated="07/11/2016";

	public static String SEARCH = "https://api.yelp.com/v3/businesses/search";
	public static String BUSINESS = "https://api.yelp.com/v3/businesses/";
	
	
	public static OAuthService service=
	        new ServiceBuilder().provider(OAth2Helper.class).apiKey(access_token)
	            .apiSecret(access_token).build();
	
	private YelpBusinessSearch(){
		super();
	}
	
	public static void setToken(String token){
		access_token=token;
	}
	
	public static String searchBusiness(String term, String location,int limit){
		OAuthRequest request=new OAuthRequest(Verb.GET, SEARCH);
		request.addHeader("Authorization", token_type+" "+access_token);
	     request.addQuerystringParameter("term", term);
	     request.addQuerystringParameter("location", location);
	     request.addQuerystringParameter("limit", String.valueOf(limit));
	     Response response = request.send();
	     return response.getBody();
	}
	
	public static String getBusiness(String id){
		OAuthRequest request=new OAuthRequest(Verb.GET, BUSINESS+id);
		request.addHeader("Authorization", token_type+" "+access_token);
	     Response response = request.send();
	     return response.getBody();
	}
}

