package fr.upmc.dar2.tests.mains;

import java.io.IOException;
import java.util.ArrayList;

import org.json.JSONException;

import fr.upmc.dar2.api.helpers.EnsSuppApiQueryHelper;
import fr.upmc.dar2.api.helpers.EnsSuppDataParser;

public class TestEnsSuppApi {

	public static void main(String[] args) throws IOException, JSONException {
		

		ArrayList<String> lisEtabName = new ArrayList<String>();
		ArrayList<String> lisEtabZip = new ArrayList<String>();
		ArrayList<String> lisEtabCity = new ArrayList<String>();
		ArrayList<String> lisEtabAddr = new ArrayList<String>();
		String requestedurluniver="https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&rows=30&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.type_d_etablissement=Universit%C3%A9";
		String urlGrandeEtab="https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&rows=30&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.type_d_etablissement=Grand+%C3%A9tablissement";
		String urlecoleing  ="https://data.enseignementsup-recherche.gouv.fr/api/records/1.0/search/?dataset=fr-esr-principaux-etablissements-enseignement-superieur&rows=30&sort=uo_lib&facet=type_d_etablissement&facet=localisation&facet=com_nom&facet=dep_nom&facet=aca_nom&refine.localisation=%C3%8Ele-de-France&refine.localisation=%C3%8Ele-de-France%3EParis&refine.type_d_etablissement=%C3%89cole+d%27ing%C3%A9nieurs";
		
		
		EnsSuppApiQueryHelper en = new EnsSuppApiQueryHelper(requestedurluniver);
		
		EnsSuppDataParser parser = new EnsSuppDataParser(en);
		
		lisEtabName = (ArrayList<String>) parser.getEstablishmentsName();
		lisEtabAddr = (ArrayList<String>)parser.getEstablishmentAdress();
		lisEtabCity=(ArrayList<String>) parser.getEstablishmentCityName();
		lisEtabZip=(ArrayList<String>) parser.getEstablishmentZipCode();
		
		
		for(int i =0;i<lisEtabAddr.size();i++){
			System.out.println("etablissement N°:"+i+":"+lisEtabName.get(i)+" : "+ "adresse:" +lisEtabAddr.get(i) +","+lisEtabZip.get(i)+","+lisEtabCity.get(i));;
		}
	}

}
