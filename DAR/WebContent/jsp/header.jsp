

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<script type="text/javascript" src="/DAR/assets/js/localisation.js"></script>

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
		<a class="navbar-brand" href="/DAR/home">UNI-Connect</a>
	</div>
	<div id="navbar" class="navbar-collapse collapse">
		<ul class="nav navbar-nav">

			<li class="dropdown "><a href="/DAR/events?mode=actus&limit=15">Actus</span></a>
				<!--          <li class=" dropdown"><a href="#" class="dropdown-toggle " data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Groupes <span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#">Voir les Groupes</a></li>
                                <li><a href="#">Rejoindre un groupe</a></li>
								 <li><a href="/DAR/create_group">cr�er un groupe</a></li>
                            </ul>
                        </li> -->
                        
                        <li class="dropdown "><a id="myLink"
				title="Ev�nements Proches" href="#"
				onclick="nearEvents();">Ev�nements Proches</a></li>
             <li><a href="/DAR/events?mode=new">Cr�er  </a></li>
			
				<li><a href="/DAR/jsp/eventSearch.jsp">Rechercher 
							</a></li>
							
							
			<!-- <li class="dropdown"><a href="/DAR/events?mode=showall" class="dropdown-toggle active"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false">Ev�nements <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="/DAR/jsp/eventSearch.jsp">Rechercher un
							�v�nement</a></li>
					<li><a href="/DAR/events?mode=new">Cr�er un �v�nement </a></li>
				</ul></li> -->
		</ul>

		<ul class="nav navbar-nav pull-right">
			<c:choose>
				<c:when test="${ not empty login}">

<!-- 
					<li class=" dropdown"><a href="#"
						class="dropdown-toggle active" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false">Amis
							<span class="caret"></span>
					</a> -->
						<!-- <ul class="dropdown-menu">
							<li><a href="/DAR/jsp/myfriends.jsp">Mes Amis</a></li>
							<li><a href="/DAR/jsp/pendingrequests.jsp">Requ�tes
									d'amis </a></li>
							<li><a href="/DAR/jsp/finduser.jsp">Rechercher un ami</a></li>
						</ul></li> -->

					<li class=" dropdown"><a href="#"
						class="dropdown-toggle active" data-toggle="dropdown"
						role="button" aria-haspopup="true" aria-expanded="false"><c:out
								value="${login}" /> <span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="/DAR/jsp/profil.jsp">Tableau de bord</a></li>
							<!-- <li><a href="#">Mes Param�tres</a></li> -->
							<li><a href="/DAR/logout">D�connexion</a></li>
						</ul></li>

				</c:when>

				<c:otherwise>

					<li class=" "><a href="/DAR/signin">Connexion </span></a>
					<li class=""><a href="/DAR/signup">Inscription</a></li>
				</c:otherwise>
			</c:choose>





		</ul>



	</div>
</div>
</nav>
</div>
</header>


