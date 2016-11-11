<%@page import="fr.upmc.dar.entities.User"%>
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

<script type='text/javascript'>			
	var submission = function (form) {
		var json = {
			mode : form.mode.value,
			id : form.id.value
		};
		
		$
		.post("/DAR/events", json)
		.done( function (data) {
			notify($.parseJSON(data), $('#notifier'));
			if ($.parseJSON(data).success != undefined)
				$('#participating').remove();
		});
	}
</script>

<script type="text/javascript" src="/DAR/assets/js/notifier.js"></script>
<link rel="stylesheet" href="/DAR/assets/css/notifier.css">
	
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
	
	
	<div class="bs-example">
    <ul class="breadcrumb">
    	<li>Uni-connect</li>
        <li><a href="#">Acceuil</a></li>
        <li class="active"><a href="#">événement > détails  </a></li>
        
    </ul>
		</div>
	
  <div class="row">
 
  <div class="panel-group">
   <div class="col-md-6">
    <div class="panel panel-default">
      <div class="panel-heading"><b><%=event.getName() %> :</b></div>
      <div class="panel-body">
	  
	  
	  <table class="table table-hover">
    <thead>
      <tr>
        <th>Créateur </th>
        <td><%=event.getCreator().getUserName() %></td>
      </tr>
    </thead>
    <tbody>
      <tr>
        <th>Politique d'accès</th>
        <td><%=event.getPrivacy() %></td>
      </tr>
      <tr>
        <th>Thème </th>
        <td><%=event.getTheme() %></td>
      </tr>
      <tr>
        <th>Description :</th>
        <td><p><%=event.getDescription()%></p><p></p></td>
      </tr>
	  <tr>
        <th>Date  </th>
        <td><%=event.getDateToString() %></td>
      </tr>
	  <tr>
        <th>Heure de début </th>
        <td><%=event.getHour() %></td>
      </tr>
	  <tr>
        <th>Places prises</th>
        <td><%= event.getCandidates().size() %> / <%= event.getPlaces() %></td>
      </tr>
      <tr>
        <th>Participants</th>
        <td>
        	<% for (User candidate : event.getCandidates()) { %>
        		[<%= candidate.getUserName() %>]
        	<% } %>
        </td>
      </tr>
	  <tr>
        <th>Lieu  </th>
        <td><%=event.getPlaceType() %></td>
      </tr>
	  <tr>
        <th>Nom du Lieu  </th>
        <td><%=event.getPlace()%></td>
      </tr>
	   <tr>
        <th>Adresse Complete </th>
        <td><%=event.getAddress() %> </td>
      </tr>
	  <tr>
        <th>Commentaire   </th>
        <td><%=event.getComments().size() %> au total </td>
		</tr>
		<th>   </th>
        <td>
        	<div id="notifier"></div>
        </td>
		<td>
			<!-- COPY FROM HERE --> 
	    	<%
		    	User user = DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login"));
		    	boolean isParticipating = false;
		    	for (User u : event.getCandidates())
		    		if (user.getId() == u.getId())
		    			isParticipating = true;
		    	if (!isParticipating) { // IF BEGIN
	    	%>
	    	<form id='participating' style='text-align: right' action='javascript:return(0)' method='POST' onsubmit='submission(this)'>
	    		<input type='hidden' name='mode' value='participate'/>
	    		<input type='hidden' name='id' value='<%= event.getId()%>' />
	    		<button type="submit" class='btn btn-primary'>Participer</button>
	    	</form>
	    	<% } // IF END %>
	    	<!-- TO HERE --> 
		</td>
      </tr>
    </tbody>
  </table>
	  
	 
	  <!-- fin panel gauche body--></div>
	  
	   <div class="panel-footer"><a href="#com">Voir les comentaires</a></div>
	  
    </div>
	</div>
	<div class="col-md-6">
    <div class="panel panel-default">
      <div class="panel-heading">Accès</div>
	  
	  
	  
	  
	  </div>
      <div class="panel-body">
	  
	  <script src='https://maps.googleapis.com/maps/api/js?v=3.exp'></script><div style='overflow:hidden;height:350px;width:500px;'><div id='gmap_canvas' style='height:350px;width:550px;'></div><div><small><a href="http://embedgooglemaps.com">google maps carte</a></small></div><div><small><a href="https://fbaddlikebutton.com/en/">quick</a></small></div><style>#gmap_canvas img{max-width:none!important;background:none!important}</style></div><script type='text/javascript'>function init_map(){var myOptions = {zoom:10,center:new google.maps.LatLng(48.856614,2.3522219000000177),mapTypeId: google.maps.MapTypeId.ROADMAP};map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);marker = new google.maps.Marker({map: map,position: new google.maps.LatLng(48.856614,2.3522219000000177)});infowindow = new google.maps.InfoWindow({content:'<strong>bar</strong><br>Paris, France<br>'});google.maps.event.addListener(marker, 'click', function(){infowindow.open(map,marker);});infowindow.open(map,marker);}google.maps.event.addDomListener(window, 'load', init_map);</script>
	 </div>
	   <div class="panel-footer">infos complémentaire:
	   
	   
	   </div>
	  
    </div>
    </div>
	</div>



	
<%-- 		<div class="panel panel-default">
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
		                    <%= event.getCandidates().size()%> / <%= event.getPlaces().toString()%>
		                </i>
		                </span>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-calendar" aria-hidden="true"></i>
		                    <b> Date : </b>
		                </span>
		                <%= event.getDateToString()%>
		            </li>
		            <li class="list-group-item">
		                <span>
		                    <i class="fa fa-university" aria-hidden="true"></i>
		                    <b> Lieu :</b>
		                </span>
		                <%= event.getPlace()%>
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
		</div> --%>
		
		<div class='well' id="com">
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