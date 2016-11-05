
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" %>
  <%@ page isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<!--  Scripts  -->
	<!-- JQuery  -->
    <script  src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <!-- Bootstrap  -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
   <!-- JQuery Validate  -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
    <!-- Fin  Scripts  -->
    
    <!-- Styles  -->
    <!-- bootstrap  -->
    <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
 	<!-- fontAwsome  -->
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
   <!-- style du footer  -->
   <link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
   <!-- style du header  -->
   <link rel="stylesheet" href="/DAR/assets/css/header.css">
    <!--  content style -->
   <link rel="stylesheet" href="/DAR/assets/css/main.css">
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
						<h3 class="panel-title">formulaire de création d'un groupe  </h3>
					</div>
					<div class="panel-body">
						<form id="groupForm" method="post" class="form-horizontal" action="/DAR/create_group">
						
						<div id="error">	
						
							<c:if test="${not empty groupNotAvailable}">
             					<div class="alert alert-danger"> <span class="glyphicon glyphicon-info-sign"></span> Le nom du groupe est déja pris merci de choisir un autre</div>
             					        								 </c:if>
      					  </div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="groupname"> Nom du groupe :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="groupname" name="groupname" placeholder="Le nom du groupe" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="groupdescription"> Description :</label>
								<div class="col-sm-5">
									<textarea rows="3" cols="40" id="groupdescription" name ="groupdescription" placeholder="decrire brièvement votre groupe" maxlength="50" >
																</textarea> 
								</div>
							</div>

							


							<div class="form-group">
								<label class="col-sm-4 control-label" for="groupcapacity">Capacité  :</label>
								<div class="col-sm-5">
									<input type="number" class="form-control" id="groupcapacity" name="groupcapacity" placeholder="Le nombre maximum de membre  " />
								</div>
							</div>
							
							<div class="form-group">
								<label class="col-sm-4 control-label" for="groupinvites">invitations*  :</label>
								
								<div class="col-sm-5">
									<input type="radio" name="groupinvites" value="0"> Mes Amis<br>
 									 <input type="radio" name="groupinvites" value="1" checked="true"> Je n'ivite personne pour l'instant!</br>
                           		 </select>
							</div>
							</div>
							
							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<button type="submit" class="btn btn-primary" name="signup" value="creategroupe">Créer mon groupe</button>
								</div>
							</div>
							
				</form>
							</div>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
		$( document ).ready( function () {
			
			$( "#groupForm" ).validate( {
				rules: {
					groupname:{
						required: true,
						minlength: 2
					} ,
					groupcapacity: {
					    required: true,
						maxlength: 2,
						number :true
						
					},
					groupdescription: {
						required: true,
						minlength: 25
					}
					
				},
				messages: {
					groupname:{
						required: "Veuillez indiquer un nom pour votre groupe",
						 minlength:"Veuillez indiquer un nom  de groupe valide"
						},
						groupcapacity: {
						 required: "Veuillez indiquer votre le nombre maximum  de membre authorisé",
						 maxlength:"un groupe ne peut avoir plus de 100 membres ",
						 number :"veuillez entrer un nombre valide"
						},
						groupdescription: {
						required: "Veuillez indiquer une description brève de votre groupe",
						minlength: "votre description  doit avoir au moins 15 caractères"
					}
					
					
				},
				errorElement: "em",
				errorPlacement: function ( error, element ) {
					// ajout d'une classe `help-block` à lelement en erreur
					error.addClass( "help-block" );

					// ajout de la classe  `has-feedback`  au parent div.form-group
					// afin d'jouter les icons au inputs
					element.parents( ".col-sm-5" ).addClass( "has-feedback" );

					
					error.insertAfter( element );
					

					
					//ajout de l'element span si il existe pas et application de la classe créer pour lui
					if ( !element.next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-remove form-control-feedback'></span>" ).insertAfter( element );
					}
				},
				success: function ( label, element ) {
					//ajout de l'element span si il existe pas et application de la classe créer pour lui.
					if ( !$( element ).next( "span" )[ 0 ] ) {
						$( "<span class='glyphicon glyphicon-ok form-control-feedback'></span>" ).insertAfter( $( element ) );
					}
				},
				highlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-5" ).addClass( "has-error" ).removeClass( "has-success" );
					$( element ).next( "span" ).addClass( "glyphicon-remove" ).removeClass( "glyphicon-ok" );
				},
				unhighlight: function ( element, errorClass, validClass ) {
					$( element ).parents( ".col-sm-5" ).addClass( "has-success" ).removeClass( "has-error" );
					$( element ).next( "span" ).addClass( "glyphicon-ok" ).removeClass( "glyphicon-remove" );
				}
			} );
		} );
	</script>


  <jsp:include page="footer.jsp">
  <jsp:param name="active" value="signunfooter" />
  </jsp:include>


	
	</body>

</html>
    