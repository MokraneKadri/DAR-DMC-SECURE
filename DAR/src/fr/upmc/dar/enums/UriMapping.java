package fr.upmc.dar.enums;

public enum UriMapping {

	
	//association url--> ressource JSP
	
	 HOME ("/jsp/index.jsp"),
	 REGISTER ("/jsp/signup.jsp"),
	 LOGIN ("/jsp/signin.jsp"),
	 RESET_PASSWORD ("/jsp/passwordReset.jsp"),
	 CREATE_EVENT ("/jsp/createEvent.jsp"),
	 CREATE_GROUP ("/jsp/createGroup.jsp");
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
