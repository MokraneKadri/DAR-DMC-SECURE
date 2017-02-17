<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Liste des Evenements</title>

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
<link rel="stylesheet" href="/DAR2/assets/css/header.css">
<!-- style du header  -->
<link rel="stylesheet" href="/DAR2/assets/css/pageFooter.css">
<!-- style du content  -->
<link rel="stylesheet" href="/DAR2/assets/css/main.css">

<!-- Fin Styles  -->


</head>
<body>
<script type="text/javascript" src="/DAR2/assets/js/localisation.js"></script>

	<jsp:include page="header.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>

<div class="bs-example">
    <ul class="breadcrumb">
    	<li>Uni-connect</li>
        <li><a href="#">Acceuil</a></li>
        <li class="active"><a href="#">évenements > Tous</a></li>
        
    </ul>
		</div>


	<div class="maincontainer" id="maincontainer">
	
			<div class="col-sm-8 col-sm-offset-2">
	
		</div>
	 <div class="row">
	</div>
	<input type="hidden" name="" value="" id="">
	<input type="hidden" name="" value="" id="">
	</div>
	<script type="text/javascript">
		
		$(document)
		.ready( function() {
			 $
			.get("/DAR2/events?mode=list")
			.done( function (data) {
				var json = $.parseJSON(data);
				console.log(data);
				$.each(json, function (i, event) {
					console.log(event); 
				   
				
					$("div.row").append('<a href=""><div class="col-sm-4 col-lg-4 col-md-4">'+
		                    '<div class="thumbnail">'+
		                       ' <img src="/DAR2/assets/img/event.jpg" alt="">'+
		                        '<div class="caption">'+
		                        '<h3>'+event.name+'</h3>'+
		                           ' <h4 class="pull-right">'+event.privacy +'</h4>'+
		                            '<h5>Crée par : </b>'+event.creator+'</h5>'+
		                            
		                            '<p> <b>description :</b>'+event.description+'</p>'+
		                            '<p>pourri ou pas description de levenement pourri ou pas description de levenement pourri ou pas .</p>'+
		                        '</div><div>'+
		                        
                  	         			'</div><div class="ratings">'+
		                         ' <p class="pull-right"><span><i class="glyphicon glyphicon-comment"></i>'+event.comments+'</span></p>'+
		                          '<p>'+
		                           ' <span><i class="glyphicon glyphicon-calendar"> </i> '+event.date+' à 21:00</span>'+
		                            '</p>'+
		                       ' </div>'+
		                    '</div>'+
		               ' </div>'+
									
								 
		                '</div></div>');	
             
				});
			})
			.fail( function () {
				alert("Une erreur est survenue au moment de la récupération de la liste des événements");
			});
		});
	</script>


	<jsp:include page="footer.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>

</body>

</html>
