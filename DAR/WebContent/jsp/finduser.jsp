<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


	<!--  Scripts  -->
	<!-- JQuery  -->
    <script  src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <!-- Bootstrap  -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
   <!-- JQuery Validate  -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
    <script type="text/javascript" src="/DAR/assets/js/friendsandrequest.js"></script>
    <script type="text/javascript" src="/DAR/assets/js/finduser.js"></script>
    <!-- Fin  Scripts  -->
    
    <!-- Styles  -->
    <!-- bootstrap  -->
    <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
 	<!-- fontAwsome  -->
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
   <!-- style du footer  -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/pageFooter.css">
   <!-- style du header  -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/header.css">
      <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/main.css">
   
   	<!-- Fin Styles  -->

<title>Rechercher Utilisateur</title>
</head>
<body>
	<jsp:include page="header.jsp" />
	
	   <div class="container ">
	<div id='page'>
		<div class='layer-center'>
			<p class='capital'>Rechercher un utilisateur</p>


			<form action="javascript:(function(){return;})()" method="get"
				OnSubmit="javascript:finduser(this)">

				<div class="form-group row">
					<label for="email" class="col-xs-4 col-form-label">Rechercher
						par </label>
					<div class="col-xs-4">
						<SELECT name="search" size="1" class="form-control">
							<OPTION value="nom">Nom</OPTION>
							<OPTION value="prenom">Prénom</OPTION>
							<OPTION value="email">Email</OPTION>
							<OPTION value="username">Nom d'utilisateur</OPTION>
						</SELECT>
					</div>
				</div>

				<div class="form-group row">

					<div class="col-xs-10">
						<input class="form-control" name="value" value="" id="value">
					</div>
				</div>

				<input type="submit" class="btn btn-primary btn-block"
					value="Chercher"> <br>
			</form>

			<div id='notifier'></div>
		</div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>