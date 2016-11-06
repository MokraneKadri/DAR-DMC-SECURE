<%@page import="fr.upmc.dar.entities.Comment"%>
<%@page import="fr.upmc.dar.enums.EventVisibility"%>
<%@page import="fr.upmc.dar.dao.interfaces.IEventDao"%>
<%@page import="fr.upmc.dar.entities.Event"%>
<%@page import="fr.upmc.dar.dao.DAOFactory"%>
<%@page import="fr.upmc.dar.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event</title>

<!--  Scripts  -->
<!-- JQuery  -->
<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<!-- Bootstrap  -->
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<!-- JQuery Validate  -->
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<!-- Fin  Scripts  -->

<!-- Styles  -->
<!-- bootstrap  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<!-- fontAwsome  -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<!-- style du footer  -->
<link rel="stylesheet" href="/DAR/assets/css/header.css">
<!-- style du header  -->
<link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
<!-- style du content  -->
<link rel="stylesheet" href="/DAR/assets/css/main.css">

</head>
<body>

	<%@include file="/jsp/header.jsp"%>

	<div class="maincontainer"></div>

	<%
		StringBuilder content = new StringBuilder();
		String id = request.getParameter("id");
		
		if (id == null) {
			content.append("<p> Nous sommes désolé mais l'événement en question n'a pas été trouvé </p>");
		} else {
			IEventDao dao = DAOFactory.createEventDao();
			Event event = dao.getEventById(Integer.valueOf(id));
			
			content.append("<div class='maincontainer'>");
			content.append("<div class='well'>");
			content.append("<div class='media'>");
			content.append("<div class='pull-left' href='#'>");
			content.append("<h4 class='media-heading'>");
			content.append("<a href='#'>");
			content.append(event.getEventName());
			content.append("</a>");
			content.append("</h4>");
			content.append("<p class='text-right'>créé par : ");
			content.append(event.getCreator().getUserName());
			content.append("</p>");
			content.append("</div>");
			content.append("<p class='text-right'><a class='btn btn-primary'>Je participe</a></p>");
			content.append("<p class='text-right'><a class='btn btn-info'> Details >> </a></p>");
			content.append("<p> Visibilité : ");
			content.append(EventVisibility.eventVisibilityToString(event.getEventprivacy()));
			content.append("</p><p> Description : ");
			content.append(event.getEventDescription());
			content.append("</p><p> Thème : ");
			content.append(event.getEventTheme());
            content.append("</p><ul class='list-inline list-unstyled'>");
            content.append("<li><span><i class='glyphicon glyphicon-calendar'> </i> ");
            content.append(event.getEventDate());
            content.append("</span></li><li>|</li><span><i class='glyphicon glyphicon-comment'></i> ");
            content.append(event.getEventCommentsCounts());
            content.append(" <a href='#'>comments</a></span><li>|</li><li><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star-empty'></span></li></ul></div></div></div>");

            for (Comment c : event.getComments()) {
    			content.append("<div class='well'>");
    			content.append("<div class='media'>");
    			
    			content.append("<div class='pull-left' style='border-right: solid #aaaaaa 1px' href='#'>");
    			content.append("<h4 class='media-heading'>");
    			content.append("Commentaire de ");
    			content.append(event.getCreator().getUserName()); // A modifier !
    			content.append("</h4>");
    			content.append("<p class='text-left'>");
    			content.append("le : ");
    			content.append(c.getCommentDate());
    			content.append("</p>");
    			content.append("</div>");
    			content.append("<div style='vertical-align: middle;'>");
    			content.append("<p style='height: 100%; width:100%; padding-left:250px;'>");
    			content.append(c.getCommentContent());
    			content.append("</p>");
    			content.append("</div>");
    
                content.append("</div></div>");
            }
              	         		
		}
	
	%>

	<%= content.toString() %>

	<%@include file="/jsp/footer.jsp"%>

</body>
</html>