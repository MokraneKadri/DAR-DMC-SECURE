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

function Process1(rep) 
{
	var div=rep.html;
	
	console.log(rep.html);
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


