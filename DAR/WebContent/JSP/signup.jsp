<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>

<head>

	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">

	<title>template v0.1</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">


    <link rel="stylesheet" href="../assets/css/header.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/SignupStyle.css">
	<link rel="stylesheet" href="../assets/css/footer.css">

	<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>


</head>
<body>

  <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>
</head>
	<div class="container">
		<div class="row main">
			<div class="panel-heading">

			</div>
			<div class="main-login main-center">
				<form class="form-horizontal"  method="post"  action="/signup">

					<div class="form-group">
						<label for="name" class="cols-sm-2 control-label">Votre Nom</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-user fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="name" id="name"  placeholder="Entrez votre nom"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="email" class="cols-sm-2 control-label">Votre Email</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-envelope fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="email" id="email"  placeholder="Entrez votre Email"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="username" class="cols-sm-2 control-label">Nom d'utilisateur</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-users fa" aria-hidden="true"></i></span>
								<input type="text" class="form-control" name="username" id="username"  placeholder="Entrez votre nom d'utilisateur"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="password" class="cols-sm-2 control-label">Mot de Passe</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<input type="password" class="form-control" name="password" id="password"  placeholder="Saisissez un mot de passe"/>
							</div>
						</div>
					</div>

					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Confirmer mot de passe</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-lock fa-lg" aria-hidden="true"></i></span>
								<input type="password" class="form-control" name="confirm" id="confirm"  placeholder="Re-Saisissez un mot de passe"/>
							</div>
						</div>
					</div>
					<div class="form-group">
						<label for="confirm" class="cols-sm-2 control-label">Etablissement</label>
						<div class="cols-sm-10">
							<div class="input-group">
								<span class="input-group-addon"><i class="fa fa-university" aria-hidden="true"></i></span>
								<select id="university" name="uni" class="form-control">
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
						<button type="button" class="btn btn-primary btn-lg btn-block login-button">S'enregistrer</button>
					</div>
					<div class="login-register">
						<a href="index.php">Connection</a>
					</div>
				</form>
			</div>
		</div>
	</div>

  <jsp:include page="footer.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>


	
	</body>

</html>
    