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
	
	<%
		StringBuilder content = new StringBuilder();
		String id = request.getParameter("id");
		
		if (id == null)  { 
			response.getWriter().print("Erreur, l'id de l'event en question n'a pas été trouvé.");
	%>
		<script>
			var redirect = settimeout(() => {
				window.location.href = "/DAR/events?mode=actus&limit=15"			
			}, 3000); 
			
			redirect();
		</script>
	<%
		}
		
		IEventDao dao = DAOFactory.createEventDao();
		Event event = dao.getEventById(Integer.valueOf(id));
	
	%>
	<div class="maincontainer">
	
		<div class="panel panel-default">
		    <div class="panel-heading"><h4><%= event.getName()%></h4></div>
		    <div class="panel-body">
		        <ul class="list-group">
		            <li class="list-group-item">
		                <i class="fa fa-user" aria-hidden="true"></i>
		                <b> Créateur :</b>
		                <%= event.getCreator().getUserName()%>
		            </i>
		            </span>
		            </li>
		            <li class="list-group-item">
		                <i class="fa fa-user-secret" aria-hidden="true"></i>
		                <b> Politique D'accès :</b>
		                <%= event.getPrivacy()%>
		            </i>
		            </span>
		            </li>
		            <li class="list-group-item">
		                <i class="fa fa-location-arrow fa-lg" aria-hidden="true"></i>
		                <b> Adresse :</b>
		                <%= event.getAddress()%>
		            </i>
		            </span>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-users" aria-hidden="true"></i>
		                    <b>Participants :</b>
		                    <%= event.getCandidates().size()%> / <%= event.getPlaces()%>
		                </i>
		                </span>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-calendar" aria-hidden="true"></i>
		                    <b> Date : </b>
		                </span>
		                <%= event.getDate()%>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-university" aria-hidden="true"></i>
		                    <b> Lieu :</b>
		                </span>
		                <%= event.getPlace().getName()%>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-comments-o" aria-hidden="true"></i>
		                    <b>
		                        Commentaires : <%= event.getComments().size()%>
		                    </b>
		                </span>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-bars" aria-hidden="true"></i>
		                    	<b> Description : </b>
		                     	<%= event.getDescription()%> 
		                   	</i>
		                </span>
		            </li>
		        </ul>
		    </div>
		</div>
		
		<div class='well'>
			<div class='pull-left' style='padding-right: 27.5px; border-right: solid #aaaaaa 1px; height: 50px;' href='#'>
				<h4 class='media-heading'> Nouveau commentaire </h4>
				<p class='text-left'></p>
			</div>
			<form action='/DAR/events' method='POST'>
				<input name='mode' type='hidden' value='comment' />
				<input name='id' type='hidden' value='<%= event.getId() %>' />
				<div style='height: 100%; width:100%; padding-left:240px;'>
					<input name='content' type='textarea' style='border: 1px solid lightgrey; border-right: none; border-radius: 5px 0px 0px 5px; width: 85%; height: 50px; padding-left: 10px;' placeholder='Nouveau commentaire, pas plus de 255 caractères ... (pour le moment)' pattern='{255}' required/>
					<input style='height: 50px; width: 15%; border: 1px solid #b3bfd1; border-radius: 0px 5px 5px 0px; background: #cedbef; color: white;' type='submit' value='Commenter'/>
				</div>
			</form>
		</div>
	
		<% for (Comment comment : event.getComments()) { %>
		
		<div class="well">
			<div class="media">
				<div class="pull-left" style="padding-right: 20px; border-right: solid #aaaaaa 1px">
					<h4 class="media-heading"> <%= comment.getCreator().getUserName() %> </h4>
					<p class="text-left">le : <%= comment.getCommentDate() %></p>
				</div>
				<div style="vertical-align: middle; text-align: justify;">
					<div style="height: 100%; width:100%; padding-left:240px;"> <%= comment.getCommentContent() %></div>
				</div>
			</div>
		</div>
		
		<% } %>
		
	</div>

	<%@include file="/jsp/footer.jsp"%>

</body>
</html>