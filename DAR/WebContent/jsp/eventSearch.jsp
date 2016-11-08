<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Uni-Connect | Recherche </title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"
	integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
<link rel="stylesheet" href="/DAR/assets/css/header.css">
<link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
<link rel="stylesheet" href="/DAR/assets/css/main.css">

</head>
<body>

	<%@include file="/jsp/header.jsp"%>
	
	<script type='text/javascript'>
		
		var request = function(form) {
			
			var json = {}; 
			
			json.mode = "search";
			console.log(form.search.value);
			switch (form.search.value) {
			case "name":
				json.name = form.value.value;
				break;
			case "description":
				json.description = form.value.value;
				break;
			case "privacy":
				json.privacy = form.value.value;
				break;
			case "places":
				json.places = form.value.value;
				break;
			case "date":
				json.date = form.value.value;
				break;
			case "theme":
				json.theme = form.value.value;
				break;
			case "address":
				json.address = form.value.value;
				break;
			}
			
			console.log(json);
			
			$
			.get("/DAR/events", json)
			.done( function (data) {
				console.log(data);
				var result = $.parseJSON(data);
				console.log(data);
				
				$("#resultset").html("");
				
				$.each(result, function (i, event) {
					console.log(event);
					
					$("#resultset").append('<div class="well">'+
		           			'<div class="media">'+
		              	    '<a class="pull-left" href="/DAR/events?mode=event&id='+ event.id + '">'+	
		              	  		'<div class="media-body">'+
		              	    		'<h4 class="media-heading">'+ event.name+'</h4>'+
		              	         	'<p class="text-right">créer par : '+ event.creator +'</p>'+
		              	         	'<p class="text-right"><a href="" class="btn btn-primary">Je participe</a>'+ '</p>'+
		              	         	'<p class="text-right"><a href="/DAR/eventDetails?evt='+event.name+'" class="btn btn-info"> Details >> </a>'+ '</p>'+
		              	         	'<p> <b>Visibilité </b>: ' + event.privacy + '</p>'+
		              	          	'<p> <b>Description </b>: ' + event.description + '</p>'+
		              	          	'<p><b> Thème</b> : ' + event.theme + '</p>'+
		              	         	'<ul class="list-inline list-unstyled">'+
		              	  				'<li><span><i class="glyphicon glyphicon-calendar"> </i> ' + event.date + '</span></li>'+
		              	            	'<li>|</li>'+
		              	            	'<span><i class="glyphicon glyphicon-comment"></i> ' + event.comments + ' <a href="/DAR/events?mode=event&id='+ event.id + '">comments<a/></span>'+
		              	            	'<li>|</li>'+
		              	            	'<li>'+
		              	             		'<span class="glyphicon glyphicon-star"></span>'+
		              	              		'<span class="glyphicon glyphicon-star"></span>'+
		              	               		'<span class="glyphicon glyphicon-star"></span>'+
		              	                	'<span class="glyphicon glyphicon-star"></span>'+
		              	                	'<span class="glyphicon glyphicon-star-empty"></span>'+
		              	            	'</li>'+
		              	            	'<li>|</li>'+
		              	            	'<li>'+
		              	            		'<!-- Use Font Awesome http://fortawesome.github.io/Font-Awesome/ -->'+
		              	             		'<span><i class="fa fa-facebook-square"></i></span> '+
		              	              		'<span><i class="fa fa-twitter-square"></i></span> '+
		              	              		'<span><i class="fa fa-google-plus-square"></i></span>'+
		              	            	'</li>'+
		              				'</ul>'+
		              	 		'</div>'+
		         			'</div>'+  
		              '</div>');
					
				});
			})
			.fail( function () {
				alert("Une erreur est survenue au moment de la récupération de la liste des événements");
			});
		}
	
	</script>
	
	<div class="maincontainer ">
		<div class="row">
			<div class="col-sm-8 col-sm-offset-2">
				<div class="panel panel-default">
					<div class="panel-heading">
						<h3>
							<p class='capital'>Rechercher un event</p>
						</h3>
					</div>
					<div class="panel-body">
						<div id='page'>
							<div class='layer-center'>
	
								<form action="javascript:(function(){return;})()" method="get"
									OnSubmit="javascript:request(this)">
	
									<div class="form-group row">
										<label class="col-xs-4 col-form-label">Rechercher par mot clef dans : </label>
										<div class="col-sm-5">
											<SELECT name="search" size="1" class="form-control">
												<OPTION value="name">Nom</OPTION>
												<OPTION value="description">Description</OPTION>
												<OPTION value="privacy">Visibilité</OPTION>
												<OPTION value="date">Date</OPTION>
												<OPTION value="theme">Theme</OPTION>
												<OPTION value="places">Places</OPTION>
												<OPTION value="address">Adresse</OPTION>
											</SELECT>
										</div>
									</div>
	
									<div class="form-group row">
										<label class="col-xs-4 col-form-label"></label>
	
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
	
							<div id='notifier'></div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div id='resultset'></div>
		
	</div>
	
	<%@include file="/jsp/footer.jsp"%>

</body>
</html>