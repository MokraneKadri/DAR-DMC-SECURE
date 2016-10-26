<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>login :</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

<link rel="stylesheet"
	href="http://localhost:8080/DAR/assets/css/header.css">
<link rel="stylesheet"
	href="http://localhost:8080/DAR/assets/css/footer.css">

<link rel="stylesheet"
	href="http://localhost:8080/DAR/assets/css/singnup.css">

<link href='http://fonts.googleapis.com/css?family=Cookie'
	rel='stylesheet' type='text/css'>

</head>
<body>

 <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>

	
    <div class="container">

      <!-- Jumbotron -->
      <div class="jumbotron">
        <h1>Uni-Connect !</h1>
        <p class="lead">Cras justo odio, dapibus ac facilisis in, egestas eget quam. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet.</p>
        <p><a class="btn btn-lg btn-success" href="#" role="button">Je M'inscrits dès aujourdhui</a></p>
      </div>

      <!-- Example row of columns -->
      <div class="row">
        <div class="col-lg-4">
          <h2>Créer vos Propre Meet up!</h2>
          <p class="text-danger">As of v9.1.2, Safari exhibits a bug in which resizing your browser horizontally causes rendering errors in the justified nav that are cleared upon refreshing.</p>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-primary" href="#" role="button">Voir les  details &raquo;</a></p>
        </div>
        <div class="col-lg-4">
          <h2>Rejoindre Des Eevenements !</h2>
          <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuada magna mollis euismod. Donec sed odio dui. </p>
          <p><a class="btn btn-primary" href="#" role="button">Voir les  details &raquo;</a></p>
       </div>
        <div class="col-lg-4">
          <h2>Rejoindre Un Groupe !</h2>
          <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam. Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa.</p>
          <p><a class="btn btn-primary" href="#" role="button">Voir les  details &raquo;</a></p>
        </div>
      </div>
    </div> <!-- /container -->
     <jsp:include page="footer.jsp">
		<jsp:param name="active" value="indexfooter" />
	</jsp:include>



</body>
</html>