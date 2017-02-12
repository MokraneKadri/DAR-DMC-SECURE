<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Uni-Connect |redirection :</title>
	
	
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
      <link rel="stylesheet" href="/DAR2/assets/css/main.css">
   
   	<!-- Fin Styles  -->	
<body>

 <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>

  
  
  
  
  
  
  
  
  
  
  
  
    <div class="maincontainer ">
    
    
    <div class="row">
			<div class="col-sm-8 col-sm-offset-2">
	

				<div class="panel panel-default">
					<div class="panel-heading">
						<h3 class="panel-title"><c:if test="${not empty pageInfos['infos']}"><c:out value="${pageInfos['infos']}"></c:out> </c:if> </h3>
					</div>
					<div class="panel-body">
						
							  
    				<p>Merci<b> <c:if test="${not empty pageInfos['user']}"><c:out value="${pageInfos['user']}"></c:out></b> </c:if></p>
    				<p><c:if test="${not empty pageInfos['content']}"><c:out value="${pageInfos['content']}"></c:out> </c:if></p>
    				</div>
    			</div>
    		</div>
    		</div>
    
    </div>
  
  
  
  
  
  
  <!-- /container -->
     <jsp:include page="footer.jsp">
		<jsp:param name="active" value="indexfooter" />
	</jsp:include>



</body>
</html>