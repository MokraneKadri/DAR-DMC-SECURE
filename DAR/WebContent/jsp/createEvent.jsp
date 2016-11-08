
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">

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
<script type="text/javascript" src="/DAR/assets/js/searchevent.js"></script>
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

<link rel="stylesheet" href="/DAR/assets/css/modal.css">

<!-- Fin Styles  -->




</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="active" value="signunheader" />
	</jsp:include>

	<div class="maincontainer">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Créer votre Meet up</h3>
					</div>
					<div class="panel-body">
						<%-- <<<<<<< HEAD
						<form id="eventForm" method="post" class="form-horizontal"
							action="/DAR/create_event">

							<div id="error">

								<c:if test="${not empty formErrors['username']}">
									<div class="alert alert-danger">
										<span class="glyphicon glyphicon-info-sign"></span>
										<c:out value="${formErrors['username']}" />
									</div>

									<c:if test="${not empty formErrors['email']}">
										<div class="alert alert-danger">
											<span class="glyphicon glyphicon-info-sign"></span>
											<c:out value="${formErrors['email']}" />
										</div>
									</c:if>
								</c:if>
							</div>
======= --%>
						<form id="eventForm" method="post" class="form-horizontal"
							action="/DAR/create_event">

							<div id="error">

								<c:if test="${not empty eventNameError}">
									<div class="alert alert-danger">
										<span class="glyphicon glyphicon-info-sign"></span> ce nom
										d'évenement est déja pris merci de choisir un autre
									</div>

								</c:if>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventname">
									intitulé :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="eventname"
										name="eventname" placeholder="intitulé de l'évenement" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventdate">
									Date :</label>
								<div class="col-sm-5">
									<input type="date" class="form-control" name="eventdate"
										id="eventdate" />
								</div>
							</div>
							<!-- Modal -->

							<div class="col-sm-5 col-sm-offset-4">
								<button type="button" class="btn btn-info btn-lg btn-block"
									data-toggle="modal" data-target="#myModal">Rechercher
									un endroit !</button>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventplace">Type
									de lieu :</label>
								<div class="col-sm-5">

									<select id="eventplace" name="eventplace" class="form-control"
										autocomplete="on">
										<option value="" disabled selected>choisir le lieu</option>
										<option value="0">Bar</option>
										<option value="1">Restaurant</option>
										<option value="2">Université</option>
										<option value="3">Autres...</option>
									</select>

								</div>


							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventplacename">
									Nom du Lieu :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="eventplacename"
										name="eventplacename" placeholder="Ex: Pizza FIVE" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventaddress">Adresse
									Complète:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="eventaddress"
										name="eventaddress" placeholder="l'adresse complète du lieu" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventtheme">Thème
									:</label>
								<div class="col-sm-5">
									<select id="eventtheme" name="eventtheme" class="form-control">
										<option value="" disabled selected>choisir un thème</option>
										<option value="0">Moulin à Parole</option>
										<option value="1">échange linguistique/culturelle</option>
										<option value="2">relatifs au études</option>
										<option value="3">Autres...</option>
									</select>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventdescription">Description
									:</label>
								<div class="col-sm-5">
									<textarea rows="5" cols="50" id="eventdescription"
										name="eventdescription" value=""
										style="resize: none; width: 100%;"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventpolicy">Confidentialité
									:</label>
								<div class="col-sm-5">
									<select id="eventpolicy" name="eventpolicy"
										class="form-control">
										<option value="" disabled selected>choisir</option>
										<option value="0">Public</option>
										<option value="1">Amis</option>
										<option value="2">Université</option>

									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventinvites">invitations*
									:</label>

								<div class="col-sm-5">
									<input type="radio" name="eventinvites" value="0"> Mes
									Amis<br> <input type="radio" name="eventinvites" value="1">
									Les Membres de mon groupe<br> <input type="radio"
										name="eventinvites" value="2"> Amis et groupe</br> <input
										type="radio" name="eventinvites" value="3" checked="true">
									je n'invite Personne!</br> </select>

								</div>
							</div>

							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<button type="submit" class="btn btn-primary" name="signup"
										value="Sign up">Céer ce Meet up</button>
								</div>
							</div>

						</form>

					</div>
				</div>
			</div>
		</div>
	</div>




	<script type="text/javascript">
		$(document)
				.ready(
						function() {
							$("#eventForm")
									.validate(
											{
												rules : {
													eventname : {
														required : true,
														minlength : 3
													},
													eventdate : {
														required : true,
														date : true

													},
													eventplace : {
														required : true

													},
													eventtheme : {
														required : true

													},
													eventaddress : {
														required : true,
														minlength : 10

													},
													eventpolicy : {
														required : true

													},
													eventdescription : {
														required : true,
														minlength : 20

													},

												},
												messages : {
													eventname : {
														required : "Veuillez indiquer l'intitulé de votre meet up"

													},
													eventplace : {
														required : "Veuillez indiquer le cadre ou se tiendra l'évenement"

													},
													eventdate : {
														required : "Veuillez indiquer la date de l'évenement",
														date : "Veuillez indiquer une date valide"
													},
													eventpolicy : {
														required : "veuillez précisez la confidentialité de l'évent"

													},

													eventtheme : {
														required : "veuillez précisez le thème de l'évenement"

													},
													eventaddress : {
														required : "Veuillez saisir l'adresse exacte ou se tiendra lévenement",
														minlength : "merci d'indiquer une adresse valide"

													},

													eventdescription : {
														required : "Veuillez décrire l'évenement que vous allez créer",
														minlength : "merci de fournir une description valide"

													}

												},
												errorElement : "em",
												errorPlacement : function(
														error, element) {
													// ajout d'une classe `help-block` à lelement en erreur
													error
															.addClass("help-block");

													// ajout de la classe  `has-feedback`  au parent div.form-group
													// afin d'jouter les icons au inputs
													element
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-feedback");

													error.insertAfter(element);

													//ajout de l'element span si il existe pas et application de la classe créer pour lui
													if (!element.next("span")[0]) {
														$(
																"<span class='glyphicon glyphicon-remove form-control-feedback'></span>")
																.insertAfter(
																		element);
													}
												},
												success : function(label,
														element) {
													//ajout de l'element span si il existe pas et application de la classe créer pour lui.
													if (!$(element)
															.next("span")[0]) {
														$(
																"<span class='glyphicon glyphicon-ok form-control-feedback'></span>")
																.insertAfter(
																		$(element));
													}
												},
												highlight : function(element,
														errorClass, validClass) {
													$(element)
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-error")
															.removeClass(
																	"has-success");
													$(element)
															.next("span")
															.addClass(
																	"glyphicon-remove")
															.removeClass(
																	"glyphicon-ok");
												},
												unhighlight : function(element,
														errorClass, validClass) {
													$(element)
															.parents(
																	".col-sm-5")
															.addClass(
																	"has-success")
															.removeClass(
																	"has-error");
													$(element)
															.next("span")
															.addClass(
																	"glyphicon-ok")
															.removeClass(
																	"glyphicon-remove");
												}
											});
						});
	</script>


	<jsp:include page="footer.jsp">
		<jsp:param name="active" value="signunfooter" />
	</jsp:include>


	<!-- Modal -->
	<div id="myModal" class="modal fade" role="dialog"
		data-backdrop="false">
		<div class="modal-dialog modal-lg" role="document">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Rechercher un endroit !</h4>
				</div>
				<div class="modal-body">
					<div class="well-searchbox">
					<div class="row">
						<form class="form-horizontal" role="form"
							action="javascript:(function(){return;})()" method="get"
							OnSubmit="javascript:searchevent(this)">

							<div class="form-group">
								<label class="col-sm-4 control-label"> Type de Lieu :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="lieu" id="lieu"
										placeholder="Bar... Restaurant..." />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label">Ville:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" name="ville" id="ville"
										placeholder="Paris ... Créteil..." />
								</div>
							</div>
							<div class="col-sm-offset-4 col-sm-5">
								<button type="submit" class="btn btn-primary" name="find"
									value="find">Chercher !</button>
							</div>


						</form>
					</div>
					</div>
					</div>
					<div id='notifier-modal'></div>
					<div class="modal-footer">
						<div class="form-group"></div>

					</div>


				</div>
			</div>

		</div>
	</div>
</body>

</html>
