package fr.upmc.dar.enums;

public enum UriMapping {

	
	//association url--> ressource JSP
	
	 HOME ("/home"),
	 REGISTER ("/JSP/signup.jsp"),
	 LOGIN ("/JSP/signin.jsp"),
	 RESETPASWD ("/JSP/passwordReset.jsp"),
	 CREATEEVENT ("/JSP/createEvent.jsp");
	 
	  private String name = "";
	   
	  //Constructeur
	  UriMapping(String name){
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enuméré
	  public String getRessourceUrl(){
	    return name.toString();
	  }
}
