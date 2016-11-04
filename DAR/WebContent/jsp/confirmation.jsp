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
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/pageFooter.css">
   <!-- style du header  -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/header.css">
      <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/main.css">
   
   	<!-- Fin Styles  -->	
<body>

 <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>

  
  
  
  
  
  
  
  
  
  
  
  
    <div class="maincontainer ">
    
    <p> un mail vous a été envoyé !</p>
    <p>merci de suivre les instructions pour recuperer votre compte </p>
    
    
    </div>
  
  
  
  
  
  
  <!-- /container -->
     <jsp:include page="footer.jsp">
		<jsp:param name="active" value="indexfooter" />
	</jsp:include>



</body>
</html>