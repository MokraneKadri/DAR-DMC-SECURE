
<%@page import="fr.upmc.dar.entities.User"%>
<%@page import="fr.upmc.dar.entities.Comment"%>
<%@page import="fr.upmc.dar.enums.EventVisibility"%>
<%@page import="fr.upmc.dar.dao.interfaces.IEventDao"%>
<%@page import="fr.upmc.dar.entities.Event"%>
<%@page import="fr.upmc.dar.dao.DAOFactory"%>
<%@page import="fr.upmc.dar.dao.EventDao"%>
<%@page import="fr.upmc.dar.api.GoogleApi"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Meet-ups !</title>
    
        <script src="https://code.jquery.com/jquery-3.1.1.min.js" integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
        <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
        <script type="text/javascript" src="/DAR/assets/js/localisation.js"></script>
        <script type="text/javascript" src="/DAR/assets/js/notifier.js"></script>
        
        <%
            StringBuilder content = new StringBuilder();
            String id = request.getParameter("id");
            GoogleApi api = new GoogleApi();

            if (id == null) {
                response.getWriter().print("Erreur, l'id de l'event en question n'a pas été trouvé.");
        %>

        <script>
            var redirect = settimeout(() => {
                window.location.href = "/DAR/events?mode=actus&limit=15"			
            }, 3000); 

            redirect();
        </script>
            
        <%
            }
            
           String connectedUser = (String)session.getAttribute("login");
            User cu = DAOFactory.createUserDao().findUserByUserName(connectedUser);


            String userAddress = cu.getStreet()+" "+cu.getZip()+" "+cu.getCity();
            IEventDao dao = DAOFactory.createEventDao();
            Event event = dao.getEventById(Integer.valueOf(id));
            String univAddresseunivAddresse;
            String univAddresse;
            try{
                univAddresse = event.getUniversity().getStreet() + "" + event.getUniversity().getZipCode()
                    + event.getUniversity().getCity();

            }catch(Exception e){
                univAddresse = userAddress;
            }
        %>


        <script type='text/javascript'>			
            var submission = function (form) {
                var json = {
                    mode : form.mode.value,
                    id : form.id.value
                };

                $
                .post("/DAR/events", json)
                .done( function (data) {
                    notify($.parseJSON(data), $('#notifier'));
                    if ($.parseJSON(data).success != undefined)
                        $('#participating').remove();
                });
            }
        </script>

        <script type='text/javascript'>

            function init_map (latitude, longitude, place, address) {
                var myOptions = {
                    zoom : 25,
                    center : new google.maps.LatLng(latitude, longitude),
                    mapTypeId : google.maps.MapTypeId.ROADMAP};
                map = new google.maps.Map(document.getElementById('gmap_canvas'), myOptions);
                marker = new google.maps.Marker( {
                    map: map,
                    position: new google.maps.LatLng(latitude, longitude)
                } );
                infowindow = new google.maps.InfoWindow( {
                    content:'<strong>' + place + '</strong><br>' + address +'<br>'});
                google.maps.event.addListener(marker, 'click', function () {
                    infowindow.open(map,marker);
                });
                infowindow.open(map,marker);
            }

            window.onload = function () {
                <% try { %>
                    google.maps.event.addDomListener(
                        window, 
                        'load', 
                        init_map (
                                <% try { %><%= event.getBusiness().getLatitude() %>, <% } catch (Exception e) { %> <%= 0.0 %>, <% } %>
                                <% try { %><%= event.getBusiness().getLongitude() %>, <% } catch (Exception e) { %> <%= 0.0 %>, <% } %>
                                <% try { %>"<%= event.getPlace() %>", <% } catch (Exception e) { %> <%= "undefined" %>, <% } %>
                                <% try { %>"<%= event.getAddress() %>"<% } catch (Exception e) { %> <%= "undefined" %><% } %>
                            )
                        );
                <% } catch (Exception e) {} %>
            }

        </script>

        <style>
            #gmap_canvas img {
                max-width: none !important;
                background: none !important;
            }
        </style>

        <link rel="stylesheet" href="/DAR/assets/css/notifier.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
        <link rel="stylesheet" href="/DAR/assets/css/header.css">
        <link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
        <link rel="stylesheet" href="/DAR/assets/css/main.css">
    </head>
            
    <body>
        
        <%@include file="/jsp/header.jsp"%>	

        <div class="maincontainer" id="maincontainer">

            <div class="bs-example">
                <ul class="breadcrumb">
                    <li>Uni-connect</li>
                    <li><a href="#">Accueil</a></li>
                    <li class="active"><a href="#">événement > détails </a></li>
                </ul>
            </div>

            <div class="row">
                <div class="panel-group">
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <b><%=event.getName()%> :</b>
                            </div>
                            <div class="panel-body">
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Créateur</th>
                                            <td><%=event.getCreator().getUserName()%></td>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <% if (event.getUniversity() != null) { %>
                                        <tr>
                                            <th>Université</th>
                                            <td>
                                                <%= event.getUniversity().getName() %>
                                            </td>
                                        </tr>
                                        <% } %>
                                        <tr>
                                            <th>Politique d'accès</th>
                                            <td><%=EventVisibility.eventVisibilityToString(event.getPrivacy())%></td>
                                        </tr>
                                        <tr>
                                            <th>Thème</th>
                                            <td><%=event.getTheme()%></td>
                                        </tr>
                                        <tr>
                                            <th>Description</th>
                                            <td><%=event.getDescription()%>
                                                </td>
                                        </tr>
                                        <tr>
                                            <th>Date</th>
                                            <td><%=event.getDateToString()%></td>
                                        </tr>
                                        <tr>
                                            <th>Heure de début</th>
                                            <td><%=event.getHour()%></td>
                                        </tr>
                                        <tr>
                                            <th>Places prises</th>
                                            <td><%=event.getCandidates().size()%> / <%=event.getPlaces()%></td>
                                        </tr>
                                        <tr>
                                            <th>Participants</th>
                                            <td>
                                                <%
                                                    for (User candidate : event.getCandidates()) {
                                                %> [<%=candidate.getUserName()%>] <%
                                                    }
                                                %>
                                            </td>
                                        </tr>
                                        <tr>
                                            <th>Lieu</th>
                                            <td><%=event.getPlaceType()%></td>
                                        </tr>
                                        <tr>
                                            <th>Lieu de rendez-vous</th>
                                            <td><%=event.getPlace()%></td>
                                        </tr>
                                        <tr>
                                            <th>Adresse complète</th>
                                            <td><%=event.getAddress()%></td>
                                        </tr>
                                        <tr>
                                            <th>Commentaires</th>
                                            <td><%=event.getComments().size()%> au total</td>
                                        </tr>
                                        <tr>
                                            <td>
                                                <div id="notifier"></div>
                                            </td>
                                            <td> 
                                                <%
                                                    User user = DAOFactory.createUserDao()
                                                            .findUserByUserName((String) request.getSession().getAttribute("login"));
                                                    boolean isParticipating = false;
                                                    for (User u : event.getCandidates())
                                                        if (user.getId() == u.getId())
                                                            isParticipating = true;
                                                    if (!isParticipating && event.getCandidates().size() < event.getPlaces()) { // IF BEGIN
                                                 %>
                                                <form id='participating' style='text-align: right'
                                                    action='javascript:return(0)' method='POST'
                                                    onsubmit='submission(this)'>
                                                    <input type='hidden' name='mode' value='participate' /> <input
                                                        type='hidden' name='id' value='<%=event.getId()%>' />
                                                    <button type="submit" class='btn btn-primary'> Participer </button>
                                                </form> 
                                                <% } // IF END %>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>
                            <div class="panel-footer">
                                <a href="#com"> Voir les comentaires </a>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="panel panel-default">
                            <div class="panel-heading"> Accès </div>
                        </div>
                        <div class="panel-body">
                            <div id="map"></div>
                            <script src='https://maps.googleapis.com/maps/api/js?key=AIzaSyAAWHzdrBHX4rFlhit9v57wDrSjVYfSUbA'></script>
                            <div style='overflow: hidden; height: 400px; width: 550px;'>
                                <div id='gmap_canvas' style='height: 400px; width: 500px;'>
                                </div>
                                <div>
                                    <small><a href="http://embedgooglemaps.com">google maps carte</a></small>
                                </div>
                            </div>
                        </div>
                        <div class="panel-footer">
                            <% try { %>
                            <b>infos complémentaires:</b>
                            <p>
                                Distance à pied depuis votre université :
                                <%=api.walkingDistance(univAddresse, event.getAddress())%></p>
                            <p>
                                Temps de marche estimé :
                                <%=api.walkingTime(univAddresse, event.getAddress())%></p>
                            <p>
                                Distance à pied depuis votre domicile :
                                <%=api.walkingDistance(userAddress, event.getAddress())%></p>
                            <p>
                                Temps de marche estimé :
                                <%=api.walkingTime(userAddress, event.getAddress())%></p>
                            <% } catch (Exception e) {} %>
                        </div>
                    </div>
                </div>
            </div>

            <div class='well' id="com">
                <div class='pull-left'
                    style='padding-right: 27.5px; border-right: solid #aaaaaa 1px; height: 50px;'
                    href='#'>
                    <h4 class='media-heading'> Nouveau commentaire </h4>
                    <p class='text-left'></p>
                </div>
                <form action='/DAR/events' method='POST'>
                    <input  name='mode' 
                            type='hidden' 
                            value='comment' /> 
                    <input  name='id' 
                            type='hidden' 
                            value='<%=event.getId()%>' />
                    <div style='height: 100%; width: 100%; padding-left: 240px;'>
                        <input  name = 'content' 
                                type = 'textarea'
                                style = 'border: 1px solid lightgrey; border-right: none; border-radius: 5px 0px 0px 5px; width: 85%; height: 50px; padding-left: 10px;'
                                placeholder = 'Nouveau commentaire, pas plus de 255 caractères ... (pour le moment)'
                                pattern = '{255}' 
                                required /> 
                         <input  class="btn btn-info btn-lg"  
                                type='submit'    
                                value='Commenter' />
                                                 </div>
                </form>
            </div>

            <%
                for (Comment comment : event.getComments()) {
            %>

            <div class="well">
                <div class="media">
                    <div class="pull-left"
                        style="padding-right: 20px; border-right: solid #aaaaaa 1px">
                        <h4 class="media-heading">
                            <%=comment.getCreator().getUserName()%>
                        </h4>
                        <p class="text-left">
                            le :
                            <%=comment.getCommentDate()%></p>
                    </div>
                    <div style="vertical-align: middle; text-align: justify;">
                        <div style="height: 100%; width: 100%; padding-left: 240px;">
                            <%=comment.getCommentContent()%></div>
                    </div>
                </div>
            </div>

            <%
                }
            %>
        </div>

        <%@include file="/jsp/footer.jsp"%>

    </body>

</html>