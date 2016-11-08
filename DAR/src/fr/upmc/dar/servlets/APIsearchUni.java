package fr.upmc.dar.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import fr.upmc.dar.api.UniversitySearch;
import fr.upmc.dar.api.YelpBusinessSearch;
import fr.upmc.dar.dao.ApiDAO;
import fr.upmc.dar.entities.University;

/**
 * Servlet implementation class APIsearchUni
 */
@WebServlet("/APIsearchUni")
public class APIsearchUni extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */

	ApiDAO apiDAO=new ApiDAO();
	public APIsearchUni() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String term = request.getParameter("term");
		JSONObject res=new JSONObject();
		try{
			String directRes="";
			String htmltab = "<table class=\"table table-hover\">"+
					"<thead><tr><th>Etablissement </th><th>Adresse</th><th>Code Postal</th><th>Action</th>"+
					"</tr></thead><tbody>";
			directRes+=htmltab;
			try{
				List<University> us =UniversitySearch.searchUniversityEntity(term, 15);
				for(University u : us){
					try{
						apiDAO.addUniversity(u);
					}catch(Exception e){};
					directRes+="+<tr><td>"+u.getName()+"</td><td>"+u.getStreet()+"</td><td>"+u.getZipCode()+"</td><td><a href=\"\" class=\"btn btn-primary\">Choisir</a></tr>";
				}
			}catch(Exception e){System.out.println(e);};
			directRes+="</tbody> </table> ";
			res.put("html", directRes);
			response.getWriter().println(res);
		}catch(Exception e){
			System.out.println(e);
			try {
				res.put("error", "Aucun Résultat");
			} catch (JSONException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				response.getWriter().println(res);
			}
			response.getWriter().println(res);
		};
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
