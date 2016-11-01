package fr.upmc.dar.enums;

public enum UriMapping {

	
	//association url--> ressource JSP
	
	 HOME ("/jsp/index.jsp"),
	 REGISTER ("/jsp/signup.jsp"),
	 LOGIN ("/jsp/signin.jsp"),
	 RESETPASWD ("/jsp/passwordReset.jsp"),
	 CREATEEVENT ("/jsp/createEvent.jsp"),
	 CREATEGROUP ("/jsp/createGroup.jsp");
	  private String name = "";
	   
	  //Constructeur
	  UriMapping(String name){
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enum�r�
	  public String getRessourceUrl(){
	    return name.toString();
	  }
}
