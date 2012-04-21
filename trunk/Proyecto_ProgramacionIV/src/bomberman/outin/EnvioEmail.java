package bomberman.outin;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import bomberman.jugador.Jugador;

public class EnvioEmail {

	private Properties props;
	private Session session;
	private MimeMessage message;
	private Transport trans;
	private Jugador jugador;

	public EnvioEmail(Jugador jug) {

		this.props = new Properties();
		this.session = Session.getDefaultInstance(props);
		this.message = new MimeMessage(session);
		this.jugador = jug;

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
		props.setProperty("mail.smtp.user", jugador.getNick());

		// Si requiere o no usuario y password para conectarse.
		props.setProperty("mail.smtp.auth", "true");

		session.setDebug(true);
	}

	public void enviarMensaje() {

		try {
			// Quien envia el correo
			message.setFrom(new InternetAddress("equipo12Bomberman@gmail.com"));
			// A quien va dirigido
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(
					jugador.getEmail()));

			message.setSubject("Puntuación Bomberman");
			message.setText(EstructuraCorreo.getEstructura(jugador.getNombre()));
			trans.connect("ejemplo@gmail.com", "la password");
			trans.sendMessage(message, message.getAllRecipients());
			trans.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}