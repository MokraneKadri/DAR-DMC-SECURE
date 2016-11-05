package fr.upmc.dar.tools;

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
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class EnseignementSuppApiQuery {
	
	public static final String URL_BASE =
			"https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur";
	
	public static final String FACET="uai";
	
	public String refines ="nom_departement";

	
	static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
	
	public static String requestAndGetJsonResponse(String requestedurl) throws IOException, JSONException{
		
		GenericUrl url = new GenericUrl(requestedurl);
        HttpRequest request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(url);
        HttpResponse response = request.execute();
        InputStream str = response.getContent();
       
 
        String encoding = response.getContentEncoding();
        System.out.println(encoding);
        encoding = encoding == null ? "UTF-8" : encoding;
        String body = IOUtils.toString(str, "UTF-8");
       return body;
		
	}
	
	public static String printPrettyJson(String obj){
		
		  JsonParser parser = new JsonParser();
	      JsonObject json = parser.parse(obj).getAsJsonObject();

	      Gson gson = new GsonBuilder().setPrettyPrinting().create();
	      String prettyJson = gson.toJson(json);

		return prettyJson.toString();
	}
	
	
	
	public static void main(String[] args) throws IOException, JSONException {
		
   String res =  requestAndGetJsonResponse("https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.com_nom=Paris+2e");
   System.out.println(printPrettyJson(res));
	
       
    
}
}