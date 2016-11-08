<%@page import="fr.upmc.dar.enums.EventVisibility"%>
<%@page import="fr.upmc.dar.entities.Event"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Uni-Connect | Actus</title>
	
	
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

	<div class='maincontainer'>

		<%
			@SuppressWarnings("unchecked")
			List<Event> events = (List<Event>) request.getAttribute("actus");

			for (Event event : events) {
		%>

		<div class='well'>
			<div class='media'>
				<div class='pull-left'>
					<h4 class='media-heading'>
						<a href='/DAR/events?mode=event&id=<%=event.getId()%>'> <%=event.getName()%> </a>
					</h4>
					<p class='text-right'>
						cr�� par : <%=event.getCreator().getUserName()%></p>
				</div>
				
				<p class='text-right'>
					<a class='btn btn-primary'>Je participe</a>
				</p>
				<p class='text-right'>
					<a class='btn btn-info'> D�tails >> </a>
				</p>
				
				<p>
					Visibilit� : <%=EventVisibility.eventVisibilityToString(event.getPrivacy())%>
				</p>
				<p>
					Description : <%=event.getDescription()%>
				</p>
				<p>
					Th�me : <%=event.getTheme()%>
				</p>
				
				<ul class='list-inline list-unstyled'>
					<li><span><i class='glyphicon glyphicon-calendar'>
						</i> <%=event.getDate()%> </span></li>
					<li>|</li>
					<li><span><i class='glyphicon glyphicon-comment'></i> <%=event.getComments().size()%>
							<a href='/DAR/events?mode=event&id=<%=event.getId()%>'>comments</a></span></li>
					<li>|</li>
					<li><span class='glyphicon glyphicon-star'></span><span
						class='glyphicon glyphicon-star'></span><span
						class='glyphicon glyphicon-star'></span><span
						class='glyphicon glyphicon-star'></span><span
						class='glyphicon glyphicon-star-empty'></span></li>
				</ul>
				
			</div>
		</div>

		<%
			}
		%>

	</div>
	
	<%@include file="/jsp/footer.jsp"%>
	
</body>
</html>