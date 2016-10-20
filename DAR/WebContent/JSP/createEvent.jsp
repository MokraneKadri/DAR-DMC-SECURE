<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

 <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
 
    <title>creer un nouvel evenement </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

    <link rel="stylesheet" href="../assets/css/header.css">
    <link rel="stylesheet" type="text/css" href="../assets/css/SignupStyle.css">
	<link rel="stylesheet" href="../assets/css/footer.css">

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">


<link href='http://fonts.googleapis.com/css?family=Cookie' rel='stylesheet' type='text/css'>


</head>
<body>

  <jsp:include page="header.jsp">
  <jsp:param name="active" value="createEvent" />
  </jsp:include>


<div class="container">
    <div class="row main">
        <div class="panel-heading">
            <div class="panel-title text-center">
                <h1 class="title">Créer un évenement</h1>
                <hr />
            </div>
        </div>
        <div class="main-login main-center">
            <form class="form-horizontal" method="post" action="#">

                <div class="form-group">
                    <label for="eventName" class="cols-sm-2 control-label">intitulé</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-tags" aria-hidden="true"></i></span>
                            <input type="text" class="form-control" name="name" id="eventName"  placeholder="nom de l'évenement"/>
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
                                        <option value="3">université</option>
                                        <option value="4">Autres...</option>
                             </select>

                         </div>
                    </div>
                </div>


                <div class="form-group">
                    <label for="eventAdress" class="cols-sm-2 control-label">Adresse Complète</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-map" aria-hidden="true"></i> </span>
                            <input type="text" class="form-control" name="eventAdress" id="eventAdress"  placeholder="Precisez l'adresse de l'event" />
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="eventTheme" class="cols-sm-2 control-label">Thème</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-thumb-tack" aria-hidden="true"></i></span>
                            <select id="eventTheme" name="eventTheme" class="form-control">
                                <option value="" disabled selected> ...</option>
                                <option value="1">Moulin à Parole</option>
                                <option value="2">échange linguistique/culturelle</option>
                                <option value="3">relatifs au études</option>
                                <option value="4">Autres...</option>
                            </select>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label for="EventMaxInvite" class="cols-sm-2 control-label">Adresse Complète</label>
                    <div class="cols-sm-10">

                             <div class="input-group">
                                 <span class="input-group-addon"><i class="fa fa-book" aria-hidden="true"></i></span>
                                 <input type="number" class="form-control" name="EventMaxInvite" id="EventMaxInvite"  placeholder="nombre max d'invités" maxlength="3" max="50" />
                             </div>

                    </div>
                </div>


                <div class="form-group">
                    <label for="eventPolicy" class="cols-sm-2 control-label">Confidentialité</label>
                    <div class="cols-sm-10">
                        <div class="input-group">
                            <span class="input-group-addon"><i class="fa fa-user-secret" aria-hidden="true"></i></span>
                            <select id="eventPolicy" name="eventPolicy" class="form-control" >
                                <option value="" disabled selected> ...</option>
                                <option value="1">Public (Tout le monde a accés)</option>
                                <option value="2">Amis uniquement </option>
                                <option value="3">intra-university (étudiant du meme compus)</option>

                            </select>
                        </div>
                    </div>
                </div>


                <div class="form-group"></div>



                    <div class="form-group ">
                    <button type="button" class="btn btn-primary btn-lg btn-block login-button"><i class="fa fa-bell" aria-hidden="true"></i> Créer cet Evenement</button>
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