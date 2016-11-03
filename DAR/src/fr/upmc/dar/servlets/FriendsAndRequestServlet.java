package fr.upmc.dar.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import fr.upmc.dar.dao.FriendsDao;
import fr.upmc.dar.dao.UserDao;

@WebServlet(urlPatterns = "/friendsandrequest" )
public class FriendsAndRequestServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private FriendsDao FriendsDao=new FriendsDao();
	private UserDao UserDao=new UserDao();
	public FriendsAndRequestServlet() {
		super();
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try{
			HttpSession session=request.getSession();
			response.setContentType("text/html");
			JSONObject js=new JSONObject();
			String userId = String.valueOf(session.getAttribute("idlogin"));
			String typeOfRequest = (String) request.getParameter("typeOfRequest");
			if(userId==null)
				js.put("error", "Not connected, no id found");
			else if(typeOfRequest==null)
				js.put("error", "No type of request specified");
			else{
				switch(Integer.parseInt(typeOfRequest)){
				case 1 :
					System.out.println("FriendsAndRequest :=> 1 ");
					js.put("result", UserDao.getUsersJSONProfileFromIds(FriendsDao.myFriendsArray(userId)));
					js.put("success", "Friends List");
					break;
				case 2 : // removeFriend
					System.out.println("FriendsAndRequest :=> 2 ");
					js.put("result", UserDao.getUsersJSONProfileFromIds(FriendsDao.pendingRequestsArray(userId)));
					js.put("success", "Pending Requests List");
					break;
				default:
					js.put("error", "type of request unknown");
				}
				response.getWriter().print(js);
			}
		}catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("error", e); //remote debug
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
