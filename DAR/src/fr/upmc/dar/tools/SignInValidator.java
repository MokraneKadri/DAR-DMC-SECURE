package fr.upmc.dar.tools;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.upmc.dar.dao.interfaces.IUserDao;
import fr.upmc.dar.enums.LoginType;
import fr.upmc.dar.enums.SignInFields;



public class SignInValidator {


	private IUserDao user;
	private Map<String,String> committedErrors;
	//on est sur davoir au moins 2 caractère 
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX  = Pattern.compile(SignInFields.EMAILREGEXP.getAttributeName(), Pattern.CASE_INSENSITIVE);
	//minimum de 5 carac
	public static final Pattern VALID_USERNAME_REGEX = Pattern.compile(SignInFields.USERNAMEREGEXP.getAttributeName(),Pattern.CASE_INSENSITIVE); 





	public static boolean IsValidEmailFormat(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}

	public static boolean IsValidUserNameFormat(String username) {
		Matcher matcher = VALID_USERNAME_REGEX .matcher(username);
		return matcher.find();
	}
	public SignInValidator(IUserDao user, Map<String,String> committedErrors) {
		super();
		this.user = user;
		this.committedErrors = committedErrors;
	}


	public void isAValidEmail(String login){
		
		if(login.contains("@")){ //l'utilisateur se connecte avec son mail
			if(!IsValidEmailFormat(login))
				committedErrors.put(SignInFields.LOGIN.getAttributeName(), "l'adresse email indiqué n'est pas valide !");
				
		}
	}


	public boolean isAValidPassword(String passwd){

		if(passwd.isEmpty()){
			committedErrors.put(SignInFields.PASSWORD.getAttributeName(), "le champ mot de passe est obligatoire ,merci de le remplir");
			return false;
		}
		if(passwd.length() < 6 ){
			committedErrors.put(SignInFields.PASSWORD.getAttributeName(), "le mot de passe indiqué est trop court !");
			return false;
		}
		return true;
	}

	public void validateCredantials(String login,String passwd) throws Exception{

		if(isAValidPassword(passwd))
		{
			try {
				if(IsValidEmailFormat(login)){
					if(user.findUserByCredantials(login, passwd, LoginType.EMAIL)==null)
						
					committedErrors.put(SignInFields.LOGIN.getAttributeName(), "aucun compte ne correspond a cette adresse mail");
					return ;
				}
					
					if(user.findUserByCredantials(login, passwd, LoginType.USERNAME)==null)
						//user name et password corresponde a un user
						committedErrors.put(SignInFields.LOGIN.getAttributeName(), "aucun compte ne correspond a ce nom d'utilisateur");
						return ;
			}
				 catch (Exception e) {

				e.printStackTrace();
			}
		}


		
	}






	public IUserDao getUser() {
		return user;
	}
	public void setUser(IUserDao user) {
		this.user = user;
	}
	public Map<String, String> getCommittedErrors() {
		return committedErrors;
	}
	public void setCommittedErrors( Map<String,String> committedErrors) {
		this.committedErrors = committedErrors;
	}


}
