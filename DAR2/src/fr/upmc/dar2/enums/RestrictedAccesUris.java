package fr.upmc.dar2.enums;

public enum RestrictedAccesUris {

	
	 CREATEGROUP ("/DAR2/create_group"),
	 CREATEEVENT ("/DAR2/create_event"),
	 USERLOGIN ("/DAR2/signin"),
	 USERSIGNUP("/DAR2/signup"),
	PROFIL("/DAR2/jsp/profil.jsp"),
	EVENT("/DAR2/events?mode=event&id="),
	FRIENDSLOOKUP("/DAR2/jsp/finduser.jsp"),
	MYFRIENDS("/DAR2/jsp/myfriends.jsp");
	
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
