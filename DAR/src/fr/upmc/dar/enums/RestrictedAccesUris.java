package fr.upmc.dar.enums;

public enum RestrictedAccesUris {

	
	 CREATEGROUP ("/DAR/create_group"),
	 CREATEEVENT ("/DAR/create_event"),
	 USERLOGIN ("/DAR/signin"),
	 USERSIGNUP("/DAR/signup"),
	PROFIL("/DAR/jsp/profil.jsp"),
	EVENT("/DAR/events?mode=event&id=");
	  private String name = "";
	  //Constructeur
	RestrictedAccesUris(String name){
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enuméré
	  public String getRessourceUrl(){
	    return name.toString();
	  }
}
