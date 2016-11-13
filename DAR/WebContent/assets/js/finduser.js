function finduser(form) 
{
	var search = form.search.value;
	var val = form.value.value;

	var ok = checkInput(search, val);
	if (ok) 
	{
		printHTML("#notifier","");
		findUserJS(search, val);
	}
}



function checkInput(search, val) 
{
	if(val.length==0)
	{
		func_message("Champ Vide");
		return false;
	}
	else 
	{
		return true;
	}
}


function findUserJS(searchv, valuev) 
{
	$.ajax({
		type : "POST",
		url : "/DAR/finduser",
		data : {search : searchv, value : valuev},
		dataType : "JSON",
		success : ProcessFindUser,
		error : function(xhr,status,errorthrown){
			console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
		}
	});
}

function ProcessFindUser(rep) 
{
	var message = "<table class=\"table\" style=\"width:100%\" cellpadding = 50 cellspacing=50 border-collapse: collapse><thead>" +
	"<tr>" +
	"<th style=\"marging-left:10px\">Nom</th><th style=\"marging-left:10px\">Prenom</th><th style=\"marging-left:10px\">Profil</th><th colspan=\"1\">Action</th>" +
	"</tr></thead><tbody>";
	var endmessage ="</table>";
	var bodymessage ="";

	var jsonData =rep.result;//= JSON.parse(rep);
	if(jsonData.users == undefined || jsonData.users.length == 0){
		message="Aucun Résultat";
		bodymessage="";
		endmessage="";
	}else{
		for (var i = 0; i < jsonData.users.length; i++) {
			var user = jsonData.users[i];
			console.log(user);
			if(user.id==rep.idv && jsonData.users.length==1){
				message="Aucun Résultat";
				bodymessage="";
				endmessage="";
			}else{
				bodymessage = bodymessage+
				
				"<tr>" +
				"<td>"+user.name+"</td>" +
				"<td>"+user.firstname+"</td>"+
				"<td><a href=\"/DAR/profil?user="+user.username+"\"> Voir Profil </a></td>"+
				"<td><a href=\"/DAR/friendsmanagement?typeOfRequest=3&id="+user.id+"\"> Envoyer Demande </a></td>"
				"</tr>";
			}
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





