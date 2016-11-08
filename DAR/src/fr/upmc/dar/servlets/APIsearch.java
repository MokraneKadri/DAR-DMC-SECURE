package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.api.client.json.JsonObjectParser;
import com.google.gson.Gson;

import fr.upmc.dar.api.helpers.YelpBusinessSearch;
import fr.upmc.dar.entities.Business;

/**
 * Servlet implementation class APIsearch
 */
@WebServlet("/APIsearch")
public class APIsearch extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public APIsearch() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String lieu = request.getParameter("lieu");
		System.out.println("Recherche de : "+lieu);
		JSONObject res=new JSONObject();
		try {
			List<String> businessIds=YelpBusinessSearch.searchBuisnessIds(lieu, "Paris", 5);
			List<Business> bs=YelpBusinessSearch.idsToBusiness(businessIds);

			Gson gson = new Gson();
			JSONArray array=new JSONArray();
			String directRes="";
			for(Business b : bs){
				if(b!=null){
					JSONObject json=new JSONObject(gson.toJson(b));
					array.put(json);


					String h="Lu : "+b.normalizeHour(b.getOp0())+"-"+b.normalizeHour(b.getCl0());
					h+="| Ma : "+b.normalizeHour(b.getOp1())+"-"+b.normalizeHour(b.getCl1());
					h+="| Me : "+b.normalizeHour(b.getOp2())+"-"+b.normalizeHour(b.getCl2());
					h+="| Je : "+b.normalizeHour(b.getOp3())+"-"+b.normalizeHour(b.getCl3());
					h+="| Ve : "+b.normalizeHour(b.getOp4())+"-"+b.normalizeHour(b.getCl4());
					h+="| Sa : "+b.normalizeHour(b.getOp5())+"-"+b.normalizeHour(b.getCl5());
					h+="| Di : "+b.normalizeHour(b.getOp6())+"-"+b.normalizeHour(b.getCl6());

					directRes+="<div class=\"media-body\">"+
							"<h4 class=\"media-heading\"> "+b.getName()+"</h4>"+
							"<p class=\"text-right\"><b>Adresse </b>:" + b.getStreet() +"</p>"+
							"<p> <b>Code Postale</b>: " + b.getZipCode() +" </p>"+
							"<p> <b>Horaires </b>:"  +h + "</p>"+
							"<a href=\""+b.getEstablishmentWebsite()+" target=\"_blank\"> Voir </a>"+
							"<p class=\"text-right\"><a href=\"\" class=\"btn btn-primary\">Choisir</a> </p>"+
							"</div>";
				}
			}
			
			System.out.println("Envoie : "+array.length() +" | "+directRes.length());

			res.put("result", array);
			res.put("html", directRes);
			
			response.getWriter().println(res);

		} catch (JSONException e) {
			try {
				res.put("error", "Aucun Résultat");
				response.getWriter().println(res);
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
