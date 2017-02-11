package fr.upmc.dar2.api.helpers;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import fr.upmc.dar2.api.interfaces.IYelpParsingModel;;



public class YelpDataParser implements IYelpParsingModel {
	
	
	private  List<JSONObject> queryresult;
	private YelpApiQueryHelper yelpHelper;
	
	

	public YelpDataParser(YelpApiQueryHelper yelpHelper) {
		super();
		this.yelpHelper = yelpHelper;
		queryresult = new ArrayList<JSONObject>();
		
        ArrayList<String > businessList = new ArrayList<String>();
		businessList = (ArrayList<String>) this.yelpHelper.getEstablishmentIDList();
		// details de chaque etablissement sous format json
		queryresult = this.yelpHelper.getBuisnessDetailsAsJsonList(businessList);
	}


	public List<JSONObject> getQueryresult() {
		return queryresult;
	}


	public void setQueryresult(List<JSONObject> queryresult) {
		this.queryresult = queryresult;
	}


	public YelpApiQueryHelper getYelpHelper() {
		return yelpHelper;
	}


	public void setYelpHelper(YelpApiQueryHelper yelpHelper) {
		this.yelpHelper = yelpHelper;
	}


	
	public JSONObject getLocation(JSONObject obj) throws ParseException{

		 JSONParser parser = new JSONParser();
		 JSONObject res =  (JSONObject) parser.parse(getJsonFieldAsString(obj, "location"));
	
		return res;
		
	}
	
	
	
	
	public String getestablishmentPhone(JSONObject obj) {

		 return getJsonFieldAsString(obj, "phone");
	}
	
	
	@Override
	public String getJsonFieldAsString(JSONObject json, String fieldName) {
		String res ="";
		try{
		res =json.get(fieldName).toString();
		
		}catch(Exception e){
			res="inconnu";
		}
		return res;
	}


	@Override
	public String getFullName(JSONObject json) {
	 return getJsonFieldAsString(json, "name");
	}


	@Override
	public String getZipCode(JSONObject json) throws ParseException {
		String res; 
		try {
			res = getJsonFieldAsString(getLocation(json), "postal_code");
		} catch (Exception e) {
			res="inconnu";
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public String getURL(JSONObject json) {
		 return getJsonFieldAsString(json, "url");
	}

	
	@Override
	public String getStreetName(JSONObject json) throws ParseException {
		 String tmp = getJsonFieldAsString(getLocation(json), "address");
		 String res = tmp.replace("[\"","");
		 res = res.replace("\"]", "");
		 return res;
		 
	}


  
	@Override
	public String getCityName(JSONObject json) {
		
		
		String res; 
		try {
			res = getJsonFieldAsString(getLocation(json), "city");
		} catch (Exception e) {
			res="inconnu";
			e.printStackTrace();
		}
		return res;
		
	}

	@Override
	public String getLongitude(JSONObject json) throws ParseException {

		 JSONParser parser = new JSONParser();
		 JSONObject res =  (JSONObject) parser.parse(getJsonFieldAsString(getLocation(json), "coordinate"));
		return  getJsonFieldAsString(res, "longitude");
		
	}
	@Override
	public String getLatitude(JSONObject json) throws ParseException {
		
		 JSONParser parser = new JSONParser();
		 JSONObject res =  (JSONObject) parser.parse(getJsonFieldAsString(getLocation(json), "coordinate"));
		 
		 //System.out.println("coordinate :"+res.toJSONString());
		return  getJsonFieldAsString(res, "latitude");
	}
	
	
	
	
	
	
	
	
	
	

	
	@Override
	public List<String> getFullNameList() {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		//System.out.println("received list size "+json.size());
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			//System.out.println("element "+json.get(i).toJSONString());
			res.add(getFullName(json.get(i)));
			
		}
		return res;
		
	}
	@Override
	public List<String> getZipCodeList() throws ParseException {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getZipCode(json.get(i)));
			
		}
		return res;
	}
	@Override
	public List<String> getURLList() {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getURL(json.get(i)));
			
		}
		return res;}
	@Override
	public List<String> getStreetNameList() throws ParseException {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getStreetName(json.get(i)));
			
		}
		return res;
	}
	@Override
	public List<String> getCityNameList() {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getCityName(json.get(i)));
			
		}
		return res;
	}
	@Override
	public List<String> getLongitudeList() throws ParseException {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getLongitude(json.get(i)));
			
		}
		return res;
	}
	@Override
	public List<String> getLatitudeList() throws ParseException {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getLatitude(json.get(i)));
			
		}
		return res;
	}


	@Override
	public List<String> getPhoneNumbersList() throws ParseException {
		List<JSONObject> json = new ArrayList<JSONObject>();
		json = getQueryresult();
		List<String> res = new ArrayList<String>();
		for(int i =0;i<json.size();i++){
			res.add(getestablishmentPhone(json.get(i)));
			
		}
		return res;
	}

}
