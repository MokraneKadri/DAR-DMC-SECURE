package fr.upmc.dar2.tools;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class PasswordEncryptor {

	

	
	/*
	   cryptage � l'aide de la  biblioth�que Jasypt 
	   cryptage � l'aide de l'algo sha-384 qui renvoie 
	   une mdpasse cod� en base64 d'une longueur de 76 caract�res  
	   
	*/
	
	public static String encryptPassword(String password) {
		ConfigurablePasswordEncryptor encryptor = 
				new ConfigurablePasswordEncryptor();
		
		encryptor.setAlgorithm("SHA-384");
		encryptor.setPlainDigest( false );
		encryptor.setStringOutputType("base64");
		return encryptor.encryptPassword(password);
	}
	
	
	//verifie si le mod de passe sous forme de chaine de caract�re correspond 
	//au mot de passe chiffr�
	public static boolean checkPassword(String plainPassword,
			String encryptedPassword) {
		ConfigurablePasswordEncryptor encryptor = 
				new ConfigurablePasswordEncryptor();
		encryptor.setAlgorithm("SHA-384");
		encryptor.setPlainDigest( false );
		// appel a la methode checkPassword
		return encryptor.checkPassword(plainPassword, encryptedPassword);
	}
	
}

