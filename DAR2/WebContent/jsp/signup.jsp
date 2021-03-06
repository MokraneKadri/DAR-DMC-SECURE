<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
  <%@ page isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  
<!DOCTYPE html>
<html>

<head>
		<title>Création de compte </title>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
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
   <link rel="stylesheet" href="/DAR2/assets/css/pageFooter.css">
   <!-- style du header  -->
   <link rel="stylesheet" href="/DAR2/assets/css/header.css">
    <!--  content style -->
   <link rel="stylesheet" href="/DAR2/assets/css/main.css">
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
        <li><a href="/DAR2/home">Accueil</a></li>
        <li class="active"><a href="/DAR2/signup">Inscription</a></li>
        
    </ul>
		</div>

	

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Créer votre compte </h3>
					</div>
					<div class="panel-body">
						<form id="signupForm" method="post" class="form-horizontal" action="/DAR2/signup">
						
						<div id="error">	
						
							<c:if test="${not empty formErrors['username']}">
             					<div class="alert alert-danger"> <span class="glyphicon glyphicon-info-sign"></span><c:out value="${formErrors['username']}"/></div>
             				
             					<c:if test="${not empty formErrors['email']}">
             					<div class="alert alert-danger"> <span class="glyphicon glyphicon-info-sign"></span><c:out value="${formErrors['email']}"/></div>
             					        								 </c:if>	        								 </c:if>
      					  </div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="firstname"> Nom :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="firstname" name="firstname" placeholder="Votre nom" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="lastname"> Prénom :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="lastname" name="lastname" placeholder="Votre prénom" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="username">Nom d'utilisateur :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="username" name="username" placeholder="Votre nom d'utilisateur" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="email">Email :</label>
								<div class="col-sm-5">
									<input type="email" class="form-control" id="email" name="email" placeholder="Votre adresse email" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="password">Mot de passe :</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="password" name="password" placeholder="Votre mot de passe" />
								</div>
							</div>

							<div class="form-group">
								<label class="col-sm-4 control-label" for="confirm_password">Confirmez  :</label>
								<div class="col-sm-5">
									<input type="password" class="form-control" id="confirm_password" name="confirm_password" placeholder="Confirmez votre mot de passe" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="street">Adresse :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="street" name="street" placeholder="ex: 10 rue montmartre  " />
								</div>
								</div>
								<div class="form-group">
								<label class="col-sm-4 control-label" for="zip">Code Postale :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="zip" name="zip" placeholder="Code postale " />
								</div>
								</div>
								<div class="form-group">
								<label class="col-sm-4 control-label" for="city">Ville :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="city" name="city" placeholder="Ville : ex Paris " />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="université">Université :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="university" name="university" placeholder="Indiquez votre university" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="cursus">Cursus :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="cursus" name="cursus" placeholder="Indiquez votre cursus " />
								</div>
							</div>
							    <input type="hidden" name="<csrf:tokenname/>" value="<csrf:tokenvalue uri="/DAR2/signup"/>" />

							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
									<button type="submit" class="btn btn-primary" name="signup" value="Sign up">Créer mon compte</button>
								</div>
							</div>
							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
								
						Vous avez déja un compte? <a href="/DAR2/signin?<csrf:token uri="/DAR2/signin"/>"> Connectez-vous ici !</a> 
								</div>
								<input type="hidden" name="<csrf:tokenname/>/>" value="<csrf:tokenvalue uri="/DAR2/signup"/>"/>
							</div>
				<form>
							</div>
						
					</div>
				</div>
			</div>
		</div>
	
	<script type="text/javascript">
	
		$( document ).ready( function () {
			$( "#signupForm" ).validate( {
				rules: {
					firstname:{
						required: true,
						minlength: 2
					} ,
					lastname: {
					    required: true,
						minlength: 2
					},
					username: {
						required: true,
						minlength: 5
					},
					password: {
						required: true,
						minlength: 5
					},
					confirm_password: {
						required: true,
						minlength: 5,
						equalTo: "#password"
					},
					email: {
						required: true,
						email: true
					},
					street: {
						required: true,
						minlength: 4
					},
					zip: {
						required: true,
						number: true
					},
					city: {
						required: true,
						minlength: 3
					}
					
				},
				messages: {
					firstname:{
						required: "Veuillez indiquer votre nom",
						 minlength:"Veuillez indiquer un nom valide"
						},
					lastname: {
						required: "Veuillez indiquer votre prénom",
						 minlength:"Veuillez indiquer un prénom valide"
						},
					username: {
						required: "Veuillez indiquer votre nom d'utilisateur",
						minlength: "votre nom d'utilisateur doit avoir au moins 5 caractères"
					},
					password: {
						required: "Veuillez saisir un mot de passe ",
						minlength: "le mot de passe doit avoir au moins 5 caractères"
					},
					confirm_password: {
						required: "Veuillez saisir un mot de passe",
						minlength: "le mot de passe doit avoir au moins 5 caractères",
						equalTo: "les mots de passes ne correspondent pas !"
					},
					email: "veuillez saisir une adresse mail valide",
					
				
				street: {
					required: "veuillez entrer le nom de votre rue",
					minlength:"veuillez saisir un nom de rue valide"
				},
				zip: {
					required: "veuillez indiquer votre code postale",
					number: "veuillez indiquer un code postale valide"
				},
				city: {
					required: "veuillez entrer le nom de votre ville ",
					minlength:"veuillez saisir un nom de ville valide"
				},
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
    