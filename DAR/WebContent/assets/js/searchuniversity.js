function searchuniversity(form) 
{
	searchu(form.termuni.value);
}
function searchu(value1) 
{
	$.ajax({
		type : "POST",
		url : "/DAR/APIsearchUni",
		data : {term : value1},
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
	console.log(rep);
	console.log(rep.html);
	func_message(div);
}

function func_message(msg)
{
	printHTML("#notifier-modal-university",msg);
}


function printHTML(dom,htm)
{ 
	$(dom).html(htm);
}


