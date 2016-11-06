package fr.upmc.dar.api.helpers;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

import fr.upmc.dar.api.interfaces.IEnseignementSuppParsingModel;

public class EnsSuppDataParser  implements IEnseignementSuppParsingModel {

	
	
	private EnsSuppApiQueryHelper ensHelper;
	
	private JsonArray receivedData;
	

	public EnsSuppDataParser(EnsSuppApiQueryHelper ensHelper) throws IOException, JSONException {
		super();
		this.ensHelper = ensHelper;
		this.receivedData= new JsonArray();
		this.receivedData = this.getEssentialData(this.ensHelper.requestAndGetJsonResponse());
	}

	public EnsSuppApiQueryHelper getEnsHelper() {
		return ensHelper;
	}

	public void setEnsHelper(EnsSuppApiQueryHelper ensHelper) {
		this.ensHelper = ensHelper;
	}

	public JsonArray getReceivedData() {
		return receivedData;
	}

	public void setReceivedData(JsonArray receivedData) {
		this.receivedData = receivedData;
	}

	public  List<String> getFieldsListUsingParams(JsonArray dataArray, String parent,String targetedField){


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

	public JsonArray getEssentialData(JsonObject json){ //renvoie le contenu de records
		return  json.getAsJsonArray("records");

	}


	// la list des nom d'etablissement d'enseignement supp
	@Override
	public  List<String> getEstablishmentsName(){
		return getFieldsListUsingParams(getReceivedData(), "fields", "uo_lib");

	}

	// le code postal d'etablissement d'enseignement supp
	@Override
	public  List<String> getEstablishmentZipCode(){
		return getFieldsListUsingParams(getReceivedData(), "fields", "com_code");

	}
	
	// le code postal d'etablissement d'enseignement supp
	@Override
		public  List<String> getEstablishmentCityName(){
			return getFieldsListUsingParams(getReceivedData(), "fields", "dep_nom");

		}
		
	// ladresse nom d'etablissement d'enseignement supp
	@Override
	public  List<String> getEstablishmentAdress(){
		return getFieldsListUsingParams(getReceivedData(), "fields", "adresse_uai");

	}

	
	
	
	
	
	
	

}
