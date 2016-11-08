function searchevent(form) 
{
	var lieu = form.lieu.value;

	search(lieu);
}
function search(value) 
{
	$.ajax({
		type : "POST",
		url : "/DAR/APIsearch",
		data : {lieu : value},
		dataType : "JSON",
		success : Process,
		error : function(xhr,status,errorthrown){
			console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
		}
	});
}

function Process(rep) 
{
//	var message = "<table class=\"table\">" +
//	"<tr>" +
//	"<th>Nom</th><th>Prenom</th><th>Profil</th><th>Action</th>" +
//	"</tr>";
//	var endmessage ="</table>";
//	var bodymessage ="";

//	var jsonData =rep.result;//= JSON.parse(rep);
//	if(jsonData.users == undefined || jsonData.users.length == 0){
//	message="Aucun Résultat";
//	bodymessage="";
//	endmessage="";
//	}else{
//	for (var i = 0; i < jsonData.users.length; i++) {
//	var user = jsonData.users[i];

//	bodymessage = bodymessage+
//	"<tr>" +
//	"<td>"+user.name+"</td>" +
//	"<td>"+user.firstname+"</td>"+
//	"<td><a href=\"/DAR/memberprofile.jsp?id="+user.id+"\"> Voir Profil </a></td>"+
//	"<td><a href=\"/DAR/friendsmanagement?typeOfRequest=2&id="+user.id+"\"> Ajouter  Amis </a></td>"
//	"</tr>";
//	}
//	}
//	var div=(message+bodymessage+endmessage);
	var div=rep.html;
	func_message(div);
}

function func_message(msg)
{
	printHTML("#notifier-modal",msg);
}


function printHTML(dom,htm)
{ 
	$(dom).html(htm);
}


