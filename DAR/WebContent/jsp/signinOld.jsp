<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="UTF-8"%>
 <%@ page isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>login :</title>
    <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
	<link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
	
	<style> <%@include file="/assets/css/header.css" %> 
	<%@include file="/assets/css/footer.css" %>  
	
	</style>
    <link href='http://fonts.googleapis.com/css?family=Cookie'	rel='stylesheet' type='text/css'>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
	<script src="../assets/js/script.js"></script>

</head>
<body>

	<jsp:include page="header.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>



	<div class="container">
		<div id="loginbox" style="margin-top: 50px;"
			class="mainbox col-md-6 col-md-offset-3 col-sm-8 col-sm-offset-2">
			<div class="panel panel-info">
				<div class="panel-heading">
					<div class="panel-title">Connexion :</div>
					<div
						style="float: right; font-size: 80%; position: relative; top: -10px">
						<a href="/DAR/passwordreset">Mot de passe oublié ?</a>
					</div>
				</div>

				<div style="padding-top: 30px" class="panel-body">

					<div style="display: none" id="login-alert"
						class="alert alert-danger col-sm-12"></div>

					<form id="loginform"  method="post" action ="/DAR/signin" class="form-horizontal" role="form">
						<c:if test="${not empty formErrors['login']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['login']}"/></span></div>
           								 </c:if>
           				<c:if test="${not empty formErrors['password']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['password']}"/></span></div>
           								 </c:if>
						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-user"></i></span> <input id="login"
								type="text" class="form-control" name="login" 
								placeholder="username or email">
						</div>

						<div style="margin-bottom: 25px" class="input-group">
							<span class="input-group-addon"><i
								class="glyphicon glyphicon-lock"></i></span> <input id="password"
								type="password" class="form-control" name="password"
								placeholder="password">
								
						</div>



						<div class="input-group">
							<div class="checkbox">
								<label> <input id="login-remember" type="checkbox"
									name="remember" value="1"> Rester Connecté
								</label>
							</div>
						</div>


						<div style="margin-top: 10px" class="form-group">
							<!-- Button -->

							<div class="col-sm-12 controls">
								<input type="submit" value="se connecter" />

							</div>
						</div>


						<div class="form-group">
							<div class="col-md-12 control">
								<div
									style="border-top: 1px solid #888; padding-top: 15px; font-size: 85%">
									Vous n'avez pas de compte! <a href="/DAR/signup"> créez en
										un ici </a>
								</div>
							</div>
						</div>
					</form>



				</div>
			</div>
		</div>

	</div>
	<jsp:include page="footer.jsp">
		<jsp:param name="active" value="signin" />
	</jsp:include>

</body>
</html>