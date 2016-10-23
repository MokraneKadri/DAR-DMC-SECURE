package fr.upmc.dar.enums;

public enum SignUpFields {

	

	//association champs du formulaire --> valeur du id du champ
	
	 NAME ("/home"),
	 USERNAME ("username"),
	 EMAIL ("email"),
	 PASSWORD ("password"),
	 PASSWORDCONFIRM ("confirmpassword"),
	 UNIVERSITY ("university"),
	 EMAILREGEXP("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"),
	 CURSUS ("cursus"),
	 SEXE("sexe"),
	 USERNAMEREGEXP("[a-zA-Z0-9\\._\\-]{5,}");
	
	
	  private String name = "";
	   
	  //Constructeur
	  SignUpFields(String name){
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enuméré
	  public String getAttributeName(){
	    return name.toString();
	  }
}
