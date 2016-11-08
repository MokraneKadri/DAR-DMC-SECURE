package fr.upmc.dar.api;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Verb;

import fr.upmc.dar.entities.University;

public class UniversitySearch {

	static String SEARCH = "https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/"+
			"?dataset=fr-esr-principaux-etablissements-enseignement-superieur"+
			"&facet=uai&facet=type_d_etablissement&facet=secteur_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&facet=reg_nom&facet=pays_etranger_acheminement&facet=siret&facet=identifiant_grid&facet=statut_juridique_court&facet=qualification_long&facet=statut_operateur_lolf&facet=identifiant_programme_lolf_chef_de_file&refine.localisation=%C3%8Ele-de-France";
	
	
	public static String searchUniversity(String term,int limit){
		OAuthRequest request=new OAuthRequest(Verb.GET, SEARCH);
		request.addQuerystringParameter("q", term);
		request.addQuerystringParameter("sort", "-uo_lib");
		request.addQuerystringParameter("rows", String.valueOf(limit));
		Response response = request.send();
		return response.getBody();
	}
	
	public static JSONObject searchUniversityJSON(String term,int limit) throws JSONException{
		return new JSONObject(searchUniversity(term, limit));
	}
	
	public static List<University> searchUniversityEntity(String term,int limit) throws JSONException{
		List<University> uni=new ArrayList<University>();
		JSONObject query=searchUniversityJSON(term, limit);
		
		JSONArray unijson=query.getJSONArray("records");
		
		for(int i =0;i<unijson.length();++i){
			JSONObject university=unijson.getJSONObject(i);
			JSONObject fields=university.getJSONObject("fields");
			JSONArray coord=fields.getJSONArray("coordonnees");
			String longi=String.valueOf(coord.getDouble(0));
			String lati=String.valueOf(coord.getDouble(1));
			University u =new University(university.getString("recordid"), fields.getString("uo_lib"), fields.getString("adresse_uai"), fields.getString("code_postal_uai")
					, fields.getString("aca_nom"), longi,lati );
			
			uni.add(u);
		}
		
		return uni;
	}
	
	
	
	
}
