
$('document').ready(function()
{ 
	 alert('test');
  $("#signin-form").validate({
      rules:
   {
    login: {
      required: true,
      minlength: 5
   },
   password: {
	   required: true,
	   minlength: 8,
	   maxlength: 15
   			}
   },
   messages:
    {
            login: "please enter a valide login",
            password:{
                      required: "please provide a password",
                      minlength: "password at least have 8 characters"
                     }
            
       },
   
    submitHandler: submitForm 
       });  
    /* validation */
    
   
});