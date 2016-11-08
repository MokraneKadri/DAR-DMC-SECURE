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

<link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/landingpage.css">
 <link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">

<!-- Fin Styles  -->
</head>
<body>


<div class="intro-header">
        <div class="container">

            <div class="row">
                <div class="col-lg-12">
                    <div class="intro-message">
                        <h1>University Connect</h1>
                        <h3> Rejoingnez La communauté dès aujourd'hui</h3>
                        <hr class="intro-divider">
                        <ul class="list-inline intro-social-buttons">
                            <li>
                                <a href="/DAR/signup" class="btn btn-primary btn-lg">Découvrir La plateforme</span></a>
                            </li>
                            
                            
                        </ul>
                    </div>
                </div>
            </div>

        </div>
        <!-- /.container -->

    </div>
    
    <jsp:include page="footer.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>

</body>
</html>