package fr.upmc.dar.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.*;

import fr.upmc.dar.dao.FriendsDao;

@WebServlet(urlPatterns = "/friendsmanagement" )
public class FriendsManagementServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	private FriendsDao FriendsDao=new FriendsDao();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		doPost(request, response);
	}

	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		try{
			JSONObject jsResponse = new JSONObject();
			HttpSession session=request.getSession();
			response.setContentType("text/html");
			String userId = String.valueOf(session.getAttribute("idlogin"));
			String otherId = (String) request.getParameter("id");
			String typeOfRequest = (String) request.getParameter("typeOfRequest");
			if(otherId == null){
				jsResponse.put("error", "ID of the other user is empty or null");
			}else if(typeOfRequest.isEmpty() || typeOfRequest == null){
				jsResponse.put("error", "No type of request specified");
			}else if(userId == otherId){
				jsResponse.put("error", "You can't request or add yourself as a Friend");
			}else{
				switch(Integer.parseInt(typeOfRequest)){
				case 1 : // addFriend
					if(!FriendsDao.areFriends(userId, otherId)){
						if(FriendsDao.isPending(otherId,userId)){
							FriendsDao.addFriend(userId,otherId);
							FriendsDao.removeRequest(otherId, userId);
							FriendsDao.removeRequest(userId, otherId);
							jsResponse.put("success", "Friend added");
							response.sendRedirect("/DAR/jsp/profil.jsp");
						}else{
							jsResponse.put("success", "You don't have any request from this user");
							response.sendRedirect("/DAR/jsp/profil.jsp");
						}
					}else{
						jsResponse.put("success", "Already Friend");
					}
					break;
				case 2 : // removeFriend
					FriendsDao.removeFriend(userId,otherId);
					jsResponse.put("success", "Friend removed");
					response.sendRedirect("/DAR/jsp/profil.jsp");
					break;
				case 3 : // addRequest
					// From -> To
					if(!FriendsDao.areFriends(userId, otherId)){
						FriendsDao.addRequest(userId,otherId);
						jsResponse.put("success", "Friend request sent");
						response.sendRedirect("/DAR/jsp/profil.jsp");
					}else{
						jsResponse.put("success", "Already Friend");
						response.sendRedirect("/DAR/jsp/profil.jsp");
					}
					break;
				case 4 : // removeRequest
					//From -> To
					FriendsDao.removeRequest(otherId, userId);
					jsResponse.put("success","Request removed");
					response.sendRedirect("/DAR/jsp/profil.jsp");
					break ;
				default:
					jsResponse.put("error", "type of request unknown");
				}
			}
			response.getWriter().print(jsResponse);
		}catch (Exception e) 
		{
			System.out.println(e.getMessage());
			e.printStackTrace();
			request.setAttribute("error", e); //remote debug
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}
	}
}
