<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@page import="fr.upmc.dar.enums.EventVisibility"%>
<%@page import="fr.upmc.dar.entities.Event"%>
<%@page import="java.util.List"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Uni-Connect| Actualités</title>


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



	<div class='maincontainer' style="margin-left: 150px;margin-right: 150px;">

			<div class="col-sm-8 col-sm-offset-2">
	<div class="bs-example">
    <ul class="breadcrumb">
    	<li>Uni-connect</li>
        <li><a href="/DAR/home">Acceuil</a></li>
        <li class="active"><a href="/DAR/events?mode=actus&limit=15">Actus et événements récents</a></li>
       
    </ul>
     </div> </div>
	 <div class="row">
	
		<%
			@SuppressWarnings("unchecked")
			List<Event> events = (List<Event>) request.getAttribute("actus");

			for (Event event : events) {
		%>
		<div class='col-sm-4 col-lg-4 col-md-4'>
			<div class='thumbnail'>
				<img src='/DAR/assets/img/event1.jpg' alt=''>
				<div class='caption'>
					<h3>
						<a href='/DAR/events?mode=event&id=<%=event.getId()%>'> <%=event.getName().substring(0, 5)%>...
						</a>
					</h3>
					<%-- <h4 class="pull-right"><%=EventVisibility.eventVisibilityToString(event.getPrivacy())%></h4> --%>
					<p>
						<span class="glyphicon glyphicon-user"></span> <b>Créé par :</b> <%=event.getCreator().getUserName()%>
					</p>
					<p><span class="glyphicon glyphicon-home"></span> <b>Adresse :</b><%=event.getAddress() %>
					<p>
						<span class="glyphicon glyphicon-tasks"></span> <b>Description :</b> 
					</p>
					<p><%=event.getDescription().substring(0, 10)%>.....</p>
					<p><a href='/DAR/events?mode=event&id=<%=event.getId()%>'> >>>>>lire la suite </a></p>
				</div>
				<div></div>
				<div class="ratings">
					<p class='pull-right'>
						<span><i class='glyphicon glyphicon-comment'></i> <%=event.getComments().size()%>
							<a href='/DAR/events?mode=event&id=<%=event.getId()%>#com'>commentaires</a></span>
					</p>
					<p>
						<span><i class='glyphicon glyphicon-calendar'> </i> <%=event.getDateToString()%>
						
						</span>
					</p>
				</div>
			</div>
		</div>



	<%
			}
		%>












	</div>
</div>
	<%@include file="/jsp/footer.jsp"%>

</body>
</html>