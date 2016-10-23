package fr.upmc.dar.tools;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.upmc.dar.dao.interfaces.IUserDao;

import fr.upmc.dar.enums.SignInFields;
import fr.upmc.dar.enums.SignUpFields;
import net.sf.json.JSONObject;

public class SignUpValidator {


	private IUserDao user;
	private Map<String,String> committedErrors;
	//on est sur davoir au moins 2 caractère 
	public static final Pattern VALID_EMAIL_ADDRESS_REGEX  = Pattern.compile(SignUpFields.EMAILREGEXP.getAttributeName(), Pattern.CASE_INSENSITIVE);
	//minimum de 5 carac
	public static final Pattern VALID_USERNAME_REGEX = Pattern.compile(SignUpFields.USERNAMEREGEXP.getAttributeName(),Pattern.CASE_INSENSITIVE); 

	public static boolean IsValidEmailFormat(String emailStr) {
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(emailStr);
		return matcher.find();
	}

	public static boolean IsValidUserNameFormat(String username) {
		Matcher matcher = VALID_USERNAME_REGEX .matcher(username);
		return matcher.find();
	}





	public SignUpValidator(IUserDao user,  Map<String,String> committedErrors) {
		super();
		this.user = user;
		this.committedErrors = committedErrors;
	}


	public boolean isAValidEmail(String email) throws Exception{


		if(!IsValidEmailFormat(email)){
			committedErrors.put(SignInFields.LOGIN.getAttributeName(), "l'adresse email indiqué n'est pas valide !");
			return false;
		}


		if(user.findUserByEmail(email)!=null){
			committedErrors.put(SignInFields.LOGIN.getAttributeName(), "l'adresse est déja prise merci d'indiquer une autre !");
			return false;

		}
		return true;


	}


	public boolean isAValidPassword(String passwd){

		if(passwd.isEmpty()){
			committedErrors.put(SignInFields.PASSWORD.getAttributeName(), "le champ mot de passe est obligatoire ,merci de le remplir");
			return false;
		}
		if(passwd.length() <6 ){
			committedErrors.put(SignInFields.PASSWORD.getAttributeName(), "le mot de passe indiqué est trop court !");
			return false;
		}

		return true;
	}

	public boolean isAValidConfirmationPassword(String passwd,String confimpasswd){

		if(!passwd.equals(confimpasswd)){
			committedErrors.put(SignUpFields.PASSWORDCONFIRM.getAttributeName(), "le champ mot de passe ne correspondent pas! ");
			return false;
		}

		return true;
	}

	public boolean isAValidUsername(String username) throws Exception{

		if(IsValidUserNameFormat(username)==false){
			committedErrors.put(SignUpFields.USERNAME.getAttributeName(), "nom d'utilisateur doit conteneir au moins 5 caractères ");
			return false;
		}
		if(user.findUserByUserName(username)!=null){
			committedErrors.put(SignUpFields.USERNAME.getAttributeName(), "nom d'utilisateur déja pris merci de choisir un autre ");
			return false;

		}

		return true;

	}

	public boolean canRegisterUser(String name, String userName, String eMail,String password, String confpassword, String etablissement,
			String cursus) throws Exception{
		
		return (isAValidConfirmationPassword(password, confpassword) && isAValidEmail(eMail) && isAValidPassword(password)&&isAValidUsername(userName))==true;

	}


	public IUserDao getUser() {
		return user;
	}
	public void setUser(IUserDao user) {
		this.user = user;
	}
	public Map<String,String> getCommittedErrors() {
		return committedErrors;
	}
	public void setCommittedErrors(JSONObject committedErrors) {
		this.committedErrors = committedErrors;
	}



}
