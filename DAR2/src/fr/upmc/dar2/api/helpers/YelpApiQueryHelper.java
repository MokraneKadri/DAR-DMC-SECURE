package fr.upmc.dar2.api.helpers;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Token;
import org.scribe.oauth.OAuthService;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.scribe.model.Response;
import org.scribe.model.Verb;



public class YelpApiQueryHelper {

	
	
	  private static final String API_HOST = "api.yelp.com";
	 // private static final String DEFAULT_TERM = "bar";
	  //private static final String DEFAULT_LOCATION = "Paris";
	 // private static final int  SEARCH_LIMIT = 10;
	  private static final String SEARCH_PATH = "/v2/search";
	  private static final String BUSINESS_PATH = "/v2/business";
	  private int nb_result;
	  private String search_term;
	  private String search_location;
	  
	
	/*
	   * Tokens d'accres permettant d'interroger l'api rest de YELP
	   */
	  private static final String CONSUMER_KEY = "qRkF28tnFUoGH9I9XAy4wQ";
	  private static final String CONSUMER_SECRET = "bBhMnT8Yt3_SsCK3V6-dR938Rj8";
	  private static final String TOKEN = "D77sao3tqP30hhJaWk79RE-ng0KBJiRT";
	  private static final String TOKEN_SECRET = "cAxo736Td-HJPW0kKc4kEcmR2ds";


	   public OAuthService service; 
	   public Token accessToken;

	   public YelpApiQueryHelper(String searchterm, String location,int limit) {
		   this.service =
			        new ServiceBuilder().provider(OAth2Helper.class).apiKey(CONSUMER_KEY)
			            .apiSecret(CONSUMER_SECRET).build();
			    this.accessToken = new Token(TOKEN, TOKEN_SECRET);
			    this.search_location= location;
			    this.search_term=searchterm;
			    this.nb_result=limit;
		
	}

	   
	   
	   
	  /**
	   * Setup the Yelp API OAuth credentials.
	   *
	   * @param consumerKey  clef client
	   * @param consumerSecret mdp client 
	   * @param token Token
	   * @param tokenSecret Token mdp
	   */
	
	   
	   public YelpApiQueryHelper(String consumerKey, String consumerSecret, String token, String tokenSecret ,int nbresult) {
		    this.service =
		        new ServiceBuilder().provider(OAth2Helper.class).apiKey(consumerKey)
		            .apiSecret(consumerSecret).build();
		    this.accessToken = new Token(token, tokenSecret);
		    this.nb_result = nbresult;
		  }
	   
	   
	   /**créér et envoie la requet a l'api de recherche
	    * 
	    * requete parametré par :
	    * 
	    * 
	    * @param term : le terme recherché
	    * @param location : le lieu ciblé
	    * @return
	    */
	   private String searchForBusinessesByLocation(String term, String location) {
	     OAuthRequest request = createOAuthRequest(SEARCH_PATH);
	     request.addQuerystringParameter("term", term);
	     request.addQuerystringParameter("location", location);
	     request.addQuerystringParameter("limit", String.valueOf(getNb_result()));
	     return sendRequestAndGetResponse(request);
	   }

	   


	public int getNb_result() {
		return nb_result;
	}




	public void setNb_result(int nb_result) {
		this.nb_result = nb_result;
	}




	/**
	    *crér et envoie la request à la  Business API en lui passant 
	    *
	    *en param le business ID de l'etablissement.
	    */
	   private String searchByBusinessId(String businessID) {
	     OAuthRequest request = createOAuthRequest(BUSINESS_PATH + "/" + businessID);
	     return sendRequestAndGetResponse(request);
	   }

	   /**
	    * crée et renvoie   l' OAuthRequest à partir de l'url 
	    * specifié pour l'api .
	    */
	   private OAuthRequest createOAuthRequest(String path) {
	     OAuthRequest request = new OAuthRequest(Verb.GET, "https://" + API_HOST + path);
	     return request;
	   }
	   
	   
	   
	   /**
	    * envoie l'OAthRequest et recupère le corp de la réponse.
	    *
	    */
	   private String sendRequestAndGetResponse(OAuthRequest request) {
	     this.service.signRequest(this.accessToken, request);
	     Response response = request.send();
	     return response.getBody();
	   }

	   
	 
	   
	   
	   public List<String> getEstablishmentIDList(){
		   ArrayList<String> EstablishementsIDs = new ArrayList<String>();
		   
		   JSONArray queryResult = queryAPI(getSearch_term(), getSearch_location());
		   for(int i =0;i<queryResult.size();i++){
		    JSONObject firstBusiness = (JSONObject) queryResult.get(i);
		    String buisness = firstBusiness.get("id").toString();
		    
		    EstablishementsIDs.add(buisness);
		   }
		   return EstablishementsIDs;
   
	   }
	   
	   
	   public List<JSONObject> getBuisnessDetailsAsJsonList(List<String> buisnessesId){
		   
		   List<JSONObject> jsondetails = new ArrayList<JSONObject>();
		   for(int i=0;i<buisnessesId.size();i++){
			   
		   
		   String etab  = buisnessesId.get(i);
		   JSONParser parser = new JSONParser();
		   String detailedResponseJSON = this.searchByBusinessId(etab.toString());
    	   try {
     		JSONObject res =  (JSONObject) parser.parse(detailedResponseJSON);
     		jsondetails.add(res);
    	   } catch (ParseException e) {

			e.printStackTrace();
			

    	   }
		   }
		   return jsondetails;
	   }
	   
	   
	   
	   private  JSONArray queryAPI( String motClef,String endroit) {
		   
		   // on recupère la liste des etablissement qui match la requete
		    String searchResponseJSON =
		        this.searchForBusinessesByLocation(motClef, endroit);

		    // on parse le json 
		    JSONParser parser = new JSONParser();
		    JSONObject response = null;
		    try {
		      response = (JSONObject) parser.parse(searchResponseJSON);
		    } catch (ParseException pe) {
		      System.out.println("Error: could not parse JSON response:");
		     // System.out.println(searchResponseJSON);
		      System.exit(1);
		    }

		    JSONArray businesses = (JSONArray) response.get("businesses");
		    
		    return businesses;

		  }
	   
	   public String getSearch_term() {
			return search_term;
		}




		public void setSearch_term(String search_term) {
			this.search_term = search_term;
		}




		public String getSearch_location() {
			return search_location;
		}




		public void setSearch_location(String search_location) {
			this.search_location = search_location;
		}

	   
}
