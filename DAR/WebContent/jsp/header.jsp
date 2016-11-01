<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    <%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<header class="header-login-signup">

	<div class="header-limiter">
	
		<h1><a href="#"><img src="http://localhost:8080/DAR/assets/img/logo.jpg" width="85" heigth="80"></a></h1>
		
		
		<nav>
			<a href="/DAR/home">Accueil </a>
			<a href="/DAR/create_event" >Evenements</a>
			<a href="/DAR/jsp/showEvents.jsp" >A venir</a>
			<a href="/DAR/create_group">Créer un groupe</a>
			<a href="/DAR/404.jsp">Rechercher/Rejoindre</a>
		</nav>

		<ul>
		
	<c:choose>
    	<c:when test="${ not empty login}">
			<li><a href="/DAR/signin"><c:out value="${login}" /></a></li>
			<li><a href="/DAR/logout">deconnexion </a></li>
    	</c:when>
    
   	    <c:otherwise>
      
			<li><a href="/DAR/signin">Connexion</a></li>
			<li><a href="/DAR/signup">Inscription </a></li>
        </c:otherwise>
	</c:choose>
		
		</ul>

	</div>

</header>

