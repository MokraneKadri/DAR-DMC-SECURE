package fr.upmc.dar.tools;

import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailHelper {

	
	private String from;
	private String to;
	private String subject;
	private String content;
	private String host;
	
	public MailHelper(String from, String to,String subject,String content) {
		super();
		this.from = from;
		this.to = to;
		this.subject=subject;
		this.content=content;
		this.host= "localhost";
		
	}
	
	public void sendEmail(){
		final String username = "noreplay.univ.connect@gmail.com";
		final String password = "kokakola";
		
		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
	  

	      Session session = Session.getDefaultInstance(props,
	    		  new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			  });

	      try {
	        
	         MimeMessage message = new MimeMessage(session);
	         message.setFrom(new InternetAddress(from));
	         message.addRecipient(Message.RecipientType.TO, new InternetAddress(this.to));
	         message.setSubject(subject);
	         Multipart multipart = new MimeMultipart("related");
	         BodyPart htmlPart = new MimeBodyPart();
	         htmlPart.setContent(this.content, "text/html");
	         multipart.addBodyPart(htmlPart);

	         message.setContent(multipart);
	         Transport.send(message);
	         System.out.println("Sent message successfully....");
	      }catch (MessagingException mex) {
	         mex.printStackTrace();
	      }

		
		
		
		
		
		
		
		
		

		
	}
}
