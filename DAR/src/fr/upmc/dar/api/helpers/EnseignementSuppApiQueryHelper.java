package fr.upmc.dar.api.helpers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;


public class EnseignementSuppApiQueryHelper {

	
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

   public String uRLToQuery ;


   
   
	public EnseignementSuppApiQueryHelper(String uRLToQuery) {
	super();
	this.uRLToQuery = uRLToQuery;
}


	
	

	public static JsonObject requestAndGetJsonResponse(String requestedurl) throws IOException, JSONException{

		GenericUrl url = new GenericUrl(requestedurl);
		HttpRequest request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(url);
		HttpResponse response = request.execute();
		InputStream str = response.getContent();
		String body = IOUtils.toString(str, "UTF-8");
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(body).getAsJsonObject();
		return json;

	}



	public static JsonArray getEssentialData(JsonObject json){ //renvoie le contenu de records
		return  json.getAsJsonArray("records");

	}


	// la list des nom d'etablissement d'enseignement supp
	public static  List<String> getEstablishmentsName(JsonArray dataArray){
		return getFieldsListUsingParams(dataArray, "fields", "uo_lib");

	}

	// le code postal d'etablissement d'enseignement supp
	public static  List<String> getEstablishmentZipCode(JsonArray dataArray){
		return getFieldsListUsingParams(dataArray, "fields", "com_code");

	}
	
	// le code postal d'etablissement d'enseignement supp
		public static  List<String> getEstablishmentCityName(JsonArray dataArray){
			return getFieldsListUsingParams(dataArray, "fields", "dep_nom");

		}
		
	// ladresse nom d'etablissement d'enseignement supp
	public static  List<String> getEstablishmentAdress(JsonArray dataArray){
		return getFieldsListUsingParams(dataArray, "fields", "adresse_uai");

	}


	public static  List<String> getFieldsListUsingParams(JsonArray dataArray, String parent,String targetedField){


		List<String> liste = new ArrayList<String>();
		for (int i = 0; i < dataArray.size(); i++)
		{
			JsonObject post_id = dataArray.get(i).getAsJsonObject();//un resultats
			JsonObject elt = post_id.getAsJsonObject(parent);
			JsonPrimitive nometab = elt.getAsJsonPrimitive(targetedField);
			liste.add(nometab.toString());
		}
		return liste;

	}

	public static String printPrettyJson(JsonObject obj){


		//		JsonParser parser = new JsonParser();
		//	      JsonObject json = parser.parse(obj).getAsJsonObject();
		//      
		//      JsonObject objetsimple = json.getAsJsonObject("parameters");
		//      System.out.println(objetsimple);
		//      JsonObject  objet2 = objetsimple.getAsJsonObject("refine");
		//      JsonPrimitive  objet3 = objet2.getAsJsonPrimitive("com_nom");
		//      System.out.println(objet3);
		//      JsonArray pageName = json.getAsJsonArray("records");
		//      
		//      for (int i = 0; i < pageName.size(); i++)
		//      {
		//    	  JsonObject post_id = pageName.get(i).getAsJsonObject();//un resultats
		//    	  JsonObject elt = post_id.getAsJsonObject("fields");
		//    	  JsonPrimitive nometab = elt.getAsJsonPrimitive("uo_lib");
		//    	 
		//          System.out.println("universite "+nometab.toString());
		//      }

		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String prettyJson = gson.toJson(obj);

		return prettyJson.toString();
	}



	public static void main(String[] args) throws IOException, JSONException {

		ArrayList<String> lisEtabName = new ArrayList<String>();
		ArrayList<String> lisEtabZip = new ArrayList<String>();
		ArrayList<String> lisEtabCity = new ArrayList<String>();
		ArrayList<String> lisEtabAddr = new ArrayList<String>();
		String requestedurluniver="https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&rows=30&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.type_d_etablissement=Universit%C3%A9";
		String urlGrandeEtab="https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&rows=30&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.type_d_etablissement=Grand+%C3%A9tablissement";
		String urlecoleing  ="https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&rows=30&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.type_d_etablissement=%C3%89cole+d%27ing%C3%A9nieurs";
		JsonArray fields = getEssentialData(requestAndGetJsonResponse(requestedurluniver));
		lisEtabName = (ArrayList<String>) getEstablishmentsName(fields);
		lisEtabAddr = (ArrayList<String>) getEstablishmentAdress(fields);
		lisEtabCity=(ArrayList<String>) getEstablishmentCityName(fields);
		lisEtabZip=(ArrayList<String>) getEstablishmentZipCode(fields);
		for(int i =0;i<lisEtabAddr.size();i++){
			System.out.println("etablissement :"+lisEtabName.get(i)+" : "+ "adresse:" +lisEtabAddr.get(i) +","+lisEtabZip.get(i)+","+lisEtabCity.get(i));;
		}


	}
}