package fr.upmc.dar2.api.interfaces;

import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;

public interface IYelpParsingModel {

	
	
	public String getJsonFieldAsString(JSONObject json, String fieldName);
	public String getFullName(JSONObject json);
	public String getZipCode(JSONObject json) throws ParseException;
	public String getURL(JSONObject json);
	public String getStreetName(JSONObject json) throws ParseException;
	public String getCityName(JSONObject json);
	public String getLongitude(JSONObject json) throws ParseException;
	public String getLatitude(JSONObject json) throws ParseException;
	
	
	
	public List<String> getFullNameList();
	public List<String> getZipCodeList() throws ParseException;
	public List<String> getURLList();
	public List<String> getStreetNameList() throws ParseException;
	public List<String> getCityNameList();
	public List<String> getLongitudeList() throws ParseException;
	public List<String> getLatitudeList() throws ParseException;
	public List<String> getPhoneNumbersList() throws ParseException;
}
