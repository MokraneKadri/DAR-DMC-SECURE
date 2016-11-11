<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html> 
<html>
<head>
	<title>Recupération du mot de passe </title>
	
	
	
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
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/pageFooter.css">
   <!-- style du header  -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/header.css">
   <!--  content style -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/main.css">
   	<!-- Fin Styles  -->	
	
	
	</head>
	
<body>


	<jsp:include page="header.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>


	<div class="maincontainer"  id="maincontainer">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
	<div class="bs-example">
    <ul class="breadcrumb">
    	<li>Uni-connect</li>
        <li><a href="/DAR/home">Acceuil</a></li>
        <li class="active"><a href="/DAR/passwordreset">Réeinitialisation du mot de passe </a></li>
        
    </ul>
		</div>

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title">Réinitialiser mon mot de passe :  </h3>
					</div>
					<div class="panel-body">
						<form id="signinform" method="post" class="form-horizontal" action="/DAR/passwordreset">
				
				<div id="error">		<c:if test="${not empty formErrors['login']}">
             					<div class="alert alert-danger"> <span class="glyphicon glyphicon-info-sign"></span><c:out value="${formErrors['login']}"/></div>
             					        								 </c:if>
      					  </div>
							<div class="form-group">
								<label class="col-sm-4 control-label" for="login"> Login ou Email :</label>
								<div class="col-sm-5">
									<input type="text" class="form-control" id="login" name="login" placeholder="Nom d'utilisateur ou email" />
								</div>
							</div>

							

							<div class="form-group">
								<div class="col-sm-9 col-sm-offset-4">
                                    <button type="submit" class="btn btn-success" name="btn-save" id="btn-submit">
                                        <span class="glyphicon glyphicon-log-in"></span> &nbsp; Récuper mon mot de passe
                                    </button>
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
			$( "#signinform" ).validate( {
				rules: {
					login:{
						required: true,
						minlength: 5
					} ,
					password: {
					    required: true,
						minlength: 5
					}
					
				},
				messages: {
					login:{
						required: "Veuillez indiquer votre login",
						 minlength:"Veuillez indiquer un login  valide"
						},
					password: {
						required: "Veuillez indiquer votre mot de passe",
						 minlength:"Veuillez indiquer un mot de passe valide"
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
		<jsp:param name="active" value="signin" />
	</jsp:include>
	</body>
</html>
