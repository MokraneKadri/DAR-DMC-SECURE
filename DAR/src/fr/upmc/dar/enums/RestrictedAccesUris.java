package fr.upmc.dar.enums;

public enum RestrictedAccesUris {

	
	 CREATEGROUP ("/DAR/create_group"),
	 CREATEEVENT ("/DAR/create_event"),
	 USERLOGIN ("/DAR/signin"),
	 USERSIGNUP("/DAR/signup");
	
	  private String name = "";
	  //Constructeur
	RestrictedAccesUris(String name){
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enum�r�
	  public String getRessourceUrl(){
	    return name.toString();
	  }
}