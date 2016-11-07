package fr.upmc.dar.tests.mains;

import java.util.ArrayList;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.upmc.dar.api.helpers.YelpApiQueryHelper;
import fr.upmc.dar.api.helpers.YelpDataParser;
import fr.upmc.dar.api.entities.*;;

public class testYelpApi {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub

		
		YelpApiQueryHelper yelp = new YelpApiQueryHelper("bar","Paris",3);
	
		YelpDataParser parser = new YelpDataParser(yelp);
		ArrayList<YelpEntityDataModel> models = new ArrayList<YelpEntityDataModel>();
		
		
		
		
	for(int i =0;i<parser.getQueryresult().size();i++){
		System.out.println(parser.getQueryresult().get(1));
		 String name = parser.getFullNameList().get(i);
		 String street = parser.getStreetNameList().get(i);
		 String zipCode = parser.getZipCodeList().get(i);
		 String city  = parser.getZipCodeList().get(i);
		 String phone = parser.getPhoneNumbersList().get(i);
		 String longitude  = parser.getLongitudeList().get(i);
		 String latitude = parser.getLatitudeList().get(i);
		 String establishmentWebsite = parser.getURLList().get(i);
		 YelpEntityDataModel model = new YelpEntityDataModel(name, street, zipCode, city, phone, longitude, latitude, establishmentWebsite);
		 models.add(model);
		 
	}
	for(int i =0;i<models.size();i++){
		
		
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String obj = gson.toJson(models.get(i), YelpEntityDataModel.class);
		System.out.println(obj);
	}
		 
		 
	}
		 
		 
		
	
}
