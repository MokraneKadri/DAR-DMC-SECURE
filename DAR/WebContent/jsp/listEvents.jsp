<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title> Liste des Evenements </title>

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
   <link rel="stylesheet" href="/DAR/assets/css/header.css">
   <!-- style du header  -->
   <link rel="stylesheet" href="/DAR/assets/css/pageFooter.css">
     <!-- style du content  -->
   <link rel="stylesheet" href="/DAR/assets/css/main.css">

   	<!-- Fin Styles  -->	
	
	
	</head>
<body>

 <jsp:include page="header.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>

	
	
		<div class="maincontainer">
	
</div>
		<script type="text/javascript">
		
			 
		$( document ).ready( function () {
		
			
				
               
                    var data = $("").serialize();

                    $.ajax({

                        type: 'POST',
                        url: '/DAR/eventList',
                        data: data,
						dataType:'json',
                        beforeSend: function () {
                        	       
                        
                        },
                       error: function(){
                                 alert('erreur');
                                             },
                                                      
                        
                        success: function (data) {
                        	
                        	//var dataRR =JSON.stringify(data);
                        //	console.log(data);
                        	console.log("profondeur0"+data.listEvents[0]);
                        	//var eventdetails = $.parseJSON(data.listEvents[0]);
                        		// console.log("profondeur0"+eventdetails);
                        	for(var i = 0;i< data.listEvents.length;i++ ){
                        		
                        	
                        			$.each(data.listEvents[i], function(j, EV){
                        			
   										var eventdetails = $.parseJSON(EV);
   										console.log("iteration n+" +i); 
   								
                        			
                           
   										 $("div.maincontainer").append('<div class="well">'+
                                    	      '<div class="media">'+
                                    	      '	<a class="pull-left" href="#">'+
                                    	    		
                                    	  		'<div class="media-body">'+
                                    	    		'<h4 class="media-heading">'+ eventdetails.eventName+'</h4>'+
                                    	         ' <p class="text-right">créer par :'+ eventdetails.creator.userName+'</p>'+
                                    	         '<p></p><p class="text-right"><a class="btn btn-primary">Je participe</a>'+
                                    	         '<p></p><p class="text-right"><a class="btn btn-info"> Details >></a>'+
                                    	          '<p></b>Description :</p>'+
                                    	          '<p>'+eventdetails.eventDescription+'</p>'+
                                    	          '<p></b>Thème :</p>'+
                                    	          '<p>'+eventdetails.eventTheme+'</p>'+
                                    	         ' <ul class="list-inline list-unstyled">'+
                                    	  			'<li><span><i class="glyphicon glyphicon-calendar"></i>' +eventdetails.eventDate+' </span></li>'+
                                    	            '<li>|</li>'+
                                    	            '<span><i class="glyphicon glyphicon-comment"></i> 2 comments</span>'+
                                    	            '<li>|</li>'+
                                    	            '<li>'+
                                    	             '  <span class="glyphicon glyphicon-star"></span>'+
                                    	              '          <span class="glyphicon glyphicon-star"></span>'+
                                    	               '         <span class="glyphicon glyphicon-star"></span>'+
                                    	                '        <span class="glyphicon glyphicon-star"></span>'+
                                    	                 '       <span class="glyphicon glyphicon-star-empty"></span>'+
                                    	            '</li>'+
                                    	            '<li>|</li>'+
                                    	            '<li>'+
                                    	            '<!-- Use Font Awesome http://fortawesome.github.io/Font-Awesome/ -->'+
                                    	             ' <span><i class="fa fa-facebook-square"></i></span>'+
                                    	              '<span><i class="fa fa-twitter-square"></i></span>'+
                                    	              '<span><i class="fa fa-google-plus-square"></i></span>'+
                                    	            '</li>'+
                                    				'</ul>'+
                                    	       '</div>'+
                                    	    '</div>'+
                                    	  
                                    '	  </div>');

                                   
                               
                                    console.log("i eqauls+" +i); 
                        		
                        	});
                    } 
                        }
                        
                    });
                  
		} );
		

	</script>

	
 <jsp:include page="footer.jsp">
  <jsp:param name="active" value="signin" />
  </jsp:include>
	
</body>
</html>
