package fr.upmc.dar.tests;

import org.joda.time.DateTime;
import org.joda.time.ReadableInstant;

import com.google.maps.DirectionsApi;
import com.google.maps.DirectionsApiRequest;
import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.GeocodingApiRequest;
import com.google.maps.model.DirectionsRoute;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.TravelMode;

public class gapi {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyBaecIdNb-rbp-gzGV9q9f_v1gAeEjgF4c");
		GeocodingResult[] results;
		
		
		
		try {
			results = GeocodingApi.geocode(context,
			    "3 rue du général lambert 75007 paris").await();
		
	
			 DateTime now = new DateTime();
			 DirectionsRoute[] result = DirectionsApi.getDirections(context, "3,rue du general lambert 75007 paris",
				        "100 rue gabriel péri 93200").departureTime(now).mode(TravelMode.WALKING).await();
			 System.out.println(result[0].legs[0].startAddress);
			 System.out.println(result[0].legs[0].endAddress);
			 System.out.println(result[0].legs[0].duration);
			 System.out.println();
			// System.out.println(result[0].summary);
			 
			 String[] origin = new String[]{"100 rue gabriel péri 93200"};
			 String[] destination =new String[]{"3,rue du general lambert 75007 "};
			 DistanceMatrix matrix =
				        DistanceMatrixApi.getDistanceMatrix(context, origin, destination).mode(TravelMode.WALKING).await();

				    System.out.println( matrix.rows[0].elements[0].distance.humanReadable);
				    
				    System.out.println( matrix.rows[0].elements[0].duration.humanReadable);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
