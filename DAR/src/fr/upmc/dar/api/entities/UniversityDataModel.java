package fr.upmc.dar.api.entities;



import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import fr.upmc.dar.api.helpers.EnseignementSuppApiQueryHelper;
import fr.upmc.dar.api.interfaces.IEstablishmentParsingModel;

public class UniversityDataModel implements IEstablishmentParsingModel {

	private EnseignementSuppApiQueryHelper EnsSupApi;
	private String name;
	private String street;
	private String zipCode;
	private String city;
	private String longitude ;
	private String latitude;
	private String establishmentWebsite;

	public UniversityDataModel(EnseignementSuppApiQueryHelper ensSupApi, String name, String street, String zipCode,
			String city, String longitude, String latitude, String establishmentWebsite) {
		super();
		EnsSupApi = ensSupApi;
		this.name = name;
		this.street = street;
		this.zipCode = zipCode;
		this.city = city;
		this.longitude = longitude;
		this.latitude = latitude;
		this.establishmentWebsite = establishmentWebsite;
	}
	
	
	@Override
	public String getJsonFieldAsString(JSONObject json, String fieldName) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getFullName(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getZipCode(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getURL(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getStreetName(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getCityName(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLongitude(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public String getLatitude(JSONObject json) {
		// TODO Auto-generated method stub
		return null;
	}
	public EnseignementSuppApiQueryHelper getEnsSupApi() {
		return EnsSupApi;
	}
	public void setEnsSupApi(EnseignementSuppApiQueryHelper ensSupApi) {
		EnsSupApi = ensSupApi;
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


	@Override
	public List<String> getFullNameList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getZipCodeList() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getURLList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getStreetNameList() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getCityNameList() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getLongitudeList() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getLatitudeList() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<String> getPhoneNumbersList() throws ParseException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	
	
}
