package fr.upmc.dar2.enums;

public enum UriMapping {

	
	//association url--> ressource JSP
	
	 HOME ("/jsp/index.jsp"),
	 REGISTER ("/jsp/signup.jsp"),
	 LOGIN ("/jsp/signin.jsp"),
	 RESET_PASSWORD ("/jsp/passwordReset.jsp"),
	 CREATE_EVENT ("/jsp/createEvent.jsp"),
	 CREATE_GROUP ("/jsp/createGroup.jsp"),
	EVENTSLIST("/events?mode=list&type=jsp"),//("/jsp/listEvents.jsp"),
	POSTPASSWDRESET("/jsp/confirmation.jsp"),
	POSTSIGNUPRESET("/jsp/confirmation.jsp"),
	EVENTDETAILS("/jsp/eventDetails.jsp");
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
