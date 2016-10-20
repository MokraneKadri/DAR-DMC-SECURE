package fr.upmc.dar.enums;

public enum Uri {

	
	//association url--> ressource JSP
	
	 HOME ("/home"),
	 REGISTER ("/JSP/signup.jsp"),
	 LOGIN ("/login"),
	 RESETPASWD ("/rest"),
	 CREATEEVENT ("/createEvent");
	 
	  private String name = "";
	   
	  //Constructeur
	  Uri(String name){
	    this.name = name;
	  }
	   
	  //renvoie la valeur du type enuméré
	  public String getRessourceUrl(){
	    return name.toString();
	  }
}
