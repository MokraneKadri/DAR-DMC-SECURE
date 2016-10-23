package fr.upmc.dar.tools;

public class testencryption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 PasswordEncryptor enc = new PasswordEncryptor();
		String encrypted_pass =enc.encryptPassword("kokakola2010");
		boolean res = enc.checkPassword("kokakola2010", "eLd9952tiC7X7tk3LLEEDj/N6fPx6xj3RMgj2VjbKPlSjWypfSc+AKhMGaMO31hvTICtS1idqIg=");
		System.out.println(res);
		System.out.println(encrypted_pass.length());
		

	}

}
