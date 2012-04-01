package bomberman.outin;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EnvioEmail {

	private Properties props;
	private Session session;
	private MimeMessage message;
	private Transport trans;
	
	public EnvioEmail(){
		
		props = new Properties();
		session = Session.getDefaultInstance(props);
		message = new MimeMessage(session);
		
		try{
			trans = session.getTransport("smtp");
		}catch(NoSuchProviderException e){
			e.printStackTrace();
		}
		
		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port","587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", "ejemplo@gmail.com");

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
		
		session.setDebug(true);
		try{
		// Quien envia el correo
		message.setFrom(new InternetAddress("ejemplo@gmail.com"));
		// A quien va dirigido
		message.addRecipient(Message.RecipientType.TO, new InternetAddress("destinatario@dominio.com"));

		message.setSubject("Hola");
		message.setText(
				"Mensajito con Java Mail<br>" + "<b>de</b> los <i>buenos</i>." + "poque si",
				"ISO-8859-1",
				"html");
		trans.connect("ejemplo@gmail.com","la password");
		trans.sendMessage(message,message.getAllRecipients());
		trans.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}