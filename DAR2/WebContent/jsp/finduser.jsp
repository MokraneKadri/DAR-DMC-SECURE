<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<li>Amis|rechercher</li>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">


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
<script type="text/javascript" src="/DAR2/assets/js/friendsandrequest.js"></script>
<script type="text/javascript" src="/DAR2/assets/js/finduser.js"></script>
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
<link rel="stylesheet" href="/DAR2/assets/css/pageFooter.css">
<!-- style du header  -->
<link rel="stylesheet" href="/DAR2/assets/css/header.css">
<link rel="stylesheet" href="/DAR2/assets/css/main.css">


<title>Rechercher Utilisateur</title>
</head>
<body>
	<jsp:include page="header.jsp" />

	<div class="maincontainer" id="maincontainer">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>
							<p class='capital'>Rechercher un utilisateur</p>
						</h3>
					</div>
					<div class="panel-body">

						<div id='page'>

							<div class='layer-center'>



								<form action="javascript:(function(){return;})()" method="get"
									OnSubmit="javascript:finduser(this)">

									<div class="form-group row">
										<label for="email" class="col-xs-4 col-form-label">Rechercher
											Par : </label>
										<div class="col-sm-5">
											<SELECT name="search" size="1" class="form-control">
												<!-- <OPTION value="nom">Nom</OPTION>
												<OPTION value="prenom">Prénom</OPTION> -->
												<OPTION value="email">Email</OPTION>
												<OPTION value="username">Nom d'utilisateur</OPTION>
											</SELECT>
										</div>
									</div>

									<div class="form-group row">
										<label for="email" class="col-xs-4 col-form-label"></label>

										<div class="col-sm-5">
											<input class="form-control" name="value" value="" id="value"
												placeholder="vous cherchez ?">
										</div>

									</div>




									<div class="form-group row">
										<label for="email" class="col-xs-4 col-form-label"></label>

										<div class="col-sm-5 col-sm-offset-5">
											<button type="submit" class="btn btn-success btn-lg"
												name="btn-save" id="btn-submit">
												<span class="glyphicon glyphicon-search"></span> &nbsp;
												Chercher
											</button>
										</div>
									</div>
								</form>
							</div>



							<div id='notifier' ></div>
						</div>
					</div>
				</div>

			</div>
		</div>
	</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
<script src="DAR2/JavaScriptServlet"></script>
</html>