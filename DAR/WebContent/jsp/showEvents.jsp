<%@page import="fr.upmc.dar.dao.EventDao"%>
<%@page import="java.util.ArrayList"%>
<%@page import="fr.upmc.dar.entities.Event"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<%
	EventDao eventDao = new EventDao();
	ArrayList<Event> events = (ArrayList<Event>) eventDao.getAllEvents();	
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>All events</title>
</head>
<body>
	<%
		StringBuilder content = new StringBuilder(); 
	
		content.append("<table>");
		
		content.append("<tr>");
		
		content.append("<th>");
		content.append("ID");
		content.append("</th>");
		
		content.append("<th>");
		content.append("NAME");
		content.append("</th>");
		
		content.append("<th>");
		content.append("OWNER");
		content.append("</th>");
		
		content.append("<th>");
		content.append("DESCRIPTION");
		content.append("</th>");	
		
		content.append("<th>");
		content.append("DATE");
		content.append("</th>");
		
		content.append("<th>");
		content.append("THEME");
		content.append("</th>");
		
		content.append("<th>");
		content.append("PLACE");
		content.append("</th>");
		
		content.append("<th>");
		content.append("ADRESSE");
		content.append("</th>");
		
		content.append("</tr>");
		
		for (Event event : events) {
			content.append("<tr>");
			
			content.append("<th>");
			content.append(event.getEventName());
			content.append("</th>");
			
			content.append("<th>");
			content.append(event.getCreator());
			content.append("</th>");
			
			content.append("<th>");
			content.append(event.getEventDescription());
			content.append("</th>");	
			
			content.append("<th>");
			content.append(event.getEventDate());
			content.append("</th>");
			
			content.append("<th>");
			content.append(event.getEventTheme());
			content.append("</th>");
			
			content.append("<th>");
			content.append(event.getEventPlace());
			content.append("</th>");
			
			content.append("<th>");
			content.append(event.getEventAdresse());
			content.append("</th>");
			
			content.append("</tr>");
		}
		
		content.append("</table>");
	%>
</body>
</html>