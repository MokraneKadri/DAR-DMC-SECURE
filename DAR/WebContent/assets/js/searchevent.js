function searchevent(form) 
{
	var lieu = form.lieu.value;
	var ville = form.ville.value;

	search(lieu,ville);
}
function search(value1,value2) 
{
	$.ajax({
		type : "POST",
		url : "/DAR/APIsearch",
		data : {lieu : value1, ville:value2},
		dataType : "JSON",
		success : Process,
		error : function(xhr,status,errorthrown){
			console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
		}
	});
}

function Process(rep) 
{
   
	var div=rep.html;

	func_message(div);
}


function onReseltSelection(lineId){
	
	
	var establsishmentName =(document.getElementById(lineId+"name").innerText);

	var openTime =(document.getElementById(lineId+"time").innerText);

	var street =(document.getElementById(lineId+"street").innerText);
	var city =(document.getElementById(lineId+"city").innerText);
	var zip =(document.getElementById(lineId+"zip").innerText);
	
	var fullAdress = street +" "+zip+","+city;
	
	document.getElementById("eventaddress").value=fullAdress;
	document.getElementById("eventplacename").value=establsishmentName;
	 $('#myModal').modal('hide');
}
function func_message(msg)
{
	printHTML("#notifier-modal",msg);
}


function printHTML(dom,htm)
{ 
	$(dom).html(htm);
}


