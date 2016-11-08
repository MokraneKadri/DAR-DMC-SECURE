package fr.upmc.dar.tests.mains;



import java.util.List;

import org.json.JSONException;
import fr.upmc.dar.api.helpers.YelpBusinessSearch;

public class TestYelpApiV3 {

	public static void main(String[] args) throws JSONException {
//		String request = YelpBusinessSearch.searchBusiness("bar","Paris",3);
//	    JSONObject response = new JSONObject(request);
//	      System.out.println(response.toString());
	      
//	      JSONArray jsonarray = YelpBusinessSearch.searchBusinessToJSON("bar","Paris",3);
	      List<String> x= YelpBusinessSearch.searchBuisnessIds("bar","Paris",5);
	      
	      System.out.println(x.size());
	      
	      if(x.size()>0)
	      System.out.println(YelpBusinessSearch.idToBusiness(x.get(0)));
	      
//	     String requestBu = YelpBusinessSearch.getBusiness("little-red-door-paris");
//	     JSONObject responseBu=new JSONObject(requestBu);
//	     System.out.println(responseBu);
	}

}
