package fr.upmc.dar.api.entities;

import java.util.ArrayList;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.JsonObject;

import fr.upmc.dar.api.interfaces.IYelpParsingModel;

//classe qui represente un etablissement(bar,restaurant etc...
public class YelpEntityDataModel  {

	
	private String name;
	private String street;
	private String zipCode;
	private String city;
	private String phone;
	private String longitude ;
	private String latitude;
	private String establishmentWebsite;
	
	
	public YelpEntityDataModel(String name, String street, String zipCode, String city,String phone, String longitude, String latitude,
			String establishmentWebsite) {
		super();

		this.name = name;
		this.phone=phone;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.establishmentWebsite = establishmentWebsite;
	}


	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getEstablishmentWebsite() {
		return establishmentWebsite;
	}
	public void setEstablishmentWebsite(String establishmentWebsite) {
		this.establishmentWebsite = establishmentWebsite;
	}
	
	


	
}
