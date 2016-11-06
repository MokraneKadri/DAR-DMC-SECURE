package fr.upmc.dar.api.interfaces;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public interface IEnseignementSuppParsingModel {

	
	
	//l'api renvoie un tabeau nommé records
	//qui contient  un ensemble d'objet nommés fields
	public   List<String> getFieldsListUsingParams(JsonArray dataArray,
			String parent,String targetedField);
	
	public   JsonArray getEssentialData(JsonObject json);
	
	public   List<String> getEstablishmentAdress();
	public   List<String> getEstablishmentCityName();
	public   List<String> getEstablishmentZipCode();
	public   List<String> getEstablishmentsName();
}
