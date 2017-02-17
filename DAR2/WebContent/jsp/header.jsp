

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.owasp.org/index.php/Category:OWASP_CSRFGuard_Project/Owasp.CsrfGuard.tld" prefix="csrf" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/DAR2/assets/js/localisation.js"></script>

<header class="navbar-wrapper">
<div class="container-fluid">
	<nav class="navbar navbar-fixed-top">
	<div class="container-fluid="navbar-header">
		<button type="button" class="navbar-toggle collapsed"
			data-toggle="collapse" data-target="#navbar" aria-expanded="false"
			aria-controls="navbar">
			<span class="sr-only">Navigation</span> <span class="icon-bar"></span>
			<span class="icon-bar"></span> <span class="icon-bar"></span>
		</button>
		<csrf:a class="navbar-brand" href="/DAR2/home">UNI-Connect</csrf:a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav">

			<li class="dropdown "><csrf:a href="/DAR2/events?mode=actus&limit=15">Actus</csrf:a>
				<!--          <li class=" dropdown"><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Groupes <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Voir les Groupes</a></li>
                                <li><a href="#">Rejoindre un groupe</a></li>
								 <li><a href="/DAR2/create_group">créer un groupe</a></li>
                            </ul>
                        </li> -->
                        
                        <li class="dropdown "><a id="myLink"
				title="Evénements Proches" href="#"
				onclick="nearEvents();">Evénements Proches</a></li>
               <li><a href="/DAR2/events?mode=new&<csrf:token uri="/DAR2/events?mode=new"/>">Créer  </a></li>
				 
				<li><a href="/DAR2/jsp/eventSearch.jsp?<csrf:token uri="/DAR2/jsp/eventSearch.jsp"/>">Rechercher 
							</a></li>
			
		</ul>

		<ul class="nav navbar-nav pull-right">
			<c:choose>
				<c:when test="${ not empty login}">



					<li class=" dropdown"><a href="#"
						class="dropdown-toggle active" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"><c:out
								value="${login}" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<!--	<li><a href="/DAR2/jsp/profil.jsp?<csrf:token uri="/DAR2/jsp/profil.jsp"/>">Tableau de bord</a></li>-->
							<li><a href="/DAR2/jsp/profil.jsp">profil</a> </li>
							<!-- <li><a href="#">Mes Paramètres</a></li> -->
						<li><a href="/DAR2/logout?"<csrf:token uri="/DAR2/logout"/>">Déconnexion</a></li>
						</ul></li>

				</c:when>

				<c:otherwise>

					<li class=" "><a href="/DAR2/signin?<csrf:token uri="/DAR2/signin"/>">Connexion </span></a> 
					<li class=""><a href="/DAR2/signup?<csrf:token uri="/DAR2/signup"/>">Inscription</a></li>
				</c:otherwise>
			</c:choose>





		</ul>



	</div>
</div>
</nav>
</div>
</header>


