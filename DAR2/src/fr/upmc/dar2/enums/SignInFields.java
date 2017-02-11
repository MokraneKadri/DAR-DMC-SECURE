package fr.upmc.dar2.enums;

public enum SignInFields {


	 LOGIN ("login"),
	 PASSWORD ("password"),
	EMAILREGEXP("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$"),
	USERNAMEREGEXP("[a-zA-Z0-9\\._\\-]{5,}");
	private String name = "";
	   
	  //Constructeur
	  private SignInFields(String name) {
	
	
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enuméré
	  public String getAttributeName(){
	    return name.toString();
	  }
}
