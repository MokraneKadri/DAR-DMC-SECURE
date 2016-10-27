
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8" %>
  <%@ page isELIgnored="false" %>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>Sign up Now</title>
	
	<style> <%@include file="/assets/css/header.css" %> 
	<%@include file="/assets/css/footer.css" %> 
	<%@include file="/assets/css/signup.css" %> 
	
	</style>
	
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">

   
	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>


</head>
<body>

  <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>

	<div class="container">
		<div class="row main">
			<div class="panel-heading">

			</div>
			<div class="main-login main-center">
				<form class="form-horizontal"  method="post"  action="/DAR/signup">
				<c:if test="${not empty formErrors['email']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['email']}"/></span></div>
	
					</c:if>
					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">Votre Nom </label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="name" id="name"  placeholder="Entrez votre nom"/>
							</div>
						</div>
					</div>
					<c:if test="${not empty formErrors['name']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['name']}"/></span></div>
	
					</c:if>
					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">Votre Email</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="email" id="email"  placeholder="Entrez votre Email"/>
							</div>
						</div>
					</div>
					<c:if test="${not empty formErrors['email']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['email']}"/></span></div>
	
					</c:if>

					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">Nom d'utilisateur</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="username" id="username"  placeholder="Entrez votre nom d'utilisateur"/>
							</div>
						</div>
					</div>
					<c:if test="${not empty formErrors['username']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['username']}"/></span></div>
	
					</c:if>
					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Mot de Passe</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<input type="password" class="form-control" name="password" id="password"  placeholder="Saisissez un mot de passe"/>
							</div>
						</div>
					</div>
				<c:if test="${not empty formErrors['password']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['password']}"/></span></div>
	
					</c:if>
					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Confirmer mot de passe</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<input type="password" class="form-control" name="confirmpassword" id="confirmpassword"  placeholder="Re-Saisissez un mot de passe"/>
							</div>
						</div>
					</div>
						<c:if test="${not empty formErrors['confirmpassword']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['confirmpassword']}"/></span></div>
	
					</c:if>
					
					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Etablissement</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-university" aria-hidden="true"></i></span>
								<select id="university" name="university" class="form-control">
									<option value="1">xxxxx</option>
									<option value="2">xxxx</option>
									<option value="3">xxxxx</option>
									<option value="4">Autres...</option>
								</select>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Cursus</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-book" aria-hidden="true"></i></span>
								<select id="cursus" name="cursus" class="form-control">
									<option value="1">xxxxx</option>
									<option value="2">xxxx</option>
									<option value="3">xxxxx</option>
									<option value="4">Autres...</option>
								</select>
							</div>
						</div>
					</div>

					<div class="form-group ">
						<input type="submit" class="btn btn-primary btn-lg btn-block login-button" value="s'enregistrer">
					</div>
					<div class="login-register">
						Vous avez déja un compte <a href="/DAR/signin"> Connectez-vous ici</a>
					</div>
				</form>
							<c:if test="${not empty formErrors['confirmpassword']}">
             					 <div><span class="help-block" style = color:#DC143C><c:out value="${formErrors['confirmpassword']}"/></span></div>
	
					</c:if>
			</div>
		</div>
	</div>

  <jsp:include page="footer.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>


	
	</body>

</html>
    