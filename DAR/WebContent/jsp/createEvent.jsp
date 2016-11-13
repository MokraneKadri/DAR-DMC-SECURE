
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>

<head>
<title>Créationde meet-up :</title>
	
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
<script type="text/javascript" src="/DAR/assets/js/searchevent.js" charset="UTF-8"></script>
<!-- <script type="text/javascript" src="/DAR/assets/js/searchuniversity.js"></script>
 --><!-- Fin  Scripts  -->

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

	<div class="bs-example">
    <ul class="breadcrumb">
    	<li>Uni-connect</li>
        <li><a href="/DAR/home">Accueil</a></li>
        <li class="active"><a href="/DAR/events?mode=create">évenements > création</a></li>
        
    </ul>
		</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Créer votre Meet up</h3>
					</div>
					<div class="panel-body">

						<form id="eventForm" method="post" class="form-horizontal"
							action="/DAR/events?mode=create">

							

							<div class="form-group">
								<label class="col-sm-4 control-label" for="name">
									Intitulé :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="name"
										name="name" placeholder="Intitulé de l'évenement" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="date">
									Date :</label>
								<div class="col-sm-5">
									<input type="date" class="form-control" name="date"
										id="date" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="hour">
									Heure :</label>
								<div class="col-sm-5">

									<input type="time"   class="form-control" name="hour"

										id="hour" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="theme">Thème
									:</label>
								<div class="col-sm-5">
									<select id="theme" name="theme" class="form-control">
										
										<option value="Moulin à parole">Moulin à paroles</option>
										<option value="échanges linguistiques/culturelles">échanges linguistiques/culturelles</option>
										<option value="relatif aux études">relatif aux études</option>
										<option value="Autres">Autres...</option>
									</select>
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="description">Description
									:</label>
								<div class="col-sm-5">
									<textarea rows="5" cols="50" id="description"
										name="description" value=""
										style="resize: none; width: 100%;"></textarea>
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventparticipants">Max. Participants:
									</label>
								<div class="col-sm-5">
									<input type="number" value="1" min="1" max="100" name="places" id="eventparticipants" placeholder="nombre maximum de participant"/>
								</div>
							</div>

				
							<div class="form-group">
							<label class="col-sm-4 control-label"> Université :</label>
							<div class=" col-sm-5">
                   			 <input type="text" class="form-control " placeholder="Trouver votre unversité" name="university"
										id="university" />
										</div>
               				 </div>
               				
               				 
               				 <div class="form-group">
               				 
               				 <label class="col-sm-4 control-label"></label>
							<div class=" col-sm-5">
                      			  <button class="btn btn-info" value="trouver" type="button" data-toggle="modal" data-target="#myModalUn">
                         			   <i class="glyphicon glyphicon-search"> </i> Aidez moi à Trouver mon université ?
                      				  </button>
                   
                				</div>
               				 </div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label" for="privacy">Confidentialité
									:</label>
								<div class="col-sm-5">
									<select id="privacy" name="privacy"
										class="form-control">
									
										<option value="public">Publique</option>
										<option value="privé">Amis</option>
										<option value="université">Université</option>
									</select>
								</div>
							</div>

						
							
							<div class="col-sm-5 "
								style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center; margin-bottom: 25px; margin-top: 10px">
								<span
									style="font-size: 20px; background-color: #F3F5F6; padding: 0px 10px;">
								  Précisez le Lieu: </span>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="place_type">Type
									de lieu :</label>
								<div class="col-sm-5">

									<input  type="text" id="place_type" name="place_type" class="form-control" placeholder="ex :Restaurant ...Bar..." value =""/>
								</div>


							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="place_name">
									Nom du Lieu :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="place_name"
										name="place_name" placeholder="ex: Bar le village" />
								</div>
							</div>


							<div class="form-group">
								<label class="col-sm-4 control-label" for="address">Adresse
									Complète:</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="address"
										name="address" placeholder="l'adresse complète du lieu" />
								</div>
							</div>
							<div class="col-sm-5 "
								style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center; margin-bottom: 25px; margin-top: 10px">
								<span
									style="font-size: 20px; background-color: #F3F5F6; padding: 0px 10px;">
									
									<button type="button" class="btn btn-danger "
									data-toggle="modal" data-target="#myModal">   <i class="glyphicon glyphicon-search"> </i>  Rechercher  un établissement sur Yelp! </button>
								  </span>
							</div>
							<!-- <div class="form-group">
							<div class="col-sm-5 col-sm-offset-4">
								<button type="button" class="btn btn-danger btn-lg "
									data-toggle="modal" data-target="#myModal">   <i class="glyphicon glyphicon-search"> </i>  Retrouver sur Yelp! </button>
							</div>
							</div> -->
							<div class="col-sm-5 ">
							<br><br>
							</div>
							<div class="form-group">
							<input type="hidden" name="business_id" id="business_id" value="">
							<input type="hidden"   name="university_id" id="university_id" value="">
								<div class="col-sm-8 col-sm-offset-4">
									<button type="submit" class="btn btn-primary btn-lg" name="creteEvent"
										value="creteEvent">Créer ce meet-up !</button>
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
							
							
							
							var today = new Date();
							var day = today.getDate();
							var mois = today.getMonth()+1; 
							var annee = today.getFullYear();
							 if(day<10){
							        day='0'+day
							    } 
							    if(mois<10){
							        mois='0'+mois
							    } 

							today = annee+'-'+mois+'-'+day;
							document.getElementById("date").setAttribute("min", today);
							$("#eventForm")
									.validate(
											{
												rules : {
													
													
													hour:{
														required:true
														
													},
													name : {
														required : true,
														minlength : 3
													},
													date : {
														required : true,
														date : true

													},
													place_type : {
														required : true

													},
													place_name : {
														required : true,
														minlength:5
													},
													theme : {
														required : true

													},
													address : {
														required : true,
														minlength : 10

													},
													eventpolicy : {
														required : true

													},
													description : {
														required : true,
														minlength : 20

													},

												},
												messages : {
													name : {
														required : "Veuillez indiquer l'intitulé de votre meet up"

													},
													place_name :{
														required :"merci d'indiquer le nom du lieu",
														minlenth :"merci d'indiquer un nom de lieu valide"
													},
													
													place_type : {
														required : "Veuillez indiquer le cadre ou se tiendra l'évenement"

													},
													date : {
														required : "Veuillez indiquer la date de l'évenement",
														date : "Veuillez indiquer une date  au format aaaa-mm-jj"
													},
													eventpolicy : {
														required : "veuillez précisez la confidentialité de l'évent"

													},

													theme : {
														required : "veuillez précisez le thème de l'évenement"

													},
													address : {
														required : "Veuillez saisir l'adresse exacte ou se tiendra lévenement",
														minlength : "merci d'indiquer une adresse valide"

													},

													description : {
														required : "Veuillez décrire l'évenement que vous allez créer",
														minlength : "merci de fournir une description valide(20caractères min)"

													},
													hour:{
														required:"merci d'indiquer l'heure à laquelle se tiendra l'évenement",
														
													},

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
		data-backdrop="false" style="z-index: 100">
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
							<form  id="yelp" class="form-horizontal" role="form"
								action="javascript:(function(){return;})()" method="get"
								OnSubmit="javascript:searchevent(this)">

								<div class="form-group">
									<label class="col-sm-4 control-label">Lieu :</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="lieu" id="lieu"
											placeholder="Bar... Restaurant... Adresse..." />
									</div>
								</div>
								<div class="form-group">
									<label class="col-sm-4 control-label">Ville:</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="ville"
											id="ville" placeholder="Paris ... Créteil..." />
									</div>
								</div>
								<div class="col-sm-offset-4 col-sm-5">
									<button type="submit" class="btn btn-primary" name="find"
										value="find" >Chercher !</button>
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
	
	
	
		<!-- Modal University-->
	<div id="myModalUn" class="modal fade" role="dialog"
		data-backdrop="false" style="z-index: 1500">
		<div class="modal-dialog modal-lg" role="document">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">Rechercher une Université !</h4>
				</div>
				<div class="modal-body">
					<div class="well-searchbox">
						<div class="row">
							<form  id="uni" class="form-horizontal" role="form"
								action="javascript:(function(){return;})()" method="get"
								OnSubmit="javascript:searchuniversity(this)">

								<div class="form-group">
									<label class="col-sm-4 control-label"> Recherche :</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="termuni" id="termuni"
											placeholder="Nom ... Adresse..." />
									</div>
								</div>
								<div class="col-sm-offset-4 col-sm-5">
									<button type="submit" class="btn btn-primary" name="find-university"
										value="find-university">Chercher !</button>
								</div>


							</form>
						</div>
					</div>
				</div>
				<div id='notifier-modal-university'></div>
				<div class="modal-footer">
					<div class="form-group"></div>
				</div>


			</div>
		</div>

	</div>
</body>

</html>
