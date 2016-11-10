<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
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
<link rel="stylesheet" href="/DAR/assets/css/header.css">
<!-- style du header  -->
<link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
<!-- style du content  -->
<link rel="stylesheet" href="/DAR/assets/css/main.css">

<!-- Fin Styles  -->


</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>




	<div class="maincontainer">
	
			<div class="col-sm-8 col-sm-offset-2">
	<div class="bs-example">
    <ul class="breadcrumb">
    	<li>Uni-connect</li>
        <li><a href="#">Acceuil</a></li>
        <li class="active"><a href="#">évenements > Tous</a></li>
        
    </ul>
		</div>
		</div>
	 <div class="row">
	</div>
	</div>
	<script type="text/javascript">
		
		$(document)
		.ready( function() {
			 $
			.get("/DAR/events?mode=list")
			.done( function (data) {
				var json = $.parseJSON(data);
				console.log(data);
				$.each(json, function (i, event) {
					console.log(event); 
				   
				
					$("div.row").append('<a href=""><div class="col-sm-4 col-lg-4 col-md-4">'+
		                    '<div class="thumbnail">'+
		                       ' <img src="/DAR/assets/img/event.jpg" alt="">'+
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
