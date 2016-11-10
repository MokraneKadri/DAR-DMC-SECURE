function searchevent(form) 
{
	var lieu = form.lieu.value;
	var ville = form.ville.value;

	var context = document.getElementById("lieu").value;
	document.getElementById("place_type").value=context;
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


function onYelpResultSelection(lineId){
	
	
	var establsishmentName =(document.getElementById(lineId+"name").innerText);

	var openTime =(document.getElementById(lineId+"time").innerText);

	var street =(document.getElementById(lineId+"street").innerText);
	var city =(document.getElementById(lineId+"city").innerText);
	var zip =(document.getElementById(lineId+"zip").innerText);
	
	var fullAdress = street +" "+zip+","+city;
	
	document.getElementById("address").value=fullAdress;
	document.getElementById("place_name").value=establsishmentName;
	var placetype =document.getElementById("place_type").value;
	var idb = (document.getElementById(lineId+"idb").value);
	//alert(idb);
    document.getElementById("business_id").value=idb;
	
    $("#eventForm").valid();
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

function searchuniversity(forme) 
{
	var form = document.getElementById("uni");
	searchu(form.termuni.value);
}
function searchu(value1) 
{
	$.ajax({
		type : "POST",
		url : "/DAR/APIsearchUni",
		data : {term : value1},
		dataType : "JSON",
		success : Process1,
		error : function(xhr,status,errorthrown){
			console.log(JSON.stringify(xhr + " " + status + " " + errorthrown));
		}
	});
}

function onUniResultSelection(idres){
	var univName =(document.getElementById(idres+"nameu").innerText);
	var univstreet =(document.getElementById(idres+"streetu").innerText);
	var univzip =(document.getElementById(idres+"zipu").innerText);
	var idu = (document.getElementById(idres+"idu").value);
	//alert(idu);

	var fullUnivQualification =univName +":"+ univstreet +" "+univzip;
	 document.getElementById("university_id").value=idu;
	document.getElementById("university").value=fullUnivQualification;
	//$("#eventForm").university.valid();//revalidation du formulaire
	
	 $('#myModalUn').modal('hide');
}
function Process1(rep) 
{
	var div=rep.html;
	
	//console.log(rep.html);
	func_message1(div);
}

function func_message1(msg)
{
	printHTML1("#notifier-modal-university",msg);
}


function printHTML1(dom,htm)
{ 
	$(dom).html(htm);
}



