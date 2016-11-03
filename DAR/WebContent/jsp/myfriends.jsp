<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
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
	href="http://localhost:8080/DAR/assets/css/signup.css">
<link href='http://fonts.googleapis.com/css?family=Cookie'
	rel='stylesheet' type='text/css'>

<script type="text/javascript" src="/DAR/assets/js/friendsandrequest.js"></script>
<script type="text/javascript" src="/DAR/assets/js/jquery-3.1.1.js"></script>
<title>Mes Amis</title>
</head>
<body onload="retrieveFriendsFunc()">
	<jsp:include page="header.jsp" />
	<div id='page'>
		<div class='layer-center'>
			<div id='notifier'></div>
		</div>
	</div>
	<jsp:include page="footer.jsp" />
</body>
</html>