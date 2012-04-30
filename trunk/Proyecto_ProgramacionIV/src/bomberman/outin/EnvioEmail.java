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
 * @author David
 * @version 1.0
 */
public class EnvioEmail {

	//Contendrá las propiedades de la conexión
	private Properties props;
	//La conexión con el servidor de correo.
	private Session session;
	//El mensaje.
	private MimeMessage message;
	//Para enviar correos.
	private Transport trans;

	/**
	 * Constructor principal de la clase EnvioEmail
	 * @param jug - Jugador
	 */
	public EnvioEmail() {

		this.props = new Properties();
		this.session = Session.getDefaultInstance(props);
		this.message = new MimeMessage(session);
		
		try {
			trans = session.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}

		// Nombre del host de correo, es smtp.gmail.com
		props.setProperty("mail.smtp.host", "smtp.gmail.com");

		// TLS si está disponible
		props.setProperty("mail.smtp.starttls.enable", "true");

		// Puerto de gmail para envio de correos
		props.setProperty("mail.smtp.port", "587");

		// Nombre del usuario
		props.setProperty("mail.smtp.user", ControlPrincipal.getJugadorUno().getNick());

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");
	}

	public void enviarMensaje() {

		try {
			// Quien envia el correo
			message.setFrom(new InternetAddress("equipo12Bomberman@gmail.com"));
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					ControlPrincipal.getJugadorUno().getEmail()));
			//El asunto del mensaje
			message.setSubject("Puntuación Bomberman");
			//El texto
			message.setText(EstructuraCorreo.getEstructura(ControlPrincipal.getJugadorUno().getNombre()));
			//Nos conectamos a nuetsro correo
			trans.connect("ejemplo@gmail.com", "la password");
			//Enviamos el mensaje
			trans.sendMessage(message, message.getAllRecipients());
			trans.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}