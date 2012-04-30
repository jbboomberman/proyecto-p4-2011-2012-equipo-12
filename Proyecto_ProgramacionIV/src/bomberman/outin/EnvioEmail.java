package bomberman.outin;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import bomberman.managers.ControlPrincipal;

/**
 * Esta clase nos permitirá enviar emails a correos electrónicos.
 * 
 * @author David
 * @version 1.0
 */
public class EnvioEmail {

	// Contendrá las propiedades de la conexión
	private static Properties props = iniciarProperties();
	// La conexión con el servidor de correo.
	private static Session session = Session.getDefaultInstance(props);
	// El mensaje.
	private static MimeMessage message = new MimeMessage(session);
	// Para enviar correos.
	private static Transport trans = iniciarTransport();
	// Password
	private static String pass = "ZXNlYm9tYmVybWFuaGF5";

	private static Properties iniciarProperties() {
		Properties tempProp = new Properties();
		// Nombre del host de correo, es smtp.gmail.com
		tempProp.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si está disponible
		tempProp.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		tempProp.setProperty("mail.smtp.port", "587");

		// Nombre del usuario
		tempProp.setProperty("mail.smtp.user", ControlPrincipal.getJugadorUno()
				.getNick());

		// Si requiere o no usuario y password para conectarse.
		tempProp.setProperty("mail.smtp.auth", "true");

		return tempProp;
	}

	public static Transport iniciarTransport() {
		Transport tempTrans = null;
		try {
			tempTrans = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		return tempTrans;
	}

	public static void enviarMensaje() {

		try {
			// Quien envia el correo
			message.setFrom(new InternetAddress("equipo12Bomberman@gmail.com"));
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					ControlPrincipal.getJugadorUno().getEmail()));
			// El asunto del mensaje
			message.setSubject("Puntuación Bomberman");
			// El texto
			message.setText(EstructuraCorreo.getEstructura(ControlPrincipal
					.getJugadorUno().getNombre()));
			// Nos conectamos a nuetsro correo
			trans.connect("equipo12Bomberman@gmail.com",
					Base64.decodeString(pass));
			// Enviamos el mensaje
			trans.sendMessage(message, message.getAllRecipients());
			trans.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}