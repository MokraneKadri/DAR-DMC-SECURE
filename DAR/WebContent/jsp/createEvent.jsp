<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 
    <title>creer un nouvel evenement </title>
	
	
	<!--  Scripts  -->
	<!-- JQuery  -->
    <script  src="https://code.jquery.com/jquery-3.1.1.min.js"   integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8=" crossorigin="anonymous"></script>
    <!-- Bootstrap  -->
    <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
   <!-- JQuery Validate  -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.15.1/jquery.validate.min.js"></script>
    <!-- Fin  Scripts  -->
    
    <!-- Styles  -->
    <!-- bootstrap  -->
    <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
 	<!-- fontAwsome  -->
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
   <!-- style du footer  -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/pageFooter.css"">
   <!-- style du header  -->
   <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/header.css"">
    <link rel="stylesheet" href="http://localhost:8080/DAR/assets/css/main.css"">
   
   	<!-- Fin Styles  -->	
<body>

  <jsp:include page="header.jsp">
  <jsp:param name="active" value="createEvent" />
  </jsp:include>


<div class="container" style="margin-top: 70px">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Cr�er un �venement</h1>
                <hr />
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="/DAR/create_event">

                <div class="form-group">
                    <label for="eventName" class="cols-sm-2 control-label">intitul�</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-tags" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="name" id="eventName"  placeholder="nom de l'�venement"/>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="eventDate" class="cols-sm-2 control-label">Date</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i> </span>
                            <input type="date" class="form-control" name="eventDate" id="eventDate"  />
                        </div>
                    </div>
                </div>


                <div class="form-group">
                    <label for="eventLocation" class="cols-sm-2 control-label">Lieu</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                             <span class="input-group-addon"><i class="fa fa-location-arrow" aria-hidden="true"></i></span>
                             <select id="eventLocation" name="eventPlace" class="form-control" autocomplete="on">
                                        <option value="" disabled selected> ...</option>
                                        <option value="1">Bar/Restaurant</option>
                                        <option value="2">Lieu/place publics</option>
                                        <option value="3">universit�</option>
                                        <option value="4">Autres...</option>
                             </select>

                         </div>
                    </div>
                </div>


                <div class="form-group">
                    <label for="eventAdress" class="cols-sm-2 control-label">Adresse Compl�te</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i> </span>
                            <input type="text" class="form-control" name="eventAdress" id="eventAdress"  placeholder="Precisez l'adresse de l'event" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="eventTheme" class="cols-sm-2 control-label">Th�me</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-thumb-tack" aria-hidden="true"></i></span>
                            <select id="eventTheme" name="eventTheme" class="form-control">
                                <option value="" disabled selected> ...</option>
                                <option value="1">Moulin � Parole</option>
                                <option value="2">�change linguistique/culturelle</option>
                                <option value="3">relatifs au �tudes</option>
                                <option value="4">Autres...</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="EventMaxInvite" class="cols-sm-2 control-label">Adresse Compl�te</label>
                    <div class="cols-sm-10">

                             <div class="input-group">
                                 <span class="input-group-addon"><i class="fa fa-book" aria-hidden="true"></i></span>
                                 <input type="number" class="form-control" name="EventMaxInvite" id="EventMaxInvite"  placeholder="nombre max d'invit�s" maxlength="3" max="50" />
                             </div>

                    </div>
                </div>


                <div class="form-group">
                    <label for="eventPolicy" class="cols-sm-2 control-label">Confidentialit�</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user-secret" aria-hidden="true"></i></span>
                            <select id="eventPolicy" name="eventPolicy" class="form-control" >
                                <option value="" disabled selected> ...</option>
                                <option value="1">Public (Tout le monde a acc�s)</option>
                                <option value="2">Amis uniquement </option>
                                <option value="3">intra-university (�tudiant du meme compus)</option>

                            </select>
                        </div>
                    </div>
                </div>


                <div class="form-group"></div>



                    <div class="form-group ">
                    <button type="button" class="btn btn-primary btn-lg btn-block login-button" onclick="submit()"><i class="fa fa-bell" aria-hidden="true"></i> Cr�er cet Evenement</button>
                </div>

            </form>
        </div>
    </div>
</div>
  <jsp:include page="footer.jsp">
  <jsp:param name="active" value="createEvent" />
  </jsp:include>


</body>
</html>