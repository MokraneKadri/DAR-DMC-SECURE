<%@page import="fr.upmc.dar.entities.Comment"%>
<%@page import="fr.upmc.dar.enums.EventVisibility"%>
<%@page import="fr.upmc.dar.dao.interfaces.IEventDao"%>
<%@page import="fr.upmc.dar.entities.Event"%>
<%@page import="fr.upmc.dar.dao.DAOFactory"%>
<%@page import="fr.upmc.dar.dao.EventDao"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Event</title>

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
<link rel="stylesheet" href="/DAR/assets/css/header.css">
<!-- style du header  -->
<link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
<!-- style du content  -->
<link rel="stylesheet" href="/DAR/assets/css/main.css">

</head>
<body>

	<%@include file="/jsp/header.jsp"%>
	
	<%
		StringBuilder content = new StringBuilder();
		String id = request.getParameter("id");
		
		if (id == null) {
			content.append("<p> Nous sommes d�sol� mais l'�v�nement en question n'a pas �t� trouv� </p>");
		} else {
			IEventDao dao = DAOFactory.createEventDao();
			Event event = dao.getEventById(Integer.valueOf(id));
			
			content.append("<div class='maincontainer'>");
			content.append("<div class='well'>");
			content.append("<div class='media'>");
			content.append("<div class='pull-left' href='#'>");
			content.append("<h4 class='media-heading'>");
			content.append("<a href='#'>");
			content.append(event.getEventName());
			content.append("</a>");
			content.append("</h4>");
			content.append("<p class='text-right'>cr�� par : ");
			content.append(event.getCreator().getUserName());
			content.append("</p>");
			content.append("</div>");
			content.append("<p class='text-right'><a class='btn btn-primary'>Je participe</a></p>");
			content.append("<p class='text-right'><a class='btn btn-info'> D�tails >> </a></p>");
			content.append("<p> Visibilit� : ");
			content.append(EventVisibility.eventVisibilityToString(event.getEventprivacy()));
			content.append("</p><p> Description : ");
			content.append(event.getEventDescription());
			content.append("</p><p> Th�me : ");
			content.append(event.getEventTheme());
            content.append("</p><ul class='list-inline list-unstyled'>");
            content.append("<li><span><i class='glyphicon glyphicon-calendar'> </i> ");
            content.append(event.getEventDate());
            content.append("</span></li><li>|</li><span><i class='glyphicon glyphicon-comment'></i> ");
            content.append(event.getComments().size());
            content.append(" <a href='#'>comments</a></span><li>|</li><li><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star'></span><span class='glyphicon glyphicon-star-empty'></span></li></ul></div></div>");

			
			content.append("<div class='well'>");
			
			content.append("<div class='pull-left' style='padding-right: 27.5px; border-right: solid #aaaaaa 1px; height: 50px;' href='#'>");
			content.append("<h4 class='media-heading'>");
			content.append("Nouveau commentaire");
			content.append("</h4>");
			content.append("<p class='text-left'>");
			content.append("</p>");
			content.append("</div>");
			
			content.append("<form action='/DAR/events' method='POST'>");

            content.append("<input name='mode' type='hidden' value='comment' />");
            content.append("<input name='date' type='hidden' value='new Date()' />");
            content.append("<input name='id' type='hidden' value='" + event.getId() + "' />");
			
            content.append("<div style='height: 100%; width:100%; padding-left:240px;'>");

            content.append("<input name='content' type='textarea' style='border: 1px solid lightgrey; border-right: none; border-radius: 5px 0px 0px 5px; width: 85%; height: 50px; padding-left: 10px;' placeholder='Nouveau commentaire, pas plus de 255 caract�res ... (pour le moment)' pattern='{255}'/>");
            content.append("<input style='height: 50px; width: 15%; border: 1px solid #b3bfd1; border-radius: 0px 5px 5px 0px; background: #cedbef; color: white;' type='submit' value='Commenter' />");

            content.append("</div>");
            
            content.append("</form>");
            
            content.append("</div>");
            
            for (Comment c : event.getComments()) {
    			content.append("<div class='well'>");
    			content.append("<div class='media'>");
    			
    			content.append("<div class='pull-left' style='padding-right: 20px; border-right: solid #aaaaaa 1px' href='#'>");
    			content.append("<h4 class='media-heading'>");
    			content.append("Commentaire de ");
    			content.append(event.getCreator().getUserName()); // A modifier !
    			content.append("</h4>");
    			content.append("<p class='text-left'>");
    			content.append("le : ");
    			content.append(c.getCommentDate().toString());
    			content.append("</p>");
    			content.append("</div>");
    			content.append("<div style='vertical-align: middle; text-align: justify;'>");
    			content.append("<div style='height: 100%; width:100%; padding-left:240px;'>");
    			content.append(c.getCommentContent());
    			//content.append("Qu'est-ce que le Lorem Ipsum?Le Lorem Ipsum est simplement du faux texte employ� dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les ann�es 1500, quand un peintre anonyme assembla ensemble des morceaux de texte pour r�aliser un livre sp�cimen de polices de texte. Il n'a pas fait que survivre cinq si�cles, mais s'est aussi adapt� � la bureautique informatique, sans que son contenu n'en soit modifi�. Il a �t� popularis� dans les ann�es 1960 gr�ce � la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus r�cemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.Pourquoi l'utiliser?On sait depuis longtemps que travailler avec du texte lisible et contenant du sens est source de distractions, et emp�che de se concentrer sur la mise en page elle-m�me. L'avantage du Lorem Ipsum sur un texte g�n�rique comme 'Du texte. Du texte. Du texte.' est qu'il poss�de une distribution de lettres plus ou moins normale, et en tout cas comparable avec celle du fran�ais standard. De nombreuses suites logicielles de mise en page ou �diteurs de sites Web ont fait du Lorem Ipsum leur faux texte par d�faut, et une recherche pour 'Lorem Ipsum' vous conduira vers de nombreux sites qui n'en sont encore qu'� leur phase de construction. Plusieurs versions sont apparues avec le temps, parfois par accident, souvent intentionnellement (histoire d'y rajouter de petits clins d'oeil, voire des phrases embarassantes).D'o� vient-il?Contrairement � une opinion r�pandue, le Lorem Ipsum n'est pas simplement du texte al�atoire. Il trouve ses racines dans une oeuvre de la litt�rature latine classique datant de 45 av. J.-C., le rendant vieux de 2000 ans. Un professeur du Hampden-Sydney College, en Virginie, s'est int�ress� � un des mots latins les plus obscurs, consectetur, extrait d'un passage du Lorem Ipsum, et en �tudiant tous les usages de ce mot dans la litt�rature classique, d�couvrit la source incontestable du Lorem Ipsum. Il provient en fait des sections 1.10.32 et 1.10.33 du  De Finibus Bonorum et Malorum  (Des Supr�mes Biens et des Supr�mes Maux) de Cic�ron. Cet ouvrage, tr�s populaire pendant la Renaissance, est un trait� sur la th�orie de l'�thique. Les premi�res lignes du Lorem Ipsum,  Lorem ipsum dolor sit amet... , proviennent de la section 1.10.32.L'extrait standard de Lorem Ipsum utilis� depuis le XVI� si�cle est reproduit ci-dessous pour les curieux. Les sections 1.10.32 et 1.10.33 du De Finibus Bonorum et Malorum de Cic�ron sont aussi reproduites dans leur version originale, accompagn�e de la traduction anglaise de H. Rackham (1914).");
    			content.append("</div>");
    			content.append("</div>");
    
                content.append("</div></div>");
            }
            
            content.append("</div>");
		}
	
	%>

	<%= content.toString() %>

	<%@include file="/jsp/footer.jsp"%>

</body>
</html>