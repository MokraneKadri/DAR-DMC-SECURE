package fr.upmc.dar.tests.mains;



import org.json.JSONException;
import org.json.JSONObject;

import fr.upmc.dar.api.helpers.YelpBusinessSearch;

public class TestYelpApiV3 {

	public static void main(String[] args) throws JSONException {
		String request = YelpBusinessSearch.searchBusiness("bar","Paris",3);
	    JSONObject response = new JSONObject(request);
	      System.out.println(response.toString());
	      
	     String requestBu = YelpBusinessSearch.getBusiness("little-red-door-paris");
	     JSONObject responseBu=new JSONObject(requestBu);
	     System.out.println(responseBu);
	}

}
