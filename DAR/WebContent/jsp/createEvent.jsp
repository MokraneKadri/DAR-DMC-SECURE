
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


				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Créer votre Meet up</h3>
					</div>
					<div class="panel-body">

						<form id="eventForm" method="post" class="form-horizontal"
							action="/DAR/events?mode=create">

							

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

							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventhour">
									Heure :</label>
								<div class="col-sm-5">

									<input type="time"   class="form-control" name="eventhour"

										id="eventhour" />
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
								<label class="col-sm-4 control-label" for="eventparticipants">Max. Participants:
									</label>
								<div class="col-sm-5">
									<input type="number" value="1" min="1" max="100" name="eventparticipants" id="eventparticipants" placeholder="nombre maximum de participant"/>
								</div>
							</div>

				
							<div class="form-group">
							<label class="col-sm-4 control-label"> Université :</label>
							<div class=" col-sm-5">
                   			 <input type="text" class="form-control " placeholder="trouver votre unversité" name="university"
										id="university" />
										</div>
               				 </div>
               				
               				 
               				 <div class="form-group">
               				 
               				 <label class="col-sm-4 control-label"></label>
							<div class=" col-sm-5">
                      			  <button class="btn btn-info bt-lg" value="trouver" type="button" data-toggle="modal" data-target="#myModalUn">
                         			   <i class="glyphicon glyphicon-search"> chercher !</i>
                      				  </button>
                   
                				</div>
               				 </div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventpolicy">Confidentialité
									:</label>
								<div class="col-sm-5">
									<select id="eventpolicy" name="eventpolicy"
										class="form-control">
										<option value="" disabled selected>choisir</option>
										<option value="public">Public</option>
										<option value="private">Amis</option>
										<option value="university">Université</option>
									</select>
								</div>
							</div>

							<!-- <div class="form-group">
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
							</div> -->


							<!-- Modal -->

							
							<div class="col-sm-5 "
								style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center; margin-bottom: 25px; margin-top: 10px">
								<span
									style="font-size: 20px; background-color: #F3F5F6; padding: 0px 10px;">
								  Précisez le Lieu: </span>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="eventplace">Type
									de lieu :</label>
								<div class="col-sm-5">

									<input  type="text" id="eventplace" name="eventplace" class="form-control" placeholder="ex :Restaurant ...Bar..." value =""/>
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
							<div class="col-sm-5 "
								style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center; margin-bottom: 25px; margin-top: 10px">
								<span
									style="font-size: 20px; background-color: #F3F5F6; padding: 0px 10px;">
								 Ou Trouvez un endroit à l'aide de yelp !  </span>
							</div>
							<div class="form-group">
							<div class="col-sm-5 col-sm-offset-4">
								<button type="button" class="btn btn-succes btn-lg "
									data-toggle="modal" data-target="#myModal">Rechercher </button>
							</div>
							</div>
							<div class="col-sm-5 "
								style="width: 100%; height: 15px; border-bottom: 1px solid black; text-align: center; margin-bottom: 25px; margin-top: 10px">
								<span
									style="font-size: 20px; background-color: #F3F5F6; padding: 0px 10px;">
								 </span>
							</div>
							<div class="form-group">
							<input type="hidden" name="business_id" id="business_id" value="">
							<input type="hidden"   name="university_id" id="university_id" value="">
								<div class="col-sm-9 col-sm-offset-4">
									<button type="submit" class="btn btn-primary" name="creteEvent"
										value="creteEvent">Créer ce Meet up</button>
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
							document.getElementById("eventdate").setAttribute("min", today);
							$("#eventForm")
									.validate(
											{
												rules : {
													
													eventparticipants:{
														required:true,
														min:2,
														max:100
													},
													eventhour:{
														required:true
														
													},
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
													eventplacename : {
														required : true,
														minlength:5
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
													eventplacename :{
														required :"merci d'indiquer le nom du lieu",
														minlenth :"merci d'indiquer un nom de lieu valide"
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
													eventparticipants:{
														required:"Veuillez renseigner le nombre de particpant",
														min:"Veuillez renseigner un nombre de particpant valide",
														max:"le nombre darticipants ne peut exceder 100",
													},

													eventdescription : {
														required : "Veuillez décrire l'évenement que vous allez créer",
														minlength : "merci de fournir une description valide"

													},
													eventhour:{
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
									<label class="col-sm-4 control-label"> Type de Lieu :</label>
									<div class="col-sm-5">
										<input type="text" class="form-control" name="lieu" id="lieu"
											placeholder="Bar... Restaurant..." />
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
		data-backdrop="false" style="z-index: 1500";
}">
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
