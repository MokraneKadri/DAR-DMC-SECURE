<%@page import="fr.upmc.dar.dao.DAOFactory"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<title>Connexion |Espace membre</title>



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
<script type="text/javascript" src="/DAR/assets/js/friendsandrequest.js"></script>
<script type="text/javascript" src="/DAR/assets/js/finduser.js"></script>
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
<link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
<!-- style du header  -->
<link rel="stylesheet" href="/DAR/assets/css/header.css">
<!--  content style -->
<link rel="stylesheet" href="/DAR/assets/css/main.css">
<link rel="stylesheet" href="/DAR/assets/css/profil.css">
<!-- Fin Styles  -->


</head>

<body>


	<jsp:include page="header.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>



	<div class="container-fluid">
		<div class="row">
			<div class="col-md-2 col-sm-4 sidebar1">
				<div class="logo">
					<a href="/DAR/home"></a> <img src="/DAR/assets/img/logo.jpg"
						class="img-responsive center-block" alt="Logo">
				</div>
				<br>
				<div class="left-navigation">
					<ul class="list">
						<h4>
							<strong>Tableau de bord </strong>
						</h4>
						<hr>
						<li><a href="javascript: void(0);" id="myprofil">Mon
								profil</a></li>
						<li><a href="javascript: void(0);" id="friends"> Mes amis</a></li>
						<li><a href="javascript: void(0);" id="request"> demandes
								reçues</a></li>
						<li><a href="javascript: void(0);" id="Addfriends">
								Rechercher un utilisateur</a></li>

						<hr />
						<li><a href="javascript: void(0);" id="mymeetsup">Mes
								Events</a></li>
						<li><a href="javascript: void(0);" id="meetups">Mes
								Participations</a></li>
					</ul>
				</div>
			</div>
			<div class="col-md-10 col-sm-8 main-content" id="main-content">
			</div>
		</div>
	</div>







	<script type="text/javascript">
		$(document)
				.ready(
						function() {

							$("#friends").click(
									function() {

										$("#main-content").load(
												'/DAR/jsp/myfriends.jsp #maincontainer');
									});

							$("#Addfriends")
									.click(
											function() {
												// alert("rrrrr");
												$("#main-content")
														.load(
																'/DAR/jsp/finduser.jsp #maincontainer');
											});

							$("#request")
									.click(
											function() {
												// alert("rrrrr");
												$("#main-content")
														.load(
																'/DAR/jsp/pendingrequests.jsp #maincontainer' );
											});

							$("#myprofil").click(
									function() {
										// alert("rrrrr");
										$("#main-content").load(
												'/DAR/profil?user=<c:out value="${login}"/> ');
									});

							$("#mymeetsup")
									.click(
											function() {
												// alert("rrrrr");
												$("#main-content")
														.load(
																//'/DAR/jsp/finduser.jsp .maincontainer');
																'/DAR/events?mode=list&type=jsp&creator_id=<%= DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login")).getId() %> .maincontainer' );
											});
							$("#meetups")
									.click(
											function() {
												// alert("rrrrr");
												$("#main-content")
														.load(
																//'/DAR/events?mode=list .maincontainer');
																'/DAR/events?mode=list&type=jsp&member_id=<%= DAOFactory.createUserDao().findUserByUserName((String)request.getSession().getAttribute("login")).getId() %> .maincontainer' );
											});

						});
	</script>

	<script>
		window.onload = function() {
			$("#main-content").load("/DAR/profil?user=<c:out value="${login}"/>");
		}
	</script>
	
	

	<jsp:include page="footer.jsp">
		<jsp:param name="active" value="profilfooter" />
	</jsp:include>

</body>
</html>