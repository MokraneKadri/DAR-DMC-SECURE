<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Uni-Connect |Acceuil :</title>
	
	
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
      <link rel="stylesheet" href="/DAR/assets/css/main.css">
   
   	<!-- Fin Styles  -->	
<body>

 <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>

	
    <div class="maincontainer ">


<div class="panel panel-default">
  <div class="panel-heading">Event Name here !</div>
  <div class="panel-body">
  <ul class="list-group">
   <li class="list-group-item"><i class="fa fa-user" aria-hidden="true"></i><b>  Créateur :</b> <c:out value="${details['creator']}"/></i></span></li>
    <li class="list-group-item"><i class="fa fa-user-secret" aria-hidden="true"></i><b>  Politique D'accès :</b> <c:out value="${details['privacy']}"/></i></span></li>
  <li class="list-group-item"><i class="fa fa-location-arrow fa-lg" aria-hidden="true"></i><b>  Adresse :</b>  <c:out value="${details['adresse']}"/> </i></span></li>
<li class="list-group-item"><span><i class="fa fa-users" aria-hidden="true"></i> <b>Participants :</b> <c:out value="${details['participants']}"/></i></span></li>
<li class="list-group-item"><span><i class="fa fa-calendar" aria-hidden="true"></i><b> Date:</b></span> <c:out value="${details['date']}"/></li>
<li class="list-group-item"><span><i class="fa fa-university" aria-hidden="true"></i><b> Lieu :</b></span> <c:out value="${details['place']}"/></li>
<li class="list-group-item"><span><i class="fa fa-comments-o" aria-hidden="true"></i><b> Commentaires:   <c:out value="${details['commentCount']}"/> commentaires <a href="#comms"> voir</a></a></b></span></li>
<li class="list-group-item"><span><i class="fa fa-bars" aria-hidden="true"></i><b> Description Détaillée :</b>  <p> <c:out value="${details['description']}"/><p></i></span></li>

  
</ul>
    
    
    
  </div>
  </div>
 
  
   <div class="panel panel-default" id="comms">
  <div class="panel-heading"><b>Les commentaires:</b></div>
  <div class="panel-body">
    	
    <div class="panel panel-default">
  			<div class="panel-heading">de jack 33 , le 11 sep 2016:</div>
  			 <div class="panel-body">
  				<p>lorem epsum je dseteste cette event il pue la merde</p>
  		
  			</div>
  	</div>
  		<div class="panel panel-default">
  			<div class="panel-heading">de jack 33 , le 11 sep 2016:</div>
  			<div class="panel-body">
  			<p>lorem epsum je dseteste cette event il pue la merde</p>
  		
  			</div>
  		</div>
  		
  		<div class="panel panel-default">
  			<div class="panel-heading">de jack 33 , le 11 sep 2016:</div>
  			<div class="panel-body">
  			<p>lorem epsum je dseteste cette event il pue la merde</p>
  		
  			</div>
  		</div>
  		<div class="panel panel-default">
  			<div class="panel-heading">de jack 33 , le 11 sep 2016:</div>
  			<div class="panel-body">
  			<p>lorem epsum je dseteste cette event il pue la merde</p>
  		
  			</div>
  		</div>
  		<div class="panel panel-default">
  			<div class="panel-heading">de jack 33 , le 11 sep 2016:</div>
  			<div class="panel-body">
  			<p>lorem epsum je dseteste cette event il pue la merde</p>
  		
  			</div>
  		</div>
  	</div>

	</div>










</div> <!-- /container -->
     <jsp:include page="footer.jsp">
		<jsp:param name="active" value="indexfooter" />
	</jsp:include>



</body>
</html>