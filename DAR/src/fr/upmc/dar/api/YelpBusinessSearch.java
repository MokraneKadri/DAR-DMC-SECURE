package fr.upmc.dar.api;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import fr.upmc.dar.api.helpers.OAth2Helper;
import fr.upmc.dar.dao.ApiDAO;
import fr.upmc.dar.entities.Business;

public final class YelpBusinessSearch {


	public static String access_token="x7ITo5nUoHqc2tgcj9m-3xYrlHVIfW1nfRkuwl5v4PscvlDOh7izAy_dMNfiz0jYK-_r5QGQLRn0GNXFpM5qX7cU6zZ8zGeat8IWRptjDP0x2I02asL6L7n6sXMOWHYx";
	public static String token_type= "Bearer";
	public static int expires_in=15551998;
	public static String generated="07/11/2016";

	public static String SEARCH = "https://api.yelp.com/v3/businesses/search";
	public static String BUSINESS = "https://api.yelp.com/v3/businesses/";

	private final static ApiDAO businessDAO =new ApiDAO(); 


	public static OAuthService service=
			new ServiceBuilder().provider(OAth2Helper.class).apiKey(access_token)
			.apiSecret(access_token).build();

	private YelpBusinessSearch(){
		super();
	}

	public static void setToken(String token){
		access_token=token;
	}

	public static String searchBusiness(String term,int limit){
		OAuthRequest request=new OAuthRequest(Verb.GET, SEARCH);
		request.addHeader("Authorization", token_type+" "+access_token);
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("limit", String.valueOf(limit));
		Response response = request.send();
		return response.getBody();
	}


	public static JSONArray searchBusinessToJSON(String term,String location,int limit) throws JSONException{
		OAuthRequest request=new OAuthRequest(Verb.GET, SEARCH);
		request.addHeader("Authorization", token_type+" "+access_token);
		request.addQuerystringParameter("term", term);
		request.addQuerystringParameter("location", location);
		request.addQuerystringParameter("limit", String.valueOf(limit));
		Response response = request.send();
		JSONObject json = new JSONObject(response.getBody());
		JSONArray businesses = (JSONArray) json.get("businesses");
		return businesses;
	}

	public static List<String> searchBuisnessIds(String term,String location,int limit) throws JSONException{
		ArrayList<String> Ids=new ArrayList<String>();
		JSONArray businesses = searchBusinessToJSON(term,location,limit);
		for(int i =0;i<businesses.length();++i){
			JSONObject res= businesses.getJSONObject(i);
			String id = res.getString("id");
			Ids.add(id);
		}
		return Ids;
	}

	public static String getBusiness(String id){
		OAuthRequest request=new OAuthRequest(Verb.GET, BUSINESS+id);
		request.addHeader("Authorization", token_type+" "+access_token);
		Response response = request.send();
		return response.getBody();
	}

	public static List<Business> idsToBusiness(List<String> ids) throws JSONException{
		List<Business> b=new ArrayList<Business>();

		for(String id : ids){
			Business bus=idToBusiness(id);
			if(bus!=null)
				b.add(bus);
		}

		return b;
	}

	public static Business idToBusiness(String id) throws JSONException{
		Business business=null;

		if((business=businessDAO.getBusiness(id))!=null){
			return business;
		}else{
			try{
				OAuthRequest request=new OAuthRequest(Verb.GET, BUSINESS+id);
				request.addHeader("Authorization", token_type+" "+access_token);
				Response response = request.send();
				JSONObject json = new JSONObject(response.getBody());

				JSONArray hours=json.getJSONArray("hours");
				JSONObject coordinates=json.getJSONObject("coordinates");
				JSONObject locationJSON=json.getJSONObject("location");
				String name=json.getString("name");
				String street=locationJSON.getString("address1");
				String zipCode=locationJSON.getString("zip_code");
				String city=locationJSON.getString("city");
				String phone=json.getString("phone");
				String longitude=coordinates.getString("longitude");
				String latitude=coordinates.getString("latitude");
				String establishmentWebsite=json.getString("url");

				business=new Business(id,name,street,zipCode,city,phone, longitude, latitude,
						establishmentWebsite);

				JSONObject res= hours.getJSONObject(0);

				if(res.getString("hours_type").toString().compareTo("REGULAR")==0){
					JSONArray open=res.getJSONArray("open");

					try{
						business.setOp0((open.getJSONObject(0)).getString("start"));
						business.setCl0((open.getJSONObject(0)).getString("end"));

						business.setOp1((open.getJSONObject(1)).getString("start"));
						business.setCl1((open.getJSONObject(1)).getString("end"));

						business.setOp2((open.getJSONObject(2)).getString("start"));
						business.setCl2((open.getJSONObject(2)).getString("end"));

						business.setOp3((open.getJSONObject(3)).getString("start"));
						business.setCl3((open.getJSONObject(3)).getString("end"));

						business.setOp4((open.getJSONObject(4)).getString("start"));
						business.setCl4((open.getJSONObject(4)).getString("end"));

						business.setOp5((open.getJSONObject(5)).getString("start"));
						business.setCl5((open.getJSONObject(5)).getString("end"));

						business.setOp6((open.getJSONObject(6)).getString("start"));
						business.setCl6((open.getJSONObject(6)).getString("end"));
					}catch(Exception e){
						businessDAO.addBusiness(business);
					}
				}
				businessDAO.addBusiness(business);
			}catch(Exception e){
				return null;
			}
			return business;
		}
	}

	public static Business getBusinessObject(String id) throws JSONException{
		Business business=null;

		try{
			OAuthRequest request=new OAuthRequest(Verb.GET, BUSINESS+id);
			request.addHeader("Authorization", token_type+" "+access_token);
			Response response = request.send();
			JSONObject json = new JSONObject(response.getBody());

			JSONArray hours=json.getJSONArray("hours");
			JSONObject coordinates=json.getJSONObject("coordinates");
			JSONObject locationJSON=json.getJSONObject("location");
			String name=json.getString("name");
			String street=locationJSON.getString("address1");
			String zipCode=locationJSON.getString("zip_code");
			String city=locationJSON.getString("city");
			String phone=json.getString("phone");
			String longitude=coordinates.getString("longitude");
			String latitude=coordinates.getString("latitude");
			String establishmentWebsite=json.getString("url");

			business=new Business(id,name,street,zipCode,city,phone, longitude, latitude,
					establishmentWebsite);

			JSONObject res= hours.getJSONObject(0);

			if(res.getString("hours_type").toString().compareTo("REGULAR")==0){
				JSONArray open=res.getJSONArray("open");

				try{
					business.setOp0((open.getJSONObject(0)).getString("start"));
					business.setCl0((open.getJSONObject(0)).getString("end"));

					business.setOp1((open.getJSONObject(1)).getString("start"));
					business.setCl1((open.getJSONObject(1)).getString("end"));

					business.setOp2((open.getJSONObject(2)).getString("start"));
					business.setCl2((open.getJSONObject(2)).getString("end"));

					business.setOp3((open.getJSONObject(3)).getString("start"));
					business.setCl3((open.getJSONObject(3)).getString("end"));

					business.setOp4((open.getJSONObject(4)).getString("start"));
					business.setCl4((open.getJSONObject(4)).getString("end"));

					business.setOp5((open.getJSONObject(5)).getString("start"));
					business.setCl5((open.getJSONObject(5)).getString("end"));

					business.setOp6((open.getJSONObject(6)).getString("start"));
					business.setCl6((open.getJSONObject(6)).getString("end"));
				}catch(Exception e){
				}
			}
		}catch(Exception e){
			return null;
		}
		return business;
	}
	
	public static void updateBusiness(String id) throws JSONException{
		try{
		Business b = getBusinessObject(id);
		businessDAO.updateBusiness(b);
		}catch(Exception e){};
	}

}


