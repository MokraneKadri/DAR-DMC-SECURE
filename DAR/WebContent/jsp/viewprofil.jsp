<%@ page language="java" contentType="text/html; charset=UTF-8"
  pageEncoding="UTF-8"%>
<%@ page isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<title>Connexion | Espace membre</title>
	
	

<head>

 <link rel="stylesheet"	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"	crossorigin="anonymous">
 	<!-- fontAwsome  -->
 	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.6.1/css/font-awesome.min.css">
	 <link rel="stylesheet"	href="/DAR/assets/css/profilview.css" >
 	<!-- fontAwsome  -->
</head>
<body>
<div class="container">
      <div class="row">
      <div class="col-md-5  toppad  pull-right col-md-offset-3 ">
          
      </div>
        <div class="col-xs-12 col-sm-12 col-md-6 col-lg-6 col-xs-offset-0 col-sm-offset-0 col-md-offset-3 col-lg-offset-3 toppad" >
   
   
          <div class="panel panel-info">
            <div class="panel-heading">
              <h3 class="panel-title"><c:out value="${userinfos['name']}"/></h3>
            </div>
            <div class="panel-body">
              <div class="row">
                <div class="col-md-3 col-lg-3 " align="center"> <img alt="User Pic" src="http://babyinfoforyou.com/wp-content/uploads/2014/10/avatar-300x300.png" class="img-circle img-responsive"> </div>
                
             
                <div class=" col-md-9 col-lg-9 "> 
                  <table class="table table-user-information">
                    <tbody>
                      <tr>
                        <td>Nom </td>
                        <td><c:out value="${userinfos['fname']}"/></td>
                      </tr>
                      <tr>
                        <td>Prénom </td>
                        <td> <c:out value="${userinfos['lname']}"/></td>
                      </tr>
                      <tr>
                        <td>@Mail</td>
                        <td><c:out value="${userinfos['mail']}"/></td>
                      </tr>
                   
                         <tr>
                             <tr>
                        <td>Adresse postale</td>
                        <td></td>
                      </tr>
                        <tr>
                        <td>Université actuelle</td>
                        <td><c:out value="${userinfos['university']}"/></td>
                      </tr>
                      <tr>
                        <td>Cursus</td>
                        <td><c:out value="${userinfos['cursus']}"/></td>
                      </tr>
                        
                           
                      </tr>
                     
                    </tbody>
                  </table>

                </div>
              </div>
            </div>
                 <div class="panel-footer">
                                  <div class="panel-footer">
                        <a data-original-title="Broadcast Message" data-toggle="tooltip" type="button" class="btn btn-sm btn-primary"><i class="glyphicon glyphicon-user"></i></a>
                        <span class="pull-right">
                            <a href="edit.html" data-original-title="Edit this user" data-toggle="tooltip" type="button" class="btn btn-sm btn-warning"><i class="glyphicon glyphicon-edit"></i></a>
                           
                        </span>
                    </div>
                    </div>
            
          </div>
        </div>
      </div>
    </div>
	</body>
	</html>