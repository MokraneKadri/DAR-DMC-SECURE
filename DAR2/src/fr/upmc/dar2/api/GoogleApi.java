package fr.upmc.dar2.api;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.DistanceMatrixApiRequest;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.model.TravelMode;

public class GoogleApi {

	

	GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyAAWHzdrBHX4rFlhit9v57wDrSjVYfSUbA");
	GeocodingResult[] results;
	
	
	public String walkingDistance(String dertureAdd,String arrivalAddr) throws Exception{
		String[] origin = new String[5];//{"100 rue gabriel péri 93200"};
		 String[] destination =new String[5];//{"3,rue du general lambert 75007 "};
		 origin[0]=dertureAdd;
		 destination[0]=arrivalAddr;
		 DistanceMatrix matrix =
			        DistanceMatrixApi.getDistanceMatrix(context, origin, destination).mode(TravelMode.WALKING).await();

			    return matrix.rows[0].elements[0].distance.humanReadable;
			    
			   
	
	}
	public String walkingTime(String dertureAdd,String arrivalAddr) throws Exception{
		String[] origin = new String[5];//{"100 rue gabriel péri 93200"};
		 String[] destination =new String[5];//{"3,rue du general lambert 75007 "};
		 origin[0]=dertureAdd;
		 destination[0]=arrivalAddr;
		 DistanceMatrix matrix =
			        DistanceMatrixApi.getDistanceMatrix(context, origin, destination).mode(TravelMode.WALKING).await();

			    return matrix.rows[0].elements[0].duration.humanReadable;
			    
	
	}


	public String walkingTimeLongLat(double latsrc,double longsrc,double latdest,double longdest)  throws Exception{
		String[] origin = new String[5];//{"100 rue gabriel péri 93200"};
		 String[] destination =new String[5];//{"3,rue du general lambert 75007 "};
		
		 LatLng dest = new LatLng(latdest, longdest);
		 LatLng src = new LatLng(latsrc, longsrc);
		
		 GeocodingApiRequest request = new GeocodingApiRequest(context);
		    request.latlng(src);
		    GeocodingResult[] result = request.await();
		    origin[0]=result[0].formattedAddress;
		    request.latlng(dest);
		    GeocodingResult[] result1 = request.await();
		    destination[0]=result1[0].formattedAddress;
		    return walkingTime(origin[0], destination[0]);
	
	}
	
	
	public String walkingDistanceLongLat(double latsrc,double longsrc,double latdest,double longdest) throws Exception{
		String[] origin = new String[5];//{"100 rue gabriel péri 93200"};
		 String[] destination =new String[5];//{"3,rue du general lambert 75007 "};
		 LatLng dest = new LatLng(latdest, longdest);
		 LatLng src = new LatLng(latsrc, longsrc);
		
		 GeocodingApiRequest request = new GeocodingApiRequest(context);
		    request.latlng(src);
		    GeocodingResult[] result = request.await();
		    origin[0]=result[0].formattedAddress;
		    request.latlng(dest);
		    GeocodingResult[] result1 = request.await();
		    destination[0]=result1[0].formattedAddress;
		    return walkingDistance(origin[0], destination[0]);
	
	}
	public GoogleApi() {
		super();
	}
	
		

}
