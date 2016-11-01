<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Création de groupe |uni-connect</title>
</head>
<body>
	<form action="/DAR/create_group" method="POST">
		
		<label for="groupe_name">Nom de groupe</label> <input type="text" name="group_name" required/>
		<label for="creator_id">Id de créateur</label> <input type="text" name="creator_id" pattern="[0-9]{32}" required/>
		<label for="creator_name">Nom créateur</label> <input type="text" name="creator_name" required/>
		<label for="group_description">Description groupe</label> <input type="text" name="group_description" required/>
		<input type="submit" value="Créer"/>
		
	</form>
</body>
</html>