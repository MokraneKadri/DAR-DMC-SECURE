function retrieveFriendsFunc() 
{
	printHTML("#notifier","");
	retrieveFriends(1);
}

function retrieveRequestsFunc() 
{
	printHTML("#notifier","");
	retrieveRequests(2);
}

function retrieveFriends(requestv) 
{
	$.ajax({
		type : "POST",
		url : "/DAR2/friendsandrequest",
		data : {typeOfRequest : requestv},
		dataType : "JSON",
		success : ProcessRetrieveFriends,
		error : function(xhr,status,errorthrown){
			console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
		}
	});
}

function retrieveRequests(requestv) 
{
	$.ajax({
		type : "POST",
		url : "/DAR2/friendsandrequest",
		data : {typeOfRequest : requestv},
		dataType : "JSON",
		success : ProcessRetrieveRequests,
		error : function(xhr,status,errorthrown){
			console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
		}
	});
}

function ProcessRetrieveFriends(rep) 
{
	var message = "<table class=\"table\">" +
	"<tr>" +
	"<th>Nom</th><th>Prenom</th><th>Profil</th><th>Evénements</th>" +
	"</tr>";
	var endmessage ="</table>";
	var bodymessage ="";

	var jsonData =rep.result;//= JSON.parse(rep);
	if(jsonData.users == undefined || jsonData.users.length == 0){
		bodymessage = bodymessage+
		"<tr style='text-align: left'>" +
		"<td> Aucun</td>" +
		"<td> Amis</td>"+
		"<td></td>"+
		"<td></td>"+
		"<td></td>"+
		"</tr>";
	}else{
		for (var i = 0; i < jsonData.users.length; i++) {
			var user = jsonData.users[i];
			bodymessage = bodymessage+
			"<tr style='text-align: left'>" +
			"<td>"+user.name+"</td>" +
			"<td>"+user.firstname+"</td>"+
			"<td><a href=\"/DAR2/profil?user="+user.username+"\"> Voir Profil </a></td>"+
			"<td><a href=\"/DAR2/events?mode=list&member_id="+user.id+"&type=jsp\"> Participation </a></td>"+
			"<td><a href=\"/DAR2/friendsmanagement?typeOfRequest=2&id="+user.id+"\"> Supprimer Amis </a></td>"
			"</tr>";
		}
	}
	var div=(message+bodymessage+endmessage);
	func_message(div);
}

function ProcessRetrieveRequests(rep) 
{

	var message = "<table class=\"table\">" +
	"<tr>" +
	"<th>Nom</th><th>Prenom</th><th>Profil</th><th>Participation</th><th>Accepter</th><th>Refuser</th>" +
	"</tr>";
	var endmessage ="</table>";
	var bodymessage ="";

	var jsonData = rep.result;//JSON.parse(rep);
	if(jsonData.users == undefined || jsonData.users.length == 0){
		bodymessage = bodymessage+
		"<tr style='text-align: left'>" +
		"<td> Aucune</td>" +
		"<td> Requêtes</td>"+
		"<td></td>"+
		"<td></td>"+
		"<td></td>"+
		"<td></td>"+
		"</tr>";
	}else{
		for (var i = 0; i < jsonData.users.length; i++) {
			var user = jsonData.users[i];
			bodymessage = bodymessage+
			"<tr style='text-align: left'>" +
			"<td>"+user.name+"</td>" +
			"<td>"+user.firstname+"</td>"+
			"<td><a href=\"/DAR2/memberprofile.jsp?id="+user.id+"\"> Voir Profil </a></td>"+
			"<td><a href=\"/DAR2/events?mode=list&member_id="+user.id+"&type=jsp\"> Participation </a></td>"+
			"<td><a href=\"/DAR2/friendsmanagement?typeOfRequest=1&id="+user.id+"\"> Accepter Requete </a></td>"+
			"<td><a href=\"/DAR2/friendsmanagement?typeOfRequest=4&id="+user.id+"\"> Refuser Requete </a></td>"
			"</tr>";
		}
	}
	var div=(message+bodymessage+endmessage);
	func_message(div);
}


function func_message(msg)
{
	printHTML("#notifier",msg);
}


function printHTML(dom,htm)
{ 
	$(dom).html(htm);
}





