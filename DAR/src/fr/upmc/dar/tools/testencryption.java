package fr.upmc.dar.tools;

public class testencryption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 PasswordEncryptor enc = new PasswordEncryptor();
		String encrypted_pass =enc.encryptPassword("king");
		boolean res = enc.checkPassword("kk", encrypted_pass);
		System.out.println(res);
		System.out.println(encrypted_pass.length());
		

	}

}
