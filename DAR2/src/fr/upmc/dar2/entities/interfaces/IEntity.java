package fr.upmc.dar2.entities.interfaces;

import org.json.JSONException;
import org.json.JSONObject;


public interface IEntity {

	JSONObject toJSONObject() throws JSONException;
	
}
