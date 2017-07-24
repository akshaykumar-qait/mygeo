package Tests.SendGreetings;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Readers.JsonReader;
import Readers.OptionReader;

public class SendGreets {

	OptionReader read;
	
	
	
	public SendGreets() {
		// TODO Auto-generated constructor stub
	
	read = new OptionReader();
	
	}
	
	
	void send(String recipients) throws IOException {

		final String username = read.optionFileReader("email_username");
		final String password = read.optionFileReader("email_password");
	read.writeit("currentpath", "resource/Recipients/");

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(username));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipients));
			message.setSubject("Testing Automatic Mail Sending");
			message.setText("Dear "+ recipients+" ,So Sry ... for this mail ");

			Transport.send(message);

			System.out.println("Done");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

		// Thread.sleep(10000);

	}

	public static void main(String[] args) throws InterruptedException, IOException {

		
		SendGreets obj = new SendGreets();
		JsonReader readjson  = new  JsonReader();
		
		
		for(int a =1;a<=10;a++)
		{
		obj.send(readjson.readit("res"+a, "recepients"));
		}
		
	}

}
