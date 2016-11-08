package fr.upmc.dar.tests.mains;



import java.util.List;

import org.json.JSONException;

import fr.upmc.dar.api.UniversitySearch;
import fr.upmc.dar.api.helpers.YelpBusinessSearch;
import fr.upmc.dar.entities.University;

public class TestYelpApiV3 {

	public static void main(String[] args) throws JSONException {
//		String request = YelpBusinessSearch.searchBusiness("bar","Paris",3);
//	    JSONObject response = new JSONObject(request);
//	      System.out.println(response.toString());
	      
//	      JSONArray jsonarray = YelpBusinessSearch.searchBusinessToJSON("bar","Paris",3);
//	      List<String> x= YelpBusinessSearch.searchBuisnessIds("bar","Paris",5);
//	      
//	      System.out.println(x.size());
//	      
//	      if(x.size()>0)
//	      System.out.println(YelpBusinessSearch.idToBusiness(x.get(0)));
	      
//	     String requestBu = YelpBusinessSearch.getBusiness("little-red-door-paris");
//	     JSONObject responseBu=new JSONObject(requestBu);
//	     System.out.println(responseBu);
		
		/****** Test Enseignement *********/
		//System.out.println(UniversitySearch.searchUniversity("Pierre", 15));
		
		List<University> u = UniversitySearch.searchUniversityEntity("Pierre", 15);
		
		for(University c : u){
			System.out.println(c);
			System.out.println("---------------------");
		}
	}

}
