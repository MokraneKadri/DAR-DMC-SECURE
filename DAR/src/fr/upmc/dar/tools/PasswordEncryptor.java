package fr.upmc.dar.tools;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

public class PasswordEncryptor {

	

	
	/*
	   cryptage à l'aide de la  bibliothèque Jasypt 
	   cryptage à l'aide de l'algo sha-384 qui renvoie 
	   une mdpasse codé en base64 d'une longueur de 76 caractères  
	   
	*/
	
	public static String encryptPassword(String password) {
		ConfigurablePasswordEncryptor encryptor = 
				new ConfigurablePasswordEncryptor();
		
		encryptor.setAlgorithm("SHA-384");
		encryptor.setPlainDigest( false );
		encryptor.setStringOutputType("base64");
		return encryptor.encryptPassword(password);
	}
	
	
	//verifie si le mod de passe sous forme de chaine de caractère correspond 
	//au mot de passe chiffré
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

