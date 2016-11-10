function nearEvents(){
	if (navigator.geolocation)
		navigator.geolocation.getCurrentPosition(successCallback, errorCallback);
	else
		alert("Votre navigateur ne prend pas en compte la géolocalisation HTML5");
}


function successCallback(position){
	{
		$.ajax({
			type : "POST",
			url : "/DAR/events",
			data : {mode : "GPS", lat : position.coords.latitude, lon : position.coords.longitude},
			dataType : "JSON",
			success : ProcessEventsNear,
			error : function(xhr,status,errorthrown){
				console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
			}
		});
	}
};  

function errorCallback(error){
	switch(error.code){
	case error.PERMISSION_DENIED:
		alert("L'utilisateur n'a pas autorisé l'accès à sa position");
		break;          
	case error.POSITION_UNAVAILABLE:
		alert("L'emplacement de l'utilisateur n'a pas pu être déterminé");
		break;
	case error.TIMEOUT:
		alert("Le service n'a pas répondu à temps");
		break;
	}
};


function ProcessEventsNear(rep) 
{
}

