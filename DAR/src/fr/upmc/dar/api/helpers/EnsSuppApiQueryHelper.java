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


public class EnsSuppApiQueryHelper {

	
   static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

   public String getuRLToQuery() {
	return uRLToQuery;
}





public void setuRLToQuery(String uRLToQuery) {
	this.uRLToQuery = uRLToQuery;
}



public String uRLToQuery ;


   
   
	public EnsSuppApiQueryHelper(String uRLToQuery) {
	super();
	this.uRLToQuery = uRLToQuery;
}


	
	

	public  JsonObject requestAndGetJsonResponse() throws IOException, JSONException{

		GenericUrl url = new GenericUrl(getuRLToQuery());
		HttpRequest request = HTTP_TRANSPORT.createRequestFactory().buildGetRequest(url);
		HttpResponse response = request.execute();
		InputStream str = response.getContent();
		String body = IOUtils.toString(str, "UTF-8");
		JsonParser parser = new JsonParser();
		JsonObject json = parser.parse(body).getAsJsonObject();
		return json;

	}



}